package com.judysen.quartz.service;

public interface NoticeService {
	
	public String sendMQmsg(String targetBeanId, String executorNo, String executeParamter) throws Exception;

	public String httpRequest(String url, String executeParamter) throws Exception;

}
