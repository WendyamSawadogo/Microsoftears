/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class PoutreArriere extends Poutre   implements Serializable{
   
    private static final PoutreArriere SINGLETON_INSTANCE = new PoutreArriere();
    public static PoutreArriere getInstance() {
       return SINGLETON_INSTANCE;
     }
   
   private Polygon shapePoutre;
    PoutreArriere() {
        super(0,0, new Point(0,0), Color.ORANGE);
    }

    public Polygon getShapePoutre() {
        return shapePoutre;
    }

    public void setShapePoutre(Polygon shapePoutre) {
        this.shapePoutre = shapePoutre;
    }
   
}
