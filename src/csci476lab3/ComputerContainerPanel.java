/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476lab3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author thech_000
 */
public class ComputerContainerPanel extends JPanel implements ButtonPanel.Controller{
    
    ArrayList<JPanel> panelList;
    Random rand;
    
    public ComputerContainerPanel(){
        rand = new Random();
        this.setSize(1280, 620);
        this.setLayout(new GridLayout(50, 200, 1, 1));
        panelList = new ArrayList();
        for(int i = 0; i < 10000; i++){
            panelList.add(new ComputerPanel());
            panelList.get(i).setPreferredSize(new Dimension(10,10));
            this.add(panelList.get(i));
        }
    }

    @Override
    public void runSimulation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetWorm() {
        rand = new Random();
        for (JPanel panel : panelList) {
            if(rand.nextBoolean()){
                panel.setBackground(Color.BLACK);
            }else{
                panel.setBackground(Color.WHITE);
            }
        }
    }
    
}
