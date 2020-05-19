package vue;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import autres.Observer;
import modele.Clefs;
import modele.Artefact;
import modele.Ile;

public class VueInventaire extends JPanel implements Observer {

	Ile modele;
	private JLabel labelInventaire;
	private ArrayList<JLabel> joueursList;
	private final int WIDTH = 50 * modele.HAUTEUR;
    private final int HEIGHT = 40 * modele.LARGEUR;
	private final int cote = modele.LARGEUR*4;
	private final int espacement = cote/2;
	private static int marge; 
	
	private BufferedImage cleEau = ImageIO.read(new File("res/Waterkey.png"));
    private BufferedImage cleFeu = ImageIO.read(new File("res/Firekey.png"));
    private BufferedImage cleAir = ImageIO.read(new File("res/Windkey.png"));
    private BufferedImage cleTerre = ImageIO.read(new File("res/Earthkey.png"));
    
    private BufferedImage artEau = ImageIO.read(new File("res/Waterart.png"));
    private BufferedImage artFeu = ImageIO.read(new File("res/Fireart.png"));
    private BufferedImage artWind = ImageIO.read(new File("res/Windart.png"));
    private BufferedImage artTerre = ImageIO.read(new File("res/Earthart.png"));


	
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
		this.joueursList = new ArrayList();
		for(int i=0; i < this.modele.getJoueurs().size(); i++) {
			this.joueursList.add(new JLabel("Inventaire du joueur "+i));
			this.add(joueursList.get(i));
			this.joueursList.get(i).setBounds(cote, espacement*(i+1), size.width, size.height);
		}
		
	}
	
	public void update() {
		repaint();
	}
	
	/**public void paint() {
		this.getParent().repaint();
		for(int i=0; i < this.modele.getJoueurs().size(); i++) {
			for(int j=0; j < modele.getJoueurs().get(i).getNbObjet(); j++) {
				ArrayList stuff = new ArrayList(modele.getJoueurs().get(i).getInventaire());
				//Clefs
				if(stuff.get(j) instanceof Clefs) {
				switch (((Clefs) stuff.get(j)).getElement()) {
                case Eau:
                    
                    
                    break;
                case Terre:
                    
                    break;
                case Feu:
                    
                    
                    break;
                case Air:
                    
                    break;
				}
				}
				//Artefacts
				if(stuff.get(j) instanceof Artefact) {
				switch (((Clefs) stuff.get(j)).getElement()) {
					case Eau:
                
                
					break;
					case Terre:
                
					break;
					case Feu:
                
          
					break;
					case Air:
                
					break;
				}
				}
			}
		}
	}**/
	
	
}

