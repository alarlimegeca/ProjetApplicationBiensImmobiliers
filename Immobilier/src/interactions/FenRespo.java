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

import individus.Respo_agence;

	public class FenRespo extends JFrame {
	  private JMenuBar menuBar = new JMenuBar();
	  private JMenu actions = new JMenu("Actions");
	  private Respo_agence respo;

	  private JMenuItem item_ann = new JMenuItem("Valider les annonces");
	  private JMenuItem item_cli = new JMenuItem("Valider les nouveaux clients");
	  private JMenuItem item_rdv = new JMenuItem("Valider les rendez-vous");
	  private JMenuItem item_note = new JMenuItem("Noter un agent immobilier");
	  private JMenuItem item_stat = new JMenuItem("Voir les statistiques");
	  private JMenuItem item_trans = new JMenuItem("Valider les transactions");
	 
	  public static void main(String[] args){
	    
	  }

	  public FenRespo(Respo_agence respo){
	    this.setSize(400, 200);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);

	    //On initialise nos menus      
	    this.actions.add(item_ann);
	    this.actions.add(item_cli);  
	    this.actions.add(item_rdv);
	    this.actions.add(item_stat);
	    this.actions.add(item_note);
	    this.actions.add(item_trans);

	    this.menuBar.add(actions);
	    this.setJMenuBar(menuBar);
	    this.setVisible(true);
	    
	    item_cli.addActionListener(new ActionListener() {
	    	Respo_agence respo = this.respo;
	    	

			public void actionPerformed(ActionEvent event){
	            respo.valider_client();
	        }
	    });
	    
	    item_ann.addActionListener(new ActionListener() {
	    	Respo_agence respo = this.respo;
	    	

			public void actionPerformed(ActionEvent event){
	            respo.valider_annonces();
	        }
	    });
	    
	    item_rdv.addActionListener(new ActionListener() {
	    	Respo_agence respo = this.respo;
	    	

			public void actionPerformed(ActionEvent event){
	         //   respo.fonction_validation_rdv();
	        }
	    });
	    
	    item_trans.addActionListener(new ActionListener() {
	    	Respo_agence respo = this.respo;
	    	

			public void actionPerformed(ActionEvent event){
	            respo.valider_transaction();
	        }
	    });
	    
	    item_note.addActionListener(new ActionListener() {
	    	Respo_agence respo = this.respo;
	    	

			public void actionPerformed(ActionEvent event){
	            respo.noter_agents();
	        }
	    });
	  }
	
}
