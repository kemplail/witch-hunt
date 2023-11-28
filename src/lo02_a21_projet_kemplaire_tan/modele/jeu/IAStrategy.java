package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe IAStrategy
*/
/**
* 
* Classe repr�sentant une Strat�gie de Bot - Chaque m�thode pr�voit un algorithme permettant au Bot de faire un choix dans les situations
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
	 * M�thode cens�e impl�menter un algorithme permettant de d�terminer une carte � d�fausser parmi une liste de cartes
	 * @param cartes - Liste de cartes pouvant �tre d�ffauss�es
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteADefausser(ArrayList<Carte> cartes);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de d�terminer une carte � prendre parmi une liste de cartes
	 * @param cartes - Liste de cartes pouvant �tre prises
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteAPrendre(ArrayList<Carte> cartes);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de d�terminer une carte � r�v�ler parmi une liste de cartes
	 * @param cartesPouvantEtreRevelees - Liste de cartes pouvant �tre r�v�l�es
	 * @return La carte choisie
	 */
	public Carte algoChoisirCarteAReveler(ArrayList<Carte> cartesPouvantEtreRevelees);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de d�terminer un joueur � choisir comme prochain joueur parmi une liste de joueurs
	 * @param joueurs - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirProchainJoueur(ArrayList<Joueur> joueurs);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de d�terminer un joueur � choisir pour prendre une carte de sa main parmi une liste de joueurs
	 * @param joueurs - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurPrendreCarte(ArrayList<Joueur> joueurs);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de d�terminer un joueur � r�v�ler parmi une liste de joueurs
	 * @param joueurs - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurAReveler(ArrayList<Joueur> joueurs);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de d�terminer un joueur cible de l'effet � executer parmi une liste de joueurs
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurCibleEffet(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de d�terminer un joueur � accuser parmi une liste de joueurs
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur algoChoisirJoueurAAccuser(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de choisir une action entre les actions Accuser ou R�v�ler une carte
	 * @param bot - Bot utilisant la m�thode
	 * @return L'action choisie
	 */
	public Action algoChoisirAccuseOuReveleCarte(Bot bot);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de choisir une action entre les actions D�fausser une carte ou R�v�ler son identit�
	 * @param bot - Bot utilisant la m�thode
	 * @return L'action choisie
	 */
	public Action algoChoisirDefausseOuReveleIdentite(Bot bot);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de choisir une action entre les actions R�v�ler son identit� ou R�v�ler une carte
	 * @param bot - Bot utilisant la m�thode
	 * @return L'action choisie
	 */
	public Action algoChoisirReveleIdentiteOuReveleCarte(Bot bot);
	
	/**
	 * M�thode cens�e impl�menter un algorithme permettant de choisir un r�le au d�but de la partie
	 * @return Le r�le choisi
	 */
	public Role algoChoisirRole();
	
}
