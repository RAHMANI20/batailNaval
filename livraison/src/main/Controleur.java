package main;

import java.util.ArrayList;

import affichage.Fenetre;
import games.BatailNaval;

public class Controleur {

	private BatailNaval model;
	private Fenetre vue;
	
	public Controleur() {
		this.model = new BatailNaval();
		this.vue = new Fenetre(this, this.model);
	}
	
	public void bateauUpdate(int emplacement){
		this.model.addBateauxJoueur(emplacement);
	}
	
	public void jouerCoup(int c) {
		if(!this.model.getCoupJoueur().contains(c)) {
			this.model.doExecute(c);
		}
		else {
			System.out.println("cette case est deja joue");
		}
	}
}