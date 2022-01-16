/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine.Plan;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public abstract class Plan2D extends Couleur  implements Serializable{
    
  
    Point positionInitiale = new Point(0,0);
    
    private Point positionCadran1= new Point(0,0);

    private Point positionCadran3= new Point(0,0);
    
    private Point positionCadran4= new Point(0,0);
    
    private Polygon shape= null;

    /**
     * Get the value of shape
     *
     * @return the value of shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * Set the value of shape
     *
     * @param shape new value of shape
     */
    public void setShape(Polygon shape) {
        this.shape = shape;
    }


    /**
     * Get the value of positionCadran3
     *
     * @return the value of positionCadran3
     */
    public Point getPositionCadran3() {
        return positionCadran3;
    }

    /**
     * Set the value of positionCadran3
     *
     * @param positionCadran3 new value of positionCadran3
     */
    public void setPositionCadran3(Point positionCadran3) {
        this.positionCadran3 = positionCadran3;
    }

   

    double longueur=0;
    double hauteur=0;
    
    public Plan2D() {
        super(Color.BLACK);
    }

    public Plan2D(Color couleur) {
        super(couleur);
        positionInitiale = new Point();
        positionCadran1 = new Point();
        positionCadran3 = new Point();
        positionCadran4 = new Point();
    }
    public Plan2D( Point positionInit, double longueur, double hauteur, Color couleur) {
        super(couleur);
        this.positionInitiale = positionInit;
        this.longueur= longueur;
        this.hauteur = hauteur;
        resetPosition();
    }
   
    public Point getPositionCadran2() {
        return positionInitiale;
    }

    public void setPositionCadran2(Point positionInitiale) {
        this.positionInitiale = positionInitiale;
        
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
        
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
        
    }
    
    /**
     * Get the value of positionCadran1
     *
     * @return the value of positionCadran1
     */
    public Point getPositionCadran1() {
        return positionCadran1;
    }

    /**
     * Set the value of positionCadran1
     *
     * @param positionCadran1 new value of positionCadran1
     */
    public void setPositionCadran1(Point positionCadran1) {
        this.positionCadran1 = positionCadran1;
    }
    
     /**
     * Get the value of positionCadran4
     *
     * @return the value of positionCadran4
     */
    public Point getPositionCadran4() {
        return positionCadran4;
    }

    /**
     * Set the value of positionCadran4
     *
     * @param positionCadran4 new value of positionCadran4
     */
    public void setPositionCadran4(Point positionCadran4) {
        this.positionCadran4 = positionCadran4;
    }
    
    public void resetPosition(){
        
        this.positionCadran1 =new Point(this.getPositionCadran2().x+(int)this.getLongueur(),this.getPositionCadran2().y);
        this.positionCadran4 =new Point(this.getPositionCadran2().x+(int)this.getLongueur(),this.getPositionCadran2().y+(int)this.getHauteur());
        this.positionCadran3 =new Point(this.getPositionCadran2().x,this.getPositionCadran2().y+(int)this.getHauteur());
     
    }
    
    /**
     * Verifier si un point exist dans le plan 2D
     * 
     * @param pointReel
     * @return 
     */
    public Boolean Exits(Point pointReel)
    {
        return this.getShape() != null && this.getShape().contains(pointReel);
    }
    
}
