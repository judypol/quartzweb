package com.judysen.quartz.dao.impl;

import com.judysen.quartz.dao.TaskErrorsDao;
import com.judysen.quartz.entity.TaskErrorsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("taskErrorsDao")
public class TaskErrorsDaoImpl {
	@Autowired
	TaskErrorsDao taskErrorsDao;
	/**
	 * 保存
	 * @param taskErrorsEntity
	 */
	public void saveTaskErrors(TaskErrorsEntity taskErrorsEntity){
		//getTemplate().insert("TaskErrorsMapper.insert",taskErrorsEntity);
		taskErrorsDao.save(taskErrorsEntity);
	}
	
	public List<Map<String, String>> getErrorsByTaskExecuteRecordId(String recordId){
		TaskErrorsEntity entity=new TaskErrorsEntity();
		entity.setTaskExecuteRecordId(recordId);
		List<TaskErrorsEntity> errors= taskErrorsDao.findAll(Example.of(entity));
		List<Map<String,String>> list=new ArrayList<>();
		for (TaskErrorsEntity error:errors) {
			Map<String,String> map=new HashMap<>();
			map.put(error.getErrorKey(),error.getErrorValue());
			list.add(map);
		}
		return list;
	}
}