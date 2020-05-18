package vue;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import modele.DeroulementPartie;
import modele.Ile;

public class CVue extends JFrame {
    /**
     * JFrame est une classe fournie pas Swing. Elle represente la fenetre
     * de l'application graphique.
     */
    //private JFrame frame;
    /**
     * VueGrille et VueCommandes sont deux classes definies plus loin, pour
     * nos deux parties de l'interface graphique.
     */
    private VueGrille grille;
    private VueCommandes commandes;
    private VueJoueur vuej;
    private VueInventaire vuei;
        
    /** Construction d'une vue attachee a un modele. 
     * @throws IOException */
    public CVue(Ile modele, DeroulementPartie dp) throws IOException {
	/** Definition de la fenetre principale. */
	//frame = new JFrame();
	this.setTitle("Ile Interdite");
	this.setIconImage(ImageIO.read(new File("res/Icone.png")));
	this.setLayout(new BorderLayout());
	
	/** Definition des deux vues et ajout a la fenetre. */
	grille = new VueGrille(modele,dp);
	this.add(grille);
	commandes = new VueCommandes(modele,dp);
	this.add(commandes,BorderLayout.SOUTH);
	commandes.setGrille(grille);
	

	vuej = new VueJoueur(dp);
	commandes.setVueJoueur(vuej);
	this.add(vuej,BorderLayout.PAGE_START);
	
	vuei = new VueInventaire(modele);
	//this.add(vuei,BorderLayout.EAST);
	
	/**
	 * Fin de la plomberie :
	 *  - Ajustement de la taille de la fenetre en fonction du contenu.
	 *  - Indiquer qu'on quitte l'application si la fenetre est fermee.
	 *  - Preciser que la fenetre doit bien apparaitre � l'ecran.
	 */
	this.pack();
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    
}

