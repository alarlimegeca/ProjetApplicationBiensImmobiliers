package bien_immobilier;

public abstract class Bien_immobilier {
	public int id_bien;
	public String nom;
	public int id_adresse;
	public double surface;
	public double transports;
	public Typehabitation type_habitation;
	
	
	public Bien_immobilier(int id_bien, String nom, int id_adresse, double surface,double transports, Typehabitation type_habitation) {
		super();
		this.id_bien= id_bien;
		this.nom=nom;
		this.id_adresse=id_adresse;
		this.surface=surface;
		this.transports=transports;
		this.type_habitation=type_habitation;
		}
	public int getId_bien() {
		return id_bien;
	}
	public void setid_bien(int id_bien) {
		this.id_bien=id_bien;
	}
	public String getNom() {
		return nom;
	}
	public void setID_adresse(int id_adresse) {
		this.id_adresse=id_adresse;
	}
	public int getID_adresse() {
		return id_adresse;
	}
	public void setNom (String nom) {
		this.nom=nom;
	}
	public double getSurface() {
		return surface;
	}
	public void setSurface (double surface) {
		this.surface=surface;
	}
	public double getTransports() {
		return transports;
	}
	public void setTransports (double transports) {
		this.transports=transports;
	}
	public Typehabitation getType_habitation() {
		return type_habitation;
	}
	public void setType_habitation(Typehabitation type_habitation) {
		this.type_habitation=type_habitation;
	}
	
	
	static final String DB_URL = "jdbc:postgresql://localhost:5432/gestion_immobilier"; 
	static final String USER = "postgres"; 
	static final String PASS = "postgres";  
	
	public static void main(String[] args) throws SQLException {
	
	Connection conn = null;
	
	try{
	Class.forName("org.postgresql.Driver"); 
	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	System.out.println("Connexion etablie avec succes !");
	}
	
	catch(Exception e){
	e.printStackTrace(); 
	}
	finally{
	if(conn != null){
	conn.close(); 
		}
	}
}
	
	public abstract void estimation(String DB_URL);
		
}

