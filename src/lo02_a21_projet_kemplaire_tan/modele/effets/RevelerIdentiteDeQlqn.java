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
* H�rite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : RevelerIdentiteDeQlqn
* <br>Description : Le joueur r�v�ler l'identit� de quelqu'un d'autre
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
	 * Permet d'executer l'effet : joueurConcerne choisi un joueur � r�v�ler et r�v�le son identit�, si au moins un joueur peut �tre accus�
	 * @param joueurConcerne - Joueur qui ex�cute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		if(Partie.getInstance().getJoueursPouvantEtreAccuses().size() > 0) {
			
			//Le joueur qui execute l'effet choisit un joueur � r�v�ler
			Joueur joueurRevele = joueurConcerne.choisirJoueurAReveler(Partie.getInstance().getJoueursPouvantEtreAccuses());
			
			execEffet = joueurConcerne.getNom()+" r�v�le l'identit� de "+joueurRevele.getNom()+". ";
			
			//Ce joueur r�v�le son identit�
			joueurRevele.reveleIdentite(joueurConcerne,2);
			
			//Si son identit� �tait witch, le joueur executant l'effet rejoue
			if(joueurRevele.getRole() == Role.WITCH) {
				
				execEffet += "Son identit� �tait : "+Role.WITCH.toString()+", le joueur courant rejoue.";
				Partie.getInstance().setProchainJoueur(joueurConcerne);
				
			//Sinon l'accus� joue
			} else {
				
				execEffet += "Son identit� �tait : "+Role.HUNT.toString()+", le joueur accus� joue";
				Partie.getInstance().setProchainJoueur(joueurRevele);
				
			}
			
			Partie.getInstance().setExecutionEffet(execEffet);
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - Aucun joueur ne peut être accus�");
			
		}
		
	}
	
}