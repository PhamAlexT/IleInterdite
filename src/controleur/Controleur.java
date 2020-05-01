package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Ile;
import modele.Joueur;

public class Controleur implements ActionListener {
	Ile modele;

	public Controleur(Ile modele) {
		this.modele = modele;
	}

	/**
	 * Action effectu�e � r�ception d'un �v�nement : appeler la m�thode [avance] du
	 * mod�le.
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.modele.avance();
		System.out.println("bipboup");
		this.modele.getJoueurs();
		System.out.println("Changement de tour !");
		for(int i=0; i<this.modele.getJoueurs().size(); i++) {
			this.modele.getJoueurs().get(i).resetEnergy();
			System.out.println(this.modele.getJoueurs().get(i).getRemainingA());
		}
	}
}

