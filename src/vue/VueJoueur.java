package vue;

import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


import autres.Observer;
import controleur.RamasserArtefact;
import modele.DeroulementPartie;
import modele.Ile;

public class VueJoueur extends JPanel implements Observer  {
	//Attributs
	//'modele' une ile.
	/**
	* Pour que le bouton puisse transmettre ses ordres, on garde une reference au
	* modele.
	*/
	Ile modele;
	//'WIDTH' un entier correspondant a une largeur.
	private final int WIDTH =  60 * modele.HAUTEUR;
	//'HEIGHT' un entier correspondant a une hauteur.
    private final int HEIGHT = 8 * modele.LARGEUR;
  //'liaison' une partie.
	DeroulementPartie dp;
	
	JLabel labelJoueur; //  Afficher les informations du joueur
	JLabel labelTourRestant;
	// Afficher les informations de la zone actuel du joueur
	JLabel labelCoordZone;
	JLabel labelSituationZone;
	JLabel labelElementZone;
	//info commandes
	JLabel info1;
	JLabel info2;
	JLabel info3;
	//Constructeur
	
	/**VueJoueur() :
	 * 		DeroulementPartie dp : une partie.
	 * 		 Construction d'une vue du joueur en train de jouer attachee a un modele.
	 **/
	public VueJoueur(DeroulementPartie dp) {
		this.dp = dp;
		dp.addObserver(this);
		modele = dp.getIle();
		
		// Reglage de la vue
		Dimension dim = new Dimension(WIDTH, HEIGHT);

		this.setPreferredSize(dim);
		
		// Reglage de la vue du joueur
		labelJoueur = new JLabel("Joueur " + Integer.toString(dp.getindiceJoueurModulo()+1));
		labelJoueur.setPreferredSize(new Dimension(WIDTH,HEIGHT/9));
		
		labelTourRestant = new JLabel("Nombre de tours restants: " + dp.getActionsRestantes());
		labelTourRestant.setPreferredSize(new Dimension(WIDTH,HEIGHT/9));
		
		//Info sur la zone du joueur
		labelCoordZone = new JLabel("Abscisse = " +dp.getJoueur().getZone().getX() + " Ordonnee = " + dp.getJoueur().getZone().getY());
		labelCoordZone.setPreferredSize(new Dimension(WIDTH,HEIGHT/9));
		
		labelSituationZone = new JLabel("Situation : " + dp.getJoueur().getZone().getSituation().name()); 
		labelSituationZone.setPreferredSize(new Dimension(WIDTH,HEIGHT/9));
		
		labelElementZone = new JLabel("Element: "+this.dp.getJoueur().getZone().getElement().name());
		labelElementZone.setPreferredSize(new Dimension(WIDTH,HEIGHT/9));
		
        String espace = "                               ";
		info1 = new JLabel(espace + "Clic gauche sur une case : on avance");
		info2 = new JLabel(espace + "Clic droit sur une case \"innondee\" : on assseche une zone");
		info3 = new JLabel(espace + "Nombre de cle minimum pour recuperer un artefact : " + RamasserArtefact.getNbClefs());
		info1.setPreferredSize(new Dimension(WIDTH,HEIGHT/9));
		info2.setPreferredSize(new Dimension(WIDTH,HEIGHT/9));
		info3.setPreferredSize(new Dimension(WIDTH,HEIGHT/13));

		
		//On ajoute tout
		this.add(info1, BorderLayout.PAGE_START);
		this.add(info2, BorderLayout.LINE_START);
		this.add(info3, BorderLayout.LINE_START);
		this.add(labelJoueur,BorderLayout.LINE_START);
		this.add(labelTourRestant,BorderLayout.LINE_START);
		this.add(labelCoordZone,BorderLayout.LINE_START);
		this.add(labelSituationZone,BorderLayout.LINE_START);
		this.add(labelElementZone,BorderLayout.LINE_START);
		
	}
	
	//Methodes
	@Override
	public void update() {
		paint();
	}
	
	/**void paint() :
	 * 		Paint sur l'interface la joueur en train de jouer, les actions qui lui restent, les coordonnees de la zone ou
	 * 		il se trouve, la situation de la zone ou il se trouve ainsi que l'element de la zone ou il se trouve.
	 * 		Rafraichit les informations affiches à l'ecran.
	 **/
	public void paint() {
		labelJoueur.setText("Joueur " + Integer.toString(dp.getindiceJoueurModulo()+1));
		labelTourRestant.setText("Nombre de tours restants: " + dp.getActionsRestantes());
		labelCoordZone.setText("Abscisse = " +dp.getJoueur().getZone().getX() + " Ordonnee = " + dp.getJoueur().getZone().getY());
		labelSituationZone.setText("Situation : " + dp.getJoueur().getZone().getSituation().name());
		labelElementZone.setText("Element: "+this.dp.getJoueur().getZone().getElement().name());
	}	
}
