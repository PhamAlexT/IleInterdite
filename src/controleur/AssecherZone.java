package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import modele.Ile;
import modele.Joueur;
import modele.Zone;
import modele.Ile.AccesHorsIle;

public class AssecherZone extends ActionJoueur implements MouseListener {
	//Attributs
	//'TAILLE' un entier correspondant a la taille de la zone.
	//int TAILLE;
	//'ile' une ile.
	Ile ile;

	//Constructeur
	/**AssecherZone() :
	* Joueur j : un joueur.
	* Definit j comme etant le joueur concerne par l'action d'assecher une zone et initialise l'attribut ile a l'ile de j.
	**/
	public AssecherZone(Joueur j) {
		super(j);
		this.ile = j.getIle();
	}

	//Methodes
	/**void mouseClicked() :
	 * MouseEvent argo : un evenement lie a l'utilisation d'une souris.
	 * Asseche une zone proche du joueur si un clique-droit est effectue sur cette zone.
	 **/
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (SwingUtilities.isRightMouseButton(arg0)) {
			try {
				int x = (arg0.getX() / 32) + 1;
				int y = (arg0.getY() / 32) + 1;
				Zone z = ile.getZone(x, y);
				if (ile.assecherZone(j, z)) {
					this.incrNbAction();
					this.notifyObservers();
				}
				;
			} catch (AccesHorsIle e) {

			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
