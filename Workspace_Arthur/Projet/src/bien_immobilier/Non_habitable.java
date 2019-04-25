package bien_immobilier;

public class Non_habitable extends Construit {

	public Non_habitable(int id_bien, String nom, int id_adresse, double surface,double transports, Typehabitation type_habitation, double surface_batie,int date_construction) {
		super(id_bien, nom, id_adresse, surface, transports,type_habitation, surface_batie, date_construction);
	}
	public void ajouterBien_immo_NonHab() {
		try {
			String type_hab= type_habitation.getContenu2();
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO bien_immobilier(id_bien,nom,id_adresse,surface,transports,type_hab,surface_batie,date_construction) VALUES (?,?,?,?,?,?,?,?)");
			preparedState.setInt(1,id_bien); 
			preparedState.setString(2,nom); 
			preparedState.setDouble(3,id_adresse); 
			preparedState.setDouble(5,transports); 
			preparedState.setString(6,type_hab); 
			preparedState.setDouble(10,surface_batie); 
			preparedState.setDouble(4,surface ); 
			preparedState.setInt(11,date_construction); 
			
			
			System.out.println(preparedState.toString());

			preparedState.executeUpdate();

			preparedState.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	public double estimation(String DB_URL, int id_bien) {
		String env=environnement.getContenu1();
		
		double dt=0;
		double ds=0;
		double dct=0;
		double m2=0;
		
		if (transports<0.5) {dt=1.2;}
		if (transports>=0.5 & transports<1 ){dt=1.1;}
		if (transports>=1 & transports<2 ){dt=1;}
		if (transports>=2 ){dt=0.9;}
		
		ds= surface_batie/surface;
		
		if(date_construction<=5) {dct=1.05;}
		if(date_construction>5 & date_construction<=20) {dct=1;}
		if(date_construction>20) {dct=0.95;}
		
		if(env=="Ville") {m2=4000;}
		if(env=="Banlieue") {m2=1000;}
		if(env=="Campagne") {m2=800;}
		
		double dmoy= (dt+ ds + dct)/3;
		return surface*m2*dmoy*ds;
	}

}
