package com.judysen.quartz.entity;

import org.springframework.data.annotation.Id;

public class TaskErrorsEntity extends Entity{
	@Id
	private String id;
	private String taskExecuteRecordId;
	private String errorKey;
	private String errorValue;
	private String createTime;
	private String lastModifyTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskExecuteRecordId() {
		return taskExecuteRecordId;
	}

	public void setTaskExecuteRecordId(String taskExecuteRecordId) {
		this.taskExecuteRecordId = taskExecuteRecordId;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public String getErrorValue() {
		return errorValue;
	}

	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

}
