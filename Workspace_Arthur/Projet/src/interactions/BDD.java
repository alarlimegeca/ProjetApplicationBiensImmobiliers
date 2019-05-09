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
	
public static void ajouterRDV(String creneau, int id_particulier, int id_agent, int id_bien, int rdv_valide){
		try {
			PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"rendezvous\"(heure,id_particulier,id_agent,id_bien,rdv_valide) VALUES (?,?,?,?,?)");
			preparedState.setString(1,creneau);
			preparedState.setInt(2, id_particulier); 
			preparedState.setInt(3, id_agent); 
			preparedState.setInt(4, id_bien); 
			preparedState.setInt(5, rdv_valide); 





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
	
	public static Individu construire_ind(int id_individu,String statut){
		Connection conn = null;
	    try {
	    	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// RequÃªte SQL
	    	String query = "SELECT * FROM '" +statut+ "' WHERE id_individu = "+id_individu;
	    	
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);
	    	
	    	int id_ind = result.getInt("id_individu");
	    	String nom = result.getString("nom");
	    	String prenom = result.getString("prenom");
	    	String e_mail = result.getString("e_mail");
	    	String num_tel = result.getString("num_tel");
	    	
	    	if (statut.equals("particulier")){
	    		Particulier particulier = new Particulier(id_ind,nom,prenom,e_mail,num_tel);
	    		return particulier;
	    	}
	    	if (statut.equals("client")){
	    		String pseudo = result.getString("pseudo_client");
	    		String mdp = result.getString("mot_de_passe_client");
	    		Client client = new Client(id_ind,nom,prenom,e_mail,num_tel,pseudo,mdp);
	    		return client;
	    	}
	    	if (statut.equals("agent")){
	    		String pseudo = result.getString("pseudo_agent");
	    		String mdp = result.getString("mot_de_passe_agent");
	    		Agent_immobilier agent = new Agent_immobilier(id_ind,nom,prenom,e_mail,num_tel,pseudo,mdp);
	    		return agent;
	    	}
	    	else {
	    		String pseudo = result.getString("pseudo_agent");
	    		String mdp = result.getString("mot_de_passe_agent");
	    		String pseudo_respo = result.getString("pseudo_respo");
	    		String mdp_respo = result.getString("mot_de_passe_respo");
	    		Respo_agence respo = new Respo_agence(id_ind,nom,prenom,e_mail,num_tel,pseudo,mdp,pseudo_respo,mdp_respo);
	    		return respo;
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
		return null;
		
	}
	
	public static Adresse construire_adresse(int id_adresse){
		Connection conn = null;
		int trouve = 0;
	    try {
	    	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// RequÃªte SQL
	    	String query = "SELECT * FROM adresse WHERE id_adresse = "+id_adresse;
	    	
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);
	    	int numero = result.getInt("numero");
	    	String voie = result.getString("voie");
	    	String postal = result.getString("code_postal");
	    	String insee = result.getString("code_insee");
	    	String commune = result.getString("commune");
	    	String pays = result.getString("pays");
	    	String environnement = result.getString("environnement");
	    	Environnement environ = Environnement.parseEnvironnement(environnement);
	    	Adresse adresse = new Adresse(id_adresse,numero,voie,postal,insee,commune,pays,environ);
	    	return adresse;
	    	
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
		return null;
		
	}
	
	public static Bien_immobilier construire_bien(int id_bien,String statut){
		Connection conn = null;
	    try {
	    	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// RequÃªte SQL
	    	String query = "SELECT * FROM '" +statut+ "' WHERE id_bien = "+id_bien;
	    	
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);
	    	
	    	int id_adresse = result.getInt("id_adresse");
			Adresse adresse = construire_adresse(id_adresse);
			
			String precis = result.getString("type_habitation");
			TypeHabitation habitation = TypeHabitation.parseHabitation(precis);
			
			String transac = result.getString("type_transaction");
			TypeTransaction transaction = TypeTransaction.parseTransaction(transac);
			
			String nom = result.getString("nom");
			
			double surface = result.getDouble("surface");
			
			double distancetr = result.getDouble("distance_transports");
			
			double distancecom = result.getDouble("distance_commerces");
			
			double distanceeco = result.getDouble("distance_ecole");
	    	
			
	    	if (statut.equals("constructible")){

	    		int qualite = result.getInt("qualite");
	    		
	    		Constructible constructible = new Constructible(id_bien, nom, adresse, surface,distancetr, habitation, qualite, distanceeco, distancecom);
	    		return constructible;
	    		
	    	}
	    	if (statut.equals("habitable")){
	    		
	    		double surface_batie = result.getDouble("surface_batie");
	    		double surface_jardin = result.getDouble("surface_jardin");
	    		int nbbains = result.getInt("nombre_sallesdeau");
	    		int nbpieces = result.getInt("nombre_pieces");
	    		int annee = result.getInt("date_construction");
	    		
	    		Habitable habitable = new Habitable(id_bien, nom,adresse, surface,distancetr, habitation, surface_batie,annee, nbpieces, nbbains, distancecom, distanceeco, surface_jardin); 
	    		return habitable;
	    		
	    	}
	    	else {
	    		
	    		double surface_batie = result.getDouble("surface_batie");
	    		int annee = result.getInt("date_construction");
	    		
	    		Non_habitable non_habitable = new Non_habitable(0, nom,  adresse, surface,distancetr, habitation, surface_batie,annee); 
	    		return non_habitable;
	    	
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
		return null;
		
	}
	
	public static Annonce construire_annonce(int id_annonce){
		Connection conn = null;
		int trouve = 0;
	    try {
	    	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// RequÃªte SQL
	    	String query = "SELECT * FROM annonce WHERE id_annonce = "+id_annonce;
	    	
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);

	    	String hab = result.getString("habitation");
	    	
	    	String titre = result.getString("titre");
	    	
	    	boolean valide = result.getBoolean("valide");
	    	
	    	int id_bien = result.getInt("id_bien");
	    	Bien_immobilier bien = construire_bien(id_bien, hab);	
	    	
	    	int id_agent = result.getInt("id_agent");
	    	Agent_immobilier agent = (Agent_immobilier) construire_ind(id_agent,"agent");

	    	int id_client = result.getInt("id_client");
	    	Client client = (Client) construire_ind(id_client,"client");
	    	
	    	String trans = result.getString("type_transaction");
	    	TypeTransaction transaction = TypeTransaction.parseTransaction(trans); 
	    	
	    	Annonce annonce = new Annonce(id_annonce,titre,valide,transaction,bien,agent,client);
	    	
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
		return null;
		
	}
	
	public static Transaction construire_transaction(int id_transaction){
		Connection conn = null;
		int trouve = 0;
	    try {
	    	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// RequÃªte SQL
	    	String query = "SELECT * FROM transaction WHERE id_transaction = "+id_transaction;
	    	
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);

	    	
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
		return null;
		
	}
	
	public static void main(String[] args) {
		ajouterNote(001,2.5,"yo");
		ajouterCreneau("30/04/2019 15:20");
		ajouterAgent(201, "Chevalier", "Didier", "didier.chevalier@gmail.fr", "06 52 12 13 57", "DChevalier", "helloworld");
		System.out.println(BDD.est_dans_BDD("client", "pseudo_client", "jdpt99"));
		search("notation");
	}

}
