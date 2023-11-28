package lo02_a21_projet_kemplaire_tan.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import lo02_a21_projet_kemplaire_tan.controller.ControllerGame;
import lo02_a21_projet_kemplaire_tan.modele.jeu.ChoixAction;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Humain;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Message;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Role;

import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Action;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.CarteNom;

/**
* Classe ViewGame
*/
/**
* 
* Classe permettant de gérer la vue de la fenêtre de jeu.
* Initialise tous les éléments présents sur la fenêtre de jeu.
* Gère l'affichage et la mise a jour des éléments. 
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.controller
* @see lo02_a21_projet_kemplaire_tan.modele.jeu
* @see lo02_a21_projet_kemplaire_tan.view
* 
*/
public class ViewGame implements Observer {

	private JFrame frame;
	private JLabel title;
	
	private JLabel labelListeJoueurs;
	private JLabel[] nomsListeJoueurs;
	
	private JLabel labelError;
	private JLabel labelDescriptionEffet;
	private JLabel choixActionLabel;
	private JLabel revelerSonIdentiteLabel;
	private JLabel mainLabel;
	
	private JTextArea descriptionActionsBot;
	private JTextArea classementFinal;
	
	private JLabel[] carteNom;
	private JLabel[] effetsWitchLabels;
	private JLabel[] effetsHuntLabels;
	
	private JButton joueTourBtn;
	private JButton validerActionBtn;
	private JButton validerEffetBtn;
	private JButton poursuivreBtn;
	private JButton prochainTourBtn;
	private JButton accuserBtn;
	private JButton choisirCarteBtn;
	private JButton valideActionBot;
	private JButton prochainRoundBtn;
	
	private JComboBox choixActionCombo;
	private JComboBox choixJoueurCombo;
	private JComboBox choixJoueurEffetCombo;
	private JComboBox choixActionEffetCombo;
	private JComboBox choixCarteCombo;
	
	/**La partie*/
	private Partie partie = Partie.getInstance();


	/**
	 * Création de l'application
	 */
	public ViewGame() {
		
		initialize();
		
		partie.addObserver(this);
		
		for(Joueur j : partie.getListeJoueursEnJeu()) {
			j.addObserver(this);
		}
		
		ControllerGame controller = new ControllerGame(this);
	}

	/**
	 * Initialise le contenu de la fenêtre
	 */
	private void initialize() {
		
		//Fenetre
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//message d'erreur 
		labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setBounds(22, 200, 600, 20);
		frame.getContentPane().add(labelError);
		
		descriptionActionsBot = new JTextArea();
		descriptionActionsBot.setText("");
		descriptionActionsBot.setBounds(22, 120, 600, 50);
		descriptionActionsBot.setEditable(false);
		frame.getContentPane().add(descriptionActionsBot);
		descriptionActionsBot.setVisible(false);
		
		classementFinal = new JTextArea();
		classementFinal.setText("");
		classementFinal.setBounds(22, 150, 600, 100);
		classementFinal.setEditable(false);
		frame.getContentPane().add(classementFinal);
		classementFinal.setVisible(false);
		
		labelDescriptionEffet = new JLabel("");
		labelDescriptionEffet.setForeground(Color.BLUE);
		labelDescriptionEffet.setBounds(22, 215, 1500, 20);
		frame.getContentPane().add(labelDescriptionEffet);
		
		//Titre
		title = new JLabel("ROUND N°"+ partie.getNbRound() + " | Au tour de " + partie.getJoueurCourant().getNom()+" | Prochain joueur : N.D");
		title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		title.setBounds(22, 26, 1000, 27);
		frame.getContentPane().add(title);
				
		//Initialisation liste des joueurs
		initialiseListeJoueursEnJeu();
		
		//Label choix action
		choixActionLabel = new JLabel("Que souhaitez vous faire ?");
		choixActionLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
		choixActionLabel.setBounds(22, 110, 1000, 20);
		frame.getContentPane().add(choixActionLabel);
		
		//Label révéler son identité
		revelerSonIdentiteLabel = new JLabel("");
		revelerSonIdentiteLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		revelerSonIdentiteLabel.setBounds(22, 140, 410, 25);
		frame.getContentPane().add(revelerSonIdentiteLabel);
		
		//Combobox choix action
		choixActionCombo = new JComboBox();
		choixActionCombo.setBounds(22, 145, 269, 22);
		frame.getContentPane().add(choixActionCombo);
		
		choixCarteCombo = new JComboBox();
		choixCarteCombo.setBounds(22, 145, 269, 22);
		frame.getContentPane().add(choixCarteCombo);
		choixCarteCombo.setVisible(false);
		
		choixJoueurCombo = new JComboBox();
		choixJoueurCombo.setBounds(22, 145, 269, 22);
		frame.getContentPane().add(choixJoueurCombo);
		choixJoueurCombo.setVisible(false);
		
		choixJoueurEffetCombo = new JComboBox();
		choixJoueurEffetCombo.setBounds(22, 145, 269, 22);
		frame.getContentPane().add(choixJoueurEffetCombo);
		choixJoueurEffetCombo.setVisible(false);
		
		choixActionEffetCombo = new JComboBox();
		choixActionEffetCombo.setBounds(22, 145, 269, 22);
		frame.getContentPane().add(choixActionEffetCombo);
		choixActionEffetCombo.setVisible(false);
		
		//Initialisation de la combobox des joueurs pouvant être accusés
		initialiseComboJoueursPouvantEtreAccuses();
		
		//Initialisation de la main du joueur
		intialiseMainJoueur();
			
		//Bouton pour lancer 1 tour
		joueTourBtn = new JButton("VALIDER !");
		joueTourBtn.setBounds(367, 529, 230, 23);
		frame.getContentPane().add(joueTourBtn);
		
		validerEffetBtn = new JButton("VALIDER L'EFFET");
		validerEffetBtn.setBounds(367,529,230,23);
		frame.getContentPane().add(validerEffetBtn);
		validerEffetBtn.setVisible(false);
		
		prochainTourBtn = new JButton("PROCHAIN TOUR");
		prochainTourBtn.setBounds(367,529,230,23);
		frame.getContentPane().add(prochainTourBtn);
		prochainTourBtn.setVisible(false);
				
		//Valider une action lors d'un choix d'action
		validerActionBtn = new JButton("VALIDER L'ACTION !");
		validerActionBtn.setBounds(367, 529, 230, 23);
		frame.getContentPane().add(validerActionBtn);
		validerActionBtn.setVisible(false);
		
		//Bouton pour valider l'action "accuser"
		accuserBtn = new JButton("ACCUSER !");
		accuserBtn.setBounds(367, 529, 230, 23);
		frame.getContentPane().add(accuserBtn);
		accuserBtn.setVisible(false);
		
		//Bouton pour valider la carte choisie
		choisirCarteBtn = new JButton("CHOISIR CETTE CARTE !");
		choisirCarteBtn.setBounds(367, 529, 230, 23);
		frame.getContentPane().add(choisirCarteBtn);
		choisirCarteBtn.setVisible(false);
		
		poursuivreBtn = new JButton("POURSUIVRE");
		poursuivreBtn.setBounds(367, 529, 230, 23);
		frame.getContentPane().add(poursuivreBtn);
		poursuivreBtn.setVisible(false);
		
		valideActionBot = new JButton("VALIDE ACTION DU BOT");
		valideActionBot.setBounds(367, 529, 230, 23);
		frame.getContentPane().add(valideActionBot);
		valideActionBot.setVisible(false);
		
		prochainRoundBtn = new JButton("PASSER AU PROCHAIN ROUND");
		prochainRoundBtn.setBounds(367, 529, 230, 23);
		frame.getContentPane().add(prochainRoundBtn);
		prochainRoundBtn.setVisible(false);
		
	}
	
	public JLabel getTitle() {
		return title;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JLabel getLabelListeJoueurs() {
		return labelListeJoueurs;
	}
	
	public JButton getJoueTourBtn() {
		return joueTourBtn;
	}
	
	public JButton getvaliderActionBtn() {
		return validerActionBtn;
	}
	
	public JButton getAccuserBouton() {
		return accuserBtn;
	}
	
	public JButton getChoisirCarteBtn () {
		return choisirCarteBtn;
	}
	
	public JButton getProchainRoundBtn() {
		return prochainRoundBtn;
	}
	
	public JComboBox getChoixActionCombo() {
		return choixActionCombo;
	}
	
	public JLabel[] getEffetsWitchLabels() {
		return effetsWitchLabels;
	}
	
	public JLabel[] getEffetsHuntLabels() {
		return effetsHuntLabels;
	}
	
	public JComboBox getChoixJoueurCombo() {
		return choixJoueurCombo;
	}
	
	public JComboBox getChoixCarteCombo() {
		return choixCarteCombo;
	}
	
	public JComboBox getChoixJoueurEffetCombo() {
		return choixJoueurEffetCombo;
	}
	
	public JComboBox getChoixActionEffetCombo() {
		return choixActionEffetCombo;
	}
	
	public JLabel getMainLabel() {
		return mainLabel;
	}
	
	public JButton getProchainTourBtn() {
		return prochainTourBtn;
	}
	
	public JLabel[] getCarteNom() {
		return carteNom;
	}
	
	public JLabel getChoixActionLabel () {
		return choixActionLabel;
	}
	
	public JLabel getRevelerSonIdentiteLabel () {
		return revelerSonIdentiteLabel;
	}
	
	public JButton getvaliderEffetBtn() {
		return validerEffetBtn;
	}
	
	public JLabel getLabelError() {
		return labelError;
	}
	
	public JLabel getLabelDescriptionEffet() {
		return labelDescriptionEffet;
	}
	
	public JButton getPoursuivreBtn() {
		return poursuivreBtn;
	}
	
	public JButton getValideActionBot() {
		return valideActionBot;
	}
	
	public JTextArea getDescriptionActionsBot() {
		return descriptionActionsBot;
	}
	
	public JTextArea getClassementFinal() {
		return classementFinal;
	}
	
	/**
	 * Initialise le combobox de joueursPouvantEtreAccuses
	 */
	public void initialiseComboJoueursPouvantEtreAccuses() {
		
		//Combobox des joueurs pouvant être accusés
		choixJoueurCombo = new JComboBox();

		String[] listeJoueurs = new String[partie.getJoueursPouvantEtreAccuses().size()];
		
		for(int i = 0; i < listeJoueurs.length; i++) {
			listeJoueurs[i] = partie.getJoueursPouvantEtreAccuses().get(i).getNom();
		}
		
		choixJoueurCombo.setModel(new DefaultComboBoxModel(listeJoueurs));
		choixJoueurCombo.setBounds(22, 145, 269, 22);
		choixJoueurCombo.setVisible(false);
		
		frame.getContentPane().add(choixJoueurCombo);
				
	}
	
	/**
	 * Initialise le comboBox de choixAction
	 */
	public void initialiseChoixAction() {
		
		Action[] listeActionsPossibles = ((Humain)partie.getJoueurCourant()).getActionsPossibles(ChoixAction.ACCUSE_OU_RUMEUR);
		choixActionCombo.setModel(new DefaultComboBoxModel(listeActionsPossibles));
		
	}
	
	/**
	 * Permet de cacher tous les JComboBox de la fenetre
	 */
	public void cacherTousLesCombos() {
		
		choixActionCombo.setVisible(false);
		choixJoueurCombo.setVisible(false);
		choixCarteCombo.setVisible(false);
		choixJoueurEffetCombo.setVisible(false);
		choixActionEffetCombo.setVisible(false);
		
	}
	
	/**
	 * Permet de cacher tous les JButton de la fenetre
	 */
	public void cacherTousLesBoutons() {
		
		joueTourBtn.setVisible(false);
		validerActionBtn.setVisible(false);
		validerEffetBtn.setVisible(false);
		poursuivreBtn.setVisible(false);
		prochainTourBtn.setVisible(false);
		accuserBtn.setVisible(false);
		choisirCarteBtn.setVisible(false);
		valideActionBot.setVisible(false);
		prochainRoundBtn.setVisible(false);
		
	}
	
	/**
	 * Permet de cacher tous les JLabel de la fenetre
	 */
	public void cacherTousLesLabels() {
		
		labelError.setVisible(false);
		labelDescriptionEffet.setVisible(false);
		choixActionLabel.setVisible(false);
		revelerSonIdentiteLabel.setVisible(false);
		mainLabel.setVisible(false);
		descriptionActionsBot.setVisible(false);
		
	}
	
	/**
	 * Affiche la main du premier joueur
	 */
	public void intialiseMainJoueur() {
		
		//Affichage de la main du joueur
		mainLabel =	new JLabel("Votre main");
		mainLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
		mainLabel.setBounds(22, 190, 77, 14);
		frame.getContentPane().add(mainLabel);
		
		//Afficher les cartes de la main du joueur courant 
		carteNom = new JLabel[partie.getJoueurCourant().getMain().size()];
		effetsWitchLabels = new JLabel[partie.getJoueurCourant().getMain().size()];
		effetsHuntLabels = new JLabel[partie.getJoueurCourant().getMain().size()];
		
		for(int i = 0; i < partie.getJoueurCourant().getMain().size(); i++) {
			
			//On récupère les descriptions des effets witch de la carte
			ArrayList<Effet> effetsWitch = partie.getJoueurCourant().getMain().get(i).getListeEffetsWitch();
			String[] descriptionsWitch = new String[effetsWitch.size()];
			for(int j = 0; j < effetsWitch.size(); j++ ) {
				descriptionsWitch[j] = effetsWitch.get(j).getDescription();
			}
			
			//On récupère les descriptions des effets hunt de la carte
			ArrayList<Effet> effetsHunt = partie.getJoueurCourant().getMain().get(i).getListeEffetsHunt();
			
			String[] descriptionsHunt = new String[effetsHunt.size()];
			for(int j = 0; j < effetsHunt.size(); j++ ) {
				descriptionsHunt[j] = effetsHunt.get(j).getDescription();
			}
			
			String descriptionWitchString = Arrays.toString(descriptionsWitch);
			String descriptionHuntString = Arrays.toString(descriptionsHunt);
			
			if(partie.getJoueurCourant().getMain().get(i).getConditionWitch() != null) {
				descriptionWitchString += " - "+partie.getJoueurCourant().getMain().get(i).getConditionWitch().getDescription();
			}
			
			if(partie.getJoueurCourant().getMain().get(i).getConditionHunt() != null) {
				descriptionHuntString += " - "+partie.getJoueurCourant().getMain().get(i).getConditionHunt().getDescription();
			}
			
			//Affichage du nom de la carte et des effets witch et hunt
			carteNom[i] = new JLabel("- Carte "+partie.getJoueurCourant().getMain().get(i).getNom());
			effetsWitchLabels[i] = new JLabel("Effet witch : "+Arrays.toString(descriptionsWitch));
			effetsHuntLabels[i] = new JLabel("Effet hunt : "+Arrays.toString(descriptionsHunt));
			
			carteNom[i].setBounds(22, 225 + i*70, 904, 14);
			effetsWitchLabels[i].setBounds(22, 240 + i*70, 904, 14);
			effetsHuntLabels[i].setBounds(22, 255 + i*70, 904, 14);
			
			//" : \nWITCH : " + Arrays.toString(descriptionsWitch) + " \nHUNT : " + Arrays.toString(descriptionsHunt)
			
			frame.getContentPane().add(carteNom[i]);
			frame.getContentPane().add(effetsWitchLabels[i]);
			frame.getContentPane().add(effetsHuntLabels[i]);
			
		}
	}
	
	/**
	 * Initialise la liste des joueurs en jeu en haut de la fenêtre
	 */
	public void initialiseListeJoueursEnJeu() {
		
		//Affichage de la liste des joueurs
		labelListeJoueurs = new JLabel("Liste des joueurs :");
		labelListeJoueurs.setFont(new Font("Segoe UI", Font.BOLD, 15));
		labelListeJoueurs.setBounds(22, 64, 262, 21);
		frame.getContentPane().add(labelListeJoueurs);
		
		//Affichage de la liste des joueurs contenus dans la liste des joueurs en jeu
		nomsListeJoueurs = new JLabel[partie.getListeJoueursEnJeu().size()];
		ArrayList<Joueur> listeJoueursEnJeu = partie.getListeJoueursEnJeu();
		
		for(int i = 0; i < listeJoueursEnJeu.size(); i++) {
			
			String role = "???";
			
			if(listeJoueursEnJeu.get(i).isIdentiteRevelee()) {
				role = listeJoueursEnJeu.get(i).getRole().toString();
			}
			
			nomsListeJoueurs[i] = new JLabel(listeJoueursEnJeu.get(i).getNom() + " | " + listeJoueursEnJeu.get(i).getNbPoints() + " pts | "+role);
			
			nomsListeJoueurs[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
			nomsListeJoueurs[i].setBounds(160 + i*160, 64, 262, 21);
			frame.getContentPane().add(nomsListeJoueurs[i]);
		}
		
	}
	
	/**
	 * Cacher a liste des joueurs 
	 */
	public void cacherListeJoueurs() {
		
		labelListeJoueurs.setVisible(false);
		
		for(int i = 0; i < nomsListeJoueurs.length; i++) {
			nomsListeJoueurs[i].setVisible(false);
		}
	}
	
	/**
	 * Rafraisir la liste des joueurs en haut de la fenêtre
	 */
	public void raffraichissementListeJoueurs() {
		
		//Raffraichissement liste joueurs en jeu
		for(int i = 0; i < nomsListeJoueurs.length; i++) {
			nomsListeJoueurs[i].setText("");
		}
		
		ArrayList<Joueur> listeJoueursEnJeu = partie.getListeJoueursEnJeu();
		for(int i = 0; i < listeJoueursEnJeu.size(); i++) {
			
			String role = "???";
			
			if(listeJoueursEnJeu.get(i).isIdentiteRevelee()) {
				role = listeJoueursEnJeu.get(i).getRole().toString();
			}
			
			nomsListeJoueurs[i].setText(listeJoueursEnJeu.get(i).getNom() + " | " + listeJoueursEnJeu.get(i).getNbPoints() + " pts | "+role);
		}
		
	}
	
	/**
	 * Mise à jour de l'affichage de la main selon le joueur
	 * @param joueur - Le joueur dont on veut afficher la main
	 */
	public void updateAffichageMain(Joueur joueur) {

		//Raffraichissement main du joueur courant
		for(int i = 0; i < getCarteNom().length; i++) {
			getCarteNom()[i].setText("");
		}

		for(int i = 0; i < getEffetsWitchLabels().length; i++) {
			getEffetsWitchLabels()[i].setText("");
		}

		for(int i = 0; i < getEffetsHuntLabels().length; i++) {
			getEffetsHuntLabels()[i].setText("");
		}

		for(int i = 0; i < joueur.getMain().size(); i++) {

			//On récupère les descriptions des effets witch de la carte
			ArrayList<Effet> effetsWitch = joueur.getMain().get(i).getListeEffetsWitch();
			String[] descriptionsWitch = new String[effetsWitch.size()];
			for(int j = 0; j < effetsWitch.size(); j++ ) {
				descriptionsWitch[j] = effetsWitch.get(j).getDescription();
			}
			

			//On récupère les descriptions des effets hunt de la carte
			ArrayList<Effet> effetsHunt = joueur.getMain().get(i).getListeEffetsHunt();

			String[] descriptionsHunt = new String[effetsHunt.size()];
			for(int j = 0; j < effetsHunt.size(); j++ ) {
				descriptionsHunt[j] = effetsHunt.get(j).getDescription();
			}
			
			String descriptionWitchString = Arrays.toString(descriptionsWitch);
			String descriptionHuntString = Arrays.toString(descriptionsHunt);
			
			if(joueur.getMain().get(i).getConditionWitch() != null) {
				descriptionWitchString += " - "+joueur.getMain().get(i).getConditionWitch().getDescription();
			}
			
			if(joueur.getMain().get(i).getConditionHunt() != null) {
				descriptionHuntString += " - "+joueur.getMain().get(i).getConditionHunt().getDescription();
			}

			//Affichage du nom de la carte et des effets witch et hunt
			getCarteNom()[i].setText("- Carte "+joueur.getMain().get(i).getNom());
			getEffetsWitchLabels()[i].setText("Effet witch : "+descriptionWitchString);
			getEffetsHuntLabels()[i].setText("Effet hunt : "+descriptionHuntString);

		}

	}
	
	/**
	 * Cacher ou afficher la main du joueur
	 * @param affiche - true : afficher | false : cacher
	 */
	public void affichageMain(boolean affiche) {

		getMainLabel().setVisible(affiche);

		setVisibleListeLabel(getCarteNom(), affiche);
		setVisibleListeLabel(getEffetsWitchLabels(), affiche);
		setVisibleListeLabel(getEffetsHuntLabels(), affiche);

	}
	
	/**
	 * Cacher ou afficher une liste de labels
	 * @param jLabel - la liste des labels à cacher ou afficher
	 * @param isVisible - true : afficher | false : cacher
	 */
	public void setVisibleListeLabel(JLabel[] jLabel, boolean isVisible) {
		for(int i = 0; i < jLabel.length; i++) {
			jLabel[i].setVisible(isVisible);
		}
	}

	/**
	 * Affichage de la fenêtre lorsque la partie est terminée
	 */
	public void affichageFinPartie() {
		
		cacherBoutonsCombosLabels();
		
		cacherListeJoueurs();
		
		getChoixActionLabel().setVisible(true);
		getChoixActionLabel().setText("Classement final : ");
		
		getClassementFinal().setVisible(true);
		String classementText = "";
		
		int num = 1;
		for(Joueur j : partie.getClassementJoueurs()) {
			classementText += num+". "+j.getNom()+" - "+j.getNbPoints()+"pts \n"; 
			num++;
		}
		
		getClassementFinal().setText(classementText);
		
		getTitle().setText("BRAVO ! PARTIE TERMINE - "+partie.getGagnantPartie().getNom()+" a gagné avec "+partie.getGagnantPartie().getNbPoints()+" pts");
		getTitle().setVisible(true);
		
	}
	
	/**
	 * Affichage de la fenêtre lorsqu'un round est terminé
	 */
	public void affichageFinRound() {
		
		cacherBoutonsCombosLabels();
		
		getTitle().setText("ROUND TERMINE ! "+partie.getListeJoueursNonReveles().get(0).getNom()+" gagne le round. Il était "+partie.getListeJoueursNonReveles().get(0).getRole().toString());
		getTitle().setVisible(true);
		
		getChoixActionLabel().setVisible(true);
		getChoixActionLabel().setText("Pour passer au prochain round, cliquez sur le bouton 'Prochain round'");
		
		getProchainRoundBtn().setVisible(true);
		
	}
	
	/**
	 * Affichage accuser un joueur de l'effet spécial "choisir un joueur qui devra accuser quelqu'un lors de son tour"
	 * @param special - l'effet est-il lancé ? 
	 */
	public void affichageAccuseJoueur(boolean special) {

		cacherBoutonsCombosLabels();

		getChoixActionLabel().setVisible(true);
		
		if(!special) {
			getChoixActionLabel().setText("Choisissez un joueur à accuser !");
		}
		
		getChoixJoueurCombo().setVisible(true);
		
		if(special) {
			
			ArrayList<Joueur> listeJoueurs;

            listeJoueurs = new ArrayList<Joueur>();
            for(Joueur j : partie.getJoueursPouvantEtreAccuses()) {
                if(j.getId() != partie.getJoueurPrecedent().getId()) {
                    listeJoueurs.add(j);
                }
            }
            
            if (listeJoueurs.size() > 0) {
            	getChoixJoueurCombo().setModel(new DefaultComboBoxModel(getNomJoueurs(listeJoueurs)));
            } else {
            	getChoixJoueurCombo().setModel(new DefaultComboBoxModel(getNomJoueurs(partie.getJoueursPouvantEtreAccuses())));
            }
			
		} else {
			getChoixJoueurCombo().setModel(new DefaultComboBoxModel(getNomJoueurs(partie.getJoueursPouvantEtreAccuses())));
		}

		affichageMain(false);
		
		getAccuserBouton().setVisible(true);

	}
	
	/**
	 * Récupère les noms des joueurs parmis une liste de joueurs
	 * @param joueurs - La liste des joueurs dont on veut les noms
	 * @return Un tableau de noms au format String
	 */
	public String[] getNomJoueurs(ArrayList<Joueur> joueurs) {

		String[] listeJoueurs = new String[joueurs.size()];
		for(int i = 0; i < joueurs.size(); i++) {
			listeJoueurs[i] = joueurs.get(i).getNom();
		}

		return listeJoueurs;

	}
	
	/**
	 * Affichage révéler son identité
	 * @param joueurAccuse - le joueur accusé
	 */
	public void affichageRevelerSonIdentite(Joueur joueurAccuse) {

		int nbPoints = 0;

		if(joueurAccuse.getRole() == Role.WITCH) {
			nbPoints = 1;
		}
		
		cacherBoutonsCombosLabels();
		
		affichageMain(false);

		joueurAccuse.reveleIdentite(partie.getJoueurCourant());

		getRevelerSonIdentiteLabel().setVisible(true);
		getRevelerSonIdentiteLabel().setText("Le joueur " + partie.getJoueurCourant().getNom() + " gagne " + nbPoints + " points !");

	}
	
	/**
	 * Affichage lors d'une action "se défendre"
	 * @param joueurAccuse - Le joueur accusé 
	 */
	public void affichageSeDefendre (Joueur joueurAccuse) {

		cacherBoutonsCombosLabels();
		
		updateAffichageMain(joueurAccuse);
		affichageMain(true);
	
		getChoixActionLabel().setVisible(true);
		getChoixActionLabel().setText("Attention "+joueurAccuse.getNom()+" ! "+partie.getJoueurCourant().getNom()+" vous accuse ! Que voulez-vous faire ?");

		//Affichage et modification de la combobox choix action
		getChoixActionCombo().setVisible(true);
		getChoixActionCombo().setModel(new DefaultComboBoxModel(((Humain)joueurAccuse).getActionsPossibles(ChoixAction.IDENTITE_OU_RUMEUR)));

		//affichage du bouton pour choixAction
		getvaliderActionBtn().setVisible(true);

	}
	
	/**
	 * Permet de cacher tous les boutons, les combos et les labels
	 */
	public void cacherBoutonsCombosLabels() {
		cacherTousLesBoutons();
		cacherTousLesCombos();
		cacherTousLesLabels();
	}
	
	/**
     * Affichage d'une nouvelle fenêtre pour choisir les rôles lors d'un nouveau round
     */
    public void affichageInitierNouveauRound() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewInitialiser window = new ViewInitialiser();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
    /**
     * update les éléments fixes de la vue
     */
	public void update (Observable o, Object arg) {
		
		if((Message) arg == Message.SET_NB_ROUND || (Message) arg == Message.SET_JOUEUR_COURANT || (Message) arg == Message.PROCHAIN_JOUEUR) {
			
			//Rafraichissement n° round / joueur courant
			title.setText("ROUND N°"+ partie.getNbRound() +" | Au tour de " + partie.getJoueurCourant().getNom()+" | Prochain joueur : "+partie.getProchainJoueur().getNom());
			
		} else if((Message) arg == Message.CHGT_JOUEURS_EN_JEU || (Message) arg == Message.CHGT_NB_POINTS) {
			
			raffraichissementListeJoueurs();
			
		} else if((Message) arg == Message.REVELE_IDENTITE) {
			
			getChoixActionLabel().setVisible(true);
			getChoixActionLabel().setText(((Joueur)o).getNom() + " révèle son identité ! Il est : " + ((Joueur)o).getRole());
			
			raffraichissementListeJoueurs();
			
		} else if((Message) arg == Message.ERROR) {
			
			labelError.setVisible(true);
			labelError.setText(partie.getError());
			
		} else if((Message) arg == Message.EXECUTION_EFFET) {
			
			labelDescriptionEffet.setVisible(true);
			labelDescriptionEffet.setText(partie.getExecutionEffet());
			
		}
		
	}
}
