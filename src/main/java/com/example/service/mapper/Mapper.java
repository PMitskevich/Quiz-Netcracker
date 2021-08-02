package com.example.service.mapper;

public interface Mapper<T, K> {

    K toDto(T entity);

    T toEntity(K dto);

    K toShortDto(T entity);
}
