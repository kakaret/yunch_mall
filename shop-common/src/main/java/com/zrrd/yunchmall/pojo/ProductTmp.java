package com.zrrd.yunchmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@TableName 完成第一级映射（Java实体类和数据库表的映射）
//@TableId 完成第二级映射（实体类属性和表字段的映射）


@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("t_product")
public class ProductTmp {
    @TableId(type = IdType.AUTO)
    private Integer pid;//主键
    private String pname;//商品名称
    private Double pprice;//商品价格
    private Integer stock;//库存
}