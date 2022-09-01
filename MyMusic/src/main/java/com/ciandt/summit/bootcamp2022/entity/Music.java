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
        if (o == null || getClass() != o.getClass()) return false;

        Music music = (Music) o;

        if (!Objects.equals(id, music.id)) return false;
        if (!Objects.equals(name, music.name)) return false;
        return Objects.equals(artist, music.artist);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
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
