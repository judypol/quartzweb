package com.judysen.quartz.dao;

import com.judysen.quartz.entity.TaskRecordsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskRecordsDao extends MongoRepository<TaskRecordsEntity,String> {

//	public TaskRecordsEntity getRecordsByTaskNo(String taskNo, String timeKeyValue);
//
//	public Long insert(TaskRecordsEntity taskRecordsEntity);
//
//	public void updateById(TaskRecordsEntity recordsEntity);
//
//	public TaskRecordsEntity selectByTaskNoAndStatus(String taskNo);
//
//	public List<TaskRecordsEntity> getListByTaskNo(String taskNo, int currentPage, String taskStatus);
//
//	public Integer getCountByTaskNo(String taskNo, String taskStatus);
}
