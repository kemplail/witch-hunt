package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Role;

/**
* Classe RevelerIdentiteDeQlqn
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : RevelerIdentiteDeQlqn
* <br>Description : Le joueur révéler l'identité de quelqu'un d'autre
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public class RevelerIdentiteDeQlqn extends Effet {
	
	/**
	 * Constructeur de la classe RevelerIdentiteDeQlqn
	 */
	public RevelerIdentiteDeQlqn() {
		super(EffetNom.REVELER_AUTRE_IDENTITE.getDescriptionEffet());
		setNom(EffetNom.REVELER_AUTRE_IDENTITE);
	}
	
	/**
	 * Permet d'executer l'effet : joueurConcerne choisi un joueur à révéler et révèle son identité, si au moins un joueur peut être accusé
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		if(Partie.getInstance().getJoueursPouvantEtreAccuses().size() > 0) {
			
			//Le joueur qui execute l'effet choisit un joueur à révéler
			Joueur joueurRevele = joueurConcerne.choisirJoueurAReveler(Partie.getInstance().getJoueursPouvantEtreAccuses());
			
			execEffet = joueurConcerne.getNom()+" révèle l'identité de "+joueurRevele.getNom()+". ";
			
			//Ce joueur révèle son identité
			joueurRevele.reveleIdentite(joueurConcerne,2);
			
			//Si son identité était witch, le joueur executant l'effet rejoue
			if(joueurRevele.getRole() == Role.WITCH) {
				
				execEffet += "Son identité était : "+Role.WITCH.toString()+", le joueur courant rejoue.";
				Partie.getInstance().setProchainJoueur(joueurConcerne);
				
			//Sinon l'accusé joue
			} else {
				
				execEffet += "Son identité était : "+Role.HUNT.toString()+", le joueur accusé joue";
				Partie.getInstance().setProchainJoueur(joueurRevele);
				
			}
			
			Partie.getInstance().setExecutionEffet(execEffet);
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - Aucun joueur ne peut Ãªtre accusé");
			
		}
		
	}
	
}