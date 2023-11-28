package lo02_a21_projet_kemplaire_tan.modele.jeu;

/**
* Classe Message
*/
/**
* 
* Enumération représentant des messages envoyés par les Observables (Modèle) aux Observers (Vues) pour leur signifier un changement d'état.
* Valeurs utilisées dans le cadre de la méthode notifyObservers()
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* 
*/
public enum Message {
	ERROR, SET_NB_JOUEURS, SET_NB_ROUND, SET_JOUEUR_COURANT, CHGT_JOUEURS_EN_JEU, CHGT_NB_POINTS, CHGT_CARTE_MAIN, REVELE_IDENTITE, EXECUTION_EFFET, PROCHAIN_JOUEUR
}
