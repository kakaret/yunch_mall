package com.zrrd.yunchmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("t_user")
public class UserTmp {
    @TableId(type = IdType.AUTO)
    private Integer uid;//主键
    private String username;//用户名
    private String password;//密码
    private String telephone;//手机号
    private String nickName;//昵称
}
