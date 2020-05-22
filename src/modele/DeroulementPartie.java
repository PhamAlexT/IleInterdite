package modele;

import java.util.ArrayList;
import java.util.Random;

import autres.Observable;
import autres.Observer;
import controleur.ActionsJoueurs;

public class DeroulementPartie extends Observable implements Observer {

	//Attributs
	
	//'rand' un generateur d'entiers aleatoires.
	Random rand = new Random();
	//'ile' une ile.
	/** On conserve un pointeur vers la classe principale du modele. */
	private Ile ile;
	//'j' un joueur.
	private Joueur j;
	//'ajActuel' les actions du joueur actuellement en action.
	private ActionsJoueurs ajActuel;
	//'indiceJoueur' un entier correspondant a l'indice du joueur actuellement en action.
	private int indiceJoueur;
	//'actionsRestantes' un entier correpondant au nombre d'actions restantes du joueur en action.
	private int actionsRestantes;
	/**DeroulementPartie() :
	 * 		Ile ile : une ile.
	 * 		Definit ile comme etant l'attribut ile de la partie. initialise indiceJoueur a 0 pour definir le premier joueur de la liste comme etant le joueur
	 * 		en action, accorde a ce joueur des actions ajActuels et un nombre d'action restante actionsRestantes et ajoute ses actions a la liste des observateurs.
	 **/
	public DeroulementPartie(Ile ile) {
		this.ile = ile;
		indiceJoueur = 0;
		this.j = ile.getJoueurs().get(indiceJoueur);
		ajActuel = new ActionsJoueurs(j);
		actionsRestantes = ajActuel.getnbActionsMax();
		ajActuel.addObserver(this);
	}

	//Methodes
	/**void prochainJoueur() :
	 * 		Fait passer le joueur actuel au joueur suivant.
	**/
	public void prochainJoueur() {
		indiceJoueur++;
		j = ile.getJoueurs().get(indiceJoueur % ile.getJoueurs().size());
		ajActuel = new ActionsJoueurs(j);
		actionsRestantes = ajActuel.getnbActionsMax();
		ajActuel.addObserver(this);
		this.notifyObservers();
	}

	/**ActionsJoueurs getAJActuel() :
	 * 		Return : les actions du joueur actuel.
	 **/
	public ActionsJoueurs getAJActuel() {
		return ajActuel;
	}

	/**int getindiceJoueurModulo() :
	 * 		Return : l'indice du joueur actuel modulo la taille de la liste de joueurs.
	 **/
	public int getindiceJoueurModulo() {
		return indiceJoueur % ile.getJoueurs().size();
	}

	/**int getActionsRestantes() :
	 * 		Return : le nombre d'actions restantes a effectuer par le joueur actuel.
	 **/
	public int getActionsRestantes() {
		// System.out.println(ajActuel.getnbActionsMax() -
		// ajActuel.nbActionsUtilisees());
		return ajActuel.getnbActionsMax() - ajActuel.nbActionsUtilisees();
	}

	/**Joueur getJoueur() :
	 * 		Return : le joueur actuel.
	 **/
	public Joueur getJoueur() {
		return this.j;
	}

	/**void giveAleaClefs() :
	 * 		Donne une clef d'un element aleatoire ou rien a un joueur de maniere aleatoire. Le joueur a 20% de chance de recevoir
	 * 		une clef (utilisee a la fin de chaque tour).
	 **/
	public void giveAleaClefs() {
		int chance = rand.nextInt(100);
		if (0 <= chance && chance < 20) {
			Joueur JoueurA = getJoueur();
			Clef cAlea = Clef.aleaClefs();
			JoueurA.recupereObjet(cAlea);
			this.notifyObservers();
			ile.notifyObservers();
		}
	}

	/**boolean gagne() :
	 * 		Return : True si les conditions d'une victoire sont reunies, false sinon.
	 **/
	public boolean gagne() {
		ArrayList<Joueur> listJ = ile.getJoueurs();
		ArrayList<Object> artefact = new ArrayList<Object>();
		for (Joueur j : listJ) {
			//on verifie qu'il est sur l'heliport
			if (!j.getZone().equals(ile.getHeli())) {
				return false;
			}
			//on parcourt ces items
			ArrayList<Objet> items = j.getInventaire();
			for (Object o : items) {
				if ((o instanceof Artefact) && (!artefact.contains(o))){
					artefact.add(o);
				}
			}
		}
		if (!(artefact.size() == ile.nbArtefact)) {
			return false;
		}
		return true;
	}
	
	/**boolean defaite() :
	 * 		Return : True si les conditions d'une defaite sont reunies, false sinon.
	 **/
	public boolean defaite() {
		return joueurSubmergees();
	}
	
	/**boolean joueurSubmergees() :
	 * 		Return : True si au moins un joueur est sur une zone submergee, false sinon.
	 **/
	public boolean joueurSubmergees() {
		for(Joueur j: ile.getJoueurs()) {
			if (j.getZone().estSubmergee()) return true;
		}
		return false;
	}

	@Override
	public void update() {
		actionsRestantes = getActionsRestantes();
		this.notifyObservers();

	}
	
	/**Ile getIle() :
	 * 		Return : l'ile concerne par la partie.
	 **/
	public Ile getIle() {
		return this.ile;
	}
}
