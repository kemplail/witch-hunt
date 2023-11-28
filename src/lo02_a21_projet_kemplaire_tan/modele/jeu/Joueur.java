package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Observable;

/**
* Classe Joueur
*/
/**
* 
* Classe représentant un Joueur au sens large (pouvant être un Bot ou un Humain) - Prévoit des méthodes pour l'ensemble des comportements que peut avoir un joueur
* Un joueur est caractérisé par un nom, un id, une identité révélée ou non, un nombre de points, un role. Il a une main et des des cartes révélées
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Carte
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Role
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Action
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.ChoixAction
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Partie
* 
*/
public abstract class Joueur extends Observable {

	/**Le nombre de joueurs dans la partie*/
	private static int nbJoueurs = 0;

	/**L'ID qui est attribué au joueur*/
	private int id;
	/**Le nom du joueur*/
	private String nom;

	/**Indique si l'identité est révélée ou non*/
	private boolean identiteRevelee;
	/**Nombre de points du joueur*/
	private int nbPoints;
	/**Le rôle choisi par le joueur*/
	private Role role;

	/**L'action que joue le joueur*/
	private Action actionJouee;

	/**La main du joueur*/
	private ArrayList<Carte> main;
	/**Les cartes révélées par le joueur*/
	private ArrayList<Carte> cartesRevelees;
	/**La dernière carte jouée par le joueur*/
	private Carte derniereCarteJouee;

	/**
	 * Constructeur de la classe Joueur
	 * @param nom - Nom du joueur
	 */
	public Joueur(String nom) {

		//Initialisation du joueur
		setNom(nom);
		setId(getNbJoueurs()+1);

		setIdentiteRevelee(false,false);
		setNbPoints(0);

		//Nombre de joueurs + 1
		setNbJoueurs(getNbJoueurs()+1);

		setMain(new ArrayList<Carte>());
		setCartesRevelees(new ArrayList<Carte>());

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static int getNbJoueurs() {
		return nbJoueurs;
	}

	public static void setNbJoueurs(int nbJoueurs) {
		Joueur.nbJoueurs = nbJoueurs;
	}

	public boolean isIdentiteRevelee() {
		return identiteRevelee;
	}
	
	/**
	 * Méthode permettant au joueur de révéler son identité, notifie les observateurs
	 * @param identiteRevelee - Détermine si l'identité du joueur est révélée ou non (true ou false)
	 * @param effet - Détermine si la méthode est appelée à la suite de l'execution d'un effet
	 */
	public void setIdentiteRevelee(boolean identiteRevelee, boolean effet) {
		
		if(!effet) {
			setChanged();
			notifyObservers(Message.REVELE_IDENTITE);
		}
		
		this.identiteRevelee = identiteRevelee;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	/**
	 * Méthode permettant au joueur de changer son nombre de points, notifie les observateurs
	 * @param nbPoints - Nombre de points à affecter au joueur
	 */
	public void setNbPoints(int nbPoints) {

		this.nbPoints = nbPoints;

		setChanged();
		notifyObservers(Message.CHGT_NB_POINTS);

	}

	public ArrayList<Carte> getMain() {
		return main;
	}

	public void setMain(ArrayList<Carte> main) {
		this.main = main;

	}

	public Carte getDerniereCarteJouee() {
		return derniereCarteJouee;
	}

	public void setDerniereCarteJouee(Carte derniereCarteJouee) {
		this.derniereCarteJouee = derniereCarteJouee;
	}

	public ArrayList<Carte> getCartesRevelees() {
		return cartesRevelees;
	}

	public void setCartesRevelees(ArrayList<Carte> cartesRevelees) {
		this.cartesRevelees = cartesRevelees;
	}

	public Action getActionJouee() {
		return actionJouee;
	}

	public void setActionJouee(Action actionJouee) {
		this.actionJouee = actionJouee;
	}

	/**
	 * Méthode permettant d'ajouter une carte à la main d'un joueur
	 * @param carte - Carte à ajouter à la main du joueur
	 */
	public void ajouterCarteMain(Carte carte) {

		if(!this.getMain().contains(carte)) {
			this.getMain().add(carte);

			setChanged();
			notifyObservers(Message.CHGT_CARTE_MAIN);

		}

	}

	/**
	 * Méthode permettant de retirer une carte de la main d'un joueur
	 * @param carte - Carte à retirer de la main du joueur
	 */
	public void removeCarteMain(Carte carte) {

		if(this.getMain().contains(carte)) {
			this.getMain().remove(carte);
		}

		setChanged();
		notifyObservers(Message.CHGT_CARTE_MAIN);

	}

	/**
	 * Méthode permettant d'ajouter une carte la liste des cartes révélées par le joueur
	 * @param carte - Carte à ajouter à la liste des cartes révélées
	 */
	public void ajouterCarteRevelee(Carte carte) {
		this.getCartesRevelees().add(carte);
	}

	/**
	 * Méthode permettant de retirer une carte de la liste des cartes révélées par un joueur
	 * @param carte - Carte à retirer de la liste des cartes révélées
	 */
	public void removeCarteRevelee(Carte carte) {

		if(this.getCartesRevelees().contains(carte)) {
			this.getCartesRevelees().remove(carte);
		}

	}

	/**
	 * Méthode permettant d'afficher la main d'un joueur
	 */
	public void consulterMain() {

		//Affiche la main du joueur
		System.out.println("\nMain du joueur : "+this.getNom()+"\n");
		Iterator<Carte> iteratorCarte = this.getMain().iterator();
		while(iteratorCarte.hasNext()) {
			System.out.println("- Carte : "+iteratorCarte.next().getNom());
		}

	}

	/**
	 * Méthode permettant de révéler l'identité du joueur courant
	 * @param effet - Boolean permet de déterminer si il est nécessaire de notifier les observateurs
	 */
	public void reveleIdentite(boolean effet) {

		System.out.println("> "+this.getNom()+" révèle son identité !");
		System.out.println(this.getNom()+" est "+this.getRole().toString());

		this.setIdentiteRevelee(true,effet);

		//Si le joueur est une Witch et a son identite revelee, on l'enleve de la liste des joueurs en jeu
		if(this.isIdentiteRevelee() && this.getRole() == Role.WITCH) {
			Partie.getInstance().removeJoueurListeJoueurEnJeu(this);
		}

	}

	/**
	 * Méthode permettant de regarder l'identité d'un joueur : Affiche le role du joueur passé en paramètre
	 * @param j - Joueur de qui regarder l'identité
	 */
	public void regarderIdentite(Joueur j) {
		
		String execEffet;
		
		execEffet = "Identité du joueur "+j.getNom()+" : "+j.getRole();
		Partie.getInstance().setExecutionEffet(execEffet);

	}

	/**
	 * Méthode permettant de prendre une carte aléatoire parmi une liste de cartes
	 * @param listeCartes - Liste de carte dans laquelle il faut prendre une carte
	 */
	public Carte prendreCarteAleatoire(ArrayList<Carte> listeCartes) {

		int index = (int) (Math.random() * (listeCartes.size()-1));

		Carte carteChoisie = listeCartes.get(index);

		System.out.println(carteChoisie.getNom()+" a été prise de manière aléatoire !");

		return carteChoisie;

	}

	/**
	 * Méthode permettant de révéler l'identité du joueur courant à la suite d'une accusation
	 * @param joueurAccusateur - Joueur ayant accusé le joueur accusé
	 */
	public void reveleIdentite(Joueur joueurAccusateur) {

		this.reveleIdentite(false);

		int nbPoints = 0;

		if(this.getRole() == Role.WITCH) {
			nbPoints = 1;
			Partie.getInstance().setProchainJoueur(joueurAccusateur);
		} else {
			Partie.getInstance().setProchainJoueur(this);
		}

		joueurAccusateur.addPoint(nbPoints);

	}

	/**
	 * Méthode permettant de révéler l'identité du joueur dans le cadre de l'execution d'un effet, ou il y a un nombre de point déterminé gagnés
	 * @param joueurAccusateur - Joueur ayant accusé le joueur accusé
	 * @param nbPoints - Nombre de points à ajouter ou retirer au joueur accusateur
	 */
	public void reveleIdentite(Joueur joueurAccusateur, int nbPoints) {
		
		this.reveleIdentite(false);

		if(this.getRole() == Role.WITCH) {
			joueurAccusateur.addPoint(nbPoints);
		} else {
			joueurAccusateur.removePoint(nbPoints);
		}

	}

	/**
	 * Méthode permettant de vérifier si le joueur est Witch
	 * @return True si le joueur est Witch, false sinon
	 */
	public boolean isWitch() {
		return (this.getRole() == Role.WITCH);
	}

	/**
	 * Méthode permettant de défausser une carte de la main du joueur courant
	 * @param c - Carte à défausser
	 */
	public void defausseCarte(Carte c) {

		if(this.getMain().contains(c) || this.getCartesRevelees().contains(c)) {
			System.out.println(this.getNom()+" défausse la carte "+c.getNom()+" !");
			Partie.getInstance().addCarteDefausse(c);
		}

	}

	/**
	 * Méthode permettant d'ajouter un nombre de points au joueur courant
	 * @param nbPoints - Points à ajouter
	 */
	public void addPoint(int nbPoints) {
		this.setNbPoints(this.getNbPoints()+nbPoints);
	}

	/**
	 * Méthode permettant de retirer un nombre de points au joueur courant
	 * @param nbPoints - Points à retirer
	 */
	public void removePoint(int nbPoints) {
		if(!(this.getNbPoints()-nbPoints < 0)) {
			this.setNbPoints(this.getNbPoints()-nbPoints);
		} else {
			this.setNbPoints(0);
		}
	}

	/**
	 * Méthode permettant retournant la liste des cartes que le joueur courant peut révéler
	 * @param role - Contexte dans lequel le joueur veut révéler une carte (WITCH ou HUNT)
	 * @return Cartes du joueur pouvant être révélées
	 */
	public ArrayList<Carte> getCartesPouvantEtreRevelees(Role role) {

		ArrayList<Carte> cartePouvantEtreRevelees = new ArrayList<>();

		for(Carte c : this.getMain()) {
			if(c.verifCondition(role, this)) {
				cartePouvantEtreRevelees.add(c);
			}
		}

		return cartePouvantEtreRevelees;

	}

	/**
	 * Méthode permettant à un joueur de jouer un tour : Le joueur choisi d'accuser un joueur qu'il choisit parmi les joueurs pouvant être accusés
	 * ou révèle une carte avec l'effet HUNT (execute les effets hunts liés à la carte)
	 * INFO : Après passage MVC, cette méthode est dépassée car les instructions relatives à cette méthodes sont appelées dans le controlleur
	 */
	public void joueTour() {

		System.out.println("\n** Au tour du joueur : "+this.getNom()+" ** \n");

		if(!Partie.getInstance().getEffetChoisirLance()) {

			if(this instanceof Bot) {
				setActionJouee(this.choisirAction(ChoixAction.ACCUSE_OU_RUMEUR));
			}

			if(getActionJouee() == Action.ACCUSE_JOUEUR) {
				
				this.accuseJoueur(Partie.getInstance().getJoueursPouvantEtreAccuses());

			} else {
				System.out.println("Le joueur a décidé de révéler une carte !");
			}

		} else {
			System.out.println("Effet lancé !");
		}

	}

	/**
	 * Méthode permettant au joueur de jouer son tour en fonction d'une action choisie
	 * @param actionChoisie L'action qui a été choisie par le joueur (<strong>accuser</strong> un autre joueur ou <strong>révéler</strong> une carte)
	 * INFO : Après passage MVC, cette méthode est dépassée car les instructions relatives à cette méthodes sont appelées dans le controlleur
	 */
	public void joueTour(Action actionChoisie) {

		System.out.println("\n** Au tour du joueur : "+this.getNom()+" ** \n");

		if(!Partie.getInstance().getEffetChoisirLance()) {

			if(this instanceof Bot) {
				setActionJouee(this.choisirAction(ChoixAction.ACCUSE_OU_RUMEUR));
			}

			if(actionChoisie == Action.ACCUSE_JOUEUR) {
				
				System.out.println("le joueur accuse");
				this.accuseJoueur(Partie.getInstance().getJoueursPouvantEtreAccuses());

			} else {

				System.out.println("Le joueur a décidé de révéler une carte !");
	
			}


		} else {

			System.out.println("Effet lancé !");

		}

	}


	/**
	 *Méthode permettant à un joueur de se défendre lorsqu'il se fait accuser : Le joueur choisi de révéler son identité ou révéler une carte avec l'effet WITCH (execute les effets
	 * witch liés à la carte)
	 * 
	 * INFO : Cette méthode est dépassée car les instructions relatives au fait de se défendre sont maintenant appelées dans le controlleur
	 * @param joueurAccusateur Le joueur qui accuse celui qui utilise la méthode seDefendre
	 */
	public void seDefendre(Joueur joueurAccusateur) {

		System.out.println("\nAttention "+this.getNom()+" ! "+joueurAccusateur.getNom()+" vous accuse ! ");

		Action actionJouee = this.choisirAction(ChoixAction.IDENTITE_OU_RUMEUR);

		if(actionJouee == Action.REVELE_IDENTITE) {

			//Joueur revele son identite
			this.reveleIdentite(joueurAccusateur);

			//Sinon il revele une carte
		} else {

			//Le joueur revele la carte
			Carte carteRevelee = this.reveleCarte(Role.WITCH);

			//Execution des effets associes a la carte
			carteRevelee.executerEffets(Role.WITCH, this, joueurAccusateur);

		}

	}

	/**
	 * Méthode permettant de reveler une carte en retirant une carte de la main du joueur et en l'ajoutant a la liste des cartes revelees
	 * @param carteChoisie La carte que l'on souhaite retirer de sa main (donc jouer). Elle sera ajoutée à la liste des cartes revelees
	 */
	public void reveleCarte(Carte carteChoisie) {

		this.removeCarteMain(carteChoisie);
		this.ajouterCarteRevelee(carteChoisie);

		this.setDerniereCarteJouee(carteChoisie);

	}

	/**
	 * Méthode permettant d'initier la révélation d'une carte : le joueur choisit une carte à révéler parmi les cartes pouvant être révélées
	 * avant de révéler la carte
	 * @param role Le rôle (WITCH ou HUNT) dont on veut executer les effets. Le choix du role differe en fonction de la situation. (WITCH : lors de joueTour() / HUNT: lors de seDefendre())
	 * @return La carte choisie
	 */
	public Carte reveleCarte(Role role) {

		System.out.println("\n> "+this.getNom()+" révèle une carte rumeur !");

		Carte carteChoisie = choisirCarteAReveler(this.getCartesPouvantEtreRevelees(role));

		System.out.println("\n"+this.getNom()+" révèle la carte "+carteChoisie.getNom()+"\n");

		//Retirer carte de la main et l'ajouter aux cartes révélées
		reveleCarte(carteChoisie);

		//Retourne la carte qui a été révélée
		return carteChoisie;

	}

	/**
	 * Méthode permettant de choisir un joueur a accuser parmis une liste de joueurs
	 * @param joueurs La liste de joueurs pouvant être choisis pour être accusé
	 */
	public void accuseJoueur(ArrayList<Joueur> joueurs) {

		System.out.println("\n> "+this.getNom()+" accuse un joueur ! \n");
		Joueur accuse = choisirJoueurAAccuser(joueurs);

		accuse.seDefendre(this);

	}

	/**
	 * Méthode permettant d'obtenir la liste des joueurs autre que le joueur courant ayant une carte révélée
	 * 
	 * @return Liste des joueurs ayant une carte révélée
	 */
	public ArrayList<Joueur> getJoueursAvecCarteRevelee() {

		ArrayList<Joueur> listeJoueurs = new ArrayList<>();

		for(Joueur j : Partie.getInstance().getListeJoueursEnJeu()) {
			if(j.getCartesRevelees().size() > 0 && j.getId() != this.getId()) {
				listeJoueurs.add(j);
			}
		}

		return listeJoueurs;

	}
	
	/**
	 * Méthode permettant de récupérer un joueur à partir de son nom, parmi une liste de joueurs définie
	 * @param listeJoueurs La liste des joueurs parmis laquelle on souhaite chercher le joueur
	 * @param nomJoueur Le nom du joueur que l'on souhaite récupérer
	 * @return Le joueur qui a ete trouvé
	 */
	public static Joueur recupererJoueur(ArrayList<Joueur> listeJoueurs, String nomJoueur) {
		
		int indexJoueur = 0;
		boolean trouve = false;

		while(indexJoueur < listeJoueurs.size() && !trouve) {
			if(listeJoueurs.get(indexJoueur).getNom().toLowerCase().equals(nomJoueur.toLowerCase())) {
				trouve = true;
				break;
			}
			
			if(!trouve) {
				indexJoueur++;
			}
		}
		
		return listeJoueurs.get(indexJoueur);
		
	}

	//Methodes utilisees dans les effets

	public abstract Joueur choisirJoueurAReveler(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	public abstract Joueur choisirJoueurCibleEffet(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	public abstract Joueur choisirProchainJoueur(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	public abstract Joueur choisirJoueurPrendreCarte(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	public abstract Carte choisirCarteADefausser(ArrayList<Carte> cartesPouvantEtreDefaussees);
	public abstract Carte choisirCarteAPrendre(ArrayList<Carte> cartePouvantEtreRecuperees);

	//Methodes utilisees dans la boucle de jeu

	public abstract Carte choisirCarteAReveler(ArrayList<Carte> cartesPouvantEtreRevelees);
	public abstract Joueur choisirJoueurAAccuser(ArrayList<Joueur> listeJoueursPouvantEtreChoisis);
	public abstract void choisirRole();
	public abstract Action choisirAction(ChoixAction choixAction);

}
