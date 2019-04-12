package interactions;

import individus.Agent_immobilier;
import individus.Respo_agence;

public class Notation {

	public double note;
	public String date;
	public static Respo_agence leresponsable;
	public static Agent_immobilier lagent;
	
	public Notation(int note, String date, Respo_agence leresponsable, Agent_immobilier lagent) {
		super();
		this.note=note;
		this.date=date;
		Notation.leresponsable=leresponsable;
		Notation.lagent=lagent;
	}
	
	public double getNote(){
		return this.note;
	}
	
	public void setNote(int note) {
		this.note=note;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String date) {
		this.date=date;
	}
	
}
