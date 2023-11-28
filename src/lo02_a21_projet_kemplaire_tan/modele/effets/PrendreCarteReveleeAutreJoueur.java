package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Bot;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe PrendreCarteReveleeAutreJoueur
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : PrendreCarteReveleeAutreJoueur
* <br>Description : Le joueur prend dans sa main une carte révélée par un autre joueur
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Bot
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public class PrendreCarteReveleeAutreJoueur extends Effet {
	
	/**
	 * Constructeur de la classe PrendreCarteReveleeAutreJoueur
	 */
	public PrendreCarteReveleeAutreJoueur() {
		super(EffetNom.PRENDRE_CARTE_REVELEE_AUTRE_JOUEUR.getDescriptionEffet());
		setNom(EffetNom.PRENDRE_CARTE_REVELEE_AUTRE_JOUEUR);
	}
	
	/**
	 * Permet d'executer l'effet : Choisis un joueur à qui prendre une carte révélée et lui prend cette carte, possible si au moins un joueur autre que joueurConcerne a une carte révélée
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet;
		
		if(joueurConcerne.getJoueursAvecCarteRevelee().size() > 0) {
			
			Joueur joueurChoisi;
			Carte carteChoisie;
			
			if(joueurConcerne instanceof Bot) {
				joueurChoisi = joueurConcerne.choisirJoueurPrendreCarte(joueurConcerne.getJoueursAvecCarteRevelee());
				carteChoisie = joueurConcerne.choisirCarteAPrendre(joueurChoisi.getCartesRevelees());
			} else {
				joueurChoisi = Partie.getInstance().getJoueurChoisi();
				carteChoisie = Partie.getInstance().getCarteChoisie();
			}
			
			//Pb
			execEffet = joueurConcerne.getNom()+" a pris la carte "+carteChoisie.getNom()+" du joueur "+joueurChoisi.getNom();
			Partie.getInstance().setExecutionEffet(execEffet);
			
			joueurChoisi.removeCarteRevelee(carteChoisie);
			joueurConcerne.ajouterCarteMain(carteChoisie);
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - Aucun joueur n'a de carte révélée.");
			
		}
		
	}
	
}