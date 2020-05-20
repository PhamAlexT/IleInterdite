package modele;

public abstract class Objet {
	public final Element e;
	
	public Objet(Element e) {
		this.e = e;
	}
	
	public Element getElement() {
		return this.e;
	}
}
