package com.judysen.quartz.dao.impl;

import com.judysen.quartz.dao.TaskInformationsDao;
import com.judysen.quartz.entity.TaskInformationsEntity;
import com.judysen.quartz.vo.TaskInformationsDetailVo;
import com.judysen.quartz.vo.TaskInformationsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskInformationsDaoImpl {
	@Autowired
	TaskInformationsDao taskInformationsDao;
	/**
	 * 获取任务列表
	 * @return
	 */
	public List<TaskInformationsEntity> getTaskList(){
		//List<TaskInformationsEntity> taskList = getTemplate().selectList("TaskInformationsMapper.selectTaskInfoList");
		List<TaskInformationsEntity> taskList=taskInformationsDao.findAll();
		return taskList;
	}
	
	/**
	 * 根据taskNo获取任务
	 * @param taskNo
	 * @return
	 */
	public TaskInformationsEntity getTaskByTaskNo(String taskNo){
		TaskInformationsEntity informationsEntity=new TaskInformationsEntity();
		informationsEntity.setId(taskNo);
		TaskInformationsEntity entity = taskInformationsDao.findOne(Example.of(informationsEntity));
		return entity;
	}

	/**
	 * 根据ID更新
	 * @param entity
	 */
	public String updateById(TaskInformationsEntity entity){
		entity=taskInformationsDao.save(entity);
		return entity.getId();
	}
	
	/**
	 * 列表获取
	 * @return
	 */
	public Page<TaskInformationsVo> getList(int currentPage){
 		Page<TaskInformationsEntity> list=taskInformationsDao.findAll(new PageRequest(currentPage-1,15,new Sort(Sort.Direction.ASC,"createTime")));
		List<TaskInformationsVo> informationsVos=new ArrayList<>();
		Page<TaskInformationsVo> pageList=null;
		for(TaskInformationsEntity entity:list){
			TaskInformationsVo vo=new TaskInformationsVo();
			vo.setExecuteParamter(entity.getExecuteParamter());
			vo.setExecutorNo(entity.getExecutorNo());
			vo.setFrozenStatus(entity.getFrozenStatus().name());
			vo.setId(entity.getId());
			vo.setTaskName(entity.getTaskName());
			vo.setSchedulerRule(entity.getSchedulerRule());
			vo.setSendType(entity.getSendType());
			vo.setUrl(entity.getUrl());
			vo.setLastModifyTime(entity.getLastModifyTime());
			vo.setTimeKey(entity.getTimeKey());
			informationsVos.add(vo);
		}
		pageList=new PageImpl<TaskInformationsVo>(informationsVos,new PageRequest(currentPage-1,15),list.getTotalPages());
		return pageList;
	}
	
	public TaskInformationsDetailVo getTaskDetail(String taskNo){
		TaskInformationsEntity example=new TaskInformationsEntity();
		example.setId(taskNo);
		TaskInformationsEntity entity=taskInformationsDao.findOne(Example.of(example));
		TaskInformationsDetailVo vo=null;
		if(entity!=null){
			vo=new TaskInformationsDetailVo();
			vo.setTimeKey(entity.getTimeKey());
			vo.setLastModifyTime(entity.getLastModifyTime());
			vo.setFrozenStatus(entity.getFrozenStatus().name());
			vo.setUrl(entity.getUrl());
			vo.setCreateTime(entity.getCreateTime());
			vo.setExecuteParamter(entity.getExecuteParamter());
			vo.setExecutorNo(entity.getExecutorNo());
			vo.setFrozenTime(entity.getFrozenTime());
			//vo.setPreExecuteTime(entity.get);
			vo.setSchedulerRule(entity.getSchedulerRule());
			vo.setSendType(entity.getSendType());
			//vo.setTaskStatus();
			vo.setId(entity.getId());
			vo.setTaskName(entity.getTaskName());
			vo.setUnfrozenTime(entity.getUnfrozenTime());
		}
		return vo;
	}
	
	public String addTaskInformation(TaskInformationsEntity entity){
		entity=taskInformationsDao.insert(entity);
		return entity.getId();
	}
	
	public TaskInformationsEntity selectById(String id){
		return taskInformationsDao.findOne(id);
	}
	
}
