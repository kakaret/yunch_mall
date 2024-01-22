package com.zrrd.yunchmall.user.mapper;

import com.zrrd.yunchmall.user.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT * FROM ums_menu " +
            "WHERE id IN " +
            "( " +
            "SELECT menu_id FROM ums_role_menu_relation WHERE role_id IN " +
            "(SELECT role_id FROM ums_admin_role_relation WHERE admin_id=#{adminId}) " +
            ") AND hidden<>#{hidden}")
    List<Menu> selectMenusByAdminId(@Param("adminId") long adminId, @Param("hidden") int isHidden);

}
