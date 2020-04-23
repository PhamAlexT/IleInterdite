package modele;

import autres.Observable;

public class Ile extends Observable {
	/** On fixe la taille de la grille. */
	public static final int HAUTEUR = 40, LARGEUR = 60;
	/** On stocke un tableau de zones. */
	private Zone[][] zones;

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
		init();
	}

	/**
	 * Initialisation aléatoire des zones, exceptées celle des bords qui ont été
	 * ajoutés.
	 */
	public void init() {
		for (int i = 1; i <= LARGEUR; i++) {
			for (int j = 1; j <= HAUTEUR; j++) {
				if (Math.random() < .2) {
					zones[i][j].etat = true;
				}
			}
		}
	}

	/**
	 * Calcul de la génération suivante.
	 */
	public void avance() {
		/**
		 * On procède en deux étapes. - D'abord, pour chaque zone on évalue ce que
		 * sera son état à la prochaine génération. - Ensuite, on applique les
		 * évolutions qui ont été calculées.
		 */
		for (int i = 1; i < LARGEUR + 1; i++) {
			for (int j = 1; j < HAUTEUR + 1; j++) {
				zones[i][j].evalue();
			}
		}
		for (int i = 1; i < LARGEUR + 1; i++) {
			for (int j = 1; j < HAUTEUR + 1; j++) {
				zones[i][j].evolue();
			}
		}
		/**
		 * Pour finir, le modèle ayant changé, on signale aux observateurs qu'ils
		 * doivent se mettre à jour.
		 */
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
	 * Une méthode pour renvoyer la zone aux coordonnées choisies (sera utilisée
	 * par la vue).
	 */
	public Zone getZone(int x, int y) {
		return zones[x][y];
	}
}
