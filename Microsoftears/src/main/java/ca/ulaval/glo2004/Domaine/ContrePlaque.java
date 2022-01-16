/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import ca.ulaval.glo2004.Domaine.Plan.Plan2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class ContrePlaque  extends Plan2D  implements Serializable{
    
    double epaisseur;
     
    protected ContrePlaque(double epaisseur,double longueur, double hauteur,   Color couleur, Point position) {
        super( position, longueur, hauteur, couleur);
        this.epaisseur = epaisseur;
       
    }
    
    protected ContrePlaque(){
        super(new Point(), 0, 0, Color.BLACK);
        this.epaisseur = 0;
       
    }

    /**
     * Get the value of epaisseur
     *
     * @return the value of epaisseur
     */
    public double getEpaisseur() {
        return epaisseur;
    }

    /**
     * Set the value of epaisseur
     *
     * @param epaisseur new value of epaisseur
     */
    public void setEpaisseur(double epaisseur) {
        this.epaisseur = epaisseur;
    }

}
