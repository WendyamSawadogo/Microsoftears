/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import ca.ulaval.glo2004.Domaine.Plan.Couleur;
import ca.ulaval.glo2004.Domaine.Plan.Plan2D;
import ca.ulaval.glo2004.Domaine.Service.Util;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;


/**
 *
 * @author Solution
 */
public class Ouverture extends Plan2D  implements Serializable{
    
    
    private double RayonArcCote;
    
    protected Ouverture(double largeur, double hauteur, Point position, double RayonArcCote, Color couleur) {
         super(position,largeur, hauteur, couleur); 
        this.RayonArcCote = RayonArcCote;
      
    }

    protected Ouverture(){
         super(new Point(), 0,0,Color.BLACK); 
       
        this.RayonArcCote = RayonArcCote;
       
    }
        
    
    /**
     * Get the value of RayonArcCote
     *
     * @return the value of RayonArcCote
     */
    protected double getRayonArcCote() {
        return RayonArcCote;
    }

    /**
     * Set the value of RayonArcCote
     *
     * @param RayonArcCote new value of RayonArcCote
     */
    protected void setRayonArcCote(double RayonArcCote) {
        this.RayonArcCote = RayonArcCote;
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
    

}
