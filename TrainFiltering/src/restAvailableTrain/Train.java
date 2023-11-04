package restAvailableTrain;

public class Train {
	int numTrain;
	String dateArrivee;
	String heureArrivee;
	String dateDepart;
	String heureDepart;
	String gareDepart;
	String gareArrivee;
	int numSiegesFirst;
	int numSiegesBusiness;
	int numSiegesStandard;
	
	public Train(int numTrain, String dateDepart, String heureDepart, String dateArrivee, String heureArrivee, String gareDepart, String gareArrivee, int numSiegesFirst, int numSiegesBusiness, int numSiegesStandard) {
		this.numTrain = numTrain;
		this.dateArrivee = dateArrivee;
		this.heureArrivee = heureArrivee;
		this.dateDepart = dateDepart;
		this.heureDepart = heureDepart;
		this.gareDepart = gareDepart;
		this.gareArrivee = gareArrivee;
		this.numSiegesFirst = numSiegesFirst;
		this.numSiegesBusiness = numSiegesBusiness;
		this.numSiegesStandard = numSiegesStandard;
	}
	
	public void setNumSiegesFirst(int numSiegesFirst) {
		this.numSiegesFirst = numSiegesFirst;
	}
	
	public void setNumSiegesBusiness(int numSiegesBusiness) {
		this.numSiegesBusiness = numSiegesBusiness;
	}
	
	public void setNumSiegesStandard(int numSiegesStandard) {
		this.numSiegesStandard = numSiegesStandard;
	}
	
	public int getNumTrain()
	{
	    return numTrain;
	}

	public String getDateArrivee()
	{
	    return dateArrivee;
	}

	public String getHeureArrivee()
	{
	    return heureArrivee;
	}
	public String getDateDepart()
	{
	    return dateDepart;
	}
	public String getHeureDepart()
	{
	    return heureDepart;
	}
	public String getGareDepart()
	{
	    return gareDepart;
	}

	public String getGareArrivee()
	{
	    return gareArrivee;
	}
	public int getNumSiegesFirst()
	{
	    return numSiegesFirst;
	}
	public int getNumSiegesBusiness()
	{
	    return numSiegesBusiness;
	}
	public int getNumSiegesStandard()
	{
	    return numSiegesStandard;
	}
	
	@Override
	public String toString() {
		return "Numéro du train :" + numTrain + "\nGare de départ : " + gareDepart + "\nGare d'arrivée : " + gareArrivee + "\nDate de départ : " + dateDepart + "\nHeure de départ : " + heureDepart + "\nDate d'arrivée : " + dateArrivee + "\nHeure d'arrivée : " + heureArrivee + "\nNombre de places en :\n   -First : " + numSiegesFirst + "\n   -Business : " + numSiegesBusiness + "\n   -Standard : " + numSiegesStandard;
	}
}
