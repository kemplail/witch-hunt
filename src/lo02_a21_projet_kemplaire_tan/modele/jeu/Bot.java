package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe Bot
*/
/**
* 
* Classe repr�sentant un joueur contr�l� par l'ordinateur, qui effectue automatiquement ses propres choix d'actions en fonction d'une strat�gie d�finie.
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.IAStrategy
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Carte
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.ChoixAction
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Action
* 
*/
public class Bot extends Joueur {

	private IAStrategy strategy;
	
    /**
     * Constructeur de la classe Bot, permet de lui attribuer un nom et une {@link IAStrategy}
     * 
     * @param nom Le nom du bot
     * @param strategy La strat�gie attribu�e au Bot
     * 
     */
	public Bot(String nom, IAStrategy strategy) {
		super(nom);
		this.setStrategy(strategy);
	}
	
	public IAStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(IAStrategy strategy) {
		this.strategy = strategy;
	}

	
    /**
     * M�thode permettant au Bot de choisir un Role de mani�re automatique, � partir de sa strat�gie
     * 
     */
	public void choisirRole() {
		
		this.setRole(strategy.algoChoisirRole());
		
	}
	
    /**
     * M�thode permettant au Bot de choisir un prochain joueur de mani�re automatique, � partir de sa strat�gie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant �tre choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirProchainJoueur(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirProchainJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * M�thode permettant au Bot de choisir une carte � d�fausser de mani�re automatique, � partir de sa strat�gie
     * @param cartesPouvantEtreDefaussees : liste des cartes pouvant �tre d�fauss�es
     * @return La carte choisie
     * 
     */
	public Carte choisirCarteADefausser(ArrayList<Carte> cartesPouvantEtreDefaussees) {
		
		return strategy.algoChoisirCarteADefausser(cartesPouvantEtreDefaussees);
		
	}
	
    /**
     * M�thode permettant au Bot de choisir une carte � r�cup�rer de mani�re automatique, � partir de sa strat�gie
     * @param cartePouvantEtreRecuperees : liste des cartes pouvant �tre r�cup�r�es
     * @return La carte choisie
     * 
     */
	public Carte choisirCarteAPrendre(ArrayList<Carte> cartePouvantEtreRecuperees) {
		
		return strategy.algoChoisirCarteAPrendre(cartePouvantEtreRecuperees);
		
	}
	
    /**
     * M�thode permettant au Bot de choisir un joueur � qui prendre une carte de mani�re automatique, � partir de sa strat�gie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant �tre choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirJoueurPrendreCarte(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirJoueurPrendreCarte(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * M�thode permettant au Bot de choisir un joueur � r�v�ler de mani�re automatique, � partir de sa strat�gie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant �tre choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirJoueurAReveler(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirJoueurAReveler(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * M�thode permettant au Bot de choisir un joueur qui sera la cible de l'effet execut� de mani�re automatique, � partir de sa strat�gie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant �tre choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirJoueurCibleEffet(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirJoueurCibleEffet(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * M�thode permettant au Bot de choisir une carte � r�v�ler de mani�re automatique, � partir de sa strat�gie
     * @param cartesPouvantEtreRevelees : liste des cartes pouvant �tre r�v�l�es
     * @return La carte choisie
     * 
     */
	public Carte choisirCarteAReveler(ArrayList<Carte> cartesPouvantEtreRevelees) {
		
		return strategy.algoChoisirCarteAReveler(cartesPouvantEtreRevelees);
		
	}
	
    /**
     * M�thode permettant au Bot de choisir un joueur � accuser de mani�re automatique, � partir de sa strat�gie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant �tre choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirJoueurAAccuser(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirJoueurAAccuser(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * M�thode permettant au Bot de choisir une action entre plusieurs lorsqu'il a un choix d'action � r�aliser, lorsqu'il a le choix (sinon
     * l'action � r�aliser est d�termin�e d'avance).
     * @param choixAction : Association de 2 actions repr�sentant un choix � faire entre ces 2 actions
     * @return L'action choisie
     * 
     */
	public Action choisirAction(ChoixAction choixAction) {
		
		Action actionChoisie = null;
		
		if(choixAction == ChoixAction.ACCUSE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.HUNT).size() > 0) {
				
				actionChoisie = strategy.algoChoisirAccuseOuReveleCarte(this);
			
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de r�v�ler une carte ! (Plus de cartes ou cartes non jouables en l'�tat)");
				actionChoisie = Action.ACCUSE_JOUEUR;
				
			}
		} else if (choixAction == ChoixAction.DEFAUSSE_OU_IDENTITE) {
			
			if(this.getMain().size() > 0) {
				
				actionChoisie = strategy.algoChoisirDefausseOuReveleIdentite(this);
				
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de d�fausser une carte !");
				actionChoisie = Action.REVELE_IDENTITE;
				
			}
			
		} else {
			
			if(this.getCartesPouvantEtreRevelees(Role.WITCH).size() > 0) {
			
				actionChoisie = strategy.algoChoisirReveleIdentiteOuReveleCarte(this);
			
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de r�v�ler une carte ! (Plus de cartes ou cartes non jouables en l'�tat)");
				actionChoisie = Action.REVELE_IDENTITE;
				
			}
		}
		
		return actionChoisie;
		
	}
	
}
