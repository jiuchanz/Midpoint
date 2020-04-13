package com.joe.midpoint;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Scheduled(fixedRate = 60000)
    public void deleteOutdatedEntry() {

    }
}
