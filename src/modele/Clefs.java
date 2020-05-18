package modele;

import modele.Zone.Element;
import java.util.Random;

public class Clefs {
	
	static Random rand = new Random();
	
	private Element element;
	
	public Clefs(Element e) {
		this.element = e;
	}	
	
	public static Clefs aleaClefs() {
		int elem = rand.nextInt(5);
		Clefs c = new Clefs(null);
		if(elem == 0) {
			c.element = Element.Air;
		}
		if(elem == 1) {
			c.element = Element.Eau;
		}
		if(elem == 2) {
			c.element = Element.Terre;
		}
		if(elem == 3) {
			c.element = Element.Feu;
		}
		return c;
	}

	public Element getElement() {
		return this.element;
	}

	public boolean cleEgales(Clefs c) {
		return this.element == c.element;
	}
}
