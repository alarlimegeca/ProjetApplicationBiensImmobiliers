
package bien_immobilier;

import interactions.Application;
import interactions.TypeTransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import individus.Connexion;

public class Habitable extends Construit {
	
    private int nombre_pieces;
	private int nombre_sallesdeau;
	private double commerce;
	private double ecole;
	private double jardin;
	
	public Habitable (int id_bien, String nom, boolean en_ligne, Adresse adresse, double surface,double transports, TypeHabitation type_habitation, double surface_batie,int date_construction, int nombre_pieces, int nombre_sallesdeau, double commerce, double ecole, double jardin) {
		super( id_bien,  nom, en_ligne, adresse, surface, transports, type_habitation, surface_batie, date_construction);
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
		 {
		try {
			String type_hab= getType_habitation().getContenu2();
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO habitable(id_bien,nom,id_adresse,surface,transports,type_habitation,commerce,ecole,surface_batie,date_construction,nombre_pieces,nombre_sallesdeau,jardin,en_ligne) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedState.setInt(1,getId_bien()); 
			preparedState.setString(2,getNom()); 
			preparedState.setInt(3, getAdresse().getId_adresse()); 
			preparedState.setDouble(5,getTransports()); 
			preparedState.setString(6,type_hab);
			preparedState.setDouble(9,getSurface_batie()); 
			preparedState.setDouble(7,commerce); 
			preparedState.setDouble(8,ecole); 
			preparedState.setDouble(4,getSurface() ); 
			preparedState.setInt(10,getDate_construction()); 
			preparedState.setInt(11,nombre_pieces ); 
			preparedState.setInt(12,nombre_sallesdeau ); 
			preparedState.setDouble(13,jardin ); 
			preparedState.setBoolean(14, isEn_ligne());
			
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
	      str += "Nom : " + this.getNom() +"\n";
	      str += "Type : " + this.getType_habitation().getContenu2()+ "\n";
	      str += "Année de construction : " + this.getDate_construction() +"\n";
	      str += "Distance aux transports en commun : " + this.getTransports() +" km\n";
	      str += "Distance à l'école la plus proche : " + this.ecole  +  " km\n";
	      str += "Distance aux commerces : " + this.commerce + " km\n";
	      str += "Nombre de pièces : " + this.nombre_pieces + "\n";
	      str += "Nombre de salles d'eau : " + this.nombre_sallesdeau + "\n";
	      str += "Surface du bien : " + this.getSurface() + " m² \n";
	      str += "Surface batie : " + this.getSurface_batie() + " m² \n";
	      str += "Surface jardin : " + this.jardin + " m² \n";
	      return str;
	    }	
public double estimation_Hab(TypeTransaction type_transaction) {
		
		String env=getAdresse().getEnvironnement().getContenu1();
		
		double dt=0;
		double dc=0;
		double de=0;
		double dp=0;
		double dsa=0;
		double dj=0;
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
		
		if (nombre_pieces==1) {dp=1;}
		if (nombre_pieces>=2 & nombre_pieces<=4) {dp=1.1;}
		if (nombre_pieces>4){dp=1.2;}
		
		if (nombre_sallesdeau==1){dsa=1;}
		if (nombre_sallesdeau==2){dsa=1.15;}
		if (nombre_sallesdeau>=3){dsa=1.2;}
		
		if (jardin==0) {dj=1;}
		if (jardin!=0 & jardin<getSurface()) {dj=1.1;}
		if (jardin>=getSurface() & jardin<2*getSurface()) {dj=1.2;}
		if (jardin>=2*getSurface()) {dj=1.3;}
		
		if(env=="Ville") {m2=8000;}
		if(env=="Banlieue") {m2=4000;}
		if(env=="Campagne") {m2=1500;}
		
		double dmoy= (dt+dc+de+dp+dsa+dj)/6;
		
		String TypeTrans=type_transaction.getContenu3();
		
		if (TypeTrans=="Vente") {return getSurface()*m2*dmoy;}
		if (TypeTrans=="Location") {return (0.00437826)*getSurface()*m2*dmoy;}
		else {return (0.003393009)*getSurface()*m2*dmoy;}
		
}
	
	public static void main(String[] args){
	  
	  }

	
}