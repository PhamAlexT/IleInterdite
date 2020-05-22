package controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import modele.Artefact;
import modele.Clef;
import modele.Joueur;
import modele.Objet;
import modele.Zone;

public class RamasserArtefact extends ActionJoueur implements ActionListener {

	//Attributs
	//'clefsMinimum' un entier correpondant au nombre de clefs d'un element minimum que doit avoir un joueur pour recuperer un artefact de cet element.
	private final static int clefsMinimum = 2; // A modifier selon l'envie

	//Constructeur
	/**RamasserArtefact() :
	* Joueur j : un joueur.
    * 		Definit j comme etant le joueur concerne par l'action de ramasser un artefact.
	**/
	public RamasserArtefact(Joueur j) {
		super(j);
	}

	//Methodes
	
	/**void donnerArtefact() :
	 * 		Joueur j : un joueur.
	 * 		Donne un artefact de l'element de la zone sur laquelle se trouve j si il a le nombre de clefs de cet element minimum requis.
	**/
	public void donnerArtefact(Joueur j) {
		Zone z = j.getZone();
		if (z.getElement() == modele.Element.Neutre) {
			System.out.println("Cette zone ne contient pas d'artefact !");
		}
		if (this.clefsMinimum <= j.combienCles(z.getElement())) {
			j.recupereArtefact(new Artefact(z.getElement()));
			j.enleveCles(new Clef(z.getElement()), clefsMinimum);
			
			this.notifyObservers();
			j.getIle().notifyObservers(); // Pour l'affichage d'inventaire.
		}
	}

	//Methode propre a l'interface ActionListener mais non utilisee ici.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
