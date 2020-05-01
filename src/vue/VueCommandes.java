package vue;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import modele.Ile;
import controleur.ActionsJoueur;
import controleur.Controleur;
import controleur.DeplacementJoueur;

class VueCommandes extends JPanel {
	/**
	 * Pour que le bouton puisse transmettre ses ordres, on garde une r�f�rence au
	 * mod�le.
	 */
	private Ile modele;

	/** Constructeur. */
	public VueCommandes(Ile modele) {
		this.modele = modele;
		DeplacementJoueur depl = new DeplacementJoueur(modele);
		/**
		 * On cr�e un nouveau bouton, de classe [JButton], en pr�cisant le texte qui
		 * doit l'�tiqueter. Puis on ajoute ce bouton au panneau [this].
		 */
		/**
		 * Le bouton, lorsqu'il est cliqu� par l'utilisateur, produit un �v�nement, de
		 * classe [ActionEvent].
		 *
		 * On a ici une variante du sch�ma observateur/observ� : un objet impl�mentant
		 * une interface [ActionListener] va s'inscrire pour "�couter" les �v�nements
		 * produits par le bouton, et recevoir automatiquements des notifications.
		 * D'autres variantes d'auditeurs pour des �v�nements particuliers :
		 * [MouseListener], [KeyboardListener], [WindowListener].
		 *
		 * Cet observateur va enrichir notre sch�ma Mod�le-Vue d'une couche
		 * interm�diaire Contr�leur, dont l'objectif est de r�cup�rer les �v�nements
		 * produits par la vue et de les traduire en instructions pour le mod�le. Cette
		 * strate interm�diaire est potentiellement riche, et peut notamment traduire
		 * les m�mes �v�nements de diff�rentes fa�ons en fonction d'un �tat de
		 * l'application. Ici nous avons un seul bouton r�alisant une seule action,
		 * notre contr�leur sera donc particuli�rement simple. Cela n�cessite n�anmoins
		 * la cr�ation d'une classe d�di�e.
		 */
		/**
		 * Variante : une lambda-expression qui �vite de cr�er une classe sp�cifique
		 * pour un contr�leur simplissime.
		 */
		JButton boutonFinDuTour = new JButton("Fin du tour");
		this.add(boutonFinDuTour);
		boutonFinDuTour.addActionListener(e -> {
			modele.avance();
			this.modele.getJoueurs();
			System.out.println("Changement de tour !");
			for(int i=0; i<this.modele.getJoueurs().size(); i++) {
				this.modele.getJoueurs().get(i).resetEnergy();
				System.out.println("Nombre d'actions r�initialis� : " + this.modele.getJoueurs().get(i).getRemainingA());
			}
		});
		JButton test = new JButton("R�cup�rer un artefact");
		this.add(test);
		//TODO CHANGE NULL
		test.addActionListener(e-> { modele.recupererArtefact(null); });
	}
}

