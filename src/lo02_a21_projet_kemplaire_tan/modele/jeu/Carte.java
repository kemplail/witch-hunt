package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe Carte
*/
/**
* 
* Classe représentant une entité carte, définie par un nom, un id, une liste d'effets witch et hunt et des conditions pour ces effets.
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.CarteNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Condition
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Role
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* 
*/
public class Carte {

	private static int nbCartes = 0;
	
	private int id;
	private CarteNom nom;
	
	private ArrayList<Effet> listeEffetsWitch;
	private ArrayList<Effet> listeEffetsHunt;
	
	private Condition conditionWitch;
	private Condition conditionHunt;
	
    /**
     * Constructeur de la classe carte permettant d'instancier une carte
     * @param nom : Nom de la carte parmi les noms prévus par l'énumération {@link CarteNom}
     * @param effetsWitch : Collection d'effets considérées comme les effets Witch relatifs à la carte
     * @param effetsHunt : Collection d'effets considérées comme les effets Hunt relatifs à la carte
     * @param conditionWitch : Condition permettant l'execution des effets contenus dans la collection effetsWitch
     * @param conditionHunt : Condition permettant l'execution des effets contenus dans la collection effetsHunt
     * 
     */
	public Carte(CarteNom nom, ArrayList<Effet> effetsWitch, ArrayList<Effet> effetsHunt, Condition conditionWitch, Condition conditionHunt) {
		
		setNom(nom);
		setId(getNbCartes()+1);
		
		setListeEffetsWitch(new ArrayList<Effet>());
		this.getListeEffetsWitch().addAll(effetsWitch);
		
		setListeEffetsHunt(new ArrayList<Effet>());
		this.getListeEffetsHunt().addAll(effetsHunt);
		
		this.setConditionWitch(conditionWitch);
		this.setConditionHunt(conditionHunt);
		
		//Nombre de joueurs + 1
		setNbCartes(getNbCartes()+1);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public CarteNom getNom() {
		return nom;
	}
	
	public void setNom(CarteNom nom) {
		this.nom = nom;
	}
	
	public ArrayList<Effet> getListeEffetsWitch() {
		return listeEffetsWitch;
	}

	public void setListeEffetsWitch(ArrayList<Effet> listeEffetsWitch) {
		this.listeEffetsWitch = listeEffetsWitch;
	}

	public ArrayList<Effet> getListeEffetsHunt() {
		return listeEffetsHunt;
	}

	public void setListeEffetsHunt(ArrayList<Effet> listeEffetsHunt) {
		this.listeEffetsHunt = listeEffetsHunt;
	}

	public Condition getConditionWitch() {
		return conditionWitch;
	}

	public void setConditionWitch(Condition conditionWitch) {
		this.conditionWitch = conditionWitch;
	}

	public Condition getConditionHunt() {
		return conditionHunt;
	}

	public void setConditionHunt(Condition conditionHunt) {
		this.conditionHunt = conditionHunt;
	}

	/**
	 * Ajoute un effet à la collection des effets Witch
	 * @param effet - Effet à ajouter
	 */
	public void addEffetWitch(Effet effet) {
		getListeEffetsWitch().add(effet);
	}
	
	/**
	 * Ajoute un effet à la collection des effets Hunt
	 * @param effet - Effet à ajouter
	 */
	public void addEffetHunt(Effet effet) {
		getListeEffetsHunt().add(effet);
	}
	
	/**
	 * Retire un effet à la collection des effets Witch
	 * @param effet - Effet à retirer
	 */
	public void removeEffetWitch(Effet effet) {
		int indexEffet = 0;
		boolean effetTrouve = false;
		
		//Parcours les effets witch et retire de la liste l'effet qui correspond à l'effet passé en paramètre
		while(!effetTrouve && indexEffet < getListeEffetsWitch().size()) {
			if(getListeEffetsWitch().get(indexEffet).equals(effet)) {
				getListeEffetsWitch().remove(indexEffet);
				effetTrouve = true;
			}
			indexEffet++;
		}
	}
	
	/**
	 * Retire un effet à la collection des effets Hunt
	 * @param effet - Effet à retirer
	 */
	public void removeEffetHunt(Effet effet) {
		int indexEffet = 0;
		boolean effetTrouve = false;
		
		//Parcours les effets witch et retire de la liste l'effet qui correspond à l'effet passé en paramètre
		while(!effetTrouve && indexEffet < getListeEffetsHunt().size()) {
			if(getListeEffetsHunt().get(indexEffet).equals(effet)) {
				getListeEffetsHunt().remove(indexEffet);
				effetTrouve = true;
			}
			indexEffet++;
		}
	}
	
	public static int getNbCartes() {
		return nbCartes;
	}
	public static void setNbCartes(int nbCartes) {
		Carte.nbCartes = nbCartes;
	}
	
	/**
	 * Détermine si le joueur vérifie les conditions (Witch ou Hunt) lui permettant d'executer les effets associés
	 * @param role - Type d'invocation de la carte (Witch ou Hunt)
	 * @param j - Joueur vérifiant les conditions
	 * @return True si la condition est vérifiée, false sinon
	 */
	public boolean verifCondition(Role role, Joueur j) {
		
		if(role == Role.WITCH) {
			if(getConditionWitch() != null) {
				return getConditionWitch().isConditionValid(j);
			}
		} else {
			if(getConditionHunt() != null) {
				return getConditionHunt().isConditionValid(j);
			}
		}
		
		return true;
		
	}
	
	/**
	 * Execute chaque effet de la liste d'effets déterminée en fonction du type d'invocation de la carte (Witch ou Hunt)
	 * @param role - Type d'invocation de la carte (Witch ou Hunt)
	 * @param joueurConcerne - Joueur qui execute les effets
	 * @param joueurAccusateur - Joueur ayant accusé (possiblement NULL)
	 */
	public void executerEffets(Role role, Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		ArrayList<Effet> effetsAExecuter;
		
		if(role == Role.HUNT) {
			effetsAExecuter = this.getListeEffetsHunt();
		} else {
			effetsAExecuter = this.getListeEffetsWitch();
		}
		
		for(int i = 0; i < effetsAExecuter.size(); i++) {
			System.out.println("Execution de l'effet "+effetsAExecuter.get(i).getNom()+" \n");
			effetsAExecuter.get(i).executerEffet(joueurConcerne, joueurAccusateur);
		}
		
	}
	
}
