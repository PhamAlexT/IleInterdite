package modele;

import java.util.ArrayList;
import java.util.Random;

import autres.Observable;
import controleur.ActionsJoueurs;
public class DeroulementPartie extends Observable {
	
	Random rand = new Random();
	
	public Ile ile;
	private Joueur j;
	private ActionsJoueurs ajActuel;
	private int indiceJoueur;

	public DeroulementPartie(Ile ile) {
		this.ile = ile;
		indiceJoueur = 0;
		this.j = ile.getJoueurs().get(indiceJoueur);
		ajActuel = new ActionsJoueurs(j);
	}
	
	public void prochainJoueur() {
		indiceJoueur++;
		j = ile.getJoueurs().get(indiceJoueur % ile.getJoueurs().size());
		ajActuel = new ActionsJoueurs(j);
		this.notifyObservers();
	}
	
	public ActionsJoueurs getAJActuel() {
		return ajActuel;
	}
	
	public int getindiceJoueurModulo() {
		return indiceJoueur % ile.getJoueurs().size();
	}
	
	public int getActionsRestantes() {
		//System.out.println(ajActuel.getnbActionsMax() - ajActuel.nbActionsUtilisees());
		return ajActuel.getnbActionsMax() - ajActuel.nbActionsUtilisees();
	}
	
	public Joueur getJoueur() {
		return this.j;
	}
	
	public void giveAleaClefs() {
		int chance = rand.nextInt(100);
		if(0 <= chance && chance < 20) {
			Joueur JoueurA = getJoueur();
			JoueurA.recupereArtefact(Clefs.aleaClefs());
			System.out.println("Ce joueur recoit une clef !");
			System.out.println("Le joueur " + JoueurA.getNb() + " possede " + JoueurA.getNbCles() + " cls");
		}
	}
	
	//Fonction verifiant si la partie est gagne
	public boolean gagne() {
		ArrayList<Joueur> listJ = ile.getJoueurs();
		int nbArte = 0;
		for (Joueur j : listJ) {
			if (!j.getZone().equals(ile.getHeli())) {
				//System.out.println("Pas tous a l'helico");
				return false;
			}
			nbArte += j.getNbArtefacts();
		}
		if (!(nbArte == ile.nbArtefact)) {
			//System.out.println("Pas tous les artefacts");
			return false;
		}
		return true;
	}
}
