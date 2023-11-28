package lo02_a21_projet_kemplaire_tan.modele.jeu;

/**
* Classe Effet
*/
/**
* 
* Classe Effet représentant un effet associé à une carte. Un effet peut être contenu dans une collection d'effets Witch ou Hunt d'une carte donnée.
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* 
*/

public abstract class Effet {

	private static int nbEffets = 0;
	private int effetId;
	private String description;
	private EffetNom nom;
	
	/**
	 * Constructeur de la classe Effet
	 * @param description - Description de l'effet
	 */
	public Effet(String description) {
		setDescription(description);
		setEffetId(getNbEffets()+1);
		setNbEffets(getNbEffets()+1);
	}
	
	public EffetNom getNom() {
		return nom;
	}

	public void setNom(EffetNom nom) {
		this.nom = nom;
	}
	
	public static int getNbEffets() {
		return nbEffets;
	}
	public static void setNbEffets(int nbEffets) {
		Effet.nbEffets = nbEffets;
	}
	public int getEffetId() {
		return effetId;
	}
	public void setEffetId(int effetId) {
		this.effetId = effetId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Méthode permettant d'executer l'effet en question
	 * @param joueurConcerne - Joueur executant l'effet
	 * @param joueurAccusateur - Joueur qui accuse (possiblement NULL)
	 */
	public abstract void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur);
	
}
