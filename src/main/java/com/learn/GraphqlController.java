package com.learn;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.service.WorkerService;

import graphql.ExecutionInput;
import graphql.GraphQL;



@RestController
public class GraphqlController {
	@Autowired
	WorkerService wsi;
	private GraphQL graphql;
	
	
	@Autowired
	public GraphqlController(GraphQL graphQL) {
		this.graphql = graphQL;
		
	}
	
	@RequestMapping(value = "/workers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> getRequest(@RequestHeader("developer_ap")String consumer) {
		String query = wsi.getQueryByConsumer(consumer);
		Map<String,Object> res = executeGraphqlQuery(query);
		/*
		 * System.out.println(query); System.out.println(res);
		 */
		return res;
	}
	
	@RequestMapping(value = "/workers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getWorker(@RequestHeader("developer_ap")String consumer,@PathVariable("id")String id,@RequestParam(value = "country",required = false)String country) {
		String query = wsi.getQueryByConsumer(consumer);
		Map<String,Object> variables = new LinkedHashMap<String, Object>();
		variables.put("personNumber", id);
		variables.put("country", country);
		Map<String, Object> result = executeGraphqlQueryVariables(query,variables);
		System.out.println(variables);
		System.out.println(id);
		return result;
		
	}
	
	private Map<String, Object> executeGraphqlQuery(String query) {
		ExecutionInput executionInput = ExecutionInput.newExecutionInput()
				.query(query)
				.build();
		return this.graphql.execute(executionInput).toSpecification();
    }
	
	private Map<String, Object> executeGraphqlQueryVariables(String query,Map<String,Object> variables) {
		ExecutionInput executionInput = ExecutionInput.newExecutionInput()
				.query(query)
				.variables(variables)
				.build();
		return this.graphql.execute(executionInput).toSpecification();
    }
	
	

}
