package com.ciandt.summit.bootcamp2022.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Artistas")
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "Nome")
    private String name;

    public Artist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artist artist = (Artist) o;
        return id != null && Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Artist(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
