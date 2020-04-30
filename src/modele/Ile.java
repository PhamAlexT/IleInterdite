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
		init();
	}

	/**
	 * Initialisation aléatoire des zones, exceptées celle des bords qui ont été
	 * ajoutés.
	 */
	/**
	 * On va rajouter la zone d'air, d'eau, de terre et enfin feu.
	 */

	public void init() {

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
				for (int[] c:casesSpe) {
					if (c[0]==i && c[1]==j){
						zones[i][j].setElement(Element.values()[iElem]);
						iElem = iElem+1;
					}
				}
			}
		}
	}

	/**
	 * Calcul du tour suivant
	 */
	public void avance() {
		System.out.println("On avance petit à petit");
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

	/**
	 * Une méthode pour renvoyer la zone aux coordonnées choisies (sera utilisée par
	 * la vue).
	 */
	public Zone getZone(int x, int y) {
		return zones[x][y];
	}

	public String toString() {
		return String.format("Hauteur: %d\nLargeur: %d%n", this.HAUTEUR, this.LARGEUR);
	}
}
