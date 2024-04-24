package com.company.tour.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum TransportType implements EnumClass<Integer> {

    BUS(10),
    MINIVAN(20),
    CAR(30);

    private final Integer id;

    TransportType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static TransportType fromId(Integer id) {
        for (TransportType at : TransportType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}