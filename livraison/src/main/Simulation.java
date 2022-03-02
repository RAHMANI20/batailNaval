package main;
import players.*;
import plays.Orchestrator;
import games.*;
import java.util.*;
public class Simulation{
  public static void main(String[] args){
          
        Scanner sc = new Scanner(System.in);
        Player player1 = new Human("Joueur Humain");
    Random rand = new Random(123);
    Player player2 = new RandomPlayer(rand);
    Game game = new BatailNavalConsol(player1, player2);
    Orchestrator orchestrator = new Orchestrator(game);
    orchestrator.play();
    System.out.println();
    System.out.println("Voulez-vous rejouer ?(oui/non)");
    String rep;
    do{
      rep = sc.nextLine();
    }
    while(!(rep.equalsIgnoreCase("oui")) && !(rep.equalsIgnoreCase("non")));
    if(rep.equalsIgnoreCase("oui")){
       main(args);
    }
    else{System.out.println("A bient?t.");}
          sc.close();
  }
}
/* cette classe initialise une instance de la classe du jeu en lui passant en param?tre deux joueurs cr?er au pr?alable */