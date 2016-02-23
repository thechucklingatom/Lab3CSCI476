/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476lab3;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author thech_000
 */
public class ButtonPanel extends JPanel{
    
    public interface Controller{
        public void runSimulation();
        public void updateValues();
        public void resetWorm();
    }
    
    private final Controller controller;
    private JButton runButton;
    private JButton updateButton;
    private JButton resetButton;
    
    public ButtonPanel(Controller controller){
        this.controller = controller;
        addButtons();
    }
    
    private void addButtons(){
        runButton = new JButton();
        runButton.setText("Run Simulation");
        runButton.addActionListener((ActionEvent e) -> {
            controller.runSimulation();
        });
        this.add(runButton);
        
        updateButton = new JButton();
        updateButton.setText("Update Values");
        updateButton.addActionListener((ActionEvent e) -> {
            controller.updateValues();
        });
        this.add(updateButton);
        
        resetButton = new JButton();
        resetButton.setText("Reset Worm");
        resetButton.addActionListener((ActionEvent e) -> {
            controller.resetWorm();
        });
        this.add(resetButton);
    }
}
