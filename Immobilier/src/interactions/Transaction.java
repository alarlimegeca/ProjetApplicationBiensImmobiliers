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
 
	//ATTRIBUTS
	
	private String date_transaction;
	private int id_transaction;
	private Bien_immobilier lebien;
	private Client leclient;
	private Agent_immobilier lagent;
	private Particulier leparticulier;
	private TypeTransaction type_transaction;
	private double prix;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	//CONSTRUCTEUR

	public Transaction(String date_transaction, TypeTransaction type_transaction, int id_transaction, double prix, Client leclient, Particulier leparticulier, Agent_immobilier lagent, Bien_immobilier lebien) {
		super();
		this.setType_transaction(type_transaction);
		this.setLeparticulier(leparticulier);
		this.setLagent(lagent);
		this.setLebien(lebien);
		this.setId_transaction(id_transaction);
		this.setLeclient(leclient);
		this.date_transaction= date_transaction;
	}
	
	//ACCESSEURS ET MUTATEURS

	public TypeTransaction getType_transaction() {
		return type_transaction;
	}

	public void setType_transaction(TypeTransaction type_transaction) {
		this.type_transaction = type_transaction;
	}

	public int getId_transaction() {
		return id_transaction;
	}

	public void setId_transaction(int id_transaction) {
		this.id_transaction = id_transaction;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Client getLeclient() {
		return leclient;
	}

	public void setLeclient(Client leclient) {
		this.leclient = leclient;
	}

	public Particulier getLeparticulier() {
		return leparticulier;
	}

	public void setLeparticulier(Particulier leparticulier) {
		this.leparticulier = leparticulier;
	}

	public Bien_immobilier getLebien() {
		return lebien;
	}

	public void setLebien(Bien_immobilier lebien) {
		this.lebien = lebien;
	}

	public Agent_immobilier getLagent() {
		return lagent;
	}

	public void setLagent(Agent_immobilier lagent) {
		this.lagent = lagent;
	}
	
	
	public TypeTransaction getType_Transaction() {
		return this.getType_transaction();
	}
	
	public void setType_Transaction(TypeTransaction type_transaction) {
		this.setType_transaction(type_transaction);
	}
	
	public String getDate_Transaction() {
		return this.date_transaction;
	}
	
	public void setDate_Transaction(String date_transaction) {
		this.date_transaction=date_transaction;
	}
	
	/**
	 * permet d'ajouter la transaction à la BDD
	 */
	
	public void ajouterTransaction() {
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO latransaction(date_transaction,id_transaction,id_bien,id_agent,id_client,id_particulier,prix,typ_transaction) VALUES (?,?,?,?,?,?,?,?)");
			preparedState.setString(1,date_transaction); 
			preparedState.setInt(2, getId_transaction()); 
			preparedState.setInt(3, getLebien().getId_bien()); 
			preparedState.setInt(4,getLagent().getId()); 
			preparedState.setInt(5,getLeclient().getId()); 
			preparedState.setInt(6, getLeparticulier().getId()); 
			preparedState.setDouble(7,getPrix()); 
			preparedState.setString(8, getType_transaction().getContenu3()); 
			
			System.out.println(preparedState.toString());

			preparedState.executeUpdate();

			preparedState.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	
	/**
	 * permet d'afficher proprement la transaction
	 */
	
	public String toString() {
		String str = "Récapitulatif transaction : \n";
		str += "Date : "+"\n";
		str += "Type : " + getType_transaction().getContenu3() +"\n";
		str += "Montant : ";
		if (getType_transaction().getContenu3().equals("Vente")) {
			str += getPrix()+" € \n";
			str += "Ancien propriétaire : "+getLeclient().getNom() +" "+getLeclient().getPrenom() +"\n";
			str += "Nouveau propriétaire : "+getLeparticulier().getNom() +" "+getLeparticulier().getPrenom()+"\n";
			str += "Agent affecté : " +getLagent().getNom()+" "+getLagent().getPrenom()+"\n";
			str += "Nom du bien : " +getLebien().getNom()+"\n";
		}
		if (getType_transaction().getContenu3().equals("Location")) {
			str += getPrix()+" € par mois \n";
			str += "Propriétaire : "+getLeclient().getNom() +" "+getLeclient().getPrenom() +"\n";
			str += "Locataire : "+getLeparticulier().getNom() +" "+getLeparticulier().getPrenom()+"\n";
			str += "Agent affecté : " +getLagent().getNom()+" "+getLagent().getPrenom()+"\n";
			str += "Nom du bien : " +getLebien().getNom()+"\n";
		}
		else {
			str += getPrix()+" € par mois \n";
			str += "Propriétaire actuel : "+getLeclient().getNom() +" "+getLeclient().getPrenom() +"\n";
			str += "Propriétaire à terme : "+getLeparticulier().getNom() +" "+getLeparticulier().getPrenom()+"\n";
			str += "Agent affecté : " +getLagent().getNom()+" "+getLagent().getPrenom()+"\n";
			str += "Nom du bien : " +getLebien().getNom()+"\n";
		}
		return str;
		
	}
	
	/**
	 * renvoie la date courante sous forme de chaîne de caractère
	 * @return date courante
	 */
	
	public static String dateAjd(){
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=new Date();
		String date_ajd=format.format(date);
		return date_ajd;
	}
}