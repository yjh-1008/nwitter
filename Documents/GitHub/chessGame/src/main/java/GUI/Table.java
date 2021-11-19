package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    private final JFrame gameFrame;
    private static Dimension OUTER_FRAME_DEMENSION= new Dimension(600,600);
    public Table(){
        this.gameFrame= new JFrame("JCHESS");
        final JMenuBar tableMenuBar= new JMenuBar();
        populateMenubar(tableMenuBar);
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DEMENSION);
        this.gameFrame.setVisible(true);

    }

    private void populateMenubar(JMenuBar tableMenuBar) {
        tableMenuBar.add(createFileMenu());
    }
    private JMenu createFileMenu(){
        final JMenu fileMenu= new JMenu("File");

        final JMenuItem openPGN=new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          System.out.println("open up that pgn file!!");
                                      }
        });

        fileMenu.add(openPGN);

        return fileMenu;
    }

}
