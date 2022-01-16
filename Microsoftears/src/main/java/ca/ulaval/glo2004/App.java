package ca.ulaval.glo2004;


import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;





public class App {
    //Exemple de creation d'une fenetre et d'un bouton avec swing. Lorsque vous allez creer votre propre GUI
    // Vous n'aurez pas besoin d'ecrire tout ce code, il sera genere automatiquement par intellij ou netbeans
    // Par contre vous aurez a creer les actions listener pour vos boutons et etc.
    public static void main(String[] args) {
        
       ca.ulaval.glo2004.gui.MainWindow mainWindow = new ca.ulaval.glo2004.gui.MainWindow();
        
     
        mainWindow.setVisible(true);        
        // Creation du main window
       /* JFrame frame = new JFrame();

        // Exemple tres simple de comment ajouter un evenement lors de la fermeture de l'application avec le X
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Creation d'un bouton
        JButton button = new JButton("click");

        // On place le bouton a un endroit dans la fenetre
        button.setBounds(130, 100, 100, 40);

        // On lui ajouter un action listener qui declanche une methode qui possede qui possede le code devant etre
        // executer lorsque le bouton est clique
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


        // On ajoute le bouton a notre fenetre principal
        frame.add(button);

        // Ici on ne fait que changer les propriete de la fenetre
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        // Simple commande permettant d'ecrire sur la console
        System.out.println("hello world"); */
    }
}

