import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A panel that displays the contents of a history text file (history.txt).
 * The content is loaded each time the panel is displayed (call reload()).
 */
public class LogPanel extends JPanel {

    private JTextArea area;
    private TextHistoryIO io;

    /**
     * Initializes the GUI.
     */
    public LogPanel() {}

    /**
     * Initializes the GUI.
     */
    public void init() {
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);

        add(new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
                BorderLayout.CENTER);
    }

    /**
     * Sets an IO object for reading/writing text history.
     * @param io TextHistoryIO instance
     */
    public void setTextHistoryIO(TextHistoryIO io) {
        this.io = io;
    }

    /**
     * Loads the contents of a file and displays it in the text area.
     */
    public void reload() {
        if (io == null) return;
        String content = io.readAll();
        area.setText(content);
        area.setCaretPosition(0);
    }
}
