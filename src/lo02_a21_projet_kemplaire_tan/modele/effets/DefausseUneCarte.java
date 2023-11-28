package lo02_a21_projet_kemplaire_tan.modele.effets;

import java.util.ArrayList;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Bot;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe DefausseUneCarte
*/
/**
* H�rite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : DefausseUneCarte
* <br>Description : Le joueur d�fausse une carte de sa main
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Bot
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Carte
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public class DefausseUneCarte extends Effet {
	
	/**
	 * Constructeur de la classe DefausseUneCarte
	 */
	public DefausseUneCarte() {
		super(EffetNom.DEFAUSSE_UNE_CARTE.getDescriptionEffet());
		setNom(EffetNom.DEFAUSSE_UNE_CARTE);
	}
	
	/**
	 * Permet d'executer l'effet : Le joueur Concerne choisi une carte et la d�fausse de sa main, s'il a au moins une carte dans sa main
	 * @param joueurConcerne - Joueur qui ex�cute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		ArrayList<Carte> main = joueurConcerne.getMain();

		if(main.size() > 0) {
			
			Carte carteChoisie;
			
			if(joueurConcerne instanceof Bot) {
				carteChoisie = joueurConcerne.choisirCarteADefausser(main);
			} else {
				carteChoisie = Partie.getInstance().getCarteChoisie();
			}
			
			execEffet = joueurConcerne.getNom()+" a d�fauss� la carte "+carteChoisie.getNom();
			Partie.getInstance().setExecutionEffet(execEffet);
			
			joueurConcerne.defausseCarte(carteChoisie);
			joueurConcerne.removeCarteMain(carteChoisie);
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - "+joueurConcerne.getNom()+" n'a plus de carte !");
			
		}
		
	}
}