package modele;

import java.util.ArrayList;

public class Joueur {
	private Ile ile;
	private Zone z;
	private ArrayList<Object> items;
	private int remainingA = 3;
	
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
	public int getRemainingA() {
		return this.remainingA;
	}
	
	public void decrease() {
		this.remainingA = this.remainingA -1;
	}
	
	public boolean action() {
		if(getRemainingA() > 0) {
			decrease();
			return true;
		}else {
			return false;
		}
	}
	
	public void resetEnergy() {
		this.remainingA = 3;
	}
}

