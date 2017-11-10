package com.judysen.quartz.service.impl;

import com.judysen.quartz.dao.TaskRecordsDao;
import com.judysen.quartz.dao.impl.TaskRecordsDaoImpl;
import com.judysen.quartz.entity.TaskRecordsEntity;
import com.judysen.quartz.service.TaskRecordsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskRecordsServiceImpl implements TaskRecordsService {
	
	@Resource
	private TaskRecordsDaoImpl recordsDao;
	
	public Page<TaskRecordsEntity> getTaskRecordsByTaskNo(String taskNo, int currentPage, String taskStatus){
		return recordsDao.getListByTaskNo(taskNo, currentPage,taskStatus);
	}
}
