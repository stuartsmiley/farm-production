package com.finca_la_caprichosa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by ssmiley on 7/20/18.
 */
@Entity
@Table(name = "herd_history")
@XmlRootElement
public class GoatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "change_date")
    private Date change;

    @NotNull
    @Column(name = "event")
    private String event;

    @NotNull
    @Column(name = "goat_id")
    private Long goatId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getChange() {
        return change;
    }

    public void setChange(Date change) {
        this.change = change;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Long getGoatId() {
        return goatId;
    }

    public void setGoatId(Long goatId) {
        this.goatId = goatId;
    }
}
