package com.alekseytyan.logiweb.schedule;

import com.alekseytyan.logiweb.config.schedule.SchedulingConfig;
import com.alekseytyan.logiweb.service.api.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataSourceScheduler {
    private static final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    private VerificationService verificationService;

    @Autowired
    public void setVerificationService(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Async
    @Scheduled(cron = "0 0 23 * * MON,FRI")
    public void scheduledTask() {
        logger.info("Database verification tokens cleaning...");

        verificationService.deleteAll();
    }
}
