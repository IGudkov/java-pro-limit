package ru.inno.java.pro.limit.controller;

import org.springframework.web.bind.annotation.*;
import ru.inno.java.pro.limit.model.dto.CancelResponseDto;
import ru.inno.java.pro.limit.model.dto.ConfirmResponseDto;
import ru.inno.java.pro.limit.model.dto.CurrentLimitResponseDto;
import ru.inno.java.pro.limit.model.dto.ReserveLimitResponseDto;
import ru.inno.java.pro.limit.service.UserLimitService;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-limit")
public class LimitController {
  private final UserLimitService userLimitService;


  public LimitController(UserLimitService userLimitService) {
    this.userLimitService = userLimitService;
  }

  @GetMapping("/{userId}")
  public CurrentLimitResponseDto getLimit(@PathVariable UUID userId) {
    return userLimitService.getUserLimit(userId);
  }

  @PostMapping("/reserve/{userId}/{amount}")
  public ReserveLimitResponseDto reserveLimit(@PathVariable UUID userId, @PathVariable BigDecimal amount) {
    return userLimitService.reserveLimit(userId, amount);
  }

  @PostMapping("/confirm/{reserveLimitId}")
  public ConfirmResponseDto confirmOperation(@PathVariable UUID reserveLimitId) {
    return userLimitService.confirmOperation(reserveLimitId);
  }

  @PostMapping("/cancel/{reserveLimitId}")
  public CancelResponseDto cancelOperation(@PathVariable UUID reserveLimitId) {
    return userLimitService.cancelOperation(reserveLimitId);
  }
}
