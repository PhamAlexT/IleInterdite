package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import modele.Ile;

public class CVue {
    /**
     * JFrame est une classe fournie pas Swing. Elle représente la fenêtre
     * de l'application graphique.
     */
    private JFrame frame;
    /**
     * VueGrille et VueCommandes sont deux classes définies plus loin, pour
     * nos deux parties de l'interface graphique.
     */
    private VueGrille grille;
    private VueCommandes commandes;

    /** Construction d'une vue attachée à un modèle. 
     * @throws IOException */
    public CVue(Ile modele) throws IOException {
	/** Définition de la fenêtre principale. */
	frame = new JFrame();
	frame.setTitle("Ile Interdite");
	frame.setIconImage(ImageIO.read(new File("res/Icone.png")));
	frame.setLayout(new BorderLayout());

	/** Définition des deux vues et ajout à la fenêtre. */
	grille = new VueGrille(modele);
	frame.add(grille);
	commandes = new VueCommandes(modele);
	frame.add(commandes,BorderLayout.SOUTH);
	/**
	 * Fin de la plomberie :
	 *  - Ajustement de la taille de la fenêtre en fonction du contenu.
	 *  - Indiquer qu'on quitte l'application si la fenêtre est fermée.
	 *  - Préciser que la fenêtre doit bien apparaître à l'écran.
	 */
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }
}

