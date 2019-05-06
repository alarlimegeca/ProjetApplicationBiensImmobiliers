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
	public void ajouterBien_immo_Hab() {
		try {
			String type_hab= type_habitation.getContenu2();
			String env=environnement.getContenu1();
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO bien_immobilier(id_bien,nom,id_adresse,surface,transports,type_hab,commerce,ecole,surface_batie,date_construction,nombre_pieces,nombre_sallesdeau,jardin,env) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedState.setInt(1,id_bien); 
			preparedState.setString(2,nom); 
			preparedState.setDouble(3,id_adresse); 
			preparedState.setDouble(5,transports); 
			preparedState.setString(6,type_hab);
			preparedState.setDouble(10,surface_batie); 
			preparedState.setDouble(8,commerce); 
			preparedState.setDouble(9,ecole); 
			preparedState.setDouble(4,surface ); 
			preparedState.setInt(11,date_construction); 
			preparedState.setInt(12,nombre_pieces ); 
			preparedState.setInt(13,nombre_sallesdeau ); 
			preparedState.setDouble(14,jardin ); 
			preparedState.setString(15, env);
			
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
		double dc=0;
		double de=0;
		double dp=0;
		double dsa=0;
		double dj=0;
		double m2=0;
		
		if (transports<0.5) {dt=1.2;}
		if (transports>=0.5 & transports<1 ){dt=1.1;}
		if (transports>=1 & transports<2 ){dt=1;}
		if (transports>=2 ){dt=0.9;}
		
		if (commerce<1) {dc=1.15;}
		if (commerce>=1 & commerce<5) {dc=1.1;}
		if (commerce>=5 & commerce<10) {dc=1.05;}
		if (commerce>=10) {dc=1;}
		
		if (ecole<1) {de=1.15;}
		if (ecole>=1 & ecole<5) {de=1.1;}
		if (ecole>=5 & ecole<10) {de=1.05;}
		if (ecole>=10) {de=1;}
		
		if (nombre_pieces==1) {dp=1;}
		if (nombre_pieces>=2 & nombre_pieces<=4) {dp=1.1;}
		if (nombre_pieces>4){dp=1.2;}
		
		if (nombre_sallesdeau==1){dsa=1;}
		if (nombre_sallesdeau==2){dsa=1.15;}
		if (nombre_sallesdeau>=3){dsa=1.2;}
		
		if (jardin==0) {dj=1;}
		if (jardin!=0 & jardin<surface) {dj=1.1;}
		if (jardin>=surface & jardin<2*surface) {dj=1.2;}
		if (jardin>=2*surface) {dj=1.3;}
		
		if(env=="Ville") {m2=8000;}
		if(env=="Banlieue") {m2=4000;}
		if(env=="Campagne") {m2=1500;}
		
		double dmoy= (dt+dc+de+dp+dsa+dj)/6;
		
		String TypeTrans=Transaction.type_transaction.getContenu3();
		
		if (TypeTrans=="Vente") {return surface*m2*dmoy;}
		if (TypeTrans=="Location") {return (1/230)*surface*m2*dmoy;}
		if (TypeTrans=="Vente_viager") {return (0.003393009)*surface*m2*dmoy;}
		
	}
}

