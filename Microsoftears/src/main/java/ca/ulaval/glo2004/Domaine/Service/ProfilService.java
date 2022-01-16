
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine.Service;

import ca.ulaval.glo2004.Domaine.MicroRoulotteControleur;
import ca.ulaval.glo2004.Domaine.Profil;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;





/**
 *
 * @author Solution
 */
public class ProfilService {
    
   
    public static Polygon coordonneePolygon( Point PointInit , double  longueurPixel, double hauteurPixel ,Point Pc0 ,  Point Pc1 , Point Pc2, Point Pc3 , double pasAngle ){
     int longueur=(int)longueurPixel;//donné
        int hauteur=(int)hauteurPixel;//donné
       Point P0=PointInit;//donné
        //dessin des point cliqué
        /* g.fillOval(Pc0.x, Pc0.y,4, 4);
        g.fillOval(Pc1.x, Pc1.y,4, 4);
        g.fillOval(Pc2.x, Pc2.y,4, 4);
        g.fillOval(Pc3.x, Pc3.y,4, 4); */
        
          //calcul des cordonnées du panneau de base PointInitAngleSuperieurGauche,PointInitAngleSuperieurDroit,PointInitAngleInferieuDroit,PointInitAngleInferieurGauche
        Point P1=new Point(P0.x+longueur,P0.y);
        Point P2=new Point(P0.x+longueur,P0.y+hauteur);
        Point P3=new Point(P0.x,P0.y+hauteur);
       
        //dessin des coins de base
       /*  g.fillOval(PointInitAngleSuperieurGauche.x, PointInitAngleSuperieurGauche.y,4, 4);
         g.fillOval(PointInitAngleSuperieurDroit.x, PointInitAngleSuperieurDroit.y,4, 4);
         g.fillOval(PointInitAngleInferieuDroit.x, PointInitAngleInferieuDroit.y,4, 4);
         g.fillOval(PointInitAngleInferieurGauche.x, PointInitAngleInferieurGauche.y,4, 4); */
        
        //calcul des 8 coordonnées polygone sans arc
        
        Point Pc0bas=new Point(P0.x,Pc0.y);
        Point Pc0haut=new Point(Pc0.x,P0.y);
        Point Pc1bas=new Point(P1.x,Pc1.y);
        Point Pc1haut=new Point(Pc1.x,P1.y);
        Point Pc2bas=new Point(Pc2.x,P2.y);
        Point Pc2haut=new Point(P2.x,Pc2.y);
        Point Pc3bas=new Point(Pc3.x,P3.y);
        Point Pc3haut=new Point(P3.x,Pc3.y);
        
        
        //calcul petitRayon et grandRayon de l"éllipse pour chaque coin
        double petitRayon0= distance(Pc0.x,Pc0.y,Pc0haut.x,Pc0haut.y);
        double grandRayon0= distance(Pc0.x,Pc0.y,Pc0bas.x,Pc0bas.y);
        double petitRayon1= distance(Pc1.x,Pc1.y,Pc1haut.x,Pc1haut.y);
        double grandRayon1= distance(Pc1.x,Pc1.y,Pc1bas.x,Pc1bas.y); 
        double grandRayon2= distance(Pc2.x,Pc2.y,Pc2haut.x,Pc2haut.y);
        double petitRayon2= distance(Pc2.x,Pc2.y,Pc2bas.x,Pc2bas.y);
        double petitRayon3= distance(Pc3.x,Pc3.y,Pc3bas.x,Pc3bas.y);
        double grandRayon3= distance(Pc3.x,Pc3.y,Pc3haut.x,Pc3haut.y);
        
        
        //calcul des coordonées de la listepoints des arc 
        ArrayList listePointsArc0=retournerPointsArc(180,90,Pc0,petitRayon0,grandRayon0,pasAngle,1,1);
        ArrayList listePointsArc1=retournerPointsArc(90,0,Pc1,petitRayon1,grandRayon1,pasAngle,-1,1);
        ArrayList listePointsArc2=retournerPointsArc(360,270,Pc2,petitRayon2,grandRayon2,pasAngle,-1,-1);
        ArrayList listePointsArc3=retournerPointsArc(270,180,Pc3,petitRayon3,grandRayon3,pasAngle,1,-1);
        
        //construction du polygone de profil
        Polygon polygonProfil=new Polygon();
        polygonProfil.addPoint(Pc0bas.x, Pc0bas.y);
        polygonProfil=AjouterListePointsAuPolygone(listePointsArc0, polygonProfil);
        polygonProfil.addPoint(Pc0haut.x, Pc0haut.y);
        polygonProfil.addPoint(Pc1haut.x, Pc1haut.y);
        polygonProfil=AjouterListePointsAuPolygone(listePointsArc1, polygonProfil);
        polygonProfil.addPoint(Pc1bas.x, Pc1bas.y);
        polygonProfil.addPoint(Pc2haut.x, Pc2haut.y);
        polygonProfil=AjouterListePointsAuPolygone(listePointsArc2, polygonProfil);
        polygonProfil.addPoint(Pc2bas.x, Pc2bas.y);
        polygonProfil.addPoint(Pc3bas.x, Pc3bas.y);
        polygonProfil=AjouterListePointsAuPolygone(listePointsArc3, polygonProfil);
        polygonProfil.addPoint(Pc3haut.x, Pc3haut.y);
        //g.drawPolygon( polygonProfil);   
        return polygonProfil;
    }
    static public double sqr(double a) {
        return a*a;
    }
    
    public static  double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(sqr(y2 - y1) + sqr(x2 - x1));
    }
     static public Polygon  AjouterListePointsAuPolygone(ArrayList<Point> listePoints, Polygon polygone) {
         for(int i=0;i<listePoints.size();i++)polygone.addPoint(listePoints.get(i).x, listePoints.get(i).y);     
        return polygone;
    }
    
    static public ArrayList<Point> retournerPointsArc(double angleDebut,double angleFin,Point centre,double petitRayon,double grandRayon,double pas, int a, int b) { 
    ArrayList listePoints=new ArrayList();
    int k=0;
    for (double i=(angleDebut-pas);i>angleFin;i=i-pas){
        int x=(int)(centre.x-a*Math.abs((grandRayon)*Math.cos(Math.toRadians(i))));
        int y=(int)(centre.y-b*(int)Math.abs((petitRayon)*Math.sin(Math.toRadians(i))));
        listePoints.add(k,new Point(x,y));
        k++;
    } 
    return listePoints;
    }
}
