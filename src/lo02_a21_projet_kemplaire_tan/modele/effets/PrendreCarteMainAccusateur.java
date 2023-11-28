package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe PrendreCarteMainAccusateur
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : PrendreCarteMainAccusateur
* <br>Description : Prendre une carte de la main de l'accusateur
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Carte
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public class PrendreCarteMainAccusateur extends Effet {
	
	/**
	 * Constructeur de la classe PrendreCarteMainAccusateur
	 */
	public PrendreCarteMainAccusateur() {
		super(EffetNom.PRENDRE_CARTE_MAIN_ACCUSATEUR.getDescriptionEffet());
		setNom(EffetNom.PRENDRE_CARTE_MAIN_ACCUSATEUR);
	}
	
	/**
	 * Permet d'executer l'effet : Prend une carte au hasard de la main du joueur accusateur, si l'accusateur a au moins une carte dans sa main
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		if(joueurAccusateur.getMain().size() > 0) {
			
			execEffet = joueurConcerne.getNom()+" prend une carte de la main de "+joueurAccusateur.getNom()+" !";
			Partie.getInstance().setExecutionEffet(execEffet);
			
			Carte carteChoisie = joueurConcerne.prendreCarteAleatoire(joueurAccusateur.getMain());
			
			joueurAccusateur.removeCarteMain(carteChoisie);
			joueurConcerne.ajouterCarteMain(carteChoisie);
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - "+joueurAccusateur.getNom()+" n'a plus de carte !");
			
		}
		
	}
	
}
