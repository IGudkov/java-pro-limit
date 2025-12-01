package ru.inno.java.pro.limit.exception;

public final class BusinessError {

  public static final ErrorCode ERROR_RESERVE_LIMIT_AMOUNT_EXCEEDING = new ErrorCode(
      1,
      "Сумма резервируемого лимита %.2f больше допустимого %.2f"
  );

  public static final ErrorCode ERROR_RESERVE_LIMIT_ID_NOT_FOUND = new ErrorCode(
      2,
      "Зарезервированный лимит id %s не найден"
  );

  public static final ErrorCode ERROR_USER_LIMIT_USER_ID_NOT_FOUND = new ErrorCode(
      3,
      "Лимит пользователя user_id %s не найден"
  );

}
