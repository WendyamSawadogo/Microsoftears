/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class MurLateral  implements Serializable{
    
   
   private Squelette squelette;

   private CoucheExterieur coucheExterieur;

    private Interieur coucheInterieur;
    
    private double epaisseurCaoutchouc;
    
    private static final MurLateral SINGLETON_INSTANCE = new MurLateral();
    public static MurLateral getInstance() {
       return SINGLETON_INSTANCE;
     }
    
    /***
     * Constructeur du Mur lateral 
     * @param murCoucheExterieur
     * @param murCoucheInterieur
     * @param murCoucheSequelette
     * @param porte
     * @param fenetre
     * @param epaisseurCaoutchouc 
     */
     private  MurLateral() {
        this.squelette = Squelette.getInstance();
        this.coucheExterieur = CoucheExterieur.getInstance();
        this.coucheInterieur = Interieur.getInstance();
        this.epaisseurCaoutchouc = 0;
    }
    
    

    /**
     * Get the value of epaisseurCaoutchouc
     *
     * @return the value of epaisseurCaoutchouc
     */
    public double getEpaisseurCaoutchouc() {
        return epaisseurCaoutchouc;
    }

    /**
     * Set the value of epaisseurCaoutchouc
     *
     * @param epaisseurCaoutchouc new value of epaisseurCaoutchouc
     */
    protected void setEpaisseurCaoutchouc(double epaisseurCaoutchouc) {
        this.epaisseurCaoutchouc = epaisseurCaoutchouc;
    }


    /**
     * Get the value of fenetre
     *
     * @return the value of fenetre
     */
    public Fenetre getFenetre() {
        return Fenetre.getInstance();
    }

   


    /**
     * Get the value of porte
     *
     * @return the value of porte
     */
    public Porte getPorte() {
        return Porte.getInstance();
    }

    /**
     * Get the value of murCoucheSequelette
     *
     * @return the value of murCoucheSequelette
     */
    public Squelette getMurCoucheSequelette() {
        return Squelette.getInstance();
    }

    
    /**
     * Get the value of murCoucheInterieur
     *
     * @return the value of murCoucheInterieur
     */
    public Interieur getMurCoucheInterieur() {
        return Interieur.getInstance();
    }

   

    /**
     * Get the value of murCoucheExterieur
     *
     * @return the value of murCoucheExterieur
     */
    public CoucheExterieur getMurCoucheExterieur() {
        return CoucheExterieur.getInstance();
    }

    double getEpaisseur() {
        return Interieur.getInstance().getEpaisseur()+Squelette.getInstance().getEpaisseur()+CoucheExterieur.getInstance().getEpaisseur();
    }

     public void setDimmensions(double longeur, double largeur){
     CoucheExterieur.getInstance().setLongueur(longeur);
     CoucheExterieur.getInstance().setHauteur(largeur);
     
     Interieur.getInstance().setLongueur(longeur);
     Interieur.getInstance().setHauteur(largeur);
     
     Squelette.getInstance().setLongueur(longeur);
     Squelette.getInstance().setHauteur(largeur);
     
     }
     
      public void setPositionInit(Point position){
        CoucheExterieur.getInstance().setPositionCadran2(position);
        Interieur.getInstance().setPositionCadran2(position);
        Squelette.getInstance().setPositionCadran2(position);
      }
    
    

    /**
     * Get the value of coucheInterieur
     *
     * @return the value of coucheInterieur
     */
    public Interieur getCoucheInterieur() {
        return coucheInterieur;
    }

    /**
     * Get the value of coucheExterieur
     *
     * @return the value of coucheExterieur
     */
    public CoucheExterieur getCoucheExterieur() {
        return coucheExterieur;
    }

      /**
     * Get the value of squelette
     *
     * @return the value of squelette
     */
    public Squelette getSquelette() {
        return squelette;
    }

}
