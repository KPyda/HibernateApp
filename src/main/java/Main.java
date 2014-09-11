import com.alee.laf.WebLookAndFeel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();

                final MainFrame mainFrame = new MainFrame();
                mainFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
                mainFrame.setSize(MainFrame.DEFAULT_WIDTH, MainFrame.DEFAULT_HEIGHT);
                mainFrame.setLocationByPlatform(true);
                mainFrame.setTitle(MainFrame.TITLE);
                mainFrame.setVisible(true);
            }
        });
    }
}
