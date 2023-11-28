package lo02_a21_projet_kemplaire_tan.modele.conditions;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Condition;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Role;

/**
* Classe EtreReveleVillageois
*/
/**
* 
* Classe permettant de définir la condition selon laquel l'effet peut être joué
* <br>Pour pouvoir jouer l'effet qui comportera cette condition, il faut avoir révélé son identité de Villageois.
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.conditions
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Condition
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Role
* 
*/
public class EtreReveleVillageois extends Condition {

	/**
	 * Constructeur de la classe EtreReveleVillageois
	 */
	public EtreReveleVillageois() {
		super();
		this.setDescription("Le joueur doit avoir été révélé villagois.");
	}
	
	/**
	 * Vérifie si la condition est valide ou non selon le joueur concerné
	 * @param j - Le joueur qui possède la carte
	 * @return True si la condition est valide (le joueur a été révélé villageois), False sinon
	 */
	public boolean isConditionValid(Joueur j) {
		return (j.getRole() == Role.HUNT && j.isIdentiteRevelee());
	}
	
}
