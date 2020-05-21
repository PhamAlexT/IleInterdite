package modele;

import java.util.ArrayList;
import java.util.Random;

import autres.Observable;
import autres.Observer;
import controleur.ActionsJoueurs;

public class DeroulementPartie extends Observable implements Observer {

	Random rand = new Random();

	private Ile ile;
	private Joueur j;
	private ActionsJoueurs ajActuel;
	private int indiceJoueur;
	private int actionsRestantes;

	public DeroulementPartie(Ile ile) {
		this.ile = ile;
		indiceJoueur = 0;
		this.j = ile.getJoueurs().get(indiceJoueur);
		ajActuel = new ActionsJoueurs(j);
		actionsRestantes = ajActuel.getnbActionsMax();
		ajActuel.addObserver(this);
	}

	public void prochainJoueur() {
		indiceJoueur++;
		j = ile.getJoueurs().get(indiceJoueur % ile.getJoueurs().size());
		ajActuel = new ActionsJoueurs(j);
		actionsRestantes = ajActuel.getnbActionsMax();
		ajActuel.addObserver(this);
		this.notifyObservers();
	}

	public ActionsJoueurs getAJActuel() {
		return ajActuel;
	}

	public int getindiceJoueurModulo() {
		return indiceJoueur % ile.getJoueurs().size();
	}

	public int getActionsRestantes() {
		// System.out.println(ajActuel.getnbActionsMax() -
		// ajActuel.nbActionsUtilisees());
		return ajActuel.getnbActionsMax() - ajActuel.nbActionsUtilisees();
	}

	public Joueur getJoueur() {
		return this.j;
	}

	public void giveAleaClefs() {
		int chance = rand.nextInt(100);
		if (0 <= chance && chance < 20) {
			Joueur JoueurA = getJoueur();
			Clef cAlea = Clef.aleaClefs();
			JoueurA.recupereArtefact(cAlea);
			System.out.println("Ce joueur recoit une clef " + cAlea.e.toString());
			this.notifyObservers();
			ile.notifyObservers();
		}
	}

	// Fonction verifiant si la partie est gagne
	public boolean gagne() {
		ArrayList<Joueur> listJ = ile.getJoueurs();
		int nbArte = 0;
		for (Joueur j : listJ) {
			if (!j.getZone().equals(ile.getHeli())) {
				// System.out.println("Pas tous a l'helico");
				return false;
			}
			nbArte += j.getNbArtefacts();
		}
		if (!(nbArte == ile.nbArtefact)) {
			// System.out.println("Pas tous les artefacts");
			return false;
		}
		return true;
	}
	
	public boolean defaite() {
		return joueurSubmergees();
	}
	
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
}
