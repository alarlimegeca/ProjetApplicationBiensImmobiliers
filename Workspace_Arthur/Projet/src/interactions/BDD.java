package interactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD {

	public static void ajouterNote(Integer id_individu, double note, String date) {
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
	
	public static void ajouterCreneau(String creneau, boolean occupe) {
		String dispo="";
		if (occupe=true){
			dispo="Indisponible";
		}
		else {
			dispo="Libre";
		}
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"rendezvous\"(creneau,disponibilite) VALUES (?,?)");
			preparedState.setString(1,creneau);
			preparedState.setString(2, dispo); 


			System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

			preparedState.executeUpdate(); // execution de la requete preparee

			preparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int search(String table){
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:/home/formation/Bureau/bdd/bddprojet";
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

	public static void main(String[] args) {
		ajouterNote(001,2.5,"15/10/2018");
		for(int i=0;i<5;i++) {
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		ajouterCreneau("18/02/2019 15h-16h",true);
		}
		search("rendezvous");
	}

}
