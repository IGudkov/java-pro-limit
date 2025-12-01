package ru.inno.java.pro.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.inno.java.pro.limit.model.entity.UserLimit;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface UserLimitRepository extends JpaRepository<UserLimit, UUID> {

  Optional<UserLimit> findByUserId(UUID userId);

  @Modifying
  @Query("update UserLimit ul set ul.currentLimit = :limit")
  int resetUserLimit(@Param("limit") BigDecimal limit);

}
