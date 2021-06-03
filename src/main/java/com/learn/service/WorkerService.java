package com.learn.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.modals.Location;
import com.learn.modals.WorkRelationships;
import com.learn.modals.Worker;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;

@Component
public class WorkerService{

	private static Map<String,Worker> items = new HashMap<String,Worker>(){{
		put("297429759", new Worker("297429759","10-12-1991",Arrays.asList(new WorkRelationships("E", "28", "0437297"),new WorkRelationships("C", "34", "3975399"))));
	}};
	private static Map<String,Location> locs = new HashMap<String,Location>(){{
		put("0437297", new Location("0437297", "US", "257327"));
		put("3975399", new Location("3975399", "UK", "397539"));
	}};

	private static Map<String,String> consumers = new HashMap<String,String>() {{
		put("consumerA", "query{\r\n"
				+ "getWorkerById(personNumber:\"297429759\"){\r\n"
				+ "  dateOfBirth\r\n"
				+ "  personNumber\r\n"
				+ "  workRelationships{\r\n"
				+ "    gradeCode\r\n"
				+ "    workType\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "}");
		put("consumerB", "query{\r\n"
				+ "getWorkerById(personNumber:\"297429759\"){\r\n"
				+ "  dateOfBirth\r\n"
				+ "  personNumber\r\n"
				+ "  workRelationships{\r\n"
				+ "    \r\n"
				+ "    location{\r\n"
				+ "      locationId\r\n"
				+ "      country\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "}");
	}};
	
	public String getQueryByConsumer(String consumer) {
		return consumers.get(consumer);
	}
	
	public DataFetcher<?> getWorkerById(){
		return DataFetchingEnvironment->{
			String id = DataFetchingEnvironment.getArgument("personNumber");
			return items.get(id);
			
		};
	}	
	
	public DataFetcher<?> getLocation(){
		return DataFetchingEnvironment->{
			WorkRelationships wr = (WorkRelationships) DataFetchingEnvironment.getSource();
			String locationId = wr.getLocationId();
			System.out.println("Hello");
			return locs.get(locationId);
			
		};
	}
}
