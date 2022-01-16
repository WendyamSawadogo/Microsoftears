/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class CoucheInterieur extends ContrePlaque  implements Serializable{
  
   double epaisseurToit;

   
    protected CoucheInterieur(){
        super();
        this.epaisseurToit = 0;
    }
   
   

    public double getEpaisseurToit() {
        return epaisseurToit;
    }

    public void setEpaisseurToit(double epaisseurToit) {
        this.epaisseurToit = epaisseurToit;
    }
    
}
