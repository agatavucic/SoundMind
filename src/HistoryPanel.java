import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * Panel that displays playlists in a table, offers sorting, search by date
 * and removing the selected playlist (Remove).
 */
public class HistoryPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;

    private JPanel controls;
    private JButton btnSortDate;
    private JButton btnSortName;
    private JButton btnReset;
    private JLabel lblDate;
    private JTextField dateField;
    private JButton btnSearch;
    private JButton btnRemove;

    private List<Playlist> allPlaylists;
    private List<Playlist> visible;

    private DateTimeFormatter fmt;
    private HistoryPanelListener listener;

    /**
     * Empty constructor.
     */
    public HistoryPanel() {
        super();
    }

    /**
     *Initializes the GUI, table, and controls.
     */
    public void init() {
        allPlaylists = new ArrayList<Playlist>();
        visible = new ArrayList<Playlist>();
        fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        setLayout(new BorderLayout());

        controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnSortDate = new JButton("Sort by Date");
        btnSortName = new JButton("Sort by Name");
        btnReset    = new JButton("Reset");
        lblDate     = new JLabel("Date (dd.MM.yyyy):");
        dateField   = new JTextField(10);
        btnSearch   = new JButton("Search");
        btnRemove   = new JButton("Remove");

        controls.add(btnSortDate);
        controls.add(btnSortName);
        controls.add(btnReset);
        controls.add(lblDate);
        controls.add(dateField);
        controls.add(btnSearch);
        controls.add(btnRemove);

        add(controls, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[] { "Date", "Playlist", "Songs" }, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
            public Class<?> getColumnClass(int columnIndex) { return String.class; }
        };

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(220);
        table.getColumnModel().getColumn(2).setPreferredWidth(600);
        table.setDefaultRenderer(String.class, new MultiLineCellRenderer());

        add(new JScrollPane(table), BorderLayout.CENTER);

        activateControls();
    }

    /**
     * Sets the panel listener (controller).
     * @param l HistoryPanelListener interface implementation
     */
    public void setHistoryPanelListener(HistoryPanelListener l) {
        this.listener = l;
    }

    /**
     * Updates the playlist list view.
     * @param list the new list of playlists to display
     */
    public void updateHistory(List<Playlist> list) {
        setPlaylists(list);
    }

    /**
     * Sets all playlists and displays them.
     * @param list all playlists
     */
    public void setPlaylists(List<Playlist> list) {
        allPlaylists.clear();
        visible.clear();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                allPlaylists.add(list.get(i));
            }
        }
        showAll();
    }

    /**
     * @return currently selected playlist or null
     */
    public Playlist getSelectedPlaylist() {
        int row = table.getSelectedRow();
        if (row >= 0 && row < visible.size()) {
            return visible.get(row);
        }
        return null;
    }

    /**
     * Show all playlists.
     */
    private void showAll() {
        visible.clear();
        for (int i = 0; i < allPlaylists.size(); i++) {
            visible.add(allPlaylists.get(i));
        }
        refreshTable();
    }

    /**
     * Sorts the display by date ascending.
     */
    private void sortByDateAsc() {
        List<Playlist> copy = new ArrayList<Playlist>(visible);
        Collections.sort(copy, new Comparator<Playlist>() {
            public int compare(Playlist a, Playlist b) {
                return a.getCreatedOn().compareTo(b.getCreatedOn());
            }
        });
        visible = copy;
        refreshTable();
    }

    /**
     * Sorts the display by name in ascending order (case-insensitive).
     */
    private void sortByNameAsc() {
        List<Playlist> copy = new ArrayList<Playlist>(visible);
        Collections.sort(copy, new Comparator<Playlist>() {
            public int compare(Playlist a, Playlist b) {
                String sa = a.getName() == null ? "" : a.getName();
                String sb = b.getName() == null ? "" : b.getName();
                return sa.compareToIgnoreCase(sb);
            }
        });
        visible = copy;
        refreshTable();
    }

    /**
     * Filters by the exact date entered (format dd.MM.yyyy).
     * @param text requested date
     */
    private void searchByDate(String text) {
        String needle = text == null ? "" : text.trim();
        if (needle.length() == 0) return;

        List<Playlist> filtered = new ArrayList<Playlist>();
        for (int i = 0; i < allPlaylists.size(); i++) {
            Playlist p = allPlaylists.get(i);
            String d = p.getCreatedOn().format(fmt);
            if (needle.equals(d)) {
                filtered.add(p);
            }
        }
        visible = filtered;
        refreshTable();
    }

    /**
     * Faster action buttons.
     */
    private void activateControls() {
        btnSortDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { sortByDateAsc(); }
        });
        btnSortName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { sortByNameAsc(); }
        });
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { showAll(); }
        });
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txt = dateField.getText();
                if (txt != null && txt.trim().length() > 0) {
                    searchByDate(txt);
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Playlist sel = getSelectedPlaylist();
                if (sel != null && listener != null) {
                    listener.favoriteRemoveRequested(sel);
                }
            }
        });
    }

    /**
     * Populates the table from the list of visible playlists.
     */
    private void refreshTable() {
        model.setRowCount(0);
        for (int i = 0; i < visible.size(); i++) {
            Playlist p = visible.get(i);
            model.addRow(new Object[] {
                    p.getCreatedOn().format(fmt),
                    p.getName() == null ? "" : p.getName(),
                    joinSongs(p.getSongs())
            });
        }
        adjustAllRowHeights();
    }

    /**
     * Merges a list of songs into a multiline text.
     * @param songs list of songs
     * @return multiline list of songs
     */
    private String joinSongs(List<Song> songs) {
        if (songs == null || songs.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        int n = Math.min(5, songs.size());
        for (int i = 0; i < n; i++) {
            Song s = songs.get(i);
            String title  = s.getTitle()  == null ? "" : s.getTitle();
            String artist = s.getArtist() == null ? "" : s.getArtist();
            sb.append(title).append(" - ").append(artist);
            if (i < n - 1) sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Adjusts row height to cell content (multi-line).
     */
    private void adjustAllRowHeights() {
        for (int row = 0; row < table.getRowCount(); row++) {
            int max = table.getRowHeight();
            for (int col = 0; col < table.getColumnCount(); col++) {
                TableCellRenderer r = table.getCellRenderer(row, col);
                Component c = table.prepareRenderer(r, row, col);
                int h = c.getPreferredSize().height + table.getRowMargin();
                if (h > max) max = h;
            }
            table.setRowHeight(row, max);
        }
    }

    /**
     * Renderer for multiline text in table cells.
     */
    private static class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
        public MultiLineCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            setSize(table.getColumnModel().getColumn(column).getWidth(), Integer.MAX_VALUE);
            int preferredHeight = getPreferredSize().height + table.getRowMargin();
            if (table.getRowHeight(row) != preferredHeight) {
                table.setRowHeight(row, preferredHeight);
            }
            return this;
        }
    }
}
