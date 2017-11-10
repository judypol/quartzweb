package com.judysen.quartz.controller;

import com.judysen.quartz.entity.TaskRecordsEntity;
import com.judysen.quartz.service.TaskRecordsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/taskRecords")
public class TaskRecordsController extends BaseController{
	
	@Resource
	private TaskRecordsService recordsService;
	
	/**
	 * 定时任务记录列表页面展示
	 * @param taskNo
	 * @param currentPage
	 * @return
	 */
	@RequestMapping("/getTaskRecordsListByTaskNo/{taskNo}/{currentPage}/{status}")
	public ModelAndView getTaskRecordsListByTaskNo(@PathVariable String taskNo, @PathVariable int currentPage, @PathVariable String status){
		ModelAndView view = new ModelAndView("/page/taskRecordsList.jsp");
		String taskStatus = "";
		if(StringUtils.isNotBlank(status)){
			taskStatus = status.substring(6);
		}
		Page<TaskRecordsEntity> recordsList = recordsService.getTaskRecordsByTaskNo(taskNo, currentPage,taskStatus);
		Integer count = recordsList.getTotalPages();
//		Page page = new Page(count);
//		page.setCurrentPage(currentPage);
//		page.setList(recordsList);
//		view.addObject("page",page);
		view.addObject("taskNo",taskNo);
		view.addObject("taskStatus",taskStatus);
		return view;
	}
}
