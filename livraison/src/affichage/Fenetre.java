package affichage;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import games.BatailNaval;
import main.Controleur;

import java.awt.event.*;
import java.util.ArrayList;

public class Fenetre extends JFrame
  {
    
	private Controleur controleur;
	private BatailNaval jeu;
	private Panel panel;
	private JButton intro;
	
	public JButton getIntro() {
		return intro;
	}

	public void setIntro(JButton intro) {
		this.intro = intro;
	}
	
	public Fenetre(Controleur c, BatailNaval jeu) {
		super("Bataille Navale");
		Toolkit outil = super.getToolkit();
		setLayout(new BorderLayout());
		this.setPreferredSize(outil.getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.controleur = c;
		this.jeu = jeu;
		this.panel = new Panel(this.controleur, this.jeu);
		this.jeu.addListener(this.panel);
		this.add(this.panel);
	    
		this.pack();
	    this.setVisible(true);
	}
  	
}

