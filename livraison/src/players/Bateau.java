package players;

public class Bateau {

	protected int caseOrigine;
	protected int orientation;
	protected int longueur;
	
	public Bateau(int origine, int orientation, int longueur){
		this.caseOrigine = origine;
		this.orientation = orientation;
		this.longueur = longueur;
	}

	public int getCaseOrigine() {
		return caseOrigine;
	}

	public void setCaseOrigine(int caseOrigine) {
		this.caseOrigine = caseOrigine;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
}

/*Classe qui defini un bateau avec sa position d'origine c'est a dire la case ou est situe le bout du bateau
 * son orientation verticale 0 / horizontale 1
 * sa longueur */
