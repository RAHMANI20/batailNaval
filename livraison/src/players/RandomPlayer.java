package players;
import java.util.*;

import games.BatailNaval;
import games.Game;

public class RandomPlayer implements Player{
	
	private Random rand;

	public RandomPlayer(Random rand){
		this.rand = rand;
	}

	/* methode qui renvoie un coup a jouer */
	public int chooseMove (Game game){
		ArrayList<Integer> move = game.validMoves();
		int indice = rand.nextInt(move.size());
		return move.get(indice);
	}
	public int chooseMove (BatailNaval game){
		ArrayList<Integer> move = game.validMoves();
		int indice = rand.nextInt(move.size());
		return move.get(indice);
	}

	/* renvoie le nom du joueur */
	public String toString (){
    	return "Joueur aleatoire n " + this.hashCode();
  	}
	
	
	public ArrayList<Integer> choisirBateaux(){
		ArrayList<Integer> listeCase = new ArrayList<Integer>();
		Random random = new Random();
		int [] tailleB = {2, 3, 3, 4, 5};
		for(int i = 0; i<tailleB.length; i++) {
			int orientation = random.nextInt(2);
			int taille = tailleB[i];
			int position;
			do {
				position = random.nextInt(100-taille);
			}while(this.positionValide(position, orientation, taille)==false || this.bateauValide(position, orientation, taille, listeCase)==false);
			listeCase = this.ajouterBateau(position, orientation, taille, listeCase);
		}
		return listeCase;
	}
	
	public boolean positionValide(int p, int o, int t) {
		int positionD = p;
		if(o == 0) {
			int positionF = positionD+t-1;
			int x = positionD/10;
			int a = positionF/10;
			
			if(x == a) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			int positionF = positionD+(10*(t-1));
			int x = positionD/10;
			int y = positionD - x*10;
			int a = positionF/10;
			int b = positionF - a*10;
			
			if(a<10 && y == b) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public boolean bateauValide(int p, int o, int t,ArrayList<Integer> listeCase) {
		int positionD = p;
		if(o == 0) {
			for(int i = 0; i<t; i++) {
				p = positionD+i;
				if(listeCase.contains(p)) {
					return false;
				}
			}
			return true;
		}
		else {
			for(int i = 0; i<t; i++) {
				p = positionD+(10*i);
				if(listeCase.contains(p)) {
					return false;
				}
			}
			return true;
		}
	}
	
	public ArrayList<Integer> ajouterBateau(int p, int o, int t,ArrayList<Integer> listeCase){
		int positionD = p;
		if(o == 0) {
			for(int i = 0; i<t; i++) {
				p = positionD+i;
				listeCase.add(p);
			}
		}
		else {
			for(int i = 0; i<t; i++) {
				p = positionD+10*i;
				listeCase.add(p);
			}
		}
		return listeCase;
	}
}