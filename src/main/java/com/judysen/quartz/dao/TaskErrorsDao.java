package com.judysen.quartz.dao;

import com.judysen.quartz.entity.TaskErrorsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;

public interface TaskErrorsDao extends MongoRepository<TaskErrorsEntity,String> {
	
//	public void saveTaskErrors(TaskErrorsEntity taskErrorsEntity);
//
//	public List<Map<String, String>> getErrorsByTaskExecuteRecordId(String recordId);
}
