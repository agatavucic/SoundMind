import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Side panel with a button to save the current playlist to favorites.
 */
public class SavePanel extends JPanel {

    private JButton saveBtn;
    private SavePanelListener listener;

    /**
     * Empty constructor.
     */
    public SavePanel() {}

    /**
     * Initializes the GUI.
     */
    public void init() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        saveBtn = new JButton("Save to Favorites");
        add(saveBtn);

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listener != null) listener.saveToFavorites();
            }
        });
    }

    /**
     * Sets the save listener.
     * @param l SavePanelListener interface implementation
     */
    public void setSavePanelListener(SavePanelListener l) {
        this.listener = l;
    }
}
