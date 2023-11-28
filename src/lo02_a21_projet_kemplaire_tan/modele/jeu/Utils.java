/**
* Classe Utils
*/
/**
* 
* Classe utilitaire permettant de lire des entrées sur la console (int ou String)
* 
* @author Kemplaire et Tan
* @since 1.0
* 
*/

package lo02_a21_projet_kemplaire_tan.modele.jeu;

import java.util.Scanner;

public class Utils {

	public static String getStringInput() {

		Scanner sc = new Scanner(System.in);
		String reponse = sc.next();

		return reponse;
	}
	
	public static int getIntInput() {

		Scanner sc = new Scanner(System.in);
		int reponse = sc.nextInt();

		return reponse;
	}
	
}
