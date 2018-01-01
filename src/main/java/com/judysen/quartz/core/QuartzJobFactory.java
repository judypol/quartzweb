package com.judysen.quartz.core;

import com.judysen.quartz.entity.TaskRecordsEntity;
import com.judysen.quartz.enums.StatusEnum;
import com.judysen.quartz.service.NoticeService;
import com.judysen.quartz.service.QuartzService;
import com.judysen.quartz.util.Const;
import com.judysen.quartz.util.ExceptionUtils;
import com.judysen.quartz.util.SpringContext;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.atomic.AtomicInteger;

@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

    protected Logger logger = Logger.getLogger(this.getClass());

    AtomicInteger ai = new AtomicInteger(0);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ai = new AtomicInteger(0);
        String targetBeanId = context.getMergedJobDataMap().getString("targetObjectId");
        String executorNo = context.getMergedJobDataMap().getString("executorNo");
        String sendType = context.getMergedJobDataMap().getString("sendType");
        String executeParamter = context.getMergedJobDataMap().getString("executeParamter");
        String url = context.getMergedJobDataMap().getString("url");
        logger.info("targetBeanId:" + targetBeanId + "|executorNo:" + executorNo + "|sendType:" + sendType
                + "|executeParamter:" + executeParamter + "|url:" + url);
        QuartzService quartzService = (QuartzService) SpringContext.getBean("quartzService");
        TaskRecordsEntity recordsEntity = null;
        try {
            recordsEntity = quartzService.beforeExecute(targetBeanId);
            if (null == recordsEntity || (StatusEnum.INIT != recordsEntity.getTaskStatus())) {
                logger.error("targetBeanId:" + targetBeanId + " record error return");
                return;
            }
            NoticeService noticeService = (NoticeService) SpringContext.getBean("noticeService");
            if (Const.ACTIVE_MQ.equals(sendType)) {

                noticeService.sendMQmsg(targetBeanId, executorNo, executeParamter);

            } else if (Const.URL_REQUEST.equals(sendType)) {
                noticeService.httpRequest(url, executeParamter);
            } else if (Const.RPC_REQUEST.equals(sendType)) {
                noticeService.rpcRequest(url, executeParamter);
            } else {
                logger.info("不支持改类型任务调度，当前类型为：" + sendType);
            }
        } catch (Exception e) {
            logger.info("QuartzJobFactory-execute thorw：", e);
            ai.incrementAndGet();
            quartzService.saveTaskErrors(String.valueOf(recordsEntity.getId()), executorNo + e.getMessage(), ExceptionUtils.getStackTrace(e));
        }finally {
            if(null!=recordsEntity){
                recordsEntity = quartzService.modifyTaskRecord(ai.get(), recordsEntity.getId());
            }
            quartzService.updateTaskInformations(targetBeanId);
        }
        if (ai.get() > 0) {
            if (null != recordsEntity) {
                recordsEntity = quartzService.modifyTaskRecord(ai.get(), recordsEntity.getId());
            }
            quartzService.updateTaskInformations(targetBeanId);
        }
        // 接收到执行端返回结果在修改任务为未冻结
		/*finally{
			if(null!=recordsEntity){
				recordsEntity = quartzService.modifyTaskRecord(ai.get(), recordsEntity.getId());
			}
			quartzService.updateTaskInformations(targetBeanId);
		}*/
    }


}
