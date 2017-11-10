package com.judysen.quartz.service;

import java.util.List;
import java.util.Map;

public interface TaskErrorsService {
	
	public List<Map<String, String>> getErrorsByRecordId(String recordId);

}
