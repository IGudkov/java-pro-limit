package ru.inno.java.pro.limit.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UserLimitDto(UUID id,
                           UUID userId,
                           BigDecimal currentLimit) {
}
