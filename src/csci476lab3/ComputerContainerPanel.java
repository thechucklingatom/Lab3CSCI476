/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476lab3;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author thech_000
 */
public class ComputerContainerPanel extends JPanel implements ButtonPanel.Controller, ActionListener{
    
    boolean runSim;
    int howManyInfectTries;
    float probability;
    ArrayList<ComputerPanel> panelList;
    ArrayList<Integer> wormList;
    Random rand;
    Timer timer;
    
    public ComputerContainerPanel(){
        runSim = false;
        howManyInfectTries = 1;
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
        howManyInfectTries = Integer.parseInt(
                JOptionPane.showInputDialog("Please enter how many computers "
                        + "you want the worm to try to infect."));
        probability = Float.parseFloat(
                JOptionPane.showInputDialog("Please enter the probability of "
                        + "reinfection. 0 < p < 1"));
        
        for(ComputerPanel panel : panelList){
            panel.setProbabilityOfReinfection(probability);
        }
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
                ArrayList<Integer> removal = new ArrayList();
                for(Integer i : wormList){
                    for(int iter = 0; iter < howManyInfectTries; iter++){
                        nextInt = rand.nextInt(panelList.size());
                        if(panelList.get(nextInt).infectComputer()){
                            holder.add(nextInt);
                        }else{
                            removal.add(i);
                        }
                    }
                }
                for(Integer i : holder){
                    wormList.add(i);
                }
                
                for(Integer i : removal){
                    wormList.remove(i);
                }
                if(wormList.isEmpty()){
                    System.out.println("ded");
                    JOptionPane.showMessageDialog(this, "The worm died out.");
                    runSim = false;
                    int uninfectedCounter = 0;
                    int infectedOnceCounter = 0;
                    int infectedManyCounter = 0;
                    int mostInfected = 0;
                    for(ComputerPanel panel : panelList){
                        if(panel.isInfectable() && panel.getNumOfInfections() == 0){
                            uninfectedCounter++;
                        }else if(panel.getNumOfInfections() == 1){
                            infectedOnceCounter++;
                            mostInfected = mostInfected < 1 ? 1 : mostInfected;
                        }else if(panel.getNumOfInfections() > 1){
                            infectedManyCounter++;
                            mostInfected = 
                                    mostInfected < panel.getNumOfInfections() ? 
                                    panel.getNumOfInfections() : mostInfected;
                        }
                    }
                    
                    JOptionPane.showMessageDialog(this, "There were "
                            + uninfectedCounter + " uninfected machines. " + 
                            infectedOnceCounter + " infected once machines. " +
                            infectedManyCounter + " machines infected more than"
                            + " once.");
                }
                removal.clear();
                holder.clear();
            } 
            
            if(runSim){
                for(ComputerPanel panel : panelList){
                    if(panel.isInfectable() && panel.getNumOfInfections() > 1 || !panel.isInfectable()){
                        runSim = false;
                    }else{
                        runSim = true;
                        break;
                    }
                }
                
                if(!runSim){
                    JOptionPane.showMessageDialog(this, "The Worm infected all"
                            + " infectable computers at least twice!");
                }
            }
        }
    }
}
