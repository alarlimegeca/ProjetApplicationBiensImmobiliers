package individus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BDDInd {

	public static void ajouterParticulier(Integer id_individu, String nom, String prenom, String e_mail, String num_tel) {
			try {
				PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"particulier\"(id_individu,nom,prenom,e_mail,num_tel) VALUES (?,?,?,?,?)");
				preparedState.setInt(1,id_individu);
				preparedState.setString(2, nom); 
				preparedState.setString(3, prenom); 
				preparedState.setString(4, e_mail);
				preparedState.setString(5, num_tel);

				System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

				preparedState.executeUpdate(); // execution de la requete preparee

				preparedState.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public static void ajouterClient(Integer id_individu, String nom, String prenom, String e_mail, String num_tel, String pseudo_client, String mot_de_passe_client) {
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"client\"(id_individu,nom,prenom,e_mail,num_tel,pseudo_client,mot_de_passe_client) VALUES (?,?,?,?,?,?,?)");
			preparedState.setInt(1,id_individu);
			preparedState.setString(2, nom); 
			preparedState.setString(3, prenom); 
			preparedState.setString(4, e_mail);
			preparedState.setString(5, num_tel);
			preparedState.setString(6, pseudo_client);
			preparedState.setString(7, mot_de_passe_client);

			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void ajouterAgent(Integer id_individu, String nom, String prenom, String e_mail, String num_tel, String pseudo_agent, String mot_de_passe_agent) {
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"agent_immobilier\"(id_individu,nom,prenom,e_mail,num_tel,pseudo_agent,mot_de_passe_agent) VALUES (?,?,?,?,?,?,?)");
			preparedState.setInt(1,id_individu);
			preparedState.setString(2, nom); 
			preparedState.setString(3, prenom); 
			preparedState.setString(4, e_mail);
			preparedState.setString(5, num_tel);
			preparedState.setString(6, pseudo_agent);
			preparedState.setString(7, mot_de_passe_agent);

			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ajouterRespo(Integer id_individu, String nom, String prenom, String e_mail, String num_tel, String pseudo_agent, String mot_de_passe_agent, String pseudo_respo, String mot_de_passe_respo) {
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"respo_agence\"(id_individu,nom,prenom,e_mail,num_tel,pseudo_agent,mot_de_passe_agent,pseudo_respo,mot_de_passe_respo) VALUES (?,?,?,?,?,?,?,?,?)");
			preparedState.setInt(1,id_individu);
			preparedState.setString(2, nom); 
			preparedState.setString(3, prenom); 
			preparedState.setString(4, e_mail);
			preparedState.setString(5, num_tel);
			preparedState.setString(6, pseudo_agent);
			preparedState.setString(7, mot_de_passe_agent);
			preparedState.setString(8, pseudo_respo);
			preparedState.setString(9, mot_de_passe_respo);

			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int search(){
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:/media/formation/CLEF MENGIN/Projet info/BDD/Individus.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);

        	
        	// Requête SQL
        	String query = "SELECT * FROM client";
       

        	Statement state = Connexion.getinstance().createStatement();

            ResultSet result = state.executeQuery(query);
            while (result.next()) {
            	String p = result.getString("prenom");
            	String n = result.getString("nom");
            	System.out.println("Le client est "+p+" "+n);
            }
          
           


        } catch (SQLException e1) {
        	System.out.println(e1.getMessage());
        	return (0);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	try {
        		if (conn != null) {
        			conn.close();
        		}
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
        	}
        }
		return 0;
	}

	
		
		public static void main(String[] args) {
			// BDDInd.ajouterParticulier(100,"Dupont", "Jean","jean.dupont@gmail.com","06 23 14 56 87");
			// BDDInd.ajouterClient(102,"Dumas", "Georges","georges.dumas@gmail.com","06 56 78 56 87","geodu00","azertyuiop");
			//  System.out.println(BDDInd.search());
		}
	
	}



