package modele;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class Joueur {
	private Ile ile;
	private Zone z;
	private ArrayList<Object> items;
	private int nbCles;
	private int nbArtefact;
	private int nbJoueur;
	
	public Joueur(Ile ile, Zone z, int n) {
		this.ile = ile;
		this.z = z;
		this.nbJoueur = n;
		this.items = new ArrayList<Object>();
		this.nbCles = 0;
		this.nbArtefact = 0;
	}
	
	public int getNb() {
		return this.nbJoueur;
	}
	public void seDeplace(Zone nz) {
			this.z = nz;
	}

	public String toString() {
		return "--Joueur--\n" + z.toString() + "------------";
	}
	
	public Ile getIle() {
		return this.ile;
	}
	public Zone getZone() {
		return this.z;
	}
	public void recupereArtefact(Object truc) {
		items.add(truc);
		if (truc instanceof Clefs) {
			this.nbCles ++;
			System.out.println(" Le joeuer a une cle en plus");
		} else {
			this.nbArtefact ++;
			System.out.println(" Le joeuer a unn artefact en plus");
		}
	}
	
	//Fonction verifiant si dans la liste le joeueur possede une cle de type l'artefact qu'il veut recuperer
	public boolean verifCle(modele.Zone.Element e) {
		for (Object obj : this.items) {
			if (obj instanceof Clefs) {
				if (e.equals(((Clefs) obj).getElement())) {
					System.out.println("Le joeur a bien une cle !");
					return true;
				}
			}
		}
		System.out.println("Le joeur n'a pas de cle !");
		return false;
	}
		
	public int getNbCles() {
		return this.nbCles;
	}
	public int getNbArtefacts() {
		return this.nbArtefact;
	}
	
	public int getNbObjects() {
		return this.getNbCles() + this.getNbArtefacts();
	}
}

