package controleur;

import modele.Ile;


import controleur.DeplacementJoueur;

public class ActionsJoueur{
	private DeplacementJoueur deplJoueur;
	private Ile ile;
	//RecupererArtefact recupArtefact;
	
	public ActionsJoueur(Ile ile) {
		this.ile = ile;
		this.deplJoueur = new DeplacementJoueur(ile);
	}

}

