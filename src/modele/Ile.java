package modele;

import java.util.ArrayList;

import java.util.Random;

import autres.Observable;
import modele.Ile.AccesHorsIle;

public class Ile extends Observable {
	/** On fixe la taille de la grille. */
	public static final int HAUTEUR = 15, LARGEUR = 15; //20
	//Nombre de joueur
	public static final int nbJoueur = 4;
	//Nb artefact
	public static final int nbArtefact = 4;
	/** On stocke un tableau de zones. */
	private Zone[][] zones;
	private Random generateur;
	private Zone heli;
	private ArrayList<Joueur> joueurs;

	/** Construction : on initialise un tableau de zones. */
	public Ile() {
		/**
		 * Pour eviter les problemes aux bords, on ajoute une ligne et une colonne de
		 * chaque cote, dont les zones n'evolueront pas.
		 */
		zones = new Zone[LARGEUR + 2][HAUTEUR + 2];
		for (int i = 0; i < LARGEUR + 2; i++) {
			for (int j = 0; j < HAUTEUR + 2; j++) {
				zones[i][j] = new Zone(this, i, j);
			}
		}
		this.generateur = new Random();
		this.joueurs = new ArrayList<Joueur>();
		
		//Alea de l'aeroport
		int caseAleaL = (int) Math.floor(Math.random() * (LARGEUR-1) +1);
		int caseAleaH = (int) Math.floor(Math.random() * (HAUTEUR-1) +1);
		zones[caseAleaL][caseAleaH].setHeliport();
		this.heli = zones[caseAleaL][caseAleaH];
		this.heli.etat = true;
		init();
	}

	public class AccesHorsIle extends Exception {
		public AccesHorsIle() {
			System.out.println("Tentative d'acces a une zone hors de l'ile");
		}
	}

	/**
	 * Initialisation aleatoire des zones, exceptees celle des bords qui ont ete
	 * ajoutes.
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
						zones[i][j].etat = true;
					}
				}
			}
		}
	}

	//Fonction verifiant si une valeur est dans un tableau
	public boolean verifEquals(int[] tab, int valeur) {
		for (int i=0; i<tab.length;i++) {
			if (tab[i] == valeur) {
				return false;
			}
		}
		return true;
	}
		
	//Initialise tab de 0
		public void initTab(int[] tab) {
			for (int i=0; i<tab.length;i++) {
				tab[i] =0;
			}
		}
		
		//Fonction initialisant les joueurs
		public void initJoueurs() {
			int[] caseAleaL = new int[nbJoueur];
			int caseAleaL2 = 0;
			int[] caseAleaH = new int[nbJoueur];
			int caseAleaH2 = 0;
			initTab(caseAleaL);
			initTab(caseAleaH);
			for (int i=0; i < nbJoueur; i++ ) {
				//Cas largeur
				while(!verifEquals(caseAleaL, caseAleaL2)) {
					caseAleaL2 = (int) Math.floor(Math.random() * (LARGEUR-1) +1);
				}
				//Cas hauteur
				while(!verifEquals(caseAleaH, caseAleaH2)) {
					caseAleaH2 = (int) Math.floor(Math.random() * (HAUTEUR-1) +1);
				}
				caseAleaL[i] = caseAleaL2;
				caseAleaH[i] = caseAleaH2;
				Joueur p = new Joueur(this, zones[caseAleaL2][caseAleaH2],i+1);
				joueurs.add(p);
				
			}
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
			//Une zone a  modifie ne doit pas l'heliport et son element soit est Neutre
			if (!zoneAModif.contains(z) && (z.estNeutre())){
				zoneAModif.add(z);
			}
		}
		for (Zone z : zoneAModif) {
			zones[z.getX()][z.getY()].progresse();
		}

		notifyObservers();
	}

	public boolean deplacementJoueur(Joueur j, Zone nZ) throws AccesHorsIle {
		if (nZ.x < 1 || nZ.x > LARGEUR || (nZ.y < 1 || nZ.y > HAUTEUR))
			throw new AccesHorsIle();
		if (j.getZone().estAdjacente(nZ)) {
			j.seDeplace(nZ);
			notifyObservers();
			return true;
		}
		notifyObservers();
		return false;
	}

	public boolean assecherZone(Joueur j, Zone zV) throws AccesHorsIle {
		if (zV.x < 1 || zV.x > LARGEUR || (zV.y < 1 || zV.y > HAUTEUR))
			throw new AccesHorsIle();
		
		if (j.getZone().memeZone(zV)) {
			if (zV.estInondee()) {
				zV.situation = Situation.Normale;
				notifyObservers();
				return true;
			}
		}
		notifyObservers();
		return false;

	}

	public void recupererArtefact(Joueur j) {
		j.recupereArtefact(null);
		notifyObservers();
	}

	/**
	 * Une methode pour renvoyer la zone aux coordonnees choisies (sera utilisee par
	 * la vue).
	 * 
	 * @throws AccesHorsIle
	 */
	public Zone getZone(int x, int y) throws AccesHorsIle {
		if (x < 1 || x > LARGEUR || (y < 1 || y > HAUTEUR))
			throw new AccesHorsIle();
		return zones[x][y];
	}

	public String toString() {
		return String.format("Infos Iles: \nHauteur: %d\nLargeur: %d%nNombre de joueurs: %d", HAUTEUR, LARGEUR,
				this.joueurs.size());
	}

	public ArrayList<Joueur> getJoueurs() {
		return this.joueurs;
	}
	
	public Zone getHeli() {
		return this.heli;
	}
}

