/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class CoucheExterieur extends ContrePlaque  implements Serializable{

   private static final CoucheExterieur SINGLETON_INSTANCE = new CoucheExterieur();
   public static CoucheExterieur getInstance() {
      return SINGLETON_INSTANCE;
    }
  

   private CoucheExterieur()  {
       super();
       this.setCouleur(Color.magenta);
    }

    
    
}
