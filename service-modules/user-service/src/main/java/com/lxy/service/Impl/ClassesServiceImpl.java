package com.lxy.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.mapper.ClassesMapper;
import com.lxy.service.ClassesService;
import com.lxy.userEntity.Classes;
import org.springframework.stereotype.Service;

@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {
}
