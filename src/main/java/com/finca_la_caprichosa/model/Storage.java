package com.finca_la_caprichosa.model;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Storage location.
 */
@Entity
@NamedQueries( {
        @NamedQuery(name = "Storage.active",
        query = "from Storage where active = true order by location")
})
public class Storage implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    private String location;
    
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
