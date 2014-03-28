/** 
 * (C) Copyright 2014 Chiral Behaviors, LLC. All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.chiralbehaviors.emissary.core;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.chiralbehaviors.CoRE.event.Job;

/**
 * @author hparry
 *
 */
public class UltrastructureApiTest {
	
	private static final String JOB_URL = "http://localhost:8080/v1/services/data/ruleform/Job";
	
	//@Test
	public void testJobResource() {
		RestTemplate jobRes = new RestTemplate();
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> response = jobRes.getForEntity(JOB_URL, List.class);
		@SuppressWarnings("unchecked")
		List<Job> jobs = response.getBody();
		assertEquals(0, jobs.size());
	}

}
