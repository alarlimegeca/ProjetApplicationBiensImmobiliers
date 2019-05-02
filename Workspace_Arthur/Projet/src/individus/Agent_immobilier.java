package individus;

public class Agent_immobilier extends Individu{
	
	// ATTRIBUTS
	
	private String pseudo_agent;
	private String mot_de_passe_agent;
	private Respo_agence respo;

	// CONSTRUCTEURS
	
	public Agent_immobilier(int id_individu, String nom, String prenom, String e_mail, int num_tel, String pseudo_agent, String mot_de_passe_agent) {
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
	
	static Scanner scan = new Scanner(System.in);
	
	public static void se_connecter_agent() {
	    String pseudo = Dialogue.pseudo("agent immobilier");
		String passe = Dialogue.mot_de_passe("agent immobilier");
		Connection conn = null;
        try {
        	JOptionPane jop = new JOptionPane();
        	String url = "jdbc:sqlite:/media/formation/CLEF MENGIN/Projet info/BDD/bdd.db";
        	//create a connection to the database
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(url);
        	// Requête SQL
        	String query = "SELECT * FROM agent_immobilier WHERE pseudo_agent LIKE '" + pseudo+"'";
        	java.sql.Statement state = Connexion.getinstance().createStatement();
        	ResultSet result = state.executeQuery(query);
            
            String mdp = result.getString("mot_de_passe_agent");
            
            
            if (mdp.contentEquals(passe)) {
            	jop.showMessageDialog(null, "Vous êtes connecté sous le pseudo " +pseudo, "Connexion", JOptionPane.INFORMATION_MESSAGE);  
        		
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

	}

}

