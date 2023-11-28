package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.ArrayList;

/**
* Classe Bot
*/
/**
* 
* Classe représentant un joueur contrôlé par l'ordinateur, qui effectue automatiquement ses propres choix d'actions en fonction d'une stratégie définie.
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
     * @param strategy La stratégie attribuée au Bot
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
     * Méthode permettant au Bot de choisir un Role de manière automatique, à partir de sa stratégie
     * 
     */
	public void choisirRole() {
		
		this.setRole(strategy.algoChoisirRole());
		
	}
	
    /**
     * Méthode permettant au Bot de choisir un prochain joueur de manière automatique, à partir de sa stratégie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant être choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirProchainJoueur(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirProchainJoueur(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * Méthode permettant au Bot de choisir une carte à défausser de manière automatique, à partir de sa stratégie
     * @param cartesPouvantEtreDefaussees : liste des cartes pouvant être défaussées
     * @return La carte choisie
     * 
     */
	public Carte choisirCarteADefausser(ArrayList<Carte> cartesPouvantEtreDefaussees) {
		
		return strategy.algoChoisirCarteADefausser(cartesPouvantEtreDefaussees);
		
	}
	
    /**
     * Méthode permettant au Bot de choisir une carte à récupérer de manière automatique, à partir de sa stratégie
     * @param cartePouvantEtreRecuperees : liste des cartes pouvant être récupérées
     * @return La carte choisie
     * 
     */
	public Carte choisirCarteAPrendre(ArrayList<Carte> cartePouvantEtreRecuperees) {
		
		return strategy.algoChoisirCarteAPrendre(cartePouvantEtreRecuperees);
		
	}
	
    /**
     * Méthode permettant au Bot de choisir un joueur à qui prendre une carte de manière automatique, à partir de sa stratégie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant être choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirJoueurPrendreCarte(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirJoueurPrendreCarte(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * Méthode permettant au Bot de choisir un joueur à révéler de manière automatique, à partir de sa stratégie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant être choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirJoueurAReveler(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirJoueurAReveler(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * Méthode permettant au Bot de choisir un joueur qui sera la cible de l'effet executé de manière automatique, à partir de sa stratégie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant être choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirJoueurCibleEffet(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirJoueurCibleEffet(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * Méthode permettant au Bot de choisir une carte à révéler de manière automatique, à partir de sa stratégie
     * @param cartesPouvantEtreRevelees : liste des cartes pouvant être révélées
     * @return La carte choisie
     * 
     */
	public Carte choisirCarteAReveler(ArrayList<Carte> cartesPouvantEtreRevelees) {
		
		return strategy.algoChoisirCarteAReveler(cartesPouvantEtreRevelees);
		
	}
	
    /**
     * Méthode permettant au Bot de choisir un joueur à accuser de manière automatique, à partir de sa stratégie
     * @param listeJoueursPouvantEtreChoisis : liste des joueurs pouvant être choisis
     * @return Le joueur choisi
     * 
     */
	public Joueur choisirJoueurAAccuser(ArrayList<Joueur> listeJoueursPouvantEtreChoisis) {
		
		return strategy.algoChoisirJoueurAAccuser(listeJoueursPouvantEtreChoisis);
		
	}
	
    /**
     * Méthode permettant au Bot de choisir une action entre plusieurs lorsqu'il a un choix d'action à réaliser, lorsqu'il a le choix (sinon
     * l'action à réaliser est déterminée d'avance).
     * @param choixAction : Association de 2 actions représentant un choix à faire entre ces 2 actions
     * @return L'action choisie
     * 
     */
	public Action choisirAction(ChoixAction choixAction) {
		
		Action actionChoisie = null;
		
		if(choixAction == ChoixAction.ACCUSE_OU_RUMEUR) {
			
			if(this.getCartesPouvantEtreRevelees(Role.HUNT).size() > 0) {
				
				actionChoisie = strategy.algoChoisirAccuseOuReveleCarte(this);
			
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de révéler une carte ! (Plus de cartes ou cartes non jouables en l'état)");
				actionChoisie = Action.ACCUSE_JOUEUR;
				
			}
		} else if (choixAction == ChoixAction.DEFAUSSE_OU_IDENTITE) {
			
			if(this.getMain().size() > 0) {
				
				actionChoisie = strategy.algoChoisirDefausseOuReveleIdentite(this);
				
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de défausser une carte !");
				actionChoisie = Action.REVELE_IDENTITE;
				
			}
			
		} else {
			
			if(this.getCartesPouvantEtreRevelees(Role.WITCH).size() > 0) {
			
				actionChoisie = strategy.algoChoisirReveleIdentiteOuReveleCarte(this);
			
			} else {
				
				System.out.println("Il est impossible pour "+this.getNom()+" de révéler une carte ! (Plus de cartes ou cartes non jouables en l'état)");
				actionChoisie = Action.REVELE_IDENTITE;
				
			}
		}
		
		return actionChoisie;
		
	}
	
}
