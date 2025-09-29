import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.border.Border;

/**
 * Poll panel. User selects mood, genre, energy and preferences.
 */
public class SurveyFormPanel extends JPanel {

    private SurveyListener surveyListener;

    private JRadioButton rHappy;
    private JRadioButton rSad;
    private JRadioButton rCalm;
    private ButtonGroup moodGroup;

    private JComboBox genreCombo;
    private JComboBox energyCombo;

    private JCheckBox chkInstrumental;
    private JCheckBox chkAcoustic;

    private JButton submitBtn;
    private JButton resetBtn;

    /**
     * Empty constructor.
     */
    public SurveyFormPanel() {}

    /**
     * Initializes graphics components and events.
     */
    public void init() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridBagLayout());
        Border inner = BorderFactory.createTitledBorder("Survey");
        Border outer = BorderFactory.createEmptyBorder(8, 8, 8, 8);
        form.setBorder(BorderFactory.createCompoundBorder(outer, inner));

        rHappy = new JRadioButton("Happy");
        rSad   = new JRadioButton("Sad");
        rCalm  = new JRadioButton("Calm");
        moodGroup = new ButtonGroup();
        moodGroup.add(rHappy);
        moodGroup.add(rSad);
        moodGroup.add(rCalm);
        rHappy.setSelected(true);

        genreCombo = new JComboBox();
        genreCombo.addItem("Pop");
        genreCombo.addItem("Rock");
        genreCombo.addItem("Jazz");

        energyCombo = new JComboBox();
        energyCombo.addItem("High");
        energyCombo.addItem("Medium");
        energyCombo.addItem("Low");

        chkInstrumental = new JCheckBox("Instrumental only");
        chkAcoustic     = new JCheckBox("Acoustic vibe");

        submitBtn = new JButton("Submit");
        resetBtn  = new JButton("Reset");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Mood:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; form.add(rHappy, gbc);
        gbc.gridx = 2; gbc.gridy = 0; form.add(rSad, gbc);
        gbc.gridx = 3; gbc.gridy = 0; form.add(rCalm, gbc);

        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Genre:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; form.add(genreCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Energy:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; form.add(energyCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; form.add(new JLabel("Preferences:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; form.add(chkInstrumental, gbc);
        gbc.gridx = 2; gbc.gridy = 3; form.add(chkAcoustic, gbc);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(resetBtn);
        bottom.add(submitBtn);

        add(form, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (surveyListener != null) {
                    SurveyEvent ev = new SurveyEvent();
                    if (rHappy.isSelected()) ev.setMood("Happy");
                    else if (rSad.isSelected()) ev.setMood("Sad");
                    else ev.setMood("Calm");

                    ev.setGenre((String) genreCombo.getSelectedItem());
                    ev.setEnergy((String) energyCombo.getSelectedItem());
                    ev.setInstrumentalOnly(chkInstrumental.isSelected());
                    ev.setAcousticVibe(chkAcoustic.isSelected());

                    surveyListener.surveySubmitted(ev);
                }
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
    }

    /**
     * Resets the form to its initial values.
     */
    public void reset() {
        rHappy.setSelected(true);
        genreCombo.setSelectedIndex(0);
        energyCombo.setSelectedIndex(0);
        chkInstrumental.setSelected(false);
        chkAcoustic.setSelected(false);
    }

    /**
     * Sets the survey listener.
     * @param l SurveyListener interface implementation
     */
    public void setSurveyListener(SurveyListener l) {
        this.surveyListener = l;
    }
}
