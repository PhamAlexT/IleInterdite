package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import modele.Ile;
import modele.Ile.AccesHorsIle;
import modele.Joueur;
import modele.Zone;

public class DeplacementJoueur extends ActionJoueur implements MouseListener {
	//Attributs
	
	//'Taille' un entier correpondant a une taille
	//int TAILLE;
	//'ile' une ile.
	Ile ile;

	//Constructeur
		
	/**DeplacementJoueur() :
	 * 		Joueur j : un joueur.
	 * 		Definit j comme etant le joueur concerne par l'action de se deplacer et initialise l'attribut ile a l'ile de j.
	 **/
		public DeplacementJoueur(Joueur j) {
			super(j);
			this.ile = j.getIle();
		}

	//Methodes	
	/**void mouseClicked() :
	* 		MouseEvent argo : un evenement lie a l'utilisation d'une souris. 
	*  		Deplace un joueur vers une zone proche si un clique-gauche est effectue sur cette zone.
	 **/
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (SwingUtilities.isLeftMouseButton(arg0)) {
			try {
				int x = (arg0.getX() / 32) + 1;
				int y = (arg0.getY() / 32) + 1;
				Zone z = ile.getZone(x, y);
				if (ile.deplacementJoueur(j, z)) {
					this.incrNbAction();
					this.notifyObservers();
				}
				;
			} catch (AccesHorsIle e) {

			}
		}
	}
	
	//Methodes propres a l'interface MouseListener mais non utilisees ici.
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
