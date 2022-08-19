package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, String> {

    public List<Music> findByArtistaId(String id);
    public List<Music> findByNomeContainingIgnoreCase(String nome);
    @Query("SELECT m FROM Music m JOIN Artist a on a.id = m.artist WHERE lower(a.name) like lower(concat('%', :name,'%')) or lower(m.name) like lower(concat('%', :name,'%')) ORDER BY a.name, m.name ASC")
    public List<Music> findByNameArtistOrNameMusic(@Param("name") String name);
}
