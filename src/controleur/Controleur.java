package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Ile;

public class Controleur implements ActionListener {
	/**
	 * On garde un pointeur vers le modèle, car le contrôleur doit provoquer un
	 * appel de méthode du modèle. Remarque : comme cette classe est interne, cette
	 * inscription explicite du modèle est inutile. On pourrait se contenter de
	 * faire directement référence au modèle enregistré pour la classe englobante
	 * [VueCommandes].
	 */
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
