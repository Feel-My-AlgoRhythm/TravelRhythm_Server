package com.travelrhythm.config;

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
  public Trigger findPoisByDatalabTrigger(JobDetail checkDealerAgreementDetail) {
    return TriggerBuilder.newTrigger().forJob(checkDealerAgreementDetail)
        .withIdentity("Find_Pois_By_Datalab_Trigger")
        .withDescription("Find_Pois_By_Datalab_Trigger")
        .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
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

}
