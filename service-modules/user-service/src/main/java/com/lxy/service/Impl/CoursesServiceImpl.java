package com.lxy.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.mapper.CoursesMapper;
import com.lxy.service.CoursesService;
import com.lxy.userEntity.Courses;
import org.springframework.stereotype.Service;

@Service
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper, Courses> implements CoursesService {
}
