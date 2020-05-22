package controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import modele.Artefact;
import modele.Clef;
import modele.Joueur;
import modele.Objet;
import modele.Zone;

public class RamasserArtefact extends ActionJoueur implements ActionListener {

	private final static int clefsMinimum = 2; // A modifier selon l'envie

	public RamasserArtefact(Joueur j) {
		super(j);
	}

	public void donnerArtefact(Joueur j) {
		Zone z = j.getZone();
		if (z.getElement() == modele.Element.Neutre) {
			System.out.println("Cette zone ne contient pas d'artefact !");
		}
		System.out.println("Test");
		if (this.clefsMinimum <= j.combienCles(z.getElement())) {
			j.recupereArtefact(new Artefact(z.getElement()));
			j.enleveCles(new Clef(z.getElement()), clefsMinimum);
			this.notifyObservers();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
