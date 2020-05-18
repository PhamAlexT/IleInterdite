package modele;

import modele.Zone.Element;

public class Artefact {

private Element element;
	
	public Artefact(Element e) {
		this.element = null;
	}
	
	public boolean artefactEgal(Artefact a) {
		return this.element == a.element;
	}
}
