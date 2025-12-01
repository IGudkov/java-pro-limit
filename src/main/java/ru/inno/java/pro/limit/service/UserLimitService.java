package ru.inno.java.pro.limit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inno.java.pro.limit.exception.BusinessServiceException;
import ru.inno.java.pro.limit.model.dto.CancelResponseDto;
import ru.inno.java.pro.limit.model.dto.ConfirmResponseDto;
import ru.inno.java.pro.limit.model.dto.CurrentLimitResponseDto;
import ru.inno.java.pro.limit.model.dto.ReserveLimitResponseDto;
import ru.inno.java.pro.limit.model.entity.ReserveLimit;
import ru.inno.java.pro.limit.model.entity.UserLimit;
import ru.inno.java.pro.limit.repository.UserLimitRepository;

import java.math.BigDecimal;
import java.util.UUID;

import static ru.inno.java.pro.limit.exception.BusinessError.ERROR_RESERVE_LIMIT_AMOUNT_EXCEEDING;
import static ru.inno.java.pro.limit.exception.BusinessError.ERROR_USER_LIMIT_USER_ID_NOT_FOUND;

@Service
public class UserLimitService {
  private final UserLimitRepository userLimitRepository;
  private final ReserveLimitService reserveLimitService;

  @Value("${app.scheduler.reset-user-limit.limit}")
  private BigDecimal defaultUserLimit;

  public UserLimitService(UserLimitRepository userLimitRepository,
                          ReserveLimitService reserveLimitService) {
    this.userLimitRepository = userLimitRepository;
    this.reserveLimitService = reserveLimitService;
  }

  @Transactional
  public CurrentLimitResponseDto getUserLimit(UUID userId) {
    BigDecimal currentLimit = getOrCreateUserLimitByUserId(userId).getCurrentLimit();
    return new CurrentLimitResponseDto(currentLimit);
  }

  @Transactional
  public ReserveLimitResponseDto reserveLimit(UUID userId, BigDecimal amount) {
    UserLimit userLimit = getOrCreateUserLimitByUserId(userId);
    BigDecimal currentLimit = userLimit.getCurrentLimit();

    BigDecimal newCurrentLimit = currentLimit.subtract(amount);

    if (newCurrentLimit.compareTo(BigDecimal.ZERO) < 0) {
      throw new BusinessServiceException(ERROR_RESERVE_LIMIT_AMOUNT_EXCEEDING,
          amount,
          currentLimit);
    }

    userLimit.setCurrentLimit(newCurrentLimit);
    userLimitRepository.save(userLimit);

    ReserveLimit reserveLimit = reserveLimitService.createReserveLimit(userId, amount);
    return new ReserveLimitResponseDto(reserveLimit.getId());
  }

  @Transactional
  public ConfirmResponseDto confirmOperation(UUID reserveLimitId) {
    ReserveLimit reserveLimit = reserveLimitService.findById(reserveLimitId);
    BigDecimal amount = reserveLimit.getAmount();

    reserveLimitService.delete(reserveLimit);

    String message = String.format("Списание лимита на сумму %.2f выполнено", amount);
    return new ConfirmResponseDto(message);
  }

  @Transactional
  public CancelResponseDto cancelOperation(UUID reserveLimitId) {
    ReserveLimit reserveLimit = reserveLimitService.findById(reserveLimitId);
    BigDecimal amount = reserveLimit.getAmount();
    UUID userId = reserveLimit.getUserId();

    reserveLimitService.delete(reserveLimit);

    UserLimit userLimit = userLimitRepository.findByUserId(userId)
        .orElseThrow(() -> new BusinessServiceException(ERROR_USER_LIMIT_USER_ID_NOT_FOUND,
            userId));
    BigDecimal currentLimit = userLimit.getCurrentLimit();
    userLimit.setCurrentLimit(currentLimit.add(amount));
    userLimitRepository.save(userLimit);

    String message = String.format("Отмена списания лимита на сумму %.2f выполнена", amount);
    return new CancelResponseDto(message);
  }

  @Transactional
  public void resetUserLimit(BigDecimal limit) {
    userLimitRepository.resetUserLimit(limit);
  }

  private UserLimit getOrCreateUserLimitByUserId(UUID userId) {
    return userLimitRepository.findByUserId(userId)
        .orElseGet(() -> createUserLimit(userId));
  }

  private UserLimit createUserLimit(UUID userId) {
    UserLimit userLimit = new UserLimit();
    userLimit.setUserId(userId);
    userLimit.setCurrentLimit(defaultUserLimit);
    return userLimitRepository.save(userLimit);
  }

}
