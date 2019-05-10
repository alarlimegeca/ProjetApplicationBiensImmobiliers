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

import individus.Client;
import individus.Respo_agence;

public class FenClient extends JFrame {
	
  // ATTRIBUTS
	
  private JMenuBar menuBar = new JMenuBar();
  private JMenu actions = new JMenu("Actions");
  private Client client;

  private JMenuItem item_ann = new JMenuItem("Soumettre une annonce");
  private JMenuItem item_choix = new JMenuItem("Choisir l'acquéreur ou le locataire du bien");

  // ACCESSEUR
  
  public Client getClient(){
  	return client;
  }
  
  // LIENS BOUTONS CODE
  
  public FenClient(Client client){
	this.client = client;
    this.setSize(400, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    
    this.actions.add(item_ann);
    this.actions.add(item_choix);  
  

    this.menuBar.add(actions);
    this.setJMenuBar(menuBar);
    this.setVisible(true);
    
    item_ann.addActionListener(new ActionListener() {
    	Client cli = client;
    	public void actionPerformed(ActionEvent event){
         cli.soumettre_annonce();
        }
    });
    
    item_choix.addActionListener(new ActionListener() {
    	Client cli = client;
    	public void actionPerformed(ActionEvent event){
         cli.choisir_particulier();
        }
    });
  }

}
