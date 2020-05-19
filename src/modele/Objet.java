package modele;

public abstract class Objet {
	Element e;
	
	public Objet(Element e) {
		this.e = e;
	}
	
	public Element getElement() {
		return this.e;
	}
}
