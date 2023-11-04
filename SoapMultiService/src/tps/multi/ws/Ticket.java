package tps.multi.ws;

public class Ticket {
	private int id;
	private int numTrain;
	private String classe;
	private int nbPlaces;
	private static int counter;
	
	public Ticket(int numTrain, String classe, int nbPlaces) {
		this.numTrain = numTrain;
		this.classe = classe;
		this.nbPlaces = nbPlaces;
		this.id = counter;
		counter++;
	}
	
	@Override
	public String toString() {
		return "Ticket ID: " + id + "\nTrain Number: " + numTrain + "\nClass: " + classe + "\nNumber of Places: " + nbPlaces;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getNumTrain() {
		return this.numTrain;
	}
	
	public String getClasse() {
		return this.classe;
	}
	
	public int getNbPlaces() {
		return this.nbPlaces;
	}
	
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
}
