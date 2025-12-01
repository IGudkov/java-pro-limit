package ru.inno.java.pro.limit.mapper.base;

public interface BaseMapper<DtoT, EntityT> {

  DtoT fromEntity(final EntityT entity);
}
