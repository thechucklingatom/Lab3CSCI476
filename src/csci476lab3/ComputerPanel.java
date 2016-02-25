/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476lab3;

import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author thech_000
 */
public class ComputerPanel extends JPanel{
    private boolean infectable;
    private int numOfInfections;
    private Random rand;
    
    public ComputerPanel(){
        rand = new Random();
        infectable = rand.nextBoolean();
        numOfInfections = 0;
        setBackground();
    }
    
    private void setBackground(){
        if(!isInfectable()){
            this.setBackground(Color.WHITE);
        }else if(getNumOfInfections() > 0 && getNumOfInfections() < 2){
            this.setBackground(Color.ORANGE);
        }else if(getNumOfInfections() > 1){
            this.setBackground(Color.RED);
        }else{
            this.setBackground(Color.BLACK);
        }
    }
    
    public boolean infectComputer(){
        if(isInfectable()){
            numOfInfections++;
            setBackground();
            return true;
        }
        return false;
    }    
    
    public void reset(){
        numOfInfections = 0;
        setBackground();
    }

    /**
     * @return the infectable
     */
    public boolean isInfectable() {
        return infectable;
    }

    /**
     * @return the numOfInfections
     */
    public int getNumOfInfections() {
        return numOfInfections;
    }
}
