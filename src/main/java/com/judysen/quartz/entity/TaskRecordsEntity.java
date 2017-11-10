package com.judysen.quartz.entity;

import com.judysen.quartz.enums.StatusEnum;
import org.springframework.data.annotation.Id;

public class TaskRecordsEntity extends Entity {

	@Id
	private String id;
	private String taskNo;
	private String timeKeyValue;
	private String executeTime;
	private StatusEnum taskStatus;
	private Integer failcount;
	private String failReason;
	private String createTime;
	private String lastModifyTime;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getTimeKeyValue() {
		return timeKeyValue;
	}

	public void setTimeKeyValue(String timeKeyValue) {
		this.timeKeyValue = timeKeyValue;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public StatusEnum getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(StatusEnum taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getFailcount() {
		return failcount;
	}

	public void setFailcount(Integer failcount) {
		this.failcount = failcount;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
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
