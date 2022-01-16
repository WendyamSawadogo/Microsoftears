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

/**
 *
 * @author Solution
 */
public class AideDessin extends Plan2D  implements Serializable{
   
    private int gratuationX;

    private int gratuationY;

   
    private static final AideDessin SINGLETON_INSTANCE = new AideDessin();
    public static AideDessin getInstance() {
      return SINGLETON_INSTANCE;
    }

  
    private AideDessin() {
         super(new Point(), 0,0,Color.BLACK);
        this.gratuationX = 30;
        this.gratuationY = 30;
        this.setTransparence(100);
        
    }
   
    
    /**
     * Get the value of gratuationY
     *
     * @return the value of gratuationY
     */
    public double getGratuationY() {
        return gratuationY;
    }

    public void setGratuationX(int gratuationX) {
        this.gratuationX = gratuationX;
    }

    public void setGratuationY(int gratuationY) {
        this.gratuationY = gratuationY;
    }

    

    /**
     * Get the value of gratuationX
     *
     * @return the value of gratuationX
     */
    public double getGratuationX() {
        return gratuationX;
    }

    /**
     * Get the value of largeur
     *
     * @return the value of largeur
     */
    public double getLargeur() {
        return super.getLongueur();
    }

    /**
     * Set the value of largeur
     *
     * @param largeur new value of largeur
     */
    public void setLargeur(double largeur) {
        super.setLongueur(largeur);
    }

     @Override
    public Polygon getShape() {
        
      
        
        Point Position1 =new Point(this.getPositionCadran2().x+(int)getLongueur(),this.getPositionCadran2().y);
        Point Position2 =new Point(this.getPositionCadran2().x+(int)getLongueur(),this.getPositionCadran2().y+(int)getHauteur());
        Point Position3 =new Point(this.getPositionCadran2().x,this.getPositionCadran2().y+(int)getHauteur());
       
        //construction du polygone de profil
        Polygon newPolygon=new Polygon();
        newPolygon.addPoint(this.getPositionCadran2().x, this.getPositionCadran2().y);
        newPolygon.addPoint(Position1.x, Position1.y);
        newPolygon.addPoint(Position2.x, Position2.y);
        newPolygon.addPoint(Position3.x, Position3.y);
        
        //g.drawPolygon( polygonProfil);   
        return newPolygon;
    }
     @Override
     public  Boolean Exits(Point pointReel){
        return getShape().contains(pointReel);
      }

    
}
