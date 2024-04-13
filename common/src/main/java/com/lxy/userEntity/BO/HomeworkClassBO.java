package com.lxy.userEntity.BO;

import com.lxy.userEntity.Homeworks;
import lombok.Data;

import java.util.List;

@Data
public class HomeworkClassBO {

    Homeworks homeworks;
    List<Integer> classId;
}
