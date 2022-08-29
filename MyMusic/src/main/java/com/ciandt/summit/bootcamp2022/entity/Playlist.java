package com.ciandt.summit.bootcamp2022.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Playlists")
public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "PlaylistMusicas",
            joinColumns = @JoinColumn(name = "Playlistid", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "MusicaId", referencedColumnName = "Id"))
    private List<Music> musicas = new ArrayList<>();

    public Playlist(String id, List<Music> musicas) {
        this.id = id;
        this.musicas = musicas;
    }

    public Playlist() {
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

    public String getId() {
        return this.id;
    }

    public List<Music> getMusicas() {
        return this.musicas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMusicas(List<Music> musicas) {
        this.musicas = musicas;
    }

    public String toString() {
        return "Playlist(id=" + this.getId() + ", musicas=" + this.getMusicas() + ")";
    }
}
