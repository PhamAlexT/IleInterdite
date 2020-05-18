package modele;

import java.util.Random;

public class Clefs extends Objet {
	
	static Random rand = new Random();
	
	private Element element;
	
	public Clefs(Element e) {
		super(e);
	}	
	
	public static Clefs aleaClefs() {
		int elem = rand.nextInt(Element.values().length -1); //Borne droite exclu pour rappel
		
		Clefs c = new Clefs(Element.values()[elem]);
		
		return c;
	}

	public Element getElement() {
		return this.element;
	}

	public boolean cleEgales(Clefs c) {
		return this.element == c.element;
	}
	
	public String toString() {
		return "Cle";
	}
}
