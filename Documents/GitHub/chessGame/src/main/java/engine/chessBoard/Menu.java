/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.chessBoard;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private JButton exitBTN;
    private JLabel infoLBL;
    private JButton playBTN;
    private JLabel title;
    private Image bg;
    public Menu() {
        initComponents();

    }

    public JButton getExitBTN() {
        return exitBTN;
    }


    public JLabel getInfoLBL() {
        return infoLBL;
    }

    public JButton getPlayBTN() {
        return playBTN;
    }

    public JLabel getTitle(){
        return title;
    }

    private void initComponents() {

        playBTN = new JButton();
        exitBTN = new JButton();
        infoLBL = new JLabel();
        title = new JLabel();

        playBTN.setText("Search Match");
        playBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBTNActionPerformed(evt);
            }
        });

        exitBTN.setText("Exit");
        exitBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBTNActionPerformed(evt);
            }
        });

        infoLBL.setText("Matching");
        title.setText("Welcome to Online ChessGame!!");
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(title)
                                        .addComponent(playBTN, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                        .addComponent(exitBTN, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(40, 40, 40)
                                .addComponent(infoLBL)
                                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(127,127,127)
                                 .addComponent(title)
                                .addGap(127, 127, 127)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(playBTN)
                                        .addComponent(infoLBL))
                                .addGap(29, 29, 29)
                                .addComponent(exitBTN)
                                .addContainerGap(190, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void playBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_playBTNActionPerformed

    private void exitBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBTNActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables

}
