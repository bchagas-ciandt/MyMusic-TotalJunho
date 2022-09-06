package com.ciandt.summit.bootcamp2022.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Usuarios")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Nome")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PlaylistId", referencedColumnName = "Id")
    private Playlist playlists;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoUsuarioId")
    private UserType userType;

    public User() {
    }

    public User(String id, String name, Playlist playlists, UserType userType) {
        this.id = id;
        this.name = name;
        this.playlists = playlists;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Playlist getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlists) {
        this.playlists = playlists;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", playlists=" + playlists +
                ", userType=" + userType +
                '}';
    }
}
