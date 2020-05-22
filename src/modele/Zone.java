package modele;

public class Zone {
	//Attributs
	//'ile' une ile.
	/** On conserve un pointeur vers la classe principale du modele. */
	private Ile ile;

	/** L'etat d'une Zone est donne par un booleen. */
	protected boolean etat;

	/**
	 * Pas envie de faire de getter/setter d'ou protected, un choix  a reconsiderer
	 * peut-etre
	 */
	protected Situation situation;
	//'element' l'element d'une zone parmi un type enumere
	private Element element;

	/**
	 * On stocke les coordonnees pour pouvoir les passer au modele lors de l'appel a
	 * [compteVoisines]. situation et element initialise a null car fait plus tard
	 * et plus facile a detecter si erreur
	 */
	//'x' et 'y' deux entiers correspondant a des coordonnees
	protected final int x, y;
	
	// On designe une zone comme etant l'heliport ou non a l'aide d'un boolean
	private boolean heliport;
	
	//Constructeur
	
	/**Zone() :
	 * 		Ile ile : une ile.
	 * 		int x : un entier formant une coordonnee avec un autre entier.
	 * 		int y : un entier formant une coordonnee avec un autre entier.
	 * 		Initialise l'attribut ile de la zone avec l'ile donnee en parametre, n'attribut pas d'etat particulier a la zone, initialise ses coordonnees
	 * 		x et y a l'aide des coordonnees donnees en parametre, lui attribut une situation normale et un element neutre.
	**/
	public Zone(Ile ile, int x, int y) {
		this.ile = ile;
		this.etat = false;
		this.x = x;
		this.y = y;
		this.situation = Situation.Normale;
		this.element = null;
	}

	//Methodes
	
	/**int getX() :
	 * 		Return : la coordonnee x de la zone.
	 **/
	public int getX() {
		return this.x;
	}
	
	/**int getY() :
	 * 		Return : la coordonnee y de la zone.
	 **/
	public int getY() {
		return this.y;
	}

	/**
	 * Le passage a la generation suivante se fait en deux etapes : - D'abord on
	 * calcule pour chaque Zone ce que sera sont etat a la generation suivante
	 * (methode [evalue]). On stocke le resultat dans un attribut supplementaire
	 * [prochainEtat]. - Ensuite on met a jour l'ensemble des cellules (methode
	 * [evolue]). Objectif : eviter qu'une evolution immediate d'une Zone pollue la
	 * decision prise pour une Zone voisine.
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
	 * Changer la visibilite, mettre les classes ailleurs peut peut-etre faciliter
	 * le code et se passer de ses petites fonctions (Un getter setter si Situation
	 * et Element a l'exterieur en public)
	 */
	/** Tout ce qui a un rapport avec la Situation */
	
	/**boolean estNormale() :
	 * 		Return : True si la situation de la zone est Normale, false sinon.
	 **/
	public boolean estNormale() {
		return this.situation == Situation.Normale;
	}

	/**boolean estInondee() :
	 * 		Return : True si la situation de la zone est Inondee, false sinon.
	 **/
	public boolean estInondee() {
		return this.situation == Situation.Inondee;
	}

	/**boolean estSubmergee() :
	 * 		Return : True si la situation de la zone est Submergee, false sinon.
	 **/
	public boolean estSubmergee() {
		return this.situation == Situation.Submergee;
	}

	//Tout ce qui a un rapport avec l'element
	
	/**Element getElement() :
	 * 		Return : l'element associe a la zone.
	 **/
	public Element getElement() {
		return element;
	}

	/**void setElement() :
	 * 		Element element : un element parmi un type enumere.
	 * 		Attribut l'element element a la zone.
	 **/
	public void setElement(Element element) {
		this.element = element;
	}

	/**boolean estAir() :
	 * 		Return : True si l'element de la zone est Air, false sinon.
	 **/
	public boolean estAir() {
		return this.getElement() == Element.Air;
	}

	/**boolean estEau() :
	 * 		Return : True si l'element de la zone est Eau, false sinon.
	 **/
	public boolean estEau() {
		return this.getElement() == Element.Eau;
	}

	/**boolean estTerre() :
	 * 		Return : True si l'element de la zone est Terre, false sinon.
	 **/
	public boolean estTerre() {
		return this.getElement() == Element.Terre;
	}

	/**boolean estFeu() :
	 * 		Return : True si l'element de la zone est Feu, false sinon.
	 **/
	public boolean estFeu() {
		return this.getElement() == Element.Feu;
	}

	/**boolean estNeutre() :
	 * 		Return : True si l'element de la zone est Neutre, false sinon.
	 **/
	public boolean estNeutre() {
		return this.getElement() == Element.Neutre;
	}
	
	//Autres
	
	/**String toString() :
	 * 		Return : Une chaine de caracteres correspondant a la zone, ses coordonnees, sa situation et son element.
	 **/
	public String toString() {
		return String.format("Zone de coordonnees x = %d et y = %d %n Situation: %s %n Element: %s%n", this.x, this.y,
				this.situation.name(), this.getElement().name());
	}
	
	/**boolean estUnDeplacementPossible() :
	 * 		Zone z : une zone.
	 * 		Return : True s'il est possible de se deplacer de la zone concernee a la zone z i.e. elles sont adjacentes, false sinon.
	 **/
	public boolean estUnDeplacementPossible(Zone z) {
		return Math.abs(this.x - z.x) == 1 && Math.abs(this.y - z.y) == 0 || Math.abs(this.x - z.x) == 0 && Math.abs(this.y - z.y) == 1;
	}
	
	/**boolean estAdjacente() :
	 * 		Zone z : une zone.
	 * 		Return : True si la zone z est adjacente a la zone concernee, false sinon.
	 **/
	public boolean estAdjacente(Zone z) {
		return Math.abs(this.x - z.x ) <= 1 && Math.abs(this.y - z.y) <=1;
	}
	
	/**boolean memeZone() :
	 * 		Zone z : une zone.
	 * 		Return : True si la zone z est la zone concernee, false sinon.
	 **/
	public boolean memeZone(Zone z) {
		return this==z;
	}
	
	/**boolean isHeliport() :
	 * 		Return : True si la zone concernee est l'heliport, false sinon.
	 **/
	public boolean isHeliport() {
		return this.heliport;
	}
	
	/**void setHeliport() :
	 * 		Definit la zone comme etant la zone de l'heliport.
	 **/
	public void setHeliport() {
		this.heliport = true;
	}

	/**Situation getSituation() :
	 * 		Return : la situation de la zone concernee.
	 **/
	public Situation getSituation() {
		return this.situation;
	}
}


