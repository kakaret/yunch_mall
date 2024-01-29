package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.user.entity.Menu;
import com.zrrd.yunchmall.user.mapper.MenuMapper;
import com.zrrd.yunchmall.user.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private RedisTemplate redisTemplate;

//    什么样的数据适合被缓存：1.频繁访问(热点数据) 2.修改频率不宜太高 3.体量较大的数据不易被缓存
    @Override
    public List<Menu> getTreeList() {
//        命中了缓存(redis数据库中已经存在了menuTree，直接取出并返回)
        if(redisTemplate.hasKey("MenuTree")) {
            return redisTemplate.opsForList().range("MenuTree", 0, -1);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id", "parent_id", "title", "level", "sort", "name", "icon", "hidden");
        queryWrapper.eq("parent_id",0);
        queryWrapper.or();
        queryWrapper.eq("level",0);
        //查询到全部一级菜单
        List<Menu> l1MenuList = super.list(queryWrapper);
        // 循环遍历一级菜单列表
        l1MenuList.forEach(menu-> {
            // 查到每个一级菜单的所有二级菜单列表
            // 清除之前的查询条件
            queryWrapper.clear();
            queryWrapper.select("id", "parent_id", "title", "level", "sort", "name", "icon", "hidden");
            queryWrapper.eq("parent_id",menu.getId());
            menu.setChildren(super.list(queryWrapper));
        });
//        如果缓存中不存在这个MenuTree 那就再查询到之后缓存到redis数据库中
        redisTemplate.opsForList().rightPushAll("MenuTree", l1MenuList);
//        设置这个缓存的有效期为5分钟 （根据经验去判断这个缓存的有效期，评估一下这个数据的修改频率）
        redisTemplate.expire("MenuTree", 5, TimeUnit.MINUTES);
        return l1MenuList;
    }


}
