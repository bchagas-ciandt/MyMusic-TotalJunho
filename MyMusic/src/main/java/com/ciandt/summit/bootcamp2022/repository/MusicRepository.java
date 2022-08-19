package com.ciandt.summit.bootcamp2022.repository;


import com.ciandt.summit.bootcamp2022.entity.Musicas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Musicas, String> {

    public List<Musicas> findByArtistId(String id);
    public List<Musicas> findByNameContainingIgnoreCase(String name);
    @Query("SELECT m FROM Music m JOIN Artist a on a.id = m.artist WHERE lower(a.name) like lower(concat('%', :name,'%')) or lower(m.name) like lower(concat('%', :name,'%')) ORDER BY a.name, m.name ASC")
    public List<Musicas> findByNameArtistOrNameMusic(@Param("name") String name);
}
