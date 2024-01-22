package com.zrrd.yunchmall.user.service.impl;

import com.zrrd.yunchmall.user.entity.Member;
import com.zrrd.yunchmall.user.mapper.MemberMapper;
import com.zrrd.yunchmall.user.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}
