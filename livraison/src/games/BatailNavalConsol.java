package games;
import players.Bateau;
import players.Player;
import java.util.ArrayList;
public class BatailNavalConsol extends AbstractGame{
        
          protected Player[][] plateauJoueur;
          protected Player[][] plateauAdversaire;
          protected ArrayList<Integer> bateauxJoueur;
          protected ArrayList<Integer> bateauxAdversaire;
          protected ArrayList<Integer> coupJoueur;
          protected ArrayList<Integer> coupAdversaire;
 
          /*Initialise les deux plateaux( du joueur et de l'adversaire) ainsi que l'emplacement des bateaux*/
          public BatailNavalConsol(Player player1, Player player2){
            super(player1, player2);
            plateauJoueur = new Player[10][10];
            plateauAdversaire = new Player[10][10];
            System.out.println(this.situationToString());
            this.bateauxJoueur = (ArrayList<Integer>) player1.choisirBateaux();
            this.bateauxAdversaire = (ArrayList<Integer>) player2.choisirBateaux();
            this.coupJoueur = new ArrayList<Integer>();
            this.coupAdversaire = new ArrayList<Integer>();
            for(int iJ : bateauxJoueur) {
                    int x = iJ/10;
                        int y = iJ - x*10;
                        this.plateauJoueur[x][y] = player1;
            }
            for(int iA : bateauxAdversaire) {
                    int x = iA/10;
                        int y = iA - x*10;
                        this.plateauAdversaire[x][y] = player2;
            }
            
          }
          /* prends un entier en entree le transforme en coordonnee x y et modifie la case du plateau en fonction du joueur qui fait le coup */
          public void doExecute(int coup){
                  int x = coup/10;
                  int y = coup-x*10;
                  if(this.currentPlayer == this.p1) {
                	  this.coupJoueur.add(coup);
                          this.plateauAdversaire[x][y] = this.p1;
                  }
                  else {
                	  this.coupAdversaire.add(coup);
                          this.plateauJoueur[x][y] = this.p2;
                  }
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
          public boolean isOver(){
                  
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
                        int y = coup -x*10;
                    return coup+" = ("+(x+1)+","+(y+1)+")";
                  }
                  /* creer un rendu visuel du jeu dans la console */
                  public String situationToString(){
                          System.out.println("Le plateau de gauche est celui du " + this.p1.toString()+ " celui de droite est le plateau de votre adversaire");
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
                                    	if(this.bateauxJoueur.contains(this.plateauJoueur[x][y])) {
                                    		chaine += "  @  ";
                                    	}
                                    	else {
                                    		chaine += "  X  ";
                                    	}
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
                                    if(this.plateauAdversaire[x][yy] == this.p1) {
                                    	if(this.bateauxAdversaire.contains(this.plateauJoueur[x][yy])) {
                                    		chaine += "  @  ";
                                    	}
                                    	else {
                                    		chaine += "  X  ";
                                    	}
                                    }
                                    else {
                                            chaine += "  ~  ";
                                    }
                                    
                            }
                      chaine += System.lineSeparator();
                    }
                    return chaine;
                  }
}