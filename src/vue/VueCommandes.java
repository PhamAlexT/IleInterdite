package vue;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import autres.Observer;
import modele.DeroulementPartie;
import modele.Ile;
import modele.Joueur;
import controleur.ActionsJoueurs;
import controleur.Controleur;
import controleur.DeplacementJoueur;
import controleur.EchangeClefs;
import controleur.RamasserArtefact;

class VueCommandes extends JPanel implements Observer {
	//Attributs
	
	//'modele' une ile.
	/**
	 * Pour que le bouton puisse transmettre ses ordres, on garde une reference au
	 * modele.
	 */
	private Ile modele;
	//'liaison' une partie.
	private DeroulementPartie liaison;
	//'vg' une VueGrille.
	private VueGrille vg;
	//'vj' une VueJoueur.
	private VueJoueur vj;
	//'ra' la capacite de ramasser un artefact.
	private RamasserArtefact ra;
	//'ea' la capacite d'echanger des clefs.
	private EchangeClefs ea;
	
	//
	private CVue cv;
	//Constructeur
	
	/**VueCommandes() :
	 * 		Ile modele : une ile.
	 * 		DeroulementPartie dp : une partie.
	 *      Construction d'une vue des commandes attachee a un modele.
    **/
	public VueCommandes(Ile modele, DeroulementPartie dp) {
		this.modele = modele;
		this.liaison = dp;
		this.ra = new RamasserArtefact(liaison.getJoueur());
		this.ea = new EchangeClefs(liaison.getJoueur());
		liaison.addObserver(this);
		JButton boutonFinDuTour = new JButton("Fin du tour");

		boutonFinDuTour.addActionListener(e -> {
			vg.removeMouseListener(liaison.getAJActuel().getDeplacementJoueur());
			vg.removeMouseListener(liaison.getAJActuel().getaZ());
			liaison.giveAleaClefs();
			liaison.prochainJoueur();
			vg.addMouseListener(liaison.getAJActuel().getDeplacementJoueur());
			vg.addMouseListener(liaison.getAJActuel().getaZ());
			modele.avance();
			
			vj.update();
			
			if (this.liaison.gagne()) {
				System.out.println(" La partie est gagne !");
				cv.ecranVictoire();
			}
			
			if (this.liaison.defaite()) {
				System.out.println(" La partie est perdue !");
				try {
					cv.ecranDefaite();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton recupArte = new JButton("Recuperer un artefact");
		recupArte.addActionListener(e -> {
				if (liaison.getAJActuel().estLibre()) {
				ra.donnerArtefact(liaison.getJoueur());
			}
		});

		JButton echangeArte = new JButton("Donner une cle");
		echangeArte.addActionListener(e -> ea.echange(liaison.getJoueur(), modele.getJoueurs()));

		this.add(echangeArte);
     	this.add(recupArte);
	    this.add(boutonFinDuTour);
	}

	//Methodes
	// CONTROLEUR --> ILE --> JOUEUR

	/**void setGrille() :
	 * 		VueGrille vg : une VueGrille.
	 * 		Donne la valeur de la VueGrille vg a l'attribut vg de la VueCommandes.
	 **/
	public void setGrille(VueGrille vg) {
		this.vg = vg;
	}

	/**void setVueJoueur() :
	 * 
	 * 		VueJoueur vj : une VueJoueur.
	 * 
	 * 		Donne la valeur de la VueJoueur vj a l'attribut vj de la VueCommandes.
	 **/
	public void setVueJoueur(VueJoueur vj) {
		this.vj = vj;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public void setVue(CVue cv) {
		this.cv = cv;
	}
}
