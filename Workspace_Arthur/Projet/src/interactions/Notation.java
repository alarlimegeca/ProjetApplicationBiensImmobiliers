package interactions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import individus.Agent_immobilier;
import individus.Respo_agence;

public class Notation {

	public double note;
	public String date;
	public static Respo_agence leresponsable;
	public static Agent_immobilier lagent;
	
	
	public Notation(int note, String date, Respo_agence leresponsable, Agent_immobilier lagent) {
		super();
		this.note=note;
		this.date=date;
		Notation.leresponsable=leresponsable;
		Notation.lagent=lagent;
	}
	
	public double getNote(){
		return this.note;
	}
	
	public void setNote(int note) {
		this.note=note;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String date) {
		this.date=date;
	}
	
	static Scanner scan = new Scanner(System.in);
	
	public static void ajout_note() throws SQLException {
		String query = "SELECT nom,prenom FROM agent_immobilier";     

    	Statement state = Connexion.getinstance().createStatement();
    	ResultSet result = state.executeQuery(query);
    	System.out.println("Liste des agents\n");
    	while (result.next()) {
        	String nom = result.getString("nom");
        	String prenom = result.getString("prenom");
        	System.out.println(nom+" "+prenom);
        }
    	
		System.out.println("\nNom de l'agent : ");
		String nom = scan.nextLine();
		while ((BDD.est_dans_BDD("agent_immobilier", "nom", nom)==false)){
			System.out.println("Le nom de cet agent n'existe pas dans la base de données ");
			System.out.println("\nNom de l'agent : ");
			nom = scan.nextLine();
		}
		System.out.println("\nPrénom de l'agent : ");
		String prenom = scan.nextLine();
		while ((BDD.est_dans_BDD("agent_immobilier", "nom", prenom)==false)){
			System.out.println("Le prénom de cet agent n'existe pas dans la base de données ");
			System.out.println("\nPrénom de l'agent : ");
			prenom = scan.nextLine();
		}
		System.out.println("\nNote : ");
		double note = scan.nextDouble();
		System.out.println("\nDate : ");
		String date = scan.next();
		
		String query2 = "SELECT * FROM agent_immobilier WHERE nom LIKE '"+nom+"' AND prenom LIKE '"+prenom+"'";     

    	Statement state2 = Connexion.getinstance().createStatement();
    	ResultSet result2 = state.executeQuery(query2);
        int id = result2.getInt("id_individu");
        
    	BDD.ajouterNote(id, note, date);
	}

	public static void main(String[] args) throws SQLException {
		ajout_note();
	}

		
}

