/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import ca.ulaval.glo2004.Domaine.Plan.Plan2D;
import ca.ulaval.glo2004.Domaine.Service.ProfilService;
import ca.ulaval.glo2004.Domaine.Service.Util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class Profil extends Plan2D  implements Serializable{
    
   private static final Profil SINGLETON_INSTANCE = new Profil();
   public static Profil getInstance() {
      return SINGLETON_INSTANCE;
    }
   private Profil(){super(Color.RED);
   centreRayonInferieurGauche = new Point();
   centreRayonInferieurDroite = new Point();
   centreRayonSuperieurGauche = new Point();
   centreRayonSuperieurDroite = new Point();
   };
   
   // centre Rayon des Arc
    private Point centreRayonInferieurGauche ;
    private Point centreRayonInferieurDroite ;
    private Point centreRayonSuperieurGauche ;
    private Point centreRayonSuperieurDroite ;
   
  
   

    /**
     * Set the value of positionInitiale
     *
     * @param positionInitiale new value of positionInitiale
     */
   @Override
    public void setPositionCadran2(Point positionInitiale) {
        super.setPositionCadran2(positionInitiale) ;
        this.resetPosition();
    }

    public Point getPointInferieurHorizontalGauche() {
        return new Point(this.centreRayonInferieurGauche.x, this.getPositionCadran2().y+(int)this.getHauteur());
    }

   
    public Point getPointInferieurHorizontalDroite() {
        return new Point(this.centreRayonInferieurDroite.x, this.getPositionCadran2().y+(int)this.getHauteur());
    }


    public Point getPointInferieurVerticalGauche() {
        return new Point(this.getPositionCadran2().x, this.centreRayonInferieurGauche.y);
    }

 
    public Point getPointInferieurVerticalDroite() {
        return  new Point(this.getPositionCadran2().x+(int)this.getLongueur(), this.centreRayonInferieurDroite.y);
    }

  

    public Point getPointSuperieurHorizontalGauche() {
        return new Point(this.centreRayonSuperieurGauche.x , this.getPositionCadran2().y);
    }

  

    public Point getPointSuperieurHorizontalDroite() {
        return new Point(this.centreRayonSuperieurDroite.x , this.getPositionCadran2().y);
    }

  

    public Point getPointSuperieurVerticalGauche() {
        return new Point(this.getPositionCadran2().x, this.centreRayonSuperieurGauche.y);
    }

    

    public Point getPointSuperieurVerticalDroite() {
        return new Point(this.getPositionCadran2().x+(int)this.getLongueur(), this.centreRayonSuperieurDroite.y);
    }

    
   

    public Point getCentreRayonInferieurDroite() {
        return centreRayonInferieurDroite;
    }

   

    public Point getCentreRayonSuperieurGauche() {
        return centreRayonSuperieurGauche;
    }

    

    public Point getCentreRayonSuperieurDroite() {
        return centreRayonSuperieurDroite;
    }

   
     public Point getCentreRayonInferieurGauche() {
        return centreRayonInferieurGauche;
    }
     public void setCentreRayonInferieurDroite(Point pointInferieurDroite) {
      
        Point Position2 =new Point(this.getPositionCadran2().x+(int)getLongueur(),this.getPositionCadran2().y+(int)getHauteur());
        if(Position2.equals( pointInferieurDroite )||getShapePanneau().contains(pointInferieurDroite)){ 
         
           this.centreRayonInferieurDroite =new Point( (int)Math.ceil(pointInferieurDroite.x),(int)Math.ceil(pointInferieurDroite.y)) ;
       }
    }
    public void setCentreRayonSuperieurGauche(Point pointSuperieuGauche) {
      
        
        if(this.getPositionCadran2().x == pointSuperieuGauche.x  && this.getPositionCadran2().y == pointSuperieuGauche.y ||getShapePanneau().contains(pointSuperieuGauche)){ 
         
           this.centreRayonSuperieurGauche =new Point( (int)Math.ceil(pointSuperieuGauche.x),(int)Math.ceil(pointSuperieuGauche.y)) ;
       }
    }
    public void setCentreRayonSuperieurDroite(Point pointSuperieurDroite) {
        
        Point Position1 =new Point(this.getPositionCadran2().x+(int)getLongueur(),this.getPositionCadran2().y);
      
         if(Position1.equals(pointSuperieurDroite) ||getShapePanneau().contains(pointSuperieurDroite)){ 
           this.centreRayonSuperieurDroite =new Point( (int)Math.ceil(pointSuperieurDroite.x),(int)Math.ceil(pointSuperieurDroite.y)) ;
       }
        
    }
     public void setCentreRayonInferieurGauche(Point pointInferieurGauche) {
       
       Point Position3 =new Point(this.getPositionCadran2().x,this.getPositionCadran2().y+(int)getHauteur());
       if(Position3.equals(pointInferieurGauche ) ||getShapePanneau().contains(pointInferieurGauche)){ 
           this.centreRayonInferieurGauche =new Point( (int)Math.ceil(pointInferieurGauche.x),(int)Math.ceil(pointInferieurGauche.y)) ;
       }
       
    }
   @Override
   public void setHauteur(double hauteur){
        super.setHauteur(hauteur);
        this.resetPosition();
        this.setCentreRayonSuperieurGauche(this.getPositionCadran2());
        this.setCentreRayonSuperieurDroite(this.getPositionCadran1());
        this.setCentreRayonInferieurDroite(this.getPositionCadran4());
        this.setCentreRayonInferieurGauche(this.getPositionCadran3());
       
    }
   @Override
    public void setLongueur(double longueur){
        super.setLongueur(longueur);
        this.resetPosition();
        this.setCentreRayonSuperieurGauche(this.getPositionCadran2());
        this.setCentreRayonSuperieurDroite(this.getPositionCadran1());
        this.setCentreRayonInferieurDroite(this.getPositionCadran4());
        this.setCentreRayonInferieurGauche(this.getPositionCadran3());
    }
    
    @Override
    public Polygon getShape() {
       Polygon polygonProfil = new Polygon();
      
       Point PCR0=getCentreRayonSuperieurGauche();//donné
       Point PCR1=getCentreRayonSuperieurDroite();//donné
       Point PCR2=getCentreRayonInferieurDroite();//donné
       Point PCR3=getCentreRayonInferieurGauche();//donné
    
        Point Position0 = this.getPositionCadran2();
        Point Position1 = this.getPositionCadran1();
        Point Position2 = this.getPositionCadran4();
        Point Position3 = this.getPositionCadran3();
     
       // Second Cadran. 
       // premier point
        polygonProfil.addPoint((int)(this.getPointSuperieurVerticalGauche().x), (int)(this.getPointSuperieurVerticalGauche().y));

        // autre point
        for(Point point :Util.coordonneeDeuxiemeCadran(this.getPositionCadran2(),this.getCentreRayonSuperieurGauche(),0.0025 ))
        polygonProfil.addPoint((int)(point.x), (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(this.getPointSuperieurHorizontalGauche().x), (int)(this.getPointSuperieurHorizontalGauche().y));
      
      
      // First Cadran.    
       // premier point
        polygonProfil.addPoint((int)(this.getPointSuperieurHorizontalDroite().x), (int)(this.getPointSuperieurHorizontalDroite().y));

        // autre point
        for(Point point :Util.coordonneePremierCadran(Position1,this.getCentreRayonSuperieurDroite(),0.0025 ))
        polygonProfil.addPoint((int)(point.x), (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(this.getPointSuperieurVerticalDroite().x), (int)(this.getPointSuperieurVerticalDroite().y));
        
      
       //Fouth Cadran
        // premier point
        polygonProfil.addPoint((int)(this.getPointInferieurVerticalDroite().x), (int)(this.getPointInferieurVerticalDroite().y));

        // autre point
        for(Point point :Util.coordonneeQuatriemeCadran(Position2,this.getCentreRayonInferieurDroite(),0.0025 ))
        polygonProfil.addPoint((int)(point.x), (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(this.getPointInferieurHorizontalDroite().x), (int)(this.getPointInferieurHorizontalDroite().y)); 
  
      //third Cadran.
      // premier point
        polygonProfil.addPoint((int)(this.getPointInferieurHorizontalGauche().x), (int)(this.getPointInferieurHorizontalGauche().y));

        // autre point
        for(Point point :Util.coordonneeTroisiemeCadran(Position3,this.getCentreRayonInferieurGauche(),0.0025 ))
        polygonProfil.addPoint((int)(point.x), (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(this.getPointInferieurVerticalGauche().x), (int)(this.getPointInferieurVerticalGauche().y));
      
        return polygonProfil;
        
    }

   
    
     public Polygon getShapePanneau() {
        
        //construction du polygone de profil
        Polygon newPolygon=new Polygon();
        newPolygon.addPoint(this.getPositionCadran2().x, this.getPositionCadran2().y);
        newPolygon.addPoint(this.getPositionCadran1().x, this.getPositionCadran1().y);
        newPolygon.addPoint(this.getPositionCadran4().x, this.getPositionCadran4().y);
        newPolygon.addPoint(this.getPositionCadran3().x, this.getPositionCadran3().y);
        
       
        
        //g.drawPolygon( polygonProfil);   
        return newPolygon;
    }
     
   @SuppressWarnings("empty-statement")
     public void updatePointControl(Point mousePoint) {
        
        //construction du polygone de profil
        Rectangle newRectangle=new Rectangle(this.getPositionCadran2(), new Dimension((int)getLongueur(), (int)getHauteur()));
        
        if( newRectangle.contains(mousePoint)){
        
          double distance0 = Math.hypot(mousePoint.x-this.getPositionCadran2().x, mousePoint.y-this.getPositionCadran2().y);
          double distance1 = Math.hypot(mousePoint.x-this.getPositionCadran1().x, mousePoint.y-this.getPositionCadran1().y);
          double distance2 = Math.hypot(mousePoint.x-this.getPositionCadran4().x, mousePoint.y-this.getPositionCadran4().y);
          double distance3 = Math.hypot(mousePoint.x-this.getPositionCadran3().x, mousePoint.y-this.getPositionCadran3().y);
          
          //
          Point newPoint =  new Point((int)(mousePoint.x) , (int)(mousePoint.y));
          
          double minValeur = distance0;
          if(distance0== distance1 && distance0==distance2 && distance0==distance3){
             setCentreRayonSuperieurDroite(mousePoint);
             setCentreRayonSuperieurGauche(mousePoint);
             setCentreRayonInferieurGauche(mousePoint);
             setCentreRayonInferieurDroite(mousePoint);
          }
          else   if(distance0== distance1 ){
            setCentreRayonSuperieurDroite(mousePoint);
             setCentreRayonSuperieurGauche(mousePoint);
          
          }
            else   if( distance0==distance3){
          
             setCentreRayonSuperieurGauche(mousePoint);
             setCentreRayonInferieurGauche(mousePoint);
             
          }else   if(distance1== distance2 ){
             setCentreRayonSuperieurDroite(mousePoint);
             setCentreRayonInferieurDroite(mousePoint);
          }else   if(distance2== distance3 ){
             setCentreRayonInferieurGauche(mousePoint);
             setCentreRayonInferieurDroite(mousePoint);
          }else
          {
                    if(minValeur > distance1 )
                        minValeur = distance1;
                    if(minValeur > distance2 )
                        minValeur = distance2;
                    if(minValeur > distance3 )
                        minValeur = distance3;

                    // update valeur;
                    if(minValeur ==  distance0 )
                        this.setCentreRayonSuperieurGauche(mousePoint);
                    else if(minValeur == distance1 )
                        this.setCentreRayonSuperieurDroite(mousePoint);
                    else if(minValeur == distance2 )
                       this.setCentreRayonInferieurDroite(mousePoint);
                     else if(minValeur== distance3 )
                        this.setCentreRayonInferieurGauche(mousePoint);
          }
          
        }
       
    }
        
}
