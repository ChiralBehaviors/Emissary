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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.chiralbehaviors.CoRE.agency.Agency;
import com.chiralbehaviors.CoRE.meta.JobModel;

/**
 * @author hparry
 * 
 */
public class JobQueueTest {

	@Test
	public void testService() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		final Agency agency = new Agency("Agency", "Agency", null);
		final JobModel model = mock(JobModel.class);
		final JobExecutor executor = mock(JobExecutor.class);
		JobQueueService service = new JobQueueService(scheduler, 1,
				TimeUnit.SECONDS, agency, model, executor);
		
		service.start();
		assertTrue(service.isRunning());
		service.stop();
		assertFalse(service.isRunning());

	}
}
