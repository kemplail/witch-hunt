package lo02_a21_projet_kemplaire_tan.modele.conditions;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Condition;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;

/**
* Classe AvoirReveleCarte
*/
/**
* 
* Classe permettant de d�finir la condition selon laquel l'effet peut �tre jou�
* <br>Pour pouvoir jouer l'effet qui comportera cette condition, il faut avoir r�v�l� une carte.
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
		this.setDescription("Le joueur doit avoir r�v�l� une carte rumeur.");
	}
	
	/**
	 * V�rifie si la condition est valide ou non selon le joueur concern�
	 * @param j - Le joueur qui poss�de la carte
	 * @return True si la condition est valide (le joueur a au moins une carte r�v�l�e), False sinon
	 */
	public boolean isConditionValid(Joueur j) {
		return (j.getCartesRevelees().size() > 0);
	}
	
}
