package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe StrategieSmart
*/
/**
* 
* Implémente l'interface IAStrategy. Permet de définir une stratégie intelligente en fonction du rôle pour un joueur BOT. 
* Stratégie smart : 
* <ul>
* <li>Choix de rôle : Aléatoire</li>
* <li>Une fois le rôle choisi, on exécute les choix différemment selon le rôle. Si le joueur est WITCH, il ne va pas révéler son identité mais jouer des cartes. S'il est HUNT, il peut révéler son identité et conserver ses cartes.</li>
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
public class StrategieSmart implements IAStrategy {
	
	/**
	 * Permet de choisir aléatoirement un prochain joueur
	 * @param joueurs la liste des joueurs parmis laquelle on choisi un joueur
	 * @return le joueur choisi
	 */
	public Joueur algoChoisirProchainJoueur(ArrayList<Joueur> joueurs) {
		
		int indexProchainJoueur = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexProchainJoueur);
		
	}
	
	/**
	 * Permet de choisir aléatoirement une carte à défeusser
	 * @param cartes La liste des cartes parmis laquelle on veut en défausser une
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteADefausser(ArrayList<Carte> cartes) {
		
		int indexCarteADefausser = (int) ((Math.random() * (cartes.size()-1)));
		
		return cartes.get(indexCarteADefausser);
		
	}
	
	/**
	 * Permet de choisir aléatoirement une carte parmis une liste de cartes
	 * @param cartes La liste des cartes parmis laquelle on veut en prendre une
	 * @return la carte choisie
	 */
	public Carte algoChoisirCarteAPrendre(ArrayList<Carte> cartes) {
		
		int indexCarteARecuperer = (int) ((Math.random() * (cartes.size()-1)));
		
		return cartes.get(indexCarteARecuperer);
		
	}
	
	/**
	 * Permet de choisir aléatoirement un joueur à qui on veut prendre une carte
	 * @param joueurs la liste des joueurs parmis laquelle on choisi un joueur
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurPrendreCarte(ArrayList<Joueur> joueurs) {
		
		int indexJoueurAChoisir = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexJoueurAChoisir);
		
	}
	
	/**
	 * Permet de choisir aléatoirement un joueur à qui on veut révéler l'identité
	 * @param joueurs la liste des joueurs parmis laquelle on choisi un joueur
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurAReveler(ArrayList<Joueur> joueurs) {
		
		int indexJoueurAChoisir = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexJoueurAChoisir);
		
	}
	
	/**
	 * Permet de choisir aléatoirement un joueur qui sera la cible de l'effet spécial 
	 * @param joueurs la liste des joueurs parmis laquelle on choisi un joueur
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurCibleEffet(ArrayList<Joueur> joueurs) {
		
		int indexJoueurAChoisir = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexJoueurAChoisir);
		
	}
	
	/**
	 * Permet de choisir aléatoirement une carte à révéler
	 * @param cartes La liste des cartes parmis laquelle on choisi une carte
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteAReveler(ArrayList<Carte> cartes) {
		
		int indexCarteAReveler = (int) ((Math.random() * (cartes.size()-1)));
		
		return cartes.get(indexCarteAReveler);
		
	}
	
	/**
	 * Permet de choisir aléatoirement un joueur a accuser
	 * @param joueurs la liste des joueurs parmis laquelle on choisi un joueur
	 */
	public Joueur algoChoisirJoueurAAccuser(ArrayList<Joueur> joueurs) {
		
		int indexJoueurAChoisir = (int) ((Math.random() * (joueurs.size()-1)));
		
		return joueurs.get(indexJoueurAChoisir);
		
	}
	
	/**
	 * Permet de choisir entre les actions ACCUSE_JOUEUR et REVELE_CARTE
	 * <br>- La strategie smart consiste à choisir l'action fonction de la taille de la main
	 * <br> Lorsque la taille de la main est inferieure a 2, on accuse systematiquement
	 * @param bot Le robot qui choisi l'action
	 * @return L'action choisie
	 */
	public Action algoChoisirAccuseOuReveleCarte(Bot bot) {
		
		if(bot.getMain().size() < 2) {
			return Action.ACCUSE_JOUEUR;
		} else {
			return Action.REVELE_CARTE;
		}
		
	}
	
	/**
	 * Permet de choisir entre les actions DEFAUSSE_CARTE et REVELE_IDENTITE
	 *<br> - La strategie smart consiste à defausser une carte au lieu de reveler son identite si le role du joueur robot est egal = WTCH 
	 * @param bot Le robot qui choisi l'action
	 * @return L'action choisie
	 */
	public Action algoChoisirDefausseOuReveleIdentite(Bot bot) {
		
		if(bot.getRole() == Role.WITCH) {
			return Action.DEFAUSSE_CARTE;
		} else {
			return Action.REVELE_IDENTITE;
		}
		
	}
	
	/**
	 * Permet de choisir entre les actions REVELE_IDENTITE et REVELE_CARTE
	 *<br> - La strategie smart consiste à reveler une carte au lieu de reveler son identite si le role du joueur robot est egal = WTCH 
	 * @param bot Le robot qui choisi l'action
	 * @return L'action choisie
	 */
	public Action algoChoisirReveleIdentiteOuReveleCarte(Bot bot) {
		
		if(bot.getRole() == Role.HUNT) {
			return Action.REVELE_IDENTITE;
		} else {
			return Action.REVELE_CARTE;
		}
		
	}
	
	/**
	 * Permet de choisir entre les roles WITCH et HUNT
	 *<br> - La strategie smart consiste à choisir aleatoirement le role
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
