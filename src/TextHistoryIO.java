import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Simple IO over a history text file (history.txt).
 * Allows appending records and loading the entire content.
 */
public class TextHistoryIO {

    private final File textFile;
    private final DateTimeFormatter fmt;

    /**
     * @param file text file to save/read history
     */
    public TextHistoryIO(File file) {
        this.textFile = file;
        this.fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    /**
     * Adds a single playlist as a line to a text file.
     * @param playlist playlist to be recorded
     */
    public void append(Playlist playlist) {
        if (playlist == null) return;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(textFile, true));
            StringBuilder sb = new StringBuilder();
            sb.append(playlist.getCreatedOn().format(fmt));
            sb.append(" | ");
            sb.append(playlist.getName() == null ? "" : playlist.getName());
            sb.append(" | ");
            List<Song> songs = playlist.getSongs();
            for (int i = 0; i < songs.size(); i++) {
                Song s = songs.get(i);
                sb.append(s.getTitle() == null ? "" : s.getTitle());
                sb.append(" - ");
                sb.append(s.getArtist() == null ? "" : s.getArtist());
                if (i < songs.size() - 1) sb.append("; ");
            }
            sb.append(System.lineSeparator());
            bw.write(sb.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (bw != null) bw.close(); } catch (Exception ignore) {}
        }
    }

    /**
     * Loads and returns the entire contents of a text file.
     * @return the contents of the file or an empty string if it doesn't exist/error
     */
    public String readAll() {
        if (!textFile.exists()) return "";
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(textFile));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (br != null) br.close(); } catch (Exception ignore) {}
        }
        return sb.toString();
    }
}
