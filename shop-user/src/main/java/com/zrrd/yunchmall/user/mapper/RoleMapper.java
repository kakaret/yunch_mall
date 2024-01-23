package com.zrrd.yunchmall.user.mapper;

import com.zrrd.yunchmall.user.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据管理员ID查询角色列表
     * @param adminId 管理员ID
     * @return
     * */
    @Select("SELECT * FROM ums_role WHERE id " +
            "IN (SELECT role_id FROM ums_admin_role_relation WHERE admin_id=#{adminId})")
    List<Role> selectRolesByAdminId(@Param("adminId") long adminId);
}
