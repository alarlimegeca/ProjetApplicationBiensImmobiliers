package individus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import interactions.Connexion;

public class Respo_agence extends Agent_immobilier {
	
	// ATTRIBUTS
	
	private String pseudo_respo;
	private String mot_de_passe_respo;
	private ArrayList<Annonce> receptionA;
	private ArrayList<Client> receptionC;
	private ArrayList<Client> lclient;
	private ArrayList<Agent_immobilier> lagent;
	
	// CONSTRUCTEURS

	public Respo_agence(int id_individu, String nom, String prenom, String e_mail, int num_tel, String pseudo_agent,
			String mot_de_passe, String pseudo_respo, String mot_de_passe_respo) {
		super(id_individu, nom, prenom, e_mail, num_tel, pseudo_agent, mot_de_passe);
		this.pseudo_respo = pseudo_respo;
		this.mot_de_passe_respo = mot_de_passe_respo;
		this.lagent = new ArrayList<Agent_immobilier> ();
		this.lclient = new ArrayList<Client>();
		this.receptionA = new ArrayList<Annonce>();
		this.receptionC = new ArrayList<Client>();
	}
	
	// ACCESSEURS ET MUTATEURS
	
	public String getPseudoRespo() {
		return pseudo_respo;
	}
	
	public void setPseudoRespo(String pseudo_respo) {
		this.pseudo_respo = pseudo_respo;
	}
	
	public String getPasseRespo() {
		return mot_de_passe_respo;
	}
	
	public void setPasseRespo(String mot_de_passe_respo) {
		this.mot_de_passe_respo = mot_de_passe_respo;
	}
	
	public ArrayList<Client> getLclient() {
	return lclient;
		 }
		
	 public void setLclient(ArrayList<Client> lclient) {
	 this.lclient = lclient;
	 }
	 
	 public ArrayList<Agent_immobilier> getLagent() {
	 return lagent;
	 }
	
	 public void setLagent(ArrayList<Agent_immobilier> lagent) {
	 this.lagent = lagent;
	 }
	 
	public void lienResAgent(Agent_immobilier agent){
		lagent.add(agent);
		agent.setRespo(this);
	}
	
	public void lienResCli(Client client){
		lclient.add(client);
		client.setRespo(this);
	}
	
	public void lienResRecClient(Client client){
		receptionC.add(client);
		client.setRespo(this);
	}
	
	// AUTRES METHODES
	
	public static int voirStatAgent(int id_agent) {
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:/home/formation/Bureau/bdd/bddprojet";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);

        	
        	// Requête SQL
        	String query = "SELECT * FROM notation WHERE id_agent='"+id_agent+"'";
       

        	Statement state = Connexion.getinstance().createStatement();

            ResultSet result = state.executeQuery(query);
            double somme_note=0;
            double nbr_note=0;
            while (result.next()) {
            	int id = result.getInt("id_agent");
            	int note = result.getInt("note");
            	String date = result.getString("date");
            	somme_note+=note;
            	nbr_note+=1;
            	System.out.println("L'agent "+id+" a reçu la note : "+note +" le "+date);
            }
            double moy_note=somme_note/nbr_note;
            System.out.println("La note moyenne de cet agent est de : "+moy_note);
            if (moy_note<10) {
                System.out.println("Nous vous proposons une prime de 0€");               
            }
            else if (moy_note>=10 && moy_note<15 ) {
                System.out.println("Nous vous proposons une prime de 100€");               

            }
            else {
                System.out.println("Nous vous proposons une prime de 200€");               
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
	
	public static void se_connecter_respo() {
		System.out.println("Pseudo_respo ?");
		String pseudo = scan.nextLine();
		System.out.println("Mot_de_passe_respo ?");
		String passe = scan.nextLine();
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:/media/formation/CLEF MENGIN/Projet info/BDD/Individus.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	// Requête SQL
        	String query = "SELECT * FROM respo_agence WHERE pseudo_respo LIKE '" + pseudo+"'";
        	Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            String mdp = result.getString("mot_de_passe_respo");
            
            
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

	public static void main(String[] args) {
		
		voirStatAgent(1);
	}

}
