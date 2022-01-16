/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine.Plan;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class Couleur  implements Serializable{
    
    int rouge; int vert ; int bleu; 
    int transparence=255;
    
    protected Couleur() {
        Color couleur = Color.BLACK;
        rouge = couleur.getRed(); 
        vert = couleur.getGreen(); 
        bleu = couleur.getBlue(); 
        transparence = 255; 
      
    }
    public Couleur(Color couleur) {
        rouge = couleur.getRed(); 
        vert = couleur.getGreen(); 
        bleu = couleur.getBlue(); 
        transparence = couleur.getTransparency(); 
      
    }
     
    public Couleur(int rouge, int vert , int bleu, int transparence) {
         this.rouge = rouge; 
         this.vert = vert; 
         this.bleu = bleu; 
         this.transparence = transparence; 
    }
    public void setCouleur(Color couleur) {
        rouge = couleur.getRed(); 
        vert = couleur.getGreen(); 
        bleu = couleur.getBlue(); 
        //transparence = couleur.getTransparency(); 
    }
    public void setCouleur(int rouge, int vert , int bleu, int transparence) {
         this.rouge = rouge; 
         this.vert = vert; 
         this.bleu = bleu; 
         this.transparence = transparence; 
    }
    public Color getCouleur(){ return new Color(  this.rouge , this.vert , this.bleu ,this.transparence);}
    
    public void setVisibilite(boolean visible){
     transparence = visible?255:0;
    }
    public void setTransparence(int tranparence){
        if(tranparence>=0 && tranparence<=255)
        transparence = tranparence;
    }
    
    public boolean Visible(){return this.transparence>0;}
}
