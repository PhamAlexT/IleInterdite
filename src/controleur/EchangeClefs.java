package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import modele.Clefs;
import modele.Element;
import modele.Joueur;

public class EchangeClefs extends ActionJoueur implements ActionListener {

	static Random rand = new Random();
	
	
	public EchangeClefs(Joueur j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	public void echange (Joueur j, ArrayList<Joueur> joueurs) {
		int donne = 0;
		if (j.getNbCles() != 0) {
			for (Joueur j2: joueurs) {
				if (j.getZone().memeZone(j2.getZone()) && (j.getNb() != j2.getNb())) {
					//le joeueur qui joue donne une cle a l'autre joueur present sur sa case
					while(donne !=1) {
						//choix aleatoire de l'artefact
						int alea = rand.nextInt(j.getInventaire().size());
						//on verifie que ces un object de type artefact
						if (j.getInventaire().get(alea) instanceof Clefs) {
							Clefs c  = (Clefs) j.getInventaire().get(alea);
							//on l'ajoute au joueur
							j2.recupereArtefact(c);
							System.out.println("Le Joueur " + j2.getNb() + " recoit une cle " + c.getElement());
							//on l'enleve a celui qui lui a donne
							j.enleveCles(c);
							//echange effectue 
							donne = 1;
						}
					}
				}
			}
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
