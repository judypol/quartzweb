package com.judysen.quartz.service.impl;

import com.judysen.quartz.service.NoticeService;
import com.judysen.quartz.util.HttpClientUtil;
import com.judysen.quartz.util.JmsClient;
import com.judysen.quartz.util.OkHttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
//	@Resource
//	private JmsClient jmsClient;
	//@Resource
	//private Destination quartzTopic;
	
	/**
	 * @author ZQ
	 * @date 2016-2-26
	 * @param targetBeanId
	 * @param executorNo
	 * @param executeParamter
	 */
	public void sendMQmsg(String targetBeanId,String executorNo,String executeParamter) throws Exception {
//		try {
//			if (StringUtils.isBlank(targetBeanId)|| StringUtils.isBlank(executorNo)) {
//				logger.error("param is null ");
//				return;
//			}
//			String message = targetBeanId + "," + executorNo + "," + executeParamter;
//			jmsClient.sendMessage(quartzTopic, message);
//			logger.info("quartzTopic send succeed"+message);
//		} catch (Exception e) {
//			logger.error("sendMQmsg send is failed：", e);
//			throw e;
//		}
	}
	
	
	
	/**
	 * @lzh
	 * @date 2016-2-26
	 * @param executeParamter
	 * @param url
	 */
	public void httpRequest(String url,String executeParamter) throws Exception{
		try {
			if(StringUtils.isBlank(url)){
				logger.error("param is null ");
				return;
			}

			String res = OkHttpUtil.doPost(url,executeParamter);
			
			logger.info("http request result is "+res);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("http request is failed：",e);
			throw e;
		}
	}

}
