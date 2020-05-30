package jeux;

import java.awt.EventQueue;
import java.io.IOException;

import modele.DeroulementPartie;
import modele.Ile;
import vue.CVue;

public class IleInterdite {
    public static void main(String[] args) {
	/**
	 * Pour les besoins du jour on considere la ligne EvenQueue... comme une
	 * incantation qu'on pourra expliquer plus tard.
	 */
	EventQueue.invokeLater(() -> {
		/** Voici le contenu qui nous interesse. */
                Ile modele = new Ile();
                DeroulementPartie dp = new DeroulementPartie(modele);
                
                try {
					CVue vue = new CVue(modele,dp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    });
    }        
}


