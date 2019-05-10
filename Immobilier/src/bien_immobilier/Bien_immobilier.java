package bien_immobilier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Bien_immobilier {
	
	//ATTRIBUTS
	
	private int id_bien;
	private String nom;
	private boolean en_ligne;
	private Adresse adresse;
	private double surface;
	private double transports;
	private TypeHabitation type_habitation;
	private Environnement environnement;
	
	//CONSTRUCTEUR
	
	public Bien_immobilier(int id_bien, String nom,boolean en_ligne, Adresse adresse, double surface,double transports, TypeHabitation type_habitation) {
		super();
		this.id_bien= id_bien;
		this.nom=nom;
		this.setEn_ligne(en_ligne);
		this.adresse=adresse;
		this.surface=surface;
		this.transports=transports;
		this.type_habitation=type_habitation;
		}
	
	//ACCESSEURS ET MUTATEURS
	
	public int getId_bien() {
		return id_bien;
	}
	public void setid_bien(int id_bien) {
		this.id_bien=id_bien;
	}
	public String getNom() {
		return nom;
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
	public TypeHabitation getType_habitation() {
		return type_habitation;
	}
	public void setType_habitation(TypeHabitation type_habitation) {
		this.type_habitation=type_habitation;
	}
	public Environnement getEnvironnement() {
		return environnement;
	}
	public void setEnvironnement(Environnement environnement) {
		this.environnement=environnement;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	
	/**
	 * Connection à la base de données 
	 */
	
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
	
	
	public boolean isEn_ligne() {
		return en_ligne;
	}
	public void setEn_ligne(boolean en_ligne) {
		this.en_ligne = en_ligne;
	}

		
}
