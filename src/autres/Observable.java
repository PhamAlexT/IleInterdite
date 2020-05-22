package autres;

import java.util.ArrayList;

public abstract class Observable {
    /**
     * On a une liste [observers] d'observateurs, initialement vide,  laquelle
     * viennent s'inscrire les observateurs via la mthode [addObserver].
     */
	//'observers' une liste d'observateurs
	private ArrayList<Observer> observers;
	//Constructeur
    /**Observable() :
	 * 		Creer une liste vide d'observateurs correspondant a l'attribut 'observers' de la classe
	 * 		a laquelle viennent s'inscrire differents observateurs via la methode addObserver().
	 **/
    public Observable() {
	this.observers = new ArrayList<Observer>();
    }
    
    //Methodes    
    /**void addObserver() :
	 * 		Observer o : un observateur
	 * 		Ajoute o a la liste d'observateurs attribut de la classe
	 **/
    public void addObserver(Observer o) {
	observers.add(o);
    }

    /**
     * Lorsque l'tat de l'objet observ change, il est convenu d'appeler la
     * methode [notifyObservers] pour prevenir l'ensemble des observateurs
     * enregistrs.
     * On le fait ici concrtement en appelant la mthode [update] de chaque
     * observateur.
     */
    /**void notifyObservers() :
	 * 		Previent l'ensemble des observateurs enregistres que l'etat de l'objet observe a change.
	 * 		Cela est fait de maniere concrete par l'appel de la methode update() de chaque observateur.
	 **/
    public void notifyObservers() {
	for(Observer o : observers) {
	    o.update();
	}
    }
}

