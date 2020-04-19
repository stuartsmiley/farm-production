package com.finca_la_caprichosa.dto;

import java.math.BigDecimal;
import java.util.Date;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * For transfering rainfall data to d3 in the browser.
 */
public class Rainfall {

    private final Date date;
    private final BigDecimal mm;
    private final BigDecimal cumulativeMm;
    private final Integer month;
    private final Integer year;

    public Rainfall(Date date, BigDecimal mm, BigDecimal cumulativeMm, Integer month, Integer year) {
        this.date = date;
        this.mm = mm;
        this.cumulativeMm = cumulativeMm;
        this.month = month;
        this.year = year;
    }

    @JsonbTypeAdapter(DateAdapter.class)
    public Date getDate() {
        return date;
    }

    public BigDecimal getMm() {
        return mm;
    }

    public BigDecimal getCumulativeMm() {
        return cumulativeMm;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }
}
