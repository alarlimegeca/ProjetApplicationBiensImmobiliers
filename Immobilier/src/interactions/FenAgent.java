package interactions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import individus.Agent_immobilier;
import individus.Client;
import individus.Respo_agence;

public class FenAgent extends JFrame {
  private JMenuBar menuBar = new JMenuBar();
  private JMenu actions = new JMenu("Actions");
  private Agent_immobilier agent;

  private JMenuItem item_res = new JMenuItem("Voir les résultats");
  private JMenuItem item_creneau = new JMenuItem("Ajouter un créneau");

  // CONSTRUCTEUR
  
  public FenAgent(Agent_immobilier agent){
	this.agent = agent;
    this.setSize(400, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    //On initialise nos menus      
    this.actions.add(item_res);  
    this.actions.add(item_creneau);
   
    this.menuBar.add(actions);
    this.setJMenuBar(menuBar);
    this.setVisible(true);
    
    // LIENS BOUTONS CODE
    
    item_res.addActionListener(new ActionListener() {
    	Agent_immobilier age = agent;

		public void actionPerformed(ActionEvent event){
			age.voir_statistiques();
        }
    });
    
    item_creneau.addActionListener(new ActionListener() {
    	Agent_immobilier age = agent;

		public void actionPerformed(ActionEvent event){
			age.ajouterCreneau();;
        }
    });
  }

}