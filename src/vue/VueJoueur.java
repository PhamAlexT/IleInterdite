package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


import autres.Observer;
import modele.DeroulementPartie;
import modele.Ile;

public class VueJoueur extends JPanel implements Observer  {
	Ile modele;
	private final int WIDTH =  60 * modele.HAUTEUR;
    private final int HEIGHT = 8 * modele.LARGEUR;
	
	DeroulementPartie dp;
	JLabel labelJoueur;
	JLabel labelTourRestant;
	JLabel labelCoordZone;
	JLabel labelSituationZone;
	JLabel labelElementZone;
	
	public VueJoueur(DeroulementPartie dp) {
		this.dp = dp;
		dp.addObserver(this);
		modele = dp.getIle();
		
		// Réglage de la vue
		Dimension dim = new Dimension(WIDTH, HEIGHT);
		System.out.println(dim);
		this.setPreferredSize(dim);
		
		// Réglage de la vue du joueur
		labelJoueur = new JLabel("Joueur " + Integer.toString(dp.getindiceJoueurModulo()+1));
		labelJoueur.setPreferredSize(new Dimension(WIDTH,HEIGHT/8 ));
		
		labelTourRestant = new JLabel("Nombre de tours restants: " + dp.getActionsRestantes());
		labelTourRestant.setPreferredSize(new Dimension(WIDTH,HEIGHT/8 ));
		
		
		//Info sur la zone du joueur
		labelCoordZone = new JLabel("x = " +dp.getJoueur().getZone().getX() + " y = " + dp.getJoueur().getZone().getY());
		labelCoordZone.setPreferredSize(new Dimension(WIDTH,HEIGHT/8));
		
		labelSituationZone = new JLabel("Situation : " + dp.getJoueur().getZone().getSituation().name()); 
		labelSituationZone.setPreferredSize(new Dimension(WIDTH,HEIGHT/8));
		
		labelElementZone = new JLabel("Element: "+this.dp.getJoueur().getZone().getElement().name());
		labelElementZone.setPreferredSize(new Dimension(WIDTH,HEIGHT/8));
		
		
		//On ajoute tout
		this.add(labelJoueur,BorderLayout.PAGE_START);
		this.add(labelTourRestant,BorderLayout.LINE_START);
		this.add(labelCoordZone,BorderLayout.LINE_START);
		this.add(labelSituationZone,BorderLayout.LINE_START);
		this.add(labelElementZone,BorderLayout.LINE_START);
	}
	

	@Override
	public void update() {
		paint();
	}
	
	public void paint() {
		labelJoueur.setText("Joueur " + Integer.toString(dp.getindiceJoueurModulo()+1));
		labelTourRestant.setText("Nombre de tours restants: " + dp.getActionsRestantes());
		labelCoordZone.setText("x = " +dp.getJoueur().getZone().getX() + "y = " + dp.getJoueur().getZone().getY());
		labelSituationZone.setText("Situation : " + dp.getJoueur().getZone().getSituation().name() + "\n test");
		labelElementZone.setText("Element: "+this.dp.getJoueur().getZone().getElement().name());
	}
	
}
