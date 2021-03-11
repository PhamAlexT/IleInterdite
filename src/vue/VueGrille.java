package vue;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	//Attributs
	//'modele' une ile.
	/**
	 * Pour que le bouton puisse transmettre ses ordres, on garde une reference au
	 * modele.
	 */
	private Ile modele;
	//'liaison' une partie.
	private DeroulementPartie liaison;
	//'TAILLE' un entier correspondant au cote du carre: une case fait 32*32 pixels
	private final static int TAILLE = 32;
	//'aj' une ActionsJoueurs representant les actions possibles des joueurs.
	private ActionsJoueurs aj;
		
	//Constructeur
		
	/**VueGrille() :
	 * 		Ile modele : une ile.
	 * 		DeroulementPartie dp : une partie.
	 * 		 Construction d'une vue de la grille attachee a un modele.
	 **/
	public VueGrille(Ile modele, DeroulementPartie dp) {
		this.modele = modele;
		this.liaison = dp;
		modele.addObserver(this);
		liaison.addObserver(this);
		
		this.addMouseListener(liaison.getAJActuel().getDeplacementJoueur());
		this.addMouseListener(liaison.getAJActuel().getaZ());
		//Definition et application d'une taille fixe pour cette zone de l'interface, calculee en fonction du nombre de cellules et de la taille d'affichage.
		Dimension dim = new Dimension(TAILLE * Ile.LARGEUR, TAILLE * Ile.HAUTEUR);
		this.setPreferredSize(dim);
	}

	//Methodes
	public void update() {
		repaint();
	}

	/**void paintComponent() :
	 * 		Graphics g : un graphique.
	 * 
	 * 		Paint les zones, les joueurs et l'heliport sur la grille.
	**/
	public void paintComponent(Graphics g) {
		super.repaint();
		for (int i = 1; i <= Ile.LARGEUR; i++) {
			for (int j = 1; j <= Ile.HAUTEUR; j++) {
				try {
					paint(g, modele.getZone(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
				} catch (AccesHorsIle e) {
					e.printStackTrace();
				}
			}
				for (Joueur j : this.modele.getJoueurs()) {
				try {
					paintJoueur(g, j);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			//Case pour l'heliport
			try {
				paintHeliport(g, modele.getHeli());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** Une fonction utile pour la fonction paint */
	/**Color getColorFromElement() :
	 * 		Zone z : une zone.
	 * 		Return : la couleur a associer a chaque element
	 **/
	private Color getColorFromElement(Zone z) {
		Color c = null;
		switch (z.getElement()) {
		case Air:
			c = new Color(114, 230, 184).brighter();
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
			c = Color.YELLOW;
			break;
		}
		return c;
	}

	/**void paint() :
	 * 		Graphics g : un graphique.
	 * 		Zone z : une zone.
	 * 		int x : un entier correspondant à des coordonnées dans la grille (pixel)
	 * 		int y : un entier correspondant à des coordonnées dans la grille (pixel)
	 * 		Paint les zones de la grille. Leur couleur ainsi que leur situation.
	 **/
	private void paint(Graphics g, Zone z, int x, int y) {
		if (!liaison.getAJActuel().estLibre()) {
			this.removeMouseListener(liaison.getAJActuel().getDeplacementJoueur());
			this.removeMouseListener(liaison.getAJActuel().getaZ());
		}
			Color c = getColorFromElement(z);
		g.setColor(c);
		g.fillRect(x, y, TAILLE, TAILLE);
		
		if(z.estInondee()) {
			try {
				Image imgEtat = ImageIO.read(new File("res/EtatInondee.png"));
				g.drawImage(imgEtat, (z.getX() - 1) * TAILLE, (z.getY() - 1) * TAILLE, this);
			} catch (Exception e) {
			}
		}
		
		if(z.estSubmergee()) {
			try {
				Image imgEtat = ImageIO.read(new File("res/EtatSubmergee.png"));
				g.drawImage(imgEtat, (z.getX() - 1) * TAILLE, (z.getY() - 1) * TAILLE, this);
			} catch (Exception e) {
				
			}
		}
	}
		
	/**void paintJoueur() :
	 * 		Graphics g : un graphique.
	 * 		Joueur j : un joueur.
	 * 		Paint les joueurs de la partie.
	 **/
	public void paintJoueur(Graphics g, Joueur j) throws IOException {
		Image imgJoueur = ImageIO.read(new File("res/Player" + Integer.toString(modele.getNbJoueur(j)+1) + ".png"));
		g.drawImage(imgJoueur, (j.getZone().getX() - 1) * TAILLE, (j.getZone().getY() - 1) * TAILLE, this);
	}
	
	/**void paintHeliport() :
	 * 		Graphics g : un graphique.
	 * 		Zone z : une zone.
	 * 		Paint l'héliport
	 **/
	public void paintHeliport(Graphics g, Zone z) throws IOException {
		Image imgHeliport = ImageIO.read(new File("res/heliport.png"));
		g.drawImage(imgHeliport, (z.getX() - 1) * TAILLE, (z.getY() - 1) * TAILLE, this);
	}
	
}


