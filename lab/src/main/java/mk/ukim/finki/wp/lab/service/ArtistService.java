package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;

import java.util.List;

public interface ArtistService {
    public List<Artist> listArtists();
    public Artist findById(Long id);
}
