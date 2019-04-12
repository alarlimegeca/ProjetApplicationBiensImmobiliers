package interactions;

import bien_immobilier.Bien_immobilier;
import bien_immobilier.Habitable;
import bien_immobilier.Typehabitation;

public class Annonce {
	
	private String titre;
	private boolean valide;
	private Bien_immobilier lebien;
	
	public Annonce(String titre, boolean valide, Bien_immobilier lebien) {
		super();
		this.titre = titre;
		this.setValide(valide);
		this.lebien=lebien;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public boolean getValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}
	

	
	public static void main(String[] args) {
		Bien_immobilier jolimaison=new Habitable (00001,"joli maison",77420,100,0.24,Typehabitation.Maison, 80,2018, 10, 1, 0.21, 0.3, 0.4);
		Annonce annonce1 = new Annonce("Vend maison en bord de Marne",false,jolimaison);
		System.out.println(annonce1.getTitre()+" Ce bien est : "+  annonce1.lebien.getType_habitation());


	}





	
}

