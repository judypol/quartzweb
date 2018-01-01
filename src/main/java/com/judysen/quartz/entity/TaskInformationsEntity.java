package com.judysen.quartz.entity;

import com.judysen.quartz.enums.TaskStatusEnum;
import org.springframework.data.annotation.Id;

public class TaskInformationsEntity extends Entity{

	
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private Integer version;
	private String taskName;
	private String schedulerRule;
	private TaskStatusEnum frozenStatus;
	private String executorNo;
	private String sendType;
	private String url;
	private String executeParamter;
	private String frozenTime;
	private String unfrozenTime;
	private String createTime;
	private String lastModifyTime;
	private String timeKey;
//--执行服务方法
	private String serviceName;
	private String methodName;
	private String params;

	public String getTimeKey() {
		return timeKey;
	}

	public void setTimeKey(String timeKey) {
		this.timeKey = timeKey;
	}

	public String getFrozenTime() {
		return frozenTime;
	}

	public void setFrozenTime(String frozenTime) {
		this.frozenTime = frozenTime;
	}

	public String getUnfrozenTime() {
		return unfrozenTime;
	}

	public void setUnfrozenTime(String unfrozenTime) {
		this.unfrozenTime = unfrozenTime;
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

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExecuteParamter() {
		return executeParamter;
	}

	public void setExecuteParamter(String executeParamter) {
		this.executeParamter = executeParamter;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getSchedulerRule() {
		return schedulerRule;
	}

	public void setSchedulerRule(String schedulerRule) {
		this.schedulerRule = schedulerRule;
	}

	public TaskStatusEnum getFrozenStatus() {
		return frozenStatus;
	}

	public void setFrozenStatus(TaskStatusEnum frozenStatus) {
		this.frozenStatus = frozenStatus;
	}

	public String getExecutorNo() {
		return executorNo;
	}

	public void setExecutorNo(String executorNo) {
		this.executorNo = executorNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}
