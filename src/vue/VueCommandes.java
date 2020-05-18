package vue;

import java.awt.event.ActionEvent;


import javax.swing.JButton;
import javax.swing.JPanel;

import autres.Observer;
import modele.DeroulementPartie;
import modele.Ile;
import modele.Joueur;
import controleur.ActionsJoueurs;
import controleur.Controleur;
import controleur.DeplacementJoueur;
import controleur.RamasserArtefact;

class VueCommandes extends JPanel implements Observer{
	/**
	 * Pour que le bouton puisse transmettre ses ordres, on garde une reference au
	 * modele.
	 */
	private Ile modele;
	private DeroulementPartie liaison;
	private VueGrille vg;
	private VueJoueur vj;
	private RamasserArtefact ra;
	
	public VueCommandes(Ile modele, DeroulementPartie dp) {
		this.modele = modele;
		this.liaison = dp;
		this.ra = new RamasserArtefact(liaison.getJoueur());
		liaison.addObserver(this);
		
		JButton boutonFinDuTour = new JButton("Fin du tour");

		boutonFinDuTour.addActionListener(e -> {
			vg.removeMouseListener(liaison.getAJActuel().getDeplacementJoueur());
			vg.removeMouseListener(liaison.getAJActuel().getaZ());
			liaison.prochainJoueur();
			vg.addMouseListener(liaison.getAJActuel().getDeplacementJoueur());
			vg.addMouseListener(liaison.getAJActuel().getaZ());
			modele.avance();
			liaison.giveAleaClefs();			
			vj.update();
			
			if (this.liaison.gagne()) {
				System.out.println(" La partie est gagne !");
			}
		});
		
		JButton test = new JButton("Recuperer un artefact");
		test.addActionListener( e -> ra.donnerArtefact(liaison.getJoueur()));
		
		this.add(test);
		this.add(boutonFinDuTour);
	}
	
	// CONTROLEUR --> ILE --> JOUEUR

	public void setGrille(VueGrille vg) {
		this.vg = vg;
	}
	public void setVueJoueur(VueJoueur vj) {
		this.vj = vj;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

