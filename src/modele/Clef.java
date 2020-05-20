package modele;

import java.util.Random;

public class Clef extends Objet {
	
	static Random rand = new Random();
	
	public Clef(Element e) {
		super(e);
	}	
	
	public static Clef aleaClefs() {
		int elem = rand.nextInt(Element.values().length-1); //L'élement neutre est exclue.
		Clef c = new Clef(Element.values()[elem]);
		
		return c;
	}

	public boolean cleEgales(Clef c) {
		return this.e == c.e;
	}
	
	public String toString() {
		return "Cle " + e.toString();
	}
}
