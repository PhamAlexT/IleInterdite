package controleur;

import modele.Joueur;

public class ActionsJoueurs {
	Joueur j;
	AssecherZone aZ;
	DeplacementJoueur dJ;
	EchangeClefs ec;
	RamasserArtefact rA;
	
	int nbActionsMax = 3;
	
	public ActionsJoueurs (Joueur j) {
		this.j = j;
		aZ = new AssecherZone(j);
		dJ = new DeplacementJoueur(j);
		rA = new RamasserArtefact(j);
		ec = new EchangeClefs(j);
	}
	
	public DeplacementJoueur getDeplacementJoueur() {
		return dJ;
	}
	
	public AssecherZone getaZ() {
		return aZ;
	}

	public RamasserArtefact getrA() {
		return rA;
	}
	
	public boolean estLibre() {
		int nbActionsUsed = aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction() + ec.getNbAction();
		//System.out.println(nbActionsUsed);
		return nbActionsUsed < nbActionsMax;
	}
	
	public int getnbActionsMax() {
		return nbActionsMax;
	}
	public int nbActionsUtilisees() {
		return aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction() + ec.getNbAction();
	}
}
