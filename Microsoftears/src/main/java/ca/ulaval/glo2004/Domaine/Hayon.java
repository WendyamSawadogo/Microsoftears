/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import ca.ulaval.glo2004.Domaine.Plan.Couleur;
import ca.ulaval.glo2004.Domaine.Plan.Plan2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Solution
 */
public class Hayon extends Plan2D  implements Serializable{
    double epaisseur;
    double poids;
    
    double rayonArcSuperieur;
    double epaisseurTraitScie;
    double distancePoutreArriere;
    double distancePlancher;
   
    Point positionAncrageRessort1;
    Point positionAncrageRessort2;
    
    double longueur;
    double largeur;
  private Polygon ShapeHayonBezier;
    private List<Point> arcExterieur;
    private List<Point> arcInterieur;

    private static final Hayon SINGLETON_INSTANCE = new Hayon();
     public static Hayon getInstance() {
      return SINGLETON_INSTANCE;
    }

    public Polygon getShapeHayonBezier() {
        return ShapeHayonBezier;
    }

    public void setShapeHayonBezier(Polygon ShapeHayonBezier) {
        this.ShapeHayonBezier = ShapeHayonBezier;
    }


     protected Hayon() {
        super(Color.GREEN);
        this.epaisseur = 0.0;
        this.poids = 50;
        this.rayonArcSuperieur = 0;
        this.epaisseurTraitScie = 0;
        this.distancePoutreArriere = 0;
        this.distancePlancher = 0;
        this.longueur = 0;
        this.largeur = 0;
       
    }
 
    public double getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(double epaisseur) {
        this.epaisseur = epaisseur;
    }

    public double getPoidsPoutre() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getRayonArcSuperieur() {
        return rayonArcSuperieur;
    }

    public void setRayonArcSuperieur(double rayonArcSuperieur) {
        this.rayonArcSuperieur = rayonArcSuperieur;
    }

    public double getEpaisseurTraitScie() {
        return epaisseurTraitScie;
    }

    public void setEpaisseurTraitScie(double epaisseurTraitScie) {
        this.epaisseurTraitScie = epaisseurTraitScie;
    }

    public double getDistancePoutreArriere() {
        return distancePoutreArriere;
    }

    public void setDistancePoutreArriere(double distancePoutreArriere) {
        this.distancePoutreArriere = distancePoutreArriere;
    }

    public double getDistancePlancher() {
        return distancePlancher;
    }

    public void setDistancePlancher(double distancePlancher) {
        this.distancePlancher = distancePlancher;
    }

    public Point getPositionAncrageRessort1() {
        return positionAncrageRessort1;
    }

    public void setPositionAncrageRessort1(Point positionAncrageRessort1) {
        this.positionAncrageRessort1 = positionAncrageRessort1;
    }

    public Point getPositionAncrageRessort2() {
        return positionAncrageRessort2;
    }

    public void setPositionAncrageRessort2(Point positionAncrageRessort2) {
        this.positionAncrageRessort2 = positionAncrageRessort2;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }


     /**
     * Get the value of arcInterieur
     *
     * @return the value of arcInterieur
     */
    public List<Point> getArcInterieur() {
        return arcInterieur;
    }

    /**
     * Set the value of arcInterieur
     *
     * @param arcInterieur new value of arcInterieur
     */
    public void setArcInterieur(List<Point> arcInterieur) {
        this.arcInterieur = arcInterieur;
    }
/**
     * Get the value of arcExterieur
     *
     * @return the value of arcExterieur
     */
    public List<Point> getArcExterieur() {
        return arcExterieur;
    }

    /**
     * Set the value of arcExterieur
     *
     * @param arcExterieur new value of arcExterieur
     */
    public void setArcExterieur(List<Point> arcExterieur) {
        this.arcExterieur = arcExterieur;
    }

    
    
}
