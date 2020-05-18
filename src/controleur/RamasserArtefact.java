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


public class RamasserArtefact extends ActionJoueur implements ActionListener{

	//A modifier selon l'envie
	public final static int combienDeCles = 2;
	public RamasserArtefact(Joueur j) {
		super(j);
		// TODO Auto-generated constructor stub
	}
	
	//Pour pouvoir ramasser un artefact il faut etre sur la bonne case et avoir la bonne cle
	public void donnerArtefact(Joueur j) {
		Zone z = j.getZone();
		if (z.getElement() == modele.Element.Neutre) {
			System.out.println("Cette zone ne contient pas d'artefact !");
		}
		//Zone avec un artefact Air
		if ((z.getElement() == modele.Element.Air) && (this.combienDeCles <= j.combienCles(modele.Element.Air))) {
			j.recupereArtefact(new Artefact(modele.Element.Air));
			System.out.println("Un artefact de type Air a ete ramasser !");
			//on enleve les clefs qu'on a utilise
			Clefs cA = new Clefs(modele.Element.Air);
			j.enleveCles(cA);
			
		}
		//Zone avec un artefact Eau
		if ((z.getElement() == modele.Element.Eau) && (this.combienDeCles <= j.combienCles(modele.Element.Eau))) {
			j.recupereArtefact(new Artefact(modele.Element.Eau));
			System.out.println("Un artefact de type Eau a ete ramasser !");
			//on enleve les clefs qu'on a utilise
			Clefs cE = new Clefs(modele.Element.Eau);
			j.enleveCles(cE);
			
		}
		//Zone avec un artefcat Terre
		if ((z.getElement() == modele.Element.Terre) && (this.combienDeCles <= j.combienCles(modele.Element.Terre))) {
			j.recupereArtefact(new Artefact(modele.Element.Terre));
			System.out.println("Un artefact de type Terre a ete ramasser !");
			//on enleve les clefs qu'on a utilise
			Clefs cT = new Clefs(modele.Element.Terre);
			j.enleveCles(cT);
			
		}
		//Zone avec un artefact Feu
		if ((z.getElement() == modele.Element.Feu) && (this.combienDeCles <= j.combienCles(modele.Element.Feu))){
			j.recupereArtefact(new Artefact(modele.Element.Feu));
			System.out.println("Un artefact de type Feu a ete ramasser !");
			//on enleve les clefs qu'on a utilise
			Clefs cF = new Clefs(modele.Element.Feu);
			j.enleveCles(cF);
		}
		this.notifyObservers();
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}



