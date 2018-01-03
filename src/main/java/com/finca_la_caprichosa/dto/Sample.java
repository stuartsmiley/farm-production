package com.finca_la_caprichosa.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * For graphing samples for a particular goat.
 */
public class Sample {

    private final BigDecimal liters;
    private final Date date;

    public Sample(BigDecimal liters, Date date) {
        this.liters = liters;
        this.date = date;
    }

    public BigDecimal getLiters() {
        return liters;
    }

    public Date getDate() {
        return date;
    }
}
