package com.zrrd.yunchmall.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestOriginParserDefinition implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
//        参数名就是 serviceName 吗？不是 任意参数都可以！
//        必须是请求参数吗？ 不是 请求头（Header）的值也可以做授权规则限制
//        httpServletRequest.getHeader("token");
        return httpServletRequest.getParameter("serviceName");
    }
}
