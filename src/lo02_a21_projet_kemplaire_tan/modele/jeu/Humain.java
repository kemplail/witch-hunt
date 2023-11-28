package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe Humain
*/
/**
* 
* Classe repr�sentant un Joueur Humain caract�ris� par un nom et un ensemble de m�thodes lui permettant d'avoir un comportement
* non automatis�, c'est � dire prenant des entr�es pour d�terminer ses choix
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
	 * M�thode permettant � l'Humain de choisir une carte parmi une liste de Cartes pouvant �tre choisies
	 * @param listeDeCartes - Liste des cartes pouvant �tre choisies
	 * @return La carte choisie
	 */
	public Carte choisirCarte(ArrayList<Carte> listeDeCartes) {

		System.out.println("Cartes pouvant �tre choisies : ");
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
	 * M�thode permettant � l'Humain de choisir un joueur parmi une liste de Joueurs pouvant �tre choisis
	 * @param listeJoueurs - Liste des joueurs pouvant �tre choisies
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueur(ArrayList<Joueur> listeJoueurs) {

		System.out.println("Joueurs pouvant �tre choisis : ");
		for(Joueur j : listeJoueurs) {
			System.out.println("- "+j.getNom());
		}

		String nom;

		boolean trouve = false;
		int indexJoueur = 0;

		//On demande au joueur d'entrer le nom du joueur � accuser et on r�cup�re ce joueur parmi la liste des joueurs
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
	
	//M�thode utilis�e avec MVC
	
	/**
	 * M�thode permettant d'attribuer un role � l'Humain en fonction d'une String role repr�sentant le role en toute lettre
	 * @param role - role entr� par le joueur
	 */
	public void choisirRole(String role) {
		
		if(!role.toLowerCase().equals("witch") && !role.toLowerCase().equals("hunt")) {
			Partie.getInstance().setError("Erreur dans l'attribution des r�les ! Il est n�cessaire d'entrer 'witch' ou 'hunt'.");
		} else {
			
			if(role.toLowerCase().equals("witch")) {
				this.setRole(Role.WITCH);
			} else {
				this.setRole(Role.HUNT);
			}
			
		}
		
	}
	
	/**
	 * M�thode permettant � un Humain de choisir un r�le et de se le voir attribuer
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

		System.out.println("Role "+this.getRole().toString()+" attribu� ! \n");

	}
	
	/**
	 * M�thode permettant de r�cup�rer les Actions qu'il est possible d'effecter pour l'Humain dans une situation ou il doit choisir entre 2 actions
	 * @param choixAction - Enum�ration repr�sentant une association de 2 actions
	 * @return Une liste d'actions possible dans un contexte d�termin� par le param�tre choixAction
	 */
	public Action[] getActionsPossibles(ChoixAction choixAction) {
		
		Action[] action;
		
		if(choixAction == ChoixAction.IDENTITE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.WITCH).size() > 0) {
				
				action = new Action[2];
				
				action[0] = Action.REVELE_IDENTITE;
				action[1] = Action.REVELE_CARTE;
				
			} else {
				
				Partie.getInstance().setError("Il est impossible pour "+this.getNom()+" de r�v�ler une carte ! ");
				
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
				
				Partie.getInstance().setError("Il est impossible pour "+this.getNom()+" de d�fausser une carte !");
				
				action = new Action[1];
				
				action[0] = Action.REVELE_IDENTITE;
				
			}
			
		}
		
		return action;
		
	}

	/**
	 * M�thode permettant � un Humain de choisir une Action dans une situation ou il doit choisir entre 2 actions, s'il peut choisir
	 * @param choixAction - Enum�ration repr�sentant une association de 2 actions
	 * @return Une action choisie parmi les actions pouvant �tre choisies
	 */
	public Action choisirAction(ChoixAction choixAction) {
		
		String[] reponses = new String[2];
		Action[] action = new Action[2];
		
		Action actionChoisie = null;
		
		if(choixAction == ChoixAction.IDENTITE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.WITCH).size() > 0) {
				
				System.out.println("Que voulez-vous faire ? Pour r�v�ler votre identit�, entrez \"identite\" et pour r�v�ler une carte rumeur entrez \"rumeur\"");
				
				reponses[0] = "identite";
				reponses[1] = "rumeur";
				
				action[0] = Action.REVELE_IDENTITE;
				action[1] = Action.REVELE_CARTE;
				
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de r�v�ler une carte ! (Plus de cartes ou cartes non jouables en l'�tat)");
				
				actionChoisie = Action.REVELE_IDENTITE;
				
			}
			
		} else if(choixAction == ChoixAction.ACCUSE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.HUNT).size() > 0) {
				
				System.out.println("Que voulez-vous faire ? Pour accuser un joueur, entrez \"accuse\" et pour r�v�ler une carte rumeur entrez \"revele\"");
				
				reponses[0] = "accuse";
				reponses[1] = "revele";
				
				action[0] = Action.ACCUSE_JOUEUR;
				action[1] = Action.REVELE_CARTE;
				
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de r�v�ler une carte ! (Plus de cartes ou cartes non jouables en l'�tat)");
				
				actionChoisie = Action.ACCUSE_JOUEUR;
				
			}
			
		} else {
			
			if(this.getMain().size() > 0) {
				
				System.out.println("Que voulez-vous faire ? Pour d�fausser une carte, entrez \"defausse\" et pour r�v�ler votre identit� entrez \"identite\"");
				
				reponses[0] = "defausse";
				reponses[1] = "identite";
				
				action[0] = Action.DEFAUSSE_CARTE;
				action[1] = Action.REVELE_IDENTITE;
				
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de d�fausser une carte !");
				
				actionChoisie = Action.REVELE_IDENTITE;
				
			}
			
		}
		
		if(actionChoisie == null) {
			
			String reponse = Utils.getStringInput();

			//Tant qu'il n'entre pas les r�ponses attendues : "accuse" ou "revele" il recommence
			while(!reponse.toLowerCase().equals(reponses[0]) && !reponse.toLowerCase().equals(reponses[1])) {
				System.out.println("R�ponse invalide. Veuillez recommencer.");
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
	 * M�thode permettant � un Humain de choisir le prochain joueur parmi une liste de joueurs pouvant �tre choisies
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirProchainJoueur(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir le prochain joueur : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
	/**
	 * M�thode permettant � un Humain de choisir une Carte � d�fausser parmi une liste de cartes pouvant �tre d�fauss�es
	 * @param cartesPouvantEtreDefaussees - Liste de cartes pouvant �tre d�fauss�es
	 * @return La carte choisie
	 */
	public Carte choisirCarteADefausser(ArrayList<Carte> cartesPouvantEtreDefaussees) {
		
		System.out.println("Veuillez choisir une carte � d�fausser : \n");
		return this.choisirCarte(cartesPouvantEtreDefaussees);
		
	}
	
	/**
	 * M�thode permettant � un Humain de choisir une carte � prendre parmi une liste de cartes pouvant �tre r�cup�r�es
	 * @param cartePouvantEtreRecuperees - Liste de cartes pouvant �tre r�cup�r�es
	 * @return La carte choisie
	 */
	public Carte choisirCarteAPrendre(ArrayList<Carte> cartePouvantEtreRecuperees) {
		
		System.out.println("Veuillez choisir une carte � prendre : \n");
		return this.choisirCarte(cartePouvantEtreRecuperees);
		
	}
	
	/**
	 * M�thode permettant � un Humain de choisir un joueur � qui prendre une carte parmi une liste de joueurs pouvant �tre choisis
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueurPrendreCarte(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir un joueur � qui prendre une carte : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
	/**
	 * M�thode permettant � un Humain de choisir un joueur � r�v�ler parmi une liste de joueurs pouvant �tre choisis
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueurAReveler(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir un joueur � r�v�ler : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
	/**
	 * M�thode permettant � un Humain de choisir un joueur qui sera cible de l'effet execut� parmi une liste de joueurs pouvant �tre choisis
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueurCibleEffet(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir un joueur cible de l'effet : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
	/**
	 * M�thode permettant � un Humain de choisir une carte � r�v�ler parmi une liste de cartes pouvant �tre r�v�l�es
	 * @param cartesPouvantEtreRevelees - Liste de cartes pouvant �tre r�v�l�es
	 * @return La carte choisie
	 */
	public Carte choisirCarteAReveler(ArrayList<Carte> cartesPouvantEtreRevelees) {
		
		System.out.println("Veuillez choisir une carte rumeur � r�v�ler : \n");
		return this.choisirCarte(cartesPouvantEtreRevelees);
		
	}
	
	/**
	 * M�thode permettant � un Humain de choisir un joueur � accuser parmi une liste de joueurs pouvant �tre choisis
	 * @param listeJoueursPouvantEtreChoisis - Liste de joueurs pouvant �tre choisis
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueurAAccuser(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		System.out.println("Veuillez choisir un joueur � accuser : \n");
		return this.choisirJoueur(listeJoueursPouvantEtreChoisis);
		
	}

}
