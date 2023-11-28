package lo02_a21_projet_kemplaire_tan.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import lo02_a21_projet_kemplaire_tan.modele.jeu.Humain;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;
import lo02_a21_projet_kemplaire_tan.view.ViewGame;
import lo02_a21_projet_kemplaire_tan.view.ViewInitialiser;

/**
* Classe ControllerInitialiser
*/
/**
* 
* Permet d'initialiser le jeu ou d'initialiser un nouveau round.
* <br><strong>Initialiser le jeu</strong> en d�terminant le nombre de joueurs, leur nom, leur role. 
* <br><strong>Initialiser un round</strong> lorsqu'il est termin� en d�terminant de nouveaux r�les aux joueurs de la liste des joueurs initial
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.controller
* @see lo02_a21_projet_kemplaire_tan.modele.jeu
* @see lo02_a21_projet_kemplaire_tan.view
* 
*/
public class ControllerInitialiser {
	
	/**La vue de l'initialisation*/
	private ViewInitialiser view;
	
	/**La partie jou�e*/
	private Partie partie = Partie.getInstance();
	
	/**Le nombre de joueurs humains*/
	private int nbJoueursHumains;
	/**Le nombre de joueurs bots*/
	private int nbJoueursBots;
	/**Le nombre de joueurs total*/
	private int nbJoueurs;
	
	/**
	 * Constructeur de la classe ControllerInitialiser
	 * Lui affecte la {@link ViewInitialiser} qu'il doit g�rer
	 * @param view La vue sur laquelle on affiche les �l�ments n�cessaires pour initialiser une partie ou un nouveau round
	 */
	public ControllerInitialiser(ViewInitialiser view) {
		
		this.view = view;
		
		//*******************************
		//Initier la partie 
		//*******************************
		initierNbJoueurs();//Indiquer le nombre de joueurs humains et bots
		initierNomsEtRoles();//Donner les noms des joueurs humains
		initierRound();//Initialiser un nouveau round	
	}
	
	/**
	 * Premi�res actions de l'initialisation d'une partie :
	 * <br>- Permet de d�finir si une partie est finie
	 * <br>- Cr�ation des listes n�cessaires (D�fausse, Tas de carte, Liste de joueurs initial et en jeu
	 * <br>- R�cup�ration du nombre de joueurs Humains et Bots 
	 */
	public void initierNbJoueurs() {
		view.getBtnNbJoueurs().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//On converti le nombre de joueurs Humains et Bots entr�s dans le LabelInput de String � int
				nbJoueursHumains = (Integer) view.getNbHumains().getValue();
				nbJoueursBots = (Integer) view.getNbBots().getValue();
				nbJoueurs = nbJoueursBots + nbJoueursHumains;
					
				//******************************
				//Initialisation de la partie
				//******************************
				//Partie : pas encore termin�e

				partie.initierPartie();
				
				//Ajout des joueurs seulement si le nombre de joueurs est comprit entre 3 et 6
				if( nbJoueurs < 3 || nbJoueursHumains == 0 || nbJoueurs > 6 ) {
					
					partie.setError("Nombre de joueurs incorrect"); //si le nombre de joueurs n'est pas respect�
					
				} else {
					
					//R�cup�rer le nombre de joueurs humains pour afficher les fields pour remplir les noms
					partie.setNbJoueurs(nbJoueursHumains, nbJoueursBots);
					
					view.getBtnNbJoueurs().setVisible(false);
					view.getBtnNomsJoueurs().setVisible(true);
					
				}				
			}
		});	
	}
	
	/**
	 * Deuxi�me action de l'initialisation d'une partie : 
	 * <br>- On v�rifie que les champs sont remplis
	 * <br>- On r�cup�re le noms des joueurs humains
	 * <br>- On insi�re tous les joueurs dans la liste des joueurs initiaux de la partie
	 */
	public void initierNomsEtRoles() {
		view.getBtnNomsJoueurs().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				boolean champsValides = true;
				
				//Ajouter les joueurs humains � la liste des joueurs initiaux
				for(int i = 0; i < nbJoueursHumains; i++) {
					if(view.getNomsJoueursInput()[i].getText().equals("")) {
						partie.setError("Erreur : Veuillez remplir tous les champs.");
						champsValides = false;
					}
				}
				
				if(champsValides) {
					
					//Suppression de l'erreur
					partie.setError(null);
					
					//On r�cup�re les noms entr�s dans les champs
					String[] nomsJoueurs = getNoms();
					String[] rolesJoueurs = getRoles();
					
					//On cr�e les joueurs humains et bots et on donne le r�le choisi aux joueurs humains
					partie.creationJoueurs(nomsJoueurs, rolesJoueurs);
					
					afficherRoles();
	
					//Fermer la fen�tre d'initialisation 
					view.getFrame().setVisible(false);
					
					debuterPartie();
					ouvrirFenetreJeu();
						
				}												
			}
		});
	}
	
	/**
	 * Initialiser un nouveau round
	 */
	public void initierRound() {
		view.getBtnRolesJoueurs().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("\n*************************************");
				System.out.println("Update des nouveaux r�les");
				System.out.println("*************************************");
				
				String[] rolesJoueurs = getRoles();
				Joueur j;
				
				//update des r�les des joueurs 
				for(int i = 0; i < rolesJoueurs.length; i++) {
										
					j = partie.getListeJoueursInitial().get(i);
					
					if(j instanceof Humain) {
						Humain h = (Humain) j;
						h.choisirRole(rolesJoueurs[i]);
					}
					
				}
				
				afficherRoles();
								
				//Fermer la fen�tre d'initialisation 
				view.getFrame().setVisible(false);
				
				ouvrirFenetreJeu();
			}
		});
	}
	
	/**
	 * R�cup�rer les noms saisis dans les champs
	 * @return Les noms de joueurs saisis dans les champs de l'interface
	 */
	public String[] getNoms() {
		String nomsJoueurs[] = new String[nbJoueursHumains];
		for(int i = 0; i < nbJoueursHumains; i++) {
			nomsJoueurs[i] = view.getNomsJoueursInput()[i].getText();
		}
		
		return nomsJoueurs;
	}
	
	/**
	 * R�cup�rer les r�les saisis dans les champs
	 * @return Les r�les choisis dans les champs de l'interface
	 */
	public String[] getRoles() {
		String rolesJoueurs[] = new String[partie.getNbJoueursHumains()];
		for(int i = 0; i < partie.getNbJoueursHumains(); i++) {
			rolesJoueurs[i] = view.getRolesJoueurs()[i].getValue().toString();
		}
		return rolesJoueurs;
	}
	
	/**
	 * Afficher les r�les dans la console
	 */
	public void afficherRoles() {
		
		//Affichage des noms et roles en console
		System.out.println("*************************************");
		System.out.println("Les roles ont �t� attribu�s");
		System.out.println("*************************************");
		
		ArrayList<Joueur> joueurs = partie.getListeJoueursInitial();
		
		for(Joueur j : joueurs) {
			System.out.println("[" + j.getNom() + "][" + j.getRole() + "]");
		}
		
		System.out.println("");
		
	}
	
	/**
	 * Faire la partie
	 */
	public void debuterPartie() {
		
		partie.debuterPartie();
		
	}
	
	/**
	 * Ouvrir une fen�tre de jeu apr�s une initialisation de partie ou de round
	 */
	public void ouvrirFenetreJeu() {
		
		//Ouvrir une nouvelle fen�tre de jeu
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewGame window = new ViewGame();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
}