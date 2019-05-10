package individus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bien_immobilier.Adresse;
import bien_immobilier.Bien_immobilier;
import bien_immobilier.Constructible;
import bien_immobilier.Environnement;
import bien_immobilier.Habitable;
import bien_immobilier.Non_habitable;
import bien_immobilier.TypeHabitation;
import interactions.Annonce;
import interactions.Transaction;
import interactions.TypeTransaction;


public class BDD {
	
	/**
	 * permet d'ajouter un particulier à la base de données
	 * @param id_individu
	 * @param nom
	 * @param prenom
	 * @param e_mail
	 * @param num_tel
	 */
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

	/**
	 * permet d'ajouter un client à la base de données
	 * @param categorie
	 * @param id_individu
	 * @param nom
	 * @param prenom
	 * @param e_mail
	 * @param num_tel
	 * @param pseudo_client
	 * @param mot_de_passe_client
	 */
public static void ajouterClient(String categorie,Integer id_individu, String nom, String prenom, String e_mail, String num_tel, String pseudo_client, String mot_de_passe_client) {
	
	try {
		PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO "+categorie+" (id_individu,nom,prenom,e_mail,num_tel,pseudo_client,mot_de_passe_client) VALUES (?,?,?,?,?,?,?)");
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
	
	/**
	 * permet d'ajouter un agent à la base de données
	 * @param id_individu
	 * @param nom
	 * @param prenom
	 * @param e_mail
	 * @param num_tel
	 * @param pseudo_agent
	 * @param mot_de_passe_agent
	 */

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

	/**
	 * permet d'ajouter le responsable à la base de données
	 * @param id_individu
	 * @param nom
	 * @param prenom
	 * @param e_mail
	 * @param num_tel
	 * @param pseudo_agent
	 * @param mot_de_passe_agent
	 * @param pseudo_respo
	 * @param mot_de_passe_respo
	 */

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


/**
 * regarde si un enregistrement se trouve dans une table
 * @param BDD
 * @param colonne
 * @param enregistrement
 * @return booleen qui répend à la question
 */

public static boolean est_dans_BDD(String BDD, String colonne, String enregistrement) {
Connection conn = null;
try {
	String url = "jdbc:sqlite:bdd.db";
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
		
			conn.close();
		
	} catch (SQLException ex) {
		System.out.println(ex.getMessage());
	}
}
return false;

}

/**
 * trouve l'identifiant d'un nouvel individu
 * @param nom
 * @param prenom
 * @return l'individu
 */

public static int trouver_id(String nom, String prenom){
	Connection conn = null;
	int rep = 0;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT id_individu FROM client WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
    	String query2 = "SELECT id_individu FROM particulier WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
    	String query3 = "SELECT id_individu FROM agent_immobilier WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
    	String query4 = "SELECT id_individu FROM respo_agence WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
    	String query9 = "SELECT id_individu FROM reception_candidature_client WHERE nom LIKE '" +nom + "' AND prenom LIKE '" +prenom+"'";
    	
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
    	
    	ResultSet result9 = state.executeQuery(query9);
    	if (result9.next()){
	    	rep = result9.getInt("id_individu");}
    	
    	if (rep == 0) {
    		String query5 = "SELECT MAX(id_individu) FROM respo_agence";
	    	String query6 = "SELECT MAX(id_individu) FROM particulier";
	    	String query7 = "SELECT MAX(id_individu) FROM client";
	    	String query8 = "SELECT MAX(id_individu) FROM agent_immobilier";
	    	String query10 = "SELECT MAX(id_individu) FROM reception_candidature_client";
    
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
    	
    	ResultSet result10 = state.executeQuery(query10);
    	int cand_max = 0;
    	if (result10.next()){
	    	agent_max = result10.getInt("MAX(id_individu)");}
    	
    	rep = Math.max(Math.max(Math.max(part_max,Math.max(cand_max,client_max)),respo_max),agent_max);
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

/**
 * trouve l'identifiant d'une nouvelle adresse
 * @return
 */

public static int trouver_id_adresse() {
Connection conn = null;
int trouve = 0;
try {
	String url = "jdbc:sqlite:bdd.db";
	Class.forName("org.sqlite.JDBC");
	conn = DriverManager.getConnection(url);
	// Requête SQL
	String query = "SELECT MAX(id_adresse) FROM adresse";
	
	Statement state = Connexion.getinstance().createStatement();
	ResultSet result = state.executeQuery(query);
	trouve = result.getInt("MAX(id_adresse)")+1;
	
	
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
return trouve;

}

/**
 * trouve l'identifiant d'une nouvelle transaction
 * @return
 */

public static int trouver_id_transaction() {
	Connection conn = null;
	int trouve = 0;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT MAX(id_transaction) FROM latransaction";
    	
    	Statement state = Connexion.getinstance().createStatement();
    	ResultSet result = state.executeQuery(query);
    	trouve = result.getInt("MAX(id_transaction)")+1;
    	
    	
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
	return trouve;
	
    }

/**
 * trouve l'identifiant d'une nouvelle annonce
 * @return
 */

public static int trouver_id_annonce() {
	Connection conn = null;
	int trouve = 0;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT MAX(id_annonce) FROM annonce";
    	String query2 = "SELECT MAX(id_annonce) FROM reception_annonce";
    	
    	Statement state = Connexion.getinstance().createStatement();
    	ResultSet result = state.executeQuery(query);
    	int ann_max = 0;
    	if (result.next()){
        	ann_max = result.getInt("MAX(id_annonce)");}
        	
    	ResultSet result6 = state.executeQuery(query2);
    	int rec_max = 0;
    	if (result6.next()){
	    	rec_max = result6.getInt("MAX(id_annonce)");}
    	trouve = Math.max(rec_max,ann_max)+1;
    	
    	
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
	return trouve;
	
    }

/**
 * trouve l'identifiant d'un nouveau bien
 * @return identifiant
 */

public static int trouver_id_bien() {
	Connection conn = null;
	int trouve = 0;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT MAX(id_bien) FROM habitable";
    	String query2 = "SELECT MAX(id_bien) FROM non_habitable";
    	String query3 = "SELECT MAX(id_bien) FROM constructible";
    	Statement state = Connexion.getinstance().createStatement();
    	
    	ResultSet result = state.executeQuery(query);
    	int hab_max = 0;
    	if (result.next()){
        	hab_max = result.getInt("MAX(id_bien)");}
        	
    	ResultSet result6 = state.executeQuery(query2);
    	int constr_max = 0;
    	if (result6.next()){
	    	constr_max = result6.getInt("MAX(id_bien)");}
    	
    	ResultSet result7 = state.executeQuery(query3);
    	int nh_max = 0;
    	if (result7.next()){
	    	nh_max = result7.getInt("MAX(id_bien)");}
    	
    	trouve = Math.max(nh_max, Math.max(constr_max, hab_max))+1;
    	
    } catch (SQLException e1) {
    	System.out.println(e1.getMessage());
    	
    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	} finally {
    	try {
    		
    			conn.close();
    		
    	} catch (SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
	}
	return trouve;
	
    }

/**
 * compte le nombre de transaction où l'agent a été impliqué
 * @param agent
 * @return
 */

public static int compte_transactions(Agent_immobilier agent) {
	int compte_transaction = 0;
	Connection conn = null;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT COUNT(*) FROM latransaction WHERE id_agent = '" + agent.getId() +"'";
    	
    	Statement state = Connexion.getinstance().createStatement();
    	ResultSet result = state.executeQuery(query);
    	compte_transaction = result.getInt("COUNT(*)");
   
    	
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
    return compte_transaction;
	}

/**
 * compte le nombre d'annonces actives sur lesquelles se trouve le responsable
 * @param agent
 * @return
 */

public static int compte_annonces(Agent_immobilier agent) {
	int compte_annonce = 0;
	Connection conn = null;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT COUNT(*) FROM annonce WHERE valide = 1 AND id_agent = '" + agent.getId() +"'";
    	
    	Statement state = Connexion.getinstance().createStatement();
    	ResultSet result = state.executeQuery(query);
    	compte_annonce = result.getInt("COUNT(*)");
   
    	
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
    return compte_annonce;
	}
	
	
/**
 * permet de construire un individu sous forme d'objet à partir d'un enregistrement de la base de données   
 * @param id_individu
 * @param statut
 * @return individu
 */

public static Individu construire_ind(int id_individu,String statut){
	Connection conn = null;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT * FROM '" +statut+ "' WHERE id_individu = "+id_individu;
    	
    	java.sql.Statement state = Connexion.getinstance().createStatement();
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
    	if (statut.equals("client") || statut.equals("reception_candidature_client")){
    		String pseudo = result.getString("pseudo_client");
    		String mdp = result.getString("mot_de_passe_client");
    		Client client = new Client(id_ind,nom,prenom,e_mail,num_tel,pseudo,mdp);
    		return client;
    	}
    	if (statut.equals("agent_immobilier")){
    		String pseudo = result.getString("pseudo_agent");
    		String mdp = result.getString("mot_de_passe_agent");
    		Agent_immobilier agent = new Agent_immobilier(id_ind,nom,prenom,e_mail,num_tel,pseudo,mdp);
    		return agent;
    	}
    	if (statut.equals("respo_agence")){
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

/**
 * permet de construire une adresse sous forme d'objet à partir d'un enregistrement de la base de données   
 * @param id_adresse
 * @return adresse construite
 */

public static Adresse construire_adresse(int id_adresse){
	Connection conn = null;
	int trouve = 0;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
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

/**
 * permet de construire un bien sous forme d'objet à partir d'un enregistrement de la base de données   
 * @param id_bien
 * @param statut
 * @return bien construit
 */

public static Bien_immobilier construire_bien(int id_bien,String statut){
	Connection conn = null;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT * FROM '" +statut+ "' WHERE id_bien = "+id_bien;
    	
    	Statement state = Connexion.getinstance().createStatement();
    	ResultSet result = state.executeQuery(query);
    	
    	int id_adresse = result.getInt("id_adresse");
		Adresse adresse = construire_adresse(id_adresse);
		
		String precis = result.getString("type_habitation");
		TypeHabitation habitation = TypeHabitation.parseHabitation(precis);
		
		String nom = result.getString("nom");
		
		double surface = result.getDouble("surface");
		
		double distancetr = result.getDouble("transports");
		
		double distancecom = result.getDouble("commerce");
		
		double distanceeco = result.getDouble("ecole");
		
		boolean en_ligne = result.getBoolean("en_ligne");
    	
		
    	if (statut.equals("constructible")){

    		int qualite = result.getInt("qualite_terrain");
    		
    		Constructible constructible = new Constructible(id_bien, nom, en_ligne, adresse, surface,distancetr, habitation, qualite, distanceeco, distancecom);
    		return constructible;
    		
    	}
    	if (statut.equals("habitable")){
    		
    		double surface_batie = result.getDouble("surface_batie");
    		double surface_jardin = result.getDouble("jardin");
    		int nbbains = result.getInt("nombre_sallesdeau");
    		int nbpieces = result.getInt("nombre_pieces");
    		int annee = result.getInt("date_construction");
    		
    		Habitable habitable = new Habitable(id_bien, nom,en_ligne, adresse, surface,distancetr, habitation, surface_batie,annee, nbpieces, nbbains, distancecom, distanceeco, surface_jardin); 
    		return habitable;
    		
    	}
    	else {
    		
    		double surface_batie = result.getDouble("surface_batie");
    		int annee = result.getInt("date_construction");
    		
    		Non_habitable non_habitable = new Non_habitable(id_bien, nom,  en_ligne, adresse, surface,distancetr, habitation, surface_batie,annee,distancecom,distanceeco); 
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

/**
 * permet de construire une annonce sous forme d'objet à partir d'un enregistrement de la base de données   
 */

public static Annonce construire_annonce(int id_annonce, String type_bien){
	Connection conn = null;
	int trouve = 0;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
    	String query = "SELECT * FROM "+type_bien+" WHERE id_annonce = "+id_annonce;
    	
    	Statement state = Connexion.getinstance().createStatement();
    	ResultSet result = state.executeQuery(query);

    	String titre = result.getString("titre");
    	
    	System.out.println(titre);
    	
    	String typeBien = result.getString("habitation");
    	
    	boolean valide = result.getBoolean("valide");
    	
    	int id_bien = result.getInt("id_bien");
    	Bien_immobilier bien = construire_bien(id_bien, typeBien);	
    	
    	int id_agent = result.getInt("id_agent");
    	Agent_immobilier agent = (Agent_immobilier) construire_ind(id_agent,"agent_immobilier");

    	int id_client = result.getInt("id_client");
    	Client client = (Client) construire_ind(id_client,"client");
    	
    	double prix = result.getDouble("prix");
    	
    	String trans = result.getString("type_transaction");
    	TypeTransaction transaction = TypeTransaction.parseTransaction(trans);
    	 

    
    	
    	Annonce annonce = new Annonce(id_annonce,titre,valide,transaction,prix,bien,agent,client,typeBien);
    	
    	return annonce;
    	
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

/**
 * permet de construire une transaction sous forme d'objet à partir d'un enregistrement de la base de données   
 * @param id_transaction
 * @return transaction construite
 */

public static Transaction construire_transaction(int id_transaction){
	Connection conn = null;
	int trouve = 0;
    try {
    	String url = "jdbc:sqlite:bdd.db";
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);
    	// Requête SQL
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

/**
 * permet d'ajouter un rendez-vous dans la base de données
 * @param creneau
 * @param id_particulier
 * @param id_agent
 * @param id_bien
 * @param rdv_valide
 */

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

public static void ajouterReceptionClientParticulier(Integer id_client, int id_particulier, int id_bien, int id_annonce){
	try {
	PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO \"reception_client_particulier"
			+ "\"(id_annonce,id_client,id_particulier,id_bien) VALUES (?,?,?,?)");
	preparedState.setInt(2,id_client);
	preparedState.setInt(3, id_particulier); 
	preparedState.setInt(4, id_bien); 
	preparedState.setInt(1, id_annonce);

	System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

	preparedState.executeUpdate(); // execution de la requete preparee

	preparedState.close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

/**
 * ajoute un enregistrement dans la table "reception_respo_transaction"
 * @param id_annonce
 * @param id_client
 * @param id_particulier
 */

public static void ajouterReceptionRespoTrans(int id_annonce,int id_client, int id_particulier){
	try {
	PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO reception_respo_transaction(id_client,id_particulier,id_annonce) VALUES (?,?,?)");
	preparedState.setInt(2,id_client);
	preparedState.setInt(3, id_particulier); 
	preparedState.setInt(1, id_annonce);

	System.out.println(preparedState.toString()); // on affiche la requete prete a etre executee

	preparedState.executeUpdate(); // execution de la requete preparee

	preparedState.close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

/**
 * permet de modifier le statut du bien et de l'annonce
 * @param annonce
 */

public static void suppr_annonce(Annonce annonce) {
	Connection conn = null;
	    try {
	    	String url = "jdbc:sqlite:bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// Requête SQL
	    	String query = "UPDATE "+annonce.getHabitation()+ " SET en_ligne = 1 WHERE id_bien = " +annonce.getLebien().getId_bien();
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);

	    	String query2 = "UPDATE annonce SET valide = 0 WHERE id_annonce = "+annonce.getId_annonce() ;
	    
	    	state.executeQuery(query2);
		
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
	    try {
	    	String url = "jdbc:sqlite:bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);

	    	String query2 = "UPDATE annonce SET valide = 0 WHERE id_annonce = "+annonce.getId_annonce() ;
	    	Statement state = Connexion.getinstance().createStatement();
	    	state.executeQuery(query2);
		
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
		}}


}
