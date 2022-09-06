package com.ciandt.summit.bootcamp2022.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TipoUsuario")
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "Descricao")
    private String Description;

    public UserType() {
    }

    public UserType(String id, String description) {
        this.id = id;
        Description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType userType = (UserType) o;
        return Objects.equals(id, userType.id) && Objects.equals(Description, userType.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Description);
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id='" + id + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
