package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository {
    public List<Song> findAll(){
        return DataHolder.songList;
    }

    public Song findByTrackId(String trackId){
        return DataHolder.songList
                .stream()
                .filter(song -> song.getTrackId().equals(trackId))
                .findFirst()
                .orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        for(Song s:DataHolder.songList){
            if(s.getTrackId().equals(song.getTrackId())){
                s.addArtistToList(artist);
                return artist;
            }
        }
        return null;
    }
}
