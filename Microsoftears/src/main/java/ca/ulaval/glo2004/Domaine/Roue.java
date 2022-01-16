/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import ca.ulaval.glo2004.Domaine.Plan.Couleur;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class Roue extends Couleur  implements Serializable{
    
    
    private double rayon;

    private Point position;


    public Roue(double rayon, Point position, Color couleur) {
        super(couleur);
        this.rayon = rayon;
        this.position = position;
       
    }
    


    /**
     * Get the value of position
     *
     * @return the value of position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Set the value of position
     *
     * @param position new value of position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Get the value of rayon
     *
     * @return the value of rayon
     */
    public double getRayon() {
        return rayon;
    }

    /**
     * Set the value of rayon
     *
     * @param rayon new value of rayon
     */
    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    
}
