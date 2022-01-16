/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class Porte extends Ouverture  implements Serializable {
    Point positionCoinInferieurGauchePorte=new Point(0,0);
   private Polygon ShapePolygonFenetre;
     private boolean select=false;
    private static final Porte SINGLETON_INSTANCE = new Porte();
   public static Porte getInstance() {
      return SINGLETON_INSTANCE;
    }
    private Porte() {
       super();
    }

    public Point getPositionCoinInferieurGauchePorte() {
        return positionCoinInferieurGauchePorte;
    }

    public void setPositionCoinInferieurGauchePorte(Point positionCoinInferieurGauchePorte) {
        this.positionCoinInferieurGauchePorte = positionCoinInferieurGauchePorte;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    
}
