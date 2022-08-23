package com.ciandt.summit.bootcamp2022.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Playlists")
public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Id")
    private String id;

    @ManyToMany
    @JoinTable(name = "PlaylistMusicas",
            joinColumns = @JoinColumn(name = "Playlistid"),
            inverseJoinColumns = @JoinColumn(name = "MusicaId"))
    @ToString.Exclude
    private List<Music> musicas;

    public Playlist() {
        musicas = new ArrayList<>();
    }

    public Playlist(String id, List<Music> musicas) {
        this.id = id;
        this.musicas = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(id, playlist.id) && Objects.equals(musicas, playlist.musicas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, musicas);
    }
}
