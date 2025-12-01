package ru.inno.java.pro.limit.exception;

public class BusinessServiceException extends RuntimeException {

  private final Integer businessCode;
  private final Object[] args;

  public BusinessServiceException(ErrorCode errorCode) {
    this(errorCode, (Object) null);
  }

  public BusinessServiceException(ErrorCode errorCode, Object... args) {
    this(errorCode.code(), errorCode.message(), args);
  }

  public BusinessServiceException(int businessCode, String message, Object... args) {
    super(message, null);
    this.businessCode = businessCode;
    this.args = args;
  }

  @Override
  public String getMessage() {
    String msg = super.getMessage();
    if (msg != null && args != null && args.length > 0) {
      msg = msg.formatted(args);
    }
    return msg;
  }

  public Integer getBusinessCode() {
    return businessCode;
  }

}
