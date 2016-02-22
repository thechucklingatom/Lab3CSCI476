/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476lab3;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author thech_000
 */
public class CSCI476Lab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
        jFrame.setSize(1280, 720);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ComputerPanel computerPanel = new ComputerPanel();
        jFrame.add(computerPanel, BorderLayout.NORTH);
        ButtonPanel buttonPanel = new ButtonPanel(computerPanel);
        jFrame.add(buttonPanel, BorderLayout.SOUTH);
        jFrame.setVisible(true);
    }
    
}
