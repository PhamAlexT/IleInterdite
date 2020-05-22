package controleur;

import autres.Observable;
import modele.Joueur;
import vue.VueJoueur;

public abstract class ActionJoueur extends Observable {
	
	//Attributs
	//'nbAction' un entier representant le nombre d'actions que peut faire un joueur j.
	int nbAction;
	//'j' un joueur.
	Joueur j;
	
	//Constructeur
	/**ActionJoueur() :
	 * 		Joueur j : un joueur.
	 * 		Definit j comme etant le joueur concerne par les actions avec l'argument j
	 *		et initialise son nombre d'action a 0.
	 **/
	public ActionJoueur(Joueur j) {
		this.j = j;
		nbAction = 0;
	}
	
	//Methodes
	/**void incrNbAction() :
	 * 		Incremente de 1 le nombre d'actions nbAction du joueur concerne par les actions.
	 **/
	public void incrNbAction(){
		this.nbAction++;	
	}
	
	/**int getNbAction() :
	 * 		Return : un entier correspondant au nombre d'action nbAction a faire du joueur concerne par les actions.
	 **/
	public int getNbAction() {
		return this.nbAction;
	}
	
}
