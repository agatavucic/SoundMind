import javax.swing.SwingUtilities;

/**
 * Entry point for the SoundMind application.
 * Launches the main window on the EDT thread.
 */
public class App {

    /**
     * Starts the application.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.init();
            }
        });
    }
}
