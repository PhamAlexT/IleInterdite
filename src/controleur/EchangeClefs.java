package controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import modele.Clef;
import modele.Element;
import modele.Joueur;

public class EchangeClefs extends ActionJoueur implements ActionListener {

	//Attributs
	//'rand' un generateur d'entiers aleatoires.
	static Random rand = new Random();

	//Constructeur
		
	/**EchangeClefs() :
	 * 		Joueur j : un joueur.
	 * 
	 * 		Definit j comme etant le joueur concerne par l'action d'echanger une clef.
	**/
	public EchangeClefs(Joueur j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	//Methodes
	
	/**void echange() :
	 * 		Joueur j : un joueur.
	 * 		joueurs : une liste de joueurs.
	 * 		Permet au joueur j de donner une de ses clefs de maniere aleatoire a un autre joueur proche parmi la liste des joueurs.
	 **/
	public void echange(Joueur j, ArrayList<Joueur> joueurs) {
		boolean echangeRealise = false;
		if (j.getNbCles() != 0) {
			for (Joueur j2 : joueurs) {
				if (j.getZone().memeZone(j2.getZone()) && j != j2) {
					while (!echangeRealise) {
						int alea = rand.nextInt(j.getInventaire().size());
						if (j.getInventaire().get(alea) instanceof Clef) {
							Clef c = (Clef) j.getInventaire().get(alea);
							j2.recupereArtefact(c);
							j.enleveCles(c, 1);
							this.incrNbAction();
							echangeRealise = true;
						}
					}
				}
			}
		}
	}

	//Methode propre a l'interface ActionListener mais non utilisee ici.
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
