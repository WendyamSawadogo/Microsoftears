/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine.drawing;

import ca.ulaval.glo2004.Domaine.MicroRoulotteControleur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Solution
 */
public class MicroRoulotteDrawer {
  private final MicroRoulotteControleur controlleur;

  public MicroRoulotteDrawer(MicroRoulotteControleur controller) 
  {
    this.controlleur = controller;
   
  }
 
  public void drawMicroRoulotte(Graphics g) 
  {
    if((this.controlleur.isGrilleAffiche()==true))
         { drawAideDessin(g);
         }
     if((this.controlleur.isEnModeDessinBezier()==true))
         { drawProfilBezier(g);
         if((this.controlleur.isEnModeAffichage()==true))
         { drawHayonBezier( g) ;
         drawPoutreArriereBezier(g);
         plancherbezier(g);
         drawToitBezier(g);
         drawMurSeparateurBezier( g);
           drawOuverturePorte(g);
           drawOuvertureFenetre(g); 
         
         }
         
         }
     if(this.controlleur.isEnModeDessinPanneau()==true)
            {drawProfil(g); 
             drawPlancher(g);
            drawPoutreArriere(g);
            drawHayon(g);
            drawMurSeparateur(g);
            drawToit(g);

            drawMurCoucheInterieur(g);
            drawMurCoucheSequelette(g);
            drawMurCoucheExterieur(g);
         
         }  
     
      
 
   
   
   
   
  }
 
 

    private void drawPlancher(Graphics g) {
         g.setColor(controlleur.getMicroRoulotte().getPlancher().getCouleur());     
         g.fillPolygon(controlleur.getMicroRoulotte().getShapePlancher());
       /*  if((controlleur.isEnModeAffichage()==true)&&(controlleur.isEnModeDessinBezier()==true)){
             g.setColor(controlleur.getMicroRoulotte().getPlancher().getCouleurPlancher());
             g.drawPolygon(controlleur.getMicroRoulotte().getPlancher().getShapeDuPlancher());
         }*/
    }

    private void drawPoutreArriere(Graphics g) {
        
         g.setColor(controlleur.getMicroRoulotte().getPoutreArriere().getCouleur());
         g.fillPolygon(controlleur.getMicroRoulotte().getShapePoutreArriere());
   }
    
private void drawPoutreArriereBezier(Graphics g) {
        
         g.setColor(Color.YELLOW);
         g.fillPolygon(controlleur.getMicroRoulotte().getShapePoutreInitialProfilBezier());
   }
private void drawToitBezier(Graphics g) {
        
         g.setColor(Color.CYAN);
         g.fillPolygon(controlleur.getMicroRoulotte().getShapeToitInitialProfilBezier());
   }
    private void drawHayon(Graphics g) {
           
         g.setColor(controlleur.getMicroRoulotte().getHayon().getCouleur());
         g.fillPolygon( controlleur.getMicroRoulotte().getShapeHayon( ));
    }
    private void drawHayonBezier(Graphics g) {
           
         g.setColor(Color.ORANGE);
         g.fillPolygon( controlleur.getMicroRoulotte().getShapeHayonInitialProfilBezier());
    }

    private void drawMurCoucheInterieur(Graphics g) {
         if(controlleur.getMicroRoulotte().getMurLateral().getMurCoucheInterieur().Visible()){
        g.setColor(controlleur.getMicroRoulotte().getMurLateral().getMurCoucheInterieur().getCouleur());
        g.fillPolygon( controlleur.getMicroRoulotte().getShapeMurInteterieur( ) ) ;
       
        //drawPlancher(g);
        drawPoutreArriere(g);
        drawMurSeparateur(g);
        drawToit(g);
        drawOuverturePorte(g);
        drawOuvertureFenetre(g);
        
    
     
       
         }
    }

    private void drawMurCoucheSequelette(Graphics g) {
        if(controlleur.getMicroRoulotte().getMurLateral().getMurCoucheSequelette().Visible()){
            g.setColor(controlleur.getMicroRoulotte().getMurLateral().getMurCoucheSequelette().getCouleur());
            g.fillPolygon( controlleur.getMicroRoulotte().getShapeMurInteterieur()) ;
            //drawPlancher(g);
            drawPoutreArriere(g);
            drawMurSeparateur(g);
            drawToit(g);
            drawOuverturePorte(g);
            drawOuvertureFenetre(g);
        }
    }

    private void drawMurCoucheExterieur(Graphics g) {
      g.setColor(controlleur.getMicroRoulotte().getMurLateral().getMurCoucheExterieur().getCouleur());
      g.fillPolygon( controlleur.getMicroRoulotte().getShapeMurExterieur( )) ;
    }
private void drawMurSeparateurBezier(Graphics g) {
      g.setColor(Color.blue);
      g.fillPolygon( controlleur.getMicroRoulotte().getShapeMurSeparateurInitialProfilBezier()) ;
    }
    private void drawToit(Graphics g) {
      g.setColor(controlleur.getMicroRoulotte().getToit().getCouleur());
      g.fillPolygon(controlleur.getMicroRoulotte().getShapeToit());
    }
 private void plancherbezier(Graphics g) {
      g.setColor(Color.BLUE);
      g.fillPolygon(controlleur.getMicroRoulotte().getShapePlancherInitialProfilBezier());
    }
    private void drawMurSeparateur(Graphics g) {
        
        g.setColor(controlleur.getMicroRoulotte().getMurSeparateur().getCouleur());
        g.fillPolygon(controlleur.getMicroRoulotte().getShapeMurSeparateur() );
   
    }

    private void drawOuverturePorte(Graphics g) {
       //  g.setColor(controlleur.getMicroRoulotte().getMurLateral().getPorte().getCouleur());
        // g.fillPolygon(controlleur.getMicroRoulotte().getMurLateral().getPorte().getShape() );
         if(this.controlleur.getMicroRoulotte().getMurLateral().getPorte().isSelect())
        { g.setColor(Color.GRAY);
        }else {g.setColor(Color.GREEN); }
       Point pointInit=this.controlleur.getMicroRoulotte().getMurLateral().getPorte().getPositionCoinInferieurGauchePorte(); 
       g.fillRect(pointInit.x, pointInit.y, (int) this.controlleur.getMicroRoulotte().getMurLateral().getPorte().getLongueur(),(int) this.controlleur.getMicroRoulotte().getMurLateral().getPorte().getHauteur());
    }

    private void drawOuvertureFenetre(Graphics g) {
        if(this.controlleur.getMicroRoulotte().getMurLateral().getFenetre().isSelect())
        { g.setColor(Color.GRAY);
        }else {g.setColor(Color.GREEN); }
       Point pointInit=this.controlleur.getMicroRoulotte().getMurLateral().getFenetre().getPositionCoinInferieurGaucheFenetre(); 
       g.fillRect(pointInit.x, pointInit.y,(int) this.controlleur.getMicroRoulotte().getMurLateral().getFenetre().getHauteur(), (int) this.controlleur.getMicroRoulotte().getMurLateral().getFenetre().getLargeur());
  
    
   
    }

    private void drawProfil(Graphics g) {
          g.setColor(controlleur.getMicroRoulotte().getProfil().getCouleur()); 
          g.drawPolygon(this.controlleur.getMicroRoulotte().getShapeProfil());
    }
 private void drawProfilBezier(Graphics g) {
          g.setColor(Color.RED);
          if(this.controlleur.isEnModeAffichage()==false){
          int r=this.controlleur.getMicroRoulotte().getProfilBezier().getRayonPointDeControle();
          g.drawPolygon(this.controlleur.getMicroRoulotte().getShapeProfilBezier());
          g.fillOval(this.controlleur.getMicroRoulotte().getProfilBezier().getPointSuperieurGauche().x, this.controlleur.getMicroRoulotte().getProfilBezier().getPointSuperieurGauche().y,r, r);
          g.fillOval(this.controlleur.getMicroRoulotte().getProfilBezier().getPointSuperieurDroit().x, this.controlleur.getMicroRoulotte().getProfilBezier().getPointSuperieurDroit().y,r, r);
        //  g.fillOval(this.controlleur.getMicroRoulotte().getProfilBezier().getPointInferieurGauche().x, this.controlleur.getMicroRoulotte().getProfilBezier().getPointInferieurGauche().y,r, r);
         // g.fillOval(this.controlleur.getMicroRoulotte().getProfilBezier().getPointInferieurDroit().x, this.controlleur.getMicroRoulotte().getProfilBezier().getPointInferieurDroit().y,r, r);
           }
          // g.setColor(controlleur.getMicroRoulotte().getProfilBezier().getCouleur());
         if(this.controlleur.isEnModeAffichage()==true)
         { g.drawPolygon(this.controlleur.getMicroRoulotte().getShapeProfilBezier());
            // g.drawPolygon(this.controlleur.getMicroRoulotte().getMurLateral().getFenetre().getShapePolygonFenetre()); 
         }
 }

    private void drawAideDessin(Graphics g) {

            int positionX = controlleur.getAideDessin().getPositionCadran2().x;
            int positionY = controlleur.getAideDessin().getPositionCadran2().y;
            double width = controlleur.getAideDessin().getLargeur();
            double height = controlleur.getAideDessin().getHauteur();
         
            g.setColor(controlleur.getMicroRoulotte().getAideDessin().getCouleur());
            g.drawPolygon(controlleur.getAideDessin().getShape());
       
            int x1 = positionX;
            int y1 = positionY;
            
            int x2=  positionX;
            int y2 = (int)height;
            while(width>controlleur.getAideDessin().getGratuationX())
            {
                 x1 +=controlleur.getAideDessin().getGratuationX(); 
                 g.drawLine(x1, positionY, x1, (int)height);
                
                  width -=controlleur.getAideDessin().getGratuationX(); 
               
            }
            positionX =0;
             width = controlleur.getAideDessin().getLargeur();
             height = controlleur.getAideDessin().getHauteur();
            while(height>controlleur.getAideDessin().getGratuationY())
            {
                y1 +=controlleur.getAideDessin().getGratuationY(); 
              
                g.drawLine(positionX, y1, positionX+(int)width, y1);
                height -= controlleur.getAideDessin().getGratuationY();
            }
      
    }
    
    
      static public double sqr(double a) {
        return a*a;
    }
 
    static public double distance(double x1, double y1, double x2, double y2) {
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
