package com.judysen.quartz.controller;

import com.judysen.quartz.service.TaskErrorsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/taskErrors")
public class TaskErrorsController extends BaseController{
	
	@Resource
	private TaskErrorsService errorsService;
	
	@RequestMapping("/getErrors/{recordId}")
	public ModelAndView getErrors(@PathVariable String recordId){
		ModelAndView view = new ModelAndView("/page/taskErrorsDetail.jsp");
		List<Map<String, String>> list = errorsService.getErrorsByRecordId(recordId);
		view.addObject("list",list);
		return view;
	}

}
