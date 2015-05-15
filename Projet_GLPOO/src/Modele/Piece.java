package Modele;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Piece {
	
	private int numPiece, rotation;
	private ImageIcon img;
	private String filename;
	private ArrayList<String> facesPiece= new ArrayList<String>();
	
	public Piece(int numPiece, String filename, String []facesPiece) {
		this.numPiece=numPiece;
		this.filename=filename;
		for (int i = 0; i < facesPiece.length; i++) {
			this.facesPiece.add(facesPiece[i]);
		}
		rotation=0;
		loadImage();
	}
	
	private void loadImage(){
		img= new ImageIcon(filename);
	}
	
	
	public String toString(){
		String str="Piece: "+numPiece+" | Faces: ";
		for (int i = 0; i < facesPiece.size(); i++) {
			str=str+facesPiece.get(i)+" ";
		}
		return(str);
	}

	public int getNumPiece() {
		return numPiece;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public ImageIcon getImg() {
		return img;
	}

	public ArrayList<String> getFacesPiece() {
		return facesPiece;
	}

	public String getFilename() {
		return filename;
	}
	
	

}
