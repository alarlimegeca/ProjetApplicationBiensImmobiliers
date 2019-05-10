package interactions;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import individus.Agent_immobilier;
import individus.Connexion;

public class Notation {
	
	// ATTRIBUTS
	private Agent_immobilier agent;
	private double note;
	private String date;
	
	// CONSTRUCTEURS
	
	public Notation(Agent_immobilier agent, double note, String date) {
		this.agent =agent;
		this.note = note;
		this.date = date;
	}
	
	/**
	 * ajoute une note dans la base de données
	 */
	
	public void ajouterNote() {
			try {
				PreparedStatement preparedState = Connexion.getinstance().prepareStatement("INSERT INTO notation(id_agent,note,date) VALUES (?,?,?)");
				preparedState.setInt(1, agent.getId()); 
				preparedState.setDouble(2, note); 
				preparedState.setString(3, date); 
				
				System.out.println(preparedState.toString());

				preparedState.executeUpdate();

				preparedState.close();
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
	}

	  
}

