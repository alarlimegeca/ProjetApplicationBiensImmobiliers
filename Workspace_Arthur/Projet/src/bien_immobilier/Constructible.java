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

		public void ajouterBien_immo_Constr() {
		try {
			String type_hab= getType_habitation().getContenu2();
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO constructible(id_bien,nom,id_adresse,surface,transports,type_habitation,qualite_terrain,commerce,ecole,en_ligne) VALUES (?,?,?,?,?,?,?,?,?,?)");
			preparedState.setInt(1, getId_bien()); 
			preparedState.setString(2, getNom()); 
			preparedState.setInt(3, getAdresse().getId_adresse()); 
			preparedState.setDouble(5,getTransports()); 
			preparedState.setString(6,type_hab); 
			preparedState.setInt(7, qualite_terrain); 
			preparedState.setDouble(8,commerce); 
			preparedState.setDouble(9, ecole); 
			preparedState.setDouble(4,getSurface() ); 
			preparedState.setBoolean(10,isEn_ligne() );
			
			System.out.println(preparedState.toString());

			preparedState.executeUpdate();

			preparedState.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
		
	 public String toString(){
		    String str;
		      str = "Description bien \n";
		      str += "Nom : " + this.getNom() + "\n";
		      str += "Type : " + this.getType_habitation().getContenu2()+ "\n";
		      str += "Distance aux transports en commun : " + this.getTransports() +" km\n";
		      str += "Distance à l'école la plus proche : " + this.ecole  +  " km\n";
		      str += "Distance aux commerces : " + this.commerce + " km\n";
		      str += "Qualité terrain : " + this.qualite_terrain + "\n";
		      str += "Surface du bien : " + this.getSurface() + "\n";
		      return str;
		    }	
	
	public double estimation_Cons() {
		
		String env=environnement.getContenu1();
		
		double dt=0;
		double dc=0;
		double de=0;
		double dq=0;
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
		
		if (qualite_terrain==1) {dq=1.2;}
		if (qualite_terrain==2) {dq=1.1;}
		if (qualite_terrain==3) {dq=1;}
		if (qualite_terrain==4) {dq=0.9;}
		if (qualite_terrain==5) {dq=0.8;}
		
		if(env=="Ville") {m2=15000;}
		if(env=="Banlieue") {m2=1000;}
		if(env=="Campagne") {m2=200;}
		
		double dmoy= (dt+ dc + dq + de)/4;
		
		String TypeTrans=Transaction.type_transaction.getContenu3();
		
		if (TypeTrans=="Vente") {return surface*m2*dmoy;}
		if (TypeTrans=="Location") {return (1/230)*surface*m2*dmoy;}
		else {return (0.003393009)*surface*m2*dmoy;}
	}
		
	public static void main(String[] args) {
		TypeHabitation type_habitation=TypeHabitation.Appartement;
		int id_bien=2;
		int id_adresse=8;
		String nom="maisonbleue";
		double transports=9.4;
		int qualite_terrain=2;
		double commerce=7;
		double ecole=5;
		double surface=56;
		Constructible.ajouterBien_immo_Constr(type_habitation, id_bien, nom, id_adresse, transports, qualite_terrain, ecole, commerce, surface);
	}
}

