package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.userEntity.ClassHomeworks;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassHomeworksMapper extends BaseMapper<ClassHomeworks> {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(ClassHomeworks record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ClassHomeworks record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ClassHomeworks selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ClassHomeworks record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ClassHomeworks record);

    int insertBatch(List<ClassHomeworks> classHomeworksList);
}