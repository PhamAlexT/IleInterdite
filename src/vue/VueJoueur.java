package vue;

import java.awt.BorderLayout;



import javax.swing.JLabel;
import javax.swing.JPanel;


import autres.Observer;
import modele.DeroulementPartie;

public class VueJoueur extends JPanel implements Observer  {
	DeroulementPartie dp;
	JLabel labelJoueur;
	JLabel labelTourRestant;
	JLabel nbCles;
	
	public VueJoueur(DeroulementPartie dp) {
		this.dp = dp;
		dp.addObserver(this);
		labelJoueur = new JLabel("Joueur " + Integer.toString(dp.getindiceJoueurModulo()+1));
		//labelTourRestant = new JLabel("Nombre de tours restants: " + Integer.toString(dp.getAJActuel().getnbActionsMax() - dp.getAJActuel().nbActionsUtilisees()));
		System.out.println(dp.getActionsRestantes());
		labelTourRestant = new JLabel("Nombre de tours restants: " + dp.getActionsRestantes());

		//Liste artefact
		
		//nb Cls:
		
		this.add(labelJoueur,BorderLayout.NORTH);
		this.add(labelTourRestant,BorderLayout.SOUTH);
	}
	

	@Override
	public void update() {
		paint();
	}
	
	public void paint() {
		labelJoueur.setText("Joueur " + Integer.toString(dp.getindiceJoueurModulo()+1));
		labelTourRestant.setText("Nombre de tours restants: " + dp.getActionsRestantes());

	}
	
}
