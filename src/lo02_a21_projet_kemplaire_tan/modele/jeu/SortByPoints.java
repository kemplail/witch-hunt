/**
* Classe SortByPoints
*/
/**
* 
* Classe de comparaison (implémente Comparator) permettant de trier une collection de joueur selon leur nombre de points
* 
* @author Kemplaire et Tan
* @since 1.0
* @see lo02_a21_projet_kemplaire_tan.modele.jeu.Joueur
* 
*/

package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.Comparator;

public class SortByPoints implements Comparator<Joueur> {

	public int compare(Joueur a, Joueur b)
    {
        return b.getNbPoints() - a.getNbPoints();
    }
	
}