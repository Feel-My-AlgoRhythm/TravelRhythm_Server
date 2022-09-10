package com.travelrhythm.config;

import com.travelrhythm.batch.job.FindPlaceDetailByNaverJob;
import com.travelrhythm.batch.job.FindPlaceListByDatalabJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

  @Bean
  public Trigger findPlaceListByDatalabTrigger(JobDetail findPlaceListByDatalabDetail) {
    return TriggerBuilder.newTrigger().forJob(findPlaceListByDatalabDetail)
        .withIdentity("Find_Place_List_By_Datalab_Trigger")
        .withDescription("Find_Place_List_By_Datalab_Trigger")
        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 * * * ?"))
        .build();
  }

  @Bean
  public JobDetail findPlaceListByDatalabDetail() {
    return JobBuilder.newJob().ofType(FindPlaceListByDatalabJob.class)
        .storeDurably()
        .withIdentity("Find_Place_List_By_Datalab_Detail")
        .withDescription("Find_Place_List_By_Datalab_Detail")
        .build();
  }

  @Bean
  public Trigger findPlaceDetailByNaverTrigger(JobDetail findPlaceDetailByNaverDetail) {
    return TriggerBuilder.newTrigger().forJob(findPlaceDetailByNaverDetail)
        .withIdentity("Find_Place_Detail_By_Naver_Trigger")
        .withDescription("Find_Place_Detail_By_Naver_Trigger")
        .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/1 * * ?"))
        .build();
  }

  @Bean
  public JobDetail findPlaceDetailByNaverDetail() {
    return JobBuilder.newJob().ofType(FindPlaceDetailByNaverJob.class)
        .storeDurably()
        .withIdentity("Find_Place_Detail_By_Naver_Detail")
        .withDescription("Find_Place_Detail_By_Naver_Detail")
        .build();
  }

}
