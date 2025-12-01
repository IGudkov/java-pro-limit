package ru.inno.java.pro.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.java.pro.limit.model.entity.UserLimit;

import java.util.Optional;
import java.util.UUID;

public interface UserLimitRepository extends JpaRepository<UserLimit, UUID> {

Optional<UserLimit> findByUserId(UUID userId);

}
