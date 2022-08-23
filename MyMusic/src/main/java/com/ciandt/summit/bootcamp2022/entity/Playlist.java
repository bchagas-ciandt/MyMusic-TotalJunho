package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @ToString.Exclude
    private List<Music> musicas = new ArrayList<>();

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
