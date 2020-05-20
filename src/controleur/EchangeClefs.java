package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import modele.Clef;
import modele.Element;
import modele.Joueur;

public class EchangeClefs extends ActionJoueur implements ActionListener {

	static Random rand = new Random();

	public EchangeClefs(Joueur j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
