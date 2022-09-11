package com.travelrhythm.batch.job;

import com.travelrhythm.batch.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FindPlaceDetailByNaverJob implements Job {

  @Autowired
  private QuartzService quartzService;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    log.info("JOB [{}] executed.", this.getClass().getSimpleName());
    quartzService.findPlaceDetailByNaver();
  }

}
