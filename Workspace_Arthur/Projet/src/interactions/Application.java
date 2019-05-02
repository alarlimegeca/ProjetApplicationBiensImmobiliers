package interactions;




import individu.Agent_immobilier;
import individu.Client;
import individu.Respo_agence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Application extends JFrame {
  private JMenuBar menuBar = new JMenuBar();
  private JMenu espace = new JMenu("Espaces");
  private JMenu sous_parti = new JMenu("Particulier");
  private JMenu sous_respo = new JMenu("Responsable d'agence");
  private JMenu sous_client = new JMenu("Client");
  private JMenu sous_agent = new JMenu("Agent immobilier");
  

  private JMenuItem voir_parti = new JMenuItem("Accéder à l'espace public");
  private JMenuItem conn_respo = new JMenuItem("Se connecter");
  private JMenuItem conn_client = new JMenuItem("Se connecter");
  private JMenuItem conn_agent = new JMenuItem("Se connecter");
  private JMenuItem creer_client = new JMenuItem("Créer un nouveau compte");

  public static void main(String[] args){
    Application appli = new Application();
  }

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
    
    conn_respo.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            Respo_agence.se_connecter_respo();
        }
    });
    
    conn_agent.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            Agent_immobilier.se_connecter_agent();
        }
    });
    
    conn_client.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            Client.se_connecter_client();
        }
    });
    
    creer_client.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent event){
            Client.creer_compte();
        }
    });
  }
}
