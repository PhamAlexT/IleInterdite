package modele;

import java.util.ArrayList;

import controleur.RamasserArtefact;

public class Joueur {
	private Ile ile;
	private Zone z;
	private ArrayList<Objet> items;
	private int nbCles;
	private int nbArtefact;
	private int nbJoueur;
	
	public Joueur(Ile ile, Zone z, int n) {
		this.ile = ile;
		this.z = z;
		this.nbJoueur = n;
		this.items = new ArrayList<Objet>();
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
	public void recupereArtefact(Objet truc) {
		items.add(truc);
		if (truc instanceof Clefs) {
			
			this.nbCles ++;
			System.out.println("Le Joueur " + this.nbJoueur + " a une cle en plus" + truc.e.toString());
		} else {
			this.nbArtefact ++;
			System.out.println("Le Joueur " + this.nbJoueur + " a un artefact en plus" + truc.e.toString());
		}
	}
	
	/**
	//Fonction verifiant si dans la liste le joeueur possede une cle de type l'artefact qu'il veut recuperer
	public boolean verifCle(modele.Zone.Element e) {
		for (int i = 0; i < this.items.size(); i++) {
			Object obj = this.items.get(i);	
			if (obj instanceof Clefs) {
				if (e.equals(((Clefs) obj).getElement())) {
					System.out.println("Le Joueur " + this.nbJoueur + " a bien une cle !");
					this.enleveCles((Clefs) obj);
					System.out.println("La cle pour ramasser cet artefact a etait utilise ");
					return true;
				}
			}
		}
		System.out.println("Le Joueur " + this.nbJoueur + " n'a pas de cle !");
		return false;
	}**/
	//Fonction parcourant la liste des items et indiquant combien il y a de cles  de type e
	public int combienCles(Element e) {
		int nb = 0;
		for (int i = 0; i < this.items.size(); i++) {
			Object obj = this.items.get(i);	
			if (obj instanceof Clefs) {
				if (e.equals(((Clefs) obj).getElement())) {
					nb ++;
					
				}
			}
		}
		System.out.println("Le Joueur " + this.nbJoueur + " a " + nb + " cles" );
		return nb;
	}
	
			
	public int getNbCles() {
		return this.nbCles;
	}
	public int getNbArtefacts() {
		return this.nbArtefact;
	}
	
	public ArrayList<Objet> getInventaire(){
		return this.items;
	}
	
	public void enleveCles(Clefs c) {
		int nb = 0;
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i) instanceof Clefs) {
				if (c.cleEgales((Clefs) this.items.get(i))) {
					this.items.remove(i);
					this.nbCles --;
					System.out.println("Le joueur " + this.nbJoueur + " a perdu une cle" + c.e.toString());
					nb ++;
				}
			}
			if (nb == RamasserArtefact.combienDeCles) {
				return;
			}
		}
	}
	
	public void enleveArtefact(Artefact a) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i) instanceof Artefact) {
				if (a.artefactEgal((Artefact) this.items.get(i))) {
					this.items.remove(i);
					this.nbArtefact --;
					System.out.println("Le joueur " + this.nbJoueur  + " perdu un artefact");
				}
			}
		}
	}
}

