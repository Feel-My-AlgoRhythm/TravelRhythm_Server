package com.travelrhythm.batch.job;

import com.travelrhythm.batch.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FindPoisByDatalabJob implements Job {

  @Autowired
  private BatchService batchService;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    log.info("JOB [{}] executed.", this.getClass().getSimpleName());
    batchService.findPoisByDatalab();
  }
}
