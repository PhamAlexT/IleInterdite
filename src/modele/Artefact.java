package modele;

public class Artefact extends Objet {

private Element element;
	
	public Artefact(Element e) {
		super(e);
	}
	
	public boolean artefactEgal(Artefact a) {
		return this.element == a.element;
	}
}
