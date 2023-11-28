package lo02_a21_projet_kemplaire_tan.modele.effets;

import java.util.ArrayList;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Bot;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe ReprendreCarteRevelee
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : ReprendreCarteRevelee
* <br>Description : Le joueur reprend une carte rumeur révélée dans sa main
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
public class ReprendreCarteRevelee extends Effet {
	
	/**
	 * Constructeur de la classe ReprendreCarteRevelee
	 */
	public ReprendreCarteRevelee() {
		super(EffetNom.REPRENDRE_CARTE_REVELEE.getDescriptionEffet());
		setNom(EffetNom.REPRENDRE_CARTE_REVELEE);
	}
	
	/**
	 * Permet d'executer l'effet : joueurConcerne récupère une de ses cartes révélées, si il a au moins une carte révélée
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet;
		
		if(joueurConcerne.getCartesRevelees().size() > 1) {
			
			ArrayList<Carte> cartesPouvantEtreRecuperees = new ArrayList<>();
			cartesPouvantEtreRecuperees.addAll(joueurConcerne.getCartesRevelees());
			cartesPouvantEtreRecuperees.remove(joueurConcerne.getDerniereCarteJouee());
			
			Carte carteReprise;
			if(joueurConcerne instanceof Bot) {
				carteReprise = joueurConcerne.choisirCarteAPrendre(cartesPouvantEtreRecuperees);
			} else {
				carteReprise = Partie.getInstance().getCarteChoisie();
			}
			
			execEffet = joueurConcerne.getNom()+" a repris la carte révélée "+carteReprise.getNom();
			Partie.getInstance().setExecutionEffet(execEffet);
			
			joueurConcerne.ajouterCarteMain(carteReprise);
			joueurConcerne.removeCarteRevelee(carteReprise);
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - "+joueurConcerne.getNom()+" n'a pas de carte révélée");
			
		}
		
	}
	
}