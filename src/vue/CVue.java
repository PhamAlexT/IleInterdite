package vue;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.DeroulementPartie;
import modele.Ile;

public class CVue extends JFrame {
	//Attributs
    /**
     * JFrame est une classe fournie pas Swing. Elle represente la fenetre
     * de l'application graphique.
     */
	//'frame' une frame de la classe JFrame.
    private JFrame frame;
    /**
     * VueGrille et VueCommandes sont deux classes definies plus loin, pour
     * nos deux parties de l'interface graphique.
     */
    //'grille' une VueGrille.
    private VueGrille grille;
    //'commandes' une VueCommandes.
    private VueCommandes commandes;
    //'vuej' une VueJoueur.
    private VueJoueur vuej;
    //'vuei' une VueInventaire.
    private VueInventaire vuei;
        
    //Constructeur
    /**
     * @throws IOException
     **/
    /**CVue() :
	 * 		Ile modele : une ile.
	 * 		DeroulementPartie dp : une partie.
	 * 		Construction d'une vue attachee a un modele. 
	 **/
    public CVue(Ile modele, DeroulementPartie dp) throws IOException {
	//Definition de la fenetre principale.
	frame = new JFrame();
	this.frame.setTitle("Ile Interdite");
	this.frame.setIconImage(ImageIO.read(new File("res/Icone.png")));
	this.frame.setLayout(new BorderLayout());
	this.frame.setResizable(false);
	
	
	//Definition des vues et ajout a la fenetre.
	grille = new VueGrille(modele,dp);
	this.frame.add(grille);
	commandes = new VueCommandes(modele,dp);
	commandes.setVue(this);
	this.frame.add(commandes,BorderLayout.SOUTH);
	commandes.setGrille(grille);
	

	vuej = new VueJoueur(dp);
	commandes.setVueJoueur(vuej);
	this.frame.add(vuej,BorderLayout.PAGE_START);
	
	vuei = new VueInventaire(modele);
	this.frame.add(vuei,BorderLayout.EAST);
	
	 //Fin de la plomberie :
	 // - Ajustement de la taille de la fenetre en fonction du contenu.
	 // - Indiquer qu'on quitte l'application si la fenetre est fermee.
	 // - Preciser que la fenetre doit bien apparaitre a l'ecran.
	this.frame.pack();
	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.frame.setVisible(true);
    }
    
    //Ecran de victoire
    public void ecranVictoire() {
    	this.frame.getContentPane().removeAll();
    	this.frame.repaint();
    	this.frame.add(new EcranDeFin("Victoire"));
    }
    
    //Ecran de défaite
    public void ecranDefaite() {
    	this.frame.getContentPane().removeAll();
    	this.frame.repaint();
    	this.frame.add(new EcranDeFin("Défaite"));
    }
}


