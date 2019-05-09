package individus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
			Statement state2 = Connexion.getinstance().createStatement();
			Statement state3 = Connexion.getinstance().createStatement();
	        String affichage ="";
	        ResultSet result = state.executeQuery(query);
	        while (result.next()) {
	        	System.out.println("1");
	        	int id_bien = result.getInt("id_bien");
	        	String nom = result.getString("nom");
	        	liste_resultat.add(nom);
	        	Double surface = result.getDouble("surface");
	        	Double jardin  = result.getDouble("jardin");
	        	Integer nbr_pieces =result.getInt("nombre_pieces");
	        	Integer nbr_sallesdeau =result.getInt("nombre_sallesdeau");
	        	Double ecole =result.getDouble("ecole");
	        	Double commerce =result.getDouble("commerce");
	        	Double transports =result.getDouble("transports");
	        	int date_construction = result.getInt("date_construction");
	        	int id_adresse = result.getInt("id_adresse");
				String query2 = "SELECT * FROM annonce WHERE id_bien LIKE '"+id_bien+"'";
		        ResultSet result2 = state2.executeQuery(query2);
		        while (result2.next()) {
		        	System.out.println("2");
		        	int id_client = result2.getInt("id_client");
		        	liste_resultat.add(id_client);
		        	int id_annonce = result2.getInt("id_annonce");
		        	liste_resultat.add(id_annonce);
		        	String titre_annonce = result2.getString("titre");
		        	double prix = result2.getDouble("prix");
		        	String type_transaction = result2.getString("type_transaction");
					String query3 = "SELECT * FROM adresse WHERE id_adresse LIKE '"+id_adresse+"'";
			        ResultSet result3 = state3.executeQuery(query3);
			        while (result3.next()) {
			        	System.out.println("3");
			        	String environnement = result3.getString("environnement");
			        	int numero = result3.getInt("numero");
			        	String  voie = result3.getString("voie");
			        	String commune = result3.getString("commune");
			        	String pays = result3.getString("pays");
			        	String code_postal = result3.getString("code_postal");
		        

	        	System.out.println("nom:"+nom+"  date de la construction:"+date_construction+"  surface:"+surface+"  jardin:"+jardin+
	        			"  nombre de pièces:"+nbr_pieces+"  nombre de salles d'eau:"+nbr_sallesdeau+"  distance des transports en commun:"
	        			+transports+"  distance d'un commerce:"+commerce+"  distance d'une école:"+ecole+"");
	        	affichage=affichage+titre_annonce+"\n"+nom+"\nSurface : "+surface+" m2    Surface jardin : "+jardin+" m2\nNombre de pièces : "
	        			+nbr_pieces+"\nNombre de salles d'eau : "+nbr_sallesdeau+"\nDistance à l'école : "+ecole+" m"
	        			+"\nDistance aux commerces : "+commerce+" m"+"\nDistance aux transports en commun "+transports
	        			+" m"+"\nDate de construction : "+date_construction+"\nAdresse : "+numero+" "+voie+"\n"+commune
	        			+" "+code_postal+"\n"+pays+"\nType de transaction : "+type_transaction+"\nPrix : "+prix+" €"+"\n\n-----------------\n\n";
	        	System.out.println(affichage);
	        	}
		        }
	        }
	        if (affichage==""){
	        	Dialogue.aucun_resultat();
	        	return null;
	        }
	        else{
	        Dialogue.afficher_recherche(affichage);
	        return liste_resultat;
	        }
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
	    	System.out.println("NON");
	    	
	    }
		else{
			String type_bien=(String)liste_noms_biens.get(0);
		    liste_noms_biens.remove(0);
		    System.out.println(liste_noms_biens);
			for (int i=0;i<liste_noms_biens.size()-2;i++){	
			    System.out.println(liste_noms_biens.get(i+1));

				int id_client = (int)liste_noms_biens.get(i+1);
				liste_noms_biens.remove(i+1);
			    System.out.println(liste_noms_biens.get(i+1));
				int id_annonce = (int)liste_noms_biens.get(i+1);
				liste_noms_biens.remove(i+1);
			}


			
		Statement stmt = null;
		Statement stmt2 = null;
	    Connection conn = null;
		Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
	    stmt2 = conn.createStatement();


   	  	String[] liste_noms_biens_simple = new String[ liste_noms_biens.size() ];
   	  	
   	  	liste_noms_biens.toArray( liste_noms_biens_simple );
   		String choix_bien = Dialogue.choisirBien(liste_noms_biens_simple);
   		ResultSet res = stmt.executeQuery("SELECT * FROM "+type_bien+" WHERE nom LIKE '"+choix_bien+"'");
   		while (res.next()){
   	  				int id_bien = res.getInt("id_bien");
   	  				System.out.println("oui");
   	  				liste_resultat.add(id_bien);
   		}
		ResultSet res2 = stmt2.executeQuery("SELECT * FROM annonce WHERE id_bien = "+liste_resultat.get(0));
				while (res2.next()){
					int id_annonce = res2.getInt("id_annonce");
	  				liste_resultat.add(id_annonce);
					int id_client = res2.getInt("id_client");
	  				liste_resultat.add(id_client);
			}
	  	stmt.close();
	  	stmt2.close();
	  	res.close();
	  	conn.close();
		}
	    return liste_resultat; 
	 }
	
	public static ArrayList choix_rdv_achat(ArrayList liste_resultat) throws ClassNotFoundException, SQLException, IOException{
		String choix = Dialogue.choix_rdv_achat();
		if (choix=="Prendre rdv pour ce bien"){
			int id_particulier=donnerInfo();
			liste_resultat.add(id_particulier);
			System.out.println("la liste : "+liste_resultat);
			String lecreneau=Creneau.visionner_creneaux_dispos( id_particulier, (int)liste_resultat.get(0));
			return liste_resultat;
		}
		else if (choix=="Acheter ou louer ce bien"){
			int id_particulier=donnerInfo();
			liste_resultat.add(id_particulier);
			BDD.ajouterReceptionClientParticulier((int)liste_resultat.get(2), (int)liste_resultat.get(3), 
					(int)liste_resultat.get(0), (int)liste_resultat.get(1));
			Dialogue.demande_transaction();
			return liste_resultat;
		}
		return liste_resultat;
	}
	
	public void acheter_bien() throws ClassNotFoundException, SQLException{
		
	}
	
	
	public static int donnerInfo() throws ClassNotFoundException, SQLException{
		String nom = Dialogue.nom();
		String prenom = Dialogue.prenom();
		if (BDD.est_dans_BDD("particulier", "nom", nom)==true && BDD.est_dans_BDD("particulier", "prenom", prenom)==true){
			int id_particulier=recupererId_Particulier( nom,  prenom);
			return id_particulier;

		    }
				 
		else{
		String telephone = Dialogue.telephone();
		String email = Dialogue.email();
		int id_particulier = BDD.trouver_id(nom, prenom);
		BDD.ajouterParticulier(id_particulier, nom, prenom, email, telephone);
		return id_particulier;
		}
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
			    if (confirmation==0){
			    	stmt.close();
			    	conn.close();
			    	System.out.println("wesh alors");
					return id_particulier;
			    }
			    
	    
	    }
	    stmt.close();
    	conn.close();
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
	
	public static String dateAjd(){
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=new Date();
		String date_ajd=format.format(date);
		return date_ajd;
	}
	
	public static void fonction_particulier() throws ClassNotFoundException, SQLException, IOException{
		ArrayList liste_nom_biens=recherche_bien();
		if (liste_nom_biens==null){
			System.exit(0);
		}
		ArrayList liste_resultat=choisirBien(liste_nom_biens);
		ArrayList liste_resultat_final=choix_rdv_achat( liste_resultat);
	}

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {


		fonction_particulier();

				
	}

}

