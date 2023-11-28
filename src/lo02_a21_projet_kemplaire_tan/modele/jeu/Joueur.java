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
* Classe repr�sentant un Joueur au sens large (pouvant �tre un Bot ou un Humain) - Pr�voit des m�thodes pour l'ensemble des comportements que peut avoir un joueur
* Un joueur est caract�ris� par un nom, un id, une identit� r�v�l�e ou non, un nombre de points, un role. Il a une main et des des cartes r�v�l�es
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

	/**L'ID qui est attribu� au joueur*/
	private int id;
	/**Le nom du joueur*/
	private String nom;

	/**Indique si l'identit� est r�v�l�e ou non*/
	private boolean identiteRevelee;
	/**Nombre de points du joueur*/
	private int nbPoints;
	/**Le r�le choisi par le joueur*/
	private Role role;

	/**L'action que joue le joueur*/
	private Action actionJouee;

	/**La main du joueur*/
	private ArrayList<Carte> main;
	/**Les cartes r�v�l�es par le joueur*/
	private ArrayList<Carte> cartesRevelees;
	/**La derni�re carte jou�e par le joueur*/
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
	 * M�thode permettant au joueur de r�v�ler son identit�, notifie les observateurs
	 * @param identiteRevelee - D�termine si l'identit� du joueur est r�v�l�e ou non (true ou false)
	 * @param effet - D�termine si la m�thode est appel�e � la suite de l'execution d'un effet
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
	 * M�thode permettant au joueur de changer son nombre de points, notifie les observateurs
	 * @param nbPoints - Nombre de points � affecter au joueur
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
	 * M�thode permettant d'ajouter une carte � la main d'un joueur
	 * @param carte - Carte � ajouter � la main du joueur
	 */
	public void ajouterCarteMain(Carte carte) {

		if(!this.getMain().contains(carte)) {
			this.getMain().add(carte);

			setChanged();
			notifyObservers(Message.CHGT_CARTE_MAIN);

		}

	}

	/**
	 * M�thode permettant de retirer une carte de la main d'un joueur
	 * @param carte - Carte � retirer de la main du joueur
	 */
	public void removeCarteMain(Carte carte) {

		if(this.getMain().contains(carte)) {
			this.getMain().remove(carte);
		}

		setChanged();
		notifyObservers(Message.CHGT_CARTE_MAIN);

	}

	/**
	 * M�thode permettant d'ajouter une carte la liste des cartes r�v�l�es par le joueur
	 * @param carte - Carte � ajouter � la liste des cartes r�v�l�es
	 */
	public void ajouterCarteRevelee(Carte carte) {
		this.getCartesRevelees().add(carte);
	}

	/**
	 * M�thode permettant de retirer une carte de la liste des cartes r�v�l�es par un joueur
	 * @param carte - Carte � retirer de la liste des cartes r�v�l�es
	 */
	public void removeCarteRevelee(Carte carte) {

		if(this.getCartesRevelees().contains(carte)) {
			this.getCartesRevelees().remove(carte);
		}

	}

	/**
	 * M�thode permettant d'afficher la main d'un joueur
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
	 * M�thode permettant de r�v�ler l'identit� du joueur courant
	 * @param effet - Boolean permet de d�terminer si il est n�cessaire de notifier les observateurs
	 */
	public void reveleIdentite(boolean effet) {

		System.out.println("> "+this.getNom()+" r�v�le son identit� !");
		System.out.println(this.getNom()+" est "+this.getRole().toString());

		this.setIdentiteRevelee(true,effet);

		//Si le joueur est une Witch et a son identite revelee, on l'enleve de la liste des joueurs en jeu
		if(this.isIdentiteRevelee() && this.getRole() == Role.WITCH) {
			Partie.getInstance().removeJoueurListeJoueurEnJeu(this);
		}

	}

	/**
	 * M�thode permettant de regarder l'identit� d'un joueur : Affiche le role du joueur pass� en param�tre
	 * @param j - Joueur de qui regarder l'identit�
	 */
	public void regarderIdentite(Joueur j) {
		
		String execEffet;
		
		execEffet = "Identit� du joueur "+j.getNom()+" : "+j.getRole();
		Partie.getInstance().setExecutionEffet(execEffet);

	}

	/**
	 * M�thode permettant de prendre une carte al�atoire parmi une liste de cartes
	 * @param listeCartes - Liste de carte dans laquelle il faut prendre une carte
	 */
	public Carte prendreCarteAleatoire(ArrayList<Carte> listeCartes) {

		int index = (int) (Math.random() * (listeCartes.size()-1));

		Carte carteChoisie = listeCartes.get(index);

		System.out.println(carteChoisie.getNom()+" a �t� prise de mani�re al�atoire !");

		return carteChoisie;

	}

	/**
	 * M�thode permettant de r�v�ler l'identit� du joueur courant � la suite d'une accusation
	 * @param joueurAccusateur - Joueur ayant accus� le joueur accus�
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
	 * M�thode permettant de r�v�ler l'identit� du joueur dans le cadre de l'execution d'un effet, ou il y a un nombre de point d�termin� gagn�s
	 * @param joueurAccusateur - Joueur ayant accus� le joueur accus�
	 * @param nbPoints - Nombre de points � ajouter ou retirer au joueur accusateur
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
	 * M�thode permettant de v�rifier si le joueur est Witch
	 * @return True si le joueur est Witch, false sinon
	 */
	public boolean isWitch() {
		return (this.getRole() == Role.WITCH);
	}

	/**
	 * M�thode permettant de d�fausser une carte de la main du joueur courant
	 * @param c - Carte � d�fausser
	 */
	public void defausseCarte(Carte c) {

		if(this.getMain().contains(c) || this.getCartesRevelees().contains(c)) {
			System.out.println(this.getNom()+" d�fausse la carte "+c.getNom()+" !");
			Partie.getInstance().addCarteDefausse(c);
		}

	}

	/**
	 * M�thode permettant d'ajouter un nombre de points au joueur courant
	 * @param nbPoints - Points � ajouter
	 */
	public void addPoint(int nbPoints) {
		this.setNbPoints(this.getNbPoints()+nbPoints);
	}

	/**
	 * M�thode permettant de retirer un nombre de points au joueur courant
	 * @param nbPoints - Points � retirer
	 */
	public void removePoint(int nbPoints) {
		if(!(this.getNbPoints()-nbPoints < 0)) {
			this.setNbPoints(this.getNbPoints()-nbPoints);
		} else {
			this.setNbPoints(0);
		}
	}

	/**
	 * M�thode permettant retournant la liste des cartes que le joueur courant peut r�v�ler
	 * @param role - Contexte dans lequel le joueur veut r�v�ler une carte (WITCH ou HUNT)
	 * @return Cartes du joueur pouvant �tre r�v�l�es
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
	 * M�thode permettant � un joueur de jouer un tour : Le joueur choisi d'accuser un joueur qu'il choisit parmi les joueurs pouvant �tre accus�s
	 * ou r�v�le une carte avec l'effet HUNT (execute les effets hunts li�s � la carte)
	 * INFO : Apr�s passage MVC, cette m�thode est d�pass�e car les instructions relatives � cette m�thodes sont appel�es dans le controlleur
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
				System.out.println("Le joueur a d�cid� de r�v�ler une carte !");
			}

		} else {
			System.out.println("Effet lanc� !");
		}

	}

	/**
	 * M�thode permettant au joueur de jouer son tour en fonction d'une action choisie
	 * @param actionChoisie L'action qui a �t� choisie par le joueur (<strong>accuser</strong> un autre joueur ou <strong>r�v�ler</strong> une carte)
	 * INFO : Apr�s passage MVC, cette m�thode est d�pass�e car les instructions relatives � cette m�thodes sont appel�es dans le controlleur
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

				System.out.println("Le joueur a d�cid� de r�v�ler une carte !");
	
			}


		} else {

			System.out.println("Effet lanc� !");

		}

	}


	/**
	 *M�thode permettant � un joueur de se d�fendre lorsqu'il se fait accuser : Le joueur choisi de r�v�ler son identit� ou r�v�ler une carte avec l'effet WITCH (execute les effets
	 * witch li�s � la carte)
	 * 
	 * INFO : Cette m�thode est d�pass�e car les instructions relatives au fait de se d�fendre sont maintenant appel�es dans le controlleur
	 * @param joueurAccusateur Le joueur qui accuse celui qui utilise la m�thode seDefendre
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
	 * M�thode permettant de reveler une carte en retirant une carte de la main du joueur et en l'ajoutant a la liste des cartes revelees
	 * @param carteChoisie La carte que l'on souhaite retirer de sa main (donc jouer). Elle sera ajout�e � la liste des cartes revelees
	 */
	public void reveleCarte(Carte carteChoisie) {

		this.removeCarteMain(carteChoisie);
		this.ajouterCarteRevelee(carteChoisie);

		this.setDerniereCarteJouee(carteChoisie);

	}

	/**
	 * M�thode permettant d'initier la r�v�lation d'une carte : le joueur choisit une carte � r�v�ler parmi les cartes pouvant �tre r�v�l�es
	 * avant de r�v�ler la carte
	 * @param role Le r�le (WITCH ou HUNT) dont on veut executer les effets. Le choix du role differe en fonction de la situation. (WITCH : lors de joueTour() / HUNT: lors de seDefendre())
	 * @return La carte choisie
	 */
	public Carte reveleCarte(Role role) {

		System.out.println("\n> "+this.getNom()+" r�v�le une carte rumeur !");

		Carte carteChoisie = choisirCarteAReveler(this.getCartesPouvantEtreRevelees(role));

		System.out.println("\n"+this.getNom()+" r�v�le la carte "+carteChoisie.getNom()+"\n");

		//Retirer carte de la main et l'ajouter aux cartes r�v�l�es
		reveleCarte(carteChoisie);

		//Retourne la carte qui a �t� r�v�l�e
		return carteChoisie;

	}

	/**
	 * M�thode permettant de choisir un joueur a accuser parmis une liste de joueurs
	 * @param joueurs La liste de joueurs pouvant �tre choisis pour �tre accus�
	 */
	public void accuseJoueur(ArrayList<Joueur> joueurs) {

		System.out.println("\n> "+this.getNom()+" accuse un joueur ! \n");
		Joueur accuse = choisirJoueurAAccuser(joueurs);

		accuse.seDefendre(this);

	}

	/**
	 * M�thode permettant d'obtenir la liste des joueurs autre que le joueur courant ayant une carte r�v�l�e
	 * 
	 * @return Liste des joueurs ayant une carte r�v�l�e
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
	 * M�thode permettant de r�cup�rer un joueur � partir de son nom, parmi une liste de joueurs d�finie
	 * @param listeJoueurs La liste des joueurs parmis laquelle on souhaite chercher le joueur
	 * @param nomJoueur Le nom du joueur que l'on souhaite r�cup�rer
	 * @return Le joueur qui a ete trouv�
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
