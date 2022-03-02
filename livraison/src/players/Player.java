package players;
import java.util.ArrayList;

import games.BatailNaval;
import games.Game;

public interface Player{

  public int chooseMove (Game game );
  public int chooseMove (BatailNaval game );
  public ArrayList<Integer> choisirBateaux();

}

