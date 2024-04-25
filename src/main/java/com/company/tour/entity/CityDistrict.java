package com.company.tour.entity;

import com.company.tour.app.GeometryConverter;
import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.locationtech.jts.geom.Polygon;

import java.util.UUID;

@JmixEntity
@Table(name = "CITY_DISTRICT", indexes = {
        @Index(name = "IDX_CITY_DISTRICT_CITY", columnList = "CITY_ID")
})
@Entity
public class CityDistrict {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "CITY_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private City city;

    @NotNull
    @Column(name = "AREA", nullable = false)
    private Polygon area;

    @JmixProperty
    @Transient
    private Polygon area3035;

    public Polygon getArea3035() {
        return area3035;
    }

    public void setArea3035(Polygon area3035) {
        this.area3035 = area3035;
    }

    public Polygon getArea() {
        return area;
    }

    public void setArea(Polygon area) {
        this.area = area;

        setArea3035FromWgs(area);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    void setArea3035FromWgs(Polygon areaWgs) {
        this.area3035 = areaWgs != null
                ? GeometryConverter.convertFromWGS(areaWgs, GeometryConverter.CRS_3035)
                : null;
    }

    @PostLoad
    public void postLoad() {
        setArea3035FromWgs(area);
    }
}