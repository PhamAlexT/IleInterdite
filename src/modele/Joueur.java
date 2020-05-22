package modele;

import java.util.ArrayList;

import controleur.RamasserArtefact;

public class Joueur {
	//Attributs
	//'ile' une ile.
	/** On conserve un pointeur vers la classe principale du modele. */
	private Ile ile;
	//'z' une zone.
	private Zone z;
	//'items' une liste d'objets.
	private ArrayList<Objet> items;
	//'nbJoueur' un entier correspondant au numero du joueur.
	private int nbJoueur;
		
	//Constructeurs
		
	/**Joueur() :
	 * 		Ile ile : une ile.
	 * 		Zone z : une zone.
	 * 		int n : un entier.
	 * 		Initialise l'attribut ile avec l'ile donnee en parametre, l'attribut z avec la zone donnee en parametre initialise la liste items des 
	 * 		objets du joueur a une liste vide.
	 **/
	public Joueur(Ile ile, Zone z, int n) {
		this.ile = ile;
		this.z = z;
		//this.nbJoueur = n;
		this.items = new ArrayList<Objet>();
	}
	
	//Methodes
	
	/**void seDeplace() :
	 * 		Zone nz : une zone.
	 * 		Donne la valeur nz a l'attribut zone z du joueur.
	**/
	public void seDeplace(Zone nz) {
			this.z = nz;
	}

	/**String toString() :
	 * 		Return : Une chaine de caracteres correspondant au joueur et a la zone ou il se trouve.
	**/
	public String toString() {
		return "--Joueur--\n" + z.toString() + "------------";
	}
		
	/**Ile getIle() :
	 * 		Return : l'ile ou se trouve le joueur.
	 **/
	public Ile getIle() {
		return this.ile;
	}
		
	/**Ile getZone() :
	 * 		Return : la zone ou se trouve le joueur.
	 **/
	public Zone getZone() {
		return this.z;
	}
	
	/**void recupererArtefact() :
	* 		Objet truc : un objet.
	* 		Ajoute l'objet truc a la liste items des objets du joueur.
	 **/
	public void recupereArtefact(Objet truc) {
		items.add(truc);
		if (truc instanceof Clef) {
			//System.out.println("Le Joueur " + this.nbJoueur + " a une cle en plus" + truc.e.toString());
		} else {
			//System.out.println("Le Joueur " + this.nbJoueur + " a un artefact en plus" + truc.e.toString());
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
		
	/**int combienCles() :
	 * 		Element e : un element parmi un type enumere.
	 * 		Return : le nombre de clefs de type e possedees par le joueur.
	 **/
	public int combienCles(Element e) {
		//System.out.println("Element" + e.toString());
		int nb = 0;
		for (int i = 0; i < this.items.size(); i++) {
			Object obj = this.items.get(i);	
			//System.out.println("Element ds la liste " + e.toString());
			if (obj instanceof Clef) {
				//System.out.println("type cles");
				if(((Clef) obj).e.equals(e)) {			
					//System.out.println("egaux");
					nb ++;	
				}
			}
		}
		//System.out.println("Le Joueur " + this.nbJoueur + " a " + nb + " cles" );
		return nb;
	}
		
	/**int getNbCles() :
	 * 		Return : le nombre de clefs que possede le joueur.
	 **/		
	public int getNbCles() {
		int res = 0;
		for (Objet o:items) {
			if (o instanceof Clef) 
				res++;
		}
		return res;
	}
	
	/**int getNbArtefacts() :
	 * 
	 * 		Return : le nombre d'artefact que possede le joueur.
	 **/
	public int getNbArtefacts() {
		int res = 0;
		for (Objet o:items) {
			if (o instanceof Artefact) 
				res++;
		}
		return res;
	}
	
	/**int getNbObjet() :
	 * 		Return : le nombre d'objets tous confondus que possede le joueur.
	 **/
	public int getNbObjet() {
		return items.size();
	}
		
	/**Arraylist getInventaire() :
		 * 		Return : l'inventaire items du joueur.
	 **/
	public ArrayList<Objet> getInventaire(){
		return this.items;
	}
		
	/**void enleveCles() :
	 * 		Clef c : une clef.
	 * 		int combien : un entier.
	 * 		Retire un nombre correspondant au parametre combien de clefs correspondant  a un type de clef de l'inventaire items du joueur.
	 **/
	public void enleveCles(Clef c, int combien) {
		int nb = 0;
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i) instanceof Clef) {
				if (c.cleEgales((Clef) this.items.get(i))) {
					this.items.remove(i);
					nb ++;
				}
			}
			if (nb == combien) {
				return;
			}
		}
	}
		
	/**void enleveArtefact() :
	 * 		Artefact a : un artefact.
	 * 		Retire un artefact a de l'inventaire items du joueur.
	 **/
	public void enleveArtefact(Artefact a) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i) instanceof Artefact) {
				if (a.artefactEgal((Artefact) this.items.get(i))) {
					this.items.remove(i);
				}
			}
		}
	}
}

