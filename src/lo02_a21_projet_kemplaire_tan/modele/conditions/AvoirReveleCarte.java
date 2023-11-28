package lo02_a21_projet_kemplaire_tan.modele.conditions;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Condition;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;

/**
* Classe AvoirReveleCarte
*/
/**
* 
* Classe permettant de définir la condition selon laquel l'effet peut être joué
* <br>Pour pouvoir jouer l'effet qui comportera cette condition, il faut avoir révélé une carte.
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.conditions
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Condition
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* 
*/
public class AvoirReveleCarte extends Condition {

	/**
	 * Constructeur de la classe AvoirReveleCarte
	 */
	public AvoirReveleCarte() {
		super();
		this.setDescription("Le joueur doit avoir révélé une carte rumeur.");
	}
	
	/**
	 * Vérifie si la condition est valide ou non selon le joueur concerné
	 * @param j - Le joueur qui possède la carte
	 * @return True si la condition est valide (le joueur a au moins une carte révélée), False sinon
	 */
	public boolean isConditionValid(Joueur j) {
		return (j.getCartesRevelees().size() > 0);
	}
	
}
