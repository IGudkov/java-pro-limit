package ru.inno.java.pro.limit.repository.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserLimitDao {
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public UserLimitDao(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private static final String RESET_USER_LIMIT = """
      update user_limit set current_limit=:limit
      """;

  public void resetUserLimit(BigDecimal limit) {
    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("limit", limit);
    jdbcTemplate.update(RESET_USER_LIMIT, parameters);
  }


}
