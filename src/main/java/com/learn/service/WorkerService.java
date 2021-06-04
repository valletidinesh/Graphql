package com.learn.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.learn.modals.Location;
import com.learn.modals.WorkRelationships;
import com.learn.modals.Worker;

import graphql.schema.DataFetcher;

@Component
public class WorkerService {

	@SuppressWarnings("serial")
	private static Map<String, Worker> items = new HashMap<String, Worker>() {
		{
			put("297429759", new Worker("297429759", "10-12-1991", Arrays
					.asList(new WorkRelationships("E", "28", "0437297"), new WorkRelationships("C", "34", "3975399"))));
			put("297429760",
					new Worker("297429760", "25-05-1988", Arrays.asList(new WorkRelationships("C", "30", "3975399"))));
		}
	};
	@SuppressWarnings("serial")
	private static Map<String, Location> locs = new HashMap<String, Location>() {
		{
			put("0437297", new Location("0437297", "US", "257327"));
			put("3975399", new Location("3975399", "UK", "397539"));
		}
	};

	@SuppressWarnings("serial")
	private static Map<String, String> consumers = new HashMap<String, String>() {
		{
			put("consumerA",
					"query{\r\n" + "getWorkerById(personNumber:\"297429759\"){\r\n" + "  dateOfBirth\r\n"
							+ "  personNumber\r\n" + "  workRelationships{\r\n" + "    gradeCode\r\n"
							+ "    workType\r\n" + "  }\r\n" + "}\r\n" + "}");
			put("consumerB",
					"query($personNumber:String!){\r\n" + "getWorkerById(personNumber:$personNumber){\r\n"
							+ "  dateOfBirth\r\n" + "  personNumber\r\n" + "  workRelationships{\r\n"
							+ "    location{\r\n" + "        locationId\r\n" + "        country\r\n" + "    }\r\n"
							+ "   gradeCode\r\n" + "  }\r\n" + "}\r\n" + "}\r\n" + "	");
			put("consumerC",
					"query{\r\n" + "getWorkers{\r\n" + "  personNumber\r\n" + "  dateOfBirth\r\n"
							+ "  workRelationships{\r\n" + "    gradeCode\r\n" + "    location{\r\n"
							+ "      locationId\r\n" + "      country\r\n" + "    }\r\n" + "  }\r\n" + "}\r\n" + "}");
			put("consumerD", "query($personNumber:String!,$country:String){\r\n"
					+ "getWorkerById(personNumber:$personNumber,country:$country){\r\n" + "  dateOfBirth\r\n"
					+ "  personNumber\r\n" + "  workRelationships{\r\n" + "    location{\r\n" + "      locationId\r\n"
					+ "      country\r\n" + "    }\r\n" + "    \r\n" + "   \r\n" + "  }\r\n" + "}\r\n" + "}");
		}
	};
	private static String country;

	public String getQueryByConsumer(String consumer) {
		return consumers.get(consumer);
	}

	public DataFetcher<?> getWorkerById() {
		return DataFetchingEnvironment -> {
			String id = DataFetchingEnvironment.getArgument("personNumber");
			country = DataFetchingEnvironment.getArgument("country");
			return items.get(id);

		};
	}

	public DataFetcher<Location> getLocation() {
		return DataFetchingEnvironment -> {
			WorkRelationships wr = (WorkRelationships) DataFetchingEnvironment.getSource();
			String locationId = wr.getLocationId();
			return locs.get(locationId);

		};
	}

	public DataFetcher<?> getWorkerRelationship(){
		return DataFetchingEnvironment->{
			System.out.println(country);
			Worker wr = (Worker) DataFetchingEnvironment.getSource();
			List<WorkRelationships> workRelationships = wr.getWorkRelationships();
			if (country!= null) {
			List<WorkRelationships> res = new ArrayList<>();
			for(WorkRelationships wr1:workRelationships) {
				Location loc = locs.get(wr1.getLocationId());
				if(loc.getCountry().equals(country)) {
					res.add(wr1);
				}
			}
			return res;
			}
			return workRelationships;
		};
	}

	public DataFetcher<?> getWorkers() {
		return DataFetchingEnvironment -> {
			return items.values();
		};
	}
}
