import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panel for displaying the selected playlist in text form (JTextArea).
 */
public class PlaylistViewPanel extends JPanel {

    private JTextArea area;

    /**
     * Empty constructor.
     */
    public PlaylistViewPanel() {}

    /**
     * Initializes the graphics components.
     */
    public void init() {
        setLayout(new BorderLayout());
        area = new JTextArea();
        area.setEditable(false);
        add(new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
                BorderLayout.CENTER);
    }

    /**
     * Displays the default playlist.
     * @param p the playlist to display
     */
    public void showPlaylist(Playlist p) {
        if (p == null) {
            area.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(p.getName()).append("\n\n");
        for (int i = 0; i < p.getSongs().size(); i++) {
            Song s = p.getSongs().get(i);
            sb.append(i + 1).append(". ");
            sb.append(s.getTitle() == null ? "" : s.getTitle());
            sb.append(" - ");
            sb.append(s.getArtist() == null ? "" : s.getArtist());
            sb.append("\n");
        }
        area.setText(sb.toString());
        area.setCaretPosition(0);
    }
}
