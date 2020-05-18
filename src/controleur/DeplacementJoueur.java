package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import modele.Ile;
import modele.Ile.AccesHorsIle;
import modele.Joueur;
import modele.Zone;

public class DeplacementJoueur extends ActionJoueur implements MouseListener {
	int TAILLE;
	Ile ile;

	public DeplacementJoueur(Joueur j) {
		super(j);
		this.ile = j.getIle();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (SwingUtilities.isLeftMouseButton(arg0)) {
			try {
				int x = (arg0.getX() / 32) + 1;
				int y = (arg0.getY() / 32) + 1;
				Zone z = ile.getZone(x, y);
				if (ile.deplacementJoueur(j, z)) {
					this.incrNbAction();
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
