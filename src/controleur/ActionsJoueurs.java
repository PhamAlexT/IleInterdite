package controleur;

import autres.Observable;
import autres.Observer;
import modele.Joueur;

public class ActionsJoueurs extends Observable implements Observer{
	//Attributs
	//'j' un joueur.
	Joueur j;
	//'az' la capacite d'assecher une zone.
	AssecherZone aZ;
	//'dJ' la capacite de se deplacer.
	DeplacementJoueur dJ;
	//'ec' la capacite d'echanger des clefs.
	EchangeClefs ec;
	//'rA' la capacite de ramasser un artefact.
	RamasserArtefact rA;
	//'nbActionMax" un entier correspondant au nombre maximum d'actions disponibles d'un joueur.
	int nbActionsMax = 3;
		
	//Constructeur
		
	/**ActionsJoueurs() :
	 * 		Joueur j : un joueur.
	 * 		Definit j comme etant le joueur attribut j concerne par les actions avec l'argument j, lui donne la capacite aZ d'assecher une zone, dJ
	 * 		de se deplacer, rA de ramasser un artefact, ec d'echanger des clefs et ajoute aZ, dJ et rA aux observateurs des actions du joueurs.
	 **/
	public ActionsJoueurs (Joueur j) {
		this.j = j;
		aZ = new AssecherZone(j);
		dJ = new DeplacementJoueur(j);
		rA = new RamasserArtefact(j);
		ec = new EchangeClefs(j);
		
		aZ.addObserver(this);
		dJ.addObserver(this);
		rA.addObserver(this);
	}

	//Methodes
	/**DeplacementJoueur getDeplacementJoueur() :
	* 	Return : dJ la capacite a se deplacer du joueur concerne par les actions.
	**/
	public DeplacementJoueur getDeplacementJoueur() {
		return dJ;
	}
	
	/**AssecherZone getaZ() : 
	 * 		Return : aZ la capacite a assecher une zone du joueur concerne par les actions.
	 **/
	public AssecherZone getaZ() {
		return aZ;
	}

	/**RamasserArtefact getrA() :
	 * Return : rA la capacite a ramasser un artefact du joueur concerne par les actions.
	 **/
	public RamasserArtefact getrA() {
		return rA;
	}

	/**boolean estLibre() :
	 * Return : True si il reste des actions a faire au joueur concerne et s'il n'est pas sur une zone submergee, false sinon.
	 **/
	public boolean estLibre() {
		int nbActionsUsed = aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction() + ec.getNbAction();
		//System.out.println(nbActionsUsed);
		return nbActionsUsed < nbActionsMax && !j.getZone().estSubmergee();
	}
	
	/**int getnbActionsMax() :
	 * Return : le nombre d'actions maximum que peut faire le joueur concerne nbActionsMax.
	 **/
	public int getnbActionsMax() {
		return nbActionsMax;
	}
	
	/**int nbActionsUtilisees() :
	 * Return : le nombre d'actions deja utilisees par le joueur concerne.
	 **/
	public int nbActionsUtilisees() {
		return aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction() + ec.getNbAction();
	}

	@Override
	public void update() {
		this.notifyObservers();
		
	}
}
