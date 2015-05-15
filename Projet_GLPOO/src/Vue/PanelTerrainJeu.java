package Vue;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelTerrainJeu extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LabelEmplacementPiece [][]terrain;
	int i_changed=17, j_changed=17;
	PanelControl control;
	
	@Override
    public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("bois.jpg");
        g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);       
        
  } 
	
	public PanelTerrainJeu(PanelControl control) {
		this.control=control;
		setPreferredSize(new Dimension(625, 625));
		buildTerrain();
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain.length; j++) {
				this.add(terrain[i][j]);
			}
		}
	}
	
	public PanelTerrainJeu(int i_changed, int j_changed, PanelControl control){
		this.control=control;
		this.i_changed= i_changed;
		this.j_changed=j_changed;
		setPreferredSize(new Dimension(625, 625));
		buildTerrain();
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain.length; j++) {
				this.add(terrain[i][j]);
			}
		}
	}
	
	public void setTerrainJeu(int i_changed, int j_changed){
		this.i_changed= i_changed;
		this.j_changed=j_changed;
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain.length; j++) {
				this.remove(terrain[i][j]);
				if(i==i_changed && j==j_changed){
					terrain[i][j]=new LabelEmplacementPiece(control.getWorker().getPieces().get(control.getNumPiece()));
					terrain[i][j].addMouseListener(this);
					this.add(terrain[i][j]);
				}
				else{
					this.add(terrain[i][j]);
				}
			}
		}
		this.repaint();
		this.revalidate();
	}
	
	public void setSuppTerrainJeu(int i_changed, int j_changed){
		this.i_changed= i_changed;
		this.j_changed=j_changed;
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain.length; j++) {
				this.remove(terrain[i][j]);
				if(i==i_changed && j==j_changed){
					terrain[i][j]=new LabelEmplacementPiece();
					terrain[i][j].addMouseListener(this);
					this.add(terrain[i][j]);
				}
				else{
					this.add(terrain[i][j]);
				}
			}
		}
		this.repaint();
		this.revalidate();
	}
	
	private void buildTerrain(){
		terrain= new LabelEmplacementPiece [4][4];
		int x=25, y=25;
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain.length; j++) {
				if(i==i_changed && j==j_changed){
					terrain[i][j]=new LabelEmplacementPiece(control.getWorker().getPieces().get(control.getNumPiece()));
				}
				else{
					terrain[i][j]= new LabelEmplacementPiece();
				}
				terrain[i][j].setBounds(x, y, 150, 150);
				terrain[i][j].addMouseListener(this);
				x+=150;
			}
			y+=150;
		}
	}
	
	public boolean jeuFini(){
		int count=0;
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain.length; j++) {
				if(terrain[i][j].getMark()){
					count++;
				}
			}
		}
		if(count==terrain.length*terrain.length){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean win(){
		/*
		
		Donne initial couleur de la face de la piece
		terrain[i][j].getPiece().getFacesPiece().get(0);
		
		Pour tester une String == String
		if(terrain[i][j].getPiece().getFacesPiece().get(0).equals(terrain[i+1][j].getPiece().getFacesPiece().get(0));
		
		Pour recupŽrer la rotation (angle)
		0: Nord ˆ sa place
		90: Nord ˆ l'Est
		180: Nord au Sud
		270: Nord ˆ l'Ouest
		terrain[i][j].getPiece().getRotation();
		
		*/
		
		return false;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain.length; j++) {
				if(e.getSource()==terrain[i][j]){
					if(terrain[i][j].getMark()){
						setSuppTerrainJeu(i, j);
						control.getCurrentPieces().add(terrain[i][j].getPiece());
					}
					else{
						setTerrainJeu(i, j);
						control.pieceSuivanteApresPose();
						control.getCurrentPieces().remove(control.getNumPiece());
					}
					if(jeuFini()){
						win();
					}
					System.out.println("Hello: terrain["+i+"]["+j+"]");
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
