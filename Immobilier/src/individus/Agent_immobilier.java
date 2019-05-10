package individus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Agent_immobilier extends Individu{
	
	// ATTRIBUTS
	
	private String pseudo_agent;
	private String mot_de_passe_agent;
	private Respo_agence respo;

	// CONSTRUCTEURS
	
	public Agent_immobilier(int id_individu, String nom, String prenom, String e_mail, String num_tel, String pseudo_agent, String mot_de_passe_agent) {
		super(id_individu, nom, prenom, e_mail, num_tel);
		this.pseudo_agent = pseudo_agent;
		this.mot_de_passe_agent = mot_de_passe_agent;
	}
	
	// ACCESSEURS ET MUTATEURS
	
	public String getPseudoAgent() {
		return pseudo_agent;
	}
	
	public void setPseudoAgent(String pseudo_agent) {
		this.pseudo_agent = pseudo_agent;
	}
	
	public String getPasseAgent() {
		return mot_de_passe_agent;
	}
	
	public void setPasseAgent(String mot_de_passe_agent) {
		this.mot_de_passe_agent = mot_de_passe_agent;
	}
	
	public Respo_agence getRespo() {
		return respo;
	}

	public void setRespo(Respo_agence respo) {
		this.respo = respo;
	}
	
	// AUTRES METHODES
	
	public void voir_statistiques() {
		Connection conn = null;
        try {
        	JOptionPane jop = new JOptionPane();
        	String url = "jdbc:sqlite:bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	String query = "SELECT COUNT(*) FROM latransaction WHERE id_agent = " +this.getId();
        	Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            int presence = result.getInt("COUNT(*)");
            
            jop.showMessageDialog(null, "Vous avez contribué à la réalisation de "+presence+" transaction.", "Connexion", JOptionPane.INFORMATION_MESSAGE);  
    		
            String query2 = "SELECT * FROM notation WHERE id_agent = " +this.getId();
            ResultSet result2 = state.executeQuery(query2);
            String resultats = "";
            while (result2.next()) {
            	int id = result.getInt("id_agent");
            	int note = result.getInt("note");
            	String date = result.getString("date");
            	resultats += "Vous avez reçu la note : "+note +" le "+date+"\n";
            	}
            
            String query3 = "SELECT AVG(note) FROM notation WHERE id_agent = " +this.getId();
            
            ResultSet result3 = state.executeQuery(query3);
            int moyenne = result3.getInt("AVG(note)");
            resultats  += "Votre moyenne est " +moyenne+"\n";
            if (moyenne<10) {
                resultats +="Nous vous proposons ainsi une prime de 0€";               
            }
            else if (moyenne>=10 && moyenne<15 ) {
                resultats += "Nous vous proposons ainsi une prime de 100€";               

            }
            else {
                resultats +="Nous vous proposons ainsi une prime de 200€";               
            }
            
            jop.showMessageDialog(null, resultats, "Connexion", JOptionPane.INFORMATION_MESSAGE);  
     		
           

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
	
	public static Agent_immobilier se_connecter_agent() {
	    String pseudo = Dialogue.pseudo("agent_immobilier");
		String passe = Dialogue.mot_de_passe("agent_immobilier");
		Connection conn = null;
        try {
        	JOptionPane jop = new JOptionPane();
        	String url = "jdbc:sqlite:bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	// Requête SQL
        	String query = "SELECT * FROM agent_immobilier WHERE pseudo_agent LIKE '" + pseudo+"'";
        	java.sql.Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            String mdp = result.getString("mot_de_passe_agent");
            int id = result.getInt("id_individu");
            
            if (mdp.contentEquals(passe)) {
            	jop.showMessageDialog(null, "Vous êtes connecté sous le pseudo " +pseudo, "Connexion", JOptionPane.INFORMATION_MESSAGE);  
            	Agent_immobilier agent = (Agent_immobilier) BDD.construire_ind(id,"agent_immobilier");
                return agent;
            }
            else {
            	jop.showMessageDialog(null, "Mot de passe incorrect", "Connexion", JOptionPane.INFORMATION_MESSAGE);  
    		;}

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

}