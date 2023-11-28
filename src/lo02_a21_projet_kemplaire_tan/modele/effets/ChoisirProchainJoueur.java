package lo02_a21_projet_kemplaire_tan.modele.effets;

import java.util.ArrayList;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Bot;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Message;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

/**
* Classe ChoisirProchainJoueur
*/
/**
* Hérite de la classe Effet.java
* Classe permettant de jouer un effet :
* <br>Nom de l'effet : ChoisirProchainJoueur
* <br>Description : le joueur qui joue peut choisir le prochain joueur 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Bot
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Effet
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.EffetNom
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Message
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public class ChoisirProchainJoueur extends Effet {
	
	/**
	 * Constructeur de la classe ChoisirProchainJoueur
	 */
	public ChoisirProchainJoueur() {
		super(EffetNom.CHOISIR_PROCHAIN_JOUEUR.getDescriptionEffet());
		setNom(EffetNom.CHOISIR_PROCHAIN_JOUEUR);
	}
	
	/**
	 * Permet d'executer l'effet : Le joueurConcerne choisit le prochain joueur
	 * @param joueurConcerne - Joueur qui exécute l'effet
	 * @param joueurAccusateur - Joueur qui accuse le joueurConcerne (optionnel) 
	 */
	public void executerEffet(Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		String execEffet = new String();
		
		if(Partie.getInstance().getListeJoueursEnJeu().size() > 1) {
			
			Joueur prochainJoueur;

			if(joueurConcerne instanceof Bot) {
				
				if(joueurAccusateur == null) {

	                prochainJoueur = joueurConcerne.choisirProchainJoueur(Partie.getInstance().getJoueursPouvantEtreChoisis());
	                
	                for(Joueur j : Partie.getInstance().getJoueursPouvantEtreChoisis()) {
	                	System.out.println(j.getNom());
	                }

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
			
		} else {
			
			Partie.getInstance().setError("Impossible d'executer l'effet - Il ne reste plus qu'un joueur en jeu !");
			
		}
		
	}
	
}