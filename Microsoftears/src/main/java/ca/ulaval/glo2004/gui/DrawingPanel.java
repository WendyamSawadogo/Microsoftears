/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.gui;

/**
 *
 * @author stell
 */
import ca.ulaval.glo2004.Domaine.drawing.MicroRoulotteDrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
 
  public class DrawingPanel extends javax.swing.JPanel
  {
    private MainWindow mainWindow;
    public DrawingPanel()
    {
    }
    
    public DrawingPanel(MainWindow mainWindow){
         this.mainWindow = mainWindow;
         /*this.setSize(1000, 1000);
         setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
         int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
         setPreferredSize(new Dimension(width/2,1));
         setVisible(true);
         int height = (int)(width*0.5);*/
       
    }
  
    @Override
     protected void paintComponent(Graphics g)
     {
        if (mainWindow != null)
        {
            super.paintComponent(g); 
            Graphics2D g2 = (Graphics2D)g;
            if(mainWindow.microRoulotteControleur.zoomer){
                AffineTransform at = new AffineTransform();
                at.scale(mainWindow.microRoulotteControleur.zoom, mainWindow.microRoulotteControleur.zoom);
                double preZoom = mainWindow.microRoulotteControleur.zoom;
                g2.transform(at);
                mainWindow.microRoulotteControleur.zoomer=false;
            }
            else {
                 mainWindow.microRoulotteControleur.zoom=1;
            }
            
            MicroRoulotteDrawer mainDrawer = new MicroRoulotteDrawer(mainWindow.microRoulotteControleur);
            mainDrawer.drawMicroRoulotte(g);
            
            
            
        }
     }
 
     public MainWindow getMainWindow()
     {
         return mainWindow;
     }
 
     public void setMainWindow(MainWindow mainWindow)
     {
         this.mainWindow = mainWindow;
     }
  }
