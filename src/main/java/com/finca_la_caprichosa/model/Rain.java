package com.finca_la_caprichosa.model;

import com.finca_la_caprichosa.dto.Rainfall;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Track daily rainfall totals.
 */
@Entity
@Table(name = "rain")
@NamedQueries({
        @NamedQuery(name = "Rain.byDateRange",
        query  = "select Object(r) from Rain r where r.recordedOn >= :start and r.recordedOn <= :end"),
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Rain.totals",
                query = "select t.recordedOn, t.millimeters, "
                        + "(select sum(x.millimeters) from rain x where x.recordedOn <= t.recordedOn "
                        + "and DATE_FORMAT(x.recordedOn, '%Y') = DATE_FORMAT(t.recordedOn, '%Y')) as cumulative, "
                        + "DATE_FORMAT(t.recordedOn, '%m') as month, DATE_FORMAT(t.recordedOn, '%Y') as year "
                        + "from rain t order by t.recordedOn asc",
        resultSetMapping = "Rainfall")
})
@SqlResultSetMapping(name = "Rainfall",
  classes = @ConstructorResult(targetClass = Rainfall.class,
  columns = {
          @ColumnResult(name = "recordedOn", type = Date.class),
          @ColumnResult(name = "millimeters", type = BigDecimal.class),
          @ColumnResult(name = "cumulative", type = BigDecimal.class),
          @ColumnResult(name = "month", type = Integer.class),
          @ColumnResult(name = "year", type = Integer.class)
  }))
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
