package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe PrendreCarteProchainJoueur
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : PrendreCarteProchainJoueur
* <br>Description : Avant leur tour, prenez une carte de leur main
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
public class PrendreCarteProchainJoueur extends Effet {
	
	/**
	 * Constructeur de la classe PrendreCarteProchainJoueur
	 */
	public PrendreCarteProchainJoueur() {
		super(EffetNom.PRENDRE_CARTE_PROCHAIN_JOUEUR.getDescriptionEffet());
		setNom(EffetNom.PRENDRE_CARTE_PROCHAIN_JOUEUR);
	}
	
	/**
	 * Permet d'executer l'effet : Prend une carte au hasard dans la main du prochain joueur, si il a au moins une carte dans sa main
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		if(Partie.getInstance().getProchainJoueur().getMain().size() > 0) {
			
			Carte carteChoisie = joueurConcerne.prendreCarteAleatoire(Partie.getInstance().getProchainJoueur().getMain());
		
			execEffet = joueurConcerne.getNom()+" prend la carte "+carteChoisie.getNom()+" de la main de "+Partie.getInstance().getProchainJoueur().getNom()+" !";
			Partie.getInstance().setExecutionEffet(execEffet);
			
			Partie.getInstance().getProchainJoueur().removeCarteMain(carteChoisie);
			joueurConcerne.ajouterCarteMain(carteChoisie);
		
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - "+Partie.getInstance().getProchainJoueur().getNom()+" n'a plus de carte !");
			
		}
		
	}
	
}