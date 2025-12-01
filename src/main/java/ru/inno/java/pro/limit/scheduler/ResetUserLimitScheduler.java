package ru.inno.java.pro.limit.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.inno.java.pro.limit.service.UserLimitService;

import java.math.BigDecimal;

@Component
@ConditionalOnProperty(prefix = "app.scheduler.reset-user-limit", name = "enabled", havingValue = "true")
public class ResetUserLimitScheduler {
  private final UserLimitService userLimitService;
  @Value("${app.scheduler.reset-user-limit.limit}")
  BigDecimal defaultUserLimit;

  public ResetUserLimitScheduler(UserLimitService userLimitService) {
    this.userLimitService = userLimitService;
  }

  @Scheduled(cron = "${app.scheduler.reset-user-limit.cron}", zone = "${spring.application.timezone}")
  public void process() {
    userLimitService.resetUserLimit(defaultUserLimit);
  }
}
