package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/1 18:35
 */
@Mapper(componentModel = "default")
public interface TimeMapper {
    TimeMapper MAPPER = Mappers.getMapper(TimeMapper.class);

    default Long map(Instant instant) {
        if (Objects.isNull(instant)) {
            return null;
        }
        return instant.toEpochMilli();
    }

    default Instant map(Long l) {
        if (Objects.isNull(l)) {
            return null;
        }
        return Instant.ofEpochMilli(l);
    }

}
