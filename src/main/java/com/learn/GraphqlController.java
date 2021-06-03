package com.learn;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/workers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> getRequest(@RequestHeader("developer_ap")String consumer) {
		String query = wsi.getQueryByConsumer(consumer);
		Map<String,Object> res = executeGraphqlQuery(query);
		System.out.println(query);
		System.out.println(res);
		return res;
	}
	
	private Map<String, Object> executeGraphqlQuery(String query) {
		ExecutionInput executionInput = ExecutionInput.newExecutionInput()
				.query(query)
				.build();
		return this.graphql.execute(executionInput).toSpecification();
    }

}
