package com.grc.discography.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArtist;
    private String name;
    private String country;
    private String genre;
    private String biography;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Album> albums;
}
