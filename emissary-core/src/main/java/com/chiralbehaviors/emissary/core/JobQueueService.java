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

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.hellblazer.CoRE.agency.Agency;
import com.hellblazer.CoRE.event.Job;
import com.hellblazer.CoRE.meta.JobModel;

/**
 * @author hparry
 * 
 */
public class JobQueueService {

	private final ScheduledExecutorService scheduler;
	private final long period;
	private final TimeUnit unit;
	private ScheduledFuture<?> task;
	private final AtomicBoolean running = new AtomicBoolean();
	private final Agency agency;
	private final JobModel model;
	private final JobExecutor executor;

	/**
	 * @param scheduler
	 * @param period
	 * @param unit
	 * @param running
	 * @param agency
	 * @param model
	 */
	public JobQueueService(ScheduledExecutorService scheduler, long period,
			TimeUnit unit, Agency agency, JobModel model, JobExecutor executor) {
		super();
		this.scheduler = scheduler;
		this.period = period;
		this.unit = unit;
		this.agency = agency;
		this.model = model;
		this.executor = executor;
	}

	public void start() {
		if (running.compareAndSet(false, true)) {
			task = scheduler.schedule(task(), period, unit);
		}
	}

	/**
	 * @return
	 */
	private Runnable task() {
		return new Runnable() {

			public void run() {
				for (Job j : model.getActiveJobsFor(agency)) {
					executor.execute(j);
				}

			}
		};
	}

	public void stop() {
		if (running.compareAndSet(true, false)) {
			task.cancel(true);
		}
	}
	
	public boolean isRunning() {
		return running.get();
	}

}
