package ru.inno.java.pro.limit.controller.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.inno.java.pro.limit.model.dto.ErrorResponseDto;
import ru.inno.java.pro.limit.exception.BusinessServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BusinessServiceException.class)
  public ErrorResponseDto handleProductNotFoundException(BusinessServiceException e) {
    return new ErrorResponseDto(e.getBusinessCode(), e.getMessage());
  }

}
