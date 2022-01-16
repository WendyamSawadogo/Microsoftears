/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import ca.ulaval.glo2004.Domaine.Poutre;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class PoutreAvant extends Poutre  implements Serializable{
    
    public PoutreAvant(double epaisseur,  double largeur, Point Positionnement, Color couleur) {
        super(epaisseur, largeur, Positionnement, couleur);
    }
    protected PoutreAvant(){}
}
