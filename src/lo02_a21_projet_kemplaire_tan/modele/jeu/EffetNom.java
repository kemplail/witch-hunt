package lo02_a21_projet_kemplaire_tan.modele.jeu;

/**
* Enumeration EffetNom
*/
/**
* 
* Enum�ration repr�sentant l'ensemble des noms que peuvent prendre les effets associ�es � la description des effets
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu
* 
*/
public enum EffetNom {
	
	PRENDRE_PROCHAIN_TOUR("Le joueur prend le prochain tour"),
	DEFAUSSE_UNE_CARTE("Le joueur d�fausse une carte de sa main"),
	REVELER_AUTRE_IDENTITE("Le joueur r�v�ler l'identit� de quelqu'un d'autre"),
	CHOISIR_PROCHAIN_JOUEUR("Le joueur choisit le prochain joueur"),
	CHOISIR_PROCHAIN_JOUEUR_SPECIAL("Le joueur choisit le prochain joueur et le prochain joueur devra accuser quelqu'un d'autre si possible"),
	REPRENDRE_CARTE_REVELEE("Le joueur reprend une carte rumeur r�v�l�e dans sa main"),
	PRENDRE_CARTE_MAIN_ACCUSATEUR("Prendre une carte de la main de l'accusateur"),
	PRENDRE_CARTE_PROCHAIN_JOUEUR("Avant leur tour, prenez une carte de leur main"),
	REGARDER_IDENTITE_PROCHAIN_JOUEUR("Regarder l'identit� du prochain joueur"),
	REVELER_OU_DEFAUSSER("Choisissez un joueur. Il doit r�v�ler son identit� ou d�fausser une carte"),
	DEFAUSSE_CARTE_ALEATOIRE("Le joueur accusateur d�fausse une carte al�atoire de sa main"),
	REVELE_SON_IDENTITE("Le joueur r�v�le son identit�. Si HUNT, le joueur choisit le prochain joueur sinon le joueur � gauche joue"),
	PRENDRE_CARTE_DEFAUSSE("Le joueur prend une carte dans la d�fausse et d�fausse la carte utilis�e"),
	PRENDRE_CARTE_REVELEE_AUTRE_JOUEUR("Le joueur prend dans sa main une carte r�v�l�e par un autre joueur");
	
	private String descriptionEffet;

	/**
	 * Constructeur de l'�num�ration EffetNom
	 * @param descriptionEffet - Description de l'effet
	 */
	EffetNom(String descriptionEffet){
        this.descriptionEffet = descriptionEffet;
    }

    public String getDescriptionEffet(){
        return this.descriptionEffet;
    }
    
}
