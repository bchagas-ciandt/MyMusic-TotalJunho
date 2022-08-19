package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Artistas {
    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "Nome")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artistas artistas = (Artistas) o;
        return id != null && Objects.equals(id, artistas.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
