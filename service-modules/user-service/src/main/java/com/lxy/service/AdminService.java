package com.lxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.userEntity.Admins;

import com.lxy.userEntity.BO.AdminAllocationBO;
import com.lxy.userEntity.VO.AdminAllocationTeachVO;



public interface AdminService extends IService<Admins> {

    AdminAllocationTeachVO getAdminAllocationTeachVOList();

    int AdminAddOrDeleteAllocation(AdminAllocationBO adminAllocationBO);



}
