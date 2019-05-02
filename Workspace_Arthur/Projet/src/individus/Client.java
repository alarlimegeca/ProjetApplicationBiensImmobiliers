package individus;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Client extends Particulier {
	
	// ATTRIBUTS
	
	private String pseudo_client;
	private String mot_de_passe_client;
	private boolean est_connecte_client;
	
	// CONSTRUCTEURS

	public Client(int id_individu, String nom, String prenom, String e_mail, int num_tel, String pseudo_client, String mot_de_passe_client) {
		super(id_individu, nom, prenom, e_mail, num_tel);
		this.pseudo_client = pseudo_client;
		this.mot_de_passe_client = mot_de_passe_client;
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
	
	// METHODES
	
	static Scanner scan = new Scanner(System.in);
	
	public static void creer_compte(Respo_agence respo) {
	    
		JOptionPane jop = new JOptionPane();
	    
		String nom = Dialogue.nom_ind();
		String prenom = Dialogue.prenom_ind(); 
		String e_mail = Dialogue.e_mail();
		String num_tel = Dialogue.num_tel_ind();
		
		String pseudo = Dialogue.pseudo("client");
		while (BDDInd.est_dans_BDD("client", "pseudo_client", pseudo)) {
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
	    //BDDInd.ajouterClient(id_individu, nom, prenom, e_mail, num_tel, pseudo, mdp);
		Client client = new Client(0,nom,prenom,e_mail,num_tel,pseudo,mdp);
		jop.showMessageDialog(null, client.toString("client"),"Adresse", JOptionPane.INFORMATION_MESSAGE);
		int valide = Dialogue.confirmation("Souhaitez-vous confirmer votre candidature ?");
		if (valide == 0) {
			client.lienCliRes(respo);
		}
	}
	
	
	public static void se_connecter_client() {
		String pseudo = Dialogue.pseudo("client");
		String passe = Dialogue.mot_de_passe("client");
		Connection conn = null;
        try {
        	JOptionPane jop = new JOptionPane();
        	// db parameters
        	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	// Requête SQL
        	String query = "SELECT * FROM client WHERE pseudo_client LIKE '" + pseudo+"'";
        	Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            String mdp = result.getString("mot_de_passe_client");
            
            
            if (mdp.contentEquals(passe)) {
            	jop.showMessageDialog(null, "Vous êtes bien connecté sous le pseudo "+pseudo, "Récapitulatif adresse", JOptionPane.INFORMATION_MESSAGE);  
        		
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

	}
	
	
	public void soumettre_annonce() {
		String titre = Dialogue.titreAnnonce();
		
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null, "Vous allez devoir rentrer l'adresse précise de votre bien dans un premier temps.","Adresse", JOptionPane.INFORMATION_MESSAGE);
		
		String pays = Dialogue.pays();
		
		String commune = Dialogue.commune();
		
		String postal = Dialogue.code_postal();
		
		String insee = Dialogue.code_INSEE();
		
		String num = Dialogue.numero();
		int numero = Integer.parseInt(num);
		
		String voie = Dialogue.voie();
		
		String environ = Dialogue.typeEnvironnement();
		Environnement environnement = Environnement.parseEnvironnement(environ);
		
		Adresse adresse = new Adresse(0,numero,voie,postal,insee,commune,pays,environnement);
		
		JOptionPane jop1 = new JOptionPane();
		jop1.showMessageDialog(null, adresse.toString(), "Récapitulatif adresse", JOptionPane.INFORMATION_MESSAGE);  
		
		JOptionPane jop2 = new JOptionPane();
		jop2.showMessageDialog(null, "Vous allez maintenant devoir rentrer les caractéristiques du bien en lui-même","Bien", JOptionPane.INFORMATION_MESSAGE);
		
		String typeBien = Dialogue.typeBien();
		
		String precis = Dialogue.typeHabitation(typeBien);
		
		String transaction = Dialogue.typeTransaction();
		
		String nom = Dialogue.nomBien();
		
		String surf = Dialogue.surfaceBien();
		double surface = Double.parseDouble(surf);
		
		String dist = Dialogue.distanceTransports();
		double distancetr = Double.parseDouble(dist);
		
		String dist2 = Dialogue.distanceCommerce();
		double distancecom = Double.parseDouble(dist2);
		
		String dist3 = Dialogue.distanceTransports();
		double distanceeco = Double.parseDouble(dist3);
		
		if (typeBien.equals("Constructible")) {
		String qual = Dialogue.qualiteTerrain();
		int qualite = Integer.parseInt(qual);
		}
		
		if (typeBien.equals("Habitable")) {
			
		String surf2 = Dialogue.surfaceBien();
		double surfaceJardin = Double.parseDouble(surf2);
		
		String nbpi = Dialogue.nb_pieces();
		int nbpieces = Integer.parseInt(nbpi);
		
		String nbba = Dialogue.nb_pieces();
		int nbbains = Integer.parseInt(nbba);
		}	
		
	}
	
	public static void main(String[] args) {
		System.out.println("Bienvenue sur l'espace client de notre agence immobilière");
		Client.creer_compte();
	}

}

