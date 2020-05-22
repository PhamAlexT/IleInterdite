package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Ile;
import modele.Joueur;

public class Controleur implements ActionListener {
	//Attributs
	
	//'modele une ile.
	Ile modele;

	//Constructeur	
	/**Controleur() :
	 * 		Ile modele : une ile. 
	 * 		Definit modele comme etant l'ile attribut modele concerne par le controleur.
	 **/
	public Controleur(Ile modele) {
		this.modele = modele;
	}
	
	//Methodes
	/**
	 * Action effectuee a la reception d'un evenement : appeler la methode [avance] du
	 * modele.
	 */
	/**void actionPerformed() :
	 * 		ActionEvent argo : une action.
	 * 		Met a jour le modele.
	 **/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.modele.avance();
	}
}
