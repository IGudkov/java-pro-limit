package ru.inno.java.pro.limit.model.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ReserveLimitDto(UUID id,
                              UUID userId,
                              BigDecimal amount,
                              Instant createdAt) {
}
