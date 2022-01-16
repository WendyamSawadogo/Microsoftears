/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;


import static ca.ulaval.glo2004.Domaine.Service.ProfilService.sqr;
import ca.ulaval.glo2004.Domaine.Service.Util;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Solution
 */
public class MicroRoulotte  implements Serializable  {

   
    
    public static enum InputType 
    {
         PROFIL_LONGUEUR,PROFIL_HAUTEUR, 
         PLANCHER_HAUTEUR, PLANCHER_MARGE_ARRIERE,PLANCHER_MARGE_AVANT,
         POUTRE_ARRIERE_LARGEUR, POUTRE_ARRIERE_HAUTEUR, POUTRE_ARRIERE_POSITION,
         HAYON_EPAISSEUR, HAYON_POIDS, HAYON_RAYON_ARC_SUP,HAYON_EPAISSEUR_TRAIT_SCIE,HAYON_DISTANCE_POUTRE_ARRIERE, HAYON_DISTANCE_PLANCHER,
         TOIT_EPAISSEUR,  TOIT_NBRE_POUTRE_AVANT,TOIT_EPAISSEUR_POUTRE_AVANT,TOIT_LARGEUR_POUTRE_AVANT,
         MUR_LATERAL_INTERIEUR, MUR_LATERAL_SQUELETTE, MUR_LATERAL_EXTERIEUR,
         PORTE_HAUTEUR, PORTE_LARGEUR, PORTE_POSITION, PORTE_RAYON_ARC,
         FENETRE_HAUTEUR, FENETRE_LARGEUR, FENETRE_POSITION, FENETRE_RAYON_ARC,
         MUR_SEPARATEUR_EPAISSEUR, MUR_SEPARATEUR_DISTANCE_PLACHER, MUR_SEPARATEUR_DISTANCE_POUTRE_ARRIERE
    }
    
    // to do initialiser
    public  Map<InputType,HashMap<String, Double>> InputConstituant = new HashMap<>();
           
    private Profil monProfil;

    
    private ProfilCourbeBezier monProfilBezier;
    
    private MurLateral murLateral;
    
    private Plancher plancher;
    
    private Hayon hayon;
    
    private Toit toit;
    
    private AideDessin aideDessin;
    
    private Ressort ressort;

    private PoutreArriere poutreArriere;
    
    private MurSeparateur murSeparateur;
    private double largueur;
    private double largeurProfil;
    private double hauteurProfil;
    private Point positionInitialeCoinSupDroit;
    private Point pointDebutPoutreArriere;
    public Point getPositionInitialeCoinSupDroit() {
        return positionInitialeCoinSupDroit;
    }
  //this.positionInitialeCoinSupDroit 
    public void setPositionInitialeCoinSupDroit(Point positionInitialeCoinSupDroit) {
        this.positionInitialeCoinSupDroit = positionInitialeCoinSupDroit;
    }

    public Point getPointDebutPoutreArriere() {
        return pointDebutPoutreArriere;
    }
    public void setPointDebutPoutreArriere( int n) {
        this.pointDebutPoutreArriere =new Point(this.monProfilBezier.listePointsPolygone.get(n).x,this.monProfilBezier.listePointsPolygone.get(n).y);
    }
    public void setPointDebutPoutreArriere(Point pointDebutPoutreArriere) {
        this.pointDebutPoutreArriere = pointDebutPoutreArriere;
    }

    
    private static final MicroRoulotte SINGLETON_INSTANCE = new MicroRoulotte();
    public static MicroRoulotte getInstance() {
      return SINGLETON_INSTANCE;
    }
    public ProfilCourbeBezier getProfilBezier() {
        return monProfilBezier;
    }

    public double getLargeurProfil() {
        return largeurProfil;
    }

    public void setLargeurProfil(double largeurProfil) {
        this.largeurProfil = largeurProfil;
    }

    public double getHauteurProfil() {
        return hauteurProfil;
    }

    public void setHauteurProfil(double hauteurProfil) {
        this.hauteurProfil = hauteurProfil;
    }
    
    private void initMapElement(){
        
         HashMap<String, Double> valeurEcran = new HashMap<>();
        valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PROFIL_LONGUEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PROFIL_HAUTEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PLANCHER_HAUTEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PLANCHER_MARGE_ARRIERE, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PLANCHER_MARGE_AVANT, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.POUTRE_ARRIERE_LARGEUR, valeurEcran); 
        valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.POUTRE_ARRIERE_HAUTEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.POUTRE_ARRIERE_POSITION, valeurEcran);
        
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.HAYON_EPAISSEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.HAYON_POIDS, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.HAYON_RAYON_ARC_SUP, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.HAYON_EPAISSEUR_TRAIT_SCIE, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.HAYON_DISTANCE_POUTRE_ARRIERE, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.HAYON_DISTANCE_PLANCHER, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.TOIT_EPAISSEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.TOIT_NBRE_POUTRE_AVANT, valeurEcran);
        
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.TOIT_EPAISSEUR_POUTRE_AVANT, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.TOIT_LARGEUR_POUTRE_AVANT, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.MUR_LATERAL_INTERIEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.MUR_LATERAL_SQUELETTE, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.MUR_LATERAL_EXTERIEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PORTE_HAUTEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PORTE_LARGEUR, valeurEcran);
        
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PORTE_POSITION, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.PORTE_RAYON_ARC, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.FENETRE_HAUTEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.FENETRE_LARGEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.FENETRE_POSITION, valeurEcran);
        
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.FENETRE_RAYON_ARC, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.MUR_SEPARATEUR_EPAISSEUR, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.MUR_SEPARATEUR_DISTANCE_PLACHER, valeurEcran);
         valeurEcran = new HashMap<>(); valeurEcran.put("", 0.0);
        InputConstituant.put(InputType.MUR_SEPARATEUR_DISTANCE_POUTRE_ARRIERE, valeurEcran);
         
        
    }
   
    private MicroRoulotte() {
        monProfil = Profil.getInstance();
        monProfilBezier = ProfilCourbeBezier.getInstance();
        murLateral =  MurLateral.getInstance();
        hayon = Hayon.getInstance();
        toit =  Toit.getInstance();
        aideDessin = AideDessin.getInstance();
        ressort = new Ressort();
        murSeparateur = MurSeparateur.getInstance();
        poutreArriere = PoutreArriere.getInstance();
        plancher = Plancher.getInstance();
        largueur = 0;
        this.plancher.setPositionCoinInfGauchePlancher(this.monProfilBezier.getPointInferieurGauche());//placer le plancher
        this.pointDebutPoutreArriere=new Point();
        initMapElement();
    }
    /**
     * Get the value of largueur
     *
     * @return the value of largueur
     */
    public double getLargueur() {
        return largueur;
    }

    /**
     * Set the value of largueur
     *
     * @param largueur new value of largueur
     */
    public void setLargueur(double largueur) {
        this.largueur = largueur;
    }


    /**
     * Get the value of murSeparateur
     *
     * @return the value of murSeparateur
     */
    public MurSeparateur getMurSeparateur() {
        return murSeparateur;
    }

 

    /**
     * Get the value of poutreArriere
     *
     * @return the value of poutreArriere
     */
    public PoutreArriere getPoutreArriere() {
        return poutreArriere;
    }


    /**
     * Get the value of ressort
     *
     * @return the value of ressort
     */
    public Ressort getRessort() {
        return ressort;
    }

  public Polygon getShapeHayonInitialProfilBezier() { 
      int e=(int)this.hayon.epaisseur;
      int ma=(int)this.plancher.margeAvant;
       int rayon=(int)this.hayon.rayonArcSuperieur;
     Polygon polyHayonBezier=new Polygon();
     polyHayonBezier.addPoint(this.monProfilBezier.getPointInferieurGauche().x,this.monProfilBezier.getPointInferieurGauche().y);
     Point pFinH=this.monProfilBezier.getListePointsPolygone().get(30);
     for (int i=0;i<=30;i++){ 
     polyHayonBezier.addPoint(this.monProfilBezier.getListePointsPolygone().get(i).x,this.monProfilBezier.getListePointsPolygone().get(i).y);
     } //ajouter arrondi ici
     this.pointDebutPoutreArriere=new Point(this.monProfilBezier.getListePointsPolygone().get(30).x,this.monProfilBezier.getListePointsPolygone().get(30).y);
      polyHayonBezier.addPoint(pointDebutPoutreArriere.x,pointDebutPoutreArriere.y);
      //
      polyHayonBezier.addPoint(pointDebutPoutreArriere.x+rayon,this.ordonnee(pointDebutPoutreArriere.x+rayon)); 
//ajout de l arc ici
      
      
    for (int i=30;i>0;i--){ 
        if((this.monProfilBezier.getListePointsPolygone().get(i).x+e<this.pointDebutPoutreArriere.x)&&(this.monProfilBezier.getListePointsPolygone().get(i).y+e<this.monProfilBezier.getPointInferieurGauche().y-e))
     polyHayonBezier.addPoint(this.monProfilBezier.getListePointsPolygone().get(i).x+e,this.monProfilBezier.getListePointsPolygone().get(i).y+e);
     }
      polyHayonBezier.addPoint(this.monProfilBezier.getPointInferieurGauche().x+e+ma,this.monProfilBezier.getPointInferieurGauche().y-e);
     polyHayonBezier.addPoint(this.monProfilBezier.getPointInferieurGauche().x+e+ma,this.monProfilBezier.getPointInferieurGauche().y);
     this.hayon.setShapeHayonBezier(polyHayonBezier);
        return polyHayonBezier;
     }

public Polygon getShapePoutreInitialProfilBezier()
        {int e=(int)this.hayon.epaisseur;
      int ma=(int)this.plancher.margeAvant;
      int rayon=(int)this.hayon.rayonArcSuperieur;
      int longPoutre=(int)this.poutreArriere.getLongueur();
      int hautPoutre=(int)this.poutreArriere.getHauteur();
      int traiscie=(int)this.hayon.epaisseurTraitScie;
       this.pointDebutPoutreArriere=new Point(this.monProfilBezier.getListePointsPolygone().get(30).x,this.monProfilBezier.getListePointsPolygone().get(30).y);
     Polygon polyPoutreBezier=new Polygon();
     polyPoutreBezier.addPoint(this.pointDebutPoutreArriere.x+rayon+traiscie,this.ordonnee(this.pointDebutPoutreArriere.x+rayon+traiscie));
     
     polyPoutreBezier.addPoint(this.pointDebutPoutreArriere.x+rayon+traiscie+longPoutre,this.ordonnee(this.pointDebutPoutreArriere.x+rayon+traiscie+longPoutre));
      polyPoutreBezier.addPoint(this.pointDebutPoutreArriere.x+rayon+traiscie+longPoutre,this.pointDebutPoutreArriere.y+hautPoutre);
       polyPoutreBezier.addPoint(this.pointDebutPoutreArriere.x+rayon+traiscie,this.pointDebutPoutreArriere.y+hautPoutre);
      this.poutreArriere.setShapePoutre(polyPoutreBezier);
        return polyPoutreBezier;
    }

int abcisse (int y) {
        for (int i=0;i<=100;i++){ 
   if(Math.abs(y-this.monProfilBezier.getListePointsPolygone().get(i).y )<0.01) return this.monProfilBezier.getListePointsPolygone().get(i).x;     
        }
    return 0;//a catcher
    }

int ordonnee (int x) {
        for (int i=0;i<=100;i++){ 
   if(x-this.monProfilBezier.getListePointsPolygone().get(i).x <0.01) return this.monProfilBezier.getListePointsPolygone().get(i).y;     
        }
    return 0;//a catcher
    }
public Polygon getShapePlancherInitialProfilBezier()
        {int e=(int)this.plancher.getHauteur();
        
      int ma=(int)this.plancher.margeAvant;
      int mr=(int)this.plancher.margeArriere;
      int eh=(int)this.hayon.epaisseur;
      int l=(int)this.monProfilBezier.getLongueur();
      int h=(int)this.monProfilBezier.getHauteur();
     int traiscie=(int)this.hayon.epaisseurTraitScie;
      
       this.pointDebutPoutreArriere=new Point(this.monProfilBezier.getListePointsPolygone().get(30).x, (this.monProfilBezier.getListePointsPolygone().get(30).y));
     Polygon polyPlancherBezier=new Polygon();
    polyPlancherBezier.addPoint(this.monProfilBezier.getPointInferieurGauche().x+eh+ma+traiscie, this.monProfilBezier.getPointInferieurGauche().y);
     polyPlancherBezier.addPoint(this.monProfilBezier.getPointInferieurGauche().x+eh+ma+traiscie, this.monProfilBezier.getPointInferieurGauche().y-e);
     polyPlancherBezier.addPoint(this.monProfilBezier.getPointInferieurDroit().x-mr, this.monProfilBezier.getPointInferieurDroit().y-e); 
     polyPlancherBezier.addPoint(this.monProfilBezier.getPointInferieurDroit().x-mr, this.monProfilBezier.getPointInferieurDroit().y); 
       this.plancher.setShapeDuPlancher(polyPlancherBezier);
     return polyPlancherBezier;
    }
public Polygon getShapeMurSeparateurInitialProfilBezier()
        {int e=(int)this.murSeparateur.epaisseur;
        int h=(int)this.murSeparateur.getHauteur();
        int l=(int)this.murSeparateur.getLongueur(); 
         int rayon=(int)this.hayon.rayonArcSuperieur;
          int longPoutre=(int)this.poutreArriere.getLongueur();
          int dp=(int)this.murSeparateur.getDistanceDuPlancher();
          int da=(int)this.murSeparateur.getDistancePoutreArriere();
          int eplancher=(int)this.plancher.getHauteur();
     Polygon polyMurSeparateur=new Polygon();
    polyMurSeparateur.addPoint(this.pointDebutPoutreArriere.x+da+rayon+longPoutre,this.ordonnee(this.pointDebutPoutreArriere.x+rayon+da+longPoutre));
    polyMurSeparateur.addPoint(this.pointDebutPoutreArriere.x+da+rayon+longPoutre+e,this.ordonnee(this.pointDebutPoutreArriere.x+rayon+da+longPoutre+e));
    polyMurSeparateur.addPoint(this.pointDebutPoutreArriere.x+da+rayon+longPoutre+e,this.monProfilBezier.getPointInferieurGauche().y-dp-eplancher);
    polyMurSeparateur.addPoint(this.pointDebutPoutreArriere.x+da+rayon+longPoutre,this.monProfilBezier.getPointInferieurGauche().y-dp-eplancher);
    
     this.murSeparateur.setShapeMurSeparateur(polyMurSeparateur);   
     return polyMurSeparateur;
    }
public Polygon getShapeToitInitialProfilBezier() { 
     int ems=(int)this.murSeparateur.epaisseur;
        int h=(int)this.murSeparateur.getHauteur();
        int l=(int)this.murSeparateur.getLongueur(); 
         int rayon=(int)this.hayon.rayonArcSuperieur;
          int longPoutre=(int)this.poutreArriere.getLongueur();
          int dp=(int)this.murSeparateur.getDistanceDuPlancher();
          int da=(int)this.murSeparateur.getDistancePoutreArriere();
          int eplancher=(int)this.plancher.getHauteur();
          int etoit=(int)this.toit.getEpaisseur();
          Point dernierPoint=new Point();
     Polygon polyToitBezier=new Polygon();
     polyToitBezier.addPoint(this.pointDebutPoutreArriere.x+da+rayon+longPoutre+ems,this.ordonnee(this.pointDebutPoutreArriere.x+rayon+da+longPoutre+ems));
     Point pFinH=this.monProfilBezier.getListePointsPolygone().get(30);
     for (int i=0;i<=100;i++){ 
         if((this.monProfilBezier.getListePointsPolygone().get(i).x>this.pointDebutPoutreArriere.x+da+rayon+longPoutre+ems)&&(this.monProfilBezier.getListePointsPolygone().get(i).y<this.monProfilBezier.getPointInferieurDroit().y-eplancher))
        {  polyToitBezier.addPoint(this.monProfilBezier.getListePointsPolygone().get(i).x,this.monProfilBezier.getListePointsPolygone().get(i).y);
          dernierPoint=new Point(this.monProfilBezier.getListePointsPolygone().get(i).x,this.monProfilBezier.getListePointsPolygone().get(i).y);
        } 
        } 
      polyToitBezier.addPoint(dernierPoint.x-etoit,dernierPoint.y);
     for (int i=100;i>30;i--){ 
         if((this.monProfilBezier.getListePointsPolygone().get(i).y<dernierPoint.y)&&(this.monProfilBezier.getListePointsPolygone().get(i).x>this.pointDebutPoutreArriere.x+da+rayon+longPoutre+ems))
            
             polyToitBezier.addPoint(this.monProfilBezier.getListePointsPolygone().get(i).x-etoit,this.monProfilBezier.getListePointsPolygone().get(i).y+etoit);
            
     }
    
     
     
     
     
  return polyToitBezier;
     }
    /**
     * Get the value of aideDessin
     *
     * @return the value of aideDessin
     */
    public AideDessin getAideDessin() {
        return aideDessin;
    }

    /**
     * Get the value of toit
     *
     * @return the value of toit
     */
    public Toit getToit() {
        return toit;
    }

   

    /**
     * Get the value of hayon
     *
     * @return the value of hayon
     */
    public Hayon getHayon() {
        return hayon;
    }

    


    /**
     * Get the value of plancher
     *
     * @return the value of plancher
     */
    public Plancher getPlancher() {
        return plancher;
    }

    


    /**
     * Get the value of murLateral
     *
     * @return the value of murLateral
     */
    public MurLateral getMurLateral() {
        return murLateral;
    }

   

    /**
     * Get the value of monProfil
     *
     * @return the value of monProfil
     */
    public Profil getProfil() {
        return monProfil;
    }

    void setToit(Toit toit) {
        this.toit = toit;
    }
     public Polygon getShapeProfilBezier() { 
        return this.monProfilBezier.getShape();
     }
    

    public Polygon getShapeMurInteterieur() {
     
        Polygon polygonProfil = new Polygon();
      
       Point PCR0=monProfil.getCentreRayonSuperieurGauche();//donné
       Point PCR1=monProfil.getCentreRayonSuperieurDroite();//donné
       Point PCR2=monProfil.getCentreRayonInferieurDroite();//donné
       Point PCR3=monProfil.getCentreRayonInferieurGauche();//donné
    
        Point Position0 = monProfil.getPositionCadran2();
        Point Position1 =monProfil.getPositionCadran1();
        Point Position2 =monProfil.getPositionCadran4();
        
        
        List<Point> arcExterieur= this.getHayon().getArcInterieur();
      
        Point nextPoint = this.getHayon().getPositionCadran4();
        
        nextPoint = new Point(nextPoint.x +(int)this.getHayon().getEpaisseurTraitScie(), nextPoint.y);
        
       // Second Cadran. 
       // premier point
        int epaisseurandTraitScie = (int)(this.getHayon().getEpaisseur()+this.getHayon().getEpaisseurTraitScie());
         // First Cadran.  
       Point newPosition0 = new Point(Position0.x+epaisseurandTraitScie, Position0.y+epaisseurandTraitScie);
       Point newPCR0 = new Point(this.getHayon().getPositionCadran1().x+(int)this.getHayon().getEpaisseurTraitScie(), PCR0.y+(int)this.getHayon().getEpaisseurTraitScie());
        
       // premier point
        Point firstPoint1= new Point(newPosition0.x, newPCR0.y); 
        Point lastPoint1= new Point(newPCR0.x, newPosition0.y);//donné
        polygonProfil.addPoint((int)(firstPoint1.x), (int)(firstPoint1.y));

        // autre point
        
          for(Point point :Util.coordonneeDeuxiemeCadran(newPosition0,newPCR0,0.0025 ))
          polygonProfil.addPoint(point.x, (int)(point.y));
       
       
        // dermier point
        polygonProfil.addPoint((int)(lastPoint1.x), (int)(lastPoint1.y));
        
        // Coin arc hayon
       Point newPosition2 =new Point(lastPoint1.x+(int)(this.getHayon().getRayonArcSuperieur()+this.getHayon().getRayonArcSuperieur()),lastPoint1.y+(int)this.getHayon().getRayonArcSuperieur()); 
       Point newPCR2=new Point(lastPoint1.x+(int)this.getHayon().getRayonArcSuperieur(), Position0.y+(int)this.getHayon().getRayonArcSuperieur());
       
       
       //Fouth Cadran
        // premier point
        polygonProfil.addPoint(lastPoint1.x+(int)this.getHayon().getEpaisseurTraitScie(), (int)(lastPoint1.y));
        // autre point
        ArrayList<Point> newPoints2 =Util.coordonneeQuatriemeCadran(newPosition2,newPCR2,0.0025 );
        for(int i = newPoints2.size()-1 ; i>=0; i--)
        {
         Point point =newPoints2.get(i);
         // polygonProfil.addPoint(point.x, (int)(point.y));
         }

        // dermier point
       polygonProfil.addPoint((int)((lastPoint1.x+(int)this.getHayon().getRayonArcSuperieur())), (int)(Position0.y));
       
        //------------------------------------
        
        //
        //Poutre 
         polygonProfil.addPoint(this.getPoutreArriere().getPositionCadran2().x, this.getPoutreArriere().getPositionCadran2().y);
         polygonProfil.addPoint(this.getPoutreArriere().getPositionCadran3().x, this.getPoutreArriere().getPositionCadran3().y);
         polygonProfil.addPoint(this.getPoutreArriere().getPositionCadran4().x, this.getPoutreArriere().getPositionCadran4().y);
         polygonProfil.addPoint(this.getPoutreArriere().getPositionCadran1().x, this.getPoutreArriere().getPositionCadran1().y);
        // Mur sepateur
        
        if(this.getMurSeparateur().Visible())
        {
            polygonProfil.addPoint(this.getMurSeparateur().getPositionCadran2().x, this.getMurSeparateur().getPositionCadran2().y);
            polygonProfil.addPoint(this.getMurSeparateur().getPositionCadran3().x, this.getMurSeparateur().getPositionCadran3().y);
            polygonProfil.addPoint(this.getMurSeparateur().getPositionCadran4().x, this.getMurSeparateur().getPositionCadran4().y);
            polygonProfil.addPoint(this.getMurSeparateur().getPositionCadran1().x, this.getMurSeparateur().getPositionCadran1().y);
        }
                
       // first point toit
      
      // First Cadran.    
       // premier point
        polygonProfil.addPoint((int)(PCR1.x), (int)(Position0.y));
        polygonProfil.addPoint(this.getToit().getPositionCadran2().x, this.getToit().getPositionCadran2().y);
        
        
        List<Point> pointIntToit = this.getToit().getArcInterieur();
        for(int i = pointIntToit.size()-1 ; i>=0; i--)
        {
         Point point =pointIntToit.get(i);
          polygonProfil.addPoint(point.x, (int)(point.y));
         }

        
        polygonProfil.addPoint(this.getToit().getPositionCadran3().x, this.getToit().getPositionCadran3().y);
        
        polygonProfil.addPoint(this.getToit().getPositionCadran4().x, this.getToit().getPositionCadran4().y);
       
       

        // dermier point
        polygonProfil.addPoint((int)(PCR2.x), (int)(Position2.y)); 
  
      //third Cadran.
        
       
        // fin plancher
        polygonProfil.addPoint(this.getPlancher().getPositionCadran4().x, this.getPlancher().getPositionCadran4().y);
        polygonProfil.addPoint(this.getPlancher().getPositionCadran1().x, this.getPlancher().getPositionCadran1().y);
        polygonProfil.addPoint(this.getPlancher().getPositionCadran2().x, this.getPlancher().getPositionCadran2().y);
        polygonProfil.addPoint(this.getPlancher().getPositionCadran3().x, this.getPlancher().getPositionCadran3().y);
        // fin distance plancher
       // polygonProfil.addPoint(this.getPlancher().getPositionCadran3().x-(int)this.getHayon().getDistancePlancher(), this.getPlancher().getPositionCadran3().y);
        Point t = this.getHayon().getPositionCadran3();
        //polygonProfil.addPoint(this.getHayon().getPositionCadran3().x+(int)this.getHayon().getEpaisseurTraitScie(), this.getHayon().getPositionCadran3().y);
        //polygonProfil.addPoint(this.getHayon().getPositionCadran3().x+(int)this.getHayon().getEpaisseurTraitScie(), this.getHayon().getPositionCadran3().y-(int)this.getHayon().getEpaisseurTraitScie());
         Point t2 = monProfil.getPositionCadran3();
        //
       
        Point newPosition3 = new Point(monProfil.getPositionCadran3().x+epaisseurandTraitScie, monProfil.getPositionCadran3().y-epaisseurandTraitScie);
        PCR3 = new Point(PCR3.x , PCR3.y);
        
        
        
        // Point Hayon
        Point newFirstPoint = new Point(PCR3.x, newPosition3.y);
        
        polygonProfil.addPoint(newFirstPoint.x, monProfil.getPositionCadran3().y);
        polygonProfil.addPoint(newFirstPoint.x, (int)(newFirstPoint.y));
        
        polygonProfil.addPoint(newFirstPoint.x, (int)(newFirstPoint.y));

        // autre point
        for(Point point :Util.coordonneeTroisiemeCadran(newPosition3,PCR3,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(newPosition3.x), (int)(PCR3.y));
        
        // sauvegarder le polygon
        this.getMurLateral().getCoucheInterieur().setShape(polygonProfil);
        this.getMurLateral().getSquelette().setShape(polygonProfil);
        
        return polygonProfil;
        
    }
    
    
    public Polygon getShapeMurExterieur() {
      
       
        Polygon polygonProfil = new Polygon();
      
       Point PCR0=monProfil.getCentreRayonSuperieurGauche();//donné
       Point PCR1=monProfil.getCentreRayonSuperieurDroite();//donné
       Point PCR2=monProfil.getCentreRayonInferieurDroite();//donné
       Point PCR3=monProfil.getCentreRayonInferieurGauche();//donné
    
        Point Position0 = monProfil.getPositionCadran2();
        Point Position1 =monProfil.getPositionCadran1();
        Point Position2 =monProfil.getPositionCadran4();
        
        
        List<Point> arcExterieur= this.getHayon().getArcInterieur();
      
        Point nextPoint = this.getHayon().getPositionCadran4();
        
        nextPoint = new Point(nextPoint.x +(int)this.getHayon().getEpaisseurTraitScie(), nextPoint.y);
        
       // Second Cadran. 
       // premier point
        int epaisseurandTraitScie = (int)(this.getHayon().getEpaisseur()+this.getHayon().getEpaisseurTraitScie());
         // First Cadran.  
       Point newPosition0 = new Point(Position0.x+epaisseurandTraitScie, Position0.y+epaisseurandTraitScie);
       Point newPCR0 = new Point(this.getHayon().getPositionCadran1().x+(int)this.getHayon().getEpaisseurTraitScie(), PCR0.y+(int)this.getHayon().getEpaisseurTraitScie());
        
       // premier point
        Point firstPoint1= new Point(newPosition0.x, newPCR0.y); 
        Point lastPoint1= new Point(newPCR0.x, newPosition0.y);//donné
        polygonProfil.addPoint((int)(firstPoint1.x), (int)(firstPoint1.y));

        // autre point
        
          for(Point point :Util.coordonneeDeuxiemeCadran(newPosition0,newPCR0,0.0025 ))
          polygonProfil.addPoint(point.x, (int)(point.y));
       
       
        // dermier point
        polygonProfil.addPoint((int)(lastPoint1.x), (int)(lastPoint1.y));
        
        // Coin arc hayon
       Point newPosition2 =new Point(lastPoint1.x+(int)(this.getHayon().getRayonArcSuperieur()+this.getHayon().getRayonArcSuperieur()),lastPoint1.y+(int)this.getHayon().getRayonArcSuperieur()); 
       Point newPCR2=new Point(lastPoint1.x+(int)this.getHayon().getRayonArcSuperieur(), Position0.y+(int)this.getHayon().getRayonArcSuperieur());
       
       
       //Fouth Cadran
        // premier point
        polygonProfil.addPoint((int)(lastPoint1.x+this.getHayon().getRayonArcSuperieur()), (int)(lastPoint1.y));
        // autre point
        ArrayList<Point> newPoints2 =Util.coordonneeQuatriemeCadran(newPosition2,newPCR2,0.0025 );
        for(int i = newPoints2.size()-1 ; i>=0; i--)
        {
         Point point =newPoints2.get(i);
         // polygonProfil.addPoint(point.x, (int)(point.y));
         }

        // dermier point
       polygonProfil.addPoint((int)((lastPoint1.x+(int)this.getHayon().getRayonArcSuperieur())), (int)(Position0.y));
       
        //------------------------------------
        
      
      
      // First Cadran.    
       // premier point
        polygonProfil.addPoint((int)(PCR1.x), (int)(Position0.y));

        // autre point
        for(Point point :Util.coordonneePremierCadran(Position1,PCR1,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(Position1.x), (int)(PCR1.y));
        
      
       //Fouth Cadran
        // premier point
        polygonProfil.addPoint((int)(Position2.x), (int)(PCR2.y));

        // autre point
        for(Point point :Util.coordonneeQuatriemeCadran(Position2,PCR2,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(PCR2.x), (int)(Position2.y)); 
  
      //third Cadran.
        
       
        // fin plancher
        //polygonProfil.addPoint(this.getPlancher().getPositionCadran3().x, this.getPlancher().getPositionCadran3().y);
        
        // fin distance plancher
       // polygonProfil.addPoint(this.getPlancher().getPositionCadran3().x-(int)this.getHayon().getDistancePlancher(), this.getPlancher().getPositionCadran3().y);
        Point t = this.getHayon().getPositionCadran3();
        //polygonProfil.addPoint(this.getHayon().getPositionCadran3().x+(int)this.getHayon().getEpaisseurTraitScie(), this.getHayon().getPositionCadran3().y);
        //polygonProfil.addPoint(this.getHayon().getPositionCadran3().x+(int)this.getHayon().getEpaisseurTraitScie(), this.getHayon().getPositionCadran3().y-(int)this.getHayon().getEpaisseurTraitScie());
         Point t2 = monProfil.getPositionCadran3();
        //
       
        Point newPosition3 = new Point(monProfil.getPositionCadran3().x+epaisseurandTraitScie, monProfil.getPositionCadran3().y-epaisseurandTraitScie);
        PCR3 = new Point(PCR3.x , PCR3.y);
        
        
        
        // Point Hayon
        Point newFirstPoint = new Point(PCR3.x, newPosition3.y);
        
        polygonProfil.addPoint(newFirstPoint.x, monProfil.getPositionCadran3().y);
        polygonProfil.addPoint(newFirstPoint.x, (int)(newFirstPoint.y));
        
        polygonProfil.addPoint(newFirstPoint.x, (int)(newFirstPoint.y));

        // autre point
        for(Point point :Util.coordonneeTroisiemeCadran(newPosition3,PCR3,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(newPosition3.x), (int)(PCR3.y));
      
        // sauvegarder le polygon
        this.getMurLateral().getCoucheExterieur().setShape(polygonProfil);
        
        return polygonProfil;
        
    }
    
     public Polygon getShapeMurExterieurV2() {
      
       
        Polygon polygonProfil = new Polygon();
      
       Point PCR0=monProfil.getCentreRayonSuperieurGauche();//donné
       Point PCR1=monProfil.getCentreRayonSuperieurDroite();//donné
       Point PCR2=monProfil.getCentreRayonInferieurDroite();//donné
       Point PCR3=monProfil.getCentreRayonInferieurGauche();//donné
    
        Point Position0 = monProfil.getPositionCadran2();
        Point Position1 =monProfil.getPositionCadran1();
        Point Position2 =monProfil.getPositionCadran4();
        
        int epaisseurandTraitScie = (int)(this.getHayon().getEpaisseur()+this.getHayon().getEpaisseurTraitScie());
        
        List<Point> arcInterieurHayon= this.getHayon().getArcInterieur();
        for(int i = arcInterieurHayon.size()-1 ; i>=0; i--)
        {
             Point point =arcInterieurHayon.get(i);
             polygonProfil.addPoint(point.x+(int)this.getHayon().getEpaisseurTraitScie(), (int)(point.y));
        }
        
        Point nextPoint = this.getHayon().getPositionCadran4();
        
        nextPoint = new Point(nextPoint.x +(int)this.getHayon().getEpaisseurTraitScie(), nextPoint.y);
        
       
      
      
      // First Cadran.    
       // premier point
        polygonProfil.addPoint((int)(PCR1.x), (int)(Position0.y));

        // autre point
        for(Point point :Util.coordonneePremierCadran(Position1,PCR1,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(Position1.x), (int)(PCR1.y));
        
      
       //Fouth Cadran
        // premier point
        polygonProfil.addPoint((int)(Position2.x), (int)(PCR2.y));

        // autre point
        for(Point point :Util.coordonneeQuatriemeCadran(Position2,PCR2,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(PCR2.x), (int)(Position2.y)); 
  
      //third Cadran.
        
       
        // fin plancher
        //polygonProfil.addPoint(this.getPlancher().getPositionCadran3().x, this.getPlancher().getPositionCadran3().y);
        
        // fin distance plancher
       // polygonProfil.addPoint(this.getPlancher().getPositionCadran3().x-(int)this.getHayon().getDistancePlancher(), this.getPlancher().getPositionCadran3().y);
        Point t = this.getHayon().getPositionCadran3();
        //polygonProfil.addPoint(this.getHayon().getPositionCadran3().x+(int)this.getHayon().getEpaisseurTraitScie(), this.getHayon().getPositionCadran3().y);
        //polygonProfil.addPoint(this.getHayon().getPositionCadran3().x+(int)this.getHayon().getEpaisseurTraitScie(), this.getHayon().getPositionCadran3().y-(int)this.getHayon().getEpaisseurTraitScie());
         Point t2 = monProfil.getPositionCadran3();
        //
       
        Point newPosition3 = new Point(monProfil.getPositionCadran3().x+epaisseurandTraitScie, monProfil.getPositionCadran3().y-epaisseurandTraitScie);
        PCR3 = new Point(PCR3.x , PCR3.y);
        
        
        
        // Point Hayon
        Point newFirstPoint = new Point(PCR3.x, newPosition3.y);
        
        
        // sauvegarder le polygon
        this.getMurLateral().getCoucheExterieur().setShape(polygonProfil);
        
      
        return polygonProfil;
        
    }
    
    public Polygon getShapeHayonV2() {
         
      
       Polygon polygonHayon = new Polygon();
      
       Point PCR0=monProfil.getCentreRayonSuperieurGauche();//donné
  
       Point PCR3=monProfil.getCentreRayonInferieurGauche();//donné
    
        Point Position0 = monProfil.getPositionCadran2();
       
        List<Point> arcExterieur= new ArrayList<Point>();
        List<Point> arcInterieur= new ArrayList<Point>();
        Point nextPoint;
        int distancePlancher =(int)this.getHayon().getDistancePlancher();
        //third Cadran.
       // premier point
        PCR3 = new Point(PCR3.x -(int)this.getHayon().getEpaisseurTraitScie(), PCR3.y);
        
        // set point 3
        Point positionCadran3 = new Point(PCR3.x, this.getProfil().getPositionCadran3().y);
        this.getHayon().setPositionCadran3(positionCadran3);
        
        // frist
     
        polygonHayon.addPoint(positionCadran3.x-distancePlancher, positionCadran3.y);
        arcExterieur.add(positionCadran3);
     
        // autre point
        for(Point point :Util.coordonneeTroisiemeCadran(monProfil.getPositionCadran3(),PCR3,0.0025 ))
        {
          if(point.x + distancePlancher <= PCR3.x ){
            polygonHayon.addPoint(point.x, (int)(point.y));
            arcExterieur.add(point);
          }
        }
        // dermier point
       
        nextPoint = new Point(monProfil.getPositionCadran3().x, PCR3.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
      
         int distancePoutreArriere =(int)this.getHayon().getDistancePoutreArriere();
         // Second Cadran. 
       // premier point
        PCR0 =new Point(this.getPoutreArriere().getPositionCadran2().x -(int)(this.getHayon().getEpaisseurTraitScie()), PCR0.y);
       
        nextPoint = new Point(Position0.x, PCR0.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
        
        // autre point
        for(Point point :Util.coordonneeDeuxiemeCadran(Position0,PCR0,0.0025 ))
        {
            if(point.x + distancePoutreArriere <= PCR0.x ){
            polygonHayon.addPoint(point.x, (int)(point.y));
            arcExterieur.add(point);
            }
        }
        // dermier point
        nextPoint = new Point(PCR0.x-distancePoutreArriere, Position0.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
        this.getHayon().setPositionCadran2(nextPoint);
       // Coin Hayon.
       
        // to to arc superieur
       Point newPCR2=new Point(PCR0.x-(int)this.getHayon().getRayonArcSuperieur(), Position0.y);
       Point newPosition2 =new Point(PCR0.x,Position0.y+(int)this.getHayon().getEpaisseur());
       
       //Fouth Cadran
        // premier point
        nextPoint = new Point(PCR0.x-distancePoutreArriere, Position0.y);
        arcInterieur.add(nextPoint);

        // autre point
        for(Point point :Util.coordonneeQuatriemeCadran(newPosition2,newPCR2,0.0025 ))
        {
           polygonHayon.addPoint(point.x-distancePoutreArriere, (int)(point.y));
           arcInterieur.add(point);
        }
        
        // dermier point
        nextPoint = new Point(newPCR2.x-distancePoutreArriere, newPosition2.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y); 
        arcInterieur.add(nextPoint);
        this.getHayon().setPositionCadran1(nextPoint);
      
      // First Cadran.  
       Point newPosition = new Point(Position0.x+(int)this.getHayon().getEpaisseur(), Position0.y+(int)this.getHayon().getEpaisseur());
       Point newPCR0 = new Point(PCR0.x-(int)this.getHayon().getRayonArcSuperieur(), PCR0.y);
        
       // premier point
        nextPoint= new Point(newPCR0.x, newPosition.y);//donné
        
        polygonHayon.addPoint(nextPoint.x-distancePoutreArriere, nextPoint.y);
        arcInterieur.add(nextPoint);
        
        // autre point
         // autre point
        ArrayList<Point> newPoints2 =Util.coordonneeDeuxiemeCadran(newPosition,newPCR0,0.0025 );
       for(int i = newPoints2.size()-1 ; i>=0; i--)
        {
           
           Point point =newPoints2.get(i);
           if( point.x+distancePoutreArriere < newPCR0.x )
           {
            polygonHayon.addPoint(point.x, (int)(point.y));
            arcInterieur.add(point); 
           }
         }
       
        // dermier point
        nextPoint=new Point(newPosition.x, newPCR0.y); 
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
       
       //Fouth Cadran
       
        Point newPosition3 = new Point(getProfil().getPositionCadran3().x+(int)this.getHayon().getEpaisseur(), this.getProfil().getPositionCadran3().y-(int)this.getHayon().getEpaisseur());
       
       
         // premier point
        nextPoint=new Point(this.getProfil().getPositionCadran3().x+(int)this.getHayon().getEpaisseur(),  PCR3.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
       
        // autre point
        List<Point> newPoints =Util.coordonneeTroisiemeCadran(newPosition3,PCR3,0.0025 );
       
        for(int i = newPoints.size()-1 ; i>=0; i--)
        {
           Point point =newPoints.get(i);
           if(point.x + distancePlancher<= PCR3.x){
          polygonHayon.addPoint(point.x, (int)(point.y));
           arcInterieur.add(point); 
           }
        }
        // dermier point
        nextPoint=new Point(PCR3.x-distancePlancher, this.getProfil().getPositionCadran3().y-(int)this.getHayon().getEpaisseur());//donné
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint); 
        this.getHayon().setPositionCadran4(nextPoint);
        
        
        // save setting
        this.getHayon().setArcExterieur(arcExterieur);
        this.getHayon().setArcInterieur(arcInterieur);
      
        
        // sauvegarder le polygon
        this.getHayon().setShape(polygonHayon);
        
        
        return polygonHayon;
        
    }
    
    public Polygon getShapeHayon() {
         
      
       Polygon polygonHayon = new Polygon();
      
       Point PCR0=monProfil.getCentreRayonSuperieurGauche();//donné
  
       Point PCR3=monProfil.getCentreRayonInferieurGauche();//donné
    
        Point Position0 = monProfil.getPositionCadran2();
       
        List<Point> arcExterieur= new ArrayList<Point>();
        List<Point> arcInterieur= new ArrayList<Point>();
        Point nextPoint;
        //third Cadran.
       // premier point
        PCR3 = new Point(PCR3.x -(int)(this.getHayon().getDistancePlancher()+this.getHayon().getEpaisseurTraitScie()), PCR3.y);
        
        // set point 3
        Point positionCadran3 = new Point(PCR3.x, this.getProfil().getPositionCadran3().y);
        this.getHayon().setPositionCadran3(positionCadran3);
        
        // frist
        polygonHayon.addPoint(positionCadran3.x, positionCadran3.y);
        arcExterieur.add(positionCadran3);
        // autre point
        for(Point point :Util.coordonneeTroisiemeCadran(monProfil.getPositionCadran3(),PCR3,0.0025 ))
        {
          polygonHayon.addPoint(point.x, (int)(point.y));
          arcExterieur.add(point);
        }
        // dermier point
        nextPoint = new Point(monProfil.getPositionCadran3().x, PCR3.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
        
        
         // Second Cadran. 
       // premier point
        PCR0 =new Point(this.getPoutreArriere().getPositionCadran2().x -(int)(this.getHayon().getDistancePoutreArriere()+this.getHayon().getEpaisseurTraitScie()), PCR0.y);
       
        nextPoint = new Point(Position0.x, PCR0.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
        
        // autre point
        for(Point point :Util.coordonneeDeuxiemeCadran(Position0,PCR0,0.0025 ))
        {
           polygonHayon.addPoint(point.x, (int)(point.y));
           arcExterieur.add(point);
        }
        // dermier point
        nextPoint = new Point(PCR0.x, Position0.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
        this.getHayon().setPositionCadran2(nextPoint);
       // Coin Hayon.
       
        // to to arc superieur
       Point newPCR2=new Point(PCR0.x-(int)this.getHayon().getRayonArcSuperieur(), Position0.y);
       Point newPosition2 =new Point(PCR0.x,Position0.y+(int)this.getHayon().getEpaisseur());
       
       //Fouth Cadran
        // premier point
        nextPoint = new Point(PCR0.x, Position0.y);
        arcInterieur.add(nextPoint);

        // autre point
        for(Point point :Util.coordonneeQuatriemeCadran(newPosition2,newPCR2,0.0025 ))
        {
           polygonHayon.addPoint(point.x, (int)(point.y));
           arcInterieur.add(point);
        }
        
        // dermier point
        nextPoint = new Point(newPCR2.x, newPosition2.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y); 
        arcInterieur.add(nextPoint);
        this.getHayon().setPositionCadran1(nextPoint);
      
      // First Cadran.  
       Point newPosition = new Point(Position0.x+(int)this.getHayon().getEpaisseur(), Position0.y+(int)this.getHayon().getEpaisseur());
       Point newPCR0 = new Point(PCR0.x-(int)this.getHayon().getRayonArcSuperieur(), PCR0.y);
        
       // premier point
        nextPoint= new Point(newPCR0.x, newPosition.y);//donné
        
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
        
        // autre point
         // autre point
        ArrayList<Point> newPoints2 =Util.coordonneeDeuxiemeCadran(newPosition,newPCR0,0.0025 );
       for(int i = newPoints2.size()-1 ; i>=0; i--)
        {
           Point point =newPoints2.get(i);
           polygonHayon.addPoint(point.x, (int)(point.y));
           arcInterieur.add(point); 
         }
       
        // dermier point
        nextPoint=new Point(newPosition.x, newPCR0.y); 
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
       
       //Fouth Cadran
       
        Point newPosition3 = new Point(getProfil().getPositionCadran3().x+(int)this.getHayon().getEpaisseur(), this.getProfil().getPositionCadran3().y-(int)this.getHayon().getEpaisseur());
       
       
         // premier point
        nextPoint=new Point(this.getProfil().getPositionCadran3().x+(int)this.getHayon().getEpaisseur(),  PCR3.y);
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
       
        // autre point
        List<Point> newPoints =Util.coordonneeTroisiemeCadran(newPosition3,PCR3,0.0025 );
       
        for(int i = newPoints.size()-1 ; i>=0; i--)
        {
           Point point =newPoints.get(i);
          polygonHayon.addPoint(point.x, (int)(point.y));
           arcInterieur.add(point); 
        }
        // dermier point
        nextPoint=new Point(PCR3.x, this.getProfil().getPositionCadran3().y-(int)this.getHayon().getEpaisseur());//donné
        polygonHayon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint); 
        this.getHayon().setPositionCadran4(nextPoint);
        
        
        // save setting
        this.getHayon().setArcExterieur(arcExterieur);
        this.getHayon().setArcInterieur(arcInterieur);
      
          
        // sauvegarder le polygon
        this.getHayon().setShape(polygonHayon);
        
        
        return polygonHayon;
        
    }
    
    public Polygon getShapePlancher() {
        
       Point PCR2=monProfil.getCentreRayonInferieurDroite();//donné
       Point PCR3=monProfil.getCentreRayonInferieurGauche();//donné
       
     
       getPlancher().setPositionCadran3(new Point( PCR3.x+(int)getPlancher().getMargeArriere(), monProfil.getPositionCadran3().y));
       getPlancher().resetPosition();
        
        Point PositionInit =getPlancher().getPositionCadran3();
        Point secondPosition =new Point(PositionInit.x,PositionInit.y-(int)getPlancher().getHauteur());
        Point thirdPosition =new Point(PCR2.x -(int)getPlancher().getMargeAvant() ,secondPosition.y);
        Point lastPosition1 =new Point(thirdPosition.x,getPlancher().getPositionCadran3().y);
       
        getPlancher().setPositionCadran2(secondPosition);
        getPlancher().setPositionCadran1(thirdPosition);
        getPlancher().setPositionCadran4(lastPosition1);
        //construction du polygone de monProfil
        Polygon newPolygon=new Polygon();
        newPolygon.addPoint((int)(PositionInit.x), (int)(PositionInit.y));
        newPolygon.addPoint((int)(secondPosition.x), (int)(secondPosition.y));
        newPolygon.addPoint((int)(thirdPosition.x), (int)(thirdPosition.y));
        newPolygon.addPoint((int)(lastPosition1.x), (int)(lastPosition1.y));
        
        // recalculer la huteur du mur lateral
        getMurSeparateur().calculerHauteur(monProfil.getHauteur(), getPlancher().getHauteur());
        
          
        // sauvegarder le polygon
        this.getPlancher().setShape(newPolygon);
        
        this.getPlancher().setShapeDuPlancher(newPolygon);//pour enregistrer la forme
        //g.drawPolygon( newPolygon);   
        return newPolygon;
    }
    
    public Polygon getShapePoutreArriere() {
       
       Polygon newPolygon = new Polygon();
      
        Point Position1 =new Point(monProfil.getPositionCadran2().x+(int)monProfil.getLongueur(),monProfil.getPositionCadran2().y);
        if(getPoutreArriere().getPositionCadran2().x<monProfil.getPositionCadran2().x || (int)(getPoutreArriere().getPositionCadran2().x+getPoutreArriere().getHauteur()) >Position1.x)
            getPoutreArriere().setPositionCadran2(new Point(monProfil.getPositionCadran2().x, monProfil.getCentreRayonSuperieurGauche().y));
        
       // Second Cadran. 
       // premier point
        Point firstPosition =getPoutreArriere().getPositionCadran2();
        Point secondPosition =new Point(firstPosition.x+(int)getPoutreArriere().getLongueur(),firstPosition.y);
        Point thirdPosition =new Point(secondPosition.x,firstPosition.y+(int)getPoutreArriere().getHauteur());
        Point lastPosition1 =new Point(firstPosition.x,thirdPosition.y);
       
        // update position
        getPoutreArriere().setPositionCadran2(firstPosition);
        getPoutreArriere().setPositionCadran1(secondPosition);
        getPoutreArriere().setPositionCadran4(thirdPosition);
        getPoutreArriere().setPositionCadran3(lastPosition1);
        
        newPolygon.addPoint((int)(firstPosition.x), (int)(firstPosition.y));
        newPolygon.addPoint((int)(secondPosition.x), (int)(secondPosition.y));
        newPolygon.addPoint((int)(thirdPosition.x), (int)(thirdPosition.y));
        newPolygon.addPoint((int)(lastPosition1.x), (int)(lastPosition1.y));
        
       // reset position Mur separateur
       getMurSeparateur().calculerPosition(firstPosition, getPoutreArriere().getLongueur());
       
       // sauvegarder le polygon
        this.getPoutreArriere().setShape(newPolygon);
        
        return newPolygon;
        
    }
    
    public Polygon getShapeProfil() {
     
       Polygon polygonProfil = new Polygon();
      
       Point PCR0=monProfil.getCentreRayonSuperieurGauche();//donné
       Point PCR1=monProfil.getCentreRayonSuperieurDroite();//donné
       Point PCR2=monProfil.getCentreRayonInferieurDroite();//donné
       Point PCR3=monProfil.getCentreRayonInferieurGauche();//donné
    
        Point Position0 = monProfil.getPositionCadran2();
        Point Position1 = monProfil.getPositionCadran1();;
        Point Position2 = monProfil.getPositionCadran4();;
        Point Position3 = monProfil.getPositionCadran3();;
     
       // Second Cadran. 
       // premier point
        polygonProfil.addPoint((int)(Position0.x), (int)(PCR0.y));

        // autre point
        for(Point point :Util.coordonneeDeuxiemeCadran(Position0,PCR0,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(PCR0.x), (int)(Position0.y));
      
      
      // First Cadran.    
       // premier point
        polygonProfil.addPoint((int)(PCR1.x), (int)(Position0.y));

        // autre point
        for(Point point :Util.coordonneePremierCadran(Position1,PCR1,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(Position1.x), (int)(PCR1.y));
        
      
       //Fouth Cadran
        // premier point
        polygonProfil.addPoint((int)(Position2.x), (int)(PCR2.y));

        // autre point
        for(Point point :Util.coordonneeQuatriemeCadran(Position2,PCR2,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint((int)(PCR2.x), (int)(Position2.y)); 
  
      //third Cadran.
      // premier point
        polygonProfil.addPoint(PCR3.x, (int)(Position3.y));

        // autre point
        for(Point point :Util.coordonneeTroisiemeCadran(Position3,PCR3,0.0025 ))
        polygonProfil.addPoint(point.x, (int)(point.y));

        // dermier point
        polygonProfil.addPoint(Position3.x, (int)(PCR3.y));
      
        
       // sauvegarder le polygon
        this.getProfil().setShape(polygonProfil);
        
        
        return polygonProfil;
        
    }
    
     public Polygon getShapeToit() {
         
       Polygon newPolygon = new Polygon();
      
     
       Point PCR1=monProfil.getCentreRayonSuperieurDroite();//donné
       Point PCR2=monProfil.getCentreRayonInferieurDroite();//donné
    
    
        Point Position0 = monProfil.getPositionCadran2();
        
        Point Position1 =monProfil.getPositionCadran1();
        Point Position2 = monProfil.getPositionCadran4();
      
     
       // Second Cadran. 
       // premier point
       Point premierPoint = this.getPoutreArriere().getPositionCadran1();
       
       if(this.getMurSeparateur().Visible() && this.getMurSeparateur().getPositionCadran2().x>= premierPoint.x  ) {
          premierPoint = this.getMurSeparateur().getPositionCadran1();
       }
       
        List<Point> arcExterieur= new ArrayList<Point>();
        List<Point> arcInterieur= new ArrayList<Point>();
        
        Point nextPoint = premierPoint;
        
       newPolygon.addPoint((int)(premierPoint.x), (int)(premierPoint.y));
       arcExterieur.add(nextPoint);
       this.getToit().setPositionCadran2(nextPoint);
      // First Cadran.    
       // premier point
        nextPoint = new Point(PCR1.x, Position0.y);
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
        // autre point
        for(Point point :Util.coordonneePremierCadran(Position1,PCR1,0.0025 ))
        {
            newPolygon.addPoint(point.x, (int)(point.y));
            arcExterieur.add(point);
        }
        // dermier point
        nextPoint = new Point(Position1.x, PCR1.y);
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
      
       //Fouth Cadran
        // premier point
        nextPoint = new Point(Position2.x, PCR2.y);
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
        
        // autre point
        for(Point point :Util.coordonneeQuatriemeCadran(Position2,PCR2,0.0025 ))
        {
          newPolygon.addPoint(point.x, (int)(point.y));
          arcExterieur.add(point);
        }

        // dermier point
        nextPoint = new Point(PCR2.x, Position2.y);
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcExterieur.add(nextPoint);
        this.getToit().setPositionCadran1(nextPoint);
        
        // Retour 
        double epaisseur = this.getToit().getEpaisseur();
        Point newPosition0 = new Point(PCR2.x, (int)(Position2.y-epaisseur));
        
        // Premier point
        nextPoint = new Point(newPosition0.x, newPosition0.y);
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
        this.getToit().setPositionCadran4(nextPoint);
         // autre point
        Point newPosition2 =  new Point(Position2.x-(int)(epaisseur), Position2.y-(int)epaisseur);
        List<Point> newPoints =Util.coordonneeQuatriemeCadran(newPosition2, PCR2,0.0025 );
       
        for(int i = newPoints.size()-1 ; i>=0; i--)
        {
           Point point =newPoints.get(i);
           newPolygon.addPoint(point.x, (int)(point.y));
           arcInterieur.add(point);
        }
        
        // dernier point
        nextPoint = new Point(newPosition2.x, PCR2.y);
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
        
      //third Cadran.
      // premier point
        Point newPosition1 =  new Point(Position1.x-(int)(epaisseur), Position1.y+(int)epaisseur);
     
        nextPoint = new Point(newPosition1.x, PCR1.y);
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);

        // autre point
       
       List<Point> newPoints0 = Util.coordonneePremierCadran(newPosition1 ,PCR1,0.0025 );
       
        for(int i = newPoints0.size()-1 ; i>=0; i--)
        {
           Point point =newPoints0.get(i);
           newPolygon.addPoint(point.x, (int)(point.y));
           arcInterieur.add(point);
        }

        // dermier point
        nextPoint = new Point(PCR1.x, (int)(Position0.y+epaisseur));
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
        
        // dernier et dernier point
        nextPoint = new Point(premierPoint.x, (int)(premierPoint.y+epaisseur));
        newPolygon.addPoint(nextPoint.x, nextPoint.y);
        arcInterieur.add(nextPoint);
        this.getToit().setPositionCadran3(nextPoint);
        
        
        // save points
        this.getToit().setArcExterieur(arcExterieur);
        this.getToit().setArcInterieur(arcInterieur);
        
         // sauvegarder le polygon
        this.getProfil().setShape(newPolygon);
        
        return newPolygon;
        
    }

      public Polygon getShapeMurSeparateur() {
       
        
        Point Position1 =new Point(this.getMurSeparateur().getPositionCadran2().x+(int)this.getMurSeparateur().getEpaisseur(),this.getMurSeparateur().getPositionCadran2().y);
        Point Position2 =new Point(this.getMurSeparateur().getPositionCadran2().x+(int)this.getMurSeparateur().getEpaisseur(),this.getMurSeparateur().getPositionCadran2().y+(int)this.getMurSeparateur().getHauteur());
        Point Position3 =new Point(this.getMurSeparateur().getPositionCadran2().x,this.getMurSeparateur().getPositionCadran2().y+(int)(int)this.getMurSeparateur().getHauteur());
       
        //construction du polygone de profil
        Polygon newPolygon=new Polygon();
        newPolygon.addPoint(this.getMurSeparateur().getPositionCadran2().x, this.getMurSeparateur().getPositionCadran2().y);
        newPolygon.addPoint(Position1.x, Position1.y);
        newPolygon.addPoint(Position2.x, Position2.y);
        newPolygon.addPoint(Position3.x, Position3.y);
        
        this.getMurSeparateur().setPositionCadran1(Position1);
        this.getMurSeparateur().setPositionCadran4(Position2);
        this.getMurSeparateur().setPositionCadran3(Position3);
        
       this.getMurSeparateur().setShape(newPolygon);
        //g.drawPolygon( polygonProfil);   
        return newPolygon;
    
    }
   
     
      public void UpdadeConstituant(MicroRoulotte.InputType inputType, String text, double inputValeurDecimal) throws Exception {
        inputValeurDecimal=  inputValeurDecimal;
        
      
        
        //  double inputDouble = 
        switch (inputType) {
            // Profil
            case PROFIL_LONGUEUR:
                this.getProfil().setLongueur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.PROFIL_LONGUEUR).put(text, inputValeurDecimal);
                break;
            case PROFIL_HAUTEUR:
                this.getProfil().setHauteur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.PROFIL_HAUTEUR).put(text, inputValeurDecimal);
                break;

            // Plancher
            case PLANCHER_HAUTEUR:

                if (this.getProfil().getHauteur() < inputValeurDecimal * 3) {
                    throw new Exception("La hauteur du plancher depasse le 1/3 de " + this.getProfil().getHauteur());
                }
                this.getPlancher().setHauteur(inputValeurDecimal);
               this.InputConstituant.get(MicroRoulotte.InputType.PLANCHER_HAUTEUR).put(text, inputValeurDecimal);
                break;
            case PLANCHER_MARGE_ARRIERE:
                this.getPlancher().setMargeArriere(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.PLANCHER_MARGE_ARRIERE).put(text, inputValeurDecimal);;
                break;
            case PLANCHER_MARGE_AVANT:
                this.getPlancher().setMargeAvant(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.PLANCHER_MARGE_AVANT).put(text, inputValeurDecimal);
                break;

            // Poutre arriere
            case POUTRE_ARRIERE_LARGEUR:
                this.getPoutreArriere().setLongueur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.POUTRE_ARRIERE_LARGEUR).put(text, inputValeurDecimal);
                break;
            case POUTRE_ARRIERE_HAUTEUR:
                this.getPoutreArriere().setHauteur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.POUTRE_ARRIERE_HAUTEUR).put(text, inputValeurDecimal);
                break;
            case POUTRE_ARRIERE_POSITION:

                // position initial du profil
                Point pointInit = this.getProfil().getPositionCadran2();

                // verifie si la position du point est hor du profil
                if (inputValeurDecimal < pointInit.x || inputValeurDecimal > pointInit.x+this.getProfil().getLongueur()) {
                    throw new Exception("La position de la poutre est invalide. Elle ne peut pas etre hors du profil");
                }

                Point point = new Point((int) (pointInit.x + inputValeurDecimal), pointInit.y);

                this.getPoutreArriere().setPositionCadran2(point);
                this.InputConstituant.get(MicroRoulotte.InputType.POUTRE_ARRIERE_POSITION).put(text, inputValeurDecimal);
                break;

            // Hayon
            case HAYON_EPAISSEUR:
                this.getHayon().setEpaisseur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.HAYON_EPAISSEUR).put(text, inputValeurDecimal);
                break;
            case HAYON_POIDS:
                this.getHayon().setPoids(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.HAYON_POIDS).put(text, inputValeurDecimal);
                break;
            case HAYON_RAYON_ARC_SUP:
                this.getHayon().setRayonArcSuperieur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.HAYON_RAYON_ARC_SUP).put(text, inputValeurDecimal);
                break;
            case HAYON_EPAISSEUR_TRAIT_SCIE:
                this.getHayon().setEpaisseurTraitScie(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.HAYON_EPAISSEUR_TRAIT_SCIE).put(text, inputValeurDecimal);
                break;
            case HAYON_DISTANCE_POUTRE_ARRIERE:
                this.getHayon().setDistancePoutreArriere(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.HAYON_DISTANCE_POUTRE_ARRIERE).put(text, inputValeurDecimal);
                break;
            case HAYON_DISTANCE_PLANCHER:
                this.getHayon().setDistancePlancher(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.HAYON_DISTANCE_PLANCHER).put(text, inputValeurDecimal);
                break;

            //Toit
            case TOIT_EPAISSEUR:
                this.getToit().setEpaisseur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.TOIT_EPAISSEUR).put(text, inputValeurDecimal);
                break;

            case TOIT_NBRE_POUTRE_AVANT:
                //this.getToit().se
                this.InputConstituant.get(MicroRoulotte.InputType.TOIT_NBRE_POUTRE_AVANT).put(text, inputValeurDecimal);
                break;
            case TOIT_EPAISSEUR_POUTRE_AVANT:

                this.InputConstituant.get(MicroRoulotte.InputType.TOIT_EPAISSEUR_POUTRE_AVANT).put(text, inputValeurDecimal);
                break;
            case TOIT_LARGEUR_POUTRE_AVANT:

                this.InputConstituant.get(MicroRoulotte.InputType.TOIT_LARGEUR_POUTRE_AVANT).put(text, inputValeurDecimal);
                break;

            // Mur lateral
            case MUR_LATERAL_INTERIEUR:
                this.getMurLateral().getMurCoucheInterieur().setEpaisseur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.MUR_LATERAL_INTERIEUR).put(text, inputValeurDecimal);
                break;
            case MUR_LATERAL_SQUELETTE:
                this.getMurLateral().getMurCoucheSequelette().setEpaisseur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.MUR_LATERAL_SQUELETTE).put(text, inputValeurDecimal);
                break;
            case MUR_LATERAL_EXTERIEUR:
                this.getMurLateral().getMurCoucheExterieur().setEpaisseur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.MUR_LATERAL_EXTERIEUR).put(text, inputValeurDecimal);
                break;

            // Porte
            case PORTE_HAUTEUR:
                    this.getMurLateral().getPorte().setHauteur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.PORTE_HAUTEUR).put(text, inputValeurDecimal);
                break;
            case PORTE_LARGEUR:
                 this.getMurLateral().getPorte().setLongueur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.PORTE_LARGEUR).put(text, inputValeurDecimal);
                break;
            case PORTE_POSITION:
                //setPositionCadran2(new Point((int)inputValeurDecimal, this.getProfil().getPositionCadran3().y/2))
                
                Point newPoint = new Point((int) inputValeurDecimal, (int)this.getProfil().getPositionCadran3().y/2);
                this.getMurLateral().getPorte().setPositionCadran2(newPoint);
                this.InputConstituant.get(MicroRoulotte.InputType.PORTE_POSITION).put(text, inputValeurDecimal);
                break;
            case PORTE_RAYON_ARC:
                this.getMurLateral().getPorte().setRayonArcCote(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.PORTE_RAYON_ARC).put(text, inputValeurDecimal);
                break;

            // Fenetre
            case FENETRE_HAUTEUR:
                this.getMurLateral().getFenetre().setHauteur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.FENETRE_HAUTEUR).put(text, inputValeurDecimal);
                break;
            case FENETRE_LARGEUR:
                this.getMurLateral().getFenetre().setLongueur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.FENETRE_LARGEUR).put(text, inputValeurDecimal);
                break;
            case FENETRE_POSITION:
                this.getMurLateral().getFenetre().setPositionCadran2(new Point((int)inputValeurDecimal, this.getProfil().getPositionCadran3().y/2));
                this.InputConstituant.get(MicroRoulotte.InputType.FENETRE_POSITION).put(text, inputValeurDecimal);
                break;
            case FENETRE_RAYON_ARC:
                this.getMurLateral().getFenetre().setRayonArcCote(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.FENETRE_RAYON_ARC).put(text, inputValeurDecimal);
                break;

            // Mur séparateur
            case MUR_SEPARATEUR_EPAISSEUR:
                this.getMurSeparateur().setEpaisseur(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.MUR_SEPARATEUR_EPAISSEUR).put(text, inputValeurDecimal);
                break;
            case MUR_SEPARATEUR_DISTANCE_PLACHER:
                this.getMurSeparateur().setDistanceDuPlancher(inputValeurDecimal);
                
               this.InputConstituant.get(MicroRoulotte.InputType.MUR_SEPARATEUR_DISTANCE_PLACHER).put(text, inputValeurDecimal);
                break;
            case MUR_SEPARATEUR_DISTANCE_POUTRE_ARRIERE:
                this.getMurSeparateur().setDistancePoutreArriere(inputValeurDecimal);
                this.InputConstituant.get(MicroRoulotte.InputType.MUR_SEPARATEUR_DISTANCE_POUTRE_ARRIERE).put(text, inputValeurDecimal);
                break;

        }

    }

       
   
}

