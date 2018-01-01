package com.judysen.quartz.controller;

import com.alibaba.fastjson.JSON;
import com.judysen.quartz.entity.TaskRecordsEntity;
import com.judysen.quartz.service.TaskRecordsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/records")
public class TaskRecordsController extends BaseController{
	
	@Resource
	private TaskRecordsService recordsService;
	
	/**
	 * 定时任务记录列表页面展示
	 * @param taskNo
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping(value={"/getTaskRecordsList/{taskNo}/{pageIndex}/{status}"})
	public @ResponseBody
	String getTaskRecordsList(@PathVariable String taskNo, @PathVariable int pageIndex,@PathVariable String status){
		String taskStatus = "";
		if(!"status".equals(status)){
			taskStatus=status;
		}
		Page<TaskRecordsEntity> recordsList = recordsService.getTaskRecordsByTaskNo(taskNo, pageIndex,taskStatus);

		return JSON.toJSONString(recordsList);
	}

	/**
	 * 获取定时任务列表页面
	 * @param taskNo 任务ID号
	 * @param currentPage 页数
	 * @param status 状态
	 * @return
	 */
	@RequestMapping(value = {"/taskRecords/{taskNo}/{currentPage}/{status}"})
	public ModelAndView getTaskRecords(@PathVariable String taskNo,@PathVariable int currentPage,@PathVariable String status){
		ModelAndView view=new ModelAndView("/scheduler/taskRecordsList");
		view.addObject("taskNo",taskNo);
		view.addObject("status",status);
		view.addObject("pageIndex",currentPage);
		return view;
	}
}
