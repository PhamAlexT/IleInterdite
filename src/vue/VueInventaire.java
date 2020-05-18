package vue;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import autres.Observer;
import modele.Ile;

public class VueInventaire extends JPanel implements Observer {

	Ile modele;
	private JLabel labelInventaire;
	private ArrayList<JLabel> joueursList;
	private final int marge = 20*50;
	private final int espacement = 20*40;
	

	
	public VueInventaire(Ile mod) throws IOException {
		this.modele = mod;
		//this.setLayout(null);
		this.modele.addObserver(this);
		this.labelInventaire = new JLabel("Inventaires des joueurs");
		this.add(labelInventaire);
		this.labelInventaire.setVisible(true);
		Dimension size = this.labelInventaire.getPreferredSize();
		this.joueursList = new ArrayList();
		for(int i=0; i < this.modele.getJoueurs().size(); i++) {
			this.joueursList.add(new JLabel("Inventaire du joueur "+i));
			this.add(joueursList.get(i));
			this.joueursList.get(i).setBounds(marge, espacement*(i+1), size.width, size.height);
		}
		
	}
	
	public void update() {
		repaint();
	}
	
	/***public void paint() {
		this.getParent().repaint();
		for(int i=0; i < this.modele.getJoueurs().size(); i++) {
			for(int j=0; j < modele.getJoueurs().get(i).getNbCles(); j++) {
				
			}
		}
	}***/

}
