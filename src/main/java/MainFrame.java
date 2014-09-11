import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    final static int DEFAULT_WIDTH = 800;
    final static int DEFAULT_HEIGHT = 600;
    final static String TITLE = "Szookacz";

    private JMenuBar menuBar;
    private JLabel console;
    private JPanel mainPanel;
    private String rootDir = System.getProperty("user.home") + "/Szookacz";
    private DatabaseInterface dbi;

    public MainFrame() {

        dbi = new DatabaseInterface();

        makeRootDirs();

         mainPanel = new JPanel();
         mainPanel.setLayout(new BorderLayout());
         mainPanel.setVisible(true);
         mainPanel.setPreferredSize(new Dimension(250, 800));
         getContentPane().add(mainPanel, "East");

        console = new JLabel();

        JScrollPane scrollPane = new JScrollPane(console);
        scrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, 25));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);
        getContentPane().add(scrollPane, "South");

        // Menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Button group for menu bar
        ButtonGroup buttonGroup = new ButtonGroup();

        // Menu bar elements (sub menus)
        JMenu fileMenu = new JMenu("File");
        buttonGroup.add(fileMenu);
        menuBar.add(fileMenu);

        // Submenu options
        // File -> Add new file
        JMenuItem importFileItem = new JMenuItem();
        importFileItem.setHorizontalTextPosition(SwingConstants.RIGHT);
        importFileItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        fileMenu.add(importFileItem);

        // File -> Add new tag
        JMenuItem importTagItem = new JMenuItem();
        importTagItem.setHorizontalTextPosition(SwingConstants.RIGHT);
        importTagItem.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
        fileMenu.add(importTagItem);

        // File -> Add tag to file
        JMenuItem addTagToFileItem = new JMenuItem();
        addTagToFileItem.setHorizontalTextPosition(SwingConstants.RIGHT);
        addTagToFileItem.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        fileMenu.add(addTagToFileItem);

        fileMenu.addSeparator();

        // File -> Exit
        JMenuItem exitItem = new JMenuItem(new ExitAction());
        exitItem.setHorizontalTextPosition(SwingConstants.RIGHT);
        exitItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        fileMenu.add(exitItem);
    }

    private class ExitAction extends AbstractAction {
        public ExitAction() {
            putValue(Action.NAME, "Exit");
            putValue(Action.SHORT_DESCRIPTION, "Exit the program");
            putValue(Action.MNEMONIC_KEY, new Integer('E'));
        }
        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
        }
    }

    private void makeRootDirs() {
        File directories = new File(rootDir);
        directories.mkdir();
        directories = new File(rootDir + "/no-tag");
        directories.mkdir();

        ArrayList<Tag> tags = dbi.getTags();
        for (Tag tag : tags) {
            directories = new File(rootDir + "/" + tag.getName());
            directories.mkdir();
        }
    }

    private class AddFileAction extends AbstractAction {
        public AddFileAction() {
            putValue(Action.NAME, "Add file");
            putValue(Action.SHORT_DESCRIPTION, "Add new file");
            putValue(Action.MNEMONIC_KEY, new Integer('f'));
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}