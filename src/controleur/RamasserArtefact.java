package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import modele.Artefact;
import modele.Clefs;
import modele.Ile;
import modele.Joueur;
import modele.Zone;
import modele.Ile.AccesHorsIle;
import modele.Zone.Element;


public class RamasserArtefact extends ActionJoueur implements ActionListener{

	public RamasserArtefact(Joueur j) {
		super(j);
		// TODO Auto-generated constructor stub
	}
	
	//Pour pouvoir ramasser un artefact il faut etre sur la bonne case et avoir la bonne cle
	public void donnerArtefact(Joueur j) {
		Zone z = j.getZone();
		if (z.getElement() == Zone.Element.Neutre) {
			System.out.println("Cette zone ne contient pas d'artefact !");
		}
		if ((z.getElement() == Zone.Element.Air) && (j.verifCle(Zone.Element.Air))) {
			j.recupereArtefact(new Artefact(Element.Air));
			System.out.println("Un artefact de type Air a ete ramasser !");
		}
		if ((z.getElement() == Zone.Element.Eau) && (j.verifCle(Zone.Element.Eau))) {
			j.recupereArtefact(new Artefact(Element.Eau));
			System.out.println("Un artefact de type Eau a ete ramasser !");
		}
		if ((z.getElement() == Zone.Element.Terre) && (j.verifCle(Zone.Element.Terre))) {
			j.recupereArtefact(new Artefact(Element.Terre));
			System.out.println("Un artefact de type Terre a ete ramasser !");
		}
		if ((z.getElement() == Zone.Element.Feu) && (j.verifCle(Zone.Element.Feu))){
			j.recupereArtefact(new Artefact(Element.Feu));
			System.out.println("Un artefact de type Feu a ete ramasser !");
		}
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}



