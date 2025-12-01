package ru.inno.java.pro.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.java.pro.limit.model.entity.ReserveLimit;

import java.util.UUID;

public interface ReserveLimitRepository extends JpaRepository<ReserveLimit, UUID> {
}
