package com.grc.discography.service;

import com.grc.discography.model.Album;
import com.grc.discography.repository.AlbumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbumById(Integer id) {
        return albumRepository.findById(id).orElse(null);
    }

    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Transactional
    public Album updateAlbum(Integer id, Album album) {
        Album existingAlbum = albumRepository.findById(id).orElse(null);
        if (existingAlbum == null) {
            return null;
        }
        if (album.getTitle() != null) {
            existingAlbum.setTitle(album.getTitle());
        }
        if (album.getReleaseYear() != null) {
            existingAlbum.setReleaseYear(album.getReleaseYear());
        }
        if (album.getLabel() != null) {
            existingAlbum.setLabel(album.getLabel());
        }
        return albumRepository.save(existingAlbum);
    }

    public List<Album> getAlbumByTitle(String title) {
        return albumRepository.findByTitleContainingIgnoreCase(title);
    }

    public void deleteAlbum(Integer id) {
        albumRepository.deleteById(id);
    }

}
