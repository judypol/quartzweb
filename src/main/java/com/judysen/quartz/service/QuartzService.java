package com.judysen.quartz.service;

import com.judysen.quartz.entity.TaskInformationsEntity;
import com.judysen.quartz.entity.TaskRecordsEntity;
import com.judysen.quartz.vo.TaskInformationsDetailVo;
import com.judysen.quartz.vo.TaskInformationsVo;
import javafx.concurrent.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuartzService {

	public void initScheduler();
	
	public String resumeScheduler(String key);
	
	public String resumeSchedulerAll();
	
	public String addScheduler(String key);
	
	public TaskRecordsEntity beforeExecute(String taskNo);
	
	public void updateTaskInformations(String taskNo);
	
	public TaskRecordsEntity modifyTaskRecord(TaskRecordsEntity entity);

	public void saveTaskErrors(String taskRecordsId, String key, String values);
	
	public String delScheduler(String key);
	
	public Page<TaskInformationsVo> getList(int currentPage);
	
	public TaskInformationsDetailVo getTaskDetail(String taskNo);
	
	public String editTaskInformation(TaskInformationsEntity entity);
	
	public TaskInformationsEntity selectTaskInfoById(String id);
	
	public String executeOnce(String taskNo);
}
