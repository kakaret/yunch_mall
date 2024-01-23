package com.zrrd.yunchmall.sale.mapper;

import com.zrrd.yunchmall.sale.entity.FlashPromotionSession;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 限时购场次表 Mapper 接口
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Repository
public interface FlashPromotionSessionMapper extends BaseMapper<FlashPromotionSession> {
    @Select("SELECT ppr.id AS id, ps.name AS name, ps.start_time AS startTime, ps.end_time AS endTime, ppr.flash_promotion_count AS productCount " +
            "FROM sms_flash_promotion_session AS ps " +
            "INNER JOIN sms_flash_promotion_product_relation AS ppr ON ps.id = ppr.flash_promotion_session_id " +
            "WHERE ppr.flash_promotion_id = #{flashPromotionId}")
    List<Map> selectFlashList(@Param("flashPromotionId") long flashPromotionId);
}
