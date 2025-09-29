import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Application toolbar with navigation buttons.
 */
public class ToolBar extends JPanel {

    private JButton surveyBtn;
    private JButton historyBtn;
    private JButton favoritesBtn;
    private JButton logBtn;

    private ToolBarListener toolBarListener;

    /**
     * Empty constructor.
     */
    public ToolBar() {}

    /**
     * Button and event initialization.
     */
    public void init() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        surveyBtn = new JButton("Survey");
        historyBtn = new JButton("History");
        favoritesBtn = new JButton("Favorites");
        logBtn = new JButton("Log");

        add(surveyBtn);
        add(historyBtn);
        add(favoritesBtn);
        add(logBtn);

        surveyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toolBarListener != null) toolBarListener.showSurvey();
            }
        });
        historyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toolBarListener != null) toolBarListener.showHistory();
            }
        });
        favoritesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toolBarListener != null) toolBarListener.showFavorites();
            }
        });
        logBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toolBarListener != null) toolBarListener.showLog();
            }
        });
    }

    /**
     * Sets the toolbar listener.
     * @param lst interface implementation ToolBarListener
     */
    public void setToolBarListener(ToolBarListener lst) {
        this.toolBarListener = lst;
    }
}
