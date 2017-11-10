package com.judysen.quartz.service;

import com.judysen.quartz.entity.TaskRecordsEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskRecordsService {
	
	public Page<TaskRecordsEntity> getTaskRecordsByTaskNo(String taskNo, int currentPage, String taskStatus);
}
