package modele;

import java.util.ArrayList;

public class Joueur {
	private Ile ile;
	private Zone z;
	private ArrayList<Object> items;
	public Joueur(Ile ile, Zone z) {
		this.ile = ile;
		this.z = z;
	}
	
	
	public void seDeplace(Zone nz) {
			this.z = nz;
	}

	public String toString() {
		return "--Joueur--\n" + z.toString() + "------------";
	}
	
	public Ile getIle() {
		return this.ile;
	}
	public Zone getZone() {
		return this.z;
	}
	public void recupereArtefact(Object truc) {
		items.add(truc);
	}
}
