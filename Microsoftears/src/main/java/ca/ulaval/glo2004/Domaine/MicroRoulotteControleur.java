/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import ca.ulaval.glo2004.Domaine.Service.Util;
import static ca.ulaval.glo2004.Domaine.Service.Util.Calculerdistance;
import ca.ulaval.glo2004.gui.DrawingPanel;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jdk.internal.org.jline.utils.Log;
import java.awt.Polygon;
import java.awt.geom.PathIterator;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
//import jdk.incubator.jpackage.internal.Log;


/**
 *
 * @author Solution
 */
public class MicroRoulotteControleur {
    private static final Logger LOG = Logger.getLogger(MicroRoulotteControleur.class.getName());
    public double zoom = 1;
    public boolean zoomer= false;
     public boolean capterUndoRedo= false;
    private MicroRoulotte microRoulotte;
    private BufferedImage imageRoulotte2d;
    private boolean enModeDessinBezier=true;
    private boolean enModeAffichage=false;
    private boolean enModeDessinPanneau=false;
    public boolean isEnCM ;
    private boolean grilleAffiche=true;
    public boolean isEnModeAffichage() {
        return enModeAffichage;
    }

    public boolean isEnModeDessinPanneau() {
        return enModeDessinPanneau;
    }
    
    public boolean isEnCM() {
        return isEnCM;
    }

    public boolean isGrilleAffiche() {
        return grilleAffiche;
    }

    public void setGrilleAffiche(boolean grilleAffiche) {
        this.grilleAffiche = grilleAffiche;
    }
    
    public boolean setisEnCM(boolean bool ) {
        return isEnCM = bool;
    }

    public void setEnModeDessinPanneau(boolean enModeDessinPanneau) {
        this.enModeDessinPanneau = enModeDessinPanneau;
    }

    public void setEnModeAffichage(boolean enModeAffichage) {
        this.enModeAffichage = enModeAffichage;
    }

    public void setEnModeDessinBezier(boolean enModeDessinBezier) {
        this.enModeDessinBezier = enModeDessinBezier;
    }
 
    public MicroRoulotteControleur(MicroRoulotte microRoulotte) {

        this.microRoulotte = microRoulotte;
         // Sauvegarder l'action
        sauvegader();
    }

    public boolean isEnModeDessinBezier() {
        return enModeDessinBezier;
    }

    public MicroRoulotteControleur() {
        microRoulotte = MicroRoulotte.getInstance();
        // Sauvegarder l'action
        sauvegader();
    }
    private static Path[] inputConstituant = new Path[1000];
    private static int indexAfficher=-1;
    
    private void sauvegader(){
      if(capterUndoRedo && indexAfficher>=-1 && indexAfficher<=999){
               // to do on serialise et sauvegarde
       Path fileName = Util.Serialize(microRoulotte);
       inputConstituant[++indexAfficher] = fileName;
       
       // reset table 
       if(inputConstituant[indexAfficher+1] != null){
           for(int i =indexAfficher+1; indexAfficher< inputConstituant.length; i++)
               inputConstituant[i] = null;
       }
       
       }
    }
    public void addFenetre(int largeur,int hauteur ) {
        
        Point p=this.getMicroRoulotte().getPositionInitialeCoinSupDroit();//a redefinir
        double l=this.getMicroRoulotte().getProfilBezier().getLongueur();//a redefinir avec longueur et hauteur microroulotte
        double h=this.getMicroRoulotte().getProfilBezier().getHauteur();
        //position initiale choisie
        int x=p.x+(int)((0.6)*l);
        int y=p.y+(int)((0.6)*h);
        Point positioncoinInfGauche=new Point(x,y);
        this.microRoulotte.getMurLateral().getFenetre().setHauteur(hauteur);
        this.microRoulotte.getMurLateral().getFenetre().setLargeur(largeur);
        this.microRoulotte.getMurLateral().getFenetre().setPositionCoinInferieurGaucheFenetre(positioncoinInfGauche);
        this.microRoulotte.getMurLateral().getFenetre().setVisibilite(true);
       // sauvegader();
    }
   
     public void addPorte(int largeur,int hauteur) {      
        Point p=this.getMicroRoulotte().getPositionInitialeCoinSupDroit();//a redefinir avec longueur et hauteur microroulotte
        double l=this.getMicroRoulotte().getProfilBezier().getLongueur();
        double h=this.getMicroRoulotte().getProfilBezier().getHauteur();
        //position initiale choisie
        int x=p.x+(int)((0.4)*l);
        int y=p.y+(int)((0.7)*h);
        Point positioncoinInfGauche=new Point(x,y); 
        this.microRoulotte.getMurLateral().getPorte().setPositionCoinInferieurGauchePorte(positioncoinInfGauche); 
        this.microRoulotte.getMurLateral().getPorte().setHauteur(hauteur);
        this.microRoulotte.getMurLateral().getPorte().setLongueur(largeur);
         this.microRoulotte.getMurLateral().getPorte().setVisibilite(true);
    }
     public void removePorte() {
    this.microRoulotte.getMurLateral().getPorte().setVisibilite(false);
    }
      public void removeFenetre() {
    this.microRoulotte.getMurLateral().getFenetre().setVisibilite(false);
    }
     public void addGrille(int pasHorizontal, int pasVertical) {
    this.microRoulotte.getAideDessin().setGratuationX(pasHorizontal);
     this.microRoulotte.getAideDessin().setGratuationX(pasVertical);
          
         
    } 
    public void reDo(){
      try
        {
            if(capterUndoRedo && indexAfficher>=-1 && indexAfficher<=999 && inputConstituant[indexAfficher+1] != null){
            
             Path fileName =  inputConstituant[indexAfficher+1];
             
             MicroRoulotte newmicroRoulotte = Util.Deserialize(fileName);
             this.microRoulotte = newmicroRoulotte;
             indexAfficher ++;
            }
        }catch(Exception ex ){
        
        }
    }
      
    public void unDo(){
        try
        {
            if(capterUndoRedo && indexAfficher>=0 && indexAfficher<=999){
             Path fileName =  inputConstituant[indexAfficher];
             MicroRoulotte newmicroRoulotte = Util.Deserialize(fileName);
             if(newmicroRoulotte != null){
               this.microRoulotte = newmicroRoulotte;
             }
             indexAfficher --;
            }
        }catch(Exception ex ){
        
        }
      
    }
    private void addProfilPanneau(Point mousePoint, double longueur, double hauteur) {

        this.microRoulotte.getProfil().setPositionCadran2(mousePoint);
        // Dimension du profil
        this.microRoulotte.getProfil().setLongueur(longueur);
        this.microRoulotte.getProfil().setHauteur(hauteur);
        

    }

    private void addProfilBezier(Point mousePoint, double longueur, double hauteur) {
        this.microRoulotte.getProfilBezier().setHauteur((int)hauteur);
        this.microRoulotte.getProfilBezier().setLongueur((int)longueur);
        this.microRoulotte.getProfilBezier().setInitialPointSuperieurGauche(mousePoint);

    }
 
    public String getElementSelectionne(Point positionReel) {

        try
        {
        if(!this.microRoulotte.getProfil().Exits(positionReel) ) {
            return "";
        }

        if (this.microRoulotte.getMurLateral().getFenetre().Visible() && this.microRoulotte.getMurLateral().getFenetre().Exits(positionReel)) {
           return TypeElement.FENETRE.toString();
            
       } 
       else if (this.microRoulotte.getMurLateral().getPorte().Visible() && this.microRoulotte.getMurLateral().getPorte().Exits(positionReel)) {

          return TypeElement.PORTE.toString();
        } 
        else if (this.microRoulotte.getMurLateral().getCoucheExterieur().Visible() && this.microRoulotte.getMurLateral().getCoucheExterieur().Exits(positionReel)) {
            return TypeElement.MUR_COUCHE_EXTERIEUR.toString();
            
        } else if (this.microRoulotte.getHayon().Visible() && this.microRoulotte.getHayon().Exits(positionReel)) {
            return TypeElement.HAYON.toString();
        } else if (this.microRoulotte.getPoutreArriere().Visible() &&   this.microRoulotte.getPoutreArriere().Exits(positionReel)) {
            return TypeElement.POUTRE_ARRIERE.toString();
        } else if (this.microRoulotte.getMurSeparateur().Visible() &&  this.microRoulotte.getMurSeparateur().Exits(positionReel)) {
            return TypeElement.MUR_SEPAREATEUR.toString();
        } else if (this.microRoulotte.getToit().Visible() &&  this.microRoulotte.getToit().Exits(positionReel)) {
            return TypeElement.TOIT.toString();
        } else if (this.microRoulotte.getMurLateral().getSquelette().Visible()&& this.microRoulotte.getMurLateral().getSquelette().Exits(positionReel)) {
            return TypeElement.MUR_COUCHE_SQUELETTE.toString();
        } else if (this.microRoulotte.getMurLateral().getCoucheInterieur().Visible()&& this.microRoulotte.getMurLateral().getCoucheInterieur().Exits(positionReel)) {
            return TypeElement.MUR_COUCHE_INTERIEUR.toString();
        } else if (this.microRoulotte.getPlancher().Visible() &&    this.microRoulotte.getPlancher().Exits(positionReel)) {
            return TypeElement.PLANCHER.toString();
        }
   // toto ajouter le ressort
   
       
        }catch(Exception ex){
            Log.info(ex.getMessage());
        }
      return "";
    }

    public void EditPointControl(Point pointCoordReel) {
        this.microRoulotte.getProfil().updatePointControl(pointCoordReel);
        this.microRoulotte.getPoutreArriere().setPositionCadran2(this.microRoulotte.getProfil().getPointSuperieurHorizontalGauche());
        
         // Sauvegarder l'action
        sauvegader();
    }

    public void setDimensioneEspaceDessin(int longueurEspaceDessin, int largeurEspaceDessin) {
        AideDessin.getInstance().setHauteur(largeurEspaceDessin);
        AideDessin.getInstance().setLargeur(longueurEspaceDessin);
        // Sauvegarder l'action
        sauvegader();
    }
    
      public  double StringToDouble(String text){
       return Util.StringToDouble( text);
      }

    public void EditConstituant(MicroRoulotte.InputType inputType, String text) throws Exception {
        try{
        double inputValeurDecimal = Util.StringToDouble( text);
        inputValeurDecimal = this.convertirMesure(inputValeurDecimal);
        this.microRoulotte.UpdadeConstituant( inputType,  text,  inputValeurDecimal);
        
        // Sauvegarder l'action
        sauvegader();
        
        }
        catch(Exception ex){
          LOG.info(ex.getMessage());
           throw ex;
        }
    }

    public void EnregistrerProfil() {

        Point PCR2 = this.microRoulotte.getProfil().getCentreRayonInferieurDroite();//donné
        Point PCR3 = this.microRoulotte.getProfil().getCentreRayonInferieurGauche();//donné

        // plancher
        this.microRoulotte.getPlancher().setLongueur((PCR2.x - PCR3.x));
        this.microRoulotte.getPlancher().setPositionCadran2(this.microRoulotte.getProfil().getPointInferieurHorizontalGauche());

        // poutre arriere
        this.microRoulotte.getPoutreArriere().setPositionCadran2(this.microRoulotte.getProfil().getPointSuperieurHorizontalGauche());

        // Mur lateral
        this.microRoulotte.getMurLateral().setDimmensions(this.microRoulotte.getProfil().getLongueur(), this.microRoulotte.getProfil().getHauteur());
        this.microRoulotte.getMurLateral().setPositionInit(this.microRoulotte.getProfil().getPositionCadran2());
        
        // Sauvegarder l'action
        sauvegader();
    }

    public Point getCoordoneeReel(Point mouse, double zoom) {
      Point coorReel= Util.getCoordoneeReel( mouse, zoom);
      return coorReel;
    }
     public void InitialiserPointPoutre () {
      Point p=this.microRoulotte.getProfilBezier().getListePointsPolygone().get(30);
      this.microRoulotte.setPointDebutPoutreArriere(p);
    }


   
   
 

    /* public static enum CadranPointControl 
    {
         PREMIER,DEUXIEME, TROISIEME, QUATRIEME, CENTRE, EXTERIEUR
    }
     */
    public enum TypeProfil {
        PANNEAU, BEZIER
    }

    public enum TypeOuverture {
        FENETRE, PORTE
    }

    public enum TypeCoucheMur {
        INTERIEUR, SEQUELETTE, EXTERIEUR
    }

    public enum TypeElement {
        AIDE_DESSIN, FENETRE, HAYON, MUR_COUCHE_EXTERIEUR, MUR_COUCHE_INTERIEUR, MUR_COUCHE_SQUELETTE, MUR_SEPAREATEUR, PLANCHER, PROFIL, PORTE, POUTRE_ARRIERE, POUTRE_AVANT, TOIT, TOIT_RECOUVREMENT
    }

    /**
     * Get the value of microRoulotte
     *
     * @return the value of microRoulotte
     */
    public MicroRoulotte getMicroRoulotte() {
        return microRoulotte;
    }

    /**
     * Get the value of aideDessin
     *
     * @return
     */
    public AideDessin getAideDessin() {
        return AideDessin.getInstance();
    }

    public void addProfil(TypeProfil typeProfil, Point mousePoint, double longueur, double hauteur) {
        
        if (typeProfil == typeProfil.PANNEAU) {
            addProfilPanneau(mousePoint, longueur, hauteur);
        } else {
            addProfilBezier(mousePoint, longueur, hauteur);
        }
        // Sauvegarder l'action
        sauvegader();
    }



    /**
     * *
     * Permet la couleur ou bien la visibilite d'un element
     *
     * @param typeElement
     * @param couleur
     */
    public void changerCouleur(TypeElement typeElement, Color couleur) {

        if (typeElement == TypeElement.AIDE_DESSIN) {
            AideDessin.getInstance().setCouleur(couleur);
        } else if (typeElement == TypeElement.FENETRE) {
            this.microRoulotte.getMurLateral().getFenetre().setCouleur(couleur);
        } else if (typeElement == TypeElement.PORTE) {
            this.microRoulotte.getMurLateral().getPorte().setCouleur(couleur);
        } else if (typeElement == TypeElement.TOIT) {
            this.microRoulotte.getToit().setCouleur(couleur);

        } else if (typeElement == TypeElement.POUTRE_ARRIERE) {
            this.microRoulotte.getPoutreArriere().setCouleur(couleur);
        } else if (typeElement == TypeElement.POUTRE_AVANT) {
            this.microRoulotte.getToit().setCouleurPoutreAvant(couleur);
        } else if (typeElement == TypeElement.TOIT_RECOUVREMENT) {
            this.microRoulotte.getToit().setCouleur(couleur);
        } else if (typeElement == TypeElement.MUR_COUCHE_INTERIEUR) {
            this.microRoulotte.getMurLateral().getMurCoucheInterieur().setCouleur(couleur);
        } else if (typeElement == TypeElement.MUR_COUCHE_SQUELETTE) {
            this.microRoulotte.getMurLateral().getMurCoucheSequelette().setCouleur(couleur);
        } else if (typeElement == TypeElement.MUR_COUCHE_EXTERIEUR) {
            this.microRoulotte.getMurLateral().getMurCoucheExterieur().setCouleur(couleur);
        } else if (typeElement == TypeElement.HAYON) {
            this.microRoulotte.getHayon().setCouleur(couleur);
        } else if (typeElement == TypeElement.PLANCHER) {
            this.microRoulotte.getPlancher().setCouleur(couleur);
        } else if (typeElement == TypeElement.MUR_SEPAREATEUR) {
            this.microRoulotte.getMurSeparateur().setCouleur(couleur);
        } else if (typeElement == TypeElement.PROFIL) {
            this.microRoulotte.getProfil().setCouleur(couleur);
        }
        
        // Sauvegarder l'action
        sauvegader();
    }

    public void changerVisibilite(TypeElement sender, int transparance) {
        if (sender == TypeElement.AIDE_DESSIN) {
            AideDessin.getInstance().setTransparence(transparance);
        } else if (sender == TypeElement.HAYON) {
            this.microRoulotte.getHayon().setTransparence(transparance);
        } else if (sender == TypeElement.MUR_COUCHE_EXTERIEUR) {
            this.microRoulotte.getMurLateral().getMurCoucheExterieur().setTransparence(transparance);
        } else if (sender == TypeElement.MUR_COUCHE_INTERIEUR) {
            this.microRoulotte.getMurLateral().getMurCoucheInterieur().setTransparence(transparance);
        } else if (sender == TypeElement.MUR_COUCHE_SQUELETTE) {
            this.microRoulotte.getMurLateral().getMurCoucheSequelette().setTransparence(transparance);
        } else if (sender == TypeElement.MUR_SEPAREATEUR) {
            this.microRoulotte.getMurSeparateur().setTransparence(transparance);
        } else if (sender == TypeElement.PLANCHER) {
            this.microRoulotte.getPlancher().setTransparence(transparance);
        } else if (sender == TypeElement.POUTRE_ARRIERE) {
            this.microRoulotte.getPoutreArriere().setTransparence(transparance);
        } else if (sender == TypeElement.PROFIL) {
            this.microRoulotte.getProfil().setTransparence(transparance);
        } else if (sender == TypeElement.POUTRE_AVANT) {
            this.microRoulotte.getToit().getCouleurPoutreAvant().setTransparence(transparance);
        } else if (sender == TypeElement.TOIT_RECOUVREMENT) {
            this.microRoulotte.getToit().setTransparence(transparance);
        } else if (sender == TypeElement.PORTE) {
            this.microRoulotte.getMurLateral().getPorte().setTransparence(transparance);
            this.microRoulotte.getMurLateral().getFenetre().setTransparence(transparance);
        }
        
        // Sauvegarder l'action
        sauvegader();
    }
    

     public Color getCouleur(TypeElement typeElement){
         Color couleur = Color.black;
        if (typeElement == TypeElement.AIDE_DESSIN) {
            AideDessin.getInstance().setCouleur(couleur);
        } else if (typeElement == TypeElement.FENETRE) {
            this.microRoulotte.getMurLateral().getFenetre().setCouleur(couleur);
        } else if (typeElement == TypeElement.PORTE) {
            this.microRoulotte.getMurLateral().getPorte().setCouleur(couleur);
        } else if (typeElement == TypeElement.TOIT) {
            this.microRoulotte.getToit().setCouleur(couleur);

        } else if (typeElement == TypeElement.POUTRE_ARRIERE) {
            this.microRoulotte.getPoutreArriere().setCouleur(couleur);
        } else if (typeElement == TypeElement.POUTRE_AVANT) {
            this.microRoulotte.getToit().setCouleurPoutreAvant(couleur);
        } else if (typeElement == TypeElement.TOIT_RECOUVREMENT) {
            this.microRoulotte.getToit().setCouleur(couleur);
        } else if (typeElement == TypeElement.MUR_COUCHE_INTERIEUR) {
            this.microRoulotte.getMurLateral().getMurCoucheInterieur().setCouleur(couleur);
        } else if (typeElement == TypeElement.MUR_COUCHE_SQUELETTE) {
            this.microRoulotte.getMurLateral().getMurCoucheSequelette().setCouleur(couleur);
        } else if (typeElement == TypeElement.MUR_COUCHE_EXTERIEUR) {
            this.microRoulotte.getMurLateral().getMurCoucheExterieur().setCouleur(couleur);
        } else if (typeElement == TypeElement.HAYON) {
            this.microRoulotte.getHayon().setCouleur(couleur);
        } else if (typeElement == TypeElement.PLANCHER) {
            this.microRoulotte.getPlancher().setCouleur(couleur);
        } else if (typeElement == TypeElement.MUR_SEPAREATEUR) {
            this.microRoulotte.getMurSeparateur().setCouleur(couleur);
        } else if (typeElement == TypeElement.PROFIL) {
            this.microRoulotte.getProfil().setCouleur(couleur);
        }
        // Sauvegarder l'action
        sauvegader();
        return couleur;
    }



     public Graphics preparerPlan(int largeur, int hauteur){
        imageRoulotte2d = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_ARGB);
        Graphics g = imageRoulotte2d.createGraphics();
        // Sauvegarder l'action
        sauvegader();
        return g;
    }
     
     public void exporter2D(String nomFichier)throws Exception{
        RenderedImage rendImage = imageRoulotte2d;
        ImageIO.write(rendImage,"jpg",new File(nomFichier));
        
        // Sauvegarder l'action
        sauvegader();
    }
     public static  Point retournerLePointLePlusProche(Point p0,Point p1,Point p2,Point p3,Point p) {
   int distance0=(int)Calculerdistance(p0.x, p0.y, p.x, p.y);
   int distance1=(int)Calculerdistance(p1.x, p1.y, p.x, p.y);
   int distance2=(int)Calculerdistance(p2.x, p2.y, p.x, p.y);
   int distance3=(int)Calculerdistance(p3.x, p3.y, p.x, p.y);
   int[] arr = new int[]{(int)distance0,(int)distance1,(int)distance2,(int)distance3};
   arr = Arrays.stream(arr).sorted().toArray();
   if (arr[0] ==distance0)return p0;
           else if (arr[0] ==distance1)return p1 ;
                else if (arr[0] ==distance2) return p2;
                     else return p3;
                   
    }
      public static  Point retournerLePointLePlusProche(Point p0,Point p1,Point p) {
   int distance0=(int)Calculerdistance(p0.x, p0.y, p.x, p.y);
   int distance1=(int)Calculerdistance(p1.x, p1.y, p.x, p.y);
   
   int[] arr = new int[]{(int)distance0,(int)distance1};
   arr = Arrays.stream(arr).sorted().toArray();
   if (arr[0] ==distance0)return p0;
           else if (arr[0] ==distance1)return p1 ;
                 return p1;
                   
    }
  
      
      public void exporterSVG(String nomFichier) throws IOException{
        //Util.exporterFormatSVG(nomFichier, microRoulotte);
        
        
     
        double hauteur = DrawingPanel.HEIGHT;
        double largeur = DrawingPanel.WIDTH;
       
//        int[] Abscisses = this.microRoulotte.getShapeProfil().xpoints;
//        int[] Ordonnees = this.microRoulotte.getShapeProfil().ypoints;
        
        String contenu = "";
        
        BufferedWriter contenuFichier = new BufferedWriter(new FileWriter(nomFichier));
        String pointExclu = " 0.0"  + ",0.0" ;
         contenu += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        
        contenu += "<!DOCTYPE svg  PUBLIC '-//W3C//DTD SVG 1.1//EN'  'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'>\n";
        
        contenu += "<svg enable-background=\"new 0 0 480 960\" version=\"1.1\" viewBox=\"0 0 480 960\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\">";
       
        
        
         if (this.microRoulotte.getProfil().Visible()){
             String coordonneespoints = "" ;
             Color couleur = this.microRoulotte.getProfil().getCouleur() ;//this.microRoulotte.getProfil().getCouleur().toString();
             
             
             PathIterator it = this.microRoulotte.getShapeProfil().getPathIterator(null);
             while (!it.isDone())
            {   double[] coordonnees = new double[2];
                it.currentSegment(coordonnees);
                String valeurpoint = " " + Double.toString(coordonnees[0]) + "," + Double.toString(coordonnees[1]) ;
                if (!coordonneespoints.contains(valeurpoint) && !valeurpoint.equals(pointExclu)){
                    coordonneespoints += valeurpoint;
                  
                }
                it.next();
                
            }
             
             contenu += "       <polygon points=\"" + coordonneespoints + "\"" +
"           style=\"fill:none;stroke:"+ couleur +";stroke-width:5;fill-rule:evenodd\" />\n";
         }
         
         if (this.microRoulotte.getHayon().Visible()){
             String coordonneespoints = "" ;
             String couleur = this.microRoulotte.getHayon().getCouleur().toString();
             
             
             PathIterator it = this.microRoulotte.getShapeHayon().getPathIterator(null);
             while (!it.isDone())
            {   double[] coordonnees = new double[2];
                it.currentSegment(coordonnees);
                String valeurpoint = " " + Double.toString(coordonnees[0]) + "," + Double.toString(coordonnees[1]) ;
                if (!coordonneespoints.contains(valeurpoint) && !valeurpoint.equals(pointExclu)){
                    coordonneespoints += valeurpoint;
                  
                }
                it.next();
            }
             
             contenu += "      <polygon points=\"" + coordonneespoints + "\"" +
"           style=\"fill:" + "GRAY" + ";stroke:" + "GRAY" + ";stroke-width:5;fill-rule:evenodd\" />\n";
         }
         
         if (this.microRoulotte.getMurLateral().getCoucheExterieur().Visible() ){
             String coordonneespoints = "" ;
             String couleur = this.microRoulotte.getMurLateral().getCoucheExterieur().getCouleur().toString();
             
             
             PathIterator it = this.microRoulotte.getShapeMurExterieur().getPathIterator(null);
             while (!it.isDone())
            {   double[] coordonnees = new double[2];
                it.currentSegment(coordonnees);
                String valeurpoint = " " + Double.toString(coordonnees[0]) + "," + Double.toString(coordonnees[1]) ;
                if (!coordonneespoints.contains(valeurpoint) && !valeurpoint.equals(pointExclu)){
                    coordonneespoints += valeurpoint;
                  
                }
                it.next();
                
                
            }
             
             contenu += "      <polygon points=\"" + coordonneespoints + "\"" +
"           style=\"fill:" + "GRAY" + ";stroke:"+ "GRAY" +";stroke-width:5;fill-rule:evenodd\" />\n";
         } else {
             if(this.microRoulotte.getMurLateral().getMurCoucheSequelette().Visible()){
                //listeShapes.add(this.microRoulotte.); 
             } else {
                 if(this.microRoulotte.getMurLateral().getCoucheInterieur().Visible())
                 {  
                     String coordonneespoints = "" ;
                     String couleur = this.microRoulotte.getMurLateral().getMurCoucheInterieur().getCouleur().toString();


                        PathIterator it = this.microRoulotte.getShapeMurInteterieur().getPathIterator(null);
                        while (!it.isDone())
                       {   double[] coordonnees = new double[2];
                           it.currentSegment(coordonnees);
                           String valeurpoint = " " + Double.toString(coordonnees[0]) + "," + Double.toString(coordonnees[1]) ;
                           if (!coordonneespoints.contains(valeurpoint) && !valeurpoint.equals(pointExclu)){
                               coordonneespoints += valeurpoint;
                  
                }
                            it.next();
                       }

                        contenu += "       <polygon points=\"" + coordonneespoints + "\"" +
           "           style=\"fill:" + "GRAY" + ";stroke:"+ "GRAY" +";stroke-width:5;fill-rule:evenodd\" />\n";
                            }
             }
         }
         
         if (this.microRoulotte.getPlancher().Visible()){
             String coordonneespoints = "" ;
             String couleur = this.microRoulotte.getPlancher().getCouleur().toString();
             
             
             PathIterator it = this.microRoulotte.getShapePlancher().getPathIterator(null);
             while (!it.isDone())
            {   double[] coordonnees = new double[2];
                it.currentSegment(coordonnees);
                String valeurpoint = " " + Double.toString(coordonnees[0]) + "," + Double.toString(coordonnees[1]) ;
                if (!coordonneespoints.contains(valeurpoint) && !valeurpoint.equals(pointExclu)){
                    coordonneespoints += valeurpoint;
                  
                }
                it.next();
            }
             
             contenu += "       <polygon points=\"" + coordonneespoints + "\"" +
"           style=\"fill:" + "GRAY" + ";stroke:"+ "GRAY" +";stroke-width:5;fill-rule:evenodd\" />\n";
         }
         
         if (this.microRoulotte.getPoutreArriere().Visible()){
             String coordonneespoints = "" ;
             String couleur = this.microRoulotte.getPoutreArriere().getCouleur().toString();
             
             
             PathIterator it = this.microRoulotte.getShapePoutreArriere().getPathIterator(null);
             while (!it.isDone())
            {   double[] coordonnees = new double[2];
                it.currentSegment(coordonnees);
                String valeurpoint = " " + Double.toString(coordonnees[0]) + "," + Double.toString(coordonnees[1]) ;
                if (!coordonneespoints.contains(valeurpoint) && !valeurpoint.equals(pointExclu)){
                    coordonneespoints += valeurpoint;
                  
                }
                it.next();
            }
             
             contenu += "       <polygon points=\"" + coordonneespoints + "\"" +
"           style=\"fill:none;stroke:"+ "GRAY" +";stroke-width:5;fill-rule:evenodd\" />\n";
         }
         
         if (this.microRoulotte.getToit().Visible()){
             String coordonneespoints = "" ;
             String couleur = this.microRoulotte.getToit().getCouleur().toString();
             
             
             PathIterator it = this.microRoulotte.getShapeToit().getPathIterator(null);
             while (!it.isDone())
            {   double[] coordonnees = new double[2];
                it.currentSegment(coordonnees);
                String valeurpoint = " " + Double.toString(coordonnees[0]) + "," + Double.toString(coordonnees[1]) ;
                if (!coordonneespoints.contains(valeurpoint) && !valeurpoint.equals(pointExclu)){
                    coordonneespoints += valeurpoint;
                  
                }
                it.next();
            }
             
             contenu += "       <polygon points=\"" + coordonneespoints + "\"" +
"           style=\"fill:none;stroke:"+ "GRAY" +";stroke-width:5;fill-rule:evenodd\" />\n" ;
         }
         
         
         contenu += "</svg>";
        
         System.out.println(contenu);
         contenuFichier.write(contenu);
         contenuFichier.close();
            }

      
       
        public double convertirMesure( double valeur){
        
        if(this.isEnCM )
           return valeur * 2;
        
        else {   
           return valeur * (2*2.54);
        }
        }
        
         public void enregistrerFichier(String nomFichier)throws IOException{
            
         FileOutputStream contenuFichier =
         new FileOutputStream(nomFichier);
         ObjectOutputStream sortie = new ObjectOutputStream(contenuFichier);
         sortie.writeObject(this.microRoulotte);
         sortie.close();
         contenuFichier.close();
            
        }
        
        public void ouvrirFichier(String nomFichier)throws IOException,ClassNotFoundException{
            
         FileOutputStream contenuFichier =
         new FileOutputStream(nomFichier);
         ObjectOutputStream sortie = new ObjectOutputStream(contenuFichier);
         sortie.writeObject(this.microRoulotte);
         sortie.close();
         contenuFichier.close();
            
        }
        
}


        

        
        
        
   
      


        

