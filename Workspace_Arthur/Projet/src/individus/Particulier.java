package individus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interactions.BDD;
import interactions.Connexion;
import interactions.Creneau;

public class Particulier extends Individu{
	
	// CONSTRUCTEURS

	public Particulier(int id_individu, String nom, String prenom, String e_mail, int num_tel) {
		super(id_individu, nom, prenom, e_mail, num_tel);
		
	}
	
public static ArrayList<Object> recherche_bien() {
		
		Connection conn = null;
	    try {
			ArrayList<Object> liste_resultat=new ArrayList<>();
			ArrayList<Object> liste_affichage=new ArrayList<>();

	    	// db parameters
	    	String url = "jdbc:sqlite:bdd";
	    	//create a connection to the database
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
			
			// Requête SQL
	    	String type_bien=Particulier.typeBien();
	    	liste_resultat.add(type_bien);
			String query = "SELECT * FROM "+type_bien+" WHERE type_habitation LIKE '"+Particulier.typeHabitation(type_bien)+"' AND environnement LIKE '"+Particulier.typeEnvironnement()+"'";
			System.out.println(query);
			Statement state = Connexion.getinstance().createStatement();
	        String affichage ="";
	        ResultSet result = state.executeQuery(query);
	        while (result.next()) {
	        	String nom = result.getString("nom");
	        	liste_resultat.add(nom);
	        	liste_affichage.add(nom);
	        	Double surface = result.getDouble("surface");
	        	liste_affichage.add(surface);
	        	Double jardin  = result.getDouble("jardin");
	        	liste_affichage.add(jardin);
	        	Integer nbr_pieces =result.getInt("nombre_pieces");
	        	liste_affichage.add(nbr_pieces);
	        	Integer nbr_sallesdeau =result.getInt("nombre_sallesdeau");
	        	liste_affichage.add(nbr_sallesdeau);
	        	Double ecole =result.getDouble("ecole");
	        	liste_affichage.add(ecole);
	        	Double commerce =result.getDouble("commerce");
	        	liste_affichage.add(commerce);
	        	Double transports =result.getDouble("transports");
	        	liste_affichage.add(transports);
	        	Integer date_construction = result.getInt("date_construction");
	        	liste_affichage.add(date_construction);
	        	System.out.println("nom:"+nom+"  date de la construction:"+date_construction+"  surface:"+surface+"  jardin:"+jardin+
	        			"  nombre de pièces:"+nbr_pieces+"  nombre de salles d'eau:"+nbr_sallesdeau+"  distance des transports en commun:"+transports+"  distance d'un commerce:"+commerce+"  distance d'une école:"+ecole+"");
	        	affichage=affichage+nom+"\nSurface : "+surface+"    Surface jardin : "+jardin+"\nNombre de pièces : "
	        			+nbr_pieces+"\nNombre de salles d'eau : "+nbr_sallesdeau+"\nDistance à l'école : "+ecole+" m"
	        			+"\nDistance aux commerces : "+commerce+" m"+"\nDistance aux transports en commun "+transports
	        			+" m"+"\nDate de construction : "+date_construction+"\n\n-----------------\n\n";
	        	}
	        Dialogue.afficher_recherche(affichage);
	        return liste_resultat;
	        }
	        
	         catch (SQLException e1) {
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

	public static ArrayList choisirBien(ArrayList liste_noms_biens) throws ClassNotFoundException, SQLException{
		
		ArrayList<Object> liste_resultat=new ArrayList<>();
		if (liste_noms_biens==null){
			Dialogue.aucun_resultat();
		}
		else{
		
	 
		Statement stmt = null;
	    Connection conn = null;
		Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
	    String type_bien=(String)liste_noms_biens.get(0);
	    liste_noms_biens.remove(0);
   	  	String[] liste_noms_biens_simple = new String[ liste_noms_biens.size() ];
   	  	
   	  	liste_noms_biens.toArray( liste_noms_biens_simple );
   		String choix_bien = Dialogue.choisirBien(liste_noms_biens_simple);
   		liste_resultat.add(choix_bien);
   		ResultSet res = stmt.executeQuery("SELECT * FROM "+type_bien+" WHERE nom LIKE '"+choix_bien+"'");
   		while (res.next()){
   	  				int id_bien = res.getInt("id_bien");
   	  				liste_resultat.add(id_bien);
   	  			}
	  	stmt.close();
	  	res.close();
	  	conn.close();


		}
	    return liste_resultat; 
	 }
	
	public static int choix_rdv_achat(ArrayList liste_resultat) throws ClassNotFoundException, SQLException, IOException{
		String choix = Dialogue.choix_rdv_achat();
		if (choix=="Prendre rdv pour ce bien"){
			int id_particulier=donnerInfo();
			System.out.println("la liste : "+liste_resultat);
			String lecreneau=Creneau.visionner_creneaux_dispos( id_particulier, (int)liste_resultat.get(1));
			return id_particulier;
		}
		else if (choix=="Acheter ce bien"){
			int id_particulier=donnerInfo();
			return id_particulier;
		}
		return 0;
	}
	
	public void acheter_bien() throws ClassNotFoundException, SQLException{
		
	}
	
	
	public static int donnerInfo() throws ClassNotFoundException, SQLException{
		String nom = Dialogue.nom();
		String prenom = Dialogue.prenom();
		if (BDD.est_dans_BDD("particulier", "nom", nom)==true && BDD.est_dans_BDD("particulier", "prenom", prenom)==true){
			recupererId_Particulier( nom,  prenom);

		    }
				 
		else{
		String telephone = Dialogue.telephone();
		String email = Dialogue.email();
		int id_particulier = BDD.trouver_id(nom, prenom);
		BDD.ajouterParticulier(id_particulier, nom, prenom, email, telephone);
		return id_particulier;
		}
		return 0;
	}
	
	public static int recupererId_Particulier(String nom, String prenom) throws ClassNotFoundException, SQLException{
		Statement stmt = null;
	    Connection conn = null;
		Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
	    ResultSet res = stmt.executeQuery("SELECT * FROM particulier WHERE nom LIKE '"+nom+"' AND prenom LIKE '"+prenom+"'");
	    while (res.next()){
	    	String email = res.getString("e_mail");
	           String telephone = res.getString("num_tel");
	           int id_particulier = res.getInt("id_individu");
				int confirmation = Dialogue.confirmation("Êtes-vous bien "+prenom+" "+nom+" ?\n"+email+"\n"+telephone);
			    if (confirmation==1){
			    	stmt.close();
			    	conn.close();
					return id_particulier;
			    }
	    
	    }
		return 0;
	}
	
	public static String typeBien() {
	String[] listeBiens = {"constructible", "non habitable", "habitable"};
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
    String type = (String)jop.showInputDialog(null, 
      "Veuillez entrer le type de bien que vous souhaitez (Constructible, Non habitable ou Habitable).",
      "Type de bien",
      JOptionPane.QUESTION_MESSAGE,
      null,
      listeBiens,
      listeBiens[2]);
    return type;
	}
	
	public static String typeHabitation(String typeBien) {
		if (typeBien.equals("constructible")) {
	    String[] listeHabitation = {"Terrain_vague", "Prairie", "Forêt"};
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String type = (String)jop.showInputDialog(null, 
	      "Veuillez sélectionner le type de bien (Terrain vague, Prairie ou Forêt).",
	      "Type de bien",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      listeHabitation,
	      listeHabitation[2]);
	    return type;
		}
		if (typeBien.equals("non habitable")) {
		String[] listeHabitation = {"Entrepot", "Parking", "Bureaux", "Garage"};
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String type = (String)jop.showInputDialog(null, 
	      "Veuillez sélectionner le type de bien (Entrepot, Parking, Bureaux ou Garage).",
	      "Type de bien",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      listeHabitation,
	      listeHabitation[2]);
	    return type;
		}
		else {
		String[] listeHabitation = {"Maison", "Appartement", "Chateau", "Chambre"};
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String type = (String)jop.showInputDialog(null, 
	      "Veuillez sélectionner le type de bien (Maison, Appartement, Chateau ou Chambre).",
	      "Type de bien",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      listeHabitation,
	      listeHabitation[2]);
	    return type;
		}
		
	   
	}
	
	public static String typeEnvironnement() {
		String[] listeEnvironnements = {"Ville", "Banlieue", "Campagne"};
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String type = (String)jop.showInputDialog(null, 
	      "Veuillez entrer la localisation recherchée (Ville, Banlieue ou Campagne).",
	      "Environnement",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      listeEnvironnements,
	      listeEnvironnements[2]);
	    return type;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {

		ArrayList liste_nom_biens=recherche_bien();
		ArrayList liste_resultat=choisirBien(liste_nom_biens);
		int id_particulier=choix_rdv_achat( liste_resultat);
		//		int id_particulier = donnerInfoRdv() ;
//		Creneau.visionner_creneaux_dispos(id_particulier, 200);
//		Creneau.demandeRdv();
//		Agent_immobilier.rdvAgent(200);
		
		
		
		
	}

}
