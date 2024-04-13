package com.lxy.userEntity.VO;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminAllocationTeachVO implements Serializable {

    private List<TeacherAllocationVO> teachers;
    private AllocationStatisticsVO statistics;

}
