package games;
import players.*;
import java.util.ArrayList;
public abstract class AbstractGame implements Game{
        protected Player p1;
        protected Player p2;
        protected Player currentPlayer;
        public AbstractGame(Player p1, Player p2){
                this.p1 = p1;
                this.p2 = p2;
                this.currentPlayer = p1;
        }
        protected abstract void doExecute(int i);
        /* appel la fonction execute du jeu et change le joueur courant */
        public void execute(int coup){
                this.doExecute(coup);
                if(this.currentPlayer == this.p1){
                        this.currentPlayer = this.p2;
                }
                else{
                        this.currentPlayer = this.p1;
                }
        }
        
        /*renvoie le joueur courant*/
        public Player getCurrentPlayer(){
                return this.currentPlayer;
        }
        public abstract ArrayList<Integer> validMoves();
        public abstract boolean isValid(int coup);
        public abstract String situationToString();
        public abstract String moveToString(int coup);
        public abstract boolean isOver();
        public abstract Player getWinner();
}