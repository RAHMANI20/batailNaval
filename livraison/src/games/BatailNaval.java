package games;
import players.Bateau;
import players.Human;
import players.Player;
import players.RandomPlayer;

import java.util.ArrayList;
import java.util.Random;

public class BatailNaval extends AbstractListenableModel{
	
	  private int etat;
	  protected Player p1;
	  protected Player p2;
	  protected Player currentPlayer;
	  protected Player[][] plateauJoueur;
	  protected Player[][] plateauAdversaire;
	  protected ArrayList<Integer> bateauxJoueur;
	  protected ArrayList<Integer> bateauxAdversaire;
	  protected ArrayList<Integer> coupJoueur;
	  protected ArrayList<Integer> coupAdversaire;

	/*Initialise les deux plateaux( du joueur et de l'adversaire) ainsi que l'emplacement des bateaux*/
	  public BatailNaval(){
		this.p1 = new Human("Joueur humain");
		this.p2 = new RandomPlayer(new Random(123));
		this.currentPlayer = p1;
	    this.etat = 0;
	    plateauJoueur = new Player[10][10];
	    plateauAdversaire = new Player[10][10];
	    this.bateauxJoueur = new ArrayList<Integer>();
	    this.bateauxAdversaire = this.p2.choisirBateaux();
	    this.coupJoueur = new ArrayList<Integer>();
	    this.coupAdversaire = new ArrayList<Integer>();
	    for(int iA : bateauxAdversaire) {
	    	int x = iA/10;
			int y = iA - x*10;
			this.plateauAdversaire[x][y] = this.p2;
	    }
	    
	  }
	
	  public void addBateauxJoueur(int e) {
		  this.bateauxJoueur.add(e);
		  
		  if(this.bateauxJoueur.size() == 17) {
			  for(int iJ : this.bateauxJoueur) {
			    	int x = iJ/10;
					int y = iJ - x*10;
					this.plateauJoueur[x][y] = this.p1;
			    }
			  this.etat = 1;
		  }
		  fireChange();
	  }

	/* prends un entier en entree le transforme en coordonnee x y et modifie la case du plateau en fonction du joueur qui fait le coup */
	  public void doExecute(int coup){
		  if(!isOver()) {
			  int x = coup/10;
			  int y = coup - x*10;
			  if(this.currentPlayer == this.p1) {
				  this.plateauAdversaire[x][y] = this.p1;
				  this.coupJoueur.add(coup);
			  }
			  else {
				  this.plateauJoueur[x][y] = this.p2;
				  this.coupAdversaire.add(coup);
			  }
			  
			  if(this.currentPlayer == this.p1){
					this.currentPlayer = this.p2;
				}
			  else{
				this.currentPlayer = this.p1;
			  }
			  
			  fireChange();
			  
			  if(!isOver()) {
				  
				  if(this.currentPlayer == this.p1) {
					  this.plateauAdversaire[x][y] = this.p1;
					  this.coupJoueur.add(this.currentPlayer.chooseMove(this));
				  }
				  else {
					  this.plateauJoueur[x][y] = this.p2;
					  this.coupAdversaire.add(this.currentPlayer.chooseMove(this));
				  }
				  
				  if(this.currentPlayer == this.p1){
						this.currentPlayer = this.p2;
					}
				  else{
					this.currentPlayer = this.p1;
				  }
				  
				  fireChange();
			  }
		  }
		  else {
			  this.etat = 2;
			  fireChange();
		  }
		  fireChange();
	  }

	/*verifie qu'un coup est valide*/
	  public boolean isValid(int coup){
		  if(this.currentPlayer == this.p1) {
			  if(this.coupJoueur.contains(coup)) {
				  return false;
			  }
			  else {
				  return true;
			  }
		  }
		  else if(this.currentPlayer == this.p2){
			  if(this.coupAdversaire.contains(coup)) {
				  return false;
			  }
			  else {
				  return true;
			  }
		  }
		  else {
			  if(coup < 0 || coup > 99){
		    	  return false;
		      }
			  else {
				  return true;
			  }
		  }
	  }

	  /* renvoie la liste des coups valides*/
	  public ArrayList<Integer> validMoves(){
	    ArrayList<Integer> move = new ArrayList<Integer>();
	    for(int coup = 0; coup < 100; coup++){
	      if( this.isValid(coup) == true){
	        move.add(coup);
	      }
	    }
	    return move;
	  }

	  /* permet de savoir si le jeu est finis ou pas */
	  public Boolean isOver(){
		  
		  if(this.coupJoueur.containsAll(this.bateauxAdversaire) || this.coupAdversaire.containsAll(this.bateauxJoueur)) {
			  return true;
		  }
		  else {
			  return false;
		  }
	  }
	  
	  /*renvoie le gagnant */
		public Player getWinner() {
			if(this.isOver() == true) {
				if(this.coupJoueur.containsAll(this.bateauxAdversaire)) {
					return this.p1;
				}
				else {
					return this.p2;
				}
			}
			else {
				return null;
			}
		}

	  /* Traduit le un coup donnee en entree en une phrase */
	  public String moveToString(int coup){
		int x = (int) coup/10;
		int y = coup % 9;
	    return coup+" = ("+(x+1)+","+(y+1)+")";
	  }

	  /* creer un rendu visuel du jeu dans la console */
	  public String situationToString(){
	    String chaine = "";
	    chaine += "    A    B    C    D    E    F    G    H    I    J    |      A    B    C    D    E    F    G    H    I    J  ";
	    chaine += System.lineSeparator();
	    for(int x = 0; x<10; x++){
	    	if(x == 9) {
	    		chaine += x+1;
	    	}
	    	else {
	    		chaine += "0"+(x+1);
	    	}
	    	for(int y = 0; y<10; y++) {
	    		if(this.plateauJoueur[x][y] == this.p1) {
	    			chaine += "  O  ";
	    		}
	    		else if(this.plateauJoueur[x][y] == this.p2) {
	    			chaine += "  X  ";
	    		}
	    		else {
	    			chaine += "  ~  ";
	    		}
	    		
	    	}
	    	if(x == 9) {
	    		chaine += "  |  "+(x+1);
	    	}
	    	else {
	    		chaine += "  |  0"+(x+1);
	    	}
	    	for(int yy = 0; yy<10; yy++) {
	    		if(this.plateauAdversaire[x][yy] == this.p2) {
	    			chaine += "  O  ";
	    		}
	    		else if(this.plateauAdversaire[x][yy] == this.p1) {
	    			chaine += "  X  ";
	    		}
	    		else {
	    			chaine += "  ~  ";
	    		}
	    		
	    	}
	      chaine += System.lineSeparator();
	    }
	    return chaine;
	  }
	
	
	public Player getP1() {
		return p1;
	}
	
	public Player getP2() {
		return p2;
	}
	
	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}

	public ArrayList<Integer> getCoupAdversaire() {
		// TODO Auto-generated method stub
		return coupAdversaire;
	}
	
	public ArrayList<Integer> getCoupJoueur() {
		return coupJoueur;
	}
	
	public ArrayList<Integer> getBateauxJoueur() {
		  return bateauxJoueur;
	  }
	  
	  public ArrayList<Integer> getBateauxAdversaire() {
			return bateauxAdversaire;
		}
	  
	  public Player[][] getPlateauJoueur() {
			return plateauJoueur;
		}

		public Player[][] getPlateauAdversaire() {
			return plateauAdversaire;
		}
		
		public int getEtat() {
			return this.etat;
		}
}
