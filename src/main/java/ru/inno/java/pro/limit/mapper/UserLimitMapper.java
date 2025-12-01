package ru.inno.java.pro.limit.mapper;

import org.mapstruct.Mapper;
import ru.inno.java.pro.limit.mapper.base.BaseMapper;
import ru.inno.java.pro.limit.model.dto.UserLimitDto;
import ru.inno.java.pro.limit.model.entity.UserLimit;

@Mapper
public interface UserLimitMapper extends BaseMapper<UserLimitDto, UserLimit> {

  @Override
  UserLimitDto fromEntity(UserLimit entity);
}
