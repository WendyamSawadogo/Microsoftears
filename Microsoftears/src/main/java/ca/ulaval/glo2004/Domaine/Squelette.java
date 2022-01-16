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
public class Squelette extends CoucheInterieur  implements Serializable{

    private static final Squelette SINGLETON_INSTANCE = new Squelette();
   public static Squelette getInstance() {
      return SINGLETON_INSTANCE;
    }

    private Squelette() {
        super();
        this.setCouleur(Color.YELLOW);
    }
    
}
