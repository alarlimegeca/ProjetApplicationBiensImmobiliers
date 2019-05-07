private double commerce;
	private double ecole;	
	
	public Non_habitable(int id_bien, String nom, boolean en_ligne, Adresse adresse, double surface,double transports, TypeHabitation type_habitation, double surface_batie,int date_construction, double commerce,double ecole) {
		super(id_bien, nom, en_ligne, adresse, surface, transports,type_habitation, surface_batie, date_construction);
		this.commerce = commerce;
		this.ecole = ecole;
	}
	public void ajouterBien_immo_NonHab() {
		 {
		try {
			String type_hab= getType_habitation().getContenu2();
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO non_habitable(id_bien,nom,id_adresse,surface,transports,commerce,ecole,type_habitation,surface_batie,date_construction,en_ligne) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			preparedState.setInt(1,getId_bien()); 
			preparedState.setString(2,getNom()); 
			preparedState.setDouble(3,getAdresse().getId_adresse()); 
			preparedState.setDouble(5,getTransports()); 
			preparedState.setString(6,type_hab); 
			preparedState.setDouble(7,commerce);
			preparedState.setDouble(8,ecole); 
			preparedState.setString(6,type_hab); 
			preparedState.setDouble(9,getSurface_batie()); 
			preparedState.setDouble(4,getSurface() ); 
			preparedState.setInt(10,getDate_construction());
			preparedState.setBoolean(11,isEn_ligne());
			
			
			System.out.println(preparedState.toString());

			preparedState.executeUpdate();

			preparedState.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	}
	
	public String toString(){
	    String str;
	      str = "Description bien \n";
	      str += "Nom : " + this.getNom() + "\n";
	      str += "Type : " + this.getType_habitation().getContenu2()+ "\n";
	      str += "Distance aux transports en commun : " + this.getTransports() +" km\n";
	      str += "Année de construction : " + this.getDate_construction() + "\n";
	      str += "Surface du bien : " + this.getSurface() + "\n";
	      str += "Surface batie : " + this.getSurface_batie() + "\n";
	      return str;
	    }	
	public double estimation_Nhab() {
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
		
String TypeTrans=Transaction.type_transaction.getContenu3();
		
		if (TypeTrans=="Vente") {return surface*m2*dmoy*ds;}
		if (TypeTrans=="Location") {return (1/230)*surface*m2*dmoy*ds;}
		else {return (0.003393009)*surface*m2*dmoy*ds;}
		
	}


}
