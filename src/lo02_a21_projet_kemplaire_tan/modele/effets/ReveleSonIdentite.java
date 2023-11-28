package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Bot;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Role;

/**
* Classe RevelerSonIdentite
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : RevelerSonIdentite
* <br>Description : Le joueur révèle son identité. Si HUNT, le joueur choisit le prochain joueur sinon le joueur à gauche joue
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Action
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Bot
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Carte
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.ChoixAction
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Role
* 
*/
public class ReveleSonIdentite extends Effet {
	
	/**
	 * Constructeur de la classe ReveleSonIdentite
	 */
	public ReveleSonIdentite() {
		super(EffetNom.REVELE_SON_IDENTITE.getDescriptionEffet());
		setNom(EffetNom.REVELE_SON_IDENTITE);
	}
	
	/**
	 * Permet d'executer l'effet: joueurConcerne révélé son identité, si son identité n'est pas encore révélé
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		if(!joueurConcerne.isIdentiteRevelee()) {
			
			joueurConcerne.reveleIdentite(true);
			
			//Si role = witch, prochain joueur à jouer est le joueur à gauche
			if(joueurConcerne.getRole() == Role.WITCH) {
				
				int indexJoueurCourant = Partie.getInstance().getListeJoueursEnJeu().indexOf(joueurConcerne);
				
				execEffet = "Votre identité était "+Role.WITCH.toString()+", le joueur à votre gauche joue";
				
				 if(indexJoueurCourant == 0) {
					 Partie.getInstance().setProchainJoueur(Partie.getInstance().getListeJoueursEnJeu().get(Partie.getInstance().getListeJoueursEnJeu().size()-1));
				 } else {
					 Partie.getInstance().setProchainJoueur(Partie.getInstance().getListeJoueursEnJeu().get(indexJoueurCourant+1));
				 }
			
			//Sinon le joueur courant choisis le prochain joueur
			} else {
				
				Joueur joueurChoisi;
				if(joueurConcerne instanceof Bot) {
					joueurChoisi = joueurConcerne.choisirProchainJoueur(Partie.getInstance().getJoueursPouvantEtreChoisis());
				} else {
					joueurChoisi = Partie.getInstance().getJoueurChoisi();
				}
				
				execEffet = "Votre identité était "+Role.HUNT.toString()+", vous avez désigné "+joueurChoisi.getNom()+" pour prendre le prochain tour";

				Partie.getInstance().setProchainJoueur(joueurChoisi);
				
			}
			
			Partie.getInstance().setExecutionEffet(execEffet);
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - "+joueurConcerne.getNom()+" avait déjà son identité révélée");
			
		}
			
	}
	
}