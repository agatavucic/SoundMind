import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja playlistu sa nazivom, popisom pjesama i datumom kreiranja.
 * Playlist se serijalizira i sprema u datoteke.
 */
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<Song> songs;
    private LocalDate createdOn;

    /**
     * Kreira praznu playlistu sa zadanim imenom.
     * @param name naziv playliste
     */
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<Song>();
        this.createdOn = LocalDate.now();
    }

    /**
     * Dodaje novu pjesmu u playlistu.
     * @param song pjesma koja se dodaje
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /** @return naziv playliste */
    public String getName() {
        return name;
    }

    /** @return sve pjesme u playlisti */
    public List<Song> getSongs() {
        return songs;
    }

    /** @return datum kreiranja playliste */
    public LocalDate getCreatedOn() {
        return createdOn;
    }
}
