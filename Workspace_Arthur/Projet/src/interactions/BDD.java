package interactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class BDD {

	public static void ajouterAnnonce(Integer id_annonce, String titre, boolean valide, Integer id_bien, Integer id_agent, Integer id_client) {
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"annonce\"(id_annonce,titre,valide,id_bien,id_agent,id_client) VALUES (?,?,?,?,?,?)");
			preparedState.setInt(1,id_annonce);
			preparedState.setString(2, titre); 
			preparedState.setBoolean(3, valide); 
			preparedState.setInt(4, id_bien);
			preparedState.setInt(5, id_agent);
			preparedState.setInt(6, id_client);
			
			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	
	public void ajouterBien_immo_Hab() {
		try {
			String type_hab= type_habitation.getContenu2();
			String env=environnement.getContenu1();
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO bien_immobilier(id_bien,nom,id_adresse,surface,transports,type_hab,commerce,ecole,surface_batie,date_construction,nombre_pieces,nombre_sallesdeau,jardin,env) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedState.setInt(1,id_bien); 
			preparedState.setString(2,nom); 
			preparedState.setDouble(3,id_adresse); 
			preparedState.setDouble(5,transports); 
			preparedState.setString(6,type_hab);
			preparedState.setDouble(10,surface_batie); 
			preparedState.setDouble(8,commerce); 
			preparedState.setDouble(9,ecole); 
			preparedState.setDouble(4,surface ); 
			preparedState.setInt(11,date_construction); 
			preparedState.setInt(12,nombre_pieces ); 
			preparedState.setInt(13,nombre_sallesdeau ); 
			preparedState.setDouble(14,jardin ); 
			preparedState.setString(15, env);
			
			System.out.println(preparedState.toString());

			preparedState.executeUpdate();

			preparedState.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	public static void ajouterNote(int id_individu, double note, String date) {
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"notation\"(id_agent,note,date) VALUES (?,?,?)");
			preparedState.setInt(1,id_individu);
			preparedState.setDouble(2, note); 
			preparedState.setString(3, date); 


			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ajouterCreneau(String creneau) {

		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"creneau\"(heure) VALUES (?)");
			preparedState.setString(1,creneau);


			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ajouterRDV(String creneau, int id_particulier, int id_agent, int id_bien){
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"rendezvous\"(heure,id_particulier,id_agent) VALUES (?,?,?,?)");
			preparedState.setString(1,creneau);
			preparedState.setInt(2, id_particulier); 
			preparedState.setInt(3, id_agent); 
			preparedState.setInt(4, id_bien); 




			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void ajouterTransaction(Integer id_particulier, Integer id_agent, Type_Transaction type_transaction,String date) {
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"notation\"(id_agent,note,date) VALUES (?,?,?)");
			preparedState.setInt(1,id_particulier);
			preparedState.setDouble(2, id_agent); 
			preparedState.setString(3, type_transaction); 


			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
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

	} catch (SQLException e1) {
	    System.out.println(e1.getMessage());	 
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

public static boolean est_dans_BDD(String BDD, String colonne, String enregistrement) {
	Connection conn = null;
    try {
    	String url = "jdbc:sqlite:bdd";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT COUNT(*) FROM " +BDD+ " WHERE " + colonne + " LIKE '" + enregistrement +"'";
    	
    	Statement state = Connexion.getinstance().createStatement();
    	ResultSet result = state.executeQuery(query);
    	int presence = result.getInt("COUNT(*)");
    	if (presence == 1) {return true;}
    	else {return false;}
    	
    } catch (SQLException e1) {
    	System.out.println(e1.getMessage());
    	
    } catch (ClassNotFoundException e) {
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
	return false;	
}

public static int search(String table){
	Connection conn = null;
    try {
    	// db parameters
    	String url = "jdbc:sqlite:bdd";
    	//create a connection to the database
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);

    	
    	// Requête SQL
    	String query = "SELECT * FROM '"+table+"'";
   

    	Statement state = Connexion.getinstance().createStatement();

        ResultSet result = state.executeQuery(query);
        while (result.next()) {
        	int id = result.getInt("id_agent");
        	int note = result.getInt("note");
        	String date = result.getString("date");
        	System.out.println("L'agent "+id+" a reçu la note : "+note +" le "+date);
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
	
public static int trouver_id(String nom, String prenom){
		Connection conn = null;
		int rep = 0;
	    try {
	    	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// RequÃªte SQL
	    	String query = "SELECT id_individu FROM client WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
	    	String query2 = "SELECT id_individu FROM particulier WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
	    	String query3 = "SELECT id_individu FROM agent_immobilier WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
	    	String query4 = "SELECT id_individu FROM respo_agence WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
	    	
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);
	    	if (result.next()){
	    	rep = result.getInt("id_individu");}
	    	
	    	ResultSet result2 = state.executeQuery(query2);
	    	if (result2.next()){
		    	rep = result2.getInt("id_individu");}
	    	
	    	ResultSet result3 = state.executeQuery(query3);
	    	if (result3.next()){
		    	rep = result3.getInt("id_individu");}
	    	
	    	ResultSet result4 = state.executeQuery(query4);
	    	if (result4.next()){
		    	rep = result4.getInt("id_individu");}
	    	
	    	if (rep == 0) {
	    		String query5 = "SELECT MAX(id_individu) FROM respo_agence";
		    	String query6 = "SELECT MAX(id_individu) FROM particulier";
		    	String query7 = "SELECT MAX(id_individu) FROM client";
		    	String query8 = "SELECT MAX(id_individu) FROM agent_immobilier";
	    
	    	ResultSet result5 = state.executeQuery(query5);
	    	int respo_max = 0;
	    	if (result5.next()){
	    	respo_max = result5.getInt("MAX(id_individu)");}
	    	
	    	ResultSet result6 = state.executeQuery(query6);
	    	int part_max = 0;
	    	if (result6.next()){
		    	part_max = result6.getInt("MAX(id_individu)");}
	    	
	    	ResultSet result7 = state.executeQuery(query7);
	    	int client_max = 0;
	    	if (result7.next()){
		    	client_max = result7.getInt("MAX(id_individu)");}
	    	
	    	ResultSet result8 = state.executeQuery(query8);
	    	int agent_max = 0;
	    	if (result8.next()){
		    	agent_max = result8.getInt("MAX(id_individu)");}
	    	
	    	rep = Math.max(Math.max(Math.max(part_max,client_max),respo_max),agent_max);
	    	rep++;
	    	
	    	}
	    } catch (SQLException e1) {
	    	System.out.println(e1.getMessage());
	    	
	    } catch (ClassNotFoundException e) {
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
		return rep;
	}
	
	public static void main(String[] args) {
		ajouterNote(001,2.5,"yo");
		ajouterCreneau("30/04/2019 15:20");
		ajouterAgent(201, "Chevalier", "Didier", "didier.chevalier@gmail.fr", "06 52 12 13 57", "DChevalier", "helloworld");
		System.out.println(BDD.est_dans_BDD("client", "pseudo_client", "jdpt99"));
		search("notation");
	}

}
