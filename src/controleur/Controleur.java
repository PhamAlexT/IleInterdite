package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Ile;

public class Controleur implements ActionListener {
	Ile modele;

	public Controleur(Ile modele) {
		this.modele = modele;
	}

	/**
	 * Action effectuée à réception d'un événement : appeler la méthode [avance] du
	 * modèle.
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		modele.avance();
	}
}
