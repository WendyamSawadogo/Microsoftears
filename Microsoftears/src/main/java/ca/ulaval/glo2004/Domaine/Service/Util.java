/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine.Service;

import ca.ulaval.glo2004.Domaine.MicroRoulotte;
import static ca.ulaval.glo2004.Domaine.Service.ProfilService.sqr;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;


/**
 *
 * @author Solution
 */
public class Util {
    
     /**
     * function to find if given point.
     * lies inside a given rectangle or not.
     *
     * @param x1 x of the first point of the rectangle.
     * @param y1 y of the first point of the rectangle.
     * @param x2 x of the last point of the rectangle.
     * @param y2 y of the last point of the rectangle
     * @param x  x of the point to find.
     * @param y  y of th point to find.
     * @return
     */
    public static boolean findPointInPolygone(double x1, double y1, double x2,
                                               double y2, double x, double y) {
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            return true;
        }

        return false;
    }
    
     public static ArrayList<Point> coordonneeTroisiemeCadran(Point PointInitAngleInferieurGauche , Point cenctreTroisiemeCadran , double pasAngle ){
    
        
        //calcul des 8 coordonnées polygone sans arc
        
        Point Pc3bas=new Point(cenctreTroisiemeCadran.x,PointInitAngleInferieurGauche.y);
        Point Pc3haut=new Point(PointInitAngleInferieurGauche.x,cenctreTroisiemeCadran.y);
        
        
        //calcul petitRayon et grandRayon de l"éllipse pour chaque coin
        
        double petitRayon3= Calculerdistance(cenctreTroisiemeCadran.x,cenctreTroisiemeCadran.y,Pc3bas.x,Pc3bas.y);
        double grandRayon3= Calculerdistance(cenctreTroisiemeCadran.x,cenctreTroisiemeCadran.y,Pc3haut.x,Pc3haut.y);
        
        
        //calcul des coordonées de la listepoints des arc 
       
        ArrayList listePointsArc3=Util.getPointEllipsis(270,180,cenctreTroisiemeCadran,petitRayon3,grandRayon3,pasAngle,1,-1);
        
        //construction du polygone de profil
       /*  Polygon polygonProfil=new Polygon();
       
        polygonProfil.addPoint(Pc3bas.x, Pc3bas.y);
        polygonProfil=AjouterListePointsAuPolygone(listePointsArc3, polygonProfil);
        polygonProfil.addPoint(Pc3haut.x, Pc3haut.y);
        //g.drawPolygon( polygonProfil);   */
        return listePointsArc3;
    }
    
    public static ArrayList<Point> coordonneeQuatriemeCadran(Point PointInitAngleInferieuDroit,  Point cenctreQuatriemeCadran,  double pasAngle ){
     
        
        //calcul des 8 coordonnées polygone sans arc
        
        Point Pc2bas=new Point(cenctreQuatriemeCadran.x,PointInitAngleInferieuDroit.y);
        Point Pc2haut=new Point(PointInitAngleInferieuDroit.x,cenctreQuatriemeCadran.y);
      
        //calcul petitRayon et grandRayon de l"éllipse pour chaque coin
       
        double grandRayon2= Calculerdistance(cenctreQuatriemeCadran.x,cenctreQuatriemeCadran.y,Pc2haut.x,Pc2haut.y);
        double petitRayon2= Calculerdistance(cenctreQuatriemeCadran.x,cenctreQuatriemeCadran.y,Pc2bas.x,Pc2bas.y);
        
        //calcul des coordonées de la listepoints des arc 
        ArrayList listePointsArc2=Util.getPointEllipsis(360,270,cenctreQuatriemeCadran,petitRayon2,grandRayon2,pasAngle,-1,-1);
      
        //construction du polygone de profil
        /* Polygon polygonProfil=new Polygon();
       
        polygonProfil.addPoint(Pc2haut.x, Pc2haut.y);
        polygonProfil=AjouterListePointsAuPolygone(listePointsArc2, polygonProfil);
        polygonProfil.addPoint(Pc2bas.x, Pc2bas.y);  */
      
        //g.drawPolygon( polygonProfil);   
        return listePointsArc2;
    
    }
    
    
     public static ArrayList<Point> coordonneePremierCadran( Point PointInitAngleSuperieurDroit,  Point cenctrePremierCadran  , double pasAngle ){
     
        
        //calcul des 8 coordonnées polygone sans arc
        Point Pc1bas=new Point(PointInitAngleSuperieurDroit.x,cenctrePremierCadran.y);
        Point Pc1haut=new Point(cenctrePremierCadran.x,PointInitAngleSuperieurDroit.y);
      
        
        //calcul petitRayon et grandRayon de l"éllipse pour chaque coin
        double petitRayon1= Calculerdistance(cenctrePremierCadran.x,cenctrePremierCadran.y,Pc1haut.x,Pc1haut.y);
        double grandRayon1= Calculerdistance(cenctrePremierCadran.x,cenctrePremierCadran.y,Pc1bas.x,Pc1bas.y); 
     
        
        ArrayList listePoints=new ArrayList();
        //calcul des coordonées de la listepoints des arc 
        ArrayList listePointsArc1=Util.getPointEllipsis(90,0,cenctrePremierCadran,petitRayon1,grandRayon1,pasAngle,-1,1);
       
        //construction du polygone de profil
      
        /* Polygon polygonProfil=new Polygon();
        polygonProfil.addPoint(Pc1haut.x, Pc1haut.y);
        polygonProfil=AjouterListePointsAuPolygone(listePointsArc1, polygonProfil);
        polygonProfil.addPoint(Pc1bas.x, Pc1bas.y);  */
       
        //g.drawPolygon( polygonProfil);   
        return listePointsArc1;
    }
  
     public static ArrayList<Point> coordonneeDeuxiemeCadran(Point PointInitAngleSuperieurGauche ,Point cenctreDeuxiemeCadran , double pasAngle ){
   
        //calcul des 8 coordonnées polygone sans arc
        Point Pc0bas_Sup_Horizontal_Gauche=new Point(PointInitAngleSuperieurGauche.x,cenctreDeuxiemeCadran.y);
        Point Pc0haut_Sup_Vertial_Gauche=new Point(cenctreDeuxiemeCadran.x,PointInitAngleSuperieurGauche.y);
       
        //calcul petitRayon et grandRayon de l"éllipse pour chaque coin
        double petitRayon0= Calculerdistance(cenctreDeuxiemeCadran.x,cenctreDeuxiemeCadran.y,Pc0haut_Sup_Vertial_Gauche.x,Pc0haut_Sup_Vertial_Gauche.y);
        double grandRayon0= Calculerdistance(cenctreDeuxiemeCadran.x,cenctreDeuxiemeCadran.y,Pc0bas_Sup_Horizontal_Gauche.x,Pc0bas_Sup_Horizontal_Gauche.y);
        
        //calcul des coordonées de la listepoints des arc 
        ArrayList listePointsArc0= Util.getPointEllipsis( 180,90,cenctreDeuxiemeCadran,petitRayon0,grandRayon0,pasAngle,1,1);
     
        //construction du polygone de profil
       /* Polygon polygonProfil=new Polygon();
        polygonProfil.addPoint(Pc0bas_Sup_Horizontal_Gauche.x, Pc0bas_Sup_Horizontal_Gauche.y);
        polygonProfil=AjouterListePointsAuPolygone(listePointsArc0, polygonProfil);
        polygonProfil.addPoint(Pc0haut_Sup_Vertial_Gauche.x, Pc0haut_Sup_Vertial_Gauche.y);
    */
        //g.drawPolygon( polygonProfil);   
        return listePointsArc0;
    }
     
    public static  ArrayList<Point> getPointEllipsis(double angleDebut,double angleFin,Point centre,double petitRayon,double grandRayon,double pas, int a, int b) { 
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

     public static  double Calculerdistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(sqr(y2 - y1) + sqr(x2 - x1));
    }

    public static Point getCoordoneeReel(Point mouse, double zoom) {
        Point newPoint = mouse; 
        if(zoom>=1) newPoint= new Point( (int)(mouse.x*zoom), (int)(mouse.y*zoom)) ;
        
        else newPoint= new Point( (int)(mouse.x/zoom), (int)(mouse.y/zoom)) ;
         return newPoint;
    }
    
      public static double StringToDouble(String text){

        text = text.trim();
         double inputValeurDecimal = 0.0;

        double partieEntierString = 0.0;
        double partieFractionnaire = 0.0;
        
        double numerateur;
        double denominateur;

        if (text.indexOf("/") != -1) {
            
            String[] input = text.split(" ");
            if (input.length > 1) {
                partieEntierString = Double.parseDouble(input[0].replace(",", "."));
                numerateur = Double.parseDouble("0" + input[1].split("/")[0].replace(" ", "").replace(",", ".").toString());
           
                denominateur =Double.parseDouble(input[1].split("/")[1].replace(" ", "").replace(",", ".").toString());
                partieFractionnaire = numerateur == 0?1:numerateur/denominateur ;
            } else {
                partieEntierString = 0.0;
                numerateur = Double.parseDouble("0" + text.split("/")[0].replace(",", ".").toString());
                denominateur = Double.parseDouble(text.split("/")[1].replace(",", ".").toString());
                
                partieFractionnaire = numerateur == 0?1:numerateur/denominateur ;
            }
            inputValeurDecimal = partieEntierString + partieFractionnaire;
        } else {

            inputValeurDecimal = Double.parseDouble(text.replace(" ", "").replace(",", "."));
        }
        
        return inputValeurDecimal;
    
    }
       public static Path  Serialize( MicroRoulotte  microRoulotte)  {
       
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long dd = timestamp.getTime();
        String tmpdir = System.getProperty("java.io.tmpdir");
        Path path = Paths.get( tmpdir, "MicroRoulotte");
       
       if (! path.toFile().exists()) path.toFile().mkdir();
        
        Path filename =Paths.get( path.toString(),dd+ "_MicroRoulotte.data");
          
        // Serialization 
        try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename.toString());
            ObjectOutputStream out = new ObjectOutputStream(file);
              
            // Method for serialization of object
            out.writeObject(microRoulotte);
              
            out.close();
            file.close();
              
            System.out.println("Object has been serialized");
  
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
  
        return filename;
      }
      
      
       public static MicroRoulotte Deserialize( Path filename  ) throws FileNotFoundException, IOException, ClassNotFoundException  {
       
          // MicroRoulotte  microRoulotte = MicroRoulotte.getInstance();
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename.toString());
            ObjectInputStream in = new ObjectInputStream(file);
            Object myObject = in.readObject();
           
            if( !myObject.getClass().equals(MicroRoulotte.class)) return null;
            // Method for deserialization of object
            MicroRoulotte  microRoulotte  = (MicroRoulotte)myObject;
              
            in.close();
            file.close();
              
          return microRoulotte;
       
      }
      
}
