package com.atguigu.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.activity.listener.NoListener;
import com.atguigu.activity.listener.YesListener;

@SuppressWarnings("unused")
public class Test {
	
	ApplicationContext app = new ClassPathXmlApplicationContext("spring/spring*.xml");
	ProcessEngine service = app.getBean(ProcessEngine.class);
	//使用流程引擎ProcessEngine以此创建相关数据库表
	@org.junit.Test
	public void test() {
		System.out.println(service);
	}
	
	//使用RepositoryService进行流程的部署
	@org.junit.Test
	public void test2() {
		RepositoryService repositoryService = service.getRepositoryService();
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("MyProcess9.bpmn").deploy();
		System.out.println(deploy);//DeploymentEntity[id=301, name=null]
	}
	
	//使用RepositoryService创建流程定义查询ProcessDefinitionQuery，使用相关api查询流程定义相关信息
	@org.junit.Test
	public void test3() {
		RepositoryService repositoryService = service.getRepositoryService();
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		//查询流程部署信息的列表
//		List<ProcessDefinition> list = query.list();
//		for(ProcessDefinition processDefinition:list) {
//			System.out.println(processDefinition.getId());
//			System.out.println(processDefinition.getKey());
//			System.out.println(processDefinition.getName());
//			System.out.println(processDefinition.getVersion());
//			System.out.println("---------------------");
//		}
		
		//查询流程部署信息的总数量
//		long count = query.count();
//		System.out.println("count="+count);
		
		//查询流程部署最新一次部署的信息
//		ProcessDefinition result = query.latestVersion().singleResult();
//		System.out.println("result="+result);
		
		//按流程定义的版本号倒序排序，查询流程部署的信息
		ProcessDefinitionQuery query2 = query.orderByProcessDefinitionVersion().desc();
		List<ProcessDefinition> listPage = query2.listPage(0, 2);
		for(ProcessDefinition processDefinition:listPage) {
			System.out.println(processDefinition.getId());
			System.out.println(processDefinition.getKey());
			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getVersion());
			System.out.println("---------------------");
		}
		
	} 
	
	//使用RepositoryService创建流程定义以获取某个流程的id，
	//使用RuntimeService根据流程id创建流程实例ProcessInstance
	@org.junit.Test
	public void test4() {
		ProcessDefinition result = service.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		ProcessInstance instance = service.getRuntimeService().startProcessInstanceById(result.getId());
		System.out.println(instance);//ProcessInstance[1501]
		
	}
	
	//创建TaskService，然后创建TaskQuery，以此查询个人的的流程任务
	@org.junit.Test
	public void test5() {
		TaskService taskService = service.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		List<Task> list2 = taskQuery.taskAssignee("lisi").list();
		System.out.println("zhangsan");
		for (Task task : list) {
			System.out.println("id="+task.getId());
			System.out.println("name="+task.getName());
			//id=504
			//name=组长审批
			taskService.complete(task.getId());
		}
		System.out.println("lisi");
		for (Task task : list2) {
			System.out.println("id="+task.getId());
			System.out.println("name="+task.getName());
			//id=602
			//name=经理审批
			taskService.complete(task.getId());
		}
	}

	//创建HistoryService，然后创建HistoricProcessInstanceQuery，查询流程定义历史再根据id创建相关实例
	@org.junit.Test
	public void test6() {
		HistoryService historyService = service.getHistoryService();
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		HistoricProcessInstance result = query.processDefinitionId("401").finished().singleResult();
		System.out.println(result);
	}
	
	//部署流程，启动流程实例，测试领取任务
	@org.junit.Test
	public void test7() {
		TaskService taskService = service.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskCandidateGroup("mana").list();
		long count = taskQuery.taskAssignee("zhangsan").count();
		System.out.println("zhangsan任务1:"+count);
		for(Task task:list) {
			System.out.println("id="+task.getId()+";name="+task.getName());
			taskService.claim(task.getId(), "zhangsan");
		}
		taskQuery = taskService.createTaskQuery();
		long count1 = taskQuery.taskAssignee("zhangsan").count();
		System.out.println("zhangsan任务2:"+count);
		
	}
	
	//流程变量
	@org.junit.Test
	public void test8() {
		ProcessDefinition result = service.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("tl", "zhangsan");
//		map.put("pm", "lisi");
		map.put("day", 3);
		ProcessInstance instance = service.getRuntimeService().startProcessInstanceById(result.getId(), map);
		System.out.println(instance);//ProcessInstance[2401]
		
	}
	
	//测试排他网关
	@org.junit.Test
	public void test9() {
		TaskService taskService = service.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		for(Task task:list) {
			taskService.complete(task.getId());
		}
		
	}
	
	//在流程中添加监听器
	@org.junit.Test
	public void test10() {
		ProcessDefinition result = service.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		RuntimeService runtimeService = service.getRuntimeService();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("yesListener", new YesListener());
		map.put("noListener", new NoListener());
		ProcessInstance instance = runtimeService.startProcessInstanceById(result.getId(), map);
//		List<ProcessInstance> list = service.getRuntimeService().createProcessInstanceQuery().list();
//		for (ProcessInstance processInstance : list) {
//			System.out.println(processInstance.getProcessVariables().get("noListener"));
//		}
		System.out.println(instance.getProcessVariables());//ProcessInstance[401]
		
	}
	
	@org.junit.Test
	public void test11() {
		ProcessDefinition result = service.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		TaskService taskService = service.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		for(Task task:list) {
			taskService.setVariable(task.getId(), "flag", "true");
			//System.out.println(taskService.getVariable(task.getId(), "flag"));
			taskService.complete(task.getId());
		}
		
	}	
	
}
