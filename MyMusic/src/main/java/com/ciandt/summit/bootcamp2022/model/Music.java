package com.ciandt.summit.bootcamp2022.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "Musicas")
public class Music {
    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "Nome")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ArtistaId")
    private Artist artist;
}
