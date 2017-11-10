package com.judysen.quartz.dao.impl;

import com.judysen.quartz.dao.TaskRecordsDao;
import com.judysen.quartz.entity.TaskRecordsEntity;
import com.judysen.quartz.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskRecordsDaoImpl{
	@Resource
	TaskRecordsDao taskRecordsDao;
	/**
	 * 根据taskNo查询记录表
	 * @param taskNo
	 * @return
	 */
	public TaskRecordsEntity getRecordsByTaskNo(String taskNo, String timeKeyValue){
		TaskRecordsEntity entity=new TaskRecordsEntity();
		entity.setTaskNo(taskNo);
		entity.setTimeKeyValue(timeKeyValue);
		TaskRecordsEntity taskRecordsEntity = taskRecordsDao.findOne(Example.of(entity));
		return taskRecordsEntity;
	}
	
	/**
	 * 保存
	 * @param taskRecordsEntity
	 */
	public String insert(TaskRecordsEntity taskRecordsEntity){
		taskRecordsDao.save(taskRecordsEntity);
		return taskRecordsEntity.getId();
	}
	
	/**
	 * 根据ID修改
	 * @param recordsEntity
	 */
	public void updateById(TaskRecordsEntity recordsEntity){
		taskRecordsDao.save(recordsEntity);
	}
	
	public TaskRecordsEntity selectByTaskNoAndStatus(String taskNo){
		TaskRecordsEntity entity=new TaskRecordsEntity();
		entity.setTaskNo(taskNo);
		List<TaskRecordsEntity> recordsList = taskRecordsDao.findAll(Example.of(entity));
		if(null != recordsList){
			return recordsList.get(0);
		}else{
			return null;
		}
	}
	
	public Page<TaskRecordsEntity> getListByTaskNo(String taskNo,int currentPage,String taskStatus){
		TaskRecordsEntity entity=new TaskRecordsEntity();
		entity.setTaskNo(taskNo);
		entity.setTaskStatus(StatusEnum.valueOf(taskStatus));

		Page<TaskRecordsEntity> pageList = taskRecordsDao.findAll(Example.of(entity), new PageRequest(currentPage,15));
		return pageList;
	}
}
