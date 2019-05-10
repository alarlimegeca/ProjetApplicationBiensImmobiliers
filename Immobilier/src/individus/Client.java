package individus;


import interactions.Annonce;
import interactions.TypeTransaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import bien_immobilier.Adresse;
import bien_immobilier.Constructible;
import bien_immobilier.Environnement;
import bien_immobilier.Habitable;
import bien_immobilier.Non_habitable;
import bien_immobilier.TypeHabitation;



public class Client extends Particulier {
	
	// ATTRIBUTS
	
	private static final String conn = null;
	private String pseudo_client;
	private String mot_de_passe_client;
	private boolean est_connecte_client;
	private static Respo_agence respo;
	
	// CONSTRUCTEURS

	public Client(int id_individu, String nom, String prenom, String e_mail, String num_tel, String pseudo_client, String mot_de_passe_client) {
		super(id_individu, nom, prenom, e_mail, num_tel);
		this.pseudo_client = pseudo_client;
		this.mot_de_passe_client = mot_de_passe_client;
		this.respo = respo;
	}
	
	// ACCESSEURS ET MUTATEURS
	
	public String getPseudoClient() {
		return pseudo_client;
	}
	
	public void setPseudo(String pseudo_client) {
		this.pseudo_client = pseudo_client;
	}
	
	public String getPasse() {
		return mot_de_passe_client;
	}
	
	public void setPasse(String mot_de_passe_client) {
		this.mot_de_passe_client = mot_de_passe_client;
	}
	
	public static Respo_agence getRespo() {
		return respo;
	}

	public static void setRespo(Respo_agence respo) {
		Client.respo = respo;
	}
	
	 public void lienCliRes(Respo_agence respo){
			respo.getReceptionC().add(this);
			this.setRespo(respo);
	 }
	
	// AUTRES METHODES

	 /**
	  * permet au client de demander un compte client au responsable
	  */
	 
	
	public static void creer_compte() {
	    
		JOptionPane jop = new JOptionPane();
	    
		String nom = Dialogue.nom_ind();
		String prenom = Dialogue.prenom_ind(); 
		String e_mail = Dialogue.e_mail();
		String num_tel = Dialogue.num_tel_ind();
		
		String pseudo = Dialogue.pseudo("client");
		while (BDD.est_dans_BDD("client", "pseudo_client", pseudo)) {
			jop.showMessageDialog(null, "Le pseudo est déjà utilisé par un autre client.","Adresse", JOptionPane.INFORMATION_MESSAGE);
		    pseudo = Dialogue.pseudo("client");
		}
		
        String mdp = Dialogue.mot_de_passe("client");
    	jop.showMessageDialog(null, "Veuillez confirmer votre mot de passe.","Adresse", JOptionPane.INFORMATION_MESSAGE);
	    String cmdp = Dialogue.mot_de_passe("client");
		while (!(mdp.contentEquals(cmdp))) {
			jop.showMessageDialog(null, "Les deux mots de passe doivent correspondre.","Adresse", JOptionPane.INFORMATION_MESSAGE);
			mdp = Dialogue.mot_de_passe("client");
			jop.showMessageDialog(null, "Les deux mots de passe doivent correspondre.","Adresse", JOptionPane.INFORMATION_MESSAGE);
			cmdp = Dialogue.mot_de_passe("client");
		}
		int id_client = BDD.trouver_id(nom, prenom);
	    Client client = new Client(id_client,nom,prenom,e_mail,num_tel,pseudo,mdp);
		jop.showMessageDialog(null, client.toString("client"),"Adresse", JOptionPane.INFORMATION_MESSAGE);
		int valide = Dialogue.confirmation("Souhaitez-vous confirmer votre candidature ?");
		if (valide == 0) {
			BDD.ajouterClient("reception_candidature_client",id_client, nom, prenom, e_mail, num_tel, pseudo, mdp);
			jop.showMessageDialog(null, "Consultez votre boîte e-mail,\n notre responsable confirmera ou non votre candidature \n dans les meilleurs délais" ,"Adresse", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	
	/**
	 * permet au client de se connecter à son compte client
	 * @return le client qui sera pris en paramètre de la fenêtre client
	 */
	
	public static Client se_connecter_client() {
		String pseudo = Dialogue.pseudo("client");
		String passe = Dialogue.mot_de_passe("client");
		Connection conn = null;
        try {
        	JOptionPane jop = new JOptionPane();
        	// db parameters
        	String url = "jdbc:sqlite:bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	// Requ�te SQL
        	String query = "SELECT * FROM client WHERE pseudo_client LIKE '" + pseudo+"'";
        	Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            String mdp = result.getString("mot_de_passe_client");
            int id = result.getInt("id_individu");
            
            if (mdp.contentEquals(passe)) {
            	jop.showMessageDialog(null, "Vous êtes bien connecté sous le pseudo "+pseudo, "Récapitulatif adresse", JOptionPane.INFORMATION_MESSAGE);  
            	Client client = (Client) BDD.construire_ind(id,"client");
                return client;
            }
            else { 
            	jop.showMessageDialog(null, "Mot de passe incorrect", "connexion", JOptionPane.INFORMATION_MESSAGE);  
            }
            
            
            
        } catch (SQLException e1) {
        	System.out.println(e1.getMessage());
        	
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
		return null;

	}
	
	/**
	 * permet au client de proposer une nouvelle annonce au responsable d'agence
	 */
	
	public void soumettre_annonce() {
		
		// On rentre les informations générales
		
		String titre = Dialogue.titreAnnonce();
		
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null, "Vous allez devoir rentrer l'adresse précise de votre bien dans un premier temps.","Adresse", JOptionPane.INFORMATION_MESSAGE);
		
		int id_adresse = BDD.trouver_id_adresse();
		
		String pays = Dialogue.pays();
		
		String commune = Dialogue.commune();
		
		String postal = Dialogue.code_postal();
		
		String insee = Dialogue.code_INSEE();
		
		String num = Dialogue.numero();
		int numero = Integer.parseInt(num);
		
		String voie = Dialogue.voie();
		
		String environ = Dialogue.typeEnvironnement();
		Environnement environnement = Environnement.parseEnvironnement(environ);
		
		Adresse adresse = new Adresse(id_adresse,numero,voie,postal,insee,commune,pays,environnement);
		Adresse.ajouterAdresse(id_adresse, pays, numero, voie, postal, insee, commune, environnement);
		
		JOptionPane jop1 = new JOptionPane();
		jop1.showMessageDialog(null, adresse.toString(), "Récapitulatif adresse", JOptionPane.INFORMATION_MESSAGE);  
		
		JOptionPane jop2 = new JOptionPane();
		jop2.showMessageDialog(null, "Vous allez maintenant devoir rentrer les caractéristiques du bien en lui-même","Bien", JOptionPane.INFORMATION_MESSAGE);
		
		String typeBien = Dialogue.typeBien();
		
		String precis = Dialogue.typeHabitation(typeBien);
		TypeHabitation habitation = TypeHabitation.parseHabitation(precis);
		
		String transac = Dialogue.typeTransaction();
		TypeTransaction transaction = TypeTransaction.parseTransaction(transac);
		
		String nom = Dialogue.nomBien();
		
		String surf = Dialogue.surfaceBien();
		double surface = Double.parseDouble(surf);
		
		String dist = Dialogue.distanceTransports();
		double distancetr = Double.parseDouble(dist);
		
		String dist2 = Dialogue.distanceCommerce();
		double distancecom = Double.parseDouble(dist2);
		
		String dist3 = Dialogue.distanceEcole();
		double distanceeco = Double.parseDouble(dist3);
		
		Agent_immobilier agent = new Agent_immobilier(0, "à déterminer", "à déterminer", "à déterminer", "à déterminer", "à déterminer", "à déterminer");
		
		int id_annonce = BDD.trouver_id_annonce();
		int id_bien = BDD.trouver_id_bien();	
		
		Annonce annonce = new Annonce(0,"",true,null,0,null,null,this,typeBien);
		
		// On rentre les paramètres du bien selon le cas correspondant
		
		if (typeBien.equals("Constructible")) {
		String qual = Dialogue.qualiteTerrain();
		int qualite = Integer.parseInt(qual);
		typeBien = "constructible";
		Constructible constructible = new Constructible(id_bien, nom, false, adresse, surface,distancetr, habitation, qualite, distanceeco, distancecom);
		double prix = constructible.estimation_Constr(transaction);
		System.out.println(prix);
		annonce = new Annonce(id_annonce,titre, true, transaction,prix, constructible, agent, this,typeBien);
		
		JOptionPane jop5 = new JOptionPane();
		jop5.showMessageDialog(null, constructible.toString(), "Récapitulatif bien", JOptionPane.INFORMATION_MESSAGE);  
		jop5.showMessageDialog(null, annonce.affichage().get(2), "Récapitulatif bien", JOptionPane.INFORMATION_MESSAGE);  
		
		int valide = Dialogue.confirmation("Souhaitez-vous confirmer votre candidature ?");
		if (valide == 0) {
			constructible.ajouterBien_immo_Constr() ;
			jop.showMessageDialog(null, "Consultez votre boîte e-mail,\n notre responsable confirmera la mise en ligne de votre annonce \n dans les meilleurs délais" ,"Adresse", JOptionPane.INFORMATION_MESSAGE);
			annonce.ajouterAnnonce("reception_annonce");
			}
		}
		
		if (typeBien.equals("Habitable")) {
		
	    typeBien = "habitable";
		String surf2 = Dialogue.surfaceBatie();
		double surfaceBatie = Double.parseDouble(surf2);
		
		String surf3 = Dialogue.surfaceJardin();
		double surfaceJardin = Double.parseDouble(surf3);
		
		String nbpi = Dialogue.nb_pieces();
		int nbpieces = Integer.parseInt(nbpi);
		
		String nbba = Dialogue.nb_salles_eaux();
		int nbbains = Integer.parseInt(nbba);
		
		String annee = Dialogue.date_construction();
		int anneeconstr = Integer.parseInt(annee);
		
		Habitable habitable = new Habitable(id_bien, nom, false, adresse, surface,distancetr, habitation, surfaceBatie,anneeconstr, nbpieces, nbbains, distancecom, distanceeco, surfaceJardin); 
		double prix = habitable.estimation_Hab(transaction);
		annonce = new Annonce(id_annonce,titre, true, transaction,prix, habitable, agent, this,typeBien);
		
		JOptionPane jop3 = new JOptionPane();
		jop3.showMessageDialog(null, habitable.toString(), "Récapitulatif bien", JOptionPane.INFORMATION_MESSAGE);  
		jop3.showMessageDialog(null, annonce.affichage().get(2), "Récapitulatif bien", JOptionPane.INFORMATION_MESSAGE);  
		
		int valide = Dialogue.confirmation("Souhaitez-vous confirmer votre annonce ?");
		if (valide == 0) {
			habitable.ajouterBien_immo_Hab() ;
			jop.showMessageDialog(null, "Consultez votre boîte e-mail,\n notre responsable confirmera la mise en ligne de votre annonce \n dans les meilleurs délais" ,"Adresse", JOptionPane.INFORMATION_MESSAGE);
			annonce = new Annonce(id_annonce,titre, true, transaction,prix, habitable, agent, this, typeBien);
			annonce.ajouterAnnonce("reception_annonce");
		}
		}
		
		if (typeBien.equals("Non habitable")) {
		typeBien = "non_habitable";	
		String annee = Dialogue.date_construction();
		int anneeconstr = Integer.parseInt(annee);
		
		String surf2 = Dialogue.surfaceBatie();
		double surfaceBatie = Double.parseDouble(surf2);
		 
		
		Non_habitable non_habitable = new Non_habitable(id_bien, nom,  false, adresse, surface,distancetr, habitation, surfaceBatie,anneeconstr, distancecom, distanceeco); 
		double prix = non_habitable.estimation_Nhab(transaction);
		System.out.println(prix);
		annonce = new Annonce(id_annonce,titre, true, transaction,prix, non_habitable, agent, this,typeBien);
		
		JOptionPane jop4 = new JOptionPane();
		jop4.showMessageDialog(null, non_habitable.toString(), "Récapitulatif bien", JOptionPane.INFORMATION_MESSAGE);  
		jop4.showMessageDialog(null, annonce.affichage().get(2), "Récapitulatif bien", JOptionPane.INFORMATION_MESSAGE);  
		
		
		int valide = Dialogue.confirmation("Souhaitez-vous confirmer votre annonce ?");
		if (valide == 0) {
			non_habitable.ajouterBien_immo_NonHab();	
			jop4.showMessageDialog(null, "Consultez votre boîte e-mail,\n notre responsable confirmera la mise en ligne de votre annonce \n dans les meilleurs délais" ,"Adresse", JOptionPane.INFORMATION_MESSAGE);
			annonce.ajouterAnnonce("reception_annonce");
		}

		
		}
		
	
	}
	
	/**
	 * le client choisit le particulier avec qui la transaction sera faite parmi plusieurs candidats 
	 */
	
	public void choisir_particulier() {
		Connection conn = null;
		ArrayList<String> titres_annonces = new ArrayList();
		ArrayList<Annonce> annonces = new ArrayList();
		
		// Le client choisit l'annonce
		
	    try {
	    	String url = "jdbc:sqlite:bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// Requête SQL
	    	String query = "SELECT * FROM annonce WHERE valide = 1 AND id_client = " +this.getId();
	    	
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);
	    	
	    	while (result.next()) {
            	int id = result.getInt("id_annonce");
            	String titre = result.getString("titre");
            	Annonce annonce = BDD.construire_annonce(id, "annonce");
            	annonces.add(annonce);
        	    titres_annonces.add(titre);
	    	}
	    	
	    } catch (SQLException e1) {
	    	System.out.println(e1.getMessage());
	    }catch (ArrayIndexOutOfBoundsException e){
	    	JOptionPane jop4 = new JOptionPane();
			jop4.showMessageDialog(null, "Vous n'avez pas d'annonce en ligne", "Récapitulatif bien", JOptionPane.INFORMATION_MESSAGE);  
			
      	
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
	  System.out.println(titres_annonces);
	    String[] array_titres = titres_annonces.toArray(new String[0]);
		String titre = Dialogue.choisir_annonce(array_titres);
		int position = Dialogue.chercher(titres_annonces,titre);
		int id_annonce = annonces.get(position).getId_annonce();
		
		
		ArrayList<String> l_particuliers = new ArrayList();
		
	    try {
	    	String url = "jdbc:sqlite:bdd.db";
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection(url);
	    	// Requête SQL
	    	String query = "SELECT * FROM reception_client_particulier WHERE id_client = "+this.getId()+" AND id_annonce = "+id_annonce;
	    	
	    	Statement state = Connexion.getinstance().createStatement();
	    	ResultSet result = state.executeQuery(query);
	    
	    	while (result.next()) {
            	int id_particulier = result.getInt("id_particulier");
            	Particulier particulier = (Particulier) BDD.construire_ind(id_particulier, "particulier");
            	l_particuliers.add(id_particulier+" "+particulier.getPrenom() +" "+ particulier.getNom());
	    	}
	    	
	    	  String query2 = "DELETE FROM reception_client_particulier WHERE id_client = "+this.getId()+" AND id_annonce = "+id_annonce;
	          state.executeQuery(query2);
	    	
	    } catch (SQLException e1) {
	    	System.out.println(e1.getMessage());
	    	
	    }catch (ArrayIndexOutOfBoundsException e){
	    	
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
	    
	    // Le client choisit le particulier
	    
	    if (l_particuliers.size()==0) {
	    	JOptionPane jop4 = new JOptionPane();
			jop4.showMessageDialog(null, "Vous n'avez aucune proposition", "Récapitulatif bien", JOptionPane.INFORMATION_MESSAGE);  
	    }
	    else {
	    String[] particuliers = l_particuliers.toArray(new String[0]);
	    String choix = Dialogue.choisir_particulier(particuliers);
	    String separation[]  = choix.split(" ");
	    List<String> list = Arrays.asList(separation);
	    
	    int id_particulier = Integer.parseInt(list.get(0));
	    BDD.ajouterReceptionRespoTrans(getId(), id_particulier,id_annonce);
	}
	    }
	

}
