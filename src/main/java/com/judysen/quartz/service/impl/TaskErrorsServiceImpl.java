package com.judysen.quartz.service.impl;

import com.judysen.quartz.dao.TaskErrorsDao;
import com.judysen.quartz.dao.impl.TaskErrorsDaoImpl;
import com.judysen.quartz.service.TaskErrorsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TaskErrorsServiceImpl implements TaskErrorsService {
	
	@Resource
	private TaskErrorsDaoImpl errorsDao;
	
	public List<Map<String, String>> getErrorsByRecordId(String recordId){
		return errorsDao.getErrorsByTaskExecuteRecordId(recordId);
	}

}
