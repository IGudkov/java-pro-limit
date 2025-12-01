package ru.inno.java.pro.limit.mapper;

import org.mapstruct.Mapper;
import ru.inno.java.pro.limit.mapper.base.BaseMapper;
import ru.inno.java.pro.limit.model.dto.ReserveLimitDto;
import ru.inno.java.pro.limit.model.entity.ReserveLimit;

@Mapper
public interface ReserveLimitMapper extends BaseMapper<ReserveLimitDto, ReserveLimit> {

  @Override
  ReserveLimitDto fromEntity(ReserveLimit entity);
}
