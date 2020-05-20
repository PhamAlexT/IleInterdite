package modele;

public class Artefact extends Objet {
	
	public Artefact(Element e) {
		super(e);
	}
	
	public boolean artefactEgal(Artefact a) {
		return this.e == a.e;
	}
}
