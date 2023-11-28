package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe DefausseUneCarteAleatoire
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : DefausseUneCarteAleatoire
* <br>Description : Le joueur accusateur défausse une carte aléatoire de sa main
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
public class DefausseUneCarteAleatoire extends Effet {
	
	/**
	 * Constructeur de la classe DefausseUneCarteAleatoire
	 */
	public DefausseUneCarteAleatoire() {
		super(EffetNom.DEFAUSSE_CARTE_ALEATOIRE.getDescriptionEffet());
		setNom(EffetNom.DEFAUSSE_CARTE_ALEATOIRE);
	}

	/**
	 * Permet d'executer l'effet : : Le joueur Concerne défausse une carte aléatoire de sa main, s'il a au moins une carte dans sa main
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();

		if(joueurAccusateur.getMain().size() > 0) {
		
			Carte carteChoisie = joueurAccusateur.prendreCarteAleatoire(joueurAccusateur.getMain());
			
			execEffet = joueurConcerne.getNom()+" a défaussé la carte "+carteChoisie.getNom();
			Partie.getInstance().setExecutionEffet(execEffet);
			
			joueurAccusateur.defausseCarte(carteChoisie);
			joueurAccusateur.removeCarteMain(carteChoisie);
		
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - "+joueurAccusateur.getNom()+" n'a plus de carte !");
			
		}
		
	}
	
}
