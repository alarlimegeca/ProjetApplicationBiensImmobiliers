package bien_immobilier;

public class Constructible extends Bien_immobilier {
	
	public int qualite_terrain;
	public double commerce;
	public double ecole;

	public Constructible (int id_bien, String nom, int id_adresse, double surface, double transports, Typehabitation type_habitation, int qualite_terrain,double commerce, double ecole) {
		super(id_bien, nom, id_adresse, surface,transports, type_habitation);
		this.qualite_terrain=qualite_terrain;
		this.commerce=commerce;
		this.ecole=ecole;
		
	}
	
	public int getQualite_terrain() {
		return qualite_terrain;
	}
	public void setQualite_terrain(int qualite_terrain) {
		this.qualite_terrain=qualite_terrain;
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

	@Override
	public void estimation(String DB_URL) {
		
		}
}

