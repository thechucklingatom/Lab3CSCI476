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
    boolean infectable;
    int numOfInfections;
    Random rand;
    
    public ComputerPanel(){
        rand = new Random();
        infectable = rand.nextBoolean();
        numOfInfections = 0;
        setBackground();
    }
    
    private void setBackground(){
        if(!infectable){
            this.setBackground(Color.WHITE);
        }else if(numOfInfections > 0 && numOfInfections < 2){
            this.setBackground(Color.ORANGE);
        }else if(numOfInfections > 1){
            this.setBackground(Color.RED);
        }else{
            this.setBackground(Color.BLACK);
        }
    }
    
    public boolean infectComputer(){
        if(infectable){
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
}
