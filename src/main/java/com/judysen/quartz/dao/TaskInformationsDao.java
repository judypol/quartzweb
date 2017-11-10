package com.judysen.quartz.dao;

import com.judysen.quartz.entity.TaskInformationsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

public interface TaskInformationsDao extends MongoRepository<TaskInformationsEntity,String> {
	
//	public List<TaskInformationsEntity> getTaskList();
//
//	public TaskInformationsEntity getTaskByTaskNo(String taskNo);
//
//	public int updateById(TaskInformationsEntity entity);
//
//	public List<TaskInformationsVo> getList(int currentPage);
//
//	public int getTotalCount();
//
//	public TaskInformationsDetailVo getTaskDetail(String taskNo);
//
//	public int addTaskInformation(TaskInformationsEntity entity);
//
//	public TaskInformationsEntity selectById(long id);
	
}
