package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe RegarderIdentiteProchainJoueur
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : RegarderIdentiteProchainJoueur
* <br>Description : Regarder l'identité du prochain joueur
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
public class RegarderIdentiteProchainJoueur extends Effet {
	
	/**
	 * Constructeur de la classe RegarderIdentiteProchainJoueur
	 */
	public RegarderIdentiteProchainJoueur() {
		super(EffetNom.REGARDER_IDENTITE_PROCHAIN_JOUEUR.getDescriptionEffet());
		setNom(EffetNom.REGARDER_IDENTITE_PROCHAIN_JOUEUR);
	}
	
	/**
	 * Permet d'executer l'effet : joueurConcerne consulte l'identité du prochain joueur
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		joueurConcerne.regarderIdentite(Partie.getInstance().getProchainJoueur());
		
	}
	
}
