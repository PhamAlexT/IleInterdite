package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import autres.Observer;
import modele.Clef;
import modele.Artefact;
import modele.Ile;
import modele.Joueur;
import modele.Objet;

public class VueInventaire extends JPanel implements Observer {

	Ile modele;
	private JLabel labelInventaire;
	private ArrayList<JLabel> joueursList;
	private final int WIDTH = 50 * modele.HAUTEUR;
    private final int HEIGHT = 40 * modele.LARGEUR;
	private final int cote = modele.LARGEUR*4;
	private final int espacement = cote/2;
	private static int marge; 


	public VueInventaire(Ile mod) throws IOException {
		this.modele = mod;
		this.setLayout(null);
		Dimension dim = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(dim);
		this.modele.addObserver(this);
		
		this.labelInventaire = new JLabel("Inventaires des joueurs");
		this.add(labelInventaire);
		Dimension size = this.labelInventaire.getPreferredSize();
		this.labelInventaire.setBounds(WIDTH/2, HEIGHT/20, size.width, size.height);
		this.labelInventaire.setVisible(true);
		marge = this.labelInventaire.getPreferredSize().width + WIDTH / 20;
		
		this.joueursList = new ArrayList<JLabel>();
		for(int i=0; i < this.modele.getJoueurs().size(); i++) {
			this.joueursList.add(new JLabel("Inventaire du joueur "+Integer.toString(i+1)));
			this.add(joueursList.get(i));
			this.joueursList.get(i).setBounds(cote, espacement*(i+1), size.width, size.height);
		}
		
	}
	
	public void update() {
		repaint();
	}
	public void paintInventaireJoueur(Graphics g, Joueur j) {
		int nbJ = modele.getJoueurs().indexOf(j);
		if (!j.getInventaire().isEmpty()) {
			for (int i = 0; i < j.getInventaire().size();i++) {
				Objet o = j.getInventaire().get(i);
				String chemin = "res/"+o.getClass().getSimpleName()+"/"+o.getElement().toString()+".png";
				try {
					Image img = ImageIO.read(new File(chemin));
					g.drawImage(img,60+32*i,32+nbJ*32,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		for (Joueur j:this.modele.getJoueurs()) {
			paintInventaireJoueur(g,j);
		}

	}
	
	public void paint(Graphics g) {
		super.paint(g);
	
	}
	
	
}

