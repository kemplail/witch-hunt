package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe PrendreProchainTour
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : PrendreProchainTour
* <br>Description : Le joueur prend le prochain tour
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public class PrendreProchainTour extends Effet {
	
	/**
	 * Constructeur de la classe PrendreProchainTour
	 */
	public PrendreProchainTour() {
		super(EffetNom.PRENDRE_PROCHAIN_TOUR.getDescriptionEffet());
		setNom(EffetNom.PRENDRE_PROCHAIN_TOUR);
	}
	
	/**
	 * Permet d'executer l'effet : joueurConcerne prend le prochain tour
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet;
		
		execEffet = joueurConcerne.getNom()+" prend le prochain tour !";
		Partie.getInstance().setExecutionEffet(execEffet);
		
		Partie.getInstance().setProchainJoueur(joueurConcerne);
		
	}
	
}
