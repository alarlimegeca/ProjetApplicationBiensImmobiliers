package interactions;




import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import individus.Agent_immobilier;
import individus.Client;
import individus.Particulier;
import individus.Respo_agence;

public class Application extends JFrame {
	
  // ATTRIBUTS	
  private JMenuBar menuBar = new JMenuBar();
  private JMenu espace = new JMenu("Espaces");
  private JMenu sous_parti = new JMenu("Particulier");
  private JMenu sous_respo = new JMenu("Responsable d'agence");
  private JMenu sous_client = new JMenu("Client");
  private JMenu sous_agent = new JMenu("Agent immobilier");
  private JLabel label = new JLabel("Bienvenue sur l'application de l'agence immobilière 'Alarli'");


  private JMenuItem voir_parti = new JMenuItem("Rechercher des biens / Prendre rendez-vous / Acquérir bien ");
  private JMenuItem conn_respo = new JMenuItem("Se connecter");
  private JMenuItem conn_client = new JMenuItem("Se connecter");
  private JMenuItem conn_agent = new JMenuItem("Se connecter");
  private JMenuItem creer_client = new JMenuItem("Créer un nouveau compte");

  // ENTREE DU PROGRAMME
  public static void main(String[] args){
    Application appli = new Application();
  }

  // CONSTRUCTEUR
  public Application(){
    this.setSize(400, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    this.sous_agent.add(conn_agent);
    this.espace.add(this.sous_agent);
    
    this.sous_respo.add(conn_respo);
    this.espace.add(this.sous_respo);
    
    this.sous_client.add(conn_client);
    this.sous_client.add(creer_client);
    this.espace.add(this.sous_client);
    
    this.sous_parti.add(voir_parti);
    this.espace.add(this.sous_parti);

    this.menuBar.add(espace);
    this.setJMenuBar(menuBar);
    this.setVisible(true);
    
    // LIAISONS BOUTONS CODE
    
    conn_respo.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            Respo_agence respo = Respo_agence.se_connecter_respo();
            if (!respo.equals(null)){
            	FenRespo fen = new FenRespo(respo);
            }
        }
    });
    
    voir_parti.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            try {
				Particulier.fonction_particulier();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    });
    
    conn_agent.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            Agent_immobilier agent = Agent_immobilier.se_connecter_agent();
            if (!agent.equals(null)){
            	FenAgent fen = new FenAgent(agent);
            }
        }
    });
    
    conn_client.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            Client client = Client.se_connecter_client();
            if (!client.equals(null)){
            	FenClient fen = new FenClient(client);
            }
        }
    });
    
    creer_client.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            Client.creer_compte();
        }
    });
  }
}
