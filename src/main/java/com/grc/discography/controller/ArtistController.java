package com.grc.discography.controller;


import com.grc.discography.model.Artist;
import com.grc.discography.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/discography/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }
    @PostMapping
    public ResponseEntity<Artist> save(@RequestBody Artist artist) {
        Artist newArtist = artistService.saveArtist(artist);
        return ResponseEntity.status(HttpStatus.CREATED).body(newArtist);
    }
    @PutMapping("/{idArtist}")
    public ResponseEntity<Artist> update(@PathVariable Integer idArtist, @RequestBody Artist artist) {
        Artist updatedArtist = artistService.updateArtist(idArtist, artist);
        if (updatedArtist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArtist);
    }
    @GetMapping
    public ResponseEntity<List<Artist>> getArtists(){
        List<Artist> artists = artistService.getArtists();
        return ResponseEntity.ok(artists);
    }
    @GetMapping("/{idArtist}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Integer idArtist){
        Artist artist = artistService.getArtistById(idArtist);
        return ResponseEntity.ok(artist);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Artist>> searchArtist(@RequestParam String name){
        List<Artist> artists = artistService.getArtistByName(name);
        return ResponseEntity.ok(artists);
    }

    @DeleteMapping("/{idArtist}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable Integer idArtist){
        artistService.deleteArtist(idArtist);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
