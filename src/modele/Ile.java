package modele;

import java.util.ArrayList;
import java.util.Random;

import autres.Observable;
import controleur.ActionsJoueur;
import modele.Zone.Element;

public class Ile extends Observable {
	/** On fixe la taille de la grille. */
	public static final int HAUTEUR = 20, LARGEUR = 20;
	/** On stocke un tableau de zones. */
	private Zone[][] zones;
	private Random generateur;

	private ArrayList<Joueur> joueurs;

	/** Construction : on initialise un tableau de zones. */
	public Ile() {
		/**
		 * Pour éviter les problèmes aux bords, on ajoute une ligne et une colonne de
		 * chaque côté, dont les zones n'évolueront pas.
		 */
		zones = new Zone[LARGEUR + 2][HAUTEUR + 2];
		for (int i = 0; i < LARGEUR + 2; i++) {
			for (int j = 0; j < HAUTEUR + 2; j++) {
				zones[i][j] = new Zone(this, i, j);
			}
		}
		this.generateur = new Random();
		this.joueurs = new ArrayList<Joueur>();
		init();
	}

	public class AccesHorsIle extends Exception{
		public AccesHorsIle() {
			System.out.println("Tentative d'accès à une zone hors de l'île");
		}
	}
	/**
	 * Initialisation aléatoire des zones, exceptées celle des bords qui ont été
	 * ajoutés.
	 */
	public void init() {
		initCellules();
		initJoueurs();
	}

	public void initCellules() {
		ArrayList<int[]> casesSpe = new ArrayList<int[]>();
		while (casesSpe.size() < 4) {
			int[] c = { generateur.nextInt(LARGEUR) + 1, generateur.nextInt(HAUTEUR) + 1 };
			if (!casesSpe.contains((int[]) c))
				casesSpe.add(c);
		}

		int iElem = 0;

		// A optimiser
		for (int i = 1; i <= LARGEUR; i++) {
			for (int j = 1; j <= HAUTEUR; j++) {
				zones[i][j].setElement(Element.Neutre);
				for (int[] c : casesSpe) {
					if (c[0] == i && c[1] == j) {
						zones[i][j].setElement(Element.values()[iElem]);
						iElem = iElem + 1;
					}
				}
			}
		}
	}

	//Il faudrait les placer de manière aléatoire sur la map quand on aura le temps
	public void initJoueurs() {
		Joueur p1 = new Joueur(this, zones[1][1]);
		joueurs.add(p1);
		Joueur p2 = new Joueur(this, zones[1][2]);
		joueurs.add(p2);
	}

	/**
	 * Calcul du tour suivant
	 */
	public void avance() {
		ArrayList<Zone> zoneNonSubmergee = new ArrayList<Zone>();

		for (int i = 1; i <= LARGEUR; i++) {
			for (int j = 1; j <= HAUTEUR; j++) {
				Zone zTraite = zones[i][j];
				if (!zTraite.estSubmergee())
					zoneNonSubmergee.add(zTraite);
			}
		}

		ArrayList<Zone> zoneAModif = new ArrayList<Zone>();

		while (zoneAModif.size() < 3) {
			Zone z = zoneNonSubmergee.get(generateur.nextInt(zoneNonSubmergee.size()));
			if (!zoneAModif.contains(z))
				zoneAModif.add(z);
		}

		for (Zone z : zoneAModif) {
			zones[z.getX()][z.getY()].progresse();
		}

		notifyObservers();
	}

	public void deplacementJoueur(Joueur j, Zone nZ) throws AccesHorsIle {
		if (nZ.x<1 || nZ.x> LARGEUR || (nZ.y<1 || nZ.y> HAUTEUR)) throw new AccesHorsIle();
		if (!nZ.estSubmergee() && j.getZone().estAdjacente(nZ)) {
			if(j.action() == true) {
				j.seDeplace(nZ);
			}else {
				System.out.println("Le joueur n'a plus d'action disponible !");
			}
			notifyObservers();
		}
	}
	
	public void assecher(Joueur j, Zone zV) {
		
	}
	
	public void recupererArtefact(Joueur j) {
		j.recupereArtefact(null);
	}

	/**
	 * Une méthode pour renvoyer la zone aux coordonnées choisies (sera utilisée par
	 * la vue).
	 * @throws AccesHorsIle 
	 */
	public Zone getZone(int x, int y) throws AccesHorsIle {
		if (x<1 || x> LARGEUR || (y<1 || y> HAUTEUR)) throw new AccesHorsIle();
		return zones[x][y];
	}

	public String toString() {
		return String.format("Infos Iles: \nHauteur: %d\nLargeur: %d%nNombre de joueurs: %d", HAUTEUR, LARGEUR,
				this.joueurs.size());
	}

	public ArrayList<Joueur> getJoueurs() {
		return this.joueurs;
	}
}

