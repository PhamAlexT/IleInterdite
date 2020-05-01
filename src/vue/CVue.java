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
     * JFrame est une classe fournie pas Swing. Elle repr�sente la fen�tre
     * de l'application graphique.
     */
    private JFrame frame;
    /**
     * VueGrille et VueCommandes sont deux classes d�finies plus loin, pour
     * nos deux parties de l'interface graphique.
     */
    private VueGrille grille;
    private VueCommandes commandes;

    /** Construction d'une vue attach�e � un mod�le. 
     * @throws IOException */
    public CVue(Ile modele) throws IOException {
	/** D�finition de la fen�tre principale. */
	frame = new JFrame();
	frame.setTitle("Ile Interdite");
	frame.setIconImage(ImageIO.read(new File("res/Icone.png")));
	frame.setLayout(new BorderLayout());

	/** D�finition des deux vues et ajout � la fen�tre. */
	grille = new VueGrille(modele);
	frame.add(grille);
	commandes = new VueCommandes(modele);
	frame.add(commandes,BorderLayout.SOUTH);
	/**
	 * Fin de la plomberie :
	 *  - Ajustement de la taille de la fen�tre en fonction du contenu.
	 *  - Indiquer qu'on quitte l'application si la fen�tre est ferm�e.
	 *  - Pr�ciser que la fen�tre doit bien appara�tre � l'�cran.
	 */
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }
}

