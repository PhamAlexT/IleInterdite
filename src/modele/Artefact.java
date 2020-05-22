package modele;

public class Artefact extends Objet {
	
	//Constructeur
	/**Artefact() :
	 * 		Element e : un element parmi un type enumere.
	 * 		Definit e comme etant l'element de l'artefact.
	 **/
	public Artefact(Element e) {
		super(e);
	}
	
	//Methodes
	/**boolean artefactEgal() :
	 *  	Artefact a : un artefact.
	 * 		Return : True si l'artefact donne en parametre a le meme element que l'artefact considere, false sinon.
	 **/
	public boolean artefactEgal(Artefact a) {
		return this.e == a.e;
	}
}
