package lo02_a21_projet_kemplaire_tan.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import lo02_a21_projet_kemplaire_tan.controller.ControllerInitialiser;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Humain;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Message;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;

import javax.swing.SpinnerListModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
* Classe ViewInitialiser
*/
/**
* 
* Classe d'afficher la vue de l'initialisation d'une partie ou de l'initialisation d'un nouveau round.
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.controller
* @see lo02_a21_projet_kemplaire_tan.modele.jeu
* @see lo02_a21_projet_kemplaire_tan.view
* 
*/
public class ViewInitialiser implements Observer {

	private JFrame frame;
	private JSpinner nbBots;
	private JSpinner nbHumains;
	private JSpinner[] rolesJoueurs;
	private JButton btnNbJoueurs;
	private JButton btnNomsJoueurs;
	private JButton btnRolesJoueurs;
	private JLabel title;
	private JLabel nbJoueursLabel;
	private JLabel nbBotsLabel;
	private JLabel labelError;
	private JLabel[] nomsJoueurs;
	private JTextField[] nomsJoueursInput;
	
	/**Détermine si la partie est commencée ou non*/
	private boolean estCommencee;

	/**La partie*/
	private Partie partie = Partie.getInstance();
		
	/**
	 * Création de l'application
	 */
	public ViewInitialiser() {
		
		this.estCommencee = partie.isEstCommencee();
		
		initialize();
		
		//L'interface graphique Observe la Partie.java
		partie.addObserver(this);
		
		ControllerInitialiser controller = new ControllerInitialiser(this);	
		
	}

	/**
	 * Initialise le contenu de la fenêtre
	 */
	private void initialize() {
		if(!estCommencee) {
			
			title = new JLabel("Initialisation de la partie");
			
		} else {
			
			title = new JLabel("Initialisation d'un nouveau round");

		}
		
		//Fenetre
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//titre en haut de la page
		title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		title.setBounds(22, 26, 400, 27);
		frame.getContentPane().add(title);
		
		//bouton pour valider le nombre de joueurs
		btnNbJoueurs = new JButton("Valider le nombre de joueurs !");
		btnNbJoueurs.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNbJoueurs.setBounds(150, 400, 283, 29);
		btnNbJoueurs.setVisible(!estCommencee);
		frame.getContentPane().add(btnNbJoueurs);
		
		//bouton pour valider les noms des joueurs
		btnNomsJoueurs = new JButton("Valider les noms des joueurs !");
		btnNomsJoueurs.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNomsJoueurs.setBounds(150, 400, 283, 29);
		btnNomsJoueurs.setVisible(!estCommencee);
		frame.getContentPane().add(btnNomsJoueurs);
		
		//bouton pour valider les roles des joueurs lors d'un nouveau round
		btnRolesJoueurs = new JButton("Valider les rôles des joueurs !");
		btnRolesJoueurs.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnRolesJoueurs.setBounds(150, 400, 283, 29);
		btnRolesJoueurs.setVisible(estCommencee);
		frame.getContentPane().add(btnRolesJoueurs);
		
		//label du nombre de joueurs humains
		nbJoueursLabel = new JLabel("Entrez le nombre de joueurs humains");
		nbJoueursLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		nbJoueursLabel.setBounds(22, 99, 262, 21);
		nbJoueursLabel.setVisible(!estCommencee);
		frame.getContentPane().add(nbJoueursLabel);
		
		//label du nombre de joueurs bots
		nbBotsLabel = new JLabel("Entrez le nombre de joueurs robots");
		nbBotsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		nbBotsLabel.setBounds(22, 137, 262, 21);
		nbBotsLabel.setVisible(!estCommencee);
		frame.getContentPane().add(nbBotsLabel);
		
		//select du nombre de joueurs humains
		nbHumains = new JSpinner();
		nbHumains.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		nbHumains.setBounds(294, 102, 78, 20);
		nbHumains.setVisible(!estCommencee);
		frame.getContentPane().add(nbHumains);
		
		//select du nombre de joueurs bots
		nbBots = new JSpinner();
		nbBots.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		nbBots.setBounds(294, 140, 78, 20);
		nbBots.setVisible(!estCommencee);
		frame.getContentPane().add(nbBots);
		
		afficherNomLabelEtRoleCombo();
		
		//message d'erreur 
		labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setBounds(22, 64, 650, 14);
		frame.getContentPane().add(labelError);
	}
	
	/**
	 * Affiche le nom du joueur dans le label et le combobox avec les rôles
	 * Si la partie n'est pas commencée, on affiche le label, le JTextFiel pour saisir le nom et le JComboBox pour saisir le rôle
	 * Si la partie est commencée, on affiche le label avec le nom déjà existant et le JComboBox pour choisir un nouveau rôle
	 */
	public void afficherNomLabelEtRoleCombo() {
		
		if(!estCommencee) {
	
			//liste de champs avec label et input pour entrer les noms des joueurs humains
			nomsJoueurs = new JLabel[6];
			nomsJoueursInput = new JTextField[6];
			rolesJoueurs = new JSpinner[6];
			
			for(int i = 0; i < 6; i++) {
				
				nomsJoueurs[i] = new JLabel("");
				nomsJoueurs[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
				nomsJoueurs[i].setBounds(22, 180 + i*30, 262, 21);
				frame.getContentPane().add(nomsJoueurs[i]);
				
				nomsJoueursInput[i] = new JTextField();
				nomsJoueursInput[i].setBounds(294, 180 + i*30, 292, 20);
				nomsJoueursInput[i].setVisible(false);
				frame.getContentPane().add(nomsJoueursInput[i]);
				nomsJoueursInput[i].setColumns(10);
				
				rolesJoueurs[i] = new JSpinner();
				rolesJoueurs[i].setModel(new SpinnerListModel(new String[] {"Witch", "Hunt"}));
				rolesJoueurs[i].setBounds(600, 180 + i*30, 120, 20);
				rolesJoueurs[i].setVisible(false);
				frame.getContentPane().add(rolesJoueurs[i]);
			}
			
		} else {
			
			//liste de champs avec label et input pour entrer les noms des joueurs humains
			nomsJoueurs = new JLabel[partie.getNbJoueursHumains()];
			nomsJoueursInput = new JTextField[partie.getNbJoueursHumains()];
			rolesJoueurs = new JSpinner[partie.getNbJoueursHumains()];
			
			Joueur joueur;
			
			for(int i = 0; i < partie.getListeJoueursInitial().size(); i++) {
				
				joueur = partie.getListeJoueursInitial().get(i);
				if(joueur instanceof Humain) {
					nomsJoueurs[i] = new JLabel("Veuillez choisir un rôle " + joueur.getNom());
					nomsJoueurs[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
					nomsJoueurs[i].setBounds(22, 80 + i*30, 262, 21);
					frame.getContentPane().add(nomsJoueurs[i]);
					
					rolesJoueurs[i] = new JSpinner();
					rolesJoueurs[i].setModel(new SpinnerListModel(new String[] {"Witch", "Hunt"}));
					rolesJoueurs[i].setBounds(300, 80 + i*30, 120, 20);
					frame.getContentPane().add(rolesJoueurs[i]);
				}
			}
		}
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public JSpinner getNbBots() {
		return nbBots;
	}

	public JSpinner getNbHumains() {
		return nbHumains;
	}

	public JButton getBtnNbJoueurs() {
		return btnNbJoueurs;
	}

	public JButton getBtnNomsJoueurs() {
		return btnNomsJoueurs;
	}
	
	public JButton getBtnRolesJoueurs() {
		return btnRolesJoueurs;
	}

	public JTextField[] getNomsJoueursInput() {
		return nomsJoueursInput;
	}
	
	public JSpinner[] getRolesJoueurs() {
		return rolesJoueurs;
	}

	/**
	 * Permet de mettre a jour les elements d'affichage
	 * @param o L'objet qui est observé
	 * @param arg Les eventuels arguments passés
	 */
	public void update(Observable o, Object arg) {
		
		if((Message) arg == Message.ERROR) {
			
			labelError.setText(partie.getError());
			
		} else if((Message) arg == Message.SET_NB_JOUEURS) {
			
			for(int i = 0; i < partie.getNbJoueursHumains(); i++) {
				nomsJoueurs[i].setText("Entrez le nom et le rôle du joueur n°" + (i+1));
				nomsJoueursInput[i].setVisible(true);
				rolesJoueurs[i].setVisible(true);
			}
			
		}
		
	}
}
