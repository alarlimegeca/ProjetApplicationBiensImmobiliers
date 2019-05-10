package bien_immobilier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import individus.Connexion;
import interactions.Transaction;
import interactions.TypeTransaction;

public class Constructible extends Bien_immobilier {
	
	//ATTRIBUTS
	
	private int qualite_terrain;
	private double commerce;
	private double ecole;

	//CONSTRUCTEUR
	
	public Constructible (int id_bien, String nom, boolean en_ligne, Adresse adresse,double surface, double transports, TypeHabitation type_habitation, int qualite_terrain,double commerce, double ecole) {
		super(id_bien, nom, en_ligne,adresse,surface,transports, type_habitation);
		this.qualite_terrain=qualite_terrain;
		this.commerce=commerce;
		this.ecole=ecole;
		
	}
	
	//ACCESSEURS ET MUTATEURS
	
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

	//AUTRES METHODES
	
	/**
	 * Méthode d'ajout d'un bien immobilier de type Constructible avec ses différents attributs à la base de données
	 */
		
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
	
	
	
	/**
	 * Méthode d'estimation d'un bien Constructible
	 * L'estimation est calculée par rapport au nombre de mètres carrés ainsi qu'à la localisation du bien.
	 * La valeur obtenue est alors ajustée selon les avantages et inconvénients autour du bien.
	 * @return la valeur de l'estimation qu'il s'agisse du prix de vente (vente), du loyer (location) 
	 * ou de la rente (vente en viager).
	 */
	
	public double estimation_Constr(TypeTransaction type_transaction) {
			String env=getAdresse().getEnvironnement().getContenu1();
	
			double dt=0;
			double dc=0;
			double de=0;
			double dq=0;
			double m2=0;
			
			if (getTransports()<0.5) {dt=1.2;}
			if (getTransports()>=0.5 & getTransports()<1 ){dt=1.1;}
			if (getTransports()>=1 & getTransports()<2 ){dt=1;}
			if (getTransports()>=2 ){dt=0.9;}
			
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
			
			if(env=="Ville") {m2=10000;}
			if(env=="Banlieue") {m2=1200;}
			if(env=="Campagne") {m2=200;}
			
			double dmoy= (dt+ dc + dq + de)/4;
			
			String TypeTrans= type_transaction.getContenu3();
			
			if (TypeTrans=="Vente") {return getSurface()*m2*dmoy;}
			if (TypeTrans=="Location") {return (0.00437826)*getSurface()*m2*dmoy;}
			else {return (0.003393009)*getSurface()*m2*dmoy;}
	}
	
	/**
	 * Donne une description des objets de la classe Constructible
	 */
	
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
		
}
