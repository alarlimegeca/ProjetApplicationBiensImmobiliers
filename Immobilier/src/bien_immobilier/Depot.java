package individus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import individus.BDD;
import individus.Connexion;
import individus.Dialogue;
import interactions.Annonce;
import interactions.Creneau;

public class Particulier extends Individu{
	
	// CONSTRUCTEURS

	public Particulier(int id_individu, String nom, String prenom, String e_mail, String num_tel) {
		super(id_individu, nom, prenom, e_mail, num_tel);
		
	}
	
public  void recherche_bien2() {
		ArrayList<Annonce> annonces=new ArrayList<>();
		ArrayList<String> liste_affichage=new ArrayList<>();
		Connection conn = null;
	    try {
			

	    	// db parameters
	    	String url = "jdbc:sqlite:bdd";
	    	//create a connection to the database
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
			
			// Requête SQL
	    	String type_bien=Particulier.typeBien();
	    	String type_environnement = Dialogue.typeEnvironnement();
	    	String type_habitation = Particulier.typeHabitation(type_bien);
			
	    	String query = "SELECT * FROM annonce";
			System.out.println(query);
			Statement state = Connexion.getinstance().createStatement();
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				int id_annonce = result.getInt("id_annonce");
            	Annonce annonce = BDD.construire_annonce(id_annonce, "annonce");
            	if (annonce.getHabitation().equals(type_bien) && annonce.getLebien().getType_habitation().getContenu2().equals(type_habitation) && annonce.getLebien().getAdresse().getEnvironnement().getContenu1().equals(type_environnement) ) {
            		annonces.add(annonce);
            		liste_affichage.add(id_annonce+ " "+ annonce.getTitre());
            	}
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
	    System.out.println(liste_affichage);
	    String[] l_annonces = liste_affichage.toArray(new String[0]);
	    Annonce choix = Dialogue.choisir_annonces(l_annonces);
	  
	    BDD.ajouterReceptionClientParticulier(choix.getClient().getId(), this.getId(), choix.getLebien().getId_bien(),choix.getId_annonce());
	    }

	public static ArrayList choisirBien(ArrayList liste_noms_biens) throws ClassNotFoundException, SQLException{
		ArrayList<Object> liste_resultat=new ArrayList<>();
		

		
	    if (liste_noms_biens.size()==1){
			Dialogue.aucun_resultat();
		}
		else{
			String type_bien=(String)liste_noms_biens.get(0);
		    liste_noms_biens.remove(0);
		    int id_client = (int)liste_noms_biens.get(liste_noms_biens.size()-2);
		    liste_noms_biens.remove(liste_noms_biens.size()-2);
		    liste_resultat.add(id_client);
		    int id_annonce = (int)liste_noms_biens.get(liste_noms_biens.size()-1);
		    liste_noms_biens.remove(liste_noms_biens.size()-1);
		    liste_resultat.add(id_annonce);

		
		Statement stmt = null;
	    Connection conn = null;
		Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();

   	  	String[] liste_noms_biens_simple = new String[ liste_noms_biens.size() ];
   	  	
   	  	liste_noms_biens.toArray( liste_noms_biens_simple );
   		String choix_bien = Dialogue.choisirBien(liste_noms_biens_simple);
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
	
	public static ArrayList choix_rdv_achat(ArrayList liste_resultat) throws ClassNotFoundException, SQLException, IOException{
		String choix = Dialogue.choix_rdv_achat();
		if (choix=="Prendre rdv pour ce bien"){
			int id_particulier=donnerInfo();
			liste_resultat.add(id_particulier);
			System.out.println("la liste : "+liste_resultat);
			String lecreneau=Creneau.visionner_creneaux_dispos( id_particulier, (int)liste_resultat.get(0));
			return liste_resultat;
		}
		else if (choix=="Acheter ce bien"){
			int id_particulier=donnerInfo();
			liste_resultat.add(id_particulier);
			BDD.ajouterReceptionClientParticulier((int)liste_resultat.get(0), (int)liste_resultat.get(3), 
					(int)liste_resultat.get(2), (int)liste_resultat.get(1));
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
	    String[] listeHabitation = {"Terrain_vague", "Prairie", "Foret"};
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
	

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {

		
		Particulier particulier = new Particulier(123,"ZER","ert","ert","aze");
		particulier.recherche_bien2();
		//ArrayList liste_nom_biens=recherche_bien();
		//ArrayList liste_resultat=choisirBien(liste_nom_biens);
		//System.out.println(liste_resultat);
		//ArrayList liste_resultat_final=choix_rdv_achat( liste_resultat);
		//		int id_particulier = donnerInfoRdv() ;
//		Creneau.visionner_creneaux_dispos(id_particulier, 200);
//		Creneau.demandeRdv();
//		Agent_immobilier.rdvAgent(200);
	
		
		
		
	}

}