package players;

import java.util.Scanner;
import java.util.Set;

import games.BatailNaval;
import games.Game;

import java.util.ArrayList;
import java.util.HashSet;

public class Human implements Player{

  	private String name;
  	private Scanner sc;

	public Human (String name){
    	this.name= name;
    	this.sc = new Scanner(System.in);
  	}

  	public String toString (){
    	return this.name;
  	}

	//Transforme un character Char en Int;
	public int translateStringToInt(char letter) {
		switch (letter) {
		case 'a': {
			return 0;
		}
		case 'b': {
			return 1;
		}
		case 'c': {
			return 2;
		}
		case 'd': {
			return 3;
		}
		case 'e': {
			return 4;
		}
		case 'f': {
			return 5;
		}
		case 'g': {
			return 6;
		}
		case 'h': {
			return 7;
		}
		case 'i': {
			return 8;
		}
		case 'j': {
			return 9;
		}
		case 'A': {
			return 0;
		}
		case 'B': {
			return 1;
		}
		case 'C': {
			return 2;
		}
		case 'D': {
			return 3;
		}
		case 'E': {
			return 4;
		}
		case 'F': {
			return 5;
		}
		case 'G': {
			return 6;
		}
		case 'H': {
			return 7;
		}
		case 'I': {
			return 8;
		}
		case 'J': {
			return 9;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + letter);
		}
	}

	/* permet de choisir un coup a joueur */
	public int chooseMove (Game game){
  		int coup_choisi;
		do{
	  		System.out.println("Choisissez la ligne : ");
	  		int ligne = sc.nextInt()-1;
	  		System.out.println("Choisissez la colonne : ");
	  		sc.nextLine();
	  		char colonne = sc.nextLine().charAt(0);
	  		coup_choisi = ligne*10 + this.translateStringToInt(colonne);
  		}while(game.isValid(coup_choisi) == false);
  		return coup_choisi;
	}

	/* permet de choisir un coup a joueur */
	public int chooseMove (BatailNaval game){
  		int coup_choisi;
		do{
	  		System.out.println("Choisissez la ligne : ");
	  		int ligne = sc.nextInt()-1;
	  		System.out.println("Choisissez la colonne : ");
	  		sc.nextLine();
	  		char colonne = sc.nextLine().charAt(0);
	  		coup_choisi = ligne*10 + this.translateStringToInt(colonne);
  		}while(game.isValid(coup_choisi) == false);
  		return coup_choisi;
	}

	/* classe permetant de placer les bateaux */
	public ArrayList<Bateau> creerBateaux() {
		// TODO Auto-generated method stub
		ArrayList<Bateau> pions = new ArrayList<Bateau>();
		int [] longueurBateau = {2, 3, 3, 4, 5};
 		int caseO;
		Integer orientation;
		int longueur;
		for(int x : longueurBateau) {
			longueur = x;
			System.out.println("Choisissez la case d'origine du bateau de longueur "+x+" :");
			System.out.println("Choisissez la ligne : ");
			int ligne = sc.nextInt()-1;
			System.out.println("Choisissez la colonne : ");
			sc.nextLine();
			char colonne = sc.nextLine().charAt(0);
			caseO = ligne*10 + this.translateStringToInt(colonne);
			System.out.println("Quel est son orientation : 1.Verticale 2.Horizontale?");
			orientation = sc.nextInt();
			if (orientation != 1  && orientation!=(2)){
				System.out.println("Quel est son orientation : 1.Verticale 2.Horizontale?");
				orientation = sc.nextInt();
			}
			orientation = orientation -1;
			pions.add(new Bateau(caseO, orientation, longueur));
		}
		return pions;
	}

	/* renvoie liste de toutes les cases occupees par un bateau */
	public ArrayList<Integer> caseBateaux(ArrayList<Bateau> l){
		ArrayList<Integer> listeCase = new ArrayList<Integer>();
		for(Bateau o : l) {
			int caseO = o.getCaseOrigine();
			int orientation = o.getOrientation();
			int longueur = o.getLongueur();
			listeCase.add(caseO);
			if(orientation == 0) {
				for(int x = 1; x<longueur; x++) {
					listeCase.add(caseO+10*x);
				}
			}
			else {
			for(int x = 1; x<longueur; x++) {
				listeCase.add(caseO+x);
			}
		}
	}
	return listeCase;
	}

	/* methode verifiant si la position des bateaux est valide A REFAIRE */
	public boolean positionValide(int position, int orientation, int longueur) {
		if(orientation == 0){
			if(position > 100-(longueur*10)) {
				return false;
			}
		}
		else {
			if(position > 100-longueur) {
				return false;
			}
		}
		return true;
	}

	public boolean listeBateauValide(ArrayList<Integer> l) {
		if(l.size() != 17) {
			return false;
		}
		for(int c : l) {
			if(c<0 || c>99) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Integer> choisirBateaux(){
		ArrayList<Bateau> pions = new ArrayList<Bateau>();
		ArrayList<Integer> listeCase = new ArrayList<Integer>();
		do {
			pions = this.creerBateaux();
			listeCase = this.caseBateaux(pions);
		}while(!this.listeBateauValide(listeCase));
		return listeCase;
	}

}
