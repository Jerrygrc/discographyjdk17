package com.grc.discography.service;

import com.grc.discography.model.Artist;
import com.grc.discography.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(Integer id) {
        return artistRepository.findById(id).orElse(null);
    }

    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Transactional
    public Artist updateArtist(Integer id, Artist artist) {
        Artist existingArtist = artistRepository.findById(id).orElse(null);

        if (existingArtist == null) {
            return null;
        }
        if (artist.getName() != null) {
            existingArtist.setName(artist.getName());
        }
        if (artist.getCountry() != null) {
            existingArtist.setCountry(artist.getCountry());
        }
        if (artist.getGenre() != null) {
            existingArtist.setGenre(artist.getGenre());
        }
        if (artist.getBiography() != null) {
            existingArtist.setBiography(artist.getBiography());
        }
        return artistRepository.save(existingArtist);
    }

    public List<Artist> getArtistByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteArtist(Integer id) {
        artistRepository.deleteById(id);
    }
}
