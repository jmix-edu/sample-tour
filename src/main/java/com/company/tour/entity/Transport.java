package com.company.tour.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "TRANSPORT", indexes = {
        @Index(name = "IDX_TRANSPORT_TOUR", columnList = "TOUR_ID")
})
@Entity
public class Transport {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @InstanceName
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TYPE_", nullable = false)
    @NotNull
    private Integer type;
    @JoinColumn(name = "TOUR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tour tour;

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public TransportType getType() {
        return type == null ? null : TransportType.fromId(type);
    }

    public void setType(TransportType type) {
        this.type = type == null ? null : type.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}