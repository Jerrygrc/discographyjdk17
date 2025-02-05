package com.grc.discography.service;

import com.grc.discography.model.Song;
import com.grc.discography.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Integer id) {
        return songRepository.findById(id).orElse(null);
    }

    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    @Transactional
    public Song updateSong(Integer id, Song song){
        Song existinSong = songRepository.findById(id).orElse(null);
        if(existinSong == null){
            return null;
        }
        if(song.getTitle() != null){
            existinSong.setTitle(song.getTitle());
        }
        if(song.getComposer() != null){
            existinSong.setComposer(song.getComposer());
        }
        return songRepository.save(existinSong);
    }

    public List<Song> getSongByTitle(String title) {
        return songRepository.findByTitleContainingIgnoreCase(title);
    }

    public void deleteSong(Integer id) {
        songRepository.deleteById(id);
    }
}
