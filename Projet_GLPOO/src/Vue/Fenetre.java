	package Vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Modele.Worker;

// Attention Taille du tableau ˆ donner

public class Fenetre extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleurFenetrePrincipale controlleur;
	private JMenuItem itemQuitter, itemSave, itemLoad;
	private JMenuBar menuBar;
	private JPanel panelPrincipal;
	private PanelTerrainJeu panelTerrainJeu;
	private PanelControl control;
	private Worker worker;
	private String pseudo;
	

	
	public Fenetre(){
		//Param Fenetre
		 super("Projet Java Adrien");
		 setSize(1000, 670);
		 setResizable(false);
	     setLocationRelativeTo(null);
	     setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	     //Creation composant fenetre
	     worker = new Worker();
	     controlleur= new ControleurFenetrePrincipale();
	     buildMenu();
	     buildFenetre();
	     setJMenuBar(menuBar);
	     setContentPane(panelPrincipal);
	     setVisible(true);
	     pseudo = getPseudo();
	     JOptionPane.showMessageDialog(this.getParent(), "Bienvenue "+ pseudo +", veuillez charger le puzzle!");
	}
	
	public void buildFenetre(){
		buildPanelControl();
		buildPanelTerrainJeu();
		panelPrincipal= new JPanel();
		panelPrincipal.setBounds(0, 0, 900, 600);
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.add(control, BorderLayout.EAST);
		panelPrincipal.add(panelTerrainJeu, BorderLayout.WEST);
	}
	
	 private void buildMenu() {
		 menuBar = new JMenuBar();
	     JMenu menuFichier = new JMenu("Fichier");
	     itemQuitter = new JMenuItem("Quitter");
	     itemLoad= new JMenuItem("Charger Puzzle");
	     itemSave = new JMenuItem("Sauvegarder");
	     itemQuitter.addActionListener(controlleur);
	     itemLoad.addActionListener(controlleur);
	     itemSave.addActionListener(controlleur);
	     menuFichier.add(itemLoad);
	     menuFichier.add(itemSave);
	     menuFichier.addSeparator();
	     menuFichier.add(itemQuitter);
	     menuBar.add(menuFichier);
	  }
	 
	 public void buildPanelControl(){
		 control= new PanelControl(worker, this);
	 }
	 
	 public void buildPanelTerrainJeu(){
		 panelTerrainJeu= new PanelTerrainJeu(control);
	 }
	 
	 public void setTerrainJeu(int i, int j, PanelControl control){
		 panelPrincipal.remove(panelTerrainJeu);
		 panelTerrainJeu= new PanelTerrainJeu(i, j, control);
		 panelPrincipal.add(panelTerrainJeu);
		 reDrawPanel();
	 }
	 
	 public void reDrawPanel(){
		 this.remove(this.getContentPane());
		 control.setEmplacementPiece();
		 this.setContentPane(panelPrincipal);
		 this.repaint();
		 this.validate();
	 }
	 
		public String getPseudo() {
	        return (String)javax.swing.JOptionPane.showInputDialog(this.getParent(), "Choisissez-vous un pseudo:", 
	        		"Votre Pseudo", javax.swing.JOptionPane.PLAIN_MESSAGE);
	    }
	 
	 public class ControleurFenetrePrincipale implements ActionListener {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Object source = e.getSource();

	            if (source == itemQuitter) {
	               dispose();
	            } 
	            
	            if(source == itemLoad){
	            	worker.loadFile();
	            	reDrawPanel();
	            	control.getChrono().startChrono();
	            	System.out.println(worker.toString());
	            }
	        }

	    }
	
}
