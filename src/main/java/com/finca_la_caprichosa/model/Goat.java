package com.finca_la_caprichosa.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
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

/**
 * Record of a goat.
 */
@Entity
@Table(name = "goat")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Goat.produced",
        query = "select distinct g from Goat g, Sample s where g.id = s.goat.id")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Goat.inProduction",
           query = "select g.id, g.nombre from goat g join current_herd c on g.id = c.goat_id where c.event = 'IN'",
           resultSetMapping = "goat_mapping")
})
@SqlResultSetMapping(name = "goat_mapping", classes = @ConstructorResult(
        targetClass = Goat.class,
        columns = {
            @ColumnResult(name = "id", type = Long.class),
            @ColumnResult(name = "nombre", type = String.class)
        }
))
public class Goat implements Serializable {

    @Id
    private Long id;

    @NotNull
    @Column(updatable = false)
    private String nombre;

    public Goat(){
        // default constructor
    }

    public Goat(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

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

    @Override
    public String toString() {
        return "Goat{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
