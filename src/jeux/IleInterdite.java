package jeux;

import java.awt.EventQueue;

import modele.Ile;
import vue.CVue;

public class IleInterdite {
    public static void main(String[] args) {
	/**
	 * Pour les besoins du jour on considère la ligne EvenQueue... comme une
	 * incantation qu'on pourra expliquer plus tard.
	 */
	EventQueue.invokeLater(() -> {
		/** Voici le contenu qui nous intéresse. */
                Ile modele = new Ile();
                CVue vue = new CVue(modele);
	    });
    }
}

