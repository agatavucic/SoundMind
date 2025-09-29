import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main framework of the application. Acts as a controller:
 * manages views (View panels), orchestrates events and persistence.
 */
public class MainFrame extends JFrame {

    private ToolBar toolBar;

    private SurveyFormPanel surveyPanel;
    private PlaylistViewPanel playlistViewPanel;
    private SavePanel savePanel;

    private HistoryPanel historyPanel;
    private HistoryPanel favoritesPanel;

    private LogPanel logPanel;
    private TextHistoryIO textIO;

    private JPanel currentCenter;
    private JPanel currentEast;

    private List<Playlist> history;
    private List<Playlist> favorites;
    private Playlist currentPlaylist;

    private File historyFile;
    private File favoritesFile;
    private File historyTxtFile;

    private PlaylistGenerator generator;

    /**
     * Creates the main window. The constructor is clean.
     */
    public MainFrame() {
        super("SoundMind");
    }

    /**
     * Initializes GUI components and connections (listeners), loads data.
     */
    public void init() {
        setLayout(new BorderLayout());
        setSize(980, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        toolBar = new ToolBar();
        toolBar.init();

        surveyPanel = new SurveyFormPanel();
        surveyPanel.init();

        playlistViewPanel = new PlaylistViewPanel();
        playlistViewPanel.init();

        savePanel = new SavePanel();
        savePanel.init();

        historyPanel = new HistoryPanel();
        historyPanel.init();

        favoritesPanel = new HistoryPanel();
        favoritesPanel.init();

        historyPanel.setHistoryPanelListener(new HistoryPanelListener() {
            public void favoriteRemoveRequested(Playlist playlist) {
                if (playlist == null) return;
                history.remove(playlist);
                historyPanel.updateHistory(history);
                saveHistory();
            }
        });

        favoritesPanel.setHistoryPanelListener(new HistoryPanelListener() {
            public void favoriteRemoveRequested(Playlist playlist) {
                if (playlist == null) return;
                favorites.remove(playlist);
                favoritesPanel.updateHistory(favorites);
                saveFavorites();
            }
        });

        logPanel = new LogPanel();
        logPanel.init();

        history = new ArrayList<Playlist>();
        favorites = new ArrayList<Playlist>();
        currentPlaylist = null;

        historyFile = new File("history.ser");
        favoritesFile = new File("favorites.ser");
        historyTxtFile = new File("history.txt");

        textIO = new TextHistoryIO(historyTxtFile);
        logPanel.setTextHistoryIO(textIO);

        generator = new PlaylistGenerator();

        add(toolBar, BorderLayout.NORTH);

        showCenterPanel(surveyPanel);
        clearEast();

        wireSurvey();
        wireToolbar();
        wireSavePanel();

        loadHistoryOnStart();
        loadFavoritesOnStart();

        setVisible(true);
    }

    /**
     * Connects the Survey panel to the controller (creates a playlist on submit).
     */
    private void wireSurvey() {
        surveyPanel.setSurveyListener(new SurveyListener() {
            public void surveySubmitted(SurveyEvent event) {
                currentPlaylist = generator.generate(event);
                history.add(currentPlaylist);
                historyPanel.updateHistory(history);
                playlistViewPanel.showPlaylist(currentPlaylist);
                showCenterPanel(playlistViewPanel);
                showEastPanel(savePanel);
                saveHistory();
                textIO.append(currentPlaylist);
            }
        });
    }

    /**
     * Connects ToolBar actions to changing central displays.
     */
    private void wireToolbar() {
        toolBar.setToolBarListener(new ToolBarListener() {
            public void showSurvey() {
                surveyPanel.reset();
                showCenterPanel(surveyPanel);
                clearEast();
            }
            public void showHistory() {
                historyPanel.updateHistory(history);
                showCenterPanel(historyPanel);
                clearEast();
            }
            public void showFavorites() {
                favoritesPanel.updateHistory(favorites);
                showCenterPanel(favoritesPanel);
                clearEast();
            }
            public void showLog() {
                showCenterPanel(logPanel);
                logPanel.reload();
                clearEast();
            }
        });
    }

    /**
     * Connects the Save panel (saving to favorites).
     */
    private void wireSavePanel() {
        savePanel.setSavePanelListener(new SavePanelListener() {
            public void saveToFavorites() {
                if (currentPlaylist == null) return;
                favorites.add(currentPlaylist);
                saveFavorites();
            }
        });
    }

    /**
     * Displays the default panel in the center of the window.
     * @param panel the panel to display
     */
    private void showCenterPanel(JPanel panel) {
        if (currentCenter != null) remove(currentCenter);
        currentCenter = panel;
        add(currentCenter, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Displays the default panel on the east side of the window.
     * @param panel the panel to display
     */
    private void showEastPanel(JPanel panel) {
        if (currentEast != null) remove(currentEast);
        currentEast = panel;
        add(currentEast, BorderLayout.EAST);
        revalidate();
        repaint();
    }

    /**
     * Removes the east panel.
     */
    private void clearEast() {
        if (currentEast != null) {
            remove(currentEast);
            currentEast = null;
            revalidate();
            repaint();
        }
    }

    /**
     * Loads history from a binary file.
     */
    private void loadHistoryOnStart() {
        if (!historyFile.exists()) return;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(historyFile));
            @SuppressWarnings("unchecked")
            List<Playlist> loaded = (List<Playlist>) ois.readObject();
            if (loaded != null) {
                history.clear();
                history.addAll(loaded);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (ois != null) ois.close(); } catch (Exception ignore) {}
        }
    }

    /**
     * Saves history to a binary file.
     */
    private void saveHistory() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(historyFile));
            oos.writeObject(history);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (oos != null) oos.close(); } catch (Exception ignore) {}
        }
    }

    /**
     * Loads favorites from a binary file.
     */
    private void loadFavoritesOnStart() {
        if (!favoritesFile.exists()) return;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(favoritesFile));
            @SuppressWarnings("unchecked")
            List<Playlist> loaded = (List<Playlist>) ois.readObject();
            if (loaded != null) {
                favorites.clear();
                favorites.addAll(loaded);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (ois != null) ois.close(); } catch (Exception ignore) {}
        }
    }

    /**
     * Saves favorites to a binary file.
     */
    private void saveFavorites() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(favoritesFile));
            oos.writeObject(favorites);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (oos != null) oos.close(); } catch (Exception ignore) {}
        }
    }
}
