package com.finca_la_caprichosa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by ssmiley on 4/1/18.
 */
@Entity
@Table(name = "rain")
@NamedQueries({
        @NamedQuery(name = "Rain.byDateRange",
        query  = "select Object(r) from Rain r where r.recordedOn >= :start and r.recordedOn <= :end")
})
@XmlRootElement
public class Rain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private double millimeters;

    @NotNull
    @Column
    private Date recordedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMillimeters() {
        return millimeters;
    }

    public void setMillimeters(double millimeters) {
        this.millimeters = millimeters;
    }

    public Date getRecordedOn() {
        return recordedOn;
    }

    public void setRecordedOn(Date recordedOn) {
        this.recordedOn = recordedOn;
    }
}
