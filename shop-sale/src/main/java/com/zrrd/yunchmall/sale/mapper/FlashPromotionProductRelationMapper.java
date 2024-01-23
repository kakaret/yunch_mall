package com.zrrd.yunchmall.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.product.entity.Product;
import com.zrrd.yunchmall.sale.entity.FlashPromotionProductRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 * 商品限时购与商品关系表 Mapper 接口
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Mapper
public interface FlashPromotionProductRelationMapper extends BaseMapper<FlashPromotionProductRelation> {

    @Select("select * from pms_product where id = #{param1}")
    Product selectProductById(int productId);

    @Results({
        @Result(property = "product", column = "product_id",
                one = @One(select = "com.zrrd.yunchmall.sale.mapper.FlashPromotionProductRelationMapper.selectProductById"))

    })
    @Select("SELECT * from sms_flash_promotion_product_relation where " +
            "(flash_promotion_session_id = #{flashPromotionSessionId}) and (flash_promotion_id=#{flashPromotionId})")
    IPage<FlashPromotionProductRelation>
    selectProductByFlashPromotionIdAndFlashPromotionSessionId(IPage page,
                                                              @Param("flashPromotionId")long flashPromotionId,
                                                              @Param("flashPromotionSessionId")long flashPromotionSessionId);
}
