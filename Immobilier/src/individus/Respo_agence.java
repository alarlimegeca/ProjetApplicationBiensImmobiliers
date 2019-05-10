package individus;

import interactions.Annonce;
import interactions.Creneau;
import interactions.Notation;
import interactions.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;


public class Respo_agence extends Agent_immobilier {
	
	// ATTRIBUTS
	
	private String pseudo_respo;
	private String mot_de_passe_respo;
	private ArrayList<Annonce> receptionA;
	private ArrayList<Client> receptionC;
	private ArrayList<Client> lclient;
	private ArrayList<Agent_immobilier> lagent;
	
	// CONSTRUCTEURS

	public Respo_agence(int id_individu, String nom, String prenom, String e_mail, String num_tel, String pseudo_agent,
			String mot_de_passe_agent, String pseudo_respo, String mot_de_passe_respo) {
		super(id_individu, nom, prenom, e_mail, num_tel, pseudo_agent, mot_de_passe_agent);
		this.pseudo_respo = pseudo_respo;
		this.mot_de_passe_respo = mot_de_passe_respo;
		this.lagent = new ArrayList<Agent_immobilier> ();
		this.lclient = new ArrayList<Client>();
		this.setReceptionA(new ArrayList<Annonce>());
		this.setReceptionC(new ArrayList<Client>());
	}
	
	// ACCESSEURS ET MUTATEURS
	
	public String getPseudoRespo() {
		return pseudo_respo;
	}
	
	public void setPseudoRespo(String pseudo_respo) {
		this.pseudo_respo = pseudo_respo;
	}
	
	public ArrayList<Annonce> getReception() {
		return getReceptionA();
	}
	
	public void setReception(ArrayList<Annonce> reception) {
		this.setReceptionA(reception);
	}
	
	public String getPasseRespo() {
		return mot_de_passe_respo;
	}
	
	public void setPasseRespo(String mot_de_passe_respo) {
		this.mot_de_passe_respo = mot_de_passe_respo;
	}
	
	public ArrayList<Agent_immobilier> getLAgent() {
		return lagent;
	}
	
	
	public ArrayList<Client> getLClient() {
		 return lclient;
		 }
		
	 public void setLClient(ArrayList<Client> lclient) {
	 this.lclient = lclient;
	 }
	 
	 public ArrayList<Agent_immobilier> getLagent() {
	 return lagent;
	 }
	
	 public void setLagent(ArrayList<Agent_immobilier> lagent) {
	 this.lagent = lagent;
	 }
	
    public ArrayList<Client> getReceptionC() {
		return receptionC;
	}

	public void setReceptionC(ArrayList<Client> receptionC) {
		this.receptionC = receptionC;
	}
	
	public ArrayList<Annonce> getReceptionA() {
		return receptionA;
	}

	public void setReceptionA(ArrayList<Annonce> receptionA) {
		this.receptionA = receptionA;
	}
	
	// LIENS INTERCLASSES
	
	public void lienResAgent(Agent_immobilier agent){
		lagent.add(agent);
		agent.setRespo(this);
	}
	
	public void lienResCli(Client client){
		lclient.add(client);
		client.setRespo(this);
	}
	
	public void lienResRecClient(Client client){
		getReceptionC().add(client);
		client.setRespo(this);
	}
	

	
	// AUTRES METHODES
	
	/**
	 * le responsable peut valider ou non les candidatures des individus souhaitant devenir clients
	 */
	
	public static void valider_client(){
		ArrayList<Client> receptionC = new ArrayList<>();
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);

        	
        	// Requête SQL
        	String query = "SELECT * FROM reception_candidature_client";
       

        	Statement state = Connexion.getinstance().createStatement();

            ResultSet result = state.executeQuery(query);
            while (result.next()) {
            	int id = result.getInt("id_individu");
            	Client client = (Client) BDD.construire_ind(id,"reception_candidature_client");
            	receptionC.add(client);
        	   
            }
            String query2 = "DELETE FROM reception_candidature_client";
            state.executeQuery(query2);


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
    	JOptionPane jop = new JOptionPane();
    	jop.showMessageDialog(null, "Vous avez "+receptionC.size()+" nouvelles candidatures.", "Validation", JOptionPane.INFORMATION_MESSAGE);  
		for (int i = 0;i<receptionC.size();i++) {
			jop.showMessageDialog(null, receptionC.get(i).toString("Client"), "Validation", JOptionPane.INFORMATION_MESSAGE);  
			int choix = Dialogue.confirmation("Souhaitez-vous accepter cette candidature ?");
			if (choix == 0){
				Client ancien = receptionC.get(i);
				Client nouveau = new Client(ancien.getId(),ancien.getNom(),ancien.getPrenom(),ancien.getEmail(),ancien.getNum(),ancien.getPseudoClient(), ancien.getPasse());
				nouveau.setId(BDD.trouver_id(nouveau.getNom(), nouveau.getPrenom()));
				BDD.ajouterClient("client",nouveau.getId(), nouveau.getNom(), nouveau.getPrenom(), nouveau.getEmail(), nouveau.getNum(), nouveau.getPseudoClient(), nouveau.getPasse());
			}
			
		}
	}
	
	/**
	 * Le responsable peut noter ses agents en fonction de leurs résultats
	 */
	
	public static void noter_agents() {
		ArrayList<String> l_agents = new ArrayList();
		Connection conn = null;
		try {
		    	String url = "jdbc:sqlite:bdd.db";
		    	Class.forName("org.sqlite.JDBC");
		    	conn = DriverManager.getConnection(url);
		    	// Requête SQL
		    	String query = "SELECT * FROM agent_immobilier";
		    	Statement state = Connexion.getinstance().createStatement();
		    	ResultSet result = state.executeQuery(query);
		    
		    	while (result.next()) {
	            	int id_agent = result.getInt("id_individu");
	            	Agent_immobilier agent = (Agent_immobilier) BDD.construire_ind(id_agent, "agent_immobilier");
	            	l_agents.add(id_agent+" "+agent.getPrenom() +" "+ agent.getNom());
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
		    String[] agents = l_agents.toArray(new String[0]);
		    
		    String choix = Dialogue.choisir_agent(agents);
		    String separation[]  = choix.split(" ");
		    List<String> list =  Arrays.asList(separation);
		    
		    int id_agent = Integer.parseInt(list.get(0));
		    Agent_immobilier agent = (Agent_immobilier) BDD.construire_ind(id_agent, "agent_immobilier");
		    int perf = BDD.compte_transactions(agent);
		    JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "L'agent "+agent.getPrenom()+ " "+ agent.getNom()+" a contribué à la finalisation de "+perf+" transactions.","Adresse", JOptionPane.INFORMATION_MESSAGE);
			
		    String note = Dialogue.notation(agent.getPrenom()+" "+agent.getNom());
		    double note_double = Double.parseDouble(note);
		    String date = Dialogue.date();
		    Notation notation = new Notation(agent,note_double,date);
		    notation.ajouterNote();
		    
		
	}
	
	/**
	 * permet au responsable d'accéder à son espace personnel après s'être correctement connecté
	 * @return le responsable qui vient de se connecter
	 */
	
	
	public static Respo_agence se_connecter_respo() {
		String pseudo = Dialogue.pseudo("responsable d'agence");
		String passe = Dialogue.mot_de_passe("responsable d'agence");
		Connection conn = null;
        try {
        	JOptionPane jop = new JOptionPane();
        	String url = "jdbc:sqlite:bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	// Requ�te SQL
        	String query = "SELECT * FROM respo_agence WHERE pseudo_respo LIKE '" + pseudo+"'";
        	Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            String mdp = result.getString("mot_de_passe_respo");
            int id = result.getInt("id_individu");
            
            if (mdp.contentEquals(passe)) {
            	jop.showMessageDialog(null, "Vous êtes connecté sous le pseudo " +pseudo, "Connexion", JOptionPane.INFORMATION_MESSAGE);  
            	Respo_agence respo = (Respo_agence) BDD.construire_ind(id,"respo_agence");
           		return respo;
            }
            else { 
            	jop.showMessageDialog(null, "Mot de passe incorrect", "Connexion", JOptionPane.INFORMATION_MESSAGE);  
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
	 * permet au responsable de valider l'annonce et d'y associer un agent s'il choisit de la valider
	 */
	
	public static void valider_annonces() {
		
		ArrayList<Annonce> receptionA = new ArrayList<>();
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);

        	
        	// Requête SQL
        	String query = "SELECT * FROM reception_annonce";
       

        	Statement state = Connexion.getinstance().createStatement();

            ResultSet result = state.executeQuery(query);
            while (result.next()) {
            	int id = result.getInt("id_annonce");
            	Annonce annonce = (Annonce) BDD.construire_annonce(id,"reception_annonce");
            	receptionA.add(annonce);
        	    System.out.println(annonce.affichage());
            }
  

            String query2 = "DELETE FROM reception_annonce";
            state.executeQuery(query2);


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
        
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "Vous avez "+receptionA.size()+" nouvelles candidatures.", "Validation", JOptionPane.INFORMATION_MESSAGE);  
		for (int i = 0;i<receptionA.size();i++) {
			jop.showMessageDialog(null, (receptionA.get(i).affichage()).get(0), "Adresse", JOptionPane.INFORMATION_MESSAGE);  
			jop.showMessageDialog(null, (receptionA.get(i).affichage()).get(1), "Caractéristiques du bien", JOptionPane.INFORMATION_MESSAGE);  
			jop.showMessageDialog(null, (receptionA.get(i).affichage()).get(2), "Client", JOptionPane.INFORMATION_MESSAGE); 
			 
			ArrayList<String> l_agents = new ArrayList();
			try {
			    	String url = "jdbc:sqlite:bdd.db";
			    	Class.forName("org.sqlite.JDBC");
			    	conn = DriverManager.getConnection(url);
			    	// Requête SQL
			    	String query = "SELECT * FROM agent_immobilier";
			    	Statement state = Connexion.getinstance().createStatement();
			    	ResultSet result = state.executeQuery(query);
			    
			    	while (result.next()) {
		            	int id_agent = result.getInt("id_individu");
		            	System.out.println(id_agent);
		            	Agent_immobilier agent = (Agent_immobilier) BDD.construire_ind(id_agent, "agent_immobilier");
		            	int presence = BDD.compte_annonces(agent);
		            	l_agents.add(id_agent+" "+agent.getPrenom() +" "+ agent.getNom() +"\n"+"Cet agent est déjà présent sur "+ presence+" annonces");
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
			
			    // Choix agent immobilier
			
			    String[] agents = l_agents.toArray(new String[0]);
			    
			    String choix = Dialogue.choisir_agent(agents);
			    String separation[]  = choix.split(" ");
			    List<String> list =  Arrays.asList(separation);
			    
			    int id_agent = Integer.parseInt(list.get(0));
			    Agent_immobilier agent = (Agent_immobilier) BDD.construire_ind(id_agent, "agent_immobilier");
			    
			// Le responsble décide de mettre en ligne l'annonce ou non
			
			int decision = Dialogue.confirmation("Souhaitez-vous mettre en ligne cette annonce ?");
			if (decision == 0){
				Annonce ancienne = receptionA.get(i);
				Annonce nouvelle = new Annonce(ancienne.getId_annonce(),ancienne.getTitre(), true,ancienne.getTransaction(),ancienne.getPrix(), ancienne.getLebien(), agent,ancienne.getClient(), ancienne.getHabitation() );
				nouvelle.ajouterAnnonce("annonce");
				}
		
		}
	}
	
	/**
	 * permet au responsable de valider une transaction après que le client et le particulier se sont mis d'accord
	 */
	
public static void valider_transaction() {
	ArrayList<Transaction> receptionT = new ArrayList<>();
	ArrayList<Annonce> annonces = new ArrayList<>();
	Connection conn = null;
    try {
    	// db parameters
    	String url = "jdbc:sqlite:bdd.db";
    	//create a connection to the database
    	Class.forName("org.sqlite.JDBC");
    	conn = DriverManager.getConnection(url);

    	
    	// Requête SQL
    	String query = "SELECT * FROM reception_respo_transaction";
   

    	Statement state = Connexion.getinstance().createStatement();

        ResultSet result = state.executeQuery(query);
        while (result.next()) {
        	int id_annonce = result.getInt("id_annonce");
        	int id_particulier = result.getInt("id_particulier");
        	Particulier particulier = (Particulier) BDD.construire_ind(id_particulier,"particulier");
        	Annonce annonce = BDD.construire_annonce(id_annonce, "annonce");
        	int id_transaction = BDD.trouver_id_transaction();
        	Transaction transaction = new Transaction("",annonce.getTransaction(),id_transaction,annonce.getPrix(),annonce.getClient(),particulier,annonce.getAgent(),annonce.getLebien());
        	System.out.println(transaction.getPrix());
        	receptionT.add(transaction);
        	annonces.add(annonce);
    	   
        }
        String query2 = "DELETE FROM reception_respo_transaction";
        state.executeQuery(query2);


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
	JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null, "Vous avez "+receptionT.size()+" transactions à valider.", "Validation", JOptionPane.INFORMATION_MESSAGE);  
	for (int i = 0;i<receptionT.size();i++) {
		jop.showMessageDialog(null, receptionT.get(i).toString(), "Validation", JOptionPane.INFORMATION_MESSAGE);  
		int choix = Dialogue.confirmation("Souhaitez-vous accepter cette transaction ?");
		if (choix == 0){
			String date = Dialogue.dateAjd();
			Transaction ancien = receptionT.get(i);
			Transaction nouveau = new Transaction(date,ancien.getType_transaction(),ancien.getId_transaction(),ancien.getPrix(),ancien.getLeclient(),ancien.getLeparticulier(), ancien.getLagent(),ancien.getLebien());
			nouveau.ajouterTransaction();
			
			BDD.suppr_annonce(annonces.get(i));
		}
		
	}
}

	/**
	 * permet au responsable de valider les rendez-vous 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static void fonction_validation_rdv() throws ClassNotFoundException, SQLException{
		Creneau.demandeRdv();   
		Dialogue.pas_de_demande();
	
	}
}