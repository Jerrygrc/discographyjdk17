package com.grc.discography.controller;

import com.grc.discography.model.Album;
import com.grc.discography.model.Song;
import com.grc.discography.repository.AlbumRepository;
import com.grc.discography.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/discography/songs")
public class SongController {

    private final SongService songService;
    private final AlbumRepository albumRepository;

    @Autowired
    public SongController(SongService songService, AlbumRepository albumRepository) {
        this.songService = songService;
        this.albumRepository = albumRepository;
    }

    @PostMapping
    public ResponseEntity<Song> save(@RequestBody Song song) {
        Album album = albumRepository.findById(song.getAlbum().getIdAlbum())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el album"));
        song.setAlbum(album);
        Song newSong  = songService.saveSong(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSong);
    }

    @PutMapping("/{idSong}")
    public ResponseEntity<Song> update(@PathVariable Integer idSong, @RequestBody Song song) {
        Song updatedSong = songService.updateSong(idSong, song);
        if (updatedSong == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSong);
    }

    @GetMapping
    public ResponseEntity<List<Song>> getSongs(){
        List<Song> songs = songService.getSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{idSong}")
    public ResponseEntity<Song> getSongById(@PathVariable Integer idSong){
        Song song = songService.getSongById(idSong);
        return ResponseEntity.ok(song);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSong(@RequestParam String title){
        List<Song> songs = songService.getSongByTitle(title);
        return ResponseEntity.ok(songs);
    }

    @DeleteMapping("/{idSong}")
    public ResponseEntity<Song> deleteSong(@PathVariable Integer idSong){
        songService.deleteSong(idSong);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
