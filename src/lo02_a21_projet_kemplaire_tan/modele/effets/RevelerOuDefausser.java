package lo02_a21_projet_kemplaire_tan.modele.effets;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Action;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Bot;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.ChoixAction;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Role;

/**
* Classe RevelerOuDefausser
*/
/**
* H�rite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : RevelerOuDefausser
* <br>Description : Choisissez un joueur. Il doit r�v�ler son identit� ou d�fausser une carte
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
public class RevelerOuDefausser extends Effet {
	
	public RevelerOuDefausser() {
		super(EffetNom.REVELER_OU_DEFAUSSER.getDescriptionEffet());
		setNom(EffetNom.REVELER_OU_DEFAUSSER);
	}
	
	/**
	 * Permet d'executer l'effet : joueurConcerne choisi le joueur cible de l'effet : Ce joueur peut choisir de r�v�ler son identit� s'il n'est pas encore
	 * r�v�l�, ou d�fausser une carte de sa main si il a au moins une carte dans sa main
	 * @param joueurConcerne - Joueur qui ex�cute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		if(Partie.getInstance().getJoueursPouvantEtreChoisis().size() > 0) {
			
			Joueur joueurChoisi;
			
			if(joueurConcerne instanceof Bot) {
				joueurChoisi = joueurConcerne.choisirJoueurCibleEffet(Partie.getInstance().getJoueursPouvantEtreChoisis());
			} else {
				joueurChoisi = Partie.getInstance().getJoueurChoisi();
			}
			
			Action actionChoisie = null;
			
			if(!joueurChoisi.isIdentiteRevelee()) {
				if(joueurChoisi instanceof Bot) {
					actionChoisie = joueurChoisi.choisirAction(ChoixAction.DEFAUSSE_OU_IDENTITE);
				} else {
					actionChoisie = Partie.getInstance().getActionChoisie();
				}
				
				//pr�venir bug
				if(actionChoisie == null) {
					actionChoisie = Action.REVELE_IDENTITE;
				}
			}
				
			if(actionChoisie == null || actionChoisie == Action.DEFAUSSE_CARTE) {
				
				if(joueurChoisi.getMain().size() > 0) {
					
					Carte carteChoisie;
					
					if(joueurChoisi instanceof Bot) {
						carteChoisie = joueurChoisi.choisirCarteADefausser(joueurChoisi.getMain());
					} else {
						carteChoisie = Partie.getInstance().getCarteChoisie();
					}
					
					//Pr�venir bug
					if(carteChoisie == null) {
						carteChoisie = joueurChoisi.getMain().get(0);
					}
					
					execEffet = joueurChoisi.getNom()+" d�fausse la carte "+carteChoisie.getNom()+" !";
					Partie.getInstance().setExecutionEffet(execEffet);

					joueurChoisi.defausseCarte(carteChoisie);
					joueurChoisi.removeCarteMain(carteChoisie);
					
					Partie.getInstance().setProchainJoueur(joueurChoisi);
					
				} else {
					
					Partie.getInstance().setError("Impossible d'executer l'effet - Le joueur n'a pas de carte � d�fausser");
					
				}
				
			} else {
				
				joueurChoisi.reveleIdentite(joueurConcerne,1);
				
				//Si son identit� �tait witch, le joueur executant l'effet rejoue
				if(joueurChoisi.getRole() == Role.WITCH) {
					
					execEffet = joueurChoisi.getNom()+" �tait "+Role.WITCH.toString()+", le joueur courant rejoue";
					Partie.getInstance().setProchainJoueur(joueurConcerne);
					
				//Sinon l'accus� joue
				} else {
					
					execEffet = joueurChoisi.getNom()+" �tait "+Role.HUNT.toString()+", le joueur accus� joue";
					Partie.getInstance().setProchainJoueur(joueurChoisi);
					
				}
				
				Partie.getInstance().setExecutionEffet(execEffet);
				
			}
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - Aucun joueur ne peut �tre choisi");
			
		}
		
	}
	
}