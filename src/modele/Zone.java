package modele;

enum Situation {
	Normale, Inondee, Submergee
}

public class Zone {

	public enum Element {
		Air, Eau, Terre, Feu, Neutre
	}

	/** On conserve un pointeur vers la classe principale du mod�le. */
	private Ile ile;

	/** L'�tat d'une Zone est donn� par un bool�en. */
	protected boolean etat;

	/**
	 * Pas envie de faire de getter/setter d'o� protected, un choix � re-consid�rer
	 * peut-�tre
	 */
	protected Situation situation;
	private Element element;

	/**
	 * On stocke les coordonn�es pour pouvoir les passer au mod�le lors de l'appel �
	 * [compteVoisines]. situation et element initialis� � null car fait plus tard
	 * et plus facile � d�tecter si erreur
	 */
	protected final int x, y;

	public Zone(Ile ile, int x, int y) {
		this.ile = ile;
		this.etat = false;
		this.x = x;
		this.y = y;
		this.situation = Situation.Normale;
		this.element = null;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	/**
	 * Le passage � la g�n�ration suivante se fait en deux �tapes : - D'abord on
	 * calcule pour chaque Zone ce que sera sont �tat � la g�n�ration suivante
	 * (m�thode [evalue]). On stocke le r�sultat dans un attribut suppl�mentaire
	 * [prochainEtat]. - Ensuite on met � jour l'ensemble des cellules (m�thode
	 * [evolue]). Objectif : �viter qu'une �volution imm�diate d'une Zone pollue la
	 * d�cision prise pour une Zone voisine.
	 */

	/* On peut jouer avec les indices et Enum.values() OPTION A CONSIDERE */
	public void progresse() {
		if (this.situation == Situation.Normale) {
			this.situation = Situation.Inondee;
		} else if (this.situation == Situation.Inondee) {
			this.situation = Situation.Submergee;
		}
	}

	/**
	 * Changer la visibilit�, mettre les classes ailleurs peut peut-�tre faciliter
	 * le code et se passer de ses petites fonctions (Un getter setter si Situation
	 * et Element � l'ext�rieur en public)
	 */
	/** Tout ce qui a un rapport avec la Situation */
	public boolean estNormale() {
		return this.situation == Situation.Normale;
	}

	public boolean estInondee() {
		return this.situation == Situation.Inondee;
	}

	public boolean estSubmergee() {
		return this.situation == Situation.Submergee;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	/** Tout ce qui a un rapport avec l'�l�ment */

	public boolean estAir() {
		return this.getElement() == Element.Air;
	}

	public boolean estEau() {
		return this.getElement() == Element.Eau;
	}

	public boolean estTerre() {
		return this.getElement() == Element.Terre;
	}

	public boolean estFeu() {
		return this.getElement() == Element.Feu;
	}

	public String toString() {
		return String.format("Zone de coordonn�es x = %d y = %d \nSituation: %s \nElement: %s\n", this.x, this.y,
				this.situation.name(), this.getElement().name());
	}
	
	public boolean estAdjacente(Zone z) {
		return Math.abs(this.x - z.x) == 1 && Math.abs(this.y - z.y) == 0 || Math.abs(this.x - z.x) == 0 && Math.abs(this.y - z.y) == 1;
	}

}

