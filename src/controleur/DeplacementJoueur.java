package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modele.Ile;
import modele.Ile.AccesHorsIle;
import modele.Joueur;
import modele.Zone;

public class DeplacementJoueur implements MouseListener {
	private Ile ile;
	private Joueur j;
	private final int TAILLE;

	public DeplacementJoueur(Ile ile) {
		this.ile = ile;
		TAILLE = ile.LARGEUR*ile.HAUTEUR;
	}

	@Override
	public void mouseClicked(MouseEvent arg0){
		j = ile.getJoueurs().get(0);
		try {
			int x = (arg0.getX() / 32)+1;
			int y = (arg0.getY() / 32)+1;
			Zone z = ile.getZone(x, y);
			ile.deplacementJoueur(j, z);
		} catch (AccesHorsIle e) {
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
