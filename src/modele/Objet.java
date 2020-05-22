package modele;

public abstract class Objet {
	//Attributs
	//'e' un element parmi un type enumere.
	public final Element e;
	
	//Constructeur
	/**Objet() :
	 * 		Element e : un element parmi un type enumere.
	 * 		Definit e comme etant l'element de l'objet.
	 **/
	public Objet(Element e) {
		this.e = e;
	}
	
	//Methodes
	
	/**Element getElement() :
	 * 		Return : l'element de l'objet concerne.
	 **/
	public Element getElement() {
		return this.e;
	}
}
