package com.project.EventManagementPlatform.mapper;

import org.springframework.beans.BeanUtils;

public class EntityMapper<T, K> {
    private final Class<T> entityClass;
    private final Class<K> dtoClass;

    public EntityMapper(Class<T> entityClass, Class<K> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public static <T, K> T mapCreateDtoToEntity(K dto, Class<T> entityClass) {
        T entity = BeanUtils.instantiateClass(entityClass);
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static <T, K> K mapEntityToDto(T entity, Class<K> dtoClass) {
        K dto = BeanUtils.instantiateClass(dtoClass);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
