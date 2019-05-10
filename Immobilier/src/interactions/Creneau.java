package interactions;


import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import individus.BDD;
import individus.Connexion;
import individus.Dialogue;

public class Creneau {
	
	//ATTRIBUTS
	
	public String creneau;
	
	//CONSTRUCTEUR
	
	public Creneau(String creneau) {
		this.creneau=creneau;
	}
	
	//ACCESSEURS ET MUTATEURS
	
	public String getCreneau() {
		return this.creneau;
	}
	
	public void setCreneau(String creneau) {
		this.creneau=creneau;
	}
	
	

	
	/**
	 * permet au particulier de voir les créneaux disponibles pour un rdv et d'en choisir un
	 * @param id_particulier
	 * @param id_bien
	 * @return le créneau sélectionner par le particulier
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	
	public static String visionner_creneaux_dispos(int id_particulier, int id_bien) throws ClassNotFoundException, SQLException, IOException{
		supprimerCreneauAncien();
		Connection conn = null;
	    Statement stmt = null;
	    Statement stmt2 = null;
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
	    stmt2 = conn.createStatement();
	    ArrayList<String> liste_creneau=new ArrayList<String>();
	    //On récupère tous les créneaux de la BDD
	    ResultSet res = stmt.executeQuery("SELECT heure FROM creneau");
        while (res.next()){
             liste_creneau.add(res.getString("heure"));
         }
        String[] liste_creneau_simple = new String[ liste_creneau.size() ];
        liste_creneau.toArray( liste_creneau_simple );
        //On récupère l'id de l'agent qui s'occupe du bien
	    ResultSet res2 = stmt2.executeQuery("SELECT id_agent FROM annonce WHERE id_bien="+id_bien);
	    while (res2.next()){

	    	int id_agent = res2.getInt("id_agent");
	    
		    stmt.close();
	        conn.close();
	        try{
	        	//Le particulier choisit le créneau qui lui convient
	            String lecreneau=Dialogue.creneauDispo(liste_creneau_simple);
	            //Le rdv est ajouté à la BDD avec rdv_valide=0
	            BDD.ajouterRDV(lecreneau, id_particulier, id_agent, id_bien,0);
	    		Connection conn2 = null;
	    	    Statement stmt3 = null;
	    	    //On supprime le créneau pris par le particulier dans la BDD
	    	    String sql = "DELETE FROM creneau WHERE heure = '"+lecreneau+"'";
	    	    Class.forName("org.sqlite.JDBC");
	    	    conn2 = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    	    stmt3 = conn2.createStatement();
	    	    stmt3.executeUpdate(sql);
	    	    stmt3.close();
	    	    conn2.close();
	    	    Dialogue.validation_rdv_particulier();
	    	    return lecreneau;

	            }
	            catch (ArrayIndexOutOfBoundsException e){
	            	Dialogue.aucun_creneau();
	            	return null;
	            }
	    	}
		return null;
	    }
	
	/**
	 * permet de voir si il y a une demande de rdv
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static void demandeRdv() throws ClassNotFoundException, SQLException {
		try {
		Connection conn = null;
	    Statement stmt = null;
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
	    //On regarde si il y a des rdv pas encore validés
	    ResultSet res = stmt.executeQuery("SELECT heure FROM rendezvous WHERE rdv_valide=0");        
	    while (res.next()){
     	   	String heure = res.getString("heure");
     	   	stmt.close();
     	   	conn.close();

     	   if (heure!=null){
     		   //On vérifie si le responsable souhaite voir ses demandes de rdv
     		   String choix=Dialogue.voirRdv();
     		   if (choix=="Oui"){
     			  creneauDispo();
     		   }
     		   else{
     			   System.exit(0);
     		   }
     	   }
     	        	   
        }	
		}
		catch(SQLException e) {
			
		}
	    
	}
	
	/**
	 * permet de visualiser les demandes de rdv
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static void creneauDispo() throws ClassNotFoundException, SQLException{
		Connection conn = null;
	    Statement stmt = null;
	    Statement stmt2 = null;
	    Statement stmt3 = null;
	    Statement stmt4 = null;
	    Statement stmt5 = null;

	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
	    stmt2 = conn.createStatement();
	    stmt3 = conn.createStatement();
	    stmt4 = conn.createStatement();  
	    stmt5 = conn.createStatement();  

	    //On va chercher toutes les informations sur le rdv dans la BDD
	    
	    ResultSet res = stmt.executeQuery("SELECT * FROM rendezvous WHERE rdv_valide =0");
        while (res.next()){
           String heure = res.getString("heure");
           int id_bien = res.getInt("id_bien");
           String id_particulier = res.getString("id_particulier");
           ResultSet res2 = stmt2.executeQuery("SELECT * FROM annonce WHERE id_bien = "+id_bien);
           while (res2.next()){
        	   String type_bien = res2.getString("habitation");
        	   ResultSet res3 = stmt3.executeQuery("SELECT * FROM "+type_bien+" WHERE id_bien = "+id_bien);
               while (res3.next()){
            	   int id_adresse = res3.getInt("id_adresse");
            	   String nom_bien = res3.getString("nom");
            	   ResultSet res4 = stmt4.executeQuery("SELECT * FROM adresse WHERE id_adresse = "+id_adresse);
            	   while (res4.next()){
            		   String numero = res4.getString("numero");
            		   String voie = res4.getString("voie");
            		   String code_postal = res4.getString("code_postal");
            		   String commune = res4.getString("commune");
            		   String pays = res4.getString("pays");
            		   ResultSet res5 = stmt5.executeQuery("SELECT * FROM particulier WHERE id_individu = "+id_particulier);
            		   while (res5.next()){
            			   String nom_particulier = res5.getString("nom");
            			   String prenom_particulier = res5.getString("prenom");
            			   String email_particulier = res5.getString("e_mail");
            			   String tel_particulier = res5.getString("num_tel");
            			   res.close();
            			   res2.close();
            			   res3.close();
            			   res4.close();
            			   res5.close();
            			   stmt.close();
            			   stmt2.close();
            			   stmt3.close();
            			   stmt4.close();
            			   stmt5.close();
            			   conn.close();
            			   valider_rdv(id_bien,heure,nom_bien, numero, voie, code_postal, commune, pays, nom_particulier, prenom_particulier, email_particulier,tel_particulier);
            			   conn.close();
            			   demandeRdv();
                   }
               }
           }
           
        }
        }

	}
	
	/**
	 * permet de valider ou non le rdv 
	 * @param id_bien
	 * @param heure
	 * @param nom_bien
	 * @param numero
	 * @param voie
	 * @param code_postal
	 * @param commune
	 * @param pays
	 * @param nom_particulier
	 * @param prenom_particulier
	 * @param email_particulier
	 * @param tel_particulier
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static void valider_rdv(int id_bien, String heure, String nom_bien, String numero, String voie, String code_postal, 
			String commune, String pays, String nom_particulier, String prenom_particulier, String email_particulier, String tel_particulier) throws ClassNotFoundException, SQLException{
			
		//Le responsable accepte ou non le rdv 
		String choix=Dialogue.accepter_rdv(heure,  nom_bien,  numero,  voie,  code_postal,  commune, 
				   pays,  nom_particulier,  prenom_particulier);
			
			if (choix == "Oui"){
				//S'il accepte, le rdv est valide et la BDD est modifiée
		    	int id_agent = 0;
		    	try {
		    	Connection conn = null;
			    Statement stmt = null;

			    Class.forName("org.sqlite.JDBC");
			    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
			    stmt = conn.createStatement();


			    ResultSet res = stmt.executeQuery("SELECT id_agent FROM annonce WHERE id_bien LIKE '" + id_bien+"'");
			    while (res.next()){
		             id_agent+=res.getInt("id_agent");  
					 stmt.close();
		             res.close();

			    }
		    	conn.close();
			    
			    	
			    	try {
			    		
			    	String sql = "UPDATE rendezvous set id_agent = ? where heure = ?";
				    PreparedStatement prepstmt = Connexion.getinstance().prepareStatement(sql);
				    prepstmt.setInt(1, id_agent);
				    prepstmt.setString(2, heure);
				    prepstmt.executeUpdate();
				    prepstmt.close();

				    String sql2 = "UPDATE rendezvous set rdv_valide = ? where heure= ?";
				    PreparedStatement prepstmt2 = Connexion.getinstance().prepareStatement(sql2);
				    prepstmt2.setInt(1, 1);
				    prepstmt2.setString(2, heure);
				    prepstmt2.executeUpdate();
				    prepstmt2.close();

			    	}
			    	catch(SQLException e) {
			    	}
			    	
			    }
			
			    finally{
				    Dialogue.validation_rdv_respo();

			    }
			    

			    
			}
			
			else{
				//Si il refuse le rdv, il est supprimé de la BDD et une boite de dialogue 
				//s'ouvre pour ne pas oublier de prévenir le particulier
				Connection conn = null;
			    Statement stmt = null;
			    Class.forName("org.sqlite.JDBC");
			    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
			    stmt = conn.createStatement();
			    String sql = "DELETE from rendezvous where heure LIKE '"+heure+"'";
    			stmt.executeUpdate(sql);
				Dialogue.refus_rdv(email_particulier, tel_particulier);
				stmt.close();
			    conn.close();

			}
			
	}
	
	/**
	 * permet de supprimer les créneaux antérieurs à la date d'aujourd'hui dans la BDD
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
		
		public static void supprimerCreneauAncien() throws SQLException, ClassNotFoundException, IOException {
		      
		    int count = 0;
			Connection conn = null;
		    Statement stmt = null;
		    Statement stmt2 = null;
		    Statement stmt3 = null;
		    Statement stmt4 = null;
		    Statement stmt5 = null;


		    Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
		    stmt = conn.createStatement();
		    stmt2 = conn.createStatement();
		    stmt3 = conn.createStatement();
		    stmt4 = conn.createStatement();
		    stmt5 = conn.createStatement();

		    //On compte le nombre d'éléments dans la BDD
            ResultSet res = stmt.executeQuery("SELECT max(rowid) FROM creneau");
            while (res.next()){
                count = res.getInt(1);
            }
            
            System.out.println("count = "+count);

            for (int i = count ; i>0 ; i--  ) {
            	try{
            	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            	ResultSet res2 = stmt2.executeQuery("SELECT * FROM creneau WHERE rowid= " + i );
        		
            	//On récupère la date d'aujourd'hui
            	Date date_ajd=new Date();
        		String date ="";
                while (res2.next()) {
                	date = res2.getString("heure");
                	System.out.println(date);
                }
                //On la compare à nos dates présentes dans la table "creneaux"
            	Date date1 = format.parse(date);
        		if (date_ajd.compareTo(date1) >= 0) {          	
        			System.out.println("supprime date trop ancienne");
        			conn.setAutoCommit(false);
        			String sql2 ="DELETE from creneau where rowid="+i;
        			stmt4.executeUpdate(sql2);
        			conn.commit();
        		}
            	
            }
            	catch (ParseException e){
            		continue;
            	}
            	catch (NullPointerException e){
            		System.out.println("supprime null");
            		String sql = "DELETE from creneau where rowid="+i;
        			stmt3.executeUpdate(sql);
            	}
            }
            stmt.close();
            stmt2.close();
            stmt3.close();
            stmt4.close();
            stmt5.close();

		    conn.close();
		}
		
	
		
}
