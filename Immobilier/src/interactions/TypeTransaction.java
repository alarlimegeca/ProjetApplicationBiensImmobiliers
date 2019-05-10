package interactions;

import bien_immobilier.Environnement;

public enum TypeTransaction {
	Vente("Vente","Vente"),
	Location("Location","Location"),
	Vente_viager("Vente en viager","Vente en viager");
	
	private String name="";
	private String contenu="";
	
	TypeTransaction(String name,String contenu){
		this.name=name;
		this.contenu=contenu;
	}
	public String getContenu3() {
		return contenu;
	}
	
	public static TypeTransaction parseTransaction(String transaction) {
		if (transaction.equals("Vente")) {
			return Vente;
		}
		if (transaction.equals("Location")) {
			return Location;
		}
		else {
			return Vente_viager;
		}
	}
}

