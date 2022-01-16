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
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class Poutre extends Plan2D  implements Serializable{
    
  
    public Poutre(double hauteur, double largeur, Point Positionnement, Color couleur) {
        super(Positionnement,largeur, hauteur, couleur);
      
    }
    
     public Poutre(){super(new Point(),0, 0,  Color.BLACK); }
   
}
