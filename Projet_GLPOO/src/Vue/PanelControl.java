package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Piece;
import Modele.Worker;

public class PanelControl extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints gbc =new GridBagConstraints();
	private JLabel titre;
	private Font font = new Font("Arial", Font.BOLD, 40);
	private JButton buttonRotate, buttonNext;
	private LabelEmplacementPiece emplacementPiece;
	private int passage, angle, numPiece;
	private Worker worker;
	private Fenetre parentFenetre;
	private Chrono chrono;
	private ArrayList<Piece> currentPieces= new ArrayList<>();
	
	@Override
    public void paintComponent(Graphics g){
		super.paintComponent(g);
        ImageIcon icon = new ImageIcon("bois.jpg");
        g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
  } 
	
	public PanelControl(Worker worker, Fenetre parentFenetre) {
		this.worker = worker;
		currentPieces= worker.getPieces();
		this.parentFenetre= parentFenetre;
		chrono= new Chrono();
		numPiece=0;
		passage=0;
		angle=0;
		setLayout(new GridBagLayout());
		builPanelControl();
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight =  1; 
		gbc.gridwidth =GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(10, 0, 50, 0);
		
		add(titre, gbc);
		gbc.gridy =1;
		add(emplacementPiece, gbc);
		gbc.gridy=2;
		gbc.gridwidth =1;
		gbc.insets = new Insets(10, 30, 50, 0);
		add(buttonNext, gbc);
		gbc.gridx=1;
		add(buttonRotate, gbc);
		gbc.gridwidth =GridBagConstraints.REMAINDER;
		gbc.gridx=0;
		gbc.gridy=3;
		add(chrono, gbc);
		setPreferredSize(new Dimension(375, 600));
	}
	
	private void builPanelControl(){
		titre= new JLabel("Let's play!");
		titre.setFont(font);
		titre.setForeground(Color.WHITE);
		buttonRotate = new JButton();
		buttonRotate.setIcon(new ImageIcon("rotate1.png"));
		buttonRotate.setPreferredSize(new Dimension(45, 45));
		buttonRotate.addActionListener(this);
		buttonNext = new JButton();
		buttonNext.setIcon(new ImageIcon("next.png"));
		buttonNext.setPreferredSize(new Dimension(45, 45));
		buttonNext.addActionListener(this);
		emplacementPiece = new LabelEmplacementPiece();
	}
	
	
	
	public void setEmplacementPiece(){
		emplacementPiece.setOpaque(false);
		emplacementPiece.setIcon(currentPieces.get(numPiece).getImg());
		reDrawPanel();
	}
	
	public void pieceSuivanteApresPose(){
		this.remove(emplacementPiece);
		numPiece++;
		worker.getPieces().get(numPiece).setRotation(0);
		currentPieces.get(numPiece).setRotation(0);
		emplacementPiece = new LabelEmplacementPiece(currentPieces.get(numPiece));
		reDrawPanel();
		parentFenetre.reDrawPanel();
		this.repaint();
		this.revalidate();
		this.getParent().revalidate();
	}
	
	
	private void reDrawPanel(){
		setLayout(new GridBagLayout());
		gbc.gridx = gbc.gridy = 0;
	    gbc.gridheight = gbc.gridwidth = 1; 
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(10, 0, 50, 0);
		
		add(titre, gbc);
		gbc.gridy =1;
		add(emplacementPiece, gbc);
		gbc.gridy=2;
		gbc.gridwidth = 1 ;
		gbc.insets = new Insets(10, 30, 50, 20);
		add(buttonNext, gbc);
		gbc.gridx=1;
		add(buttonRotate, gbc);
		gbc.gridwidth =GridBagConstraints.REMAINDER;
		gbc.gridx=0;
		gbc.gridy=3;
		add(chrono, gbc);
	}
	
	public int getNumPiece(){
		return( numPiece);
	}
	
	public Worker getWorker(){
		return worker;
	}
	
	public Fenetre getFenetre(){
		return parentFenetre;
	}
	
	public Chrono getChrono(){
		return chrono;
	}
	
	public ArrayList<Piece> getCurrentPieces(){
		return currentPieces;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==buttonRotate){
			//TODO
			passage++;
			switch(passage){
			case 1:
				angle=90;
				break;
			case 2:
				angle=180;
				break;
			case 3:
				angle=270;
				break;
			case 4:
				angle=360;
				passage=0;
				break;
			}
			this.remove(emplacementPiece);
			worker.getPieces().get(numPiece).setRotation(angle);
			currentPieces.get(numPiece).setRotation(angle);
			emplacementPiece = new LabelEmplacementPiece(currentPieces.get(numPiece));
			reDrawPanel();
			parentFenetre.reDrawPanel();
			this.repaint();
			this.revalidate();
			this.getParent().revalidate();
		}
		if(e.getSource()==buttonNext){
			//TODO
			angle=0;
			passage=0;
			numPiece++;
			if(numPiece==currentPieces.size()-1){
				numPiece=0;
			}
			this.remove(emplacementPiece);
			worker.getPieces().get(numPiece).setRotation(angle);
			currentPieces.get(numPiece).setRotation(angle);
			emplacementPiece= new LabelEmplacementPiece(currentPieces.get(numPiece));
			reDrawPanel();
			parentFenetre.reDrawPanel();
			this.repaint();
			this.revalidate();
			System.out.println(this.getParent().getParent().toString());
		}
		
	}

	
}
