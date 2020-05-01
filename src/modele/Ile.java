package modele;

import java.util.ArrayList;
import java.util.Random;

import autres.Observable;
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

	public void initJoueurs() {
		Joueur p = new Joueur(this, zones[1][1]);
		joueurs.add(p);
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

	public void deplacementJoueur(Joueur j, Zone nZ) {
		if (!nZ.estSubmergee() && j.getZone().estAdjacente(nZ)) {
			j.seDeplace(nZ);
			notifyObservers();
		}
	}
	
	public void assecher(Joueur j, Zone zV) {
		
	}
	
	public void recupererArtefact() {
		
	}

	/**
	 * Une méthode pour renvoyer la zone aux coordonnées choisies (sera utilisée par
	 * la vue).
	 */
	public Zone getZone(int x, int y) {
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
