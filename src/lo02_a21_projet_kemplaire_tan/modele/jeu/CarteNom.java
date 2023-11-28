package lo02_a21_projet_kemplaire_tan.modele.jeu;

/**
* Enumeration CarteNom
*/
/**
* 
* Enumération représentant l'ensemble des noms que peut prendre une carte
* 
* 
* @author Kemplaire et Tan
* @since 1.0
* 
*/
public enum CarteNom {
	
    ANGRY("Angry Mol"),
    INQUISITION("The Inquisition"),
    HAT("Pointed Hat"), 
    NOSE("Hooked Nose"), 
    BROOMSTICK("Broom Stick"), 
    WART("Wart"), 
    DUCKING("Duncking Stool"), 
    CAULDRON("Cauldron"), 
    EYE("Evil Eye"), 
    TOAD("Toad"), 
    BLACK("Black Cat"), 
    PET("Pet Newt"); 
    
    private String nomCarte;

	/**
	 * Constructeur de l'énumération
	 * @param nomCarte : Nom de la carte
	 */
    CarteNom(String nomCarte){
        this.nomCarte = nomCarte;
    }

    public String getNomCarte(){
        return this.nomCarte;
    }
}
