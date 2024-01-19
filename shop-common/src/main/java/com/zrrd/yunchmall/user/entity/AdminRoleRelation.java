package com.zrrd.yunchmall.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 后台用户和角色关系表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("ums_admin_role_relation")
@ApiModel(value = "AdminRoleRelation对象", description = "后台用户和角色关系表")
@NoArgsConstructor
@AllArgsConstructor
public class AdminRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("管理员id")
    private Long adminId;

    @ApiModelProperty("角色id")
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "AdminRoleRelation{" +
            "id = " + id +
            ", adminId = " + adminId +
            ", roleId = " + roleId +
        "}";
    }
}
