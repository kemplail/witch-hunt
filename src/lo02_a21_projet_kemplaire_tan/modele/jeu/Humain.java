package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe Humain
*/
/**
* 
* Classe représentant un Joueur Humain caractérisé par un nom et un ensemble de méthodes lui permettant d'avoir un comportement
* non automatisé, c'est à dire prenant des entrées pour déterminer ses choix
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Carte
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Role
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Action
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.ChoixAction
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public class Humain extends Joueur {

	/**
	 * Constructeur de la classe Humain
	 * @param nom - Nom de l'humain
	 */
	public Humain(String nom) {
		super(nom);
	}

	/**
	 * Méthode permettant à l'Humain de choisir une carte parmi une liste de Cartes pouvant être choisies
	 * @param listeDeCartes - Liste des cartes pouvant être choisies
	 * @return La carte choisie
	 */
	public Carte choisirCarte(ArrayList<Carte> listeDeCartes) {

		System.out.println("Cartes pouvant être choisies : ");
		for(Carte c : listeDeCartes) {
			System.out.println("- "+c.getNom().toString());
		}

		String nom;
		boolean trouve = false;
		int indexCarte = 0;

		while(!trouve) {

			System.out.println("\nVeuillez entrer le nom de la carte que vous souhaitez choisir. Merci d'entrer un nom valide : ");
			nom = Utils.getStringInput();

			indexCarte = 0;
			trouve = false;

			while(indexCarte < listeDeCartes.size()) {
				if(listeDeCartes.get(indexCarte).getNom().toString().toLowerCase().equals(nom.toLowerCase())) {
					trouve = true;
					break;
				}

				indexCarte++;
			}

		}

		return listeDeCartes.get(indexCarte);

	}

	/**
	 * Méthode permettant à l'Humain de choisir un joueur parmi une liste de Joueurs pouvant être choisis
	 * @param listeJoueurs - Liste des joueurs pouvant être choisies
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueur(ArrayList<Joueur> listeJoueurs) {

		System.out.println("Joueurs pouvant être choisis : ");
		for(Joueur j : listeJoueurs) {
			System.out.println("- "+j.getNom());
		}

		String nom;

		boolean trouve = false;
		int indexJoueur = 0;

		//On demande au joueur d'entrer le nom du joueur à  accuser et on récupà¨re ce joueur parmi la liste des joueurs
		while(!trouve) {

			System.out.println("\nVeuillez entrer le nom du joueur que vous souhaitez choisir. Merci d'entrer un nom valide : ");
			nom = Utils.getStringInput();

			indexJoueur = 0;

			while(indexJoueur < listeJoueurs.size()) {
				if(listeJoueurs.get(indexJoueur).getNom().toLowerCase().equals(nom.toLowerCase())) {
					trouve = true;
					break;
				}

				indexJoueur++;
			}

		}

		return listeJoueurs.get(indexJoueur);

	}
	
	//Méthode utilisée avec MVC
	
	/**
	 * Méthode permettant d'attribuer un role à l'Humain en fonction d'une String role représentant le role en toute lettre
	 * @param role - role entré par le joueur
	 */
	public void choisirRole(String role) {
		
		if(!role.toLowerCase().equals("witch") && !role.toLowerCase().equals("hunt")) {
			Partie.getInstance().setError("Erreur dans l'attribution des rôles ! Il est nécessaire d'entrer 'witch' ou 'hunt'.");
		} else {
			
			if(role.toLowerCase().equals("witch")) {
				this.setRole(Role.WITCH);
			} else {
				this.setRole(Role.HUNT);
			}
			
		}
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir un rôle et de se le voir attribuer
	 */
	public void choisirRole() {

		String role = new String("");

		System.out.println(getNom()+" - Veuillez entrer en toute lettre le role que vous souhaitez choisir.");

		//On rcupre le rle entr par le joueur
		role = Utils.getStringInput();

		//Tant que le joueur n'a pas entr un rle valide, il recommence
		while(!role.toLowerCase().equals("witch") && !role.toLowerCase().equals("hunt")) {
			System.out.println("Erreur : Veuillez entrer Witch ou Hunt.");
			role = Utils.getStringInput();
		}

		//On affecte le role au joueur
		if(role.toLowerCase().equals("witch")) {
			this.setRole(Role.WITCH);
		} else {
			this.setRole(Role.HUNT);
		}

		System.out.println("Role "+this.getRole().toString()+" attribué ! \n");

	}
	
	/**
	 * Méthode permettant de récupérer les Actions qu'il est possible d'effecter pour l'Humain dans une situation ou il doit choisir entre 2 actions
	 * @param choixAction - Enumération représentant une association de 2 actions
	 * @return Une liste d'actions possible dans un contexte déterminé par le paramètre choixAction
	 */
	public Action[] getActionsPossibles(ChoixAction choixAction) {
		
		Action[] action;
		
		if(choixAction == ChoixAction.IDENTITE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.WITCH).size() > 0) {
				
				action = new Action[2];
				
				action[0] = Action.REVELE_IDENTITE;
				action[1] = Action.REVELE_CARTE;
				
			} else {
				
				Partie.getInstance().setError("Il est impossible pour "+this.getNom()+" de révéler une carte ! ");
				
				action = new Action[1];
				
				action[0] = Action.REVELE_IDENTITE;
				
			}
			
		} else if(choixAction == ChoixAction.ACCUSE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.HUNT).size() > 0) {
				
				action = new Action[2];
				
				action[0] = Action.ACCUSE_JOUEUR;
				action[1] = Action.REVELE_CARTE;
				
			} else {
				
				action = new Action[1];
				
				action[0] = Action.ACCUSE_JOUEUR;
				
			}
			
		} else {
			
			if(this.getMain().size() > 0) {
	
				action = new Action[2];
				
				action[0] = Action.DEFAUSSE_CARTE;
				action[1] = Action.REVELE_IDENTITE;
				
			} else {
				
				Partie.getInstance().setError("Il est impossible pour "+this.getNom()+" de défausser une carte !");
				
				action = new Action[1];
				
				action[0] = Action.REVELE_IDENTITE;
				
			}
			
		}
		
		return action;
		
	}

	/**
	 * Méthode permettant à un Humain de choisir une Action dans une situation ou il doit choisir entre 2 actions, s'il peut choisir
	 * @param choixAction - Enumération représentant une association de 2 actions
	 * @return Une action choisie parmi les actions pouvant être choisies
	 */
	public Action choisirAction(ChoixAction choixAction) {
		
		String[] reponses = new String[2];
		Action[] action = new Action[2];
		
		Action actionChoisie = null;
		
		if(choixAction == ChoixAction.IDENTITE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.WITCH).size() > 0) {
				
				System.out.println("Que voulez-vous faire ? Pour révéler votre identité, entrez \"identite\" et pour révéler une carte rumeur entrez \"rumeur\"");
				
				reponses[0] = "identite";
				reponses[1] = "rumeur";
				
				action[0] = Action.REVELE_IDENTITE;
				action[1] = Action.REVELE_CARTE;
				
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de révéler une carte ! (Plus de cartes ou cartes non jouables en l'état)");
				
				actionChoisie = Action.REVELE_IDENTITE;
				
			}
			
		} else if(choixAction == ChoixAction.ACCUSE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.HUNT).size() > 0) {
				
				System.out.println("Que voulez-vous faire ? Pour accuser un joueur, entrez \"accuse\" et pour révéler une carte rumeur entrez \"revele\"");
				
				reponses[0] = "accuse";
				reponses[1] = "revele";
				
				action[0] = Action.ACCUSE_JOUEUR;
				action[1] = Action.REVELE_CARTE;
				
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de révéler une carte ! (Plus de cartes ou cartes non jouables en l'état)");
				
				actionChoisie = Action.ACCUSE_JOUEUR;
				
			}
			
		} else {
			
			if(this.getMain().size() > 0) {
				
				System.out.println("Que voulez-vous faire ? Pour défausser une carte, entrez \"defausse\" et pour rï¿½vï¿½ler votre identité entrez \"identite\"");
				
				reponses[0] = "defausse";
				reponses[1] = "identite";
				
				action[0] = Action.DEFAUSSE_CARTE;
				action[1] = Action.REVELE_IDENTITE;
				
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de défausser une carte !");
				
				actionChoisie = Action.REVELE_IDENTITE;
				
			}
			
		}
		
		if(actionChoisie == null) {
			
			String reponse = Utils.getStringInput();

			//Tant qu'il n'entre pas les rï¿½ponses attendues : "accuse" ou "revele" il recommence
			while(!reponse.toLowerCase().equals(reponses[0]) && !reponse.toLowerCase().equals(reponses[1])) {
				System.out.println("Réponse invalide. Veuillez recommencer.");
				reponse = Utils.getStringInput();
			}
			
			if(reponse.equals(reponses[0])) {
				actionChoisie = action[0];
			} else {
				actionChoisie = action[1];
			}
			
		}
		
		return actionChoisie;
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir le prochain joueur parmi une liste de joueurs pouvant être choisies
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirProchainJoueur(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir le prochain joueur : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir une Carte à défausser parmi une liste de cartes pouvant être défaussées
	 * @param cartesPouvantEtreDefaussees - Liste de cartes pouvant être défaussées
	 * @return La carte choisie
	 */
	public Carte choisirCarteADefausser(ArrayList<Carte> cartesPouvantEtreDefaussees) {
		
		System.out.println("Veuillez choisir une carte à  défausser : \n");
		return this.choisirCarte(cartesPouvantEtreDefaussees);
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir une carte à prendre parmi une liste de cartes pouvant être récupérées
	 * @param cartePouvantEtreRecuperees - Liste de cartes pouvant être récupérées
	 * @return La carte choisie
	 */
	public Carte choisirCarteAPrendre(ArrayList<Carte> cartePouvantEtreRecuperees) {
		
		System.out.println("Veuillez choisir une carte à  prendre : \n");
		return this.choisirCarte(cartePouvantEtreRecuperees);
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir un joueur à qui prendre une carte parmi une liste de joueurs pouvant être choisis
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueurPrendreCarte(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir un joueur à  qui prendre une carte : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir un joueur à révéler parmi une liste de joueurs pouvant être choisis
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueurAReveler(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir un joueur à  révéler : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir un joueur qui sera cible de l'effet executé parmi une liste de joueurs pouvant être choisis
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueurCibleEffet(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir un joueur cible de l'effet : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir une carte à révéler parmi une liste de cartes pouvant être révélées
	 * @param cartesPouvantEtreRevelees - Liste de cartes pouvant être révélées
	 * @return La carte choisie
	 */
	public Carte choisirCarteAReveler(ArrayList<Carte> cartesPouvantEtreRevelees) {
		
		System.out.println("Veuillez choisir une carte rumeur à  révéler : \n");
		return this.choisirCarte(cartesPouvantEtreRevelees);
		
	}
	
	/**
	 * Méthode permettant à un Humain de choisir un joueur à accuser parmi une liste de joueurs pouvant être choisis
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant être choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueurAAccuser(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir un joueur à  accuser : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}

}
