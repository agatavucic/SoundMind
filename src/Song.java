import java.io.Serializable;

/**
 * Represents a single song with title and artist.
 * The song is serializable and can be saved to a file.
 */
public class Song implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String artist;

    /**
     * Creates a new song with the given title and artist.
     * @param title song name
     * @param artist artist
     */
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    /** @return song name */
    public String getTitle() {
        return title;
    }

    /** @return song artist */
    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }
}
