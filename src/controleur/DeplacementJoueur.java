package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modele.Ile;
import modele.Joueur;
import modele.Zone;

public class DeplacementJoueur implements MouseListener {
	private Ile ile;
	private Joueur j;
	private final int TAILLE;

	public DeplacementJoueur(Ile ile, int nbPixel) {
		this.ile = ile;
		TAILLE = nbPixel;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = (arg0.getX() / 32)+1;
		int y = (arg0.getY() / 32)+1;
		Zone z = null;

		System.out.println(x + "      " + y);
		try {
			z = ile.getZone(x, y);
		} catch (Exception e) {
			System.out.println("PROBLEME DEPLACEMENT SOURIS");
			System.exit(1);
		}

		j = ile.getJoueurs().get(0);
		ile.deplacementJoueur(j, z);
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
