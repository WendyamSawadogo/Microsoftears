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
public class Plancher extends Plan2D  implements Serializable{
    
    double largueur=0.0;
    double epaisseurPlancher=0.0;
    double margeArriere=0.0;
    double margeAvant=0.0;
    Polygon shapeDuPlancher;
    Point positionCoinInfGauchePlancher=new Point();
    Color couleurPlancher=Color.GREEN;
   private static final Plancher SINGLETON_INSTANCE = new Plancher();
   private Plancher ShapePlancher;
   public static Plancher getInstance() {
      return SINGLETON_INSTANCE;
    }

   
    private Plancher(){
        
        super(new Point(0,0), 0, 0, Color.BLUE);
        largueur=0.0;
        margeArriere=0.0;
        margeAvant=0.0;
        epaisseurPlancher=0.0;
    }
   
    public double getLargueur() {
        return largueur;
    }

    public void setEpaisseurPlancher(double epaisseurPlancher) {
        this.epaisseurPlancher = epaisseurPlancher;
    }

    public Point getPositionCoinInfGauchePlancher() {
        return positionCoinInfGauchePlancher;
    }

    public void setPositionCoinInfGauchePlancher(Point positionCoinInfGauchePlancher) {
        this.positionCoinInfGauchePlancher = positionCoinInfGauchePlancher;
    }

    

    public double getEpaisseur() {
        return epaisseurPlancher;
    }

    public void setEpaisseur1(double epaisseur) {
        this.epaisseurPlancher = epaisseur;
    }

    public Polygon getShapeDuPlancher() {
        return shapeDuPlancher;
    }

    public void setShapeDuPlancher(Polygon shapeDuPlancher) {
        this.shapeDuPlancher = shapeDuPlancher;
    }

    public Color getCouleurPlancher() {
        return couleurPlancher;
    }

    public void setCouleurPlancher(Color couleurPlancher) {
        this.couleurPlancher = couleurPlancher;
    }

    
    public void setLargueur(double largueur) {
        this.largueur = largueur;
    }

    public double getMargeArriere() {
        return margeArriere;
    }

    public void setMargeArriere(double margeArriere) {
      
        this.margeArriere = margeArriere;
        
    }

    public double getMargeAvant() {
        return margeAvant;
       
    }
  
    
    public void setMargeAvant(double margeAvant)  {
      
        this.margeAvant = margeAvant;
       
        
    }
    
  
    
    @Override
    public void resetPosition(){
        try
        {
        Point positionCadran2 =new Point(this.getPositionCadran3().x, this.getPositionCadran3().y-(int)this.getHauteur());
        Point positionCadran1 =new Point(this.getPositionCadran3().x+(int)(this.getLongueur()-this.getMargeArriere()),this.getPositionCadran3().y-(int)this.getHauteur());
        Point positionCadran4 =new Point(this.getPositionCadran3().x+(int)(this.getLongueur()-this.getMargeArriere()),this.getPositionCadran3().y);
       super.setPositionCadran1(positionCadran1);
       super.setPositionCadran2(positionCadran2);
       super.setPositionCadran4(positionCadran4);
        }catch(Exception ex){
       System.out.println(ex);
        }
     
    }
    
     public Polygon getShapePlancher() { 
           Polygon poly=new Polygon();
            int e=(int)this.epaisseurPlancher;
            int l=(int)this.largueur;
            
            poly.addPoint(positionCoinInfGauchePlancher.x,positionCoinInfGauchePlancher.y);
            poly.addPoint(positionCoinInfGauchePlancher.x+l,positionCoinInfGauchePlancher.y);
            poly.addPoint(positionCoinInfGauchePlancher.x+l,positionCoinInfGauchePlancher.y+e);
            poly.addPoint(positionCoinInfGauchePlancher.x,positionCoinInfGauchePlancher.y+e);
           //deplacer si nouvelle position de depacement transmise
           /* if ((position.x!=0)&&(position.y!=0))poly.translate(position.x, position.y);
            position.x=0;
            position.y=0;
            this.ShapePolygonFenetre=poly;*/
            return poly; 
                  
     }
    
   /*
    @Override
    public Polygon getShape() {
        
        Point PositionInit =new Point( this.getPositionCadran2().x+(int)margeAvant, this.getPositionCadran2().y);
      
        Point Position1 =new Point(PositionInit.x,PositionInit.y-(int)this.getHauteur());
        Point Position2 =new Point(PositionInit.x+(int)getLongueur()-(int)this.margeArriere-(int)margeAvant ,Position1.y);
        Point Position3 =new Point(Position2.x,this.getPositionCadran2().y);
       
        //construction du polygone de profil
        Polygon newPolygon=new Polygon();
        newPolygon.addPoint(PositionInit.x, PositionInit.y);
        newPolygon.addPoint(Position1.x, Position1.y);
        newPolygon.addPoint(Position2.x, Position2.y);
        newPolygon.addPoint(Position3.x, Position3.y);
        
        //g.drawPolygon( polygonProfil);   
        return newPolygon;
    }
*/

    void setEpaisseur(double epaisseur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
