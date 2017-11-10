package com.judysen.quartz.controller;

import com.alibaba.fastjson.JSON;
import com.judysen.quartz.dao.TaskInformationsDao;
import com.judysen.quartz.dao.impl.TaskInformationsDaoImpl;
import com.judysen.quartz.entity.TaskInformationsEntity;
import com.judysen.quartz.service.QuartzService;
import com.judysen.quartz.util.Const;
import com.judysen.quartz.vo.ResponseMessage;
import com.judysen.quartz.vo.TaskInformationsDetailVo;
import com.judysen.quartz.vo.TaskInformationsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/scheduler")
public class SchedulerController extends BaseController{
	
	@Resource
	private QuartzService quartzServer;
	
	@Autowired
	private TaskInformationsDaoImpl taskInformationsDao;

	/**
	 * 重启指定任务
	 * @param jobKey
	 * @return
	 */
	@RequestMapping("/resumeScheduler")
	public @ResponseBody
    String resumeScheduler(String jobKey){
		logger.info("开始重新调度任务");
		String msg = null;
		try {
			msg = quartzServer.resumeScheduler(jobKey);
			logger.info("重新调度任务完成");
			return msg;
		} catch (Exception e) {
			logger.info("重新调度任务异常：", e);
			return msg;
		}
	}
	
	/**
	 * 增加任务调度
	 * @return
	 */
	@RequestMapping("/addScheduler")
	public @ResponseBody String addScheduler(String taskNo){
		logger.info("增加任务调用开始");
		String msg = null;
		try {
			msg = quartzServer.addScheduler(taskNo);
			logger.info("增加任务调度成功");
			return msg;
		} catch (Exception e) {
			logger.info("异常：",e);
			return msg;
		}
	}
	
	/**
	 * 从定时器中删除指定任务
	 * @param taskNo
	 * @return
	 */
	@RequestMapping("/delScheduler")
	public @ResponseBody
    String delScheduler(String taskNo){
		logger.info("删除任务调用开始");
		String msg = null;
		try {
			msg = quartzServer.delScheduler(taskNo);
			logger.info("删除任务调度成功");
			return msg;
		} catch (Exception e) {
			logger.info("异常：",e);
			return msg;
		}
	}
	
	/**
	 * 全部任务重新调度
	 * @return
	 */
	@RequestMapping("/resumeSchedulerAll")
	public @ResponseBody
    String resumeSchedulerAll(){
		logger.info("开始重新调度任务");
		String msg = null;
		try {
			msg = quartzServer.resumeSchedulerAll();
			logger.info("重新调度任务完成");
			return msg;
		} catch (Exception e) {
			logger.info("重新调度任务异常：", e);
			return msg;
		}
	}
	
	@RequestMapping("/list/{currentPage}")
	public ModelAndView getTaskInformationsList(@PathVariable int currentPage){
		ModelAndView view = new ModelAndView("/scheduler/quartzList");
		//Page<TaskInformationsVo> voList = quartzServer.getList(currentPage);
		//int count = voList.getTotalPages();
		return view;
	}
	@RequestMapping("/getTaskInfoList")
	public @ResponseBody String getTaskInfoList(Integer pageIndex){
		Page<TaskInformationsVo> voList=quartzServer.getList(pageIndex);
		return JSON.toJSONString(voList);
	}
	
	@RequestMapping("/detail/{taskNo}")
	public ModelAndView getTaskDetail(@PathVariable String taskNo){
		ModelAndView view = new ModelAndView("/scheduler/quartzDetails");
		//TaskInformationsDetailVo detail = quartzServer.getTaskDetail(taskNo);
		//view.addObject("detail",detail);
		view.addObject("taskNo",taskNo);
		return view;
	}

	/**
	 * 获取任务详情
	 * @param taskNo
	 * @return
	 */
	@RequestMapping("getTaskDetails")
	public @ResponseBody String getTaskDetails(String taskNo){
		TaskInformationsDetailVo detail=quartzServer.getTaskDetail(taskNo);

		return JSON.toJSONString(detail);
	}
	
	@RequestMapping("/taskEditPage/{id}")
	public ModelAndView taskEditPage(@PathVariable String id){
		ModelAndView view = new ModelAndView("/scheduler/quartzEdit");
		if(StringUtils.isEmpty(id)){
			view.addObject("title","编辑定时任务");
		}else{
			view.addObject("title","新建定时任务");
		}
		view.addObject("id",id);
		return view;
	}

	/**
	 *获取task详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/taskInfo")
	public @ResponseBody String taskInfo(String id){
		TaskInformationsEntity entity=new TaskInformationsEntity();
		entity.setSendType("http");
		if(!StringUtils.isEmpty(id)&&!"0".equals(id)){
			entity=quartzServer.selectTaskInfoById(id);
		}
		return JSON.toJSONString(entity);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String editTaskInfo(@RequestBody TaskInformationsEntity entity){
		String count = quartzServer.editTaskInformation(entity);
		ResponseMessage rm=new ResponseMessage();
		if(!StringUtils.isEmpty(count)){
			if("-10000".equals(count)){
				rm.setStatus(false);
				rm.setMessage(entity.getTaskNo()+"任务已经存在！");
			}else{
				rm.setStatus(true);
				rm.setMessage("创建任务"+entity.getTaskNo()+"成功！");
			}
		}else{
			rm.setStatus(false);
			rm.setMessage("创建任务"+entity.getTaskNo()+"失败！");
		}
		return JSON.toJSONString(rm);
	}
	
	@RequestMapping("/executeOnce")
	@ResponseBody
	public String executeOnce(String taskNo){
		String msg = quartzServer.executeOnce(taskNo);
		return msg;
	}
	
	@RequestMapping("/checkTaskNo/{taskNo}")
	@ResponseBody
	public String checkTaskNo(@PathVariable String taskNo){
		TaskInformationsEntity entity = taskInformationsDao.getTaskByTaskNo(taskNo);
		if(null != entity){
			return Const.FAILED;
		}else{
			return Const.SUCCESS;
		}
	}
	
}
