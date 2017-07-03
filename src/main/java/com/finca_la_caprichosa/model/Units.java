package com.finca_la_caprichosa.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The unit of production. Stored in the db so we
 * can dynamically start tracking whatever type of
 * production we want to track.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Units.active",
        query = "from Units where active = true order by name")
}
)
public class Units implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
