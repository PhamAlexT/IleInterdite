package autres;

import java.util.ArrayList;

public abstract class Observable {
    /**
     * On a une liste [observers] d'observateurs, initialement vide,  laquelle
     * viennent s'inscrire les observateurs via la mthode [addObserver].
     */
    private ArrayList<Observer> observers;
    public Observable() {
	this.observers = new ArrayList<Observer>();
    }
    public void addObserver(Observer o) {
	observers.add(o);
    }

    /**
     * Lorsque l'tat de l'objet observ change, il est convenu d'appeler la
     * mthode [notifyObservers] pour prvenir l'ensemble des observateurs
     * enregistrs.
     * On le fait ici concrtement en appelant la mthode [update] de chaque
     * observateur.
     */
    public void notifyObservers() {
	for(Observer o : observers) {
	    o.update();
	}
    }
}

