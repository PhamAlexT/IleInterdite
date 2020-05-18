package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import autres.Observer;
import controleur.ActionsJoueurs;
import controleur.DeplacementJoueur;
import modele.DeroulementPartie;
import modele.Ile;
import modele.Ile.AccesHorsIle;
import modele.Joueur;
import modele.Zone;

class VueGrille extends JPanel implements Observer {
	private Ile modele;
	private DeroulementPartie liaison;
	
	private final static int TAILLE = 32;
	private ActionsJoueurs aj;
	
	public VueGrille(Ile modele, DeroulementPartie dp) {
		this.modele = modele;
		this.liaison = dp;
		modele.addObserver(this);
		liaison.addObserver(this);
		
		this.addMouseListener(liaison.getAJActuel().getDeplacementJoueur());
		this.addMouseListener(liaison.getAJActuel().getaZ());
		/**
		 * Definition et application d'une taille fixe pour cette zone de l'interface,
		 * calculee en fonction du nombre de cellules et de la taille d'affichage.
		 */
		Dimension dim = new Dimension(TAILLE * Ile.LARGEUR, TAILLE * Ile.HAUTEUR);
		this.setPreferredSize(dim);
	}

	public void update() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.repaint();
		for (int i = 1; i <= Ile.LARGEUR; i++) {
			for (int j = 1; j <= Ile.HAUTEUR; j++) {
				/**
				 * ... Appeler une fonction d'affichage auxiliaire. On lui fournit les
				 * informations de dessin [g] et les coordonnees du coin en haut a gauche.
				 */
				try {
					paint(g, modele.getZone(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
				} catch (AccesHorsIle e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (Joueur j : this.modele.getJoueurs()) {
				try {
					paintJoueur(g, j);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Case pour hlicoptere
			try {
				paintHeliport(g, modele.getHeli());
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** Une fonction utile pour la fonction paint */
	private Color getColorFromElement(Zone z) {
		Color c = null;
		switch (z.getElement()) {
		case Air:
			c = new Color(114, 230, 184);
			break;
		case Eau:
			c = new Color(44, 122, 225);
			break;
		case Terre:
			c = new Color(128, 81, 34);
			break;
		case Feu:
			c = Color.RED;
			break;
		default:
			c = Color.GRAY;
			break;
		}
		return c;
	}

	private void paint(Graphics g, Zone z, int x, int y) {
		if (!liaison.getAJActuel().estLibre()) {
			this.removeMouseListener(liaison.getAJActuel().getDeplacementJoueur());
		}
		Color c = getColorFromElement(z).brighter();

		if (z.estNormale()) {
			g.setColor(c);
		}

		if (z.estInondee()) {
			g.setColor(Color.MAGENTA);
		}

		if (z.estSubmergee()) {
			g.setColor(Color.BLACK);
		}
		/** Coloration d'un rectangle. */
		g.fillRect(x, y, TAILLE, TAILLE);
	}

	public void paintJoueur(Graphics g, Joueur j) throws IOException {
		Image imgJoueur = ImageIO.read(new File("res/Player" + j.getNb() + ".png"));
		g.drawImage(imgJoueur, (j.getZone().getX() - 1) * TAILLE, (j.getZone().getY() - 1) * TAILLE, this);
	}
	
	public void paintHeliport(Graphics g, Zone z) throws IOException {
		Image imgHeliport = ImageIO.read(new File("res/heliport.png"));
		g.drawImage(imgHeliport, (z.getX() - 1) * TAILLE, (z.getY() - 1) * TAILLE, this);
	}
}


