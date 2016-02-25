/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476lab3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author thech_000
 */
public class ComputerContainerPanel extends JPanel implements ButtonPanel.Controller, ActionListener{
    
    boolean runSim;
    ArrayList<ComputerPanel> panelList;
    ArrayList<Integer> wormList;
    Random rand;
    Timer timer;
    
    public ComputerContainerPanel(){
        runSim = false;
        rand = new Random();
        this.setSize(1280, 620);
        this.setLayout(new GridLayout(50, 200, 1, 1));
        panelList = new ArrayList();
        for(int i = 0; i < 10000; i++){
            panelList.add(new ComputerPanel());
            panelList.get(i).setPreferredSize(new Dimension(10,10));
            this.add(panelList.get(i));
        }
        wormList = new ArrayList();
        
        timer = new Timer(1000, this);
        timer.setInitialDelay(1000);
        timer.start();
    }

    @Override
    public void runSimulation() {
        runSim = true;        
    }

    @Override
    public void updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetWorm() {
        runSim = false;
        rand = new Random();
        for(ComputerPanel panel : panelList){
            panel.reset();
        }
        wormList.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        runTheSim();
        repaint();
    }
    
    private void runTheSim(){
        if(runSim){
            int nextInt;
            nextInt = rand.nextInt(panelList.size());
            //panelList.get(nextInt).infectComputer();
            if(wormList.isEmpty()){
            
                if(panelList.get(nextInt).infectComputer()){
                    wormList.add(nextInt);
                }
                repaint();
            }else{
                ArrayList<Integer> holder = new ArrayList();
                for(Integer i : wormList){
                    nextInt = rand.nextInt(panelList.size());
                    if(panelList.get(nextInt).infectComputer()){
                        holder.add(nextInt);
                    }
                }
                for(Integer i : holder){
                    wormList.add(i);
                }
                holder.clear();
            } 
            
            for(ComputerPanel panel : panelList){
                if(panel.isInfectable() && panel.getNumOfInfections() > 1 || !panel.isInfectable()){
                    runSim = false;
                }else{
                    runSim = true;
                    break;
                }
            }
        }
    }
}
