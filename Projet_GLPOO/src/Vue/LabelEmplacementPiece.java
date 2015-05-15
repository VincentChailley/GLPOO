package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import Modele.Piece;

public class LabelEmplacementPiece extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graphics2D g2d;
	private Piece piece;
	private boolean mark;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d= (Graphics2D) g;
		AffineTransform trans = new AffineTransform();
		// Le centre du JPanel mesurant (150, 150) est situé au point (75, 75)
		// Rotation autour de ce point
		if(piece!=null){
			trans.rotate( Math.toRadians(piece.getRotation()), 75, 75);
			g2d.drawImage(piece.getImg().getImage(), trans, null);
		}
		this.repaint();
	}
	
	public LabelEmplacementPiece(){
		super();
		mark=false;
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(150, 150));
	}
	
	public LabelEmplacementPiece(Piece piece){
		super();
		mark=true;
		this.piece=piece;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setPreferredSize(new Dimension(150, 150));
	}

	public boolean getMark(){
		return mark;
	}
	
	public Graphics2D getGraph(){
		return(g2d);
	}
	
	public Piece getPiece(){
		return piece;
	}

	
}
