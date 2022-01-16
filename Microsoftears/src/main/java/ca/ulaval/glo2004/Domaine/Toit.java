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
public class Toit extends Plan2D  implements Serializable{
    
  
    double largeur;
    
    List<PoutreAvant> poutreAvants =new ArrayList<>();;
    
    private Couleur CouleurPoutreAvant;
    
    
    private List<Point> arcExterieur;
    private List<Point> arcInterieur;

   private Polygon ShapeToit;
    private static final Toit SINGLETON_INSTANCE = new Toit();
    public static Toit getInstance() {
      return SINGLETON_INSTANCE;
    }

    public Polygon getShapeToit() {
        return ShapeToit;
    }

    public void setShapeToit(Polygon ShapeToit) {
        this.ShapeToit = ShapeToit;
    }
   
     private Toit(){ 
         super(new Point(),0,0, Color.BLACK);
         this.largeur= 0;
         this.CouleurPoutreAvant = new Couleur(Color.BLACK);
         poutreAvants = new ArrayList<>();
     }
     /***
     * Get the value of poutreAvants
     * @param quantitePoutreAvant
     * @param epaisseurPoutreAvant
     * @param largeurPoutreAvant 
     * @param couleurPoutreAvant 
     */
    public void setPoutreAvants(int quantitePoutreAvant, double epaisseurPoutreAvant, double largeurPoutreAvant, Color couleurPoutreAvant) {
        
       //TODO calculer les coodonnees positionX et positionY de chaque poutres
       int positionX;
       int positionY;
        poutreAvants = new ArrayList<>();
        for(int i=0 ; i<quantitePoutreAvant; i++){
            positionX=0;
            positionY=0;
            poutreAvants.add(i,  new PoutreAvant(epaisseurPoutreAvant, largeurPoutreAvant, new Point(positionX, positionY), couleurPoutreAvant));
        }
    }
    
    /**
     * Get the value of poutreAvants
     *
     * @return the value of poutreAvants
     */
    public List<PoutreAvant> getPoutreAvants() {
        return poutreAvants;
    }

   


    /**
     * Get the value of largeur
     *
     * @return the value of largeur
     */
    public double getLargeur() {
        return largeur;
    }

    /**
     * Set the value of largeur
     *
     * @param largeur new value of largeur
     */
    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }


   

    /**
     * Get the value of epaisseur
     *
     * @return the value of epaisseur
     */
    public double getEpaisseur() {
        return super.getHauteur();
    }

    /**
     * Set the value of epaisseur
     *
     * @param epaisseur new value of epaisseur
     */
    public void setEpaisseur(double epaisseur) {
         super.setHauteur(epaisseur);
    }
   
    /**
     * Get the value of CouleurPoutreAvant
     *
     * @return the value of CouleurPoutreAvant
     */
    public Couleur getCouleurPoutreAvant() {
        return CouleurPoutreAvant;
    }

    /**
     * Set the value of CouleurPoutreAvant
     *
     * @param CouleurPoutreAvant new value of CouleurPoutreAvant
     */
    public void setCouleurPoutreAvant(Color CouleurPoutreAvant) {
        this.CouleurPoutreAvant =new Couleur(CouleurPoutreAvant) ;
         for(PoutreAvant poutreAvant : poutreAvants){
           poutreAvant.setCouleur(CouleurPoutreAvant);
        }
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
