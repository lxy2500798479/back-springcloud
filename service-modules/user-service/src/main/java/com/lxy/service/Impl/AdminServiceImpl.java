package com.lxy.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.mapper.AdminsMapper;
import com.lxy.mapper.TeacherClassCoursesMapper;
import com.lxy.service.AdminService;
import com.lxy.userEntity.Admins;
import com.lxy.userEntity.BO.AdminAllocationBO;
import com.lxy.userEntity.TeacherClassCourses;
import com.lxy.userEntity.VO.AdminAllocationTeachVO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminServiceImpl extends ServiceImpl<AdminsMapper, Admins> implements AdminService {

    private final TeacherClassCoursesMapper teacherClassCoursesMapper;

    @Override
    public AdminAllocationTeachVO getAdminAllocationTeachVOList() {
        return null;
    }

    @Override
    public int AdminAddOrDeleteAllocation(AdminAllocationBO adminAllocationBO) {
        List<TeacherClassCourses> list = generateTeacherClassCoursesList(adminAllocationBO);

        if ("add".equals(adminAllocationBO.getOperation())) {
            // 这里调用批量添加的mapper方法
            return teacherClassCoursesMapper.insertBatch(list);
        } else {
            // 这里调用批量删除的mapper方法，注意: 需要实现此方法
            return teacherClassCoursesMapper.deleteBatch(list);
        }
    }

    private List<TeacherClassCourses> generateTeacherClassCoursesList(AdminAllocationBO adminAllocationBO) {
        System.out.println(adminAllocationBO.toString());
        return adminAllocationBO.getClasses().stream()
                .flatMap(cls -> adminAllocationBO.getCourses().stream()
                        .map(crs -> {
                            TeacherClassCourses teacherClassCourses = new TeacherClassCourses();
                            teacherClassCourses.setUserId(adminAllocationBO.getUserId());
                            teacherClassCourses.setClassId(cls.getClassId());
                            teacherClassCourses.setCourseId(crs.getCourseId());
                            return teacherClassCourses;
                        }))
                .collect(Collectors.toList());
    }
}
