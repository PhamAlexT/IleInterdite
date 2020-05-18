package controleur;

import autres.Observable;
import modele.Joueur;
import vue.VueJoueur;

public abstract class ActionJoueur extends Observable {
	int nbAction;
	Joueur j;
	
	public ActionJoueur(Joueur j) {
		this.j = j;
		nbAction = 0;
	}
	
	public void incrNbAction(){
		this.nbAction++;
		
		
	}
	
	public int getNbAction() {
		return this.nbAction;
	}
	
}
