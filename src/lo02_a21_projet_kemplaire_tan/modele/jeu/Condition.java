package lo02_a21_projet_kemplaire_tan.modele.jeu;

/**
* Classe Condition
*/
/**
* 
* Classe Condition représentant une condition permettant d'executer les effets (selon le type d'invocation : Witch ou Hunt) d'une carte
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* 
*/
public abstract class Condition {
	
	private static int nbConditions = 0;
	private int conditionId;
	private String description;
	
	/**
	 * Constructeur de la classe condition
	 */
	public Condition() {

		setConditionId(getNbConditions()+1);
		setNbConditions(getNbConditions()+1);
		
	}
	
	public int getConditionId() {
		return conditionId;
	}

	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	
	public static int getNbConditions() {
		return nbConditions;
	}
	public static void setNbConditions(int nbConditions) {
		Condition.nbConditions = nbConditions;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Vérifie si la condition est valide pour que le joueur j puisse jouer un effet
	 * @param j - Joueur vérifiant la condition
	 * @return True si la condition est valide, false sinon
	 */
	public abstract boolean isConditionValid(Joueur j);
	
}
