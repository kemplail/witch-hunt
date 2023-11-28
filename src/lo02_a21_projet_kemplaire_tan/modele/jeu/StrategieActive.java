package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe StrategieActive
*/
/**
* 
* Implémente l'interface IAStrategy. Permet de définir une stratégie active pour un joueur BOT. 
* Stratégie active : 
* <ul>
* <li>Choix de rôle : WITCH</li>
* <li>Accuse systématiquement un joueur lorsqu'il joue son tour.</li>
* <li>Ne révèle son identité qu'en dernier recours. Sinon, il joue systématiquement une carte rumeur pour se défendre.</li>
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
public class StrategieActive implements IAStrategy {
	
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
	 * <br>- La strategie active consiste à toujours accuser les autres joueurs
	 * @param bot Le robot qui choisi l'action
	 * @return L'action ACCUSE_JOUEUR
	 */
	public Action algoChoisirAccuseOuReveleCarte(Bot bot) {

		return Action.ACCUSE_JOUEUR;
		
	}
	
	/**
	 * Permet de choisir entre les actions DEFAUSSE_CARTE et REVELE_IDENTITE
	 * <br>- La strategie active consiste à toujours defausser une carte
	 * @param bot Le robot qui choisi l'action
	 * @return L'action DEFAUSSE_CARTE
	 */
	public Action algoChoisirDefausseOuReveleIdentite(Bot bot) {
		
		return Action.DEFAUSSE_CARTE;
		
	}
	
	/**
	 * Permet de choisir entre les actions REVELE_IDENTITE et REVELE_CARTE
	 * <br>- La strategie active consiste à toujours reveler une carte
	 * @param bot Le robot qui choisi l'action
	 * @return L'action REVELE_CARTE
	 */
	public Action algoChoisirReveleIdentiteOuReveleCarte(Bot bot) {
		
		return Action.REVELE_CARTE;
		
	}
	
	/**
	 * Permet de choisir entre les roles WITCH et HUNT
	 * <br>- La strategie active consiste à toujours choisir le role WITCH
	 * @return Le role choisi par le BOT (WITCH)
	 */
	public Role algoChoisirRole() {
		
		return Role.WITCH;
		
	}
	
}
