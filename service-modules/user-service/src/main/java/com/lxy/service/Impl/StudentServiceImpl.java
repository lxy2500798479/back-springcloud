package com.lxy.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.mapper.StudentsMapper;
import com.lxy.service.StudentService;
import com.lxy.userEntity.Students;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl extends ServiceImpl<StudentsMapper, Students> implements StudentService {



    private final StudentsMapper studentsMapper;

    @Override
    public Students getStudentByStuId(String stu_id) {
        return studentsMapper.getByStuId(stu_id);
    }
}