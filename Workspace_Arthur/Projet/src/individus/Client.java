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
	
	public static void creer_compte() {
		
		System.out.println("id_individu");
		int id_individu = scan.nextInt();
		scan.nextLine();
		
		System.out.println("nom");
		String nom = scan.nextLine();
		
		System.out.println("prénom");
		String prenom = scan.nextLine();
		
		System.out.println("adresse e_mail");
		String e_mail = scan.nextLine();
		
		System.out.println("numéro de téléphone");
		String num_tel = scan.nextLine();
		
		System.out.println("pseudo");
		String pseudo = scan.nextLine();
		while (BDDInd.est_dans_BDD("client", "pseudo_client", pseudo)) {
			System.out.println("pseudo déjà pris");
			System.out.println("pseudo");
			pseudo = scan.nextLine();
		};
		
        System.out.println("mot de passe");
		String mdp = scan.nextLine();
		System.out.println("confirmer mot de passe");
		String cmdp = scan.nextLine();
		while (!(mdp.contentEquals(cmdp))) {
			System.out.println("Les deux mots de passe doivent correspondre");
			System.out.println("mot de passe");
			mdp = scan.nextLine();
			System.out.println("confirmer mot de passe");
			cmdp = scan.nextLine();
		}
		
		BDDInd.ajouterClient(id_individu, nom, prenom, e_mail, num_tel, pseudo, mdp);
	}
	
	public static void se_connecter_client() {
		System.out.println("Pseudo_client ?");
		String pseudo = scan.nextLine();
		System.out.println("Mot_de_passe_client ?");
		String passe = scan.nextLine();
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:/media/formation/CLEF MENGIN/Projet info/BDD/Individus.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	// Requête SQL
        	String query = "SELECT * FROM client WHERE pseudo_client LIKE '" + pseudo+"'";
        	Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            String mdp = result.getString("mot_de_passe_client");
            
            
            if (mdp.contentEquals(passe)) {
            	System.out.println("Vous êtes connecté");
            }
            else { System.out.println("Mot de passe incorrect");}

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
		
		String typeBien = Dialogue.typeBien();
		
		String precis = Dialogue.typeHabitation(typeBien);
		
		String environnement = Dialogue.typeEnvironnement();
		
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
	
	public static void main(String[] args) {
		System.out.println("Bienvenue sur l'espace client de notre agence immobilière");
		Client.creer_compte();
	}

}

