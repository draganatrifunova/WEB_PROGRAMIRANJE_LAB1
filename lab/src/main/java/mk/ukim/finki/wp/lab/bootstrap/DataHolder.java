package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = new ArrayList<>();
    public static List<Song> songList = new ArrayList<>();

    @PostConstruct
    public void init(){
        artistList.add(new Artist(10L, "Tose", "Proevski", "Najpoznat makedonski peac"));
        artistList.add(new Artist(12L, "Esma", "Redzepova", "Poznata romska peacka"));
        artistList.add(new Artist(13L, "Michael", "Jackson", "Poznat amerikanski peac"));
        artistList.add(new Artist(15L, "Sasa", "Kovacevic", "Poznat srpski peac"));
        artistList.add(new Artist(8L, "Mustafa", "Ceceli", "Poznat turski peac"));

        songList.add(new Song("TR001", "Bohemian Rhapsody", "Rock", 1975));
        songList.add(new Song("TR002", "Billie Jean", "Pop", 1982));
        songList.add(new Song("TR003", "Shape of You", "Pop", 2017));
        songList.add(new Song("TR004", "Smells Like Teen Spirit", "Grunge", 1991));
        songList.add(new Song("TR005", "Imagine", "Soft Rock", 1971));

    }

}
