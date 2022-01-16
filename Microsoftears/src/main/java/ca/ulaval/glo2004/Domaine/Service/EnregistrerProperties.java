/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tchmi
 */
package ca.ulaval.glo2004.Domaine.Service;
import ca.ulaval.glo2004.Domaine.Fenetre;
import ca.ulaval.glo2004.Domaine.MicroRoulotte;
import ca.ulaval.glo2004.Domaine.MicroRoulotteControleur;
import ca.ulaval.glo2004.Domaine.Profil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class EnregistrerProperties {
    
     public static void sauvegarderProperties() {

        try (OutputStream output = new FileOutputStream("D:/ULAVAL/config.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty(MicroRoulotte.InputType.PROFIL_LONGUEUR.toString(), Double.toString( Profil.getInstance().getLongueur()));
            prop.setProperty(MicroRoulotte.InputType.PROFIL_HAUTEUR.toString(), Double.toString( Profil.getInstance().getHauteur()));
            prop.setProperty(MicroRoulotte.InputType.FENETRE_HAUTEUR.toString(), Double.toString( Fenetre.getInstance().getHauteur()));
            prop.setProperty(MicroRoulotte.InputType.FENETRE_LARGEUR.toString(), Double.toString( Fenetre.getInstance().getLargeur()));
            //prop.setProperty(MicroRoulotte.InputType.PORTE_HAUTEUR.toString(), Double.toString( Porte.getInstance().getHauteur));

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
     
    public static void ouvrirProjet() {

        try (InputStream input = new FileInputStream("D:/ULAVAL/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            
            String hauteurSaveMicrol
                    = prop.getProperty(MicroRoulotte.InputType.PROFIL_LONGUEUR.toString(), Double.toString( Profil.getInstance().getLongueur()));
            // get the property value and print it out
            /*System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"))*/

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
