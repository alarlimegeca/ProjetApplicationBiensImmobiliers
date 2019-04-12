package bien_immobilier;

public class Habitable extends Construit {
	
	public int nombre_pieces;
	public int nombre_sallesdeau;
	public double commerce;
	public double ecole;
	public double jardin;
	
	public Habitable (int id_bien, String nom, int id_adresse, double surface,double transports, Typehabitation type_habitation, double surface_batie,int date_construction, int nombre_pieces, int nombre_sallesdeau, double commerce, double ecole, double jardin) {
		super( id_bien,  nom, id_adresse, surface, transports, type_habitation, surface_batie, date_construction);
		this.nombre_pieces=nombre_pieces;
		this.nombre_sallesdeau=nombre_sallesdeau;
		this.commerce=commerce;
		this.ecole=ecole;
		this.jardin=jardin;
	}
	
	public double getCommerce () {
		return commerce;
	}
	public void setCommerce(double commerce) {
		this.commerce=commerce;
	}
	public double getEcole() {
		return ecole;
	}
	public void setEcole(double ecole) {
		this.ecole=ecole;
	}
	public int getNombre_pieces() {
		return nombre_pieces;
	}
	public void setNombre_pieces(int nombre_pieces) {
		this.nombre_pieces=nombre_pieces;
	}
	public int getNombre_sallesdeau() {
		return nombre_sallesdeau;
	}
	public void setNombre_sallesdeau(int nombre_sallesdeau) {
		this.nombre_sallesdeau=nombre_sallesdeau;
	}
	public double getJardin() {
		return jardin;
	}
	public void setJardin (double jardin) {
		this.jardin=jardin;
	}
}

