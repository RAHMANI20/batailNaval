package plays;
import games.*;
import players.*;
import main.*;
import java.util.*;
public class Orchestrator{
protected Game game;
 public Orchestrator (Game game){
   this.game=game;
 }
 public void play(){
     //Scanner sc = new Scanner(System.in);
     Game game = this.game;
     
     System.out.println();
     System.out.println(game.situationToString());
            while (game.isOver() == false){
         Player p = game.getCurrentPlayer();
         System.out.println();
         System.out.println(game.getCurrentPlayer());
         System.out.println("C'est au tour de "+game.getCurrentPlayer().toString()+" de jouer.");
                int coup = game.getCurrentPlayer().chooseMove(game);
                if(game.isValid(coup)){
                        game.execute(coup);
                }
         else{
           System.out.println ("coup invalide");
         }
                      System.out.println(game.situationToString());
         if(game.getWinner() != null){
               System.out.println(game.getWinner().toString()+" a gagn?!!!");
         }
         else if(game.getWinner() == null && game.isOver() != false){
            System.out.println("Match nul!");
         }
     }
 }
}
/* classe qui fait d?rouler le jeu, fait appel a toutes les fonctions du jeux dans le bon ordre jusqu'a ce que le jeu soit termin? */