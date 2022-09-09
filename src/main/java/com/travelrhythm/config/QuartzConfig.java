package com.travelrhythm.config;

import com.travelrhythm.batch.job.AddPoiDetailDataByNaverJob;
import com.travelrhythm.batch.job.FindPoisByDatalabJob;
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
  public Trigger findPoisByDatalabTrigger(JobDetail findPoisByDatalabDetail) {
    return TriggerBuilder.newTrigger().forJob(findPoisByDatalabDetail)
        .withIdentity("Find_Pois_By_Datalab_Trigger")
        .withDescription("Find_Pois_By_Datalab_Trigger")
        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 * * * ?"))
        .build();
  }

  @Bean
  public JobDetail findPoisByDatalabDetail() {
    return JobBuilder.newJob().ofType(FindPoisByDatalabJob.class)
        .storeDurably()
        .withIdentity("Find_Pois_By_Datalab_Detail")
        .withDescription("Find_Pois_By_Datalab_Detail")
        .build();
  }

  @Bean
  public Trigger addPoiDetailDataByNaverTrigger(JobDetail addPoiDetailDataByNaverDetail) {
    return TriggerBuilder.newTrigger().forJob(addPoiDetailDataByNaverDetail)
        .withIdentity("Add_Poi_Detail_Data_By_Naver_Trigger")
        .withDescription("Add_Poi_Detail_Data_By_Naver_Trigger")
        .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/1 * * ?"))
        .build();
  }

  @Bean
  public JobDetail addPoiDetailDataByNaverDetail() {
    return JobBuilder.newJob().ofType(AddPoiDetailDataByNaverJob.class)
        .storeDurably()
        .withIdentity("Add_Poi_Detail_Data_By_Naver_Detail")
        .withDescription("Add_Poi_Detail_Data_By_Naver_Detail")
        .build();
  }

}
