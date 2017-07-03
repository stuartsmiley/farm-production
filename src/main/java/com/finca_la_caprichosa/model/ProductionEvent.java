package com.finca_la_caprichosa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * For recording production on the farm.
 */
@Entity
@XmlRootElement
public class ProductionEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @Version
    private Short version;

    private Date created;

    private Double quantity;

    private Units units;

    private Integer producers;

    private Storage storage;

    private String note;

    private String disposition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public Integer getProducers() {
        return producers;
    }

    public void setProducers(Integer producers) {
        this.producers = producers;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }
}
