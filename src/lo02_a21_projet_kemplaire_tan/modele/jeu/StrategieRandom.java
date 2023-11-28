package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe StrategieRandom
*/
/**
* 
* Implémente l'interface IAStrategy. Permet de définir une stratégie aléatoire pour un joueur BOT. 
* Stratégie random : 
* <ul>
* <li>Toutes les actions de choix sont aléatoires.</li>
* </ul>
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Role
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Action
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Carte
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see IAStrategy
* 
*/
public class StrategieRandom implements IAStrategy {
	
	/**
	 * Permet de choisir le prochain joueur aleatoirement
	 * @param joueurs La liste des joueurs parmi laquelle on veut en choisir un
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirProchainJoueur(ArrayList<Joueur> joueurs) {
		
		int indexProchainJoueur = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexProchainJoueur);
		
	}
	
	/**
	 * Permet de choisir une carte a defausser aleatoirement
	 * @param cartes La liste de cartes parmi laquelle on veut en choisir une
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteADefausser(ArrayList<Carte> cartes) {
		
		int indexCarteADefausser = (int) ((Math.random() * (cartes.size()-1)));
		
		return cartes.get(indexCarteADefausser);
		
	}
	
	/**
	 * Permet de choisir une carte a prendre aleatoirement
	 * @param cartes La liste de cartes parmi laquelle on veut en choisir une
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteAPrendre(ArrayList<Carte> cartes) {
		
		int indexCarteARecuperer = (int) ((Math.random() * (cartes.size()-1)));
		
		return cartes.get(indexCarteARecuperer);
		
	}
	
	/**
	 * Permet de choisir aleatoirement un joueur a qui on veut prendre une carte
	 * @param joueurs La liste de joueurs parmi laquelle on veut en choisir un
	 * @return Le joueur a qui on veut prendre une carte
	 */
	public Joueur algoChoisirJoueurPrendreCarte(ArrayList<Joueur> joueurs) {
		
		int indexJoueurAChoisir = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexJoueurAChoisir);
		
	}
	
	/**
	 * Permet de choisir aleatoirement un joueur a qui on veut reveler l'identite
	 * @param joueurs La liste de joueurs parmi laquelle on veut en choisir un
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurAReveler(ArrayList<Joueur> joueurs) {
		
		int indexJoueurAChoisir = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexJoueurAChoisir);
		
	}
	
	/**
	 * Permet de choisir aleatoirement un joueur qu'on veut cibler pour un effet
	 * @param joueurs La liste de joueurs parmi laquelle on veut en choisir un
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurCibleEffet(ArrayList<Joueur> joueurs) {
		
		int indexJoueurAChoisir = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexJoueurAChoisir);
		
	}
	
	/**
	 * Permet de choisir une carte a reveler aleatoirement
	 * @param cartes La liste de cartes parmi laquelle on veut en choisir une
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteAReveler(ArrayList<Carte> cartes) {
		
		int indexCarteAReveler = (int) ((Math.random() * (cartes.size()-1)));
		
		return cartes.get(indexCarteAReveler);
		
	}
	
	/**
	 * Permet de choisir aleatoirement un joueur a accuser
	 * @param joueurs La liste de joueurs parmi laquelle on veut en choisir un
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurAAccuser(ArrayList<Joueur> joueurs) {
		
		int indexJoueurAChoisir = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexJoueurAChoisir);
		
	}
	
	/**
	 * Permet de choisir entre les actions ACCUSE_JOUEUR et REVELE_CARTE
	 * <br>- La strategie random consiste à choisir aleatoirement
	 * @param bot Le robot qui choisi l'action
	 * @return L'action choisie aleatoirement
	 */
	public Action algoChoisirAccuseOuReveleCarte(Bot bot) {
		
		double choix = Math.random();
		if(choix < 0.5) {
			return Action.ACCUSE_JOUEUR;
		} else {
			return Action.REVELE_CARTE;
		}
		
	}
	
	/**
	 * Permet de choisir entre les actions DEFAUSSE_CARTE et REVELE_IDENTITE
	 * <br>- La strategie random consiste à choisir aleatoirement
	 * @param bot Le robot qui choisi l'action
	 * @return L'action choisie aleatoirement
	 */
	public Action algoChoisirDefausseOuReveleIdentite(Bot bot) {
		
		double choix = Math.random();
		if(choix < 0.5) {
			return Action.DEFAUSSE_CARTE;
		} else {
			return Action.REVELE_IDENTITE;
		}
		
	}
	
	/**
	 * Permet de choisir entre les actions REVELE_IDENTITE et REVELE_CARTE
	 * <br>- La strategie random consiste à choisir aleatoirement
	 * @param bot Le robot qui choisi l'action
	 * @return L'action choisie aleatoirement
	 */
	public Action algoChoisirReveleIdentiteOuReveleCarte(Bot bot) {
		
		double choix = Math.random();
		if(choix < 0.5) {
			return Action.REVELE_IDENTITE;
		} else {
			return Action.REVELE_CARTE;
		}
		
	}
	
	/**
	 * Permet de choisir entre les roles WITCH et HUNT
	 * <br>- La strategie random consiste à choisir aleatoirement
	 * @return Le role choisi aleatoirement
	 */
	public Role algoChoisirRole() {
		
		double choix = Math.random();
		if(choix < 0.5) {
			return Role.HUNT;
		} else {
			return Role.WITCH;
		}
		
	}
	
}
