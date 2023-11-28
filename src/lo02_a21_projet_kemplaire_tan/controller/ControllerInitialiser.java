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
* <br><strong>Initialiser le jeu</strong> en déterminant le nombre de joueurs, leur nom, leur role. 
* <br><strong>Initialiser un round</strong> lorsqu'il est terminé en déterminant de nouveaux rôles aux joueurs de la liste des joueurs initial
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
	
	/**La partie jouée*/
	private Partie partie = Partie.getInstance();
	
	/**Le nombre de joueurs humains*/
	private int nbJoueursHumains;
	/**Le nombre de joueurs bots*/
	private int nbJoueursBots;
	/**Le nombre de joueurs total*/
	private int nbJoueurs;
	
	/**
	 * Constructeur de la classe ControllerInitialiser
	 * Lui affecte la {@link ViewInitialiser} qu'il doit gérer
	 * @param view La vue sur laquelle on affiche les éléments nécessaires pour initialiser une partie ou un nouveau round
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
	 * Premières actions de l'initialisation d'une partie :
	 * <br>- Permet de définir si une partie est finie
	 * <br>- Création des listes nécessaires (Défausse, Tas de carte, Liste de joueurs initial et en jeu
	 * <br>- Récupération du nombre de joueurs Humains et Bots 
	 */
	public void initierNbJoueurs() {
		view.getBtnNbJoueurs().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//On converti le nombre de joueurs Humains et Bots entrés dans le LabelInput de String à int
				nbJoueursHumains = (Integer) view.getNbHumains().getValue();
				nbJoueursBots = (Integer) view.getNbBots().getValue();
				nbJoueurs = nbJoueursBots + nbJoueursHumains;
					
				//******************************
				//Initialisation de la partie
				//******************************
				//Partie : pas encore terminée

				partie.initierPartie();
				
				//Ajout des joueurs seulement si le nombre de joueurs est comprit entre 3 et 6
				if( nbJoueurs < 3 || nbJoueursHumains == 0 || nbJoueurs > 6 ) {
					
					partie.setError("Nombre de joueurs incorrect"); //si le nombre de joueurs n'est pas respecté
					
				} else {
					
					//Récupérer le nombre de joueurs humains pour afficher les fields pour remplir les noms
					partie.setNbJoueurs(nbJoueursHumains, nbJoueursBots);
					
					view.getBtnNbJoueurs().setVisible(false);
					view.getBtnNomsJoueurs().setVisible(true);
					
				}				
			}
		});	
	}
	
	/**
	 * Deuxième action de l'initialisation d'une partie : 
	 * <br>- On vérifie que les champs sont remplis
	 * <br>- On récupère le noms des joueurs humains
	 * <br>- On insière tous les joueurs dans la liste des joueurs initiaux de la partie
	 */
	public void initierNomsEtRoles() {
		view.getBtnNomsJoueurs().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				boolean champsValides = true;
				
				//Ajouter les joueurs humains à la liste des joueurs initiaux
				for(int i = 0; i < nbJoueursHumains; i++) {
					if(view.getNomsJoueursInput()[i].getText().equals("")) {
						partie.setError("Erreur : Veuillez remplir tous les champs.");
						champsValides = false;
					}
				}
				
				if(champsValides) {
					
					//Suppression de l'erreur
					partie.setError(null);
					
					//On récupère les noms entrés dans les champs
					String[] nomsJoueurs = getNoms();
					String[] rolesJoueurs = getRoles();
					
					//On crée les joueurs humains et bots et on donne le rôle choisi aux joueurs humains
					partie.creationJoueurs(nomsJoueurs, rolesJoueurs);
					
					afficherRoles();
	
					//Fermer la fenêtre d'initialisation 
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
				System.out.println("Update des nouveaux rôles");
				System.out.println("*************************************");
				
				String[] rolesJoueurs = getRoles();
				Joueur j;
				
				//update des rôles des joueurs 
				for(int i = 0; i < rolesJoueurs.length; i++) {
										
					j = partie.getListeJoueursInitial().get(i);
					
					if(j instanceof Humain) {
						Humain h = (Humain) j;
						h.choisirRole(rolesJoueurs[i]);
					}
					
				}
				
				afficherRoles();
								
				//Fermer la fenêtre d'initialisation 
				view.getFrame().setVisible(false);
				
				ouvrirFenetreJeu();
			}
		});
	}
	
	/**
	 * Récupérer les noms saisis dans les champs
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
	 * Récupérer les rôles saisis dans les champs
	 * @return Les rôles choisis dans les champs de l'interface
	 */
	public String[] getRoles() {
		String rolesJoueurs[] = new String[partie.getNbJoueursHumains()];
		for(int i = 0; i < partie.getNbJoueursHumains(); i++) {
			rolesJoueurs[i] = view.getRolesJoueurs()[i].getValue().toString();
		}
		return rolesJoueurs;
	}
	
	/**
	 * Afficher les rôles dans la console
	 */
	public void afficherRoles() {
		
		//Affichage des noms et roles en console
		System.out.println("*************************************");
		System.out.println("Les roles ont été attribués");
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
	 * Ouvrir une fenêtre de jeu après une initialisation de partie ou de round
	 */
	public void ouvrirFenetreJeu() {
		
		//Ouvrir une nouvelle fenêtre de jeu
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