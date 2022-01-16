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
public class Ressort extends Couleur  implements Serializable{
    
    private double longeurMinimale;

    private double longeurMaximale;

    private double force;
    
    private Point positionSurMur;
    
    private Point positionSurHayon;

    /***
     * Constructeur du ressort
     * 
     * @param longeurMinimale
     * @param longeurMaximale
     * @param force
     * @param positionSurMur
     * @param positionSurHayon
     * @param couleur 
     */
    public Ressort(double longeurMinimale, double longeurMaximale, double force, Point positionSurMur, Point positionSurHayon, Color couleur) {
       super(couleur);
        this.longeurMinimale = longeurMinimale;
        this.longeurMaximale = longeurMaximale;
        this.force = force;
        this.positionSurMur = positionSurMur;
        this.positionSurHayon = positionSurHayon;
        
    }

    Ressort() {
       super(Color.BLACK);
    }
    
    

    /**
     * Get the value of positionSurHayon
     *
     * @return the value of positionSurHayon
     */
    public Point getPositionSurHayon() {
        return positionSurHayon;
    }

    /**
     * Set the value of positionSurHayon
     *
     * @param positionSurHayon new value of positionSurHayon
     */
    public void setPositionSurHayon(Point positionSurHayon) {
        this.positionSurHayon = positionSurHayon;
    }


    /**
     * Get the value of positionSurMur
     *
     * @return the value of positionSurMur
     */
    public Point getPositionSurMur() {
        return positionSurMur;
    }

    /**
     * Set the value of positionSurMur
     *
     * @param positionSurMur new value of positionSurMur
     */
    public void setPositionSurMur(Point positionSurMur) {
        this.positionSurMur = positionSurMur;
    }


    /**
     * Get the value of force
     *
     * @return the value of force
     */
    public double getForce() {
        return force;
    }

    /**
     * Set the value of force
     *
     * @param force new value of force
     */
    public void setForce(double force) {
        this.force = force;
    }

    /**
     * Get the value of longeurMaximale
     *
     * @return the value of longeurMaximale
     */
    public double getLongeurMaximale() {
        return longeurMaximale;
    }

    /**
     * Set the value of longeurMaximale
     *
     * @param longeurMaximale new value of longeurMaximale
     */
    public void setLongeurMaximale(double longeurMaximale) {
        this.longeurMaximale = longeurMaximale;
    }

    
    /**
     * Get the value of longeurMinimale
     *
     * @return the value of longeurMinimale
     */
    public double getLongeurMinimale() {
        return longeurMinimale;
    }

    /**
     * Set the value of longeurMinimale
     *
     * @param longeurMinimale new value of longeurMinimale
     */
    public void setLongeurMinimale(double longeurMinimale) {
        this.longeurMinimale = longeurMinimale;
    }

    
}
