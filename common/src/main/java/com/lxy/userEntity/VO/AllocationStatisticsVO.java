package com.lxy.userEntity.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class AllocationStatisticsVO implements Serializable {

    private int totalTeachers;
    private int unallocatedTeachers;
    private int registeredTeachers;

    private static final long serialVersionUID = 1L;
}
