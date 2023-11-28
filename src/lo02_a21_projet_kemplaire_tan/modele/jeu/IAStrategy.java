package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe IAStrategy
*/
/**
* 
* Classe représentant une Stratégie de Bot - Chaque méthode prévoit un algorithme permettant au Bot de faire un choix dans les situations
* dans lesquelles il doit faire un choix
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Carte
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Role
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Action
* 
*/
public interface IAStrategy {
	
	/**
	 * Méthode censée implémenter un algorithme permettant de déterminer une carte à défausser parmi une liste de cartes
	 * @param cartes - Liste de cartes pouvant être déffaussées
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteADefausser(ArrayList<Carte> cartes);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de déterminer une carte à prendre parmi une liste de cartes
	 * @param cartes - Liste de cartes pouvant être prises
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteAPrendre(ArrayList<Carte> cartes);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de déterminer une carte à révéler parmi une liste de cartes
	 * @param cartesPouvantEtreRevelees - Liste de cartes pouvant être révélées
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteAReveler(ArrayList<Carte> cartesPouvantEtreRevelees);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de déterminer un joueur à choisir comme prochain joueur parmi une liste de joueurs
	 * @param joueurs - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirProchainJoueur(ArrayList<Joueur> joueurs);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de déterminer un joueur à choisir pour prendre une carte de sa main parmi une liste de joueurs
	 * @param joueurs - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurPrendreCarte(ArrayList<Joueur> joueurs);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de déterminer un joueur à révéler parmi une liste de joueurs
	 * @param joueurs - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurAReveler(ArrayList<Joueur> joueurs);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de déterminer un joueur cible de l'effet à executer parmi une liste de joueurs
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurCibleEffet(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de déterminer un joueur à accuser parmi une liste de joueurs
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurAAccuser(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de choisir une action entre les actions Accuser ou Révéler une carte
	 * @param bot - Bot utilisant la méthode
	 * @return L'action choisie
	 */
	public Action algoChoisirAccuseOuReveleCarte(Bot bot);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de choisir une action entre les actions Défausser une carte ou Révéler son identité
	 * @param bot - Bot utilisant la méthode
	 * @return L'action choisie
	 */
	public Action algoChoisirDefausseOuReveleIdentite(Bot bot);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de choisir une action entre les actions Révéler son identité ou Révéler une carte
	 * @param bot - Bot utilisant la méthode
	 * @return L'action choisie
	 */
	public Action algoChoisirReveleIdentiteOuReveleCarte(Bot bot);
	
	/**
	 * Méthode censée implémenter un algorithme permettant de choisir un rôle au début de la partie
	 * @return Le rôle choisi
	 */
	public Role algoChoisirRole();
	
}
