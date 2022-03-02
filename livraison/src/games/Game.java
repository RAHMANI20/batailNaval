package games;
import players.*;
import java.util.*;

public interface Game{
  public void execute (int coup);
  public Player getCurrentPlayer();
  public boolean isValid (int coup);
  public  ArrayList<Integer> validMoves();
  public Player getWinner();
  public boolean isOver();
  public String moveToString(int coup);
  public String situationToString();
}
