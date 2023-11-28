package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Observable;

import lo02_a21_projet_kemplaire_tan.modele.conditions.*;
import lo02_a21_projet_kemplaire_tan.modele.effets.*;

/**
* Classe Partie
*/
/**
* 
* Classe centrale du jeu. Permet de g�rer les �v�nements du jeu, les liens entre les joueurs, le lancement des actions, g�re les rounds, tours et indique si une partie est termin�e ou non.
* Classe respectant le patron Singleton repr�sentant une partie. Poss�de un ensemble d'attributs qui permet de caract�riser l'�tat de la partie :
* liste de joueurs initiale, liste de joueurs en jeu, une d�fausse, un joueur pr�c�dent / courant / suivant / accus�, un num�ro de round, 
* des strat�gies ...
* 
* Cette classe impl�mente la logique du d�roulement d'une partie et la boucle de jeu, allant de l'initialisation de la partie, au d�roulement des rounds jusqu'� la fin de la partie.
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu
* @see lo02_a21_projet_kemplaire_tan.modele.conditions
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* 
*/
public class Partie extends Observable {

	/**indique si la partie est commencee*/
	private boolean estCommencee;

	/** joueur precedent le joueur courant */
	private Joueur joueurPrecedent;
	/** joueur courant qui joue son tour */
	private Joueur joueurCourant;
	/** prochain joueur qui jouera son tour */
	private Joueur prochainJoueur;
	/** le joueur accus�*/
	private Joueur joueurAccuse;
	/** nombre de joueurs humains*/
	private int nbJoueursHumains;
	/** nombre de joueurs bots*/
	private int nbJoueursBots;
	
	/**nombre de rounds dans la partie*/
	private int nbRound = 0;
	
	/**Les effets a executer*/
	private String executionEffet;

	/** Effet accuser un joueur pendant son tour : effet lance */
	private boolean effetChoisirLance = false;

	/**liste des joueurs crees a l'initialisation d'une partie*/
	private ArrayList<Joueur> listeJoueursInitial;
	/**liste des joueurs encore en jeu*/
	private ArrayList<Joueur> listeJoueursEnJeu;
	
	/**liste des cartes defaussees*/
	private LinkedList<Carte> listeDefausse;
	/**jeu de carte initial*/
	private LinkedList<Carte> tasInitial;
	
	/**instance de partie*/
	private static Partie partie = null;
	
	/**joueur choisi lorsqu'il faut choisir un joueur*/
	private Joueur joueurChoisi;
	/**carte choisie lorsqu'il faut choisir une carte*/
	private Carte carteChoisie;
	/**action choisie lorsqu'il faut choisir une action*/
	private Action actionChoisie;
	
	/**une erreur*/
	private String error;
	/**indique s'il y a une erreur*/
	private boolean isError;
	
	/**strategies du BOT*/
	private IAStrategy[] strategies;

	/**
	 * Constructeur de Partie
	 */
	private Partie() {
		
		//Initialisation de la partie
	
		this.setEstCommencee(false);
		
	}
	
	/**
	 * M�thode de classe permettant de r�cup�rer l'unique instance de la classe partie
	 * 
	 * @return Unique instance de la classe partie
	 */
	public static Partie getInstance() {
		
		//Patron Singleton : si la Partie n'est pas instanci�e, on l'instancie
		if(Partie.partie == null) {
			Partie.partie = new Partie();
		}
		
		//On retourne l'instance de Partie
		return Partie.partie;
	}
	
	public String getError() {
		return this.error;
	}

	/**
	 * M�thode permettant de stocker la description d'une erreur ayant eu lieu, les Observateurs sont notifi�s pour �tre inform�s
	 * 
	 * @param error : Description de l'erreur
	 */
	public void setError(String error) {
		
		this.error = error;
		
		setChanged();
		notifyObservers(Message.ERROR);
		
	}
	
	public boolean isError() {
		return this.isError;
	}
	
	/**
	 * M�thode permettant de d�terminer qu'une erreur vient d'avoir lieu, les Observateurs sont notifi�s pour �tre inform�s
	 * 
	 * @param error : D�termine s'il y a erreur ou non (selon sa valeur true ou false)
	 */
	public void setIsError(boolean error) {
		this.isError = error;
		
		setChanged();
		notifyObservers(Message.ERROR);
	}
	
	/**
	 * M�thode permettant de stocker la trace relative � la description d'un effet execut� (actions effectu�es dans le cadre de l'execution de l'effet)
	 * Cela permet aux Observateurs d'acc�der � la descriptions des actions entreprises dans le cadre de l'execution d'un effet pour l'afficher
	 * Les observateurs sont notifi�s
	 * 
	 * @param descriptionEffet : Trace de l'execution de l'effet
	 */
	public void setExecutionEffet(String descriptionEffet) {
		this.executionEffet = descriptionEffet;
		
		setChanged();
		notifyObservers(Message.EXECUTION_EFFET);
	}
	
	public String getExecutionEffet() {
		return this.executionEffet;
	}
	
	public Carte getCarteChoisie() {
		return this.carteChoisie;
	}
	
	public void setCarteChoisie(Carte carte) {
		this.carteChoisie = carte;
	}
	
	public Joueur getJoueurChoisi() {
		return this.joueurChoisi;
	}
	
	public void setJoueurChoisi(Joueur joueurChoisi) {
		this.joueurChoisi = joueurChoisi;
	}
	
	/**
	 * R�cup�re le joueur accus�
	 * @return Le joueur accus�
	 */
	public Joueur getJoueurAccuse() {
		return this.joueurAccuse;
	}

	/**
	 * Indiquer le joueur accus�
	 * @param j - Le joueur qu'on accuse
	 */
	public void setJoueurAccuse(Joueur j) {
		this.joueurAccuse = j;
	}

	public Action getActionChoisie() {
		return actionChoisie;
	}

	public void setActionChoisie(Action actionChoisie) {
		this.actionChoisie = actionChoisie;
	}
	
	public int getNbJoueursHumains() {
		return nbJoueursHumains;
	}
	
	public int getNbJoueursBots() {
		return nbJoueursBots;
	}

	/**
	 * M�thode permettant de mettre � jour le nombre de joueurs Humain et Bot dans la partie
	 * Les observateurs sont notifi�s pour mettre � jour la liste des joueurs sur l'interface
	 * 
	 * @param nbJoueursHumains : Nombre de joueurs Humains
	 * @param nbJoueursBots : Nombre de joueurs Bot
	 */
	public void setNbJoueurs(int nbJoueursHumains, int nbJoueursBots) {
		
		this.nbJoueursHumains = nbJoueursHumains;
		this.nbJoueursBots = nbJoueursBots;
		
		setChanged();
		notifyObservers(Message.SET_NB_JOUEURS);
		
	}
	
	/** Retourne si l'effet choisir un joueur � accuser a �t� lanc� */
	public boolean getEffetChoisirLance(){
		return this.effetChoisirLance;
	}

	/** D�finie si l'effet choisir un joueur � accuser a �t� lanc� */
	public void setEffetChoisirLance(boolean b){
		this.effetChoisirLance = b;
	}

	/** Retourne le joueur pr�c�dent */
	public Joueur getJoueurPrecedent(){
		return this.joueurPrecedent;
	}

	/** D�finie le joueur pr�c�dent */
	public void setJoueurPrecedent(Joueur joueurPrecedent){
		this.joueurPrecedent = joueurPrecedent;
	}
	
	/**Retourne la liste globale des joueurs*/
	public ArrayList<Joueur> getListeJoueursInitial() {
		return listeJoueursInitial;
	}

	/**D�finie la liste globale des joueurs*/
	public void setListeJoueursInitial(ArrayList<Joueur> listeJoueursInitial) {
		this.listeJoueursInitial = listeJoueursInitial;
	}

	/**Retourne la liste des joueurs en jeu*/
	public ArrayList<Joueur> getListeJoueursEnJeu() {
		return listeJoueursEnJeu;
	}

	/**Affecte une nouvelle liste de joueurs en jeu, notifie les Observateurs
	 * @param listeJoueursEnJeu : Nouvelle liste de joueurs en jeu
	 */
	public void setListeJoueursEnJeu(ArrayList<Joueur> listeJoueursEnJeu) {
		
		this.listeJoueursEnJeu = listeJoueursEnJeu;
		
		setChanged();
		notifyObservers(Message.CHGT_JOUEURS_EN_JEU);
		
	}
	
	public int getNbRound() {
		return nbRound;
	}

	/**D�finit le num�ro du round
	 * @param nbRound : Num�ro du round
	 */
	public void setNbRound(int nbRound) {
		
		this.nbRound = nbRound;
		
		setChanged();
		notifyObservers(Message.SET_NB_ROUND);
		
	}

	/**
	 * M�thode permettant d'ajouter un joueur a la liste globale des joueurs, si ce dernier n'est pas deja present dans la liste
	 * @param j Le joueur qu'on souhaite ajouter
	 */
	public void addJoueurListeJoueurInitial(Joueur j) {
		if(!this.getListeJoueursInitial().contains(j)) {
			this.getListeJoueursInitial().add(j);
		}
	}
	
	/**
	 * Ajoute un joueur a la liste des joueurs en jeu, si ce dernier n'est pas deja present dans la liste
	 * @param j Le joueur que l'on veut ajouter
	 */
	public void addJoueurListeJoueurEnJeu(Joueur j) {
		
		if(!this.getListeJoueursEnJeu().contains(j)) {
			this.getListeJoueursEnJeu().add(j);
			
			setChanged();
			notifyObservers(Message.CHGT_JOUEURS_EN_JEU);
		}
	}
	
	/**
	 * Enleve un joueur de la liste des joueurs en jeu, si ce dernier est present dans la liste, notifie les observateurs
	 * @param j Le joueur que l'on veut retirer de la liste
	 */
	public void removeJoueurListeJoueurEnJeu(Joueur j) {
		if(this.getListeJoueursEnJeu().contains(j)) {
			
			this.getListeJoueursEnJeu().remove(j);
			
			setChanged();
			notifyObservers(Message.CHGT_JOUEURS_EN_JEU);
		}
	}
	
	public Joueur getProchainJoueur() {
		return this.prochainJoueur;
	}

	/**
	 * D�finit le prochain joueur
	 * @param prochainJoueur Joueur destin� � �tre le prochain joueur
	 */
	public void setProchainJoueur(Joueur prochainJoueur) {
		this.prochainJoueur = prochainJoueur;	
		setChanged();
		notifyObservers(Message.PROCHAIN_JOUEUR);
	}

	/**Retourne l'etat de la partie*/
	public boolean isEstCommencee() {
		return this.estCommencee;
	}

	/**Definie l'etat de la partie*/
	public void setEstCommencee(boolean estCommencee) {
		this.estCommencee = estCommencee;
	}

	/**Retourne le joueur courant*/
	public Joueur getJoueurCourant() {
		return this.joueurCourant;
	}

	/**Definie le joueur courant*/
	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
		setChanged();
		notifyObservers(Message.SET_JOUEUR_COURANT);
	}

	/**Retourne la defausse*/
	public LinkedList<Carte> getListeDefausse() {
		return listeDefausse;
	}

	/**Definie la defausse*/
	public void setListeDefausse(LinkedList<Carte> listeDefausse) {
		this.listeDefausse = listeDefausse;
	}
	
	/**Ajoute une carte a la defausse, si elle n'est pas*/ 
	public void ajouteCarteDefausse(Carte carte) {
		this.getListeDefausse().offer(carte);
	}
	
	/**Permet d'ajouter les strategies dans un tableau*/
	public void initierStrategies() {
		
		strategies = new IAStrategy[4];

		strategies[0] = new StrategieRandom();
		strategies[1] = new StrategieActive();
		strategies[2] = new StrategiePassive();
		strategies[3] = new StrategieSmart();
	}
	
	/**
	 * Methode permettant de retirer une carte de la defausse si elle n'est pas vide
	 * @return La carte que l'on choisi de retirer de la defausse
	 */
	public Carte retireCarteDefausse() {
		
		Carte carteRetiree = null;
		
		if(this.getListeDefausse().size() > 0) {
			
			carteRetiree = this.getListeDefausse().poll();
			System.out.println("La carte "+carteRetiree.getNom()+" a �t� retir�e de la d�fausse");
			
		} else {
			
			System.out.println("La d�fausse est vide !");
			
		}
		
		return carteRetiree;
		
	}

	public LinkedList<Carte> getTasInitial() {
		return tasInitial;
	}

	public void setTasInitial(LinkedList<Carte> tasInitial) {
		this.tasInitial = tasInitial;
	}

	/**
	 * M�thode destin�e � initier une Partie
	 * Cr�e le tas de carte, la d�fausse, les listes de joueurs, les strat�gies et les cartes
	 */
	public void initierPartie() {
		
		System.out.println("** Configuration de la partie ** \n");
		
		this.setEffetChoisirLance(false);
		
		//Cr�ation de la d�fausse
		setListeDefausse(new LinkedList<Carte>());
		
		//Cr�ation du tas initial de cartes
		setTasInitial(new LinkedList<Carte>());			
		
		//Cr�ation de la liste globale des joueurs
		setListeJoueursInitial(new ArrayList<Joueur>());				
		
		//Cr�ation de la liste des joueurs en jeu
		setListeJoueursEnJeu(new ArrayList<Joueur>());
		
		initierStrategies();
		
		//Cr�ation des cartes
		creationCartes();
		
	}
	
	/**
	 * M�thode permettant d'initier la partie en initiant un round
	 */
	public void debuterPartie() {
		initierRound();
	}
	
	/**
	 * M�thode permettant de distribuer les cartes aux diff�rents joueurs
	 */
	public void distribuerCartes() {
		
		melangerCartes();
		
		LinkedList<Carte> cartesADistribuer = new LinkedList<>();
		cartesADistribuer.addAll(tasInitial);
		
		//Calcul du nombre de carte � distribuer
		int nbCartesADistribuer = Math.round(cartesADistribuer.size() / this.getListeJoueursInitial().size());
		int nbCartesDistribueesParJoueur = 0;
		
		System.out.println("Nombre de cartes � distribuer par joueur : "+nbCartesADistribuer);
		
		//Distribution
		while(nbCartesDistribueesParJoueur != nbCartesADistribuer) {
			for(Joueur j : this.getListeJoueursEnJeu()) {
				j.ajouterCarteMain(cartesADistribuer.poll());
			}
			nbCartesDistribueesParJoueur++;
		}
		
		this.afficheMainsJoueurs();
		
	}
	
	/**
	 * M�thode permettant de cr�er le jeu de carte et les effets/conditions associ�es
	 */
	public void creationCartes() {
		
		DefausseUneCarte defausseUneCarte = new DefausseUneCarte();
		PrendreProchainTour prendreProchainTour = new PrendreProchainTour();
		RevelerIdentiteDeQlqn revelerIdentiteQlqn = new RevelerIdentiteDeQlqn();
		ChoisirProchainJoueur choisirProchainJoueur = new ChoisirProchainJoueur();
		PrendreCarteMainAccusateur prendreCarteMainAccusateur = new PrendreCarteMainAccusateur();
		PrendreCarteProchainJoueur prendreCarteProchainJoueur = new PrendreCarteProchainJoueur();
		ReprendreCarteRevelee reprendreCarteRevelee = new ReprendreCarteRevelee();
		RegarderIdentiteProchainJoueur regarderIdentiteProchainJoueur = new RegarderIdentiteProchainJoueur();
		RevelerOuDefausser revelerOuDefausser = new RevelerOuDefausser();
		DefausseUneCarteAleatoire defausseUneCarteAleatoire = new DefausseUneCarteAleatoire();
		ReveleSonIdentite revelerSonIdentite = new ReveleSonIdentite();
		ChoisirProchainJoueurSpecial choisirProchainJoueurSpecial = new ChoisirProchainJoueurSpecial();
		PrendreCarteReveleeAutreJoueur prendreCarteReveleeAutreJoueur = new PrendreCarteReveleeAutreJoueur();
		PrendreCarteDefausse prendreCarteDefausse = new PrendreCarteDefausse();
		
		ArrayList<Effet> effetsWitchAngryMob = new ArrayList<>();
		ArrayList<Effet> effetsHuntAngryMob = new ArrayList<>();
		effetsWitchAngryMob.add(prendreProchainTour);
		effetsHuntAngryMob.add(revelerIdentiteQlqn);

		ArrayList<Effet> effetsWitchTheInquisition = new ArrayList<>();
		ArrayList<Effet> effetsHuntTheInquisition = new ArrayList<>();
		effetsWitchTheInquisition.add(defausseUneCarte);
		effetsWitchTheInquisition.add(prendreProchainTour);
		effetsHuntTheInquisition.add(choisirProchainJoueur);
		effetsHuntTheInquisition.add(regarderIdentiteProchainJoueur);

		ArrayList<Effet> effetsWitchPointedHat = new ArrayList<>();
		ArrayList<Effet> effetsHuntPointedHat = new ArrayList<>();
		effetsWitchPointedHat.add(reprendreCarteRevelee);
		effetsWitchPointedHat.add(prendreProchainTour);
		effetsHuntPointedHat.add(reprendreCarteRevelee);
		effetsHuntPointedHat.add(choisirProchainJoueur);

		ArrayList<Effet> effetsWitchNose = new ArrayList<>();
		ArrayList<Effet> effetsHuntNose = new ArrayList<>();
		effetsWitchNose.add(prendreCarteMainAccusateur);
		effetsWitchNose.add(prendreProchainTour);
		effetsHuntNose.add(choisirProchainJoueur);
		effetsHuntNose.add(prendreCarteProchainJoueur);

		ArrayList<Effet> effetsWitchBroomStick = new ArrayList<>();
		ArrayList<Effet> effetsHuntBroomStick = new ArrayList<>();
		effetsWitchBroomStick.add(prendreProchainTour);
		effetsHuntBroomStick.add(choisirProchainJoueur);

		ArrayList<Effet> effetsWitchWart = new ArrayList<>();
		ArrayList<Effet> effetsHuntWart = new ArrayList<>();
		effetsWitchWart.add(prendreProchainTour);
		effetsHuntWart.add(choisirProchainJoueur);

		ArrayList<Effet> effetsWitchDuckingStool = new ArrayList<>();
		ArrayList<Effet> effetsHuntDuckingStool = new ArrayList<>();
		effetsWitchDuckingStool.add(choisirProchainJoueur);
		effetsHuntDuckingStool.add(revelerOuDefausser);

		ArrayList<Effet> effetsWitchCauldron = new ArrayList<>();
		ArrayList<Effet> effetsHuntCauldron = new ArrayList<>();
		effetsWitchCauldron.add(defausseUneCarteAleatoire);
		effetsHuntCauldron.add(revelerSonIdentite);

		ArrayList<Effet> effetsWitchEye = new ArrayList<>();
		ArrayList<Effet> effetsHuntEye = new ArrayList<>();
		effetsWitchEye.add(choisirProchainJoueurSpecial);
		effetsHuntEye.add(choisirProchainJoueurSpecial);

		ArrayList<Effet> effetsWitchToad = new ArrayList<>();
		ArrayList<Effet> effetsHuntToad = new ArrayList<>();
		effetsWitchToad.add(prendreProchainTour);
		effetsHuntToad.add(revelerSonIdentite);

		ArrayList<Effet> effetsWitchBlackCat = new ArrayList<>();
		ArrayList<Effet> effetsHuntBlackCat = new ArrayList<>();
		effetsWitchBlackCat.add(prendreProchainTour);
		//Mettre l'effet PrendreCarteDefausse de L�o
		effetsHuntBlackCat.add(prendreCarteDefausse);
		effetsHuntBlackCat.add(prendreProchainTour);

		ArrayList<Effet> effetsWitchNewt = new ArrayList<>();
		ArrayList<Effet> effetsHuntNewt = new ArrayList<>();
		effetsWitchNewt.add(prendreProchainTour);
		//Mettre l'effet PrendreCarteReveleeAutreJoueur de L�o
		effetsHuntNewt.add(prendreCarteReveleeAutreJoueur);
		effetsHuntNewt.add(choisirProchainJoueur);

		
		//Cr�ation des nullitions
		
		EtreReveleVillageois villageois = new EtreReveleVillageois();
		AvoirReveleCarte avoirReveleCarte = new AvoirReveleCarte();
		
		//Cr�ation des cartes
		
		Carte angryMob = new Carte(CarteNom.ANGRY,effetsWitchAngryMob,effetsHuntAngryMob,null,villageois);
		Carte theInquisition = new Carte(CarteNom.INQUISITION,effetsWitchTheInquisition,effetsHuntTheInquisition,null,villageois);
		Carte pointedHat = new Carte(CarteNom.HAT,effetsWitchPointedHat,effetsHuntPointedHat,avoirReveleCarte,avoirReveleCarte);
		Carte hookedNose = new Carte(CarteNom.NOSE,effetsWitchNose,effetsHuntNose,null,null);
		Carte broomStick = new Carte(CarteNom.BROOMSTICK,effetsWitchBroomStick,effetsHuntBroomStick,null,null);
		Carte wart = new Carte(CarteNom.WART,effetsWitchWart,effetsHuntWart,null,null);
		Carte duckingStool = new Carte(CarteNom.DUCKING,effetsWitchDuckingStool,effetsHuntDuckingStool,null,null);	
		Carte cauldron = new Carte(CarteNom.CAULDRON,effetsWitchCauldron,effetsHuntCauldron,null,null);
		Carte evilEye = new Carte(CarteNom.EYE,effetsWitchEye,effetsHuntEye,null,null);
		Carte toad = new Carte(CarteNom.TOAD,effetsWitchToad,effetsHuntToad,null,null);	
		Carte blackCat = new Carte(CarteNom.BLACK,effetsWitchBlackCat,effetsHuntBlackCat,null,null);
		Carte petNewt = new Carte(CarteNom.PET,effetsWitchNewt,effetsHuntNewt,null,null);


		//Ajout des cartes au tas initial
		
		this.getTasInitial().add(angryMob);
		this.getTasInitial().add(theInquisition);
		this.getTasInitial().add(pointedHat);
		this.getTasInitial().add(hookedNose);
		this.getTasInitial().add(broomStick);
		this.getTasInitial().add(wart);
		this.getTasInitial().add(duckingStool);
		this.getTasInitial().add(cauldron);
		this.getTasInitial().add(evilEye);
		this.getTasInitial().add(toad);
		this.getTasInitial().add(blackCat);
		this.getTasInitial().add(petNewt);
		
	}
	
	/**
	 * Permet de m�langer les cartes du jeu de carte
	 */
	public void melangerCartes() {
		Collections.shuffle(tasInitial);
	}
	
	/**
	 * M�thode permettant d'initier un round : Mise � jour du num�ro de round, r�initialisation de l'�tat des joueurs,
	 * r�initialisation de la liste des joueurs en jeu, mise en place des roles et distribution des cartes
	 */
	public void initierRound() {
		//Initiation d'un round
		setNbRound(this.getNbRound()+1);
		
		System.out.println("> D�but du round n�"+this.getNbRound()+"\n");
		
		//R�initialisation de l'�tat des joueurs
		reinitialisationDesJoueurs();
		
		//Reinitialisation de la liste des joueurs en jeu et de la defausse
		this.getListeJoueursEnJeu().clear();
		this.getListeJoueursEnJeu().addAll(this.getListeJoueursInitial());
		
		this.getListeDefausse().clear();
		
		//Melange des joueurs et designation du premier joueur courant
		Collections.shuffle(this.getListeJoueursEnJeu());
		setJoueurCourant(this.getListeJoueursEnJeu().get(0));
		
		System.out.println("Joueur d�butant le round : "+getJoueurCourant().getNom()+"\n");
		
		//Choix des r�les
		choixDesRoles();
		
		System.out.println("** Distribution des cartes ** \n");

		//Distribution des cartes
		distribuerCartes();	
	}
	
	/**
	 * M�thode permettant de d�terminer le prochain joueur � jouer
	 */
	public void determinerProchainJoueur() {
		
		//D�termination du prochain joueur
		int indexJoueurCourant = this.getListeJoueursEnJeu().indexOf(this.getJoueurCourant());
		
		 if(indexJoueurCourant == this.getListeJoueursEnJeu().size()-1) {
			 this.setProchainJoueur(this.getListeJoueursEnJeu().get(0));
		 } else {
			 this.setProchainJoueur(this.getListeJoueursEnJeu().get(indexJoueurCourant+1));
		 }
		 
	}
	
	/**
	 * M�thode permettant de d�terminer si le prochain joueur a �t� determine
	 * @return true : prochain joueur a �t� d�termin� / false : le prochain joueur n'a pas ete determine
	 */
	public boolean isProchainJoueurDetermine() {
		return this.getListeJoueursEnJeu().contains(this.getProchainJoueur()) && this.getProchainJoueur() != null;
			
	}
	
	/**
	 * Methode permettant de jouer le round
	 */
	public void faireRound() {
		determinerProchainJoueur();
		
		//Joueur courant choisit son action � r�aliser durant le tour
		getJoueurCourant().joueTour();
		
		if(!isProchainJoueurDetermine()) {
			determinerProchainJoueur();
		}
		
		System.out.println("\nProchain joueur : "+this.getProchainJoueur().getNom());
		
		//Changement de joueur courant
		this.setJoueurCourant(this.getProchainJoueur());
		
		this.afficherPointsJoueurs();	
		
	}
	
	/**
	 * Methode permettant de verifier si un joueur a gagne ou non
	 * @return true : un joueur a gagne / false : personne n'a encore gagne
	 */
	public boolean verifJoueurAGagne() {
		boolean joueurAGagne = false;
		int indexJoueur = 0;
		
		//Parcours des joueurs pour determiner si un joueur a au moins 5 points
		while(!joueurAGagne && indexJoueur < this.getListeJoueursInitial().size()) {
			if(this.getListeJoueursInitial().get(indexJoueur).getNbPoints() >= 5) {
				joueurAGagne = true;
			}
			indexJoueur++;
		}

		return joueurAGagne;
	}
	
	/**
	 * Permet d'afficher lorsque la partie est terminee
	 */
	public void terminePartie() {
		//Terminaison de la partie
		
		Joueur gagnant = this.getGagnantPartie();
		
		System.out.println("*** La partie est termin�e ! ***");
		System.out.println("Le gagnant est : "+gagnant.getNom());
		
	}
	
	/**
	 * Methode permettant de r�cup�rer le joueur ayant gagn� la partie lorsqu'il existe
	 * @return Le joueur ayant gagn� la partie
	 */
	public Joueur getGagnantPartie() {
		
		int nbPointMaxTrouve = 0;
		int indexGagnant = 0;

		//D�termination du gagnant : le joueur ayant le plus grand nombre de point
		for(int i = 0; i < this.getListeJoueursInitial().size(); i++) {
			if(this.getListeJoueursInitial().get(i).getNbPoints() > nbPointMaxTrouve) {
				nbPointMaxTrouve = this.getListeJoueursInitial().get(i).getNbPoints();
				indexGagnant = i;
			}
		}
		
		return this.getListeJoueursInitial().get(indexGagnant);
		
	}
	
	/**
	 * Permet de verifier si un round est termine ou non
	 * @return true : le round est termine / false : le round n'est pas termine
	 */
	public boolean verifRoundTermine() {
		
		return (getListeJoueursNonReveles().size() == 1);
	}
	
	/**
	 * Permet de r�cup�rer la liste des joueurs n'ayant pas leur identit� r�v�l�e
	 * @return Liste des joueurs n'ayant pas leur identit� r�v�l�e
	 */
	public ArrayList<Joueur> getListeJoueursNonReveles() {
		
		ArrayList<Joueur> listeJoueursNonReveles = new ArrayList<Joueur>();
		
		for(Joueur j : this.getListeJoueursEnJeu()) {
			if(!j.isIdentiteRevelee()) {
				listeJoueursNonReveles.add(j);
			}
		}
		
		return listeJoueursNonReveles;
		
	}
	
	/**
	 * Permet de creer les joueurs en debut de partie
	 * @param nomsJoueurs Le nom des joueurs humains
	 * @param rolesJoueurs Les roles des joueurs 
	 */
	public void creationJoueurs(String[] nomsJoueurs, String[] rolesJoueurs) {
		
		for(int i = 0; i < this.getNbJoueursHumains(); i++) {
			
			String nom = nomsJoueurs[i];
			String role = rolesJoueurs[i];
			
			Humain h = new Humain(nom);
			this.addJoueurListeJoueurInitial(h);
			h.choisirRole(role);
			
		}
		
		for(int i = 0; i < this.getNbJoueursBots(); i++) {
			
			int numStretegie = (int) Math.round(Math.random()*3);
			IAStrategy strategieChoisie = strategies[numStretegie];
			Bot b = new Bot("BOT"+(i+1), strategieChoisie);
			
			this.addJoueurListeJoueurInitial(b);
			b.choisirRole();

		}
		
	}
	
	
	/**
	 * Permet de creer les joeurs en debut de partie
	 */
	public void creationJoueurs() {
		
		int nbJoueursHumain = 0;
		int nbJoueursBot = 0;
		
		System.out.println("** CREATION DES JOUEURS ** \n");
		
		System.out.println("> Choix du nombre de joueurs");
		
		while((nbJoueursHumain+nbJoueursBot) < 3 || nbJoueursHumain == 0 || (nbJoueursHumain+nbJoueursBot) > 6) {
			
			System.out.println("\nATTENTION : Le nombre total de joueur doit �tre au moins �gal � 3 et inf�rieur ou �gal � 6 \n");
			
			try {
				
				System.out.println("Veuillez entrer le nombre de joueurs humain : ");
				nbJoueursHumain = Utils.getIntInput();
				
				System.out.println("Veuillez entrer le nombre de joueurs bot : ");
				nbJoueursBot = Utils.getIntInput();
				
			} catch(InputMismatchException exception) {
				
				System.out.println("\nERREUR - Veuillez r�essayer \n");
				
				nbJoueursHumain = 0;
				nbJoueursBot = 0;
				
			}
			
		}
		
		System.out.println("\n> Nommage des joueurs \n");
		
		for(int i = 0; i < nbJoueursHumain; i++) {
			
			String nom = new String();
			System.out.println("Veuillez entrer le nom du joueur n�"+(Joueur.getNbJoueurs()+1)+" : ");
			
			nom = Utils.getStringInput();
			
			this.addJoueurListeJoueurInitial(new Humain(nom));
		}
		
		for(int i = 0; i < nbJoueursBot; i++) {
			int numStretegie = (int) Math.round(Math.random()*3);
			IAStrategy strategieChoisie = strategies[numStretegie];
		
			this.addJoueurListeJoueurInitial(new Bot("BOT"+(i+1),strategieChoisie));
			
		}
		
		System.out.println("\n Cr�ation des joueurs effectu�e ! \n");
		
	}
	
	/**
	 * Permet d'ajouter une carte a la defausse
	 * @param c La carte qu'on veut ajouter a la defausse
	 */
	public void addCarteDefausse(Carte c) {
		
		this.getListeDefausse().add(c);
		
	}
	
	/**
	 * Permet de retirer une carte de la defausse
	 * @param c La carte qu'on veut retirer de la defausse
	 */
	public void removeCarteDefausse(Carte c) {
		
		if(this.getListeDefausse().contains(c)) {
			this.getListeDefausse().remove(c);
		}
		
	}
	
	/**
	 * Consultation des cartes de chaque joueur
	 */
	public void afficheMainsJoueurs() {
		
		for(Joueur j : this.getListeJoueursEnJeu()) {
			j.consulterMain();
		}
		
	}
	
	/**
	 * Permet d'initier le choix du role de chaque joueur
	 */
	public void choixDesRoles() {
		
		for(Joueur j : this.getListeJoueursEnJeu()) {
			if(j instanceof Bot) {
				j.choisirRole();
			}
		}
		
	}
	
	/**
	 * Permet de terminer un round : Ajout des points et affichage des points
	 */
	public void termineRound() {
		
		System.out.println("\nLe gagnant de ce round est : "+this.getListeJoueursNonReveles().get(0).getNom());
		System.out.println("Son r�le �tait : "+this.getListeJoueursNonReveles().get(0).getRole().toString()+" ! ");
		
		int nbPointsAAjouter = 1;
		if(this.getListeJoueursNonReveles().get(0).getRole() == Role.WITCH) {
			nbPointsAAjouter = 2;
		}
		
		this.getListeJoueursNonReveles().get(0).addPoint(nbPointsAAjouter);
		
		this.afficherPointsJoueurs();
		
	}
	
	/**
	 * Reinitialiser l'�tat de chaque joueur
	 */
	public void reinitialisationDesJoueurs() {
		
		for(Joueur j : this.getListeJoueursInitial()) {
			j.setIdentiteRevelee(false,false);
			j.getCartesRevelees().clear();
			j.getMain().clear();
			j.setDerniereCarteJouee(null);
		}
		
	}
	
	/**
	 * Permet de recuperer la liste des joueurs qui peuvent etre accuses
	 * @return La liste des joueurs qui peuvent etre accuses
	 */
	public ArrayList<Joueur> getJoueursPouvantEtreAccuses() {
		
		ArrayList<Joueur> joueurs = new ArrayList<>();
		
		for(Joueur j : getJoueursPouvantEtreChoisis()) {
			if(!j.isIdentiteRevelee()) {
				joueurs.add(j);
			}
		}
		
		return joueurs;
		
	}
	
	/**
	 * Permet de recuperer la liste des joueurs qui peuvent etre choisis
	 * @return La liste de joueurs qui peuvent etre choisis
	 */
	public ArrayList<Joueur> getJoueursPouvantEtreChoisis() {
		
		ArrayList<Joueur> joueurs = new ArrayList<>();
		
		for(Joueur j : Partie.getInstance().getListeJoueursEnJeu()) {
			if(j.getId() != this.getJoueurCourant().getId()) {
				joueurs.add(j);
			}
		}
		
		return joueurs;
		
	}
	
	/**
	 * Permet d'obtenir le classement g�n�ral des joueurs par point, dans une Collection
	 * @return La liste des joueurs ordonn�s par nombre de point
	 */
	public ArrayList<Joueur> getClassementJoueurs() {
		
		ArrayList<Joueur> classement = new ArrayList<>();
		
		for(Joueur j : this.getListeJoueursInitial()) {
			classement.add(j);
		}
		
		Collections.sort(classement, new SortByPoints());
		
		return classement;
		
	}
	
	/**
	 * Permet d'afficher les points des joueurs
	 */
	public void afficherPointsJoueurs() {
		
		System.out.println("\n ** Affichage des points ** \n");
		
		for(Joueur j : this.getListeJoueursInitial()) {
			System.out.println("Le joueur "+j.getNom()+" a "+j.getNbPoints()+" points.");
		}
		
	}
}