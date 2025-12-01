package ru.inno.java.pro.limit.service;

import org.springframework.stereotype.Service;
import ru.inno.java.pro.limit.model.entity.ReserveLimit;
import ru.inno.java.pro.limit.exception.BusinessServiceException;
import ru.inno.java.pro.limit.repository.ReserveLimitRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static ru.inno.java.pro.limit.exception.BusinessError.ERROR_RESERVE_LIMIT_ID_NOT_FOUND;

@Service
public class ReserveLimitService {
  private final ReserveLimitRepository reserveLimitRepository;

  public ReserveLimitService(ReserveLimitRepository reserveLimitRepository) {
    this.reserveLimitRepository = reserveLimitRepository;
  }

  public ReserveLimit createReserveLimit(UUID userId, BigDecimal amount) {
    ReserveLimit reserveLimit = new ReserveLimit();
    reserveLimit.setUserId(userId);
    reserveLimit.setAmount(amount);
    reserveLimit.setCreatedAt(Instant.now());
    return reserveLimitRepository.save(reserveLimit);
  }

  public ReserveLimit findById(UUID reserveLimitId) {
    return reserveLimitRepository.findById(reserveLimitId)
        .orElseThrow(() -> new BusinessServiceException(ERROR_RESERVE_LIMIT_ID_NOT_FOUND,
            reserveLimitId.toString()));
  }

  public void delete(ReserveLimit reserveLimit) {
    reserveLimitRepository.delete(reserveLimit);
  }
}
