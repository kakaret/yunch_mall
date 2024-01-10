package com.zrrd.yunchmall.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.pojo.UserTmp;
import com.zrrd.yunchmall.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserTmp> {
}
