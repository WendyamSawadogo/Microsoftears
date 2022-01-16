/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class Fenetre extends Ouverture  implements Serializable{
   private int largeur;
   private int hauteur;
   Point positionCoinInferieurGaucheFenetre;
   private Polygon ShapePolygonFenetre;
   private Point position;
   //private Color couleurfenetre=Color.GREEN;
   private boolean select=false;
   private static final Fenetre SINGLETON_INSTANCE = new Fenetre();
   public static Fenetre getInstance() {
      return SINGLETON_INSTANCE;
    }

    private Fenetre() {
       super();
       this.hauteur=0;
       this.largeur=0;
       this.positionCoinInferieurGaucheFenetre=new Point();
    }

    public Polygon getShapePolygonFenetre() {
        return ShapePolygonFenetre;
    }

    public void setShapePolygonFenetre(Polygon ShapePolygonFenetre) {
        this.ShapePolygonFenetre = ShapePolygonFenetre;
    }

    public Point getPosition() {
        return position;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public Point getPositionCoinInferieurGaucheFenetre() {
        return positionCoinInferieurGaucheFenetre;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
/*
    public Color getCouleurfenetre() {
        return couleurfenetre;
    }

    public void setCouleurfenetre(Color couleurfenetre) {
        this.couleurfenetre = couleurfenetre;
    }*/

    public double getLargeur() {
        return largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setPositionCoinInferieurGaucheFenetre(Point positionCoinInferieurGaucheFenetre) {
        this.positionCoinInferieurGaucheFenetre = positionCoinInferieurGaucheFenetre;
    }

    
    
    @Override
     public Polygon getShape() { 
           Polygon poly=new Polygon();
            poly.addPoint(positionCoinInferieurGaucheFenetre.x,positionCoinInferieurGaucheFenetre.y);
            poly.addPoint(positionCoinInferieurGaucheFenetre.x+largeur,positionCoinInferieurGaucheFenetre.y);
            poly.addPoint(positionCoinInferieurGaucheFenetre.x+largeur,positionCoinInferieurGaucheFenetre.y+hauteur);
            poly.addPoint(positionCoinInferieurGaucheFenetre.x,positionCoinInferieurGaucheFenetre.y+hauteur);        
           //deplacer si nouvelle position de depacement transmise
            if ((position.x!=0)&&(position.y!=0))poly.translate(position.x, position.y);
            position.x=0;
            position.y=0;
            this.ShapePolygonFenetre=poly;
            return poly; 
                  
     }
}
