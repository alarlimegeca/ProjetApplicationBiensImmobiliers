package bien_immobilier;


public abstract class Construit extends Bien_immobilier {
	
	private double surface_batie;
	private int date_construction;
	
	public Construit (int id_bien, String nom, boolean en_ligne, Adresse adresse, double surface,double transports, TypeHabitation type_habitation, double surface_batie,int date_construction) {
		super(id_bien, nom, en_ligne, adresse, surface, transports, type_habitation);
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




}

