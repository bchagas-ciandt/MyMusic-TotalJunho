package com.ciandt.summit.bootcamp2022.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Musicas")
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "Nome")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ArtistaId")
    private Artist artist;

    public Music(String id, String name, Artist artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }

    public Music() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Music music = (Music) o;
        return id != null && Objects.equals(id, music.id);
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

    public Artist getArtist() {
        return this.artist;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String toString() {
        return "Music(id=" + this.getId() + ", name=" + this.getName() + ", artist=" + this.getArtist() + ")";
    }
}
