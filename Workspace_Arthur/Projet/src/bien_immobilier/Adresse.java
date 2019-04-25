package bien_immobilier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Adresse {
	public int id_adresse;
	public int numero;
	public String voie;
	public String code_postal;
	public String code_INSEE;
	public String commune;
	public String pays;
	public Environnement environnement;
	
	public Adresse(int id_adresse, int numero, String voie, String code_postal, String code_INSEE, String commune, String pays,Environnement environnement) {
		super();
		this.id_adresse=id_adresse;
		this.numero=numero;
		this.voie=voie;
		this.code_postal=code_postal;
		this.code_INSEE=code_INSEE;
		this.commune=commune;
		this.pays=pays;
		this.environnement=environnement;
	}
	public int getId_adresse() {
		return id_adresse;
	}
	public void setId_adresse(int id_adresse) {
		this.id_adresse=id_adresse;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero=numero;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie=voie;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal=code_postal;
	}
	public String code_INSEE() {
		return code_INSEE;
	}
	public void setCode_INSEE(String code_INSEE) {
		this.code_INSEE=code_INSEE;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune=commune;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays=pays;
	}
	public Environnement getEnvironnement() {
		return environnement;
	}
	public void setEnvironnement(Environnement environnement) {
		this.environnement=environnement;
	}
	public void ajouterAdresse(int id_adresse) {
		try {
			String env=environnement.getContenu1();
			PreparedStatement preparedState = Connexion.getInstance().prepareStatement("INSERT INTO biens_immobiliers(id_adresse,pays,numero,voie,code_postal,code_insee,commune,env) VALUES (?,?,?,?,?,?,?)");
			preparedState.setInt(1, id_adresse); 
			preparedState.setString(2, pays); 
			preparedState.setDouble(3, numero); 
			preparedState.setString(4,voie); 
			preparedState.setString(5, code_postal); 
			preparedState.setString(6, code_INSEE); 
			preparedState.setString(7,commune); 
			preparedState.setString(8,env);  
			
			System.out.println(preparedState.toString());

			preparedState.executeUpdate();

			preparedState.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
		 public String toString(){
		    String str;
		    if(this.id_adresse == 0){
		      str = "Affichage adresse \n";
		      str += "id_adresse : à déterminer \n";
		      str += this.pays +"\n";
		      str += this.numero + " " + this.voie +  "\n";
		      str += this.code_postal + " " + this.commune + "\n";
		      str += "Code INSEE : " + this.code_INSEE + "\n";
		      str += "Environnement : " + this.environnement.getContenu1() + "\n";
		    }
		    else{
		    	str = "Affichage adresse \n";
			      str += "id_adresse : " + this.id_adresse + "\n";
			      str += this.pays+ "\n";
			      str += this.numero + " " + this.voie +  "\n";
			      str += this.code_postal + " " + this.commune + "\n";
			      str += "Code INSEE : " + this.code_INSEE + "\n";
			      str += "Environnement : " + this.environnement.getContenu1() + "\n";
		    }
		    return str;
		  }
}
