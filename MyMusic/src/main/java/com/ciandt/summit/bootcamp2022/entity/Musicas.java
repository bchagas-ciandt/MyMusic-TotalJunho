package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Musicas {
    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "Nome")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ArtistaId")
    private Artistas artistas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Musicas musicas = (Musicas) o;
        return id != null && Objects.equals(id, musicas.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
