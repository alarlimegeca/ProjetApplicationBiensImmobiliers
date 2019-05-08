package interactions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import bien_immobilier.Bien_immobilier;
import individus.Agent_immobilier;
import individus.Client;
import individus.Connexion;
import individus.Particulier;

public class Transaction {
 
	private String date_transaction;
	private int id_transaction;
	private Bien_immobilier lebien;
	private Client leclient;
	private Agent_immobilier lagent;
	private Particulier leparticulier;
	private TypeTransaction type_transaction;
	private double prix;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	

	public Transaction(String date_transaction, TypeTransaction type_transaction, int id_transaction, double prix, Client leclient, Particulier leparticulier, Agent_immobilier lagent, Bien_immobilier lebien) {
		super();
		this.type_transaction=type_transaction;
		this.leparticulier=leparticulier;
		this.lagent=lagent;
		this.date_transaction = Date_Ajd();
		this.lebien = lebien;
		this.id_transaction = id_transaction;
		this.leclient = leclient;
		this.date_transaction= date_transaction;
	}
	
	public String Date_Ajd(){
		Date date=new Date();	
		String date_ajd = dateFormat.format(date);
		return date_ajd;

	}
	
	public TypeTransaction getType_Transaction() {
		return this.type_transaction;
	}
	
	public void setType_Transaction(TypeTransaction type_transaction) {
		this.type_transaction=type_transaction;
	}
	
	public String getDate_Transaction() {
		return this.date_transaction;
	}
	
	public void setDate_Transaction(String date_transaction) {
		this.date_transaction=date_transaction;
	}
	
	public void ajouterTransaction() {
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO transaction(date,id_transaction,id_bien,id_agent,id_client,id_particulier,prix,type_transaction) VALUES (?,?,?,?,?,?,?,?)");
			preparedState.setString(1,date_transaction); 
			preparedState.setInt(2, id_transaction); 
			preparedState.setInt(3, lebien.getId_bien()); 
			preparedState.setInt(4,lagent.getId()); 
			preparedState.setInt(5,leclient.getId()); 
			preparedState.setInt(6, leparticulier.getId()); 
			preparedState.setDouble(7,prix); 
			preparedState.setString(8, type_transaction.getContenu3()); 
			
			System.out.println(preparedState.toString());

			preparedState.executeUpdate();

			preparedState.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	
	public static String dateAjd(){
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=new Date();
		String date_ajd=format.format(date);
		return date_ajd;
	}
	
	public String toString() {
		String str = "Récapitulatif transaction : \n";
		str += "Date : "+"\n";
		str += "Type : " + type_transaction.getContenu3() +"\n";
		str += "Montant : ";
		if (type_transaction.getContenu3().equals("Vente")) {
			str += prix+" € \n";
			str += "Ancien propriétaire : "+leclient.getNom() +" "+leclient.getPrenom() +"\n";
			str += "Nouveau propriétaire : "+leparticulier.getNom() +" "+leparticulier.getPrenom()+"\n";
			str += "Agent affecté : " +lagent.getNom()+" "+lagent.getPrenom()+"\n";
			str += "Nom du bien : " +lebien.getNom()+"\n";
		}
		if (type_transaction.getContenu3().equals("Location")) {
			str += prix+" € par mois \n";
			str += "Propriétaire : "+leclient.getNom() +" "+leclient.getPrenom() +"\n";
			str += "Locataire : "+leparticulier.getNom() +" "+leparticulier.getPrenom()+"\n";
			str += "Agent affecté : " +lagent.getNom()+" "+lagent.getPrenom()+"\n";
			str += "Nom du bien : " +lebien.getNom()+"\n";
		}
		else {
			str += prix+" € par mois \n";
			str += "Propriétaire actuel : "+leclient.getNom() +" "+leclient.getPrenom() +"\n";
			str += "Propriétaire à terme : "+leparticulier.getNom() +" "+leparticulier.getPrenom()+"\n";
			str += "Agent affecté : " +lagent.getNom()+" "+lagent.getPrenom()+"\n";
			str += "Nom du bien : " +lebien.getNom()+"\n";
		}
		return str;
		
	}
