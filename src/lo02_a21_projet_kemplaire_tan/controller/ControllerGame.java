package lo02_a21_projet_kemplaire_tan.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import lo02_a21_projet_kemplaire_tan.modele.effets.ChoisirProchainJoueur;
import lo02_a21_projet_kemplaire_tan.modele.effets.ChoisirProchainJoueurSpecial;
import lo02_a21_projet_kemplaire_tan.modele.effets.DefausseUneCarte;
import lo02_a21_projet_kemplaire_tan.modele.effets.PrendreCarteReveleeAutreJoueur;
import lo02_a21_projet_kemplaire_tan.modele.effets.ReprendreCarteRevelee;
import lo02_a21_projet_kemplaire_tan.modele.effets.ReveleSonIdentite;
import lo02_a21_projet_kemplaire_tan.modele.effets.RevelerOuDefausser;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Action;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Carte;
import lo02_a21_projet_kemplaire_tan.modele.jeu.CarteNom;
import lo02_a21_projet_kemplaire_tan.modele.jeu.ChoixAction;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Effet;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Humain;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Partie;
import lo02_a21_projet_kemplaire_tan.modele.jeu.Role;
import lo02_a21_projet_kemplaire_tan.view.ViewGame;
import lo02_a21_projet_kemplaire_tan.view.ViewInitialiser;

/**
* Classe ControllerGame
*/
/**
* 
* Classe faisant le lien entre la Vue et le Mod�le :
* R�cup�re les informations saisies sur la vue et les transmet au mod�le et met
* � jour la vue.
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.controller
* @see lo02_a21_projet_kemplaire_tan.modele.jeu
* @see lo02_a21_projet_kemplaire_tan.modele.effets
* @see lo02_a21_projet_kemplaire_tan.view
* 
*/
public class ControllerGame {
	
	/**Le vue qui affiche le jeu (View)*/
	private ViewGame view;
	/**La partie qui est lanc�e (Model)*/
	private Partie partie = Partie.getInstance();
	
	/**Le r�le utilis� pour ex�cuter les effets de la carte*/
	private Role roleUtilise;

	/**Indique si le joueur doit choisir une carte ou un joueur lorsqu'il lance un effet*/
	private boolean choix;
	/**Joueur qui accuse un autre joueur lors d'un effet*/
	private Joueur joueurAccusateur;
	/**Le joueur qui est accus�*/
	private Joueur joueurAccuse;
	/**Joueur qui joue un effet*/
	private Joueur joueurConcerne;
	/**L'effet qui est en train d'�tre ex�cut�*/
	private Effet effetEnCours;

	/**L'action qui est en train d'�tre ex�cut�e*/
	private String actionEnCours;

	/**La carte qui a �t� r�v�l�e*/
	private Carte carteRevelee;

	/**La liste des effets a ex�cuter*/
	private LinkedList<Effet> effetsAExecuter;

	/**
	 * Constructeur du ControllerGame
	 * Lui affecte la {@link ViewGame} qu'il doit g�rer
	 * @param view - La vue que l'on souhaite relier
	 */
	public ControllerGame(ViewGame view) {

		this.view = view;
		this.joueurAccuse = null;

		initialiseListeners();
		joueTour();

	}
	
	public Role getRoleUtilise() {
		return roleUtilise;
	}

	public void setRoleUtilise(Role roleUtilise) {
		this.roleUtilise = roleUtilise;
	}

	public void setChoix(boolean choix) {
		this.choix = choix;
	}

	public Joueur getJoueurAccusateur() {
		return joueurAccusateur;
	}

	public void setJoueurAccusateur(Joueur joueurAccusateur) {
		this.joueurAccusateur = joueurAccusateur;
	}

	public Joueur getJoueurConcerne() {
		return joueurConcerne;
	}

	public void setJoueurConcerne(Joueur joueurConcerne) {
		this.joueurConcerne = joueurConcerne;
	}
	
	public Effet getEffetEnCours() {
		return effetEnCours;
	}

	public void setEffetEnCours(Effet effetEnCours) {
		this.effetEnCours = effetEnCours;
	}
	
	
	public String getActionEnCours() {
		return actionEnCours;
	}

	public void setActionEnCours(String actionEnCours) {
		this.actionEnCours = actionEnCours;
	}
	
	public Carte getCarteRevelee() {
		return carteRevelee;
	}

	public void setCarteRevelee(Carte carteRevelee) {
		this.carteRevelee = carteRevelee;
	}
	
	/**
	 * <b>Permet de lancer une action.</b>
	 * <br> Ex�cute les actions suivantes lors d'�v�nements au clic : 
	 * <br>- Jouer son tour : choisir entre accuser et r�v�ler une carte hunt
	 * <br>- Se d�fendre : choisir entre r�v�ler son identit� ou r�v�ler une carte witch
	 * <br>- Valider l'action "Accuser"
	 * <br>- Valider l'action "Choisir une carte"
	 * <br>- Passer au prochain tour
	 * <br>- S�lectionner un joueur ou une carte lors d'ex�cution d'effet
	 * 
	 */
	public void initialiseListeners() {
		
		//Sinon on laisse choisir l'action accuse ou revele
		view.getJoueTourBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//On indique que la partie est commenc�e / en cours
				partie.setEstCommencee(true);
				
				//On ex�cute l'action selon ce qui est entr� dans getChoixActionCombo()
				Action actionChoisie = recupAction(false);

				if(actionChoisie == Action.ACCUSE_JOUEUR) {
					
					//On cache les �l�ments inutiles a l'�tape
					//On affiche la liste des joueurs pouvant �tre accus�s et le nouveau bouton pour valider le nom de la personne a accuser 
					view.affichageAccuseJoueur(false);

				} else {

					setRoleUtilise(Role.HUNT);
					affichageRevelerUneCarte();

				}

			}
		});
		
		//Se defendre	
		view.getvaliderActionBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Action action = recupAction(false);

				if (action == Action.REVELE_IDENTITE) {

					view.affichageRevelerSonIdentite(partie.getJoueurAccuse());	
					passerAuProchainTour();

				} else {

					setRoleUtilise(Role.WITCH);
					affichageRevelerUneCarte();

				}

			}
		});
		
		//Accuse joueur	
		view.getAccuserBouton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nomJoueurCombo = (String)view.getChoixJoueurCombo().getSelectedItem();
				Joueur joueurAccuse = Joueur.recupererJoueur(partie.getJoueursPouvantEtreAccuses(),nomJoueurCombo);
				
				partie.setJoueurAccuse(joueurAccuse);
				
				if(joueurAccuse instanceof Humain) {
					
					view.affichageSeDefendre(joueurAccuse);
					
				} else {
					
					view.cacherBoutonsCombosLabels();
					
					Action actionJouee = partie.getJoueurAccuse().choisirAction(ChoixAction.IDENTITE_OU_RUMEUR);

					if(actionJouee == Action.REVELE_IDENTITE) {
						setActionEnCours("REVELE_IDENTITE");
					} else {
						setActionEnCours("REVELE_CARTE_DEFENDRE");
					}
					
					view.getDescriptionActionsBot().setText(partie.getJoueurAccuse().getNom()+" a choisi : "+actionJouee.toString());
					view.getDescriptionActionsBot().setVisible(true);
					view.getValideActionBot().setVisible(true);
					
				}

			}

		});
		
		//Choisir une carte
		view.getChoisirCarteBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Carte carteRecuperee;
				
				setJoueurConcerne(null);
				setJoueurAccusateur(null);

				if(getRoleUtilise() == Role.WITCH) {
					setJoueurConcerne(partie.getJoueurAccuse());
					setJoueurAccusateur(partie.getJoueurCourant());
				} else {	
					setJoueurConcerne(partie.getJoueurCourant());
				}

				carteRecuperee = recupCarte(getJoueurConcerne().getCartesPouvantEtreRevelees(getRoleUtilise()));
				getJoueurConcerne().reveleCarte(carteRecuperee);

				//Chargement des effets 
				chargerEffets(getRoleUtilise(), carteRecuperee, getJoueurConcerne(), getJoueurAccusateur());
				afficherEffet(getRoleUtilise(), getJoueurConcerne(), getJoueurAccusateur());
				
			}
		});
		
		//Prochain tour
		view.getProchainTourBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				view.getLabelError().setText("");
				view.getLabelDescriptionEffet().setText("");
				
				//tant que le round n'est pas termin�
				if(!partie.verifRoundTermine()) {

					if(!partie.isProchainJoueurDetermine()) {
						partie.determinerProchainJoueur();
					}

					System.out.println("Prochain joueur " + partie.getProchainJoueur().getNom());

					partie.setJoueurCourant(partie.getProchainJoueur());

					joueTour();

					//Le round est termin�
				} else {
					
					partie.termineRound();

					//Personne n'a encore gagn�
					if(!partie.verifJoueurAGagne()) {

						view.affichageFinRound();

					} else {

						partie.setEstCommencee(false);
						view.affichageFinPartie();

					}

				}

			}
		});
		
		//S�lectionner un joueur ou une carte si l'effet le demande
		view.getvaliderEffetBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				view.cacherTousLesCombos();
				
				if(!partie.isError() && choix) {

					//R�cup�rer donn�es dans les comboboxs

					if(getEffetEnCours() instanceof ChoisirProchainJoueur || getEffetEnCours() instanceof ChoisirProchainJoueurSpecial) {
						partie.setJoueurChoisi(Joueur.recupererJoueur(partie.getListeJoueursEnJeu(),(String)view.getChoixJoueurCombo().getSelectedItem()));
					} else if(getEffetEnCours() instanceof DefausseUneCarte) {
						partie.setCarteChoisie(recupCarte(joueurConcerne.getMain()));
					} else if(getEffetEnCours() instanceof PrendreCarteReveleeAutreJoueur) {
						partie.setJoueurChoisi(Joueur.recupererJoueur(Partie.getInstance().getListeJoueursEnJeu(),(String)view.getChoixJoueurCombo().getSelectedItem()));
						partie.setCarteChoisie(recupCarte(partie.getJoueurChoisi().getCartesRevelees()));
					} else if(getEffetEnCours() instanceof ReprendreCarteRevelee) {
						partie.setCarteChoisie(recupCarte(joueurConcerne.getCartesRevelees()));
					} else if(getEffetEnCours() instanceof RevelerOuDefausser) {
						partie.setJoueurChoisi(Joueur.recupererJoueur(partie.getListeJoueursEnJeu(),(String)view.getChoixJoueurEffetCombo().getSelectedItem()));
						partie.setActionChoisie(recupAction(true));
						partie.setCarteChoisie(recupCarte(partie.getJoueurChoisi().getMain()));
					}

					getEffetEnCours().executerEffet(getJoueurConcerne(), getJoueurAccusateur());
					passerEffetSuivant(getRoleUtilise(),getJoueurConcerne(),getJoueurAccusateur());

				}

			}
		});
		
		//Changer dynamiquement la main du joueur en fonction du joueur choisi
		view.getChoixJoueurCombo().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				String nomJoueur = (String)view.getChoixJoueurCombo().getSelectedItem();
				Joueur joueurChoisi = Joueur.recupererJoueur(partie.getListeJoueursEnJeu(),nomJoueur);

				view.getChoixCarteCombo().setModel(new DefaultComboBoxModel(getNomCartes(joueurChoisi.getCartesRevelees())));

			}
		});
		
		
		//Changer dynamiquement la main et les actions possibles en fonction du joueur choisi lors de l'effet Hunt de la carte Ducking
		view.getChoixJoueurEffetCombo().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				view.getChoixActionEffetCombo().setVisible(false);
				view.getChoixCarteCombo().setVisible(false);
				
				String nomJoueur = (String)view.getChoixJoueurEffetCombo().getSelectedItem();
				Joueur joueurChoisi = Joueur.recupererJoueur(partie.getListeJoueursEnJeu(),nomJoueur);
				Action actionChoisie = null;
				
				if(!joueurChoisi.isIdentiteRevelee()) {
					
					view.getChoixActionEffetCombo().setVisible(true);
					view.getChoixActionEffetCombo().setBounds(22, 175, 269, 22);
					
					view.getChoixActionEffetCombo().setModel(new DefaultComboBoxModel(((Humain)joueurConcerne).getActionsPossibles(ChoixAction.DEFAUSSE_OU_IDENTITE)));
					
					actionChoisie = recupAction(true);
					
				}
				
				if(actionChoisie == null || actionChoisie == Action.DEFAUSSE_CARTE) {
					
					if(joueurChoisi.getMain().size() > 0) {
						
						view.getChoixCarteCombo().setVisible(true);
						view.getChoixCarteCombo().setBounds(22, 205, 269, 22);
						
						view.getChoixCarteCombo().setModel(new DefaultComboBoxModel(getNomCartes(joueurChoisi.getMain())));
						
					} else {
						
						Partie.getInstance().setError("Impossible d'executer l'effet - Le joueur n'a pas de carte � d�fausser");
						partie.setIsError(true);
						
					}
					
				}

			}
		});
		
		//Permettre de choisir une action possible en fonction du joueur
		view.getChoixActionEffetCombo().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				view.getChoixCarteCombo().setVisible(false);
				
				String nomJoueur = (String)view.getChoixJoueurEffetCombo().getSelectedItem();
				Joueur joueurChoisi = Joueur.recupererJoueur(partie.getListeJoueursEnJeu(),nomJoueur);
				Action actionChoisie = null;
				
				if(!joueurChoisi.isIdentiteRevelee()) {
					actionChoisie = recupAction(true);	
				}
				
				if(actionChoisie == null || actionChoisie == Action.DEFAUSSE_CARTE) {
					
					if(joueurChoisi.getMain().size() > 0) {
						
						view.getChoixCarteCombo().setVisible(true);
						view.getChoixCarteCombo().setBounds(22, 205, 269, 22);
						
						view.getChoixCarteCombo().setModel(new DefaultComboBoxModel(getNomCartes(joueurChoisi.getMain())));
						
					} else {
						
						partie.setError("Impossible d'executer l'effet - Le joueur n'a pas de carte � d�fausser");
						partie.setIsError(true);
						
					}
					
				}

			}
		});
		
		//Passer � l'effet suivant
		view.getPoursuivreBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				view.getLabelError().setText("");
				view.getLabelDescriptionEffet().setText("");
				
				afficherEffet(getRoleUtilise(), joueurConcerne, joueurAccusateur);	
				
			}
		});
		
		//Valider l'action d'un BOT lorsqu'il joue
		view.getValideActionBot().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				view.getValideActionBot().setVisible(false);
				view.getDescriptionActionsBot().setVisible(false);
				view.getDescriptionActionsBot().setText("");
				
				//Ex�cution de l'action ACCUSE_JOUEUR
				if(getActionEnCours().equals("ACCUSE_JOUEUR")) {
					
					if(partie.getJoueurAccuse() instanceof Humain) {
						
						view.affichageSeDefendre(partie.getJoueurAccuse());
						
					} else {
						
						Action actionJouee = partie.getJoueurAccuse().choisirAction(ChoixAction.IDENTITE_OU_RUMEUR);

						if(actionJouee == Action.REVELE_IDENTITE) {
							setActionEnCours("REVELE_IDENTITE");
						} else {
							setActionEnCours("REVELE_CARTE_DEFENDRE");
						}
						
						view.getDescriptionActionsBot().setText(view.getDescriptionActionsBot().getText()+partie.getJoueurAccuse().getNom()+" a choisi : "+actionJouee.toString());
						view.getDescriptionActionsBot().setVisible(true);
						view.getValideActionBot().setVisible(true);
						
					}
					
				} 
				//Ex�cution de l'action REVELE_IDENTITE
				else if(getActionEnCours().equals("REVELE_IDENTITE")) {
					
					view.affichageRevelerSonIdentite(partie.getJoueurAccuse());
					passerAuProchainTour();
					
				} 
				//Ex�cution de l'action REVELE_CARTE_DEFENDRE => r�v�ler une carte selon l'action (se d�fendre ou jouer son tour)
				else if(getActionEnCours().equals("REVELE_CARTE_DEFENDRE") || getActionEnCours().equals("REVELE_CARTE_ATTAQUE")) {
					
					Carte carteRevelee;
					
					if(getActionEnCours().equals("REVELE_CARTE_DEFENDRE")) {
						setRoleUtilise(Role.WITCH);
						carteRevelee = partie.getJoueurAccuse().reveleCarte(Role.WITCH);
					} else {
						setRoleUtilise(Role.HUNT);
						carteRevelee = partie.getJoueurCourant().reveleCarte(Role.HUNT);
					}
					
					setJoueurConcerne(null);
					setJoueurAccusateur(null);

					if(getRoleUtilise() == Role.WITCH) {
						setJoueurConcerne(partie.getJoueurAccuse());
						setJoueurAccusateur(partie.getJoueurCourant());
					} else {	
						setJoueurConcerne(partie.getJoueurCourant());
					}

					getJoueurConcerne().reveleCarte(carteRevelee);
					setCarteRevelee(carteRevelee);
					
					view.getDescriptionActionsBot().setText(getJoueurConcerne().getNom()+" a r�v�l� la carte "+carteRevelee.getNom());
					view.getDescriptionActionsBot().setVisible(true);
					view.getValideActionBot().setVisible(true);
					
					setActionEnCours("A_REVELE_CARTE");
					
				} 
				//Afficher et jouer les effets si on a d�cid� de r�v�ler une carte
				else if (getActionEnCours().equals("A_REVELE_CARTE")) {
					
					chargerEffets(getRoleUtilise(), getCarteRevelee(), getJoueurConcerne(), getJoueurAccusateur());
					afficherEffet(getRoleUtilise(), getJoueurConcerne(), getJoueurAccusateur());
					
				}
				
			}
		});
		
		//Initier un nouveau Round 
		view.getProchainRoundBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				initierNouveauRound();
				
			}
		});
		
	}

	/**
	 * Le joueur courant joue son tour
	 */
 	public void joueTour() {
 		
 		view.raffraichissementListeJoueurs();
		partie.determinerProchainJoueur();
		
		view.cacherBoutonsCombosLabels();
		
		//Si l'effet sp�cial "accuser une personne pendant son tour" n'est pas lanc�
		if(!partie.getEffetChoisirLance()) {
			
			if(partie.getJoueurCourant() instanceof Humain) {
				
				view.initialiseChoixAction();
				
				view.getChoixActionLabel().setVisible(true);
				view.getChoixActionLabel().setText("Que souhaitez vous faire ?");
				
				view.getChoixActionCombo().setVisible(true);
				view.getJoueTourBtn().setVisible(true);
				
				view.updateAffichageMain(partie.getJoueurCourant());
				view.affichageMain(true);
				
			} else {
				
				view.affichageMain(false);
				
				partie.getJoueurCourant().setActionJouee(partie.getJoueurCourant().choisirAction(ChoixAction.ACCUSE_OU_RUMEUR));
				
				if(partie.getJoueurCourant().getActionJouee() == Action.ACCUSE_JOUEUR) {
					
					Joueur accuse = partie.getJoueurCourant().choisirJoueurAAccuser(partie.getJoueursPouvantEtreAccuses());
					partie.setJoueurAccuse(accuse);
					
					setActionEnCours("ACCUSE_JOUEUR");
					
					view.getDescriptionActionsBot().setText(partie.getJoueurCourant().getNom()+" a choisi d'accuser "+accuse.getNom());
					view.getDescriptionActionsBot().setVisible(true);
					view.getValideActionBot().setVisible(true);
					
				} else {
					
					setActionEnCours("REVELE_CARTE_ATTAQUE");
					
					view.getDescriptionActionsBot().setText(partie.getJoueurCourant().getNom()+" a choisi : "+getActionEnCours().toString());
					view.getDescriptionActionsBot().setVisible(true);
					view.getValideActionBot().setVisible(true);
					
				}
				
			}
			
		} 
		//Sinon : l'effet sp�cial "choisir un joueur a accuser pendant son tour" est lanc�
		else {

            partie.setEffetChoisirLance(false);
            
            if(partie.getJoueurCourant() instanceof Humain) {
            	
            	view.getChoixActionLabel().setVisible(true);
    			view.getChoixActionLabel().setText("Le joueur pr�c�dent ("+partie.getJoueurPrecedent().getNom()+") vous a lanc� un effet. Veuillez choisir un joueur a accuser en �vitant le joueur pr�c�dent.");
    			
                view.affichageAccuseJoueur(true);
            	
            } else {
            	
            	ArrayList<Joueur> listeJoueurs;

                listeJoueurs = new ArrayList<Joueur>();
                for(Joueur j : partie.getJoueursPouvantEtreAccuses()) {
                    if(j.getId() != partie.getJoueurPrecedent().getId()) {
                        listeJoueurs.add(j);
                    }
                }
                
                Joueur accuse;
                
                if (listeJoueurs.size() > 0) {
                	accuse = partie.getJoueurCourant().choisirJoueurAAccuser(listeJoueurs);
                } else {
                	accuse = partie.getJoueurCourant().choisirJoueurAAccuser(partie.getJoueursPouvantEtreAccuses());
                }
            	
				partie.setJoueurAccuse(accuse);
				
				setActionEnCours("ACCUSE_JOUEUR");
				
				view.getDescriptionActionsBot().setText(partie.getJoueurCourant().getNom()+" a choisi d'accuser "+accuse.getNom());
				view.getDescriptionActionsBot().setVisible(true);
				view.getValideActionBot().setVisible(true);
            	
            }
            
		}

	}

 	/**
 	 * Passer au prochain effet.
 	 * @param role - Le role avec lequel on ex�cute l'effet
 	 * @param joueurConcerne - le joueur qui joue la carte
 	 * @param joueurAccusateur - le joueur qui a accus� le joueur qui joue la carte
 	 */
	public void passerEffetSuivant(Role role, Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		view.getChoixCarteCombo().setBounds(22, 145, 269, 22);
		view.getChoixActionCombo().setBounds(22, 145, 269, 22);
		view.getChoixJoueurCombo().setBounds(22, 145, 269, 22);
		
		view.cacherTousLesBoutons();
		view.getPoursuivreBtn().setVisible(true);
		
		if(effetsAExecuter.size() <= 0) {
			passerAuProchainTour();
		}

	}

	/**
	 * Ex�cute les effets de la carte choisie par le joueur
	 * @param role - Role d'execution de la carte - s�lectionne la liste des effets witch ou hunt a executer 
	 * @param joueurConcerne - Le joueur qui execute l'effet
	 * @param effet - Effet execut�
	 * @param joueurAccusateur - Le joueur qui a accus� le joueur qui joue la carte (optionnel)
	 * @param error - Permet de d�terminer s'il y a une erreur dans le cadre de l'execution de l'effet
	 * @param choix - Permet de d�terminer si l'execution de l'effet fait suite � un choix manuel de la part du joueur
	 */
	public void executerEffet (Role role, Effet effet, Joueur joueurConcerne, Joueur joueurAccusateur, boolean error, boolean choix) {
		
		if(!choix || error) {

			view.cacherBoutonsCombosLabels();
			view.getChoixActionLabel().setVisible(true);

			effet.executerEffet(joueurConcerne, joueurAccusateur);
			passerEffetSuivant(role,joueurConcerne,joueurAccusateur);

		}

	}

	/**
	 * Affichage de l'�x�cution des effets
	 * @param role - S�lectionne la liste des effets witch ou hunt a executer 
	 * @param carteRecuperee - La carte qui a �t� s�lectionn�e
	 * @param joueurConcerne - Le joueur qui joue la carte
	 * @param joueurAccusateur - Le joueur qui a accus� le joueur qui joue la carte (optionnel)
	 */
	public void chargerEffets (Role role, Carte carteRecuperee, Joueur joueurConcerne, Joueur joueurAccusateur) {

		//On v�rifie si les effets n�c�ssitent de choisir un joueur ou une carte
		
		this.effetsAExecuter = new LinkedList<>();

		ArrayList<Effet> listeEffets;
		
		if (role == Role.HUNT) {
			listeEffets = carteRecuperee.getListeEffetsHunt();
		} else {
			listeEffets = carteRecuperee.getListeEffetsWitch();
		}

		for(int i = 0; i < listeEffets.size(); i++) {
			effetsAExecuter.add(listeEffets.get(i));
		}

	}

	/**
	 * Afficher les effets (description et affichage selon les effets)
	 * @param role - le r�le avec lequel on ex�cute l'effet
	 * @param joueurConcerne - le joueur qui joue la carte
	 * @param joueurAccusateur - le joueur qui accuse le joueurConcerne
	 */
	public void afficherEffet(Role role, Joueur joueurConcerne, Joueur joueurAccusateur) {
		
		view.cacherBoutonsCombosLabels();
		
		view.updateAffichageMain(joueurConcerne);
		view.affichageMain(false);
		
		partie.setIsError(false);
		setChoix(false);

		if(effetsAExecuter.size() > 0) {

			setEffetEnCours(effetsAExecuter.pop());
			
			view.getChoixActionLabel().setVisible(true);
			view.getChoixActionLabel().setText("Effet : "+getEffetEnCours().getDescription());
			
			view.getvaliderEffetBtn().setVisible(true);

			if(joueurConcerne instanceof Humain) {
				
				if(getEffetEnCours() instanceof ChoisirProchainJoueur || getEffetEnCours() instanceof ChoisirProchainJoueurSpecial) {

					setChoix(true);

					view.getChoixJoueurCombo().setVisible(true);

					ArrayList<Joueur> joueursPouvantEtreChoisis;

					if(joueurAccusateur == null) {
						joueursPouvantEtreChoisis = partie.getJoueursPouvantEtreChoisis();
					} else {
						joueursPouvantEtreChoisis = partie.getListeJoueursEnJeu();
						joueursPouvantEtreChoisis.remove(joueurConcerne);
					}

					view.getChoixJoueurCombo().setModel(new DefaultComboBoxModel(view.getNomJoueurs(joueursPouvantEtreChoisis)));

				}
				
				else if (getEffetEnCours() instanceof DefausseUneCarte) {

					setChoix(true);

					ArrayList<Carte> main = joueurConcerne.getMain();

					if(main.size() > 0) {

						view.getChoixCarteCombo().setVisible(true);
						view.getChoixCarteCombo().setModel(new DefaultComboBoxModel(getNomCartes(main)));

					} else {

						partie.setError("Impossible d'executer l'effet - Aucun joueur n'a de carte r�v�l�e.");
						partie.setIsError(true);

					}

				}
				else if (getEffetEnCours() instanceof PrendreCarteReveleeAutreJoueur) {

					setChoix(true);

					if(joueurConcerne.getJoueursAvecCarteRevelee().size() > 0) {

						view.getChoixJoueurCombo().setVisible(true);
						view.getChoixCarteCombo().setVisible(true);

						view.getChoixJoueurCombo().setModel(new DefaultComboBoxModel(view.getNomJoueurs(joueurConcerne.getJoueursAvecCarteRevelee())));

						String nomJoueur = (String)view.getChoixJoueurCombo().getSelectedItem();
						Joueur joueurChoisi = Joueur.recupererJoueur(partie.getListeJoueursEnJeu(),nomJoueur);

						view.getChoixCarteCombo().setBounds(22, 175, 269, 22);
						view.getChoixCarteCombo().setModel(new DefaultComboBoxModel(getNomCartes(joueurChoisi.getCartesRevelees())));

						//changecombo

					} else {

						partie.setError("Impossible d'executer l'effet - Aucun joueur n'a de carte r�v�l�e.");
						partie.setIsError(true);

					}

				}
				else if (getEffetEnCours() instanceof ReprendreCarteRevelee) {

					setChoix(true);

					if(joueurConcerne.getCartesRevelees().size() > 1) {

						view.getChoixCarteCombo().setVisible(true);

						ArrayList<Carte> cartesPouvantEtreRecuperees = new ArrayList<>();
						cartesPouvantEtreRecuperees.addAll(joueurConcerne.getCartesRevelees());
						cartesPouvantEtreRecuperees.remove(joueurConcerne.getDerniereCarteJouee());

						view.getChoixCarteCombo().setModel(new DefaultComboBoxModel(getNomCartes(cartesPouvantEtreRecuperees)));

					} else {

						partie.setError("Impossible d'executer l'effet - Aucun joueur n'a de carte r�v�l�e.");
						partie.setIsError(true);

					}
					
				} else if (getEffetEnCours() instanceof ReveleSonIdentite) {
					
					if(joueurConcerne.getRole() == Role.HUNT) {
						
						setChoix(true);
						
						view.getChoixJoueurCombo().setVisible(true);

						ArrayList<Joueur> joueursPouvantEtreChoisis;

						if(joueurAccusateur == null) {
							joueursPouvantEtreChoisis = partie.getJoueursPouvantEtreChoisis();
						} else {
							joueursPouvantEtreChoisis = partie.getListeJoueursEnJeu();
							joueursPouvantEtreChoisis.remove(joueurConcerne);
						}

						view.getChoixJoueurCombo().setModel(new DefaultComboBoxModel(view.getNomJoueurs(joueursPouvantEtreChoisis)));
						
					}

				} else if (getEffetEnCours() instanceof RevelerOuDefausser) {
					
					if(partie.getJoueursPouvantEtreChoisis().size() > 0) {
						
						setChoix(true);
						
						view.getChoixJoueurEffetCombo().setVisible(true);
						
						ArrayList<Joueur> joueursPouvantEtreChoisis = partie.getJoueursPouvantEtreChoisis();
						view.getChoixJoueurEffetCombo().setModel(new DefaultComboBoxModel(view.getNomJoueurs(joueursPouvantEtreChoisis)));
						
						String nomJoueur = (String)view.getChoixJoueurEffetCombo().getSelectedItem();
						Joueur joueurChoisi = Joueur.recupererJoueur(partie.getListeJoueursEnJeu(),nomJoueur);
						Action actionChoisie = null;
						
						if(!joueurChoisi.isIdentiteRevelee()) {
							
							view.getChoixActionEffetCombo().setVisible(true);
							view.getChoixActionEffetCombo().setBounds(22, 175, 269, 22);
							
							view.getChoixActionEffetCombo().setModel(new DefaultComboBoxModel(((Humain)joueurConcerne).getActionsPossibles(ChoixAction.DEFAUSSE_OU_IDENTITE)));
							
							actionChoisie = recupAction(true);
							
						}
						
						if(actionChoisie == null || actionChoisie == Action.DEFAUSSE_CARTE) {
							
							if(joueurChoisi.getMain().size() > 0) {
								
								view.getChoixCarteCombo().setVisible(true);
								view.getChoixCarteCombo().setBounds(22, 205, 269, 22);
								
								view.getChoixCarteCombo().setModel(new DefaultComboBoxModel(getNomCartes(joueurChoisi.getMain())));
								
							} else {
								
								partie.setError("Impossible d'executer l'effet - Le joueur n'a pas de carte � d�fausser");
								partie.setIsError(true);
								
							}
							
						}
						
					} else {
						
						partie.setError("Impossible d'executer l'effet - Aucun joueur ne peut �tre choisi");
						partie.setIsError(true);
						
					}

				} 
				
			}

			executerEffet(role,getEffetEnCours(),joueurConcerne,joueurAccusateur,partie.isError(),choix);

		}

	}

	/**
	 * Permet de passer au prochain tour
	 */
	public void passerAuProchainTour() {
		
		view.cacherTousLesBoutons();
		view.getProchainTourBtn().setVisible(true);

	}

	/**
	 * Initier un nouveau round lorsque tous les joueurs sauf 1 ont r�v�l� leur identit� ou ont jou� toutes leurs cartes
	 */
	public void initierNouveauRound() {
		
		partie.initierRound();
		joueTour();
		
		view.getFrame().setVisible(false);
		
        //Afficher fen�tre de jeu d'initialisation d'un nouveau round
        view.affichageInitierNouveauRound();
		
	}

    /**
     * Affichage pour r�v�ler une carte selon une liste de cartes et le r�le
     */
	public void affichageRevelerUneCarte() {

		CarteNom[] nomsCartes = getListeCartePouvantEtreRevelee(getRoleUtilise());
		
		if(getRoleUtilise() == Role.WITCH) {
			view.updateAffichageMain(partie.getJoueurAccuse());
		} else {
			view.updateAffichageMain(partie.getJoueurCourant());
		}
		
		view.cacherBoutonsCombosLabels();

		//Afficher une liste de carte et la main
		view.getChoixActionLabel().setVisible(true);
		view.getChoixActionLabel().setText("Veuillez s�lectionner une carte. Vous ex�cutez cette carte avec l'effet " + getRoleUtilise().toString());

		view.getChoixCarteCombo().setVisible(true);
		view.getChoixCarteCombo().setModel(new DefaultComboBoxModel(nomsCartes));

		view.affichageMain(true);

		view.getChoisirCarteBtn().setVisible(true);

	}

	

	/**
	 * R�cup�re l'action qu'on veut effectuer selectionn�e dans un comboBox
	 * @param effet - Indique si l'effet sp�cial "choisir un joueur a accuser lors de son tour" a �t� lanc�
	 * @return L'action qui a �t� choisie
	 */
	public Action recupAction(boolean effet) {

		String actionString;
		
		//On r�cup�re l'action entr�e
		if(effet) {
			actionString = view.getChoixActionEffetCombo().getSelectedItem().toString();
		} else {
			actionString = view.getChoixActionCombo().getSelectedItem().toString();
		}
		
		Action actionChoisie;

		if(actionString.equals("ACCUSE_JOUEUR")) {

			actionChoisie = Action.ACCUSE_JOUEUR;

		} else if(actionString.equals("REVELE_IDENTITE")) {

			actionChoisie = Action.REVELE_IDENTITE;
			
		} else if(actionString.equals("DEFAUSSE_CARTE")) {
			
			actionChoisie = Action.DEFAUSSE_CARTE;

		} else {

			actionChoisie = Action.REVELE_CARTE;

		}

		partie.getJoueurCourant().setActionJouee(actionChoisie);

		return actionChoisie;

	}

	/**
	 * R�cup�re une carte dans une liste de cartes 
	 * @param main - La liste de cartes (main d'un joueur ou d�fausse)
	 * @return La carte qui a �t� choisie
	 */
	public Carte recupCarte (ArrayList<Carte> main) {

		String carteChoisieString = view.getChoixCarteCombo().getSelectedItem().toString();

		System.out.println(carteChoisieString);
		for(Carte c : main) {
			System.out.println("Carte : "+c.getNom());
		}
		
		CarteNom carteNom = null;
		Carte carteChoisie = null;

		//On converti carteChoisieString (String) en carteNom (CarteNom)
		for( int i = 0; i < CarteNom.values().length; i++ ) {
			if( carteChoisieString.equals(CarteNom.values()[i].toString())) {
				carteNom = CarteNom.values()[i];
			}
		}

		//On r�cup�re la bonne carte dans la main gr�ce au nom de la carte
		for(Carte c : main) {
			if(carteNom == c.getNom()) {
				carteChoisie = c;
			}
		}

		return carteChoisie;
	}

	/**
	 * R�cup�re le nom de toutes les cartes d'une liste de cartes
	 * @param cartes - la liste des cartes dont on veut les noms
	 * @return Un tableau de noms de cartes
	 */
	public CarteNom[] getNomCartes(ArrayList<Carte> cartes) {

		CarteNom[] listeCartes = new CarteNom[cartes.size()];
		for(int i = 0; i < cartes.size(); i++) {
			listeCartes[i] = cartes.get(i).getNom();
		}

		return listeCartes;

	}
	
	/**
	 * Le noms des cartes selon le r�le qu'on veut jouer
	 * @param role - Le r�le avec lequel on joue la carte
	 * @return Un tableau de noms de cartes 
	 */
	public CarteNom[] getListeCartePouvantEtreRevelee (Role role) {

		//On r�cup�re les cartes selon le role 
		ArrayList<Carte> cartes;	
		
		if (role == Role.WITCH) {
			cartes = partie.getJoueurAccuse().getCartesPouvantEtreRevelees(role);	
		} else {			
			cartes = partie.getJoueurCourant().getCartesPouvantEtreRevelees(role);		

		}

		return getNomCartes(cartes);
	}





}