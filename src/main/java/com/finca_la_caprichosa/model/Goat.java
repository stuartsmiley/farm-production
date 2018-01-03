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

/**
 * Record of a goat.
 */
@Entity
@Table(name = "goat")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Goat.inProduction",
        query = "from Goat where producing is true"),
        @NamedQuery(name = "Goat.produced",
        query = "select distinct g from Goat g, Sample s where g.id = s.goat.id")
})
public class Goat implements Serializable {

    @Id
    private Long id;

    @NotNull
    @Column(updatable = false)
    private String nombre;

    @NotNull
    private boolean producing;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isProducing() {
        return producing;
    }

    public void setProducing(boolean producing) {
        this.producing = producing;
    }
}
