package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import games.BatailNaval;
import main.Controleur;
import players.Player;

public class Panel extends JPanel implements ModelListener, MouseListener{

	private int etat;
	private int [] tailleB = {2, 3, 3, 4, 5};
	private final static int ORIGINE = 50;
	private BatailNaval jeu;
	private Controleur controleur;
	private final static Color[] COLORS = { Color.BLUE, Color.GREEN, Color.RED };
	private ArrayList<Integer> bateau;
	private int taille;
	
	  private Image bateau2;
	  private Image bateau3;
	  private Image bateau4;
	  private Image bateau5;
	  private Image bateau22;
	  private Image bateau33;
	  private Image bateau44;
	  private Image bateau55;
	  private Image croixR;
	  private Image croixV;
	  private Image explosion;
	  private Image grille;
	  private Image victoireJ;
	  private Image victoireA;
	  private int orientation;

	public Panel(Controleur c, BatailNaval jeu) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream  i1 = classLoader.getResourceAsStream("grille.jpg");
		InputStream i2 =classLoader.getResourceAsStream("explosion.png");
		InputStream i3 = classLoader.getResourceAsStream("croixV.png");
		InputStream i4 = classLoader.getResourceAsStream("croixR.png");
		InputStream i5 = classLoader.getResourceAsStream("bateau5.png");
		InputStream i6 = classLoader.getResourceAsStream("bateau4.png");
		InputStream i7 = classLoader.getResourceAsStream("bateau3.png");
		InputStream i8 = classLoader.getResourceAsStream("bateau22.png");
		InputStream i9 = classLoader.getResourceAsStream("bateau55.png");
		InputStream i10 = classLoader.getResourceAsStream("bateau44.png");
		InputStream i11 = classLoader.getResourceAsStream("bateau33.png");
		InputStream i12 = classLoader.getResourceAsStream("bateau2.png");
		InputStream i13 = classLoader.getResourceAsStream("victoireJ.png");
		InputStream i14 = classLoader.getResourceAsStream("victoireA.png");
		try {
			this.grille = ImageIO.read(i1);
			this.explosion = ImageIO.read(i2);
			this.croixV = ImageIO.read(i3);
			this.croixR = ImageIO.read(i4);
			this.bateau5 = ImageIO.read(i5);
			this.bateau4 = ImageIO.read(i6);
			this.bateau3 = ImageIO.read(i7);
			this.bateau2 = ImageIO.read(i8);
			this.bateau55 = ImageIO.read(i9);
			this.bateau44 = ImageIO.read(i10);
			this.bateau33 = ImageIO.read(i11);
			this.bateau22 = ImageIO.read(i12);
			this.victoireJ = ImageIO.read(i13);
			this.victoireA = ImageIO.read(i14);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.jeu = jeu;
		this.controleur = c;
		this.etat = 0;
		this.addMouseListener(this);
		this.orientation = 0;
	}

	@Override
	public void modelUpdated(Object o){
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(this.jeu.getEtat() == 0) {
			this.choixDesEmplacements(g);
		}
		else if(this.jeu.getEtat() == 1){
			this.corpDuJeu(g);
		}
		else {
			this.finDuJeu(g);
		}
	}
	
	public void choixDesEmplacements(Graphics g){
		System.out.println("aaaaaaaaaa");
		double hauteur = super.getSize().getHeight();
		double largeur = super.getSize().getWidth();
		double widthP = (80*largeur)/100;
		double heigthP = (80*hauteur)/100;
		if(widthP < heigthP) {
			heigthP = widthP;
		}
		else if(widthP > heigthP) {
			widthP = heigthP;
		}
		double borderH = (hauteur-heigthP)/2;
		double borderW = (largeur-widthP)/2;
		this.taille = (int) Math.sqrt((widthP*heigthP/100));
		
		String str = null;
		if(this.etat >= 5) {
			str = "Appuyer sur TERMINER.";
		}
		else {
			str = "Cliquer sur les cases du bateau de longueur "+this.tailleB[this.etat]+" .";
		}
		g.drawString(str,  (int) ((largeur/2)-str.length()), (int) borderH/2);
		g.drawImage(this.grille ,(int) borderW ,(int) borderH, (int) widthP, (int) heigthP, null);
		
		this.bateau = this.jeu.getBateauxJoueur();
		
		for(int x = 0; x<10; x++) {
			for(int y = 0; y<10; y++) {
				int emplacement = (y*10)+x;
				g.drawLine((int) borderW+(x*taille), (int) borderH, (int) borderW+(x*taille), (int) borderH+(int) heigthP);
				g.drawLine((int) borderW, (int) borderH+(y*taille), (int)borderW+(int) widthP , (int) borderH+(y*taille));
				if(this.bateau != null && this.bateau.contains(emplacement)) {
					g.setColor(Color.DARK_GRAY);
					g.fillOval((int) borderW+5+(x*taille), (int) borderH+5+(y*taille), taille-10, taille-10);
				}
			}
		}
		g.drawLine((int) borderW+(10*taille), (int) borderH, (int) borderW+(10*taille), (int) borderH+(int) heigthP);
		g.drawLine((int) borderW, (int) borderH+(10*taille), (int)borderW+(int) widthP , (int) borderH+(10*taille));
	}
	
	public void corpDuJeu(Graphics g) {
		double hauteur = super.getSize().getHeight();
		double largeur = super.getSize().getWidth();
		double widthP = (80*largeur)/100;
		double heigthP = (80*hauteur)/100;
		if(widthP < heigthP) {
			heigthP = widthP;
		}
		else if(widthP > heigthP) {
			widthP = heigthP;
		}
		double borderH = (hauteur-heigthP)/2;
		double borderW = (largeur-(2*widthP))/3;
		this.taille = (int) Math.sqrt((widthP*heigthP/100));

		g.drawImage(this.grille ,(int) borderW, (int) borderH, (int) widthP, (int) heigthP, null);
		g.drawImage(this.grille ,2*((int) borderW)+(int)widthP, (int) borderH, (int) widthP, (int) heigthP, null);
		for(int x = 0; x<=10; x++) {
			for(int y = 0; y<=10; y++) {
				g.drawLine((int)borderW +(x*taille), (int)borderH, (int)borderW +(x*taille), (int)borderH+(int)heigthP);
				g.drawLine((int)borderW, (int)borderH+(y*taille),(int)borderW+(int)widthP, (int)borderH+(y*taille));
				g.drawLine(2*((int)borderW)+(int)widthP+(x*taille), (int)borderH, 2*((int)borderW)+(int)widthP+(x*taille), (int)borderH+(int)heigthP);
				g.drawLine(2*((int)borderW)+(int)widthP, (int)borderH+(y*taille),2*((int)borderW)+2*((int)widthP),(int)borderH+(y*taille));
			}
		}
		
		this.dessinerBateau(g);

		ArrayList<Integer> bateauJoueur = this.jeu.getBateauxJoueur();
		ArrayList<Integer> bateauAdv = this.jeu.getBateauxAdversaire();
		ArrayList<Integer> coupJ = this.jeu.getCoupJoueur();
		ArrayList<Integer> coupAdv = this.jeu.getCoupAdversaire();
		
		for(int b : coupJ) {
			int y = b/10;
			int x = b - y*10;
			if(bateauAdv.contains(b)) {
				g.drawImage(this.explosion,(int)widthP + 2*((int)borderW)+(x*taille), (int)borderH+(y*taille), taille, taille, null);
			}
			else {
				g.drawImage(this.croixV,(int)widthP + 2*((int)borderW)+(x*taille), (int)borderH+(y*taille), taille, taille, null);
			}
		}
		for(int c : coupAdv) {
			int y = c/10;
			int x = c - y*10;
			if(bateauJoueur.contains(c)) {
				g.drawImage(this.explosion,(int)borderW+5+(x*taille)-15,(int)borderH+5+(y*taille), taille-15, taille-15, null);
			}
			else {
				g.drawImage(this.croixR,(int)borderW+5+(x*taille)-15,(int)borderH+5+(y*taille), taille-15, taille-15, null);
			}
		}
		
	}
	
	public void dessinerBateau(Graphics g) {
		
		double hauteur = super.getSize().getHeight();
		double largeur = super.getSize().getWidth();
		double widthP = (80*largeur)/100;
		double heigthP = (80*hauteur)/100;
		if(widthP < heigthP) {
			heigthP = widthP;
		}
		else if(widthP > heigthP) {
			widthP = heigthP;
		}
		double borderH = (hauteur-heigthP)/2;
		double borderW = (largeur-(2*widthP))/3;
		this.taille = (int) Math.sqrt((widthP*heigthP/100));

		ArrayList<Integer> bateauJoueur = this.jeu.getBateauxJoueur();
		int y = bateauJoueur.get(0)/10;
		int x = bateauJoueur.get(0) - y*10;
		int b = bateauJoueur.get(1)/10;
		int a = bateauJoueur.get(1) - b*10;
		if(y == b) {
			g.drawImage(this.bateau2,(int)borderW+5+(x*taille),(int)borderH+5+(y*taille), taille*2-10, taille-10, null);
		}
		else {
			g.drawImage(this.bateau22,(int)borderW+5+(x*taille),(int)borderH+5+(y*taille), taille-10, taille*2-10, null);
		}
		
		y = bateauJoueur.get(2)/10;
		x = bateauJoueur.get(2) - y*10;
		b = bateauJoueur.get(4)/10;
		a = bateauJoueur.get(4) - b*10;
		if(y == b) {
			g.drawImage(this.bateau3,(int) borderW+5+(x*taille),(int) borderH+5+(y*taille), taille*3-10, taille-10, null);
		}
		else {
			g.drawImage(this.bateau33,(int) borderW+5+(x*taille),(int) borderH+5+(y*taille), taille-10, taille*3-10, null);
		}
		
		y = bateauJoueur.get(5)/10;
		x = bateauJoueur.get(5) - y*10;
		b = bateauJoueur.get(7)/10;
		a = bateauJoueur.get(7) - b*10;
		if(y == b) {
			g.drawImage(this.bateau3,(int)borderW+5+(x*taille),(int)borderH+5+(y*taille), taille*3-10, taille-10, null);
		}
		else {
			g.drawImage(this.bateau33,(int)borderW+5+(x*taille),(int)borderH+5+(y*taille), taille-10, taille*3-10, null);
		}
		
		y = bateauJoueur.get(8)/10;
		x = bateauJoueur.get(8) - y*10;
		b = bateauJoueur.get(11)/10;
		a = bateauJoueur.get(11) - b*10;
		if(y == b) {
			g.drawImage(this.bateau4,(int)borderW+5+(x*taille),(int)borderH+5+(y*taille), taille*4-10, taille-10, null);
		}
		else {
			g.drawImage(this.bateau44,(int)borderW+5+(x*taille),(int)borderH+5+(y*taille), taille-10, taille*4-10, null);
		}
		
		y = bateauJoueur.get(12)/10;
		x = bateauJoueur.get(12) - y*10;
		b = bateauJoueur.get(16)/10;
		a = bateauJoueur.get(16) - b*10;
		if(y == b) {
			g.drawImage(this.bateau5,(int)borderW+5+(x*taille),(int)borderH+5+(y*taille), taille*5-10, taille-10, null);
		}
		else {
			g.drawImage(this.bateau55,(int)borderW+5+(x*taille),(int)borderH+5+(y*taille), taille-10, taille*5-10, null);
		}
	}
	
	public void finDuJeu(Graphics g) {
		double hauteur = super.getSize().getHeight();
		double largeur = super.getSize().getWidth();
		if(this.jeu.getWinner() == this.jeu.getP1()) {
			g.drawImage(this.victoireJ,0,0,(int) largeur, (int)hauteur, null);
		}
		else {
			g.drawImage(this.victoireA,0,0,(int) largeur, (int)hauteur, null);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(this.jeu.getEtat() == 0) {
			this.ajoutEmplacementBateau(e);
		}
		else {
			this.jouerUnCoup(e);
		}
		
		repaint();
	}
	
	public void ajoutEmplacementBateau(MouseEvent e) {
		double hauteur = super.getSize().getHeight();
		double largeur = super.getSize().getWidth();
		double widthP = (80*largeur)/100;
		double heigthP = (80*hauteur)/100;
		if(widthP < heigthP) {
			heigthP = widthP;
		}
		else if(widthP > heigthP) {
			widthP = heigthP;
		}
		double borderH = (hauteur-heigthP)/2;
		double borderW = (largeur-widthP)/2;
		this.taille = (int) Math.sqrt((widthP*heigthP/100));
		
		int y = (int) ((e.getX()-borderW)/taille);
		int x = (int) ((e.getY()-borderH)/taille);
		int emplacement = (x*10)+y;
		
		this.bateau = this.jeu.getBateauxJoueur();
		
		if(!this.bateau.contains(emplacement)) {
			if(this.bateau.size() < 17) {
				if(this.bateau.size() == 2 || this.bateau.size() == 5 || this.bateau.size() == 8 || this.bateau.size() == 12 || this.bateau.size() == 0) {
					this.controleur.bateauUpdate(emplacement);
					this.orientation = 0;
				}
				else {
					if(this.orientation == 0) {
						if(emplacement == this.bateau.get(this.bateau.size()-1)-1 ) {
							this.controleur.bateauUpdate(emplacement);
						}
						else if(emplacement == this.bateau.get(this.bateau.size()-1)+1 ) {
							this.controleur.bateauUpdate(emplacement);
						}
						else if(emplacement == this.bateau.get(this.bateau.size()-1)-10 ) {
							this.controleur.bateauUpdate(emplacement);
						}
						else if(emplacement == this.bateau.get(this.bateau.size()-1)+10 ) {
							this.controleur.bateauUpdate(emplacement);
						}
						
						if(this.bateau.get(this.bateau.size()-1)-1 == this.bateau.get(this.bateau.size()-2)) {
							this.orientation = 1;
						}
						else {
							this.orientation = 2;
						}
					}
					else {
						System.out.println("111111111");
						if(this.orientation == 1) {
							if(emplacement == this.bateau.get(this.bateau.size()-1)-1 || emplacement == this.bateau.get(this.bateau.size()-1)+1 ) {
								this.controleur.bateauUpdate(emplacement);
							}
						}
						else{
							System.out.println("ppppppppppppp");
							if(emplacement == this.bateau.get(this.bateau.size()-1)-10 || emplacement == this.bateau.get(this.bateau.size()-1)+10) {
								this.controleur.bateauUpdate(emplacement);
							}
						}
					}
				}
			}
		}
		
		if(this.bateau.size() >= 12) {
			this.etat = 4;
		}
		else if(this.bateau.size() >= 8) {
			this.etat = 3;
		}
		else if(this.bateau.size() >= 5) {
			this.etat = 2;
		}
		else if(this.bateau.size() >= 2) {
			this.etat = 1;
		}
		
	}
	
	public void jouerUnCoup(MouseEvent e) {
		
		double hauteur = super.getSize().getHeight();
		double largeur = super.getSize().getWidth();
		double widthP = (80*largeur)/100;
		double heigthP = (80*hauteur)/100;
		if(widthP < heigthP) {
			heigthP = widthP;
		}
		else if(widthP > heigthP) {
			widthP = heigthP;
		}
		double borderH = (hauteur-heigthP)/2;
		double borderW = (largeur-(2*widthP))/3;
		this.taille = (int) Math.sqrt((widthP*heigthP/100));
		
		if(e.getX() >= widthP+(2*borderW) && e.getX() <= widthP+(2*borderW)+widthP) {
			if(e.getY() >= borderW && e.getY() <= borderH+heigthP) {
				int y = (int) ((e.getX()-2*borderW-widthP)/taille);
				int x = (int) ((e.getY()-borderH)/taille);
				int emplacement = (x*10)+y;
				if(!this.jeu.getCoupJoueur().contains(emplacement)) {
					this.controleur.jouerCoup(emplacement);
				}
				else {
					System.out.println("cette case est deja joue");
				}
			}
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

