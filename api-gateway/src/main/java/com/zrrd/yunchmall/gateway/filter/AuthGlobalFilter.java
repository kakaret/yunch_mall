package com.zrrd.yunchmall.gateway.filter;

import com.zrrd.yunchmall.gateway.client.AuthServiceClient;
import com.zrrd.yunchmall.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 再往观众实现统一的权限认证
 * 针对每个请求头或请求参数中的token令牌进行有效性验证
 * 如果有效就完成后续的路由操作 否则直接返回401错误（无法访问）
 */
//第一步 声明一个Filter类 实现GlobalFilter接口
@Service //第三步 将这个类注入Spring的Bean容器
@ConfigurationProperties("auth-filter") //从yml配置文件中将auth-filter属性的值注入到该类
@SuppressWarnings("all")
public class AuthGlobalFilter implements GlobalFilter, Ordered {
//    不需要进行鉴权的请求路径集合 （excludes包括的路径直接执行）
    private List<String> excludes;
//    设置一个基于配置文件的开关 如果是true 过滤器工作, false 过滤器关闭
//    @Value("${auth-filter.enabled}")
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }

    @Autowired
    private AuthServiceClient authService;
    @Autowired
    private RedisTemplate redisTemplate;

//     保证我们自定义的过滤器位于整个过滤器链路的最前端
    @Override
    public int getOrder() {
        return 1;
    }

    //  第二步 重写filter方法
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//        当enabled==false 直接跳过当前过滤器
        if(!enabled) return chain.filter(exchange);

        String uri = exchange.getRequest().getURI().getPath();
//        如果是包含在放行路径集合里的URI 直接通过鉴权环节
        if(excludes.contains(uri)) {
            return chain.filter(exchange);
        }
        // Authorization这个请求头中要包含jwt token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
//        1.没有token 2.token过期了 3.token无效（被伪造） 4.token令牌在redis中未找到
        String key = "LOGIN_TOKEN_" + token.substring(token.lastIndexOf(".") + 1);
        System.out.println(redisTemplate.hasKey(key));
        if(token == null
                || JwtUtil.parseAdminToken(token.substring(7)) == null
                || !redisTemplate.hasKey(key)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); //响应结束 方法终止
        } else {
            return chain.filter(exchange);
        }
    }

}
