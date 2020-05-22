package modele;

import java.util.ArrayList;

import java.util.Random;

import autres.Observable;
import modele.Ile.AccesHorsIle;

public class Ile extends Observable {
	//Attributs
	
	//'HAUTEUR' un entier correspondant a la hauteur de la grille et 'LARGEUR' un entier correspondant a la largeur de la grille.
	public static final int HAUTEUR = 15, LARGEUR = 15; // 20
	//'nbJoueur' un entier representant le nombre de joueurs de la partie.
	public static final int nbJoueur = 4;
	//'nbArtefact' un entier representant le nombre d'artefact de la partie.
	public static final int nbArtefact = 4;
	//'zones' un tableau stockant des zones correspondant aux zones de l'ile.
	private Zone[][] zones;
	//'generateur' un generateur d'entiers aleatoires.
	private Random generateur;
	//'heli' une zone speciale correspondant a l'heliport.
	private Zone heli;
	//'joueurs' une liste de joueurs contenant les joueurs de la partie.
	private ArrayList<Joueur> joueurs;

	//Constructeur
	/**Ile() :
	 * 		Creer tout d'abord un tableau de zone vide correspondant a l'attribut zones de l'ile puis creer une liste vide de joueurs.
	 * 		On place ensuite l'heliport et on initialise la liste des joueurs et des zones.
	 **/
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

		// Alea de l'aeroport
		int caseAleaL = (int) Math.floor(Math.random() * (LARGEUR - 1) + 1);
		int caseAleaH = (int) Math.floor(Math.random() * (HAUTEUR - 1) + 1);
		zones[caseAleaL][caseAleaH].setHeliport();
		this.heli = zones[caseAleaL][caseAleaH];
		this.heli.etat = true;
		init();
	}

	public class AccesHorsIle extends Exception {
		
		//Constructeur
		/**AccesHorsIle() :
		 * 		Affiche un message si le joueur essaye d'acceder a une zone hors de l'ile.
		 **/
		public AccesHorsIle() {
			System.out.println("Tentative d'acces a une zone hors de l'ile");
		}
	}
	//Methodes
	
	/**void init() :
	 * Initialisation aleatoire des zones, exceptees celle des bords qui ont ete
	 * ajoutes.
	 */
	public void init() {
		initCellules();
		initJoueurs();
	}

	/**void initCellules() :
	 * 		Initialise aleatoirement les zones exceptees les zones des bords qui sont des surplus.
	 **/
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

	/**boolean verifEquals() :
	 * 		int[] tab : un tableau d'entiers.
	 * 		int valeur : un entier.
	 * 		Return : True si valeur se trouve dans tab[], false sinon.
	 **/
	public boolean verifEquals(int[] tab, int valeur) {
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == valeur) {
				return false;
			}
		}
		return true;
	}

	/**void initTab() :
	 * 		int[] tab : un tableau d'entiers.
	 * 		Initialise ou mets toutes les valeurs du tableau tab a 0.
	 **/
	public void initTab(int[] tab) {
		for (int i = 0; i < tab.length; i++) {
			tab[i] = 0;
		}
	}

	/**void initJoueurs() :
      *Initialise les joueurs de la partie, 
      * les places de maniere aleatoire sur la carte 
      * et les ajoute a la liste de joueurs de la partie
	 **/
	public void initJoueurs() {
		int[] caseAleaL = new int[nbJoueur];
		int caseAleaL2 = 0;
		int[] caseAleaH = new int[nbJoueur];
		int caseAleaH2 = 0;
		initTab(caseAleaL);
		initTab(caseAleaH);
		for (int i = 0; i < nbJoueur; i++) {
			// Cas largeur
			while (!verifEquals(caseAleaL, caseAleaL2)) {
				caseAleaL2 = (int) Math.floor(Math.random() * (LARGEUR - 1) + 1);
			}
			// Cas hauteur
			while (!verifEquals(caseAleaH, caseAleaH2)) {
				caseAleaH2 = (int) Math.floor(Math.random() * (HAUTEUR - 1) + 1);
			}
			caseAleaL[i] = caseAleaL2;
			caseAleaH[i] = caseAleaH2;
			Joueur p = new Joueur(this, zones[caseAleaL2][caseAleaH2], i + 1);
			joueurs.add(p);

		}
	}

	/**void avance() :
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
			// Une zone a modifie ne doit pas l'heliport et son element soit est Neutre
			if (!zoneAModif.contains(z) && (z.estNeutre())) {
				zoneAModif.add(z);
			}
		}
		for (Zone z : zoneAModif) {
			zones[z.getX()][z.getY()].progresse();
		}

		notifyObservers();
	}

	/**boolean deplacementJoueur() :
	 * 		Joueur j : un joueur.
	 * 		Zone nZ : une zone.
	 * 		Deplace un joueur j dans les limites des dimensions de l'ile dans une zone nZ si elle est proche
	 * 		Return : True si le joueur a pu etre deplace, false sinon.
	 **/
	public boolean deplacementJoueur(Joueur j, Zone nZ) throws AccesHorsIle {
		if (nZ.x < 1 || nZ.x > LARGEUR || (nZ.y < 1 || nZ.y > HAUTEUR))
			throw new AccesHorsIle();
		if (j.getZone().estUnDeplacementPossible(nZ) && !nZ.estSubmergee()) {
			j.seDeplace(nZ);
			notifyObservers();
			return true;
		}
		notifyObservers();
		return false;
	}

	/**boolean assecherZone() :
	 * 		Joueur j : un joueur.
	 * 		Zone zV : une zone.
	 * 		Asseche une zone zV dans les limites des dimensions de l'ile si elle est proche du joueur j effectuant l'action
	 * 		Return : True si la zone a pu etre assechee, false sinon.
	 **/
	public boolean assecherZone(Joueur j, Zone zV) throws AccesHorsIle {
		if (zV.x < 1 || zV.x > LARGEUR || (zV.y < 1 || zV.y > HAUTEUR))
			throw new AccesHorsIle();

		if (j.getZone().estAdjacente(zV)) {
			if (zV.estInondee()) {
				zV.situation = Situation.Normale;
				notifyObservers();
				return true;
			}
		}
		notifyObservers();
		return false;

	}

	/**void recupererArtefact() :
	 * 		Joueur j : un joueur.
	 * 		Permet au joueur j de recuperer un artefact.
	 **/
	public void recupererArtefact(Joueur j) {
		j.recupereArtefact(null);
		notifyObservers();
	}

	/**Zone getZone() :
	 * 		int x : un entier formant une coordonnee avec un autre entier.
	 * 		int y : un entier formant une coordonnee avec un autre entier.
	 * 		Return : la zone presente aux coordonnees choisies si elle n'est pas hors de l'ile. 
	 * @throws AccesHorsIle
	 */
	public Zone getZone(int x, int y) throws AccesHorsIle {
		if (x < 1 || x > LARGEUR || (y < 1 || y > HAUTEUR))
			throw new AccesHorsIle();
		return zones[x][y];
	}

	/**String toString() :
	 * 		Return : Une chaine de caracteres correspondant aux informations de l'ile fournies par certains attributs.
	 **/
	public String toString() {
		return String.format("Infos Iles: \nHauteur: %d\nLargeur: %d%nNombre de joueurs: %d", HAUTEUR, LARGEUR,
				this.joueurs.size());
	}

	/**ArrayList getJoueurs() :
	 * 		Return : la liste des joueurs de la partie.
	 **/
	public ArrayList<Joueur> getJoueurs() {
		return this.joueurs;
	}

	/**Zone getHeli() :
	 * 		Return : la zone presentant l'heliport.
	 **/
	public Zone getHeli() {
		return this.heli;
	}

	/**int getNbJoueur() :
	 * 		Joueur j : un joueur.
	 * 
	 * 		Return : l'index du joueur j dans la lisre des joueurs de la partie.
	 **/
	public int getNbJoueur(Joueur j) {
		int index = joueurs.indexOf(j);
		if (index==-1) {
			System.out.println("Joueur non existant");
			System.exit(1);
		}
		return index;
	}
}
