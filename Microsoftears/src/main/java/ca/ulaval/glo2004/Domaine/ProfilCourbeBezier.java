/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import ca.ulaval.glo2004.Domaine.Plan.Plan2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stell
 */
public class ProfilCourbeBezier extends Plan2D {  private static final ProfilCourbeBezier SINGLETON_INSTANCE = new ProfilCourbeBezier();
   public static ProfilCourbeBezier getInstance() {
      return SINGLETON_INSTANCE;
    }
   // points de controle de Bezier
    private Point pointInferieurGauche ;
    private Point pointSuperieurGauche ;
    private Point pointInferieurDroit ;
    private Point pointSuperieurDroit ; 
    private Point pointLePlushautDeLaCourbe;
    private int rayonPointDeControle=10;
    private double pasCalculPointsDessinProfil=0.01;
   
    List<Point> listePointsPolygone=new ArrayList();
    List<Double> listeT;
    private double hauteur;
    private double longueur;
     private Polygon ShapeProfilBezier; 
    private ProfilCourbeBezier() {
        super(Color.RED);
        this.listeT = new ArrayList();
        this.pointInferieurGauche = new Point();
        this.pointSuperieurGauche = new Point();
        this.pointInferieurDroit = new Point();
        this.pointSuperieurDroit = new Point();
        this.hauteur=0;
        this.longueur=0;
       
        this.pointLePlushautDeLaCourbe= new Point();
    }  

    public Polygon getShapeProfilBezier() {
        return ShapeProfilBezier;
    }

    public void setShapeProfilBezier(Polygon ShapeProfilBezier) {
        this.ShapeProfilBezier = ShapeProfilBezier;
    }
   
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
         majCoordonneesPoints();
        
    }

    public List<Point> getListePointsPolygone() {
        return listePointsPolygone;
    }

    public void setListePointsPolygone(List<Point> listePointsPolygone) {
        this.listePointsPolygone = listePointsPolygone;
    }

    public List<Double> getListeT() {
        return listeT;
    }

    public void setListeT(List<Double> listeT) {
        this.listeT = listeT;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
         majCoordonneesPoints();
    }  
   //position initiale du dessin 
    public void setInitialPointSuperieurGauche(Point _pointSuperieurGauche) {
        this.pointSuperieurGauche = _pointSuperieurGauche;
         majCoordonneesPoints();
        
    } 
  //position initiale du dessin
    public void setPointSuperieurGauche(Point _pointSuperieurGauche) {
        this.pointSuperieurGauche = _pointSuperieurGauche;
         
        
    }
      
    public void setPointInferieurGauche(Point pointInferieurGauche) {
        this.pointInferieurGauche.x = pointInferieurGauche.x;
        
    }
   

    public void setPointSuperieurDroit(Point pointSuperieurDroit) {
        this.pointSuperieurDroit = pointSuperieurDroit;
       
    } 

    public void setPointInferieurDroit(Point pointInferieurDroit) {
        this.pointInferieurDroit.x = pointInferieurDroit.x;
        
    }
   
    
    public void setPasCalculPointsDessinProfil(double pasCalculPointsDessinProfil) {
        this.pasCalculPointsDessinProfil = pasCalculPointsDessinProfil;
    }
    public Point getPointInferieurGauche() {
        return pointInferieurGauche;
    }

    public Point getPointInferieurDroit() {
        return pointInferieurDroit;
    }

    public Point getPointSuperieurGauche() {
        return pointSuperieurGauche;
    }

    public Point getPointSuperieurDroit() {
        return pointSuperieurDroit;
    }

    public int getRayonPointDeControle() {
        return rayonPointDeControle;
    }

    public void setRayonPointDeControle(int rayonPointDeControle) {
        this.rayonPointDeControle = rayonPointDeControle;
    }
  
    
   
     
    
    public void majCoordonneesPoints() {
        int hauteur=(int)this.hauteur;
        int longueur=(int)this.longueur;
        this.pointInferieurGauche=new Point(this.pointSuperieurGauche.x,this.pointSuperieurGauche.y+hauteur); 
        this.pointSuperieurDroit= new Point(this.pointSuperieurGauche.x+longueur,this.pointSuperieurGauche.y);
        this.pointInferieurDroit=new Point(this.pointSuperieurGauche.x+longueur,this.pointSuperieurGauche.y+hauteur);    
    }

    @Override
     public Polygon getShape() {   
       //if(!listePointsPolygone.isEmpty())listePointsPolygone.clear();
     //  listePointsPolygone.clear();
        int x,y,n=0;
        Polygon polygonProfil=new Polygon();
        polygonProfil.addPoint(pointInferieurGauche.x, pointInferieurGauche.y);
        for(double t=0;t<=1;t=t+pasCalculPointsDessinProfil){n++;
        x = (int)((1-t)*(1-t)*(1-t)*pointInferieurGauche.x + 3*(1-t)*(1-t)*t*pointSuperieurGauche.x + 3*(1-t)*t*t*pointSuperieurDroit.x + t*t*t*pointInferieurDroit.x);
        y = (int) ((1-t)*(1-t)*(1-t)*pointInferieurGauche.y + 3*(1-t)*(1-t)*t*pointSuperieurGauche.y + 3*(1-t)*t*t*pointSuperieurDroit.y + t*t*t*pointInferieurDroit.y);
       listePointsPolygone .add(new Point(x,y));
    //if(!listePointsPolygone.) listePointsPolygone.set(n, new Point(x,y));
   
       listeT.add(t);
        polygonProfil.addPoint(x,y);
       
        }
       polygonProfil.addPoint(pointInferieurDroit.x, pointInferieurDroit.y); 
       this.ShapeProfilBezier=polygonProfil;
        return polygonProfil;
    } 
}
     