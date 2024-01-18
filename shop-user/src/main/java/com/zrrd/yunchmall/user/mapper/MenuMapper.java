package com.zrrd.yunchmall.user.mapper;

import com.zrrd.yunchmall.user.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据管理员Id查询其拥有访问权限的所有菜单
     * @param adminId 管理员Id
     * @param isHidden 查询的菜单显示状态
     * @return 菜单列表
     */
    @Select("SELECT * FROM ums_menu " +
            "WHERE id IN " +
            "( " +
            "SELECT menu_id FROM ums_role_menu_relation WHERE role_id IN " +
            "(SELECT role_id FROM ums_admin_role_relation WHERE admin_id=#{adminId}) " +
            ") AND hidden<>#{isHidden}")
    List<Menu> selectMenusByAdminId(long adminId, int isHidden);
}
