package com.zrrd.yunchmall.pojo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("ums_admin_permission_relation")
@ApiModel(value = "AdminPermissionRelation对象", description = "后台用户和权限关系表(除角色中定义的权限以外的加减权限)")
public class AdminPermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("管理员id")
    private Long adminId;

    @ApiModelProperty("权限id")
    private Long permissionId;

    @ApiModelProperty("关系类型")
    private Integer type;

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

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AdminPermissionRelation{" +
            "id = " + id +
            ", adminId = " + adminId +
            ", permissionId = " + permissionId +
            ", type = " + type +
        "}";
    }
}
