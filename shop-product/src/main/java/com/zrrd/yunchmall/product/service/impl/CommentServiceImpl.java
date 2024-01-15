package com.zrrd.yunchmall.product.service.impl;

import com.zrrd.yunchmall.product.entity.Comment;
import com.zrrd.yunchmall.product.mapper.CommentMapper;
import com.zrrd.yunchmall.product.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
