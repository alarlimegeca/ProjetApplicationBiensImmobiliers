package bien_immobilier;

public class Construit extends Bien_immobilier {
	
	public double surface_batie;
	public int date_construction;
	
	public Construit (int id_bien, String nom, int id_adresse, double surface,double transports, Typehabitation type_habitation, double surface_batie,int date_construction) {
		super(id_bien, nom, id_adresse, surface, transports, type_habitation);
		this.surface_batie=surface_batie;
		this.date_construction=date_construction;
	}
	
	public double getSurface_batie() {
		return surface_batie;
	}
	public void setSurface_bâtie(double surface_batie) {
		this.surface_batie=surface_batie;
	}
	public int getDate_construction () {
		return date_construction;
		}
	public void setDate_construction(int date_construction) {
		this.date_construction=date_construction;
	}

	@Override
	public void estimation(String DB_URL) {
		// TODO Auto-generated method stub
		
	}
}
