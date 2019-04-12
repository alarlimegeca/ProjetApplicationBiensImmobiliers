package bien_immobilier;

public class Non_habitable extends Construit {

	public Non_habitable(int id_bien, String nom, int id_adresse, double surface,double transports, Typehabitation type_habitation, double surface_batie,int date_construction) {
		super(id_bien, nom, id_adresse, surface, transports,type_habitation, surface_batie, date_construction);
	}
	
}
