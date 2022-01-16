/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class Interieur extends CoucheInterieur  implements Serializable{

   private static final Interieur SINGLETON_INSTANCE = new Interieur();
   public static Interieur getInstance() {
      return SINGLETON_INSTANCE;
    }

   private Interieur() {
        super();
        this.setCouleur(Color.PINK);
    }
    
}
