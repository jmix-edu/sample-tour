package com.company.tour.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "TOUR", indexes = {
        @Index(name = "IDX_TOUR_CITY", columnList = "CITY_ID"),
        @Index(name = "IDX_TOUR_DISPATCHER", columnList = "DISPATCHER_ID")
})
@Entity
public class Tour {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "DURATION")
    private Integer duration;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @JoinColumn(name = "CITY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @JoinColumn(name = "DISPATCHER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User dispatcher;

    @OneToMany(mappedBy = "tour")
    private List<Transport> transports;

    @JoinTable(name = "TOUR_STOP_LINK",
            joinColumns = @JoinColumn(name = "TOUR_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STOP_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Stop> stops;

    @Column(name = "ROUTE_COLOR")
    private String routeColor;

    public String getRouteColor() {
        return routeColor;
    }

    public void setRouteColor(String routeColor) {
        this.routeColor = routeColor;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public User getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(User dispatcher) {
        this.dispatcher = dispatcher;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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