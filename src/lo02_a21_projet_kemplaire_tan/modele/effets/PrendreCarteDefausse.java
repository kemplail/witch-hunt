package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe PrendreCarteDefausse
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : PrendreCarteDefausse
* <br>Description : Le joueur prend une carte dans la défausse et défausse la carte utilisée
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
public class PrendreCarteDefausse extends Effet {
	
	/**
	 * Constructeur de la classe PrendreCarteDefausse
	 */
	public PrendreCarteDefausse() {
		super(EffetNom.PRENDRE_CARTE_DEFAUSSE.getDescriptionEffet());
		setNom(EffetNom.PRENDRE_CARTE_DEFAUSSE);
	}
	
	/**
	 * Permet d'executer l'effet : Le joueur prend une carte de la défausse, si la défausse n'est pas vide
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		Carte carteRetiree = Partie.getInstance().retireCarteDefausse();
		
		if(carteRetiree != null) {
			
			execEffet = joueurConcerne.getNom()+" a pris la carte "+carteRetiree.getNom()+" de la défausse !";
			Partie.getInstance().setExecutionEffet(execEffet);
			
			if(carteRetiree != null) {
				
				joueurConcerne.ajouterCarteMain(carteRetiree);
				
			}
			
			joueurConcerne.defausseCarte(joueurConcerne.getDerniereCarteJouee());
			joueurConcerne.removeCarteMain(joueurConcerne.getDerniereCarteJouee());
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - La défausse est vide.");
			
		}
		
	}
	
}