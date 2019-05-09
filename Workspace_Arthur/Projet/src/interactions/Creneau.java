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

import individus.Dialogue;

public class Creneau {
	
	public String creneau;
	
	public Creneau(String creneau) {
		this.creneau=creneau;
	}
	
	public String getCreneau() {
		return this.creneau;
	}
	
	public void setCreneau(String creneau) {
		this.creneau=creneau;
	}
	
	
	public static void ajouterCreneau() {
		String creneau = Dialogue.creneau();
		BDD.ajouterCreneau(creneau);
	}
	
		
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
	    ResultSet res = stmt.executeQuery("SELECT heure FROM creneau");
        while (res.next()){
             liste_creneau.add(res.getString("heure"));
         }
        String[] liste_creneau_simple = new String[ liste_creneau.size() ];
        liste_creneau.toArray( liste_creneau_simple );
	    ResultSet res2 = stmt2.executeQuery("SELECT id_agent FROM annonce WHERE id_bien="+id_bien);
	    while (res2.next()){
	    	int id_agent = res2.getInt("id_agent");
		    stmt.close();
	        conn.close();
	        try{
	            String lecreneau=Dialogue.creneauDispo(liste_creneau_simple);
	            BDD.ajouterRDV(lecreneau, id_particulier, id_agent, id_bien,0);
	            System.out.println(lecreneau);
	    		Connection conn2 = null;
	    	    Statement stmt3 = null;
	    	    String sql = "DELETE FROM creneau WHERE heure = '"+lecreneau+"'";
	    	    Class.forName("org.sqlite.JDBC");
	    	    conn2 = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    	    stmt3 = conn2.createStatement();
	    	    stmt3.executeUpdate(sql);
	    	    stmt3.close();
	    	    conn2.close();
	    	    
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
		Connection conn = null;
	    Statement stmt = null;
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
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
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
	    stmt = conn.createStatement();
	    stmt2 = conn.createStatement();
	    stmt3 = conn.createStatement();
	    stmt4 = conn.createStatement();  
	    
	    //On va chercher toutes les informations sur le rdv dans la BDD
	    
	    ResultSet res = stmt.executeQuery("SELECT * FROM rendezvous WHERE rdv_valide =0");
        while (res.next()){

           String heure = res.getString("heure");
           int id_bien = res.getInt("id_bien");
           String id_particulier = res.getString("id_particulier");
           ResultSet res2 = stmt2.executeQuery("SELECT * FROM biens_immobiliers WHERE id_bien = "+id_bien);
           while (res2.next()){

        	   String nom_bien = res2.getString("nom");
        	   String id_adresse = res2.getString("id_adresse");
        	   ResultSet res3 = stmt3.executeQuery("SELECT * FROM adresse WHERE id_adresse = "+id_adresse);
               while (res3.next()){

            	   String numero = res3.getString("numero");
            	   String voie = res3.getString("voie");
            	   String code_postal = res3.getString("code_postal");
            	   String commune = res3.getString("commune");
            	   String pays = res3.getString("pays");
            	   ResultSet res4 = stmt4.executeQuery("SELECT * FROM particulier WHERE id_individu = "+id_particulier);
                   while (res4.next()){

                	   String nom_particulier = res4.getString("nom");
                	   String prenom_particulier = res4.getString("prenom");
                	   String email_particulier = res4.getString("e_mail");
                	   String tel_particulier = res4.getString("num_tel");
           		        stmt.close();
           		        stmt2.close();
           		        stmt3.close();
           		        stmt4.close();
           			    conn.close();
                       valider_rdv(id_bien,heure,nom_bien, numero, voie, code_postal, commune, pays, nom_particulier, prenom_particulier, email_particulier,tel_particulier);
                       demandeRdv();
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
				Connection conn = null;
			    Statement stmt = null;
			    Class.forName("org.sqlite.JDBC");
			    conn = DriverManager.getConnection("jdbc:sqlite:bdd.db");
			    stmt = conn.createStatement();
			    ResultSet res = stmt.executeQuery("SELECT id_agent FROM annonce WHERE id_bien LIKE '" + id_bien+"'");
			    while (res.next()){
		             int id_agent=res.getInt("id_agent");  
					 stmt.close();
				     String sql = "UPDATE rendezvous set id_agent = ? where heure= ?";
				     PreparedStatement prepstmt = conn.prepareStatement(sql);
				     prepstmt .setInt(1, id_agent);
				     prepstmt .setString(2, heure);
				     prepstmt .executeUpdate();
				     String sql2 = "UPDATE rendezvous set rdv_valide = ? where heure= ?";
				     PreparedStatement prepstmt2 = conn.prepareStatement(sql2);
				     prepstmt2.setInt(1, 1);
				     prepstmt2.setString(2, heure);
				     prepstmt2.executeUpdate();
				     conn.close();

			    }
			    Dialogue.validation_rdv_respo();
			}
			
			else{
				//Si il refuse le rdv, il est supprimé de la BDD et une boite de dialogue s'ouvre pour ne pas oublier de prévenir le particulier
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


            ResultSet res = stmt.executeQuery("SELECT max(rowid) FROM creneau");
            while (res.next()){
                count = res.getInt(1);
            }
            
            System.out.println("count = "+count);

            for (int i = count ; i>0 ; i--  ) {
            	try{
            	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            	ResultSet res2 = stmt2.executeQuery("SELECT * FROM creneau WHERE rowid= " + i );
        		Date date_ajd=new Date();
        		String date ="";
                while (res2.next()) {
                	date = res2.getString("heure");
                	System.out.println(date);
                }
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
			
		/**
		 * regroupe toutes les fonctions pour la validation d'un rdv
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		
	public static void fonction_validation_rdv() throws ClassNotFoundException, SQLException{
		demandeRdv();   
		Dialogue.pas_de_demande();

	}
		

		
}



