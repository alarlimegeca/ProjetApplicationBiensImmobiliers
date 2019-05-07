package individus;

import interactions.Annonce;
import interactions.B;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Respo_agence extends Agent_immobilier {
	
	// ATTRIBUTS
	
	private String pseudo_respo;
	private String mot_de_passe_respo;
	private ArrayList<Annonce> receptionA;
	private ArrayList<Client> receptionC;
	private ArrayList<Client> lclient;
	private ArrayList<Agent_immobilier> lagent;
	private Fenetre fen;
	
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
	
	public Fenetre getFen() {
		return fen;
	}

	public void setFen(Fenetre fen) {
		this.fen = fen;
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
	
	public static void valider_client(){
		ArrayList<Client> receptionC = new ArrayList<>();
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);

        	
        	// Requête SQL
        	String query = "SELECT * FROM reception_candidature_client";
       

        	Statement state = Connexion.getinstance().createStatement();

            ResultSet result = state.executeQuery(query);
            while (result.next()) {
            	int id = result.getInt("id_individu");
            	Client client = (Client) BDDInd.construire_ind(id,"reception_candidature_client");
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
				nouveau.setId(BDDInd.trouver_id(nouveau.getNom(), nouveau.getPrenom()));
				BDDInd.ajouterClient("client",nouveau.getId(), nouveau.getNom(), nouveau.getPrenom(), nouveau.getEmail(), nouveau.getNum(), nouveau.getPseudoClient(), nouveau.getPasse());
			}
			
		}
	}
	
	public static int voirStatAgent(int id_agent) {
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);

        	
        	// Requ�te SQL
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
            	System.out.println("L'agent "+id+" a re�u la note : "+note +" le "+date);
            }
            double moy_note=somme_note/nbr_note;
            System.out.println("La note moyenne de cet agent est de : "+moy_note);
            if (moy_note<10) {
                System.out.println("Nous vous proposons une prime de 0�");               
            }
            else if (moy_note>=10 && moy_note<15 ) {
                System.out.println("Nous vous proposons une prime de 100�");               

            }
            else {
                System.out.println("Nous vous proposons une prime de 200�");               
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
	
	public static Respo_agence se_connecter_respo() {
		String pseudo = Dialogue.pseudo("responsable d'agence");
		String passe = Dialogue.mot_de_passe("responsable d'agence");
		Connection conn = null;
        try {
        	JOptionPane jop = new JOptionPane();
        	String url = "jdbc:F:\\Projet info\\BDD\\bdd.db";
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
            	jop.showMessageDialog(null, "Vous �tes connect� sous le pseudo " +pseudo, "Connexion", JOptionPane.INFORMATION_MESSAGE);  
            	Respo_agence respo = (Respo_agence) BDDInd.construire_ind(id,"responsable");
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
	
	public ArrayList<String> dispo_agent() {
		ArrayList<Integer> compte_annonce = BDDInd.compte_annonces(this.lagent);
		ArrayList<String> l_agents = new ArrayList();
		for (int i = 0;i<this.lagent.size();i++) {
			String str = "" + this.lagent.get(i).getNom() + " " + this.lagent.get(i).getPrenom() + " " + compte_annonce.get(i);
			l_agents.add(str);
		}
		return l_agents;
	}
	
	public static void valider_annonces() {
		
		ArrayList<Annonce> receptionA = new ArrayList<>();
		Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:F:\\Projet info\\BDD\\bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);

        	
        	// Requête SQL
        	String query = "SELECT * FROM reception_annonce";
       

        	Statement state = Connexion.getinstance().createStatement();

            ResultSet result = state.executeQuery(query);
            while (result.next()) {
            	int id = result.getInt("id_annonce");
            	Annonce annonce = (Annonce) BDDInd.construire_annonce(id,"reception_annonce");
            	receptionA.add(annonce);
        	    
            }
            System.out.println(receptionA.get(0).affichage().get(1));
            //String query2 = "DELETE FROM reception_candidature_client";
            //state.executeQuery(query2);


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
			jop.showMessageDialog(null, (receptionA.get(i).affichage()).get(1), "Caract�ristiques du bien", JOptionPane.INFORMATION_MESSAGE);  
			jop.showMessageDialog(null, (receptionA.get(i).affichage()).get(2), "Client", JOptionPane.INFORMATION_MESSAGE);  
			int choix = Dialogue.confirmation("Souhaitez-vous mettre en ligne cette annonce ?");
			if (choix == 0){
				Annonce ancienne = receptionA.get(i);
				Annonce nouvelle = new Annonce(ancienne.getId_annonce(),ancienne.getTitre(), true,ancienne.getTransaction(),ancienne.getPrix(), ancienne.getLebien(), ancienne.getAgent(),ancienne.getClient(), ancienne.getHabitation() );
				nouvelle.ajouterAnnonce("annonce");
				}
		
		}
	}

	public static void main(String[] args) {
		
		valider_annonces();
		//voirStatAgent(1);
	}
