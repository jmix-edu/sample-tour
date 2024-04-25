package com.company.tour.entity;

import com.company.tour.app.GeometryConverter;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.locationtech.jts.geom.Point;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "STOP")
@Entity
public class Stop {
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

    @JoinTable(name = "TOUR_STOP_LINK",
            joinColumns = @JoinColumn(name = "STOP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TOUR_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Tour> tours;

    @Column(name = "LOCATION")
    private Point location;

    @JmixProperty
    @Transient
    private Point location3035;

    public Point getLocation3035() {
        return location3035;
    }

    public void setLocation3035(Point location3035) {
        this.location3035 = location3035;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;

        setLocation3035FromWgs(location);
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
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

    void setLocation3035FromWgs(Point location) {
        this.location3035 = location != null
                ? GeometryConverter.convertFromWGS(location, GeometryConverter.CRS_3035)
                : null;
    }

    @PostLoad
    public void postLoad() {
        setLocation3035FromWgs(location);
    }
}