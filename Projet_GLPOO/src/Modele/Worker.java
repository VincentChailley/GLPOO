package Modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Worker {
	
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	//private ArrayList<Piece> results = new ArrayList<Piece>();
	
	public Worker() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadFile(){
		int mark=0, numPiece;
		String filename;
		String []facesPiece = new String[4];
		BufferedReader bufferedReader = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			bufferedReader= new BufferedReader(new FileReader("pieces.csv"));
			for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
	            lines.add(line);
	        }
			 for (String line : lines) {
				 if(mark>0 && mark <=16){
					 String[] data = line.split(";");
					 numPiece=mark;
					 filename="P"+numPiece+".jpg";
					 for (int i = 1; i < data.length; i++) {
						facesPiece[i-1]=data[i];
					}
					pieces.add(new Piece(numPiece, filename, facesPiece));
				 }
		            mark++;
		        }
		} catch (FileNotFoundException e) {
			System.err.println("Erreur d'ouverture du fichier: "+ e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Erreur de lecture du fichier: "+ e.getMessage());
			//e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

	public String toString(){
		String str ="Pieces: \n";
		for (Piece piece : pieces) {
			str=str+piece.toString()+"\n";
		}
		return(str);
	}
}
