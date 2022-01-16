/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.Domaine.MicroRoulotte;
import ca.ulaval.glo2004.Domaine.MicroRoulotte.InputType;
import ca.ulaval.glo2004.Domaine.MicroRoulotteControleur;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Solution
 */
public class MainWindow extends javax.swing.JFrame {

    
    public final MicroRoulotteControleur microRoulotteControleur ; 
    
    private Point pointInit = new Point(50,200);
    private ButtonGroup CMorInches = new ButtonGroup();
   
    
    
    
    
    /**
     * Creates new form design
     */
    public MainWindow() {
        microRoulotteControleur  = new MicroRoulotteControleur();
        initComponents();
        initControlleur();
        CMorInches.add(jRadioButtonMenuItem2);
        CMorInches.add(jRadioButtonMenuItem1);
       
    }
     private void initControlleur(){
         
         try{
             this.microRoulotteControleur.capterUndoRedo = false;
             this.jCheckBoxMenuItemCapterUndoRedo.setSelected(this.microRoulotteControleur.capterUndoRedo);
             EditConstituants(  false);
        int longueurEspaceDessin=  this.drawingPanel.getWidth();
        int largeurEspaceDessin=  this.drawingPanel.getHeight();
        this.microRoulotteControleur.setDimensioneEspaceDessin(longueurEspaceDessin, largeurEspaceDessin);
        
        double longueur= Double.parseDouble(this.profilLongueurTxt.getText()); //this.microRoulotteControleur.convertirMesure( Double.parseDouble(this.profilLongueurTxt.getText()));
        double hauteur= Double.parseDouble(this.profilHauteurTxt.getText()); //this.microRoulotteControleur.convertirMesure( Double.parseDouble(this.profilHauteurTxt.getText()));//donné
     
        this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().setHauteur(hauteur);
        this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().setLongueur(longueur);
       
         this.microRoulotteControleur.addProfil(MicroRoulotteControleur.TypeProfil.PANNEAU,pointInit, (double)longueur, (double)hauteur);
         this.microRoulotteControleur.addProfil(MicroRoulotteControleur.TypeProfil.BEZIER,pointInit, (double)longueur, (double)hauteur);
         this.jCheckBoxMenuItemVisibiliteProfil.setSelected(true);
         changerVisibilite(jCheckBoxMenuItemVisibiliteProfil, true);
        
         // Plancher;
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_HAUTEUR ,this.plancherEpaisseurText.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_MARGE_ARRIERE ,this.plancherMargeArriereTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_MARGE_AVANT ,this.plancherMargeAvantTxt.getText());
           this.changerVisibilite(jCheckBoxMenuItemVisibilitePlancher, false);
          // Poutre Arriere
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.POUTRE_ARRIERE_LARGEUR ,this.poutreArriereLargeurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.POUTRE_ARRIERE_HAUTEUR ,this.poutreArriereHauteurTxt.getText());
           this.changerVisibilite(  jCheckBoxMenuItemVisibilitePoutreArriere, false);
           
           // Hayon 
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_EPAISSEUR ,this.hayonEpaisseurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_POIDS ,this.hayonPoidsTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_RAYON_ARC_SUP ,this.hayonRayonArcSperieurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_EPAISSEUR_TRAIT_SCIE ,this.hayonEpaisseurTraitScieTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_DISTANCE_POUTRE_ARRIERE ,this.hayonDistancePoutreArriere.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_DISTANCE_PLANCHER ,this.hayonDistancePlacher.getText());
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteHayon, false);
           
           // Toit
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_EPAISSEUR ,this.toitEpaisseurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_NBRE_POUTRE_AVANT ,this.toitNombrePoutreAvantTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_EPAISSEUR_POUTRE_AVANT ,this.toitEpaisseurPoutreAvantsTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_LARGEUR_POUTRE_AVANT ,this.toitLargeurPoutreAvantTxt.getText());

           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteToitPoutreAvant, false);
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteToitRecouvrement, false);
      
           
           // Mur latereur
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_EXTERIEUR ,this.murLateralEpaisseurCoucheExterieurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_INTERIEUR ,this.murLateralEpaisseurCoucheInterieurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_SQUELETTE ,this.murLateralEpaisseurCoucheSqueletteTxt.getText());
           
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteMLCoucheExterieure, false);
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteMLCoucheInterieur, false);
           this.changerVisibilite(jCheckBoxMenuItemVisibiliteMLCoucheSquelette, false);
           
           // Porte
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_HAUTEUR ,this.porteHauteurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_LARGEUR ,this.porteLargeurTxt.getText());
        //   this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_RAYON_ARC ,this.porteRayonArc.getText());
          
           this.changerVisibilite( jCheckBoxMenuItemVisibilitePorte , false);
           
            // Fenetre
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_HAUTEUR ,this.fenetreHauteurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_LARGEUR ,this.fenetreLargeurTxt.getText());
          // this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_RAYON_ARC ,this.fenetreRayonArc.getText());
          
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteFenetre, false);
           
             // Mur separateur
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_EPAISSEUR ,this.murSeparateurEpaisseurTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_DISTANCE_PLACHER ,this.murSeparateurDistancePlancherTxt.getText());
           this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_DISTANCE_POUTRE_ARRIERE ,this.murSeparateurDistancePoutreArriceTxt.getText());
          
           this.changerVisibilite( jCheckBoxMenuItemVisibiliteMurSeparateur , false);
           this.microRoulotteControleur.getMicroRoulotte().setPositionInitialeCoinSupDroit(pointInit);
         }
         catch( Exception ex){
            lblMessageErreur.setText(ex.getMessage());;
         }
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanelConstituants = new javax.swing.JPanel();
        jPanelChoisirProfil = new javax.swing.JPanel();
        jRadioButtonPanneau = new javax.swing.JRadioButton();
        jRadioButtonCourbeBezier = new javax.swing.JRadioButton();
        jPanelPanneauRectangle = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        profilLongueurTxt = new javax.swing.JTextField();
        profilHauteurTxt = new javax.swing.JTextField();
        jButtonEnregistrerProfil = new javax.swing.JButton();
        profilCheckboxAfficher = new java.awt.Checkbox();
        jButtonEnregistrer = new javax.swing.JButton();
        jPanelHayon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        hayonEpaisseurTxt = new javax.swing.JTextField();
        hayonPoidsTxt = new javax.swing.JTextField();
        hayonRayonArcSperieurTxt = new javax.swing.JTextField();
        hayonEpaisseurTraitScieTxt = new javax.swing.JTextField();
        hayonDistancePoutreArriere = new javax.swing.JTextField();
        hayonDistancePlacher = new javax.swing.JTextField();
        hayonCheckboxAfficher = new java.awt.Checkbox();
        couleur_Lbl_hayon = new javax.swing.JLabel();
        jPanelMurLateral = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        murLateralEpaisseurCoucheInterieurTxt = new javax.swing.JTextField();
        murLateralEpaisseurCoucheSqueletteTxt = new javax.swing.JTextField();
        murLateralEpaisseurCoucheExterieurTxt = new javax.swing.JTextField();
        jPanelOuverture = new javax.swing.JPanel();
        jPanelPorte = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        porteHauteurTxt = new javax.swing.JTextField();
        porteLargeurTxt = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        portePositionTxt = new javax.swing.JTextField();
        porteCheckboxAfficher = new java.awt.Checkbox();
        couleur_Lbl_porte = new javax.swing.JLabel();
        jCheckBoxSelectionPorte = new javax.swing.JCheckBox();
        jCheckBoxPorteAf = new javax.swing.JCheckBox();
        jPanelFenetre = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        fenetreLargeurTxt = new javax.swing.JTextField();
        fenetreHauteurTxt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        fenetrePositionTxt = new javax.swing.JTextField();
        fenetreCheckboxAfficher = new java.awt.Checkbox();
        couleur_Lbl_fenetre = new javax.swing.JLabel();
        jCheckBoxSelectionFenetre = new javax.swing.JCheckBox();
        jCheckBoxFenetreAf = new javax.swing.JCheckBox();
        murSqueletteCheckboxAfficher = new java.awt.Checkbox();
        murExterieurCheckboxAfficher = new java.awt.Checkbox();
        murInterieurCheckboxAfficher = new java.awt.Checkbox();
        couleur_Lbl_epaisseurC_Interieur = new javax.swing.JLabel();
        couleur_Lbl_epaisseur_C_squel = new javax.swing.JLabel();
        couleur_Lbl_epaisseur_c_Exterieur = new javax.swing.JLabel();
        jPanalPoudreArriere1 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSpinnerPasHorizontal = new javax.swing.JSpinner();
        jSpinnerPasVertical = new javax.swing.JSpinner();
        jCheckBoxAjouterGrille = new javax.swing.JCheckBox();
        jPanelPlancherPoutreArriere = new javax.swing.JPanel();
        jPanelPlancher = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        plancherEpaisseurText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        plancherMargeAvantTxt = new javax.swing.JTextField();
        plancherMargeArriereTxt = new javax.swing.JTextField();
        plancherCheckboxAfficher = new java.awt.Checkbox();
        couleur_Lbl_plancher2 = new javax.swing.JLabel();
        jPanalPoudreArriere = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        poutreArriereLargeurTxt = new javax.swing.JTextField();
        poutreArriereHauteurTxt = new javax.swing.JTextField();
        poutreArrierePosition = new javax.swing.JTextField();
        poutreArriereCheckboxAfficher = new java.awt.Checkbox();
        couleur_Lbl_poutreArr = new javax.swing.JLabel();
        jPanelMurSeparateur = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        murSeparateurEpaisseurTxt = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        murSeparateurDistancePlancherTxt = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        murSeparateurDistancePoutreArriceTxt = new javax.swing.JTextField();
        murSeparateurCheckboxAfficher = new java.awt.Checkbox();
        couleur_Lbl_murSeparateur = new javax.swing.JLabel();
        lblMessageErreur = new javax.swing.JLabel();
        jPanelToit = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        toitEpaisseurTxt = new javax.swing.JTextField();
        toitNombrePoutreAvantTxt = new javax.swing.JTextField();
        toitEpaisseurPoutreAvantsTxt = new javax.swing.JTextField();
        toitLargeurPoutreAvantTxt = new javax.swing.JTextField();
        toitCheckboxAfficher = new java.awt.Checkbox();
        couleur_Lbl_toit = new javax.swing.JLabel();
        jPanelDessin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        drawingPanel = new ca.ulaval.glo2004.gui.DrawingPanel(this);
        buttonTopPanel = new javax.swing.JPanel();
        topMenuBarMainWindow = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        jMenuItemOuvrirProjet = new javax.swing.JMenuItem();
        jMenuItemCreerNouveauProjet = new javax.swing.JMenuItem();
        jMenuItemEnregistrerSous = new javax.swing.JMenuItem();
        jMenuItemEnregistrer = new javax.swing.JMenuItem();
        jMenuItemFermer = new javax.swing.JMenuItem();
        jMenuEdition = new javax.swing.JMenu();
        jMenuItemUndo = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItemRedo = new javax.swing.JMenuItem();
        jCheckBoxMenuItemCapterUndoRedo = new javax.swing.JCheckBoxMenuItem();
        jMenuItemEditerProfil = new javax.swing.JMenuItem();
        jMenuItemEditerConstituant = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenuExporter = new javax.swing.JMenu();
        jMenuItemExporter2A = new javax.swing.JMenuItem();
        jMenuItemExporterSVG = new javax.swing.JMenuItem();
        jMenuAffichage = new javax.swing.JMenu();
        jMenuItemZoom = new javax.swing.JMenuItem();
        jMenuItemUnZoom = new javax.swing.JMenuItem();
        jMenuCouleur = new javax.swing.JMenu();
        jMenuItemCouleurAideDessin = new javax.swing.JMenuItem();
        jMenuItemCouleurHayon = new javax.swing.JMenuItem();
        jMenuCouleurMurLateral = new javax.swing.JMenu();
        jMenuItemCouleurMurLateralCoucheExterieur = new javax.swing.JMenuItem();
        jMenuItemCouleurMurLateralCoucheInterieur = new javax.swing.JMenuItem();
        jMenuItemCouleurMurLateralCoucheSequelette = new javax.swing.JMenuItem();
        jMenuItemCouleurMurSeparateur = new javax.swing.JMenuItem();
        jMenuItemCouleurPlancher = new javax.swing.JMenuItem();
        jMenuItemCouleurPoutreArriere = new javax.swing.JMenuItem();
        jMenuItemCouleurProfil = new javax.swing.JMenuItem();
        jMenuCouleurToit = new javax.swing.JMenu();
        jMenuItemCouleurToitPoutreAvant = new javax.swing.JMenuItem();
        jMenuItemCouleurToitRecouvrement = new javax.swing.JMenuItem();
        jMenuItemCouleurPorte = new javax.swing.JMenuItem();
        jMenuItemCouleurFenetre = new javax.swing.JMenuItem();
        jMenuVisibilite = new javax.swing.JMenu();
        jCheckBoxMenuItemVisibiliteAideDessin = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibiliteFenetre = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibiliteHayon = new javax.swing.JCheckBoxMenuItem();
        jMenuVisibiliteMurLateral = new javax.swing.JMenu();
        jCheckBoxMenuItemVisibiliteMLCoucheExterieure = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibiliteMLCoucheInterieur = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibiliteMLCoucheSquelette = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibiliteMurSeparateur = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibilitePlancher = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibilitePoutreArriere = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibilitePorte = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibiliteProfil = new javax.swing.JCheckBoxMenuItem();
        jMenuVisibiliteToit = new javax.swing.JMenu();
        jCheckBoxMenuItemVisibiliteToitPoutreAvant = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVisibiliteToitRecouvrement = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configMursLateraux_keyPressed(evt);
            }
        });

        mainPanel.setLayout(new java.awt.BorderLayout());

        jPanelConstituants.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelConstituants.setName("configurationPanel"); // NOI18N

        jPanelChoisirProfil.setBorder(javax.swing.BorderFactory.createTitledBorder("Choisir type de profil"));
        jPanelChoisirProfil.setPreferredSize(new java.awt.Dimension(420, 140));

        jRadioButtonPanneau.setText("A partir du panneau");
        jRadioButtonPanneau.setActionCommand("radioBtnRectangle");
        jRadioButtonPanneau.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonPanneauStateChanged(evt);
            }
        });
        jRadioButtonPanneau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseProfilMouse_Click(evt);
            }
        });
        jRadioButtonPanneau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPanneauActionPerformed(evt);
            }
        });
        jRadioButtonPanneau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jRadioButtonPanneauPropertyChange(evt);
            }
        });

        jRadioButtonCourbeBezier.setSelected(true);
        jRadioButtonCourbeBezier.setText("A partir de la courbe de Bézier");
        jRadioButtonCourbeBezier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseProfilMouse_Click(evt);
            }
        });
        jRadioButtonCourbeBezier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCourbeBezierActionPerformed(evt);
            }
        });
        jRadioButtonCourbeBezier.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jRadioButtonCourbeBezierPropertyChange(evt);
            }
        });

        jPanelPanneauRectangle.setBorder(javax.swing.BorderFactory.createTitledBorder("Dimesion du rectangle"));
        jPanelPanneauRectangle.setName("dimRectanglePanel"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Longueur:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Largeur:");

        profilLongueurTxt.setText("960");
        profilLongueurTxt.setName("txtLongueur"); // NOI18N
        profilLongueurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                choisirProfil_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                profilDimensionChanged(evt);
            }
        });

        profilHauteurTxt.setText("480");
        profilHauteurTxt.setName("txtHauteur"); // NOI18N
        profilHauteurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                choisirProfil_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                profilDimensionChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelPanneauRectangleLayout = new javax.swing.GroupLayout(jPanelPanneauRectangle);
        jPanelPanneauRectangle.setLayout(jPanelPanneauRectangleLayout);
        jPanelPanneauRectangleLayout.setHorizontalGroup(
            jPanelPanneauRectangleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPanneauRectangleLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelPanneauRectangleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanelPanneauRectangleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profilLongueurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profilHauteurTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelPanneauRectangleLayout.setVerticalGroup(
            jPanelPanneauRectangleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPanneauRectangleLayout.createSequentialGroup()
                .addGroup(jPanelPanneauRectangleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profilLongueurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPanneauRectangleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profilHauteurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)))
        );

        jButtonEnregistrerProfil.setText("Enreg Panneau");
        jButtonEnregistrerProfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEnregistrerProfilMouseClicked(evt);
            }
        });
        jButtonEnregistrerProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnregistrerProfilActionPerformed(evt);
            }
        });

        profilCheckboxAfficher.setLabel("Afficher");
        profilCheckboxAfficher.setState(true);
        profilCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        jButtonEnregistrer.setText("enreg Bezier");
        jButtonEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnregistrerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelChoisirProfilLayout = new javax.swing.GroupLayout(jPanelChoisirProfil);
        jPanelChoisirProfil.setLayout(jPanelChoisirProfilLayout);
        jPanelChoisirProfilLayout.setHorizontalGroup(
            jPanelChoisirProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChoisirProfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPanneauRectangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelChoisirProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelChoisirProfilLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButtonEnregistrerProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButtonEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelChoisirProfilLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profilCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelChoisirProfilLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jRadioButtonPanneau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButtonCourbeBezier)
                .addGap(23, 23, 23))
        );
        jPanelChoisirProfilLayout.setVerticalGroup(
            jPanelChoisirProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChoisirProfilLayout.createSequentialGroup()
                .addGroup(jPanelChoisirProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonPanneau)
                    .addComponent(jRadioButtonCourbeBezier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelChoisirProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPanneauRectangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelChoisirProfilLayout.createSequentialGroup()
                        .addComponent(profilCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanelChoisirProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEnregistrerProfil)
                            .addComponent(jButtonEnregistrer))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelHayon.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer le hayon"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Epaisseur :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Poids (en g):");
        jLabel2.setToolTipText("");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Rayon arc supérieur:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Epaisseur Trait de scie :");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Distance poutre arriere:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Distance au plancher:");

        hayonEpaisseurTxt.setText("4");
        hayonEpaisseurTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hayonEpaisseurTxtActionPerformed(evt);
            }
        });
        hayonEpaisseurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configHayon_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configHayon_KeyReleased(evt);
            }
        });

        hayonPoidsTxt.setText("200");
        hayonPoidsTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configHayon_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configHayon_KeyReleased(evt);
            }
        });

        hayonRayonArcSperieurTxt.setText("2 3/8");
        hayonRayonArcSperieurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configHayon_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configHayon_KeyReleased(evt);
            }
        });

        hayonEpaisseurTraitScieTxt.setText("1/16");
        hayonEpaisseurTraitScieTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configHayon_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configHayon_KeyReleased(evt);
            }
        });

        hayonDistancePoutreArriere.setText("5/16");
        hayonDistancePoutreArriere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hayonDistancePoutreArriereActionPerformed(evt);
            }
        });
        hayonDistancePoutreArriere.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configHayon_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configHayon_KeyReleased(evt);
            }
        });

        hayonDistancePlacher.setText("3/8");
        hayonDistancePlacher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configHayon_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configHayon_KeyReleased(evt);
            }
        });

        hayonCheckboxAfficher.setLabel("Afficher");
        hayonCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        couleur_Lbl_hayon.setText("Changer Couleur");
        couleur_Lbl_hayon.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_hayon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_hayon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelHayonLayout = new javax.swing.GroupLayout(jPanelHayon);
        jPanelHayon.setLayout(jPanelHayonLayout);
        jPanelHayonLayout.setHorizontalGroup(
            jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHayonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHayonLayout.createSequentialGroup()
                        .addComponent(hayonRayonArcSperieurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHayonLayout.createSequentialGroup()
                        .addComponent(hayonPoidsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(couleur_Lbl_hayon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHayonLayout.createSequentialGroup()
                        .addComponent(hayonEpaisseurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hayonCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hayonEpaisseurTraitScieTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hayonDistancePoutreArriere, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hayonDistancePlacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelHayonLayout.setVerticalGroup(
            jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHayonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hayonEpaisseurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hayonEpaisseurTraitScieTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hayonCheckboxAfficher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hayonPoidsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hayonDistancePoutreArriere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(couleur_Lbl_hayon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHayonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hayonRayonArcSperieurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hayonDistancePlacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jPanelMurLateral.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer murs latereaux"));
        jPanelMurLateral.setPreferredSize(new java.awt.Dimension(420, 250));

        jLabel15.setText("Épaisseur couche interieur:");

        jLabel16.setText("Épaisseur couche squelette:");

        jLabel17.setText("Épaisseur couche exterieur:");

        murLateralEpaisseurCoucheInterieurTxt.setText("1/4");
        murLateralEpaisseurCoucheInterieurTxt.setToolTipText("");
        murLateralEpaisseurCoucheInterieurTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                murLateralEpaisseurCoucheInterieurTxtActionPerformed(evt);
            }
        });
        murLateralEpaisseurCoucheInterieurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configMursLateraux_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configMursLateraux_keyReleased(evt);
            }
        });

        murLateralEpaisseurCoucheSqueletteTxt.setText("1/8");
        murLateralEpaisseurCoucheSqueletteTxt.setToolTipText("");
        murLateralEpaisseurCoucheSqueletteTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                murLateralEpaisseurCoucheSqueletteTxtActionPerformed(evt);
            }
        });
        murLateralEpaisseurCoucheSqueletteTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configMursLateraux_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configMursLateraux_keyReleased(evt);
            }
        });

        murLateralEpaisseurCoucheExterieurTxt.setText("3/4");
        murLateralEpaisseurCoucheExterieurTxt.setToolTipText("");
        murLateralEpaisseurCoucheExterieurTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                murLateralEpaisseurCoucheExterieurTxtActionPerformed(evt);
            }
        });
        murLateralEpaisseurCoucheExterieurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configMursLateraux_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configMursLateraux_keyReleased(evt);
            }
        });

        jPanelOuverture.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer les ouvertures"));

        jPanelPorte.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer la porte"));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Hauteur:");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Largeur:");

        porteHauteurTxt.setText("80");
        porteHauteurTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porteHauteurTxtActionPerformed(evt);
            }
        });
        porteHauteurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerOuvertures_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configOuvertures_keyReleased(evt);
            }
        });

        porteLargeurTxt.setText("50");
        porteLargeurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerOuvertures_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configOuvertures_keyReleased(evt);
            }
        });

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Position:");

        portePositionTxt.setText("0");
        portePositionTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerOuvertures_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configOuvertures_keyReleased(evt);
            }
        });

        porteCheckboxAfficher.setLabel("Afficher");
        porteCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        couleur_Lbl_porte.setText("Changer Couleur");
        couleur_Lbl_porte.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_porte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_porte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        jCheckBoxSelectionPorte.setText("Selectionner");
        jCheckBoxSelectionPorte.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxSelectionPorteItemStateChanged(evt);
            }
        });
        jCheckBoxSelectionPorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSelectionPorteActionPerformed(evt);
            }
        });

        jCheckBoxPorteAf.setText("af");
        jCheckBoxPorteAf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxPorteAfItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelPorteLayout = new javax.swing.GroupLayout(jPanelPorte);
        jPanelPorte.setLayout(jPanelPorteLayout);
        jPanelPorteLayout.setHorizontalGroup(
            jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPorteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPorteLayout.createSequentialGroup()
                        .addGroup(jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPorteLayout.createSequentialGroup()
                                .addComponent(jCheckBoxSelectionPorte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                            .addComponent(couleur_Lbl_porte, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPorteLayout.createSequentialGroup()
                        .addComponent(jCheckBoxPorteAf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPorteLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(portePositionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porteLargeurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porteHauteurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porteCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelPorteLayout.setVerticalGroup(
            jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPorteLayout.createSequentialGroup()
                .addGroup(jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(porteCheckboxAfficher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(couleur_Lbl_porte, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(porteHauteurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jCheckBoxPorteAf))
                    .addComponent(porteLargeurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(portePositionTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jCheckBoxSelectionPorte)))
                .addGap(20, 20, 20))
        );

        jPanelFenetre.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer les fénêtres"));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Hauteur:");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Largeur:");

        fenetreLargeurTxt.setText("50");
        fenetreLargeurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerOuvertures_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configOuvertures_keyReleased(evt);
            }
        });

        fenetreHauteurTxt.setText("50");
        fenetreHauteurTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fenetreHauteurTxtActionPerformed(evt);
            }
        });
        fenetreHauteurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerOuvertures_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configOuvertures_keyReleased(evt);
            }
        });

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Position:");

        fenetrePositionTxt.setText("30");
        fenetrePositionTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerOuvertures_keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configOuvertures_keyReleased(evt);
            }
        });

        fenetreCheckboxAfficher.setLabel("Afficher");
        fenetreCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        couleur_Lbl_fenetre.setText("Changer Couleur");
        couleur_Lbl_fenetre.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_fenetre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_fenetre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        jCheckBoxSelectionFenetre.setText("Selectionner");
        jCheckBoxSelectionFenetre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxSelectionFenetreItemStateChanged(evt);
            }
        });

        jCheckBoxFenetreAf.setText("af");
        jCheckBoxFenetreAf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxFenetreAfItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelFenetreLayout = new javax.swing.GroupLayout(jPanelFenetre);
        jPanelFenetre.setLayout(jPanelFenetreLayout);
        jPanelFenetreLayout.setHorizontalGroup(
            jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFenetreLayout.createSequentialGroup()
                .addGroup(jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFenetreLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(fenetreCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFenetreLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23)
                        .addComponent(fenetreLargeurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFenetreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxSelectionFenetre)
                            .addComponent(jCheckBoxFenetreAf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFenetreLayout.createSequentialGroup()
                                .addGap(0, 35, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fenetreHauteurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFenetreLayout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(fenetrePositionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFenetreLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(couleur_Lbl_fenetre)
                        .addGap(86, 86, 86)))
                .addContainerGap())
        );
        jPanelFenetreLayout.setVerticalGroup(
            jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFenetreLayout.createSequentialGroup()
                .addGroup(jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fenetreCheckboxAfficher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(couleur_Lbl_fenetre, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fenetreLargeurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxFenetreAf)
                    .addGroup(jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fenetreHauteurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFenetreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fenetrePositionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jCheckBoxSelectionFenetre)))
        );

        javax.swing.GroupLayout jPanelOuvertureLayout = new javax.swing.GroupLayout(jPanelOuverture);
        jPanelOuverture.setLayout(jPanelOuvertureLayout);
        jPanelOuvertureLayout.setHorizontalGroup(
            jPanelOuvertureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOuvertureLayout.createSequentialGroup()
                .addComponent(jPanelPorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelFenetre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelOuvertureLayout.setVerticalGroup(
            jPanelOuvertureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOuvertureLayout.createSequentialGroup()
                .addComponent(jPanelPorte, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelOuvertureLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelFenetre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        murSqueletteCheckboxAfficher.setLabel("Afficher");
        murSqueletteCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        murExterieurCheckboxAfficher.setLabel("Afficher");
        murExterieurCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        murInterieurCheckboxAfficher.setLabel("Afficher");
        murInterieurCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        couleur_Lbl_epaisseurC_Interieur.setText("Changer Couleur");
        couleur_Lbl_epaisseurC_Interieur.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_epaisseurC_Interieur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_epaisseurC_Interieur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        couleur_Lbl_epaisseur_C_squel.setText("Changer Couleur");
        couleur_Lbl_epaisseur_C_squel.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_epaisseur_C_squel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_epaisseur_C_squel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        couleur_Lbl_epaisseur_c_Exterieur.setText("Changer Couleur");
        couleur_Lbl_epaisseur_c_Exterieur.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_epaisseur_c_Exterieur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_epaisseur_c_Exterieur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        jPanalPoudreArriere1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer grille"));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Pas Horizontal");
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Pas Vertical");
        jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jSpinnerPasHorizontal.setValue(10);
        jSpinnerPasHorizontal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPasHorizontalStateChanged(evt);
            }
        });

        jSpinnerPasVertical.setValue(10);
        jSpinnerPasVertical.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPasVerticalStateChanged(evt);
            }
        });

        jCheckBoxAjouterGrille.setText("Ajouter");
        jCheckBoxAjouterGrille.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxAjouterGrilleItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanalPoudreArriere1Layout = new javax.swing.GroupLayout(jPanalPoudreArriere1);
        jPanalPoudreArriere1.setLayout(jPanalPoudreArriere1Layout);
        jPanalPoudreArriere1Layout.setHorizontalGroup(
            jPanalPoudreArriere1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanalPoudreArriere1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanalPoudreArriere1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanalPoudreArriere1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSpinnerPasVertical, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jSpinnerPasHorizontal))
                .addGap(22, 22, 22))
            .addGroup(jPanalPoudreArriere1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jCheckBoxAjouterGrille)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanalPoudreArriere1Layout.setVerticalGroup(
            jPanalPoudreArriere1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanalPoudreArriere1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxAjouterGrille)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanalPoudreArriere1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jSpinnerPasHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanalPoudreArriere1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jSpinnerPasVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanelMurLateralLayout = new javax.swing.GroupLayout(jPanelMurLateral);
        jPanelMurLateral.setLayout(jPanelMurLateralLayout);
        jPanelMurLateralLayout.setHorizontalGroup(
            jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMurLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelOuverture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                        .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(murLateralEpaisseurCoucheInterieurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(murInterieurCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(couleur_Lbl_epaisseurC_Interieur))
                            .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                        .addComponent(murLateralEpaisseurCoucheExterieurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(murExterieurCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(couleur_Lbl_epaisseur_c_Exterieur))
                                    .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                        .addComponent(murLateralEpaisseurCoucheSqueletteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(murSqueletteCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(couleur_Lbl_epaisseur_C_squel)))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanalPoudreArriere1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelMurLateralLayout.setVerticalGroup(
            jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                        .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(couleur_Lbl_epaisseur_C_squel))
                            .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(murLateralEpaisseurCoucheInterieurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(murInterieurCheckboxAfficher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(couleur_Lbl_epaisseurC_Interieur)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(murLateralEpaisseurCoucheSqueletteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                                        .addComponent(murSqueletteCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanelMurLateralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanalPoudreArriere1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMurLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(murLateralEpaisseurCoucheExterieurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(murExterieurCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(couleur_Lbl_epaisseur_c_Exterieur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelOuverture, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        jPanelPlancher.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer le plancher"));

        jLabel9.setText("Épaisseur:");

        plancherEpaisseurText.setText("5");
        plancherEpaisseurText.setToolTipText("");
        plancherEpaisseurText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerPlancer_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configurerPlancer_KeyReleased(evt);
            }
        });

        jLabel10.setText("Marge avant:");

        jLabel11.setText("Marge Arrière:");

        plancherMargeAvantTxt.setText("5");
        plancherMargeAvantTxt.setToolTipText("");
        plancherMargeAvantTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerPlancer_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configurerPlancer_KeyReleased(evt);
            }
        });

        plancherMargeArriereTxt.setText("2");
        plancherMargeArriereTxt.setToolTipText("");
        plancherMargeArriereTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configurerPlancer_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configurerPlancer_KeyReleased(evt);
            }
        });

        plancherCheckboxAfficher.setLabel("Afficher");
        plancherCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        couleur_Lbl_plancher2.setText("Changer Couleur");
        couleur_Lbl_plancher2.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_plancher2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_plancher2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleur_Lbl_plancher2couleurs_Lbl_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelPlancherLayout = new javax.swing.GroupLayout(jPanelPlancher);
        jPanelPlancher.setLayout(jPanelPlancherLayout);
        jPanelPlancherLayout.setHorizontalGroup(
            jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPlancherLayout.createSequentialGroup()
                .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plancherMargeAvantTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPlancherLayout.createSequentialGroup()
                        .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(plancherEpaisseurText, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plancherMargeArriereTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(couleur_Lbl_plancher2)
                            .addComponent(plancherCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanelPlancherLayout.setVerticalGroup(
            jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPlancherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPlancherLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plancherEpaisseurText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(couleur_Lbl_plancher2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPlancherLayout.createSequentialGroup()
                        .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plancherMargeAvantTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPlancherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plancherMargeArriereTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelPlancherLayout.createSequentialGroup()
                        .addComponent(plancherCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanalPoudreArriere.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer poutre arrière"));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Largeur:");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Hauteur:");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Position:");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        poutreArriereLargeurTxt.setText("5");
        poutreArriereLargeurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configPoutreAr_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configPoutreAr_KeyReleased(evt);
            }
        });

        poutreArriereHauteurTxt.setText("3");
        poutreArriereHauteurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configPoutreAr_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configPoutreAr_KeyReleased(evt);
            }
        });

        poutreArrierePosition.setText("0");
        poutreArrierePosition.setToolTipText("");
        poutreArrierePosition.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configPoutreAr_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configPoutreAr_KeyReleased(evt);
            }
        });

        poutreArriereCheckboxAfficher.setLabel("Afficher");
        poutreArriereCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        couleur_Lbl_poutreArr.setText("Changer Couleur");
        couleur_Lbl_poutreArr.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_poutreArr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_poutreArr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanalPoudreArriereLayout = new javax.swing.GroupLayout(jPanalPoudreArriere);
        jPanalPoudreArriere.setLayout(jPanalPoudreArriereLayout);
        jPanalPoudreArriereLayout.setHorizontalGroup(
            jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanalPoudreArriereLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addGroup(jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(poutreArriereHauteurTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(poutreArriereLargeurTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(poutreArrierePosition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(poutreArriereCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(couleur_Lbl_poutreArr))
                .addContainerGap())
        );
        jPanalPoudreArriereLayout.setVerticalGroup(
            jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanalPoudreArriereLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(poutreArriereLargeurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(couleur_Lbl_poutreArr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(poutreArriereHauteurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(poutreArriereCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanalPoudreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(poutreArrierePosition))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanelPlancherPoutreArriereLayout = new javax.swing.GroupLayout(jPanelPlancherPoutreArriere);
        jPanelPlancherPoutreArriere.setLayout(jPanelPlancherPoutreArriereLayout);
        jPanelPlancherPoutreArriereLayout.setHorizontalGroup(
            jPanelPlancherPoutreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPlancherPoutreArriereLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPlancher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanalPoudreArriere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanelPlancherPoutreArriereLayout.setVerticalGroup(
            jPanelPlancherPoutreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPlancherPoutreArriereLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelPlancherPoutreArriereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPlancher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanalPoudreArriere, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jPanelMurSeparateur.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer Mur Separateur"));
        jPanelMurSeparateur.setPreferredSize(new java.awt.Dimension(420, 117));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Épaisseur:");

        murSeparateurEpaisseurTxt.setText("10");
        murSeparateurEpaisseurTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                murSeparateurEpaisseurTxtActionPerformed(evt);
            }
        });
        murSeparateurEpaisseurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configMurSeparateur_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configMurSeparateur_KeyReleased(evt);
            }
        });

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Distance au plancher:");

        murSeparateurDistancePlancherTxt.setText("3");
        murSeparateurDistancePlancherTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                murSeparateurDistancePlancherTxtActionPerformed(evt);
            }
        });
        murSeparateurDistancePlancherTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configMurSeparateur_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configMurSeparateur_KeyReleased(evt);
            }
        });

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Distance à la poutre Arriere:");

        murSeparateurDistancePoutreArriceTxt.setText("5");
        murSeparateurDistancePoutreArriceTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configMurSeparateur_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configMurSeparateur_KeyReleased(evt);
            }
        });

        murSeparateurCheckboxAfficher.setLabel("Afficher");
        murSeparateurCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        couleur_Lbl_murSeparateur.setText("Changer Couleur");
        couleur_Lbl_murSeparateur.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_murSeparateur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_murSeparateur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelMurSeparateurLayout = new javax.swing.GroupLayout(jPanelMurSeparateur);
        jPanelMurSeparateur.setLayout(jPanelMurSeparateurLayout);
        jPanelMurSeparateurLayout.setHorizontalGroup(
            jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMurSeparateurLayout.createSequentialGroup()
                .addGroup(jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addGroup(jPanelMurSeparateurLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(murSeparateurDistancePoutreArriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(murSeparateurDistancePlancherTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(murSeparateurEpaisseurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(couleur_Lbl_murSeparateur)
                    .addComponent(murSeparateurCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        jPanelMurSeparateurLayout.setVerticalGroup(
            jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMurSeparateurLayout.createSequentialGroup()
                .addGroup(jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(murSeparateurEpaisseurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(couleur_Lbl_murSeparateur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(murSeparateurCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(murSeparateurDistancePlancherTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMurSeparateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(murSeparateurDistancePoutreArriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMessageErreur.setForeground(new java.awt.Color(255, 0, 0));
        lblMessageErreur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessageErreur.setText("commencer par le profil ");

        jPanelToit.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurer le toit"));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Épaisseur du toit:");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Nombre poutre avant:");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Épaisseur poutre avant:");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Largeur poutre avant:");

        toitEpaisseurTxt.setText("3");
        toitEpaisseurTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toitEpaisseurTxtActionPerformed(evt);
            }
        });
        toitEpaisseurTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configToit_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configToit_KeyReleased(evt);
            }
        });

        toitNombrePoutreAvantTxt.setText("2");
        toitNombrePoutreAvantTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configToit_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configToit_KeyReleased(evt);
            }
        });

        toitEpaisseurPoutreAvantsTxt.setText("2");
        toitEpaisseurPoutreAvantsTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configToit_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configToit_KeyReleased(evt);
            }
        });

        toitLargeurPoutreAvantTxt.setText("2");
        toitLargeurPoutreAvantTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configToit_KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configToit_KeyReleased(evt);
            }
        });

        toitCheckboxAfficher.setLabel("Afficher");
        toitCheckboxAfficher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxAfficherItem_StateChanged(evt);
            }
        });

        couleur_Lbl_toit.setText("Changer Couleur");
        couleur_Lbl_toit.setToolTipText("Clicker ici pour changer la couleur de l'élément");
        couleur_Lbl_toit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        couleur_Lbl_toit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                couleurs_Lbl_MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelToitLayout = new javax.swing.GroupLayout(jPanelToit);
        jPanelToit.setLayout(jPanelToitLayout);
        jPanelToitLayout.setHorizontalGroup(
            jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToitLayout.createSequentialGroup()
                .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelToitLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(toitEpaisseurTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(toitNombrePoutreAvantTxt))
                .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelToitLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(toitCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toitEpaisseurPoutreAvantsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelToitLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(couleur_Lbl_toit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toitLargeurPoutreAvantTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelToitLayout.setVerticalGroup(
            jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToitLayout.createSequentialGroup()
                .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toitEpaisseurTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelToitLayout.createSequentialGroup()
                        .addComponent(toitCheckboxAfficher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(toitEpaisseurPoutreAvantsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelToitLayout.createSequentialGroup()
                        .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toitNombrePoutreAvantTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))
                    .addGroup(jPanelToitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(toitLargeurPoutreAvantTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelToitLayout.createSequentialGroup()
                        .addComponent(couleur_Lbl_toit)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanelConstituantsLayout = new javax.swing.GroupLayout(jPanelConstituants);
        jPanelConstituants.setLayout(jPanelConstituantsLayout);
        jPanelConstituantsLayout.setHorizontalGroup(
            jPanelConstituantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConstituantsLayout.createSequentialGroup()
                .addGroup(jPanelConstituantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMessageErreur, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelConstituantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanelChoisirProfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                        .addComponent(jPanelPlancherPoutreArriere, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelToit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelMurSeparateur, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                        .addComponent(jPanelHayon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelMurLateral, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
        );
        jPanelConstituantsLayout.setVerticalGroup(
            jPanelConstituantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConstituantsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessageErreur, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelChoisirProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPlancherPoutreArriere, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelHayon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelToit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelMurLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMurSeparateur, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelDessin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDessin.setName("dessinPanel"); // NOI18N
        jPanelDessin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelDessinMouseClicked(evt);
            }
        });

        drawingPanel.setBackground(new java.awt.Color(255, 255, 255));
        drawingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        drawingPanel.setMaximumSize(new java.awt.Dimension(1181, 915));
        drawingPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                drawingPanelMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                drawingPanelMouseMoved(evt);
            }
        });
        drawingPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                drawingPanelMouseWheelMoved(evt);
            }
        });
        drawingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawingPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                drawingPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                drawingPanelMouseReleased(evt);
            }
        });
        drawingPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                configOuvertures_keyReleased(evt);
            }
        });

        javax.swing.GroupLayout drawingPanelLayout = new javax.swing.GroupLayout(drawingPanel);
        drawingPanel.setLayout(drawingPanelLayout);
        drawingPanelLayout.setHorizontalGroup(
            drawingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1111, Short.MAX_VALUE)
        );
        drawingPanelLayout.setVerticalGroup(
            drawingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(drawingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelDessinLayout = new javax.swing.GroupLayout(jPanelDessin);
        jPanelDessin.setLayout(jPanelDessinLayout);
        jPanelDessinLayout.setHorizontalGroup(
            jPanelDessinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
            .addGroup(jPanelDessinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDessinLayout.setVerticalGroup(
            jPanelDessinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1047, Short.MAX_VALUE)
            .addGroup(jPanelDessinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout buttonTopPanelLayout = new javax.swing.GroupLayout(buttonTopPanel);
        buttonTopPanel.setLayout(buttonTopPanelLayout);
        buttonTopPanelLayout.setHorizontalGroup(
            buttonTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1125, Short.MAX_VALUE)
        );
        buttonTopPanelLayout.setVerticalGroup(
            buttonTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        topMenuBarMainWindow.setMaximumSize(new java.awt.Dimension(1000, 32769));
        topMenuBarMainWindow.setMinimumSize(new java.awt.Dimension(1000, 21));
        topMenuBarMainWindow.setPreferredSize(new java.awt.Dimension(1000, 21));

        jMenuFichier.setText("Fichier");
        jMenuFichier.setToolTipText("");

        jMenuItemOuvrirProjet.setText("Ouvrir un projet");
        jMenuItemOuvrirProjet.setToolTipText("");
        jMenuItemOuvrirProjet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOuvrirProjetActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemOuvrirProjet);

        jMenuItemCreerNouveauProjet.setText("Créer un nouveau projet");
        jMenuFichier.add(jMenuItemCreerNouveauProjet);

        jMenuItemEnregistrerSous.setText("Enregistrer sous");
        jMenuItemEnregistrerSous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEnregistrerSousActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemEnregistrerSous);

        jMenuItemEnregistrer.setText("Enregistrer");
        jMenuItemEnregistrer.setToolTipText("");
        jMenuFichier.add(jMenuItemEnregistrer);

        jMenuItemFermer.setText("Fermer");
        jMenuItemFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFermerActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemFermer);

        topMenuBarMainWindow.add(jMenuFichier);

        jMenuEdition.setText("Edition");

        jMenuItemUndo.setText("Undo");
        jMenuItemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ActionPerfomed(evt);
            }
        });
        jMenuEdition.add(jMenuItemUndo);

        jMenuItemRedo.setText("Redo");
        jMenuItemRedo.setToolTipText("");
        jMenuItemRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRedoActionPerformed(evt);
            }
        });
        jMenuEdition.add(jMenuItemRedo);

        jCheckBoxMenuItemCapterUndoRedo.setText("Capter Undo/Redo");
        jCheckBoxMenuItemCapterUndoRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemCapterUndoRedoActionPerformed(evt);
            }
        });
        jMenuEdition.add(jCheckBoxMenuItemCapterUndoRedo);

        jMenuItemEditerProfil.setText("Editer Profil");
        jMenuItemEditerProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditerProfilActionPerformed(evt);
            }
        });
        jMenuEdition.add(jMenuItemEditerProfil);

        jMenuItemEditerConstituant.setText("Editer Constituents");
        jMenuItemEditerConstituant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditerConstituantActionPerformed(evt);
            }
        });
        jMenuEdition.add(jMenuItemEditerConstituant);

        jMenu1.setText("System Mesure");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Impérial");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Métrique");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem2);

        jMenuEdition.add(jMenu1);

        topMenuBarMainWindow.add(jMenuEdition);

        jMenuExporter.setText("Exporter");

        jMenuItemExporter2A.setText("Exporter 2D");
        jMenuItemExporter2A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExporter2AActionPerformed(evt);
            }
        });
        jMenuExporter.add(jMenuItemExporter2A);

        jMenuItemExporterSVG.setText("Exporter SVG");
        jMenuItemExporterSVG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExporterSVGActionPerformed(evt);
            }
        });
        jMenuExporter.add(jMenuItemExporterSVG);

        topMenuBarMainWindow.add(jMenuExporter);

        jMenuAffichage.setText("Affichage");
        jMenuAffichage.setToolTipText("");

        jMenuItemZoom.setText("Zoomer");
        jMenuItemZoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmenuItem_ZoomerDezoomer(evt);
            }
        });
        jMenuAffichage.add(jMenuItemZoom);

        jMenuItemUnZoom.setText("Dezoomer");
        jMenuItemUnZoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmenuItem_ZoomerDezoomer(evt);
            }
        });
        jMenuAffichage.add(jMenuItemUnZoom);

        topMenuBarMainWindow.add(jMenuAffichage);

        jMenuCouleur.setText("Couleur");

        jMenuItemCouleurAideDessin.setText("Aide Dessin");
        jMenuItemCouleurAideDessin.addMenuDragMouseListener(new javax.swing.event.MenuDragMouseListener() {
            public void menuDragMouseDragged(javax.swing.event.MenuDragMouseEvent evt) {
            }
            public void menuDragMouseEntered(javax.swing.event.MenuDragMouseEvent evt) {
            }
            public void menuDragMouseExited(javax.swing.event.MenuDragMouseEvent evt) {
            }
            public void menuDragMouseReleased(javax.swing.event.MenuDragMouseEvent evt) {
                jMenuItemCouleurAideDessinMenuDragMouseReleased(evt);
            }
        });
        jMenuItemCouleurAideDessin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemCouleurAideDessinMouseClicked(evt);
            }
        });
        jMenuItemCouleurAideDessin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleur.add(jMenuItemCouleurAideDessin);

        jMenuItemCouleurHayon.setText("Hayon");
        jMenuItemCouleurHayon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleur.add(jMenuItemCouleurHayon);

        jMenuCouleurMurLateral.setText("Mur Latéral");

        jMenuItemCouleurMurLateralCoucheExterieur.setText("Couche Extérieure");
        jMenuItemCouleurMurLateralCoucheExterieur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleurMurLateral.add(jMenuItemCouleurMurLateralCoucheExterieur);

        jMenuItemCouleurMurLateralCoucheInterieur.setText("Couche Intérieure");
        jMenuItemCouleurMurLateralCoucheInterieur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleurMurLateral.add(jMenuItemCouleurMurLateralCoucheInterieur);

        jMenuItemCouleurMurLateralCoucheSequelette.setText("Couche Sequelette");
        jMenuItemCouleurMurLateralCoucheSequelette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleurMurLateral.add(jMenuItemCouleurMurLateralCoucheSequelette);

        jMenuCouleur.add(jMenuCouleurMurLateral);

        jMenuItemCouleurMurSeparateur.setText("Mur Séparateur");
        jMenuItemCouleurMurSeparateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleur.add(jMenuItemCouleurMurSeparateur);

        jMenuItemCouleurPlancher.setText("Plancher");
        jMenuItemCouleurPlancher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleur.add(jMenuItemCouleurPlancher);

        jMenuItemCouleurPoutreArriere.setText("Poutre Arrière");
        jMenuItemCouleurPoutreArriere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleur.add(jMenuItemCouleurPoutreArriere);

        jMenuItemCouleurProfil.setText("Profil");
        jMenuItemCouleurProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleur.add(jMenuItemCouleurProfil);

        jMenuCouleurToit.setText("Toit");

        jMenuItemCouleurToitPoutreAvant.setText("Poutres Avant");
        jMenuItemCouleurToitPoutreAvant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleurToit.add(jMenuItemCouleurToitPoutreAvant);

        jMenuItemCouleurToitRecouvrement.setText("Recouvrement");
        jMenuItemCouleurToitRecouvrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleurToit.add(jMenuItemCouleurToitRecouvrement);

        jMenuCouleur.add(jMenuCouleurToit);

        jMenuItemCouleurPorte.setText("Porte");
        jMenuItemCouleurPorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleur.add(jMenuItemCouleurPorte);

        jMenuItemCouleurFenetre.setText("Fenetre");
        jMenuItemCouleurFenetre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleur_ActionPerformed(evt);
            }
        });
        jMenuCouleur.add(jMenuItemCouleurFenetre);

        topMenuBarMainWindow.add(jMenuCouleur);

        jMenuVisibilite.setText("Visibilité");

        jCheckBoxMenuItemVisibiliteAideDessin.setSelected(true);
        jCheckBoxMenuItemVisibiliteAideDessin.setText("Aide Dessin");
        jCheckBoxMenuItemVisibiliteAideDessin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibilite.add(jCheckBoxMenuItemVisibiliteAideDessin);

        jCheckBoxMenuItemVisibiliteFenetre.setText("Fenêtre");
        jCheckBoxMenuItemVisibiliteFenetre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibilite.add(jCheckBoxMenuItemVisibiliteFenetre);

        jCheckBoxMenuItemVisibiliteHayon.setText("Hayon");
        jCheckBoxMenuItemVisibiliteHayon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibilite.add(jCheckBoxMenuItemVisibiliteHayon);

        jMenuVisibiliteMurLateral.setText("Mur Latéral");

        jCheckBoxMenuItemVisibiliteMLCoucheExterieure.setText("Couche Extérieure");
        jCheckBoxMenuItemVisibiliteMLCoucheExterieure.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibiliteMurLateral.add(jCheckBoxMenuItemVisibiliteMLCoucheExterieure);

        jCheckBoxMenuItemVisibiliteMLCoucheInterieur.setText("Couche Intérieure");
        jCheckBoxMenuItemVisibiliteMLCoucheInterieur.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibiliteMurLateral.add(jCheckBoxMenuItemVisibiliteMLCoucheInterieur);

        jCheckBoxMenuItemVisibiliteMLCoucheSquelette.setText("Couche Séquelette");
        jCheckBoxMenuItemVisibiliteMLCoucheSquelette.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibiliteMurLateral.add(jCheckBoxMenuItemVisibiliteMLCoucheSquelette);

        jMenuVisibilite.add(jMenuVisibiliteMurLateral);

        jCheckBoxMenuItemVisibiliteMurSeparateur.setText("Mur Separateur");
        jCheckBoxMenuItemVisibiliteMurSeparateur.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibilite.add(jCheckBoxMenuItemVisibiliteMurSeparateur);

        jCheckBoxMenuItemVisibilitePlancher.setText("Plancher");
        jCheckBoxMenuItemVisibilitePlancher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibilite.add(jCheckBoxMenuItemVisibilitePlancher);

        jCheckBoxMenuItemVisibilitePoutreArriere.setText("Poutre Arriere");
        jCheckBoxMenuItemVisibilitePoutreArriere.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibilite.add(jCheckBoxMenuItemVisibilitePoutreArriere);

        jCheckBoxMenuItemVisibilitePorte.setText("Porte");
        jCheckBoxMenuItemVisibilitePorte.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibilite.add(jCheckBoxMenuItemVisibilitePorte);

        jCheckBoxMenuItemVisibiliteProfil.setText("Profil");
        jCheckBoxMenuItemVisibiliteProfil.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibilite.add(jCheckBoxMenuItemVisibiliteProfil);

        jMenuVisibiliteToit.setText("Toit");

        jCheckBoxMenuItemVisibiliteToitPoutreAvant.setText("Poutres Avant");
        jCheckBoxMenuItemVisibiliteToitPoutreAvant.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibiliteToit.add(jCheckBoxMenuItemVisibiliteToitPoutreAvant);

        jCheckBoxMenuItemVisibiliteToitRecouvrement.setText("Recouvrement");
        jCheckBoxMenuItemVisibiliteToitRecouvrement.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemVisibilite_StateChanged(evt);
            }
        });
        jMenuVisibiliteToit.add(jCheckBoxMenuItemVisibiliteToitRecouvrement);

        jMenuVisibilite.add(jMenuVisibiliteToit);

        topMenuBarMainWindow.add(jMenuVisibilite);

        setJMenuBar(topMenuBarMainWindow);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelConstituants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelDessin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buttonTopPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(buttonTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDessin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelConstituants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        buttonTopPanel.getAccessibleContext().setAccessibleParent(mainPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void murLateralEpaisseurCoucheInterieurTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_murLateralEpaisseurCoucheInterieurTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_murLateralEpaisseurCoucheInterieurTxtActionPerformed

    private void murLateralEpaisseurCoucheSqueletteTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_murLateralEpaisseurCoucheSqueletteTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_murLateralEpaisseurCoucheSqueletteTxtActionPerformed

    private void murLateralEpaisseurCoucheExterieurTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_murLateralEpaisseurCoucheExterieurTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_murLateralEpaisseurCoucheExterieurTxtActionPerformed

    private void jMenuItemFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFermerActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemFermerActionPerformed

    private void jPanelDessinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelDessinMouseClicked
       
        Point positionActuel = evt.getPoint();
        
        String nomElementTrouve = this.microRoulotteControleur.getElementSelectionne(positionActuel);
        this.lblMessageErreur.setText(nomElementTrouve);
        //
    }//GEN-LAST:event_jPanelDessinMouseClicked
  
    private void drawingPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawingPanelMouseClicked
      if(this.profilLongueurTxt.isEditable()){
        microRoulotteControleur.EditPointControl(evt.getPoint());
        this.poutreArrierePosition.setText(Integer.toString(microRoulotteControleur.getMicroRoulotte().getPoutreArriere().getPositionCadran2().x));
         drawingPanel.repaint();
      }
    }//GEN-LAST:event_drawingPanelMouseClicked

    private void profilDimensionChanged(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_profilDimensionChanged
    
        try{
            if(evt.getSource() == profilLongueurTxt) 
              {  this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PROFIL_LONGUEUR ,this.profilLongueurTxt.getText());
                 this.microRoulotteControleur.getMicroRoulotte().setLargeurProfil(Double.parseDouble(this.profilLongueurTxt.getText()));
              }
                else{
                
                this.microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PROFIL_HAUTEUR ,this.profilHauteurTxt.getText());
                this.microRoulotteControleur.getMicroRoulotte().setHauteurProfil(Double.parseDouble(this.profilHauteurTxt.getText()));
              }
            drawingPanel.repaint();
        }
         catch( Exception ex){
            lblMessageErreur.setText(ex.getMessage());;
         }

    }//GEN-LAST:event_profilDimensionChanged

    private void chooseProfilMouse_Click(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseProfilMouse_Click
       JRadioButton radioButton = (JRadioButton)evt.getSource();
        
        if(radioButton == jRadioButtonCourbeBezier){
            jRadioButtonPanneau.setSelected(Boolean.FALSE);
            this.profilHauteurTxt.setEditable(Boolean.FALSE);
            this.profilLongueurTxt.setEditable(Boolean.FALSE);
            this.microRoulotteControleur.setEnModeDessinPanneau(false);
            this.microRoulotteControleur.setEnModeDessinBezier(true);
            this.microRoulotteControleur.setEnModeAffichage(false);
        }else{
          jRadioButtonCourbeBezier.setSelected(Boolean.FALSE);
          
          this.profilHauteurTxt.setEditable(Boolean.TRUE);
          this.profilLongueurTxt.setEditable(Boolean.TRUE);
          this.profilLongueurTxt.setFocusable(Boolean.TRUE);
          this.microRoulotteControleur.setEnModeDessinPanneau(true);
          this.microRoulotteControleur.setEnModeDessinBezier(false);
        }
        
        drawingPanel.repaint();
    }//GEN-LAST:event_chooseProfilMouse_Click
    public static boolean isNumeric(String str) { 
      try {  
        Double.parseDouble(str);  
        return true;
      } catch(NumberFormatException e){  
        return false;  
      }  
    }
    private void configurerPlancer_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configurerPlancer_KeyReleased
        
        JTextField sender = (JTextField)evt.getSource();
         if(sender.getText().indexOf("/") !=-1 && !isNumeric(sender.getText().split("/")[0]) ){
             // ne rien faire si l'utilisateur tape le slache pour la premier foi. 
            return;
         }
       try{
            if( sender == plancherEpaisseurText){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_HAUTEUR , sender.getText());
            }else  if( sender == plancherMargeArriereTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_MARGE_ARRIERE, sender.getText());
            }
            else  if( sender == plancherMargeAvantTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_MARGE_AVANT , sender.getText());
            }
            
             drawingPanel.repaint();
         }
         catch( Exception ex){
             
            lblMessageErreur.setText(ex.getMessage());
            
            /*if( sender == plancherEpaisseurText){
             / plancherEpaisseurText.setText(microRoulotteControleur.getMicroRoulotte().getPlancher().);  
            }else  if( sender == plancherMargeArriereTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_MARGE_ARRIERE, sender.getText());
            }
            else  if( sender == plancherMargeAvantTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_MARGE_AVANT , sender.getText());
            }
            */
         }
    }//GEN-LAST:event_configurerPlancer_KeyReleased

    private void configurerPlancer_KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configurerPlancer_KeyPressed
          valideInput( evt);
    
    }//GEN-LAST:event_configurerPlancer_KeyPressed

    private void jRadioButtonPanneauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPanneauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonPanneauActionPerformed

    private void choisirProfil_KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_choisirProfil_KeyPressed

              valideInput( evt);
               
    }//GEN-LAST:event_choisirProfil_KeyPressed

    private void configMursLateraux_keyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configMursLateraux_keyPressed
        valideInput(evt);
    }//GEN-LAST:event_configMursLateraux_keyPressed

    private void configurerOuvertures_keyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configurerOuvertures_keyPressed
       valideInput(evt);
    }//GEN-LAST:event_configurerOuvertures_keyPressed

    private void configMursLateraux_keyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configMursLateraux_keyReleased
        JTextField sender = (JTextField)evt.getSource();
         if(sender.getText().indexOf("/") !=-1 && !isNumeric(sender.getText().split("/")[0]) ){
             // ne rien faire si l'utilisateur tape le slache pour la premier foi. 
            return;
         }
       try{
            if( sender == murLateralEpaisseurCoucheInterieurTxt ){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_INTERIEUR , sender.getText());
            }else  if( sender == murLateralEpaisseurCoucheSqueletteTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_SQUELETTE, sender.getText());
            }
            else  if( sender == murLateralEpaisseurCoucheExterieurTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_EXTERIEUR , sender.getText());
            }
             drawingPanel.repaint();
         }
         catch( Exception ex){
            lblMessageErreur.setText(ex.getMessage());;
         }
    }//GEN-LAST:event_configMursLateraux_keyReleased

    private void configOuvertures_keyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configOuvertures_keyReleased
         JTextField sender = (JTextField)evt.getSource();
         if(sender.getText().indexOf("/") !=-1 && !isNumeric(sender.getText().split("/")[0]) ){
             // ne rien faire si l'utilisateur tape le slache pour la premier foi. 
            return;
         }
       try{
            if( sender == porteHauteurTxt ){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_HAUTEUR , sender.getText());
            }else  if( sender == porteLargeurTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_LARGEUR, sender.getText());
            }
            else  if( sender == portePositionTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_POSITION , sender.getText());
            }
           // else  if( sender == porteRayonArc){
               // microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_RAYON_ARC , sender.getText());
                
           // }
            else  if( sender == fenetreHauteurTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_HAUTEUR , sender.getText());
                
            }else  if( sender == fenetreLargeurTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_LARGEUR, sender.getText());
            }
            else  if( sender == fenetrePositionTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_POSITION , sender.getText());
            }
           // else  if( sender == fenetreRayonArc){
            //    microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_RAYON_ARC , sender.getText());
           // }
             drawingPanel.repaint();
         }
         catch( Exception ex){
            lblMessageErreur.setText(ex.getMessage());;
         }
    }//GEN-LAST:event_configOuvertures_keyReleased

    private void InitialiserConstituants(){
        
        try
        {
         // constituants
        // plancher
      
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_HAUTEUR ,  this.plancherEpaisseurText.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_MARGE_AVANT ,  this.plancherMargeAvantTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PLANCHER_MARGE_ARRIERE ,  this.plancherMargeArriereTxt.getText());
         
        // poutre arriere
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.POUTRE_ARRIERE_POSITION ,  this.poutreArrierePosition.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.POUTRE_ARRIERE_HAUTEUR ,  this.poutreArriereHauteurTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.POUTRE_ARRIERE_LARGEUR ,  this.poutreArriereLargeurTxt.getText());
         
        // hayon
       
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_EPAISSEUR ,  this.hayonEpaisseurTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_POIDS ,  this.hayonPoidsTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_RAYON_ARC_SUP ,  this.hayonRayonArcSperieurTxt.getText());
         
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_EPAISSEUR_TRAIT_SCIE ,  this.hayonEpaisseurTraitScieTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_DISTANCE_POUTRE_ARRIERE ,  this.hayonDistancePoutreArriere.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_DISTANCE_PLANCHER ,  this.hayonDistancePlacher.getText());
         
        // toit
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_EPAISSEUR ,  this.toitEpaisseurTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_NBRE_POUTRE_AVANT ,  this.toitNombrePoutreAvantTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_EPAISSEUR_POUTRE_AVANT ,  this.toitEpaisseurPoutreAvantsTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_LARGEUR_POUTRE_AVANT ,  this.toitLargeurPoutreAvantTxt.getText());
       
        // Mur lateral
       
        microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_EXTERIEUR ,  this.murLateralEpaisseurCoucheExterieurTxt.getText());
        microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_INTERIEUR ,  this.murLateralEpaisseurCoucheInterieurTxt.getText());
        microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_LATERAL_SQUELETTE ,  this.murLateralEpaisseurCoucheSqueletteTxt.getText());
        
       // Porte
       
        microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_HAUTEUR ,  this.porteHauteurTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_LARGEUR ,  this.porteLargeurTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_POSITION ,  this.portePositionTxt.getText());
      //   microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.PORTE_RAYON_ARC ,  this.porteRayonArc.getText());    
       // Fenetre 
       
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_HAUTEUR ,  this.fenetreHauteurTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_LARGEUR ,  this.fenetreLargeurTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_POSITION ,  this.fenetrePositionTxt.getText());
       //  microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.FENETRE_RAYON_ARC ,  this.fenetreRayonArc.getText());         
       // Mur separateur
      
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_EPAISSEUR ,  this.murSeparateurEpaisseurTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_DISTANCE_POUTRE_ARRIERE ,  this.murSeparateurDistancePlancherTxt.getText());
         microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_DISTANCE_POUTRE_ARRIERE ,  this.murSeparateurDistancePoutreArriceTxt.getText());

        }
        catch( Exception ex){
            
            this.lblMessageErreur.setText(ex.getMessage());
        }
    }
    
     private void EditConstituants(boolean  editConstituant){
        
       // profil
         this.profilHauteurTxt.setEditable(!editConstituant);
         this.profilLongueurTxt.setEditable(!editConstituant);
         profilCheckboxAfficher.setState(!editConstituant);
         
         // constituants
        // plancher
        this.plancherEpaisseurText.setEditable(editConstituant);
        this.plancherMargeAvantTxt.setEditable(editConstituant);
        this.plancherMargeArriereTxt.setEditable(editConstituant);
        this.plancherCheckboxAfficher.setState(editConstituant);
        
        // poutre arriere
        this.poutreArrierePosition.setEditable(editConstituant);
        this.poutreArriereHauteurTxt.setEditable(editConstituant);
        this.poutreArriereLargeurTxt.setEditable(editConstituant);
        this.poutreArriereCheckboxAfficher.setState(editConstituant);
        
        // hayon
        this.hayonEpaisseurTxt.setEditable(editConstituant);
        this.hayonPoidsTxt.setEditable(editConstituant);
        this.hayonRayonArcSperieurTxt.setEditable(editConstituant);
        this.hayonEpaisseurTraitScieTxt.setEditable(editConstituant);
        this.hayonDistancePoutreArriere.setEditable(editConstituant);
        this.hayonDistancePlacher.setEditable(editConstituant);
        this.hayonCheckboxAfficher.setState(editConstituant);
        
        // toit
         this.toitEpaisseurTxt.setEditable(editConstituant);
        this.toitNombrePoutreAvantTxt.setEditable(editConstituant);
        this.toitEpaisseurPoutreAvantsTxt.setEditable(editConstituant);
        this.toitLargeurPoutreAvantTxt.setEditable(editConstituant);
        this.toitCheckboxAfficher.setState(editConstituant);
        
       // Mur lateral
       
       this.murLateralEpaisseurCoucheExterieurTxt.setEditable(editConstituant);
       this.murLateralEpaisseurCoucheInterieurTxt.setEditable(editConstituant);
       this.murLateralEpaisseurCoucheSqueletteTxt.setEditable(editConstituant);
       this.murExterieurCheckboxAfficher.setState(editConstituant);
       this.murInterieurCheckboxAfficher.setState(editConstituant);
       this.murSqueletteCheckboxAfficher.setState(editConstituant);
       
       // Porte
       
        this.porteHauteurTxt.setEditable(editConstituant);
        this.porteLargeurTxt.setEditable(editConstituant);
        this.portePositionTxt.setEditable(editConstituant);
    //    this.porteRayonArc.setEditable(editConstituant);
        this.porteCheckboxAfficher.setState(editConstituant);
        
       // Fenetre 
       this.fenetreHauteurTxt.setEditable(editConstituant);
       this.fenetreLargeurTxt.setEditable(editConstituant);
       this.fenetrePositionTxt.setEditable(editConstituant);
     // this.fenetreRayonArc.setEditable(editConstituant);
       this.fenetreCheckboxAfficher.setState(editConstituant);
       
       // Mur separateur
      this.murSeparateurEpaisseurTxt.setEditable(editConstituant);
      this.murSeparateurDistancePlancherTxt.setEditable(editConstituant);
      this.murSeparateurDistancePoutreArriceTxt.setEditable(editConstituant);
      this.murSeparateurCheckboxAfficher.setState(editConstituant);
       
    }
     
    private void configPoutreAr_KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configPoutreAr_KeyPressed
         valideInput(evt);
       
    }//GEN-LAST:event_configPoutreAr_KeyPressed

    private void configPoutreAr_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configPoutreAr_KeyReleased
        JTextField sender = (JTextField)evt.getSource();
        if(sender.getText().indexOf("/") !=-1 && !isNumeric(sender.getText().split("/")[0]) ){
             // ne rien faire si l'utilisateur tape le slache pour la premier foi. 
            return;
         }
       try{
            double inputValeurDecimal = this.microRoulotteControleur.StringToDouble(sender.getText());
            if( sender == poutreArriereLargeurTxt ){
                 if(inputValeurDecimal>this.microRoulotteControleur.getMicroRoulotte().getProfil().getLongueur()/3 )
                  {
                     this.lblMessageErreur.setText( "La largeur '"+inputValeurDecimal +"' n'est accapté.");
                     sender.setEditable(false);
                     sender.requestFocusInWindow();

                     return;
                   }
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.POUTRE_ARRIERE_LARGEUR , sender.getText());
            }else  if( sender == poutreArriereHauteurTxt){
                 if(inputValeurDecimal>this.microRoulotteControleur.getMicroRoulotte().getProfil().getHauteur()/3 )
                  {
                     this.lblMessageErreur.setText( "La hauteur '"+inputValeurDecimal +"' n'est accapté.");
                     sender.setEditable(false);
                     sender.requestFocusInWindow();

                     return;
                   }
                 
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.POUTRE_ARRIERE_HAUTEUR, sender.getText());
            }
            else  if( sender == poutreArrierePosition){
            
             

                  if(inputValeurDecimal<this.microRoulotteControleur.getMicroRoulotte().getProfil().getPositionCadran2().x 
                          || inputValeurDecimal<this.microRoulotteControleur.getMicroRoulotte().getProfil().getPositionCadran2().x+ this.microRoulotteControleur.getMicroRoulotte().getProfil().getLongueur() )
                  {
                     this.lblMessageErreur.setText( "La position '"+inputValeurDecimal +"' n'est accapté.");
                     sender.setEditable(false);
                     sender.requestFocusInWindow();

                     return;
                   }
              
                  microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.POUTRE_ARRIERE_POSITION , sender.getText());
            }
            
             drawingPanel.repaint();
         }
         catch( Exception ex){
            lblMessageErreur.setText(ex.getMessage());
         }
    }//GEN-LAST:event_configPoutreAr_KeyReleased

    private void jButtonEnregistrerProfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerProfilMouseClicked
      
        EditConstituants( true);
         
        changerVisibilite( jCheckBoxMenuItemVisibiliteProfil, false);

       // microRoulotteControleur.EnregistrerProfil();
       // this.poutreArrierePosition.setText(Double.toString(microRoulotteControleur.getMicroRoulotte().getPoutreArriere().getPositionCadran2().x));
        this.afficherConstituants();
        drawingPanel.repaint();
    }//GEN-LAST:event_jButtonEnregistrerProfilMouseClicked

    private void jMenuItemEditerProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditerProfilActionPerformed
       if(this.microRoulotteControleur.isEnModeDessinPanneau())
     {    EditConstituants( false);
       
        this.cacherConstituants();
        changerVisibilite( jCheckBoxMenuItemVisibiliteProfil, true);
     }
         if(this.microRoulotteControleur.isEnModeDessinBezier())
             {    this.microRoulotteControleur.setEnModeAffichage(false);
             drawingPanel.repaint();
     }
       
       
    }//GEN-LAST:event_jMenuItemEditerProfilActionPerformed

    private void jMenuItemEditerConstituantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditerConstituantActionPerformed
        EditConstituants( true);
        this.afficherConstituants();
        changerVisibilite( jCheckBoxMenuItemVisibiliteProfil, false);
    }//GEN-LAST:event_jMenuItemEditerConstituantActionPerformed

    private void drawingPanelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_drawingPanelMouseWheelMoved
       
    int retation = evt.getWheelRotation();
    this.lblMessageErreur.setText(Double.toString(retation));
    this.microRoulotteControleur.zoomer =true;
    if(retation <0)
    {
      this.microRoulotteControleur.zoom *=  1.1;
      drawingPanel.repaint();
    }
    if(retation>0)
    {
       this.microRoulotteControleur.zoom /= 1.1;
      drawingPanel.repaint();
    }
    }//GEN-LAST:event_drawingPanelMouseWheelMoved

    private void jCheckBoxMenuItemVisibilite_StateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemVisibilite_StateChanged
      
        JCheckBoxMenuItem sender = (JCheckBoxMenuItem)evt.getSource();
         changerVisibilite( sender, sender.getState());
         drawingPanel.repaint();
        
    }//GEN-LAST:event_jCheckBoxMenuItemVisibilite_StateChanged
    private void configToit_KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configToit_KeyPressed
        // TODO add your handling code here:
        valideInput(evt);
    }//GEN-LAST:event_configToit_KeyPressed

    private void configToit_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configToit_KeyReleased
        // TODO add your handling code here:
                JTextField sender = (JTextField)evt.getSource();
        if(sender.getText().indexOf("/") != -1 && !isNumeric(sender.getText().split("/")[0]))
        {return;}
        try{
                if(sender == toitEpaisseurTxt)
                {microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_EPAISSEUR , sender.getText());}
                else if(sender == toitNombrePoutreAvantTxt)
                {microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_NBRE_POUTRE_AVANT, sender.getText());}
                else if (sender == toitEpaisseurPoutreAvantsTxt)
                {microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_EPAISSEUR_POUTRE_AVANT, sender.getText());}
                else if(sender == toitEpaisseurPoutreAvantsTxt)
                {microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_EPAISSEUR_POUTRE_AVANT, sender.getText());}
                else if(sender == toitLargeurPoutreAvantTxt)
                {microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.TOIT_LARGEUR_POUTRE_AVANT, sender.getText());}
                
                drawingPanel.repaint();
            }
        
        catch( Exception ex){
            lblMessageErreur.setText(ex.getMessage());;
         }
    }//GEN-LAST:event_configToit_KeyReleased

    private void toitEpaisseurTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toitEpaisseurTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_toitEpaisseurTxtActionPerformed

    private void murSeparateurEpaisseurTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_murSeparateurEpaisseurTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_murSeparateurEpaisseurTxtActionPerformed

    private void configMurSeparateur_KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configMurSeparateur_KeyPressed
        // TODO add your handling code here:
        valideInput(evt);
    }//GEN-LAST:event_configMurSeparateur_KeyPressed

    private void configMurSeparateur_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configMurSeparateur_KeyReleased
        // TODO add your handling code here:
        JTextField sender = (JTextField)evt.getSource();
        if(sender.getText().indexOf("/") != -1 && !isNumeric(sender.getText().split("/")[0]))
        {return;}
        try{
                if(sender == murSeparateurEpaisseurTxt)
                {microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_EPAISSEUR , sender.getText());}
                else if(sender == murSeparateurDistancePlancherTxt)
                {microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_DISTANCE_PLACHER, sender.getText());}
                else if(sender == murSeparateurDistancePoutreArriceTxt)
                {microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.MUR_SEPARATEUR_DISTANCE_POUTRE_ARRIERE, sender.getText());}
                
                drawingPanel.repaint();
        }
        catch( Exception ex){
            lblMessageErreur.setText(ex.getMessage());;
         }
    }//GEN-LAST:event_configMurSeparateur_KeyReleased

    private void configHayon_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configHayon_KeyReleased

        JTextField sender = (JTextField)evt.getSource();
       // if(sender.getText().indexOf("/") !=-1 && !isNumeric(sender.getText().split("/")[0]) ){
            // ne rien faire si l'utilisateur tape le slache pour la premier foi.
       //     return;
       // }
        try{
            if( sender == hayonEpaisseurTxt ){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_EPAISSEUR , sender.getText());
            }else  if( sender == hayonPoidsTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_POIDS, sender.getText());
            }
            else  if( sender == hayonRayonArcSperieurTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_RAYON_ARC_SUP , sender.getText());
            }
            else  if( sender == hayonEpaisseurTraitScieTxt){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_EPAISSEUR_TRAIT_SCIE , sender.getText());

            }else  if( sender == hayonDistancePoutreArriere){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_DISTANCE_POUTRE_ARRIERE , sender.getText());

            }else  if( sender == hayonDistancePlacher){
                microRoulotteControleur.EditConstituant(MicroRoulotte.InputType.HAYON_DISTANCE_PLANCHER, sender.getText());
            }

            drawingPanel.repaint();
        }
        catch( Exception ex){
            lblMessageErreur.setText(ex.getMessage());
        }
    }//GEN-LAST:event_configHayon_KeyReleased

    private void configHayon_KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configHayon_KeyPressed
        valideInput(evt);

    }//GEN-LAST:event_configHayon_KeyPressed

    private void hayonDistancePoutreArriereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hayonDistancePoutreArriereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hayonDistancePoutreArriereActionPerformed

    private void hayonEpaisseurTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hayonEpaisseurTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hayonEpaisseurTxtActionPerformed

    private void checkboxAfficherItem_StateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkboxAfficherItem_StateChanged
       
        Checkbox sender = (Checkbox)evt.getSource();
        boolean editConstituant = sender.getState();
        if(sender == profilCheckboxAfficher){
             jCheckBoxMenuItemVisibiliteProfil.setState(editConstituant);
               // profil
            this.profilHauteurTxt.setEditable(!editConstituant);
            this.profilLongueurTxt.setEditable(!editConstituant);
            
          }else if(sender == plancherCheckboxAfficher){
                jCheckBoxMenuItemVisibilitePlancher.setState(editConstituant);
                   // plancher
                this.plancherEpaisseurText.setEditable(editConstituant);
                this.plancherMargeAvantTxt.setEditable(editConstituant);
                this.plancherMargeArriereTxt.setEditable(editConstituant);
                
          }else  if(sender == poutreArriereCheckboxAfficher){
              
                jCheckBoxMenuItemVisibilitePoutreArriere.setState(editConstituant);
                // poutre arriere
                this.poutreArrierePosition.setEditable(editConstituant);
                this.poutreArriereHauteurTxt.setEditable(editConstituant);
                this.poutreArriereLargeurTxt.setEditable(editConstituant);
                
          }else  if(sender == hayonCheckboxAfficher){
            jCheckBoxMenuItemVisibiliteHayon.setState(editConstituant);
                // hayon
            this.hayonEpaisseurTxt.setEditable(editConstituant);
            this.hayonPoidsTxt.setEditable(editConstituant);
            this.hayonRayonArcSperieurTxt.setEditable(editConstituant);
            this.hayonEpaisseurTraitScieTxt.setEditable(editConstituant);
            this.hayonDistancePoutreArriere.setEditable(editConstituant);
            this.hayonDistancePlacher.setEditable(editConstituant);
            
          }else if(sender == murExterieurCheckboxAfficher){
            jCheckBoxMenuItemVisibiliteMLCoucheExterieure.setState(editConstituant);
            // Mur lateral
            this.murLateralEpaisseurCoucheExterieurTxt.setEditable(editConstituant);
          
          }else if(sender == murInterieurCheckboxAfficher ){
             jCheckBoxMenuItemVisibiliteMLCoucheInterieur.setState(editConstituant);
                   this.murLateralEpaisseurCoucheInterieurTxt.setEditable(editConstituant);
           
          }else if(sender == murSqueletteCheckboxAfficher){
            jCheckBoxMenuItemVisibiliteMLCoucheSquelette.setState(editConstituant);
                 this.murLateralEpaisseurCoucheSqueletteTxt.setEditable(editConstituant);
                 
          }else if(sender == porteCheckboxAfficher){
            jCheckBoxMenuItemVisibilitePorte.setState(editConstituant);
               // Porte
                this.porteHauteurTxt.setEditable(editConstituant);
                this.porteLargeurTxt.setEditable(editConstituant);
                this.portePositionTxt.setEditable(editConstituant);
          //      this.porteRayonArc.setEditable(editConstituant);
                //  this.microRoulotteControleur.addPorte();
          }else if(sender == fenetreCheckboxAfficher){
            
           jCheckBoxMenuItemVisibiliteFenetre.setState(editConstituant);
               // Fenetre 
            this.fenetreHauteurTxt.setEditable(editConstituant);
            this.fenetreLargeurTxt.setEditable(editConstituant);
            this.fenetrePositionTxt.setEditable(editConstituant);
        //    this.fenetreRayonArc.setEditable(editConstituant);
            // this.microRoulotteControleur.addFenetre();
          }
        else   if(sender == toitCheckboxAfficher){
            jCheckBoxMenuItemVisibiliteToitPoutreAvant.setState(editConstituant);
            jCheckBoxMenuItemVisibiliteToitRecouvrement.setState(editConstituant);
              
              // toit
               this.toitEpaisseurTxt.setEditable(editConstituant);
               this.toitNombrePoutreAvantTxt.setEditable(editConstituant);
               this.toitEpaisseurPoutreAvantsTxt.setEditable(editConstituant);
               this.toitLargeurPoutreAvantTxt.setEditable(editConstituant);
          }
         else if(sender == murSeparateurCheckboxAfficher ){
               jCheckBoxMenuItemVisibiliteMurSeparateur.setState(editConstituant);
                   // Mur separateur
                this.murSeparateurEpaisseurTxt.setEditable(editConstituant);
                this.murSeparateurDistancePlancherTxt.setEditable(editConstituant);
                this.murSeparateurDistancePoutreArriceTxt.setEditable(editConstituant);
                
          }
          drawingPanel.repaint();
         
    }//GEN-LAST:event_checkboxAfficherItem_StateChanged

    private void jMenuItemCouleur_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCouleur_ActionPerformed

       JMenuItem currentSender = (JMenuItem)evt.getSource();
       JColorChooser jColorChooser = new JColorChooser();
       Color color = jColorChooser.showDialog(this,"Selecter une couleur",Color.BLACK);   
    
        changerCouleur(currentSender, color);
       drawingPanel.repaint();
      
       
    }//GEN-LAST:event_jMenuItemCouleur_ActionPerformed

    private void jMenuItemCouleurAideDessinMenuDragMouseReleased(javax.swing.event.MenuDragMouseEvent evt) {//GEN-FIRST:event_jMenuItemCouleurAideDessinMenuDragMouseReleased
     
    }//GEN-LAST:event_jMenuItemCouleurAideDessinMenuDragMouseReleased

    private void jMenuItemCouleurAideDessinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemCouleurAideDessinMouseClicked
        JColorChooser jColorChooser = new JColorChooser();
        Color color=jColorChooser.showDialog(this,"Selecter une couleur",Color.BLACK);   
       
        jColorChooser.addAncestorListener(new AncestorListener(){
            @Override
            public void ancestorAdded(AncestorEvent event) {
                 changerCouleur(jMenuItemCouleurHayon, ( (JColorChooser)event.getSource()).getColor());
                 drawingPanel.repaint();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        
        });
       
       
    }//GEN-LAST:event_jMenuItemCouleurAideDessinMouseClicked

    private void drawingPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawingPanelMouseMoved
         this.lblMessageErreur.setText("survole");
          Point mouse = evt.getPoint();
          Point mouseReel  = this.microRoulotteControleur.getCoordoneeReel(mouse,this.microRoulotteControleur.zoom);
          String nomElement =     this.microRoulotteControleur.getElementSelectionne(mouseReel);
          
          this.lblMessageErreur.setText(nomElement.replace('_', ' '));
    }//GEN-LAST:event_drawingPanelMouseMoved


    private void jMenuItemExporter2AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExporter2AActionPerformed
       
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exporter le plan en 2d");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                this.drawingPanel.paint(this.microRoulotteControleur.preparerPlan(this.drawingPanel.getSize().width , this.drawingPanel.getSize().height));
                this.microRoulotteControleur.exporter2D(fileToSave.getPath()+".jpg");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Un problème est survenu lors de l'enregistrement du plan", "Exception", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItemExporter2AActionPerformed

    private void JmenuItem_ZoomerDezoomer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmenuItem_ZoomerDezoomer
        this.microRoulotteControleur.zoomer=true;
        if(evt.getSource()==jMenuItemZoom)
            {
                this.microRoulotteControleur.zoom *=1.1;
            }
        else 
            {
                this.microRoulotteControleur.zoom /=1.1;
            }
        this.drawingPanel.repaint();
        
                
    }//GEN-LAST:event_JmenuItem_ZoomerDezoomer

    private void couleurs_Lbl_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_couleurs_Lbl_MouseClicked
        // TODO add your handling code here:
        MicroRoulotteControleur.TypeElement type = TypeElementSelectionnerCouleur((JLabel)evt.getSource());
        Color initialcolor=this.microRoulotteControleur.getCouleur(type);
        Color nouvelleCouleur =JColorChooser.showDialog(this,"Selecter une couleur",initialcolor);

        if(nouvelleCouleur != initialcolor){

        microRoulotteControleur.changerCouleur(type, nouvelleCouleur);
        drawingPanel.repaint();
}
        
    }//GEN-LAST:event_couleurs_Lbl_MouseClicked

    private void jMenuItem_ActionPerfomed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ActionPerfomed
       
        this.microRoulotteControleur.unDo();
        this.drawingPanel.repaint();
    }//GEN-LAST:event_jMenuItem_ActionPerfomed

    private void jMenuItemRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRedoActionPerformed
      this.microRoulotteControleur.reDo();
      this.drawingPanel.repaint();
    }//GEN-LAST:event_jMenuItemRedoActionPerformed

    private void drawingPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawingPanelMouseDragged
        if((this.microRoulotteControleur.isEnModeDessinBezier()==true)&&this.microRoulotteControleur.isEnModeAffichage()==false){
        Point p=(evt.getPoint());
        // this.jLabel1.setText("mouse draged: coordonnees "+evt.getX()+", "+evt.getY()+" ");
        // Point p3=this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().getPointInferieurDroit();
        // Point p0=this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().getPointInferieurGauche();
         Point p1=this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().getPointSuperieurGauche();
         Point p2=this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().getPointSuperieurDroit();
         Point pProchePointeur=this.microRoulotteControleur.retournerLePointLePlusProche(p1,p2,p);
         if (p1 ==pProchePointeur)this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().setPointSuperieurGauche(p); 
                else if (p2 ==pProchePointeur) this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().setPointSuperieurDroit(p);
                     else this.microRoulotteControleur.getMicroRoulotte().getProfilBezier().setPointInferieurDroit(p);        
        
        drawingPanel.repaint();
       }  
    }//GEN-LAST:event_drawingPanelMouseDragged

    private void jButtonEnregistrerProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnregistrerProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEnregistrerProfilActionPerformed

    private void drawingPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawingPanelMousePressed
       Point po = evt.getPoint();
    /*    if(this.microRoulotteControleur.isEnModeAffichage()==true)
      {  
        if(this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().getShape().contains(po)==true){
            //debut code fenetre
            double hauteur  = Double . parseDouble ( this.fenetreHauteurTxt.getText() ) ;
                 double largeur= Double . parseDouble ( this.fenetreLargeurTxt.getText() ) ;
            if(microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().getCouleur()==Color.GRAY)
            {   this.lblMessageErreur.setText(" mousepress sur fenetre pour vert");
                this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().setCouleur(Color.GREEN);
                this.microRoulotteControleur.addFenetre(largeur, hauteur);
            }else
                { microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().setCouleur(Color.GRAY);  
                 this.lblMessageErreur.setText(" mousepress sur fenetre pour gris");
                 //this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().setPosition(p);
                 this.microRoulotteControleur.addFenetre(largeur, hauteur);
          
           }   drawingPanel.repaint(); //fin code fenetre 
            }else  if( microRoulotteControleur.getMicroRoulotte().getMurLateral().getMurCoucheExterieur().getShape().contains(po)){
                //couche exterieure
            }
            else  if(microRoulotteControleur.getMicroRoulotte().getShapePlancher().contains(po)){
                //plancher
                //microRoulotteControleur.getMicroRoulotte().getShapePlancher().s
                
            }else  if(microRoulotteControleur.getMicroRoulotte().getPoutreArriere().getShape().contains(po)){
                //poutre arriere
                
            }else  if(microRoulotteControleur.getMicroRoulotte().getShapeMurInteterieur().contains(po)){
                //mur interieur
                
            }else  if(microRoulotteControleur.getMicroRoulotte().getShapeToit().contains(po)){
                //toit
             }  
      } //fin if modeaffichage true */
    }//GEN-LAST:event_drawingPanelMousePressed

    private void jButtonEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnregistrerActionPerformed
        this.microRoulotteControleur.setEnModeAffichage(true);
         this.microRoulotteControleur.setEnModeDessinBezier(true);
         this.microRoulotteControleur.InitialiserPointPoutre ();
         drawingPanel.repaint();
       
    }//GEN-LAST:event_jButtonEnregistrerActionPerformed

    private void jRadioButtonPanneauStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonPanneauStateChanged
       
    }//GEN-LAST:event_jRadioButtonPanneauStateChanged

    private void jRadioButtonPanneauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jRadioButtonPanneauPropertyChange
       
    }//GEN-LAST:event_jRadioButtonPanneauPropertyChange

    private void jRadioButtonCourbeBezierPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jRadioButtonCourbeBezierPropertyChange
       
    }//GEN-LAST:event_jRadioButtonCourbeBezierPropertyChange

    private void jRadioButtonCourbeBezierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCourbeBezierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCourbeBezierActionPerformed

    private void jCheckBoxMenuItemCapterUndoRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemCapterUndoRedoActionPerformed
         this.microRoulotteControleur.capterUndoRedo = this.jCheckBoxMenuItemCapterUndoRedo.isSelected();
    }//GEN-LAST:event_jCheckBoxMenuItemCapterUndoRedoActionPerformed

    private void jMenuItemExporterSVGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExporterSVGActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exporter le plan en 2d");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try{
                this.microRoulotteControleur.exporterSVG(fileToSave.getPath() + ".svg");
            }
            catch (Exception e)
            {
             JOptionPane.showMessageDialog(null, "Un problème est survenu lors de l'enregistrement du plan" + e.toString(), "Exception", JOptionPane.INFORMATION_MESSAGE);   
            }
        }
    }//GEN-LAST:event_jMenuItemExporterSVGActionPerformed

    private void couleur_Lbl_plancher2couleurs_Lbl_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_couleur_Lbl_plancher2couleurs_Lbl_MouseClicked
        // TODO add your handling code here:
        MicroRoulotteControleur.TypeElement type = TypeElementSelectionnerCouleur((JLabel)evt.getSource());
        Color initialcolor=this.microRoulotteControleur.getCouleur(type);
        Color nouvelleCouleur =JColorChooser.showDialog(this,"Selecter une couleur",initialcolor);

        if(nouvelleCouleur != initialcolor){

            microRoulotteControleur.changerCouleur(type, nouvelleCouleur);
            drawingPanel.repaint();
        }
    }//GEN-LAST:event_couleur_Lbl_plancher2couleurs_Lbl_MouseClicked

    private void jCheckBoxSelectionFenetreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxSelectionFenetreItemStateChanged
        if(jCheckBoxSelectionFenetre.isSelected()){
            this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().setSelect(true);
            lblMessageErreur.setText("Fenetre Select");

        }else{
            this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().setSelect(false);
            lblMessageErreur.setText("Fenetre Deselect");

        }  drawingPanel.repaint();
    }//GEN-LAST:event_jCheckBoxSelectionFenetreItemStateChanged

    private void jCheckBoxSelectionPorteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxSelectionPorteItemStateChanged
        if(jCheckBoxSelectionPorte.isSelected()){
            this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getPorte().setSelect(true);
            lblMessageErreur.setText("Porte Select");

        }else{
            this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getPorte().setSelect(false);
            lblMessageErreur.setText("Porte Deselect");

        }  drawingPanel.repaint();
    }//GEN-LAST:event_jCheckBoxSelectionPorteItemStateChanged

    private void jCheckBoxSelectionPorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSelectionPorteActionPerformed

    }//GEN-LAST:event_jCheckBoxSelectionPorteActionPerformed

    private void jCheckBoxFenetreAfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxFenetreAfItemStateChanged
         if(jCheckBoxFenetreAf.isSelected()){ 
          int hauteur  = Integer.parseInt( this.fenetreHauteurTxt.getText() ) ;
          int largeur= Integer.parseInt ( this.fenetreLargeurTxt.getText() ) ;    
          this.microRoulotteControleur.addFenetre(largeur, hauteur);
         lblMessageErreur.setText("Fenetre affich");
         drawingPanel.repaint();
    }else{                                                      
       this.microRoulotteControleur.removeFenetre();
        lblMessageErreur.setText("Fenetre enleve");
        drawingPanel.repaint();
       
    }      
    }//GEN-LAST:event_jCheckBoxFenetreAfItemStateChanged

    private void drawingPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawingPanelMouseReleased
           if((this.microRoulotteControleur.isEnModeDessinBezier()==true)&&this.microRoulotteControleur.isEnModeAffichage()==true){
        Point p=(evt.getPoint()); 
         if(((this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().isSelect()==true))){
           this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getFenetre().setPositionCoinInferieurGaucheFenetre(p);    
            } 
         if(((this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getPorte().isSelect()==true))){
           this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getPorte().setPositionCoinInferieurGauchePorte(p);    
            } 
         }  
         
        drawingPanel.repaint(); 
    }//GEN-LAST:event_drawingPanelMouseReleased

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
     
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
        
        if(jRadioButtonMenuItem2.isSelected() )
            
            this.microRoulotteControleur.setisEnCM(true);
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        
        if (jRadioButtonMenuItem1.isSelected() )
            
            this.microRoulotteControleur.setisEnCM(false);
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jSpinnerPasHorizontalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPasHorizontalStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerPasHorizontalStateChanged

    private void jSpinnerPasVerticalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPasVerticalStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerPasVerticalStateChanged

    private void jCheckBoxAjouterGrilleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxAjouterGrilleItemStateChanged
 
        if(jCheckBoxAjouterGrille.isSelected()){
            lblMessageErreur.setText("Ajout Grille");
            this.microRoulotteControleur.setGrilleAffiche(true);
            int gradX = Integer.parseInt(jSpinnerPasHorizontal.getValue().toString());
            int gradY = Integer.parseInt(jSpinnerPasVertical.getValue().toString());
            this.microRoulotteControleur.addGrille(gradX, gradY);

           
        }
         else
        {
            this.microRoulotteControleur.setGrilleAffiche(false);
            lblMessageErreur.setText("Retrait Grille");
           
        } 
         drawingPanel.repaint();
        
    }//GEN-LAST:event_jCheckBoxAjouterGrilleItemStateChanged

    private void fenetreHauteurTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fenetreHauteurTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fenetreHauteurTxtActionPerformed

    private void jCheckBoxPorteAfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxPorteAfItemStateChanged
        if(jCheckBoxPorteAf.isSelected()){
            lblMessageErreur.setText("Porte Select");
            int hauteur  = Integer.parseInt( this.porteHauteurTxt.getText() ) ;
            int largeur= Integer.parseInt ( this.porteLargeurTxt.getText() ) ;
            this.microRoulotteControleur.addPorte(largeur, hauteur);
            drawingPanel.repaint();
        }else{
            this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getPorte().setSelect(false);
            this.microRoulotteControleur.getMicroRoulotte().getMurLateral().getPorte().setVisibilite(false);
            lblMessageErreur.setText("Porte Deselect");
            drawingPanel.repaint();
        }
    }//GEN-LAST:event_jCheckBoxPorteAfItemStateChanged

    private void porteHauteurTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porteHauteurTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porteHauteurTxtActionPerformed

    private void murSeparateurDistancePlancherTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_murSeparateurDistancePlancherTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_murSeparateurDistancePlancherTxtActionPerformed

    private void jMenuItemOuvrirProjetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOuvrirProjetActionPerformed
        
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Ouvrir projet:");
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("ser");
            //chooser.setFileFilter(filter);
            FileFilter filter = new FileNameExtensionFilter("SER File","05");
            chooser.addChoosableFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    if(chooser.getSelectedFile().getPath().endsWith(".05")){
                        this.microRoulotteControleur.ouvrirFichier(chooser.getSelectedFile().getPath());
                  
                        drawingPanel.setMainWindow(this);
                        drawingPanel.repaint();
                    }else{
                        throw new ClassNotFoundException();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Problème lors de la manipulation d'un fichier", 
                            "IOException", JOptionPane.INFORMATION_MESSAGE);

                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Problème lors du chargement du fichier", 
                            "ClassNotFoundException", JOptionPane.INFORMATION_MESSAGE);

                }

            
        }
    }//GEN-LAST:event_jMenuItemOuvrirProjetActionPerformed

    private void jMenuItemEnregistrerSousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEnregistrerSousActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Enregistrer le fichier sous");
        int selectionfichier = fileChooser.showSaveDialog(this);

        if (selectionfichier == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try{
                this.microRoulotteControleur.enregistrerFichier(fileToSave.getPath() + ".05");
            }
            catch (Exception e)
            {
             JOptionPane.showMessageDialog(null, "Un problème est survenu lors de l'enregistrement du fichier" + e.toString(), "Exception", JOptionPane.INFORMATION_MESSAGE);   
            }
        }
    }//GEN-LAST:event_jMenuItemEnregistrerSousActionPerformed

      
private MicroRoulotteControleur.TypeElement TypeElementSelectionnerCouleur(JLabel sender){

    MicroRoulotteControleur.TypeElement typeElement = MicroRoulotteControleur.TypeElement.AIDE_DESSIN ;

    if(sender == couleur_Lbl_hayon){
    typeElement = MicroRoulotteControleur.TypeElement.HAYON ;
    }else if(sender == couleur_Lbl_epaisseur_c_Exterieur){
    typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_EXTERIEUR ;
    }else if(sender == couleur_Lbl_epaisseurC_Interieur){
    typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_INTERIEUR ;
    }else if(sender == couleur_Lbl_epaisseur_C_squel){
    typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_SQUELETTE ;
    }else if(sender == couleur_Lbl_murSeparateur){
    typeElement = MicroRoulotteControleur.TypeElement.MUR_SEPAREATEUR ;
    }
    else if(sender == couleur_Lbl_fenetre){
    typeElement = MicroRoulotteControleur.TypeElement.FENETRE ;
    }
    else if(sender ==couleur_Lbl_porte){
    typeElement = MicroRoulotteControleur.TypeElement.PORTE ;
    }else if(sender == couleur_Lbl_poutreArr){
    typeElement = MicroRoulotteControleur.TypeElement.POUTRE_ARRIERE ;
    }else if(sender == couleur_Lbl_toit){
    typeElement = MicroRoulotteControleur.TypeElement.POUTRE_AVANT ;
    }else if(sender == couleur_Lbl_plancher2){
    typeElement = MicroRoulotteControleur.TypeElement.PLANCHER ;
    }

    return typeElement;
}
    
    
    private void changerCouleur(JMenuItem sender, Color  color){
    
        // 
        MicroRoulotteControleur.TypeElement typeElement = MicroRoulotteControleur.TypeElement.AIDE_DESSIN ;
   
        if(sender == jMenuItemCouleurAideDessin){
            typeElement = MicroRoulotteControleur.TypeElement.AIDE_DESSIN ;
          } 
        else if(sender == jMenuItemCouleurHayon){
                 typeElement = MicroRoulotteControleur.TypeElement.HAYON ;
          }else if(sender == jMenuItemCouleurMurLateralCoucheExterieur){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_EXTERIEUR ;
          }else if(sender == jMenuItemCouleurMurLateralCoucheInterieur){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_INTERIEUR ;
          }else if(sender == jMenuItemCouleurMurLateralCoucheSequelette){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_SQUELETTE ;
          }else if(sender == jMenuItemCouleurMurSeparateur){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_SEPAREATEUR ;
          }else if(sender == jMenuItemCouleurPlancher){
                 typeElement = MicroRoulotteControleur.TypeElement.PLANCHER ;
          }
          else if(sender == jMenuItemCouleurFenetre){
            
                 typeElement = MicroRoulotteControleur.TypeElement.FENETRE ;
          }
          else if(sender ==jMenuItemCouleurPorte){
                 typeElement = MicroRoulotteControleur.TypeElement.PORTE ;
          }else if(sender == jMenuItemCouleurPoutreArriere){
                 typeElement = MicroRoulotteControleur.TypeElement.POUTRE_ARRIERE ;
          }else if(sender == jMenuItemCouleurProfil){
                 typeElement = MicroRoulotteControleur.TypeElement.PROFIL ;
          }else if(sender == jMenuItemCouleurToitPoutreAvant){
                 typeElement = MicroRoulotteControleur.TypeElement.POUTRE_AVANT ;
          }else if(sender == jMenuItemCouleurToitRecouvrement){
                 typeElement = MicroRoulotteControleur.TypeElement.TOIT_RECOUVREMENT ;
          }
          
         microRoulotteControleur.changerCouleur(typeElement, color);
    }
    
     
        private MicroRoulotteControleur.TypeElement TypeElementSelectionnerCouleur(JMenuItem sender){
    
        // 
        MicroRoulotteControleur.TypeElement typeElement = MicroRoulotteControleur.TypeElement.AIDE_DESSIN ;
   
        if(sender == jMenuItemCouleurAideDessin){
            typeElement = MicroRoulotteControleur.TypeElement.AIDE_DESSIN ;
          } 
        else if(sender == jMenuItemCouleurHayon){
                 typeElement = MicroRoulotteControleur.TypeElement.HAYON ;
          }else if(sender == jMenuItemCouleurMurLateralCoucheExterieur){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_EXTERIEUR ;
          }else if(sender == jMenuItemCouleurMurLateralCoucheInterieur){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_INTERIEUR ;
          }else if(sender == jMenuItemCouleurMurLateralCoucheSequelette){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_SQUELETTE ;
          }else if(sender == jMenuItemCouleurMurSeparateur){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_SEPAREATEUR ;
          }else if(sender == jMenuItemCouleurPlancher){
                 typeElement = MicroRoulotteControleur.TypeElement.PLANCHER ;
          }
          else if(sender == jMenuItemCouleurFenetre){
            
                 typeElement = MicroRoulotteControleur.TypeElement.FENETRE ;
          }
          else if(sender ==jMenuItemCouleurPorte){
                 typeElement = MicroRoulotteControleur.TypeElement.PORTE ;
          }else if(sender == jMenuItemCouleurPoutreArriere){
                 typeElement = MicroRoulotteControleur.TypeElement.POUTRE_ARRIERE ;
          }else if(sender == jMenuItemCouleurProfil){
                 typeElement = MicroRoulotteControleur.TypeElement.PROFIL ;
          }else if(sender == jMenuItemCouleurToitPoutreAvant){
                 typeElement = MicroRoulotteControleur.TypeElement.POUTRE_AVANT ;
          }else if(sender == jMenuItemCouleurToitRecouvrement){
                 typeElement = MicroRoulotteControleur.TypeElement.TOIT_RECOUVREMENT ;
          }
          
        return typeElement;
    }
    
    private void cacherConstituants(){
           // Plancher;
           this.changerVisibilite(jCheckBoxMenuItemVisibilitePlancher, false);
          // Poutre Arriere
          
           this.changerVisibilite(  jCheckBoxMenuItemVisibilitePoutreArriere, false);
           // Hayon 
      
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteHayon, false);
           
           // Toit
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteToitPoutreAvant, false);
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteToitRecouvrement, false);
           
           // Mur latereur
      
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteMLCoucheExterieure, false);
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteMLCoucheInterieur, false);
           this.changerVisibilite(jCheckBoxMenuItemVisibiliteMLCoucheSquelette, false);
           // Porte
           this.changerVisibilite( jCheckBoxMenuItemVisibilitePorte , false);
           
            // Fenetre
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteFenetre, false);
           
           // Mur Separateur
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteMurSeparateur, false);
              
    }
    private void afficherConstituants(){
           // Plancher;
           this.changerVisibilite(jCheckBoxMenuItemVisibilitePlancher, true);
          // Poutre Arriere
          
           this.changerVisibilite(  jCheckBoxMenuItemVisibilitePoutreArriere, true);
           // Hayon 
      
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteHayon, true);
           
           // Toit
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteToitPoutreAvant, true);
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteToitRecouvrement, true);
           
           // Mur latereur
      
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteMLCoucheExterieure, true);
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteMLCoucheInterieur, true);
           this.changerVisibilite(jCheckBoxMenuItemVisibiliteMLCoucheSquelette, true);
           // Porte
           
           this.jCheckBoxMenuItemVisibilitePorte.setState(false);
           this.changerVisibilite( jCheckBoxMenuItemVisibilitePorte , true);
            // Fenetre
    
           this.jCheckBoxMenuItemVisibiliteFenetre.setState(false);
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteFenetre, true);
           
           // Mur Separateur
           this.changerVisibilite(  jCheckBoxMenuItemVisibiliteMurSeparateur, true);
              
    }
    private void changerVisibilite(JCheckBoxMenuItem sender, boolean  visible){
    
        int transparence = visible ?255:0;
        sender.setState(visible);
        this.lblMessageErreur.setText(Boolean.toString(sender.getState()));
        
        MicroRoulotteControleur.TypeElement typeElement = MicroRoulotteControleur.TypeElement.AIDE_DESSIN ;
   
        if(sender == jCheckBoxMenuItemVisibiliteAideDessin){
            typeElement = MicroRoulotteControleur.TypeElement.AIDE_DESSIN ;
          } else if(sender == jCheckBoxMenuItemVisibiliteFenetre){
                 typeElement = MicroRoulotteControleur.TypeElement.FENETRE ;
          }
        else if(sender == jCheckBoxMenuItemVisibiliteHayon){
                 typeElement = MicroRoulotteControleur.TypeElement.HAYON ;
          }else if(sender == jCheckBoxMenuItemVisibiliteMLCoucheExterieure){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_EXTERIEUR ;
          }else if(sender == jCheckBoxMenuItemVisibiliteMLCoucheInterieur){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_INTERIEUR ;
          }else if(sender == jCheckBoxMenuItemVisibiliteMLCoucheSquelette){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_COUCHE_SQUELETTE ;
          }else if(sender == jCheckBoxMenuItemVisibiliteMurSeparateur){
                 typeElement = MicroRoulotteControleur.TypeElement.MUR_SEPAREATEUR ;
          }else if(sender == jCheckBoxMenuItemVisibilitePlancher){
                 typeElement = MicroRoulotteControleur.TypeElement.PLANCHER ;
          }else if(sender ==jCheckBoxMenuItemVisibilitePorte){
                 typeElement = MicroRoulotteControleur.TypeElement.PORTE ;
          }else if(sender == jCheckBoxMenuItemVisibilitePoutreArriere){
                 typeElement = MicroRoulotteControleur.TypeElement.POUTRE_ARRIERE ;
          }else if(sender == jCheckBoxMenuItemVisibiliteProfil){
                 typeElement = MicroRoulotteControleur.TypeElement.PROFIL ;
          }else if(sender == jCheckBoxMenuItemVisibiliteToitPoutreAvant){
                 typeElement = MicroRoulotteControleur.TypeElement.POUTRE_AVANT ;
          }else if(sender == jCheckBoxMenuItemVisibiliteToitRecouvrement){
                 typeElement = MicroRoulotteControleur.TypeElement.TOIT_RECOUVREMENT ;
          }
          
         microRoulotteControleur.changerVisibilite(typeElement, transparence);
    }
    
  
    
    
    private void valideInput(java.awt.event.KeyEvent evt){
        JTextField sender = (JTextField) evt.getSource();
        sender.setEditable(true);
        char c = evt.getKeyChar();
         
        
        if (!(Character.isDigit(c) || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE 
                || evt.getKeyCode() == KeyEvent.VK_SPACE && sender.getText().indexOf(KeyEvent.VK_SPACE) ==-1 // on n accepte pas deux fois espace
                || evt.getKeyCode() == KeyEvent.VK_DELETE 
                || evt.getKeyCode() == KeyEvent.VK_COMMA && sender.getText().indexOf(KeyEvent.VK_COMMA) ==-1  && sender.getText().indexOf(KeyEvent.VK_PERIOD) ==-1// on n accepte pas deux fois la virgule ou bien le point decimal
                || evt.getKeyCode() == KeyEvent.VK_PERIOD && sender.getText().indexOf(KeyEvent.VK_COMMA) ==-1  && sender.getText().indexOf(KeyEvent.VK_PERIOD) ==-1 // on n accepte pas deux fois  le point ou bien la virgule
                || sender.getText().indexOf("/") ==-1  && c == '/')
                ) {
            if(evt.getKeyCode() == KeyEvent.VK_COMMA)  
                evt.setKeyChar('.');
            this.lblMessageErreur.setText( "Le charactère '"+c +"' n'est accapté.");
            sender.setEditable(false);
            sender.requestFocusInWindow();

            return;
            
        } else {
             this.lblMessageErreur.setText( "");

           sender.setEditable(true);
        }
    }
  
          
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonTopPanel;
    private javax.swing.JLabel couleur_Lbl_epaisseurC_Interieur;
    private javax.swing.JLabel couleur_Lbl_epaisseur_C_squel;
    private javax.swing.JLabel couleur_Lbl_epaisseur_c_Exterieur;
    private javax.swing.JLabel couleur_Lbl_fenetre;
    private javax.swing.JLabel couleur_Lbl_hayon;
    private javax.swing.JLabel couleur_Lbl_murSeparateur;
    private javax.swing.JLabel couleur_Lbl_plancher2;
    private javax.swing.JLabel couleur_Lbl_porte;
    private javax.swing.JLabel couleur_Lbl_poutreArr;
    private javax.swing.JLabel couleur_Lbl_toit;
    public ca.ulaval.glo2004.gui.DrawingPanel drawingPanel;
    private java.awt.Checkbox fenetreCheckboxAfficher;
    private javax.swing.JTextField fenetreHauteurTxt;
    private javax.swing.JTextField fenetreLargeurTxt;
    private javax.swing.JTextField fenetrePositionTxt;
    private java.awt.Checkbox hayonCheckboxAfficher;
    private javax.swing.JTextField hayonDistancePlacher;
    private javax.swing.JTextField hayonDistancePoutreArriere;
    private javax.swing.JTextField hayonEpaisseurTraitScieTxt;
    private javax.swing.JTextField hayonEpaisseurTxt;
    private javax.swing.JTextField hayonPoidsTxt;
    private javax.swing.JTextField hayonRayonArcSperieurTxt;
    private javax.swing.JButton jButtonEnregistrer;
    private javax.swing.JButton jButtonEnregistrerProfil;
    private javax.swing.JCheckBox jCheckBoxAjouterGrille;
    private javax.swing.JCheckBox jCheckBoxFenetreAf;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemCapterUndoRedo;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteAideDessin;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteFenetre;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteHayon;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteMLCoucheExterieure;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteMLCoucheInterieur;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteMLCoucheSquelette;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteMurSeparateur;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibilitePlancher;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibilitePorte;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibilitePoutreArriere;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteProfil;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteToitPoutreAvant;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVisibiliteToitRecouvrement;
    private javax.swing.JCheckBox jCheckBoxPorteAf;
    private javax.swing.JCheckBox jCheckBoxSelectionFenetre;
    private javax.swing.JCheckBox jCheckBoxSelectionPorte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenuAffichage;
    private javax.swing.JMenu jMenuCouleur;
    private javax.swing.JMenu jMenuCouleurMurLateral;
    private javax.swing.JMenu jMenuCouleurToit;
    private javax.swing.JMenu jMenuEdition;
    private javax.swing.JMenu jMenuExporter;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JMenuItem jMenuItemCouleurAideDessin;
    private javax.swing.JMenuItem jMenuItemCouleurFenetre;
    private javax.swing.JMenuItem jMenuItemCouleurHayon;
    private javax.swing.JMenuItem jMenuItemCouleurMurLateralCoucheExterieur;
    private javax.swing.JMenuItem jMenuItemCouleurMurLateralCoucheInterieur;
    private javax.swing.JMenuItem jMenuItemCouleurMurLateralCoucheSequelette;
    private javax.swing.JMenuItem jMenuItemCouleurMurSeparateur;
    private javax.swing.JMenuItem jMenuItemCouleurPlancher;
    private javax.swing.JMenuItem jMenuItemCouleurPorte;
    private javax.swing.JMenuItem jMenuItemCouleurPoutreArriere;
    private javax.swing.JMenuItem jMenuItemCouleurProfil;
    private javax.swing.JMenuItem jMenuItemCouleurToitPoutreAvant;
    private javax.swing.JMenuItem jMenuItemCouleurToitRecouvrement;
    private javax.swing.JMenuItem jMenuItemCreerNouveauProjet;
    private javax.swing.JMenuItem jMenuItemEditerConstituant;
    private javax.swing.JMenuItem jMenuItemEditerProfil;
    private javax.swing.JMenuItem jMenuItemEnregistrer;
    private javax.swing.JMenuItem jMenuItemEnregistrerSous;
    private javax.swing.JMenuItem jMenuItemExporter2A;
    private javax.swing.JMenuItem jMenuItemExporterSVG;
    private javax.swing.JMenuItem jMenuItemFermer;
    private javax.swing.JMenuItem jMenuItemOuvrirProjet;
    private javax.swing.JMenuItem jMenuItemUnZoom;
    private javax.swing.JMenuItem jMenuItemUndo;
    private javax.swing.JMenuItem jMenuItemZoom;
    private javax.swing.JMenu jMenuVisibilite;
    private javax.swing.JMenu jMenuVisibiliteMurLateral;
    private javax.swing.JMenu jMenuVisibiliteToit;
    private javax.swing.JPanel jPanalPoudreArriere;
    private javax.swing.JPanel jPanalPoudreArriere1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelChoisirProfil;
    public static javax.swing.JPanel jPanelConstituants;
    private javax.swing.JPanel jPanelDessin;
    private javax.swing.JPanel jPanelFenetre;
    private javax.swing.JPanel jPanelHayon;
    private javax.swing.JPanel jPanelMurLateral;
    private javax.swing.JPanel jPanelMurSeparateur;
    private javax.swing.JPanel jPanelOuverture;
    private javax.swing.JPanel jPanelPanneauRectangle;
    private javax.swing.JPanel jPanelPlancher;
    private javax.swing.JPanel jPanelPlancherPoutreArriere;
    private javax.swing.JPanel jPanelPorte;
    private javax.swing.JPanel jPanelToit;
    private javax.swing.JRadioButton jRadioButtonCourbeBezier;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButton jRadioButtonPanneau;
    private javax.swing.JSpinner jSpinnerPasHorizontal;
    private javax.swing.JSpinner jSpinnerPasVertical;
    private javax.swing.JLabel lblMessageErreur;
    private javax.swing.JPanel mainPanel;
    private java.awt.Checkbox murExterieurCheckboxAfficher;
    private java.awt.Checkbox murInterieurCheckboxAfficher;
    private javax.swing.JTextField murLateralEpaisseurCoucheExterieurTxt;
    private javax.swing.JTextField murLateralEpaisseurCoucheInterieurTxt;
    private javax.swing.JTextField murLateralEpaisseurCoucheSqueletteTxt;
    private java.awt.Checkbox murSeparateurCheckboxAfficher;
    private javax.swing.JTextField murSeparateurDistancePlancherTxt;
    private javax.swing.JTextField murSeparateurDistancePoutreArriceTxt;
    private javax.swing.JTextField murSeparateurEpaisseurTxt;
    private java.awt.Checkbox murSqueletteCheckboxAfficher;
    private java.awt.Checkbox plancherCheckboxAfficher;
    private javax.swing.JTextField plancherEpaisseurText;
    private javax.swing.JTextField plancherMargeArriereTxt;
    private javax.swing.JTextField plancherMargeAvantTxt;
    private java.awt.Checkbox porteCheckboxAfficher;
    private javax.swing.JTextField porteHauteurTxt;
    private javax.swing.JTextField porteLargeurTxt;
    private javax.swing.JTextField portePositionTxt;
    private java.awt.Checkbox poutreArriereCheckboxAfficher;
    private javax.swing.JTextField poutreArriereHauteurTxt;
    private javax.swing.JTextField poutreArriereLargeurTxt;
    private javax.swing.JTextField poutreArrierePosition;
    private java.awt.Checkbox profilCheckboxAfficher;
    private javax.swing.JTextField profilHauteurTxt;
    private javax.swing.JTextField profilLongueurTxt;
    private java.awt.Checkbox toitCheckboxAfficher;
    private javax.swing.JTextField toitEpaisseurPoutreAvantsTxt;
    private javax.swing.JTextField toitEpaisseurTxt;
    private javax.swing.JTextField toitLargeurPoutreAvantTxt;
    private javax.swing.JTextField toitNombrePoutreAvantTxt;
    private javax.swing.JMenuBar topMenuBarMainWindow;
    // End of variables declaration//GEN-END:variables
}
