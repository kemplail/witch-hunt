package lo02_a21_projet_kemplaire_tan.modele.effets;

import java.util.ArrayList;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Bot;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe ChoisirProchainJoueurSpecial
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : ChoisirProchainJoueurSpecial
* <br>Description : Le joueur choisit le prochain joueur et le prochain joueur devra accuser quelqu'un d'autre si possible
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Bot
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public class ChoisirProchainJoueurSpecial extends Effet {
	
	/**
	 * Constructeur de la classe ChoisirProchainJoueurSpecial
	 */
	public ChoisirProchainJoueurSpecial() {
		super(EffetNom.CHOISIR_PROCHAIN_JOUEUR_SPECIAL.getDescriptionEffet());
		setNom(EffetNom.CHOISIR_PROCHAIN_JOUEUR_SPECIAL);
	}
	
	/**
	 * Permet d'executer l'effet : Le joueurConcerne choisit un prochain joueur, lors de son tour il devra accuser un autre joueur que le joueurConcerne
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
        Partie.getInstance().setJoueurPrecedent(joueurConcerne);
        Partie.getInstance().setEffetChoisirLance(true);

		Joueur prochainJoueur;

		if(joueurConcerne instanceof Bot) {
			
			if(joueurAccusateur == null) {

				prochainJoueur = joueurConcerne.choisirProchainJoueur(Partie.getInstance().getJoueursPouvantEtreChoisis());

			} else {

				ArrayList<Joueur> joueursPouvantEtreChoisi = Partie.getInstance().getListeJoueursEnJeu();
				joueursPouvantEtreChoisi.remove(joueurConcerne);

				prochainJoueur = joueurConcerne.choisirProchainJoueur(joueursPouvantEtreChoisi);
			}
			
		} else {
			
			prochainJoueur = Partie.getInstance().getJoueurChoisi();
			
		}
		
		execEffet = prochainJoueur.getNom()+" a été choisi comme prochain joueur !";
		Partie.getInstance().setExecutionEffet(execEffet);

		Partie.getInstance().setProchainJoueur(prochainJoueur);
		
	}
	
}