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
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class MurSeparateur extends Plan2D  implements Serializable{
    
    double epaisseur;

    private double distanceDuPlancher;
   
    private double distancePoutreArriere;
    
   private static final MurSeparateur SINGLETON_INSTANCE = new MurSeparateur();
   public static MurSeparateur getInstance() {
      return SINGLETON_INSTANCE;
    }
    
   private MurSeparateur(){
   super(Color.cyan);
   distanceDuPlancher = 0;
   distancePoutreArriere = 0;
   this.epaisseur = 0;
   }
   private Polygon shapeMurSeparateur;
    /**
     * Get the value of epaisseur
     *
     * @return the value of epaisseur
     */
    public double getEpaisseur() {
        return epaisseur;
    }

    public Polygon getShapeMurSeparateur() {
        return shapeMurSeparateur;
    }

    public void setShapeMurSeparateur(Polygon shapeMurSeparateur) {
        this.shapeMurSeparateur = shapeMurSeparateur;
    }

    /**
     * Set the value of epaisseur
     *
     * @param epaisseur new value of epaisseur
     */
    public void setEpaisseur(double epaisseur) {
        this.epaisseur = epaisseur;
    }
    
    /**
     * Get the value of distancePoutreArriere
     *
     * @return the value of distancePoutreArriere
     */
    public double getDistancePoutreArriere() {
        return distancePoutreArriere;
    }

    /**
     * Set the value of distancePoutreArriere
     *
     * @param distancePoutreArriere new value of distancePoutreArriere
     */
    public void setDistancePoutreArriere(double distancePoutreArriere) {
        this.distancePoutreArriere = distancePoutreArriere;
    }

    /**
     * Get the value of distanceDuPlancher
     *
     * @return the value of distanceDuPlancher
     */
    public double getDistanceDuPlancher() {
        return distanceDuPlancher;
    }

    public void setDistanceDuPlancher(double distanceDuPlancher) {
        this.distanceDuPlancher = distanceDuPlancher;
    }
    
    public void calculerHauteur(double hauteurProfil, double hauteurPlanche) {
        this.setHauteur( hauteurProfil-(hauteurPlanche+this.distanceDuPlancher));
    }
    
    public void calculerPosition(Point positionPoutreArriere, double largeurPoutreArreire) {
        this.setPositionCadran2(new Point(positionPoutreArriere.x+ (int)(largeurPoutreArreire+this.distancePoutreArriere), positionPoutreArriere.y));
    }
    
 /*  @Override
    public Polygon getShape() {
       
        Point Position1 =new Point(this.getPositionCadran2().x+(int)getEpaisseur(),this.getPositionCadran2().y);
        Point Position2 =new Point(this.getPositionCadran2().x+(int)getEpaisseur(),this.getPositionCadran2().y+(int)this.getHauteur());
        Point Position3 =new Point(this.getPositionCadran2().x,this.getPositionCadran2().y+(int)(int)this.getHauteur());
       
        //construction du polygone de profil
        Polygon newPolygon=new Polygon();
        newPolygon.addPoint(this.getPositionCadran2().x, this.getPositionCadran2().y);
        newPolygon.addPoint(Position1.x, Position1.y);
        newPolygon.addPoint(Position2.x, Position2.y);
        newPolygon.addPoint(Position3.x, Position3.y);
        
        this.setPositionCadran1(Position1);
        this.setPositionCadran4(Position2);
        this.setPositionCadran3(Position3);
        //g.drawPolygon( polygonProfil);   
        return newPolygon;
    }
     */
    
}
