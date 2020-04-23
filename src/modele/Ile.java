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
	public void init() {
		for (int i = 1; i <= LARGEUR; i++) {
			for (int j = 1; j <= HAUTEUR; j++) {
				zones[i][j].situation = Situation.Normale;
				zones[i][j].setElement(Element.values()[generateur.nextInt(Element.values().length)]);
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
		
		for(Zone z:zoneAModif) {
			zones[z.getX()][z.getY()].progresse();
		}
		
		notifyObservers();
	}

	/**
	 * Méthode auxiliaire : compte le nombre de voisines vivantes d'une zone
	 * désignée par ses coordonnées.
	 */
	protected int compteVoisines(int x, int y) {
		int res = 0;
		/**
		 * Stratégie simple à écrire : on compte les zones vivantes dans le carré 3x3
		 * centré autour des coordonnées (x, y), puis on retire 1 si la zone centrale
		 * est elle-même vivante. On n'a pas besoin de traiter à part les bords du
		 * tableau de zones grâce aux lignes et colonnes supplémentaires qui ont été
		 * ajoutées de chaque côté (dont les zones sont mortes et n'évolueront pas).
		 */
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (zones[i][j].etat) {
					res++;
				}
			}
		}
		return (res - ((zones[x][y].etat) ? 1 : 0));
		/**
		 * L'expression [(c)?e1:e2] prend la valeur de [e1] si [c] vaut [true] et celle
		 * de [e2] si [c] vaut [false]. Cette dernière ligne est donc équivalente à int
		 * v; if (zones[x][y].etat) { v = res - 1; } else { v = res - 0; } return v;
		 */
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
