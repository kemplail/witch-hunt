package lo02_a21_projet_kemplaire_tan.modele.jeu;

/**
* Classe Message
*/
/**
* 
* Enum�ration repr�sentant des messages envoy�s par les Observables (Mod�le) aux Observers (Vues) pour leur signifier un changement d'�tat.
* Valeurs utilis�es dans le cadre de la m�thode notifyObservers()
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* 
*/
public enum Message {
	ERROR, SET_NB_JOUEURS, SET_NB_ROUND, SET_JOUEUR_COURANT, CHGT_JOUEURS_EN_JEU, CHGT_NB_POINTS, CHGT_CARTE_MAIN, REVELE_IDENTITE, EXECUTION_EFFET, PROCHAIN_JOUEUR
}
