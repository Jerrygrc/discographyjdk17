package com.grc.discography.controller;

import com.grc.discography.model.Album;
import com.grc.discography.model.Artist;
import com.grc.discography.repository.ArtistRepository;
import com.grc.discography.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/discography/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumController(AlbumService albumService, ArtistRepository artistRepository) {
        this.albumService = albumService;
        this.artistRepository = artistRepository;
    }

    @PostMapping
    public ResponseEntity<Album> save(@RequestBody Album album) {
        Artist artist = artistRepository.findById(album.getArtist().getIdArtist())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el artista o grupo"));
        album.setArtist(artist);
        Album newAlbum = albumService.saveAlbum(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAlbum);
    }

    @PutMapping("/{idAlbum}")
    public ResponseEntity<Album> update(@PathVariable Integer idAlbum, @RequestBody Album album) {
        Album updatedAlbum = albumService.updateAlbum(idAlbum, album);
        if (updatedAlbum == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAlbum);
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAlbums(){
        List<Album> albums = albumService.getAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{idAlbum}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Integer idAlbum){
        Album album = albumService.getAlbumById(idAlbum);
        return ResponseEntity.ok(album);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Album>> searchAlbum(@RequestParam String title){
        List<Album> albums = albumService.getAlbumByTitle(title);
        return ResponseEntity.ok(albums);
    }
    @DeleteMapping("/{idAlbum}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable Integer idAlbum){
        albumService.deleteAlbum(idAlbum);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
