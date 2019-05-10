package interactions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import bien_immobilier.Adresse;
import bien_immobilier.Bien_immobilier;
import bien_immobilier.TypeHabitation;
import individus.Agent_immobilier;
import individus.BDD;
import individus.Client;
import individus.Connexion;
import individus.Respo_agence;

public class Annonce {
	
	// ATTRIBUTS
	
	private int id_annonce;
	private String titre;
	private boolean valide;
	private TypeTransaction transaction;
	private Bien_immobilier lebien;
	private Agent_immobilier agent;
	private Client client;
	private double prix;
	private String habitation;
	
	
	// CONSTRUCTEUR
	
	public Annonce(int id_annonce,String titre, boolean valide, TypeTransaction transaction, double prix, Bien_immobilier lebien,Agent_immobilier agent, Client client, String habitation) {
		super();
		this.id_annonce = id_annonce;
		this.titre = titre;
		this.setValide(valide);
		this.setTransaction(transaction);
		this.setLebien(lebien);
		this.agent = agent;
		this.setClient(client);
		this.setPrix(prix);
		this.habitation = habitation;
	}
	
	// ACCESSEURS ET MUTATEURS

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public boolean getValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}
	
	public Agent_immobilier getAgent() {
		return agent;
	}
	

	 public int getId_annonce() {
			return id_annonce;
		}

	public void setId_annonce(int id_annonce) {
		this.id_annonce = id_annonce;
	}

	public Bien_immobilier getLebien() {
		return lebien;
	}

	public void setLebien(Bien_immobilier lebien) {
		this.lebien = lebien;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public TypeTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(TypeTransaction transaction) {
		this.transaction = transaction;
	}
	
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}



	public String getHabitation() {
		return habitation;
	}



	public void setHabitation(String habitation) {
		this.habitation = habitation;
	}

	// AUTRES METHODES
	
	/**
	 * ajoute l'annonce dans la base de données
	 * @param categorie
	 */
	
	public void ajouterAnnonce(String categorie) {
		try {
			String statement = "INSERT INTO "+categorie+"(id_annonce,titre,valide,type_transaction,prix,id_bien,id_agent,id_client,habitation) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement(statement);
			preparedState.setInt(1,id_annonce);
			preparedState.setString(2, titre); 
			preparedState.setBoolean(3, valide); 
			preparedState.setString(4, transaction.getContenu3());
			preparedState.setDouble(5,prix);
			preparedState.setInt(6, lebien.getId_bien());
			preparedState.setInt(7, agent.getId());
			preparedState.setInt(8, client.getId());
			preparedState.setString(9, habitation);
			
			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * permet d'afficher proprement l'annonce
	 * @return plusieurs châine de caractères qui décrivent l'adresse, le bien, le prix, le client et l'agent
	 */
	
	
	public ArrayList<String> affichage() {
		String str1 = this.getLebien().getAdresse().toString();
		String str2 = this.getLebien().toString();
		String str3 = "Client : \n" ; 
		str3 += "Nom : " + this.client.getNom() + "\n";
		str3 += "Prénom : " + this.client.getPrenom()+"\n";
		str3 += "Agent immobilier : \n" ; 
		str3 += "Nom : " + this.agent.getNom() + "\n";
		str3 += "Prénom : " + this.agent.getPrenom()+"\n";
		str3 += "Valeur : \n";
		str3 += this.transaction.getContenu3();
		if (this.transaction.getContenu3().equals("Vente")){
			str3 += " " +this.getPrix() + " €";
		}
		else {
			str3 += " " + this.getPrix() + " € par mois";
		}
		ArrayList<String> affichage = new ArrayList();
		affichage.add(str1);
		affichage.add(str2);
		affichage.add(str3);
		return affichage;
	}



	
	

	
}