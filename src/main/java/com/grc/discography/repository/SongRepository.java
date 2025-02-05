package com.grc.discography.repository;

import com.grc.discography.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    List<Song> findByTitleContainingIgnoreCase(String title);

}
