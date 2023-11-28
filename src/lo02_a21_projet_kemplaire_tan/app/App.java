package lo02_a21_projet_kemplaire_tan.app;

import java.awt.EventQueue;

import lo02_a21_projet_kemplaire_tan.modele.jeu.*;
import lo02_a21_projet_kemplaire_tan.view.ViewInitialiser;

/**
* Classe App
*/
/**
* 
* Classe permettant de lancer le programme du jeu
* Contient la m�thode main()
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.app
* @see lo02_a21_projet_kemplaire_tan.modele.jeu
* @see lo02_a21_projet_kemplaire_tan.view.ViewInitialiser
* 
*/
public class App {

	/**
	 * M�thode d'�x�cution du programme
	 * @param args Param�tres apport�s � l'execution du programme
	 */
	public static void main(String[] args) {
				
		//Afficher la fen�tre de jeu
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewInitialiser window = new ViewInitialiser();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
}
