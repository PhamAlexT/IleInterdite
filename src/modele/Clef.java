package modele;

import java.util.Random;

public class Clef extends Objet {
	
	//Attributs
	//'rand' un generateur d'entiers aleatoires.
	static Random rand = new Random();
	
	//Constructeur
	/**Clef() :
	 * 		Element e : un element parmi un type enumere.
	 * 		Definit e comme etant l'element de la clef.
	 **/
	public Clef(Element e) {
		super(e);
	}	
	
	//Methodes
	/**Clef aleaClefs() :
	 * 		Return : une clef possedant un element definit de maniere aleatoire.
	 **/
	public static Clef aleaClefs() {
		//L'element neutre est exclu.
		int elem = rand.nextInt(Element.values().length-1);
		Clef c = new Clef(Element.values()[elem]);
		return c;
	}

	/**boolean cleEgales() :
	 * 		Clef c : une clef.
	 * 		Return : True si la clef donne en parametre a le meme element que la clef considere, false sinon.
	 **/
	public boolean cleEgales(Clef c) {
		return this.e == c.e;
	}
	
	/**String toString() :
	 * 		Return : Une chaine de caracteres correspondant au mot cle suivit de l'element de la cle considere.
	 **/
	public String toString() {
		return "Cle " + e.toString();
	}
}
