package restAvailableTrain;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class BookTrain extends ServerResource{
	
	
	@Post
	public String bookTrain(Representation entity) {
		Form form = new Form(entity);
		//On récupère les conditions
		
		int id = Integer.parseInt(form.getFirstValue("id"));
		String classe = form.getFirstValue("classe");
		int nbTickets = Integer.parseInt(form.getFirstValue("nbTickets"));
		
		List<Train> listTrains = getList();
		
		//On fait les tests
        int index = -1;
		for (int i = 0 ; i < listTrains.size() ; i++) {
			if (listTrains.get(i).getNumTrain() == id) {
				switch(classe) {
					case "Standard":
						if (nbTickets <= listTrains.get(i).getNumSiegesStandard()) {
							index = i;
							listTrains.get(i).setNumSiegesStandard(listTrains.get(i).getNumSiegesStandard() - nbTickets);
						}
						break;		
					case "Business":
						if (nbTickets <= listTrains.get(i).getNumSiegesBusiness()) {						
							index = i;
							listTrains.get(i).setNumSiegesBusiness(listTrains.get(i).getNumSiegesBusiness() - nbTickets);
						}
						break;
					case "First":
						if (nbTickets <= listTrains.get(i).getNumSiegesFirst()) {
							index = i;
							listTrains.get(i).setNumSiegesFirst(listTrains.get(i).getNumSiegesFirst() - nbTickets);
						}
						break;
					default:
						return "Classe incorrecte";
				}
			}
		}
		
		//On retourne les trains qui fonctionnent
        if (index == -1)
        	return "Not enough tikets available or train not found";
        if (saveData(listTrains))
        	return listTrains.get(index).toString();
        return "Error in saving data";
	} 
	
	@Get  
	public String findTrain() {  
		String villes = (String) getRequestAttributes().get("villes");
		String[] listVilles = villes.split("&");
		
		List<Train> listTrains = getList();
        
        List<Train> listAvailable = new ArrayList<>();
        Train train;
		for (int i = 0 ; i < listTrains.size() ; i++) {
			train = listTrains.get(i);
			if (train.getGareDepart().equals(listVilles[0]) && train.getGareArrivee().equals(listVilles[1]))
				listAvailable.add(train);
		}
		
		if (listAvailable.size() == 0)
			return "No train found";
		String s = "";
		for (int i = 0 ; i < listAvailable.size() ; i++) {
			s += listAvailable.get(i).toString();
			s += "\n";
		}
		return s;
	}
	
	@Put
	public String updateTicket(Representation entity) {

		Representation result = null;
		String idString = (String) getRequestAttributes().get("id");
		int id = Integer.parseInt(idString);

		Form form = new Form(entity);
		int formerNbTickets = Integer.parseInt(form.getFirstValue("NbTickets"));
		String formerClasse = form.getFirstValue("Classe");
		int newNbTickets = Integer.parseInt(form.getFirstValue("newNbTickets"));
		String classe = form.getFirstValue("newClasse");

		int index = -1;
		List<Train> listTrains = getList();
		for (int i = 0 ; i < listTrains.size() ; i++) {
			if (listTrains.get(i).getNumTrain() == id) {
				switch(classe) {
					case "Standard":
						if (newNbTickets <= listTrains.get(i).getNumSiegesStandard()) {
							index = i;
							listTrains.get(i).setNumSiegesStandard(listTrains.get(i).getNumSiegesStandard() - newNbTickets);
						}
						break;		
					case "Business":
						if (newNbTickets <= listTrains.get(i).getNumSiegesBusiness()) {						
							index = i;
							listTrains.get(i).setNumSiegesBusiness(listTrains.get(i).getNumSiegesBusiness() - newNbTickets);
						}
						break;
					case "First":
						if (newNbTickets <= listTrains.get(i).getNumSiegesFirst()) {
							index = i;
							listTrains.get(i).setNumSiegesFirst(listTrains.get(i).getNumSiegesFirst() - newNbTickets);
						}
						break;
					default:
						return "Classe incorrecte";
				}
				switch(formerClasse) {
					case "Standard":
						listTrains.get(i).setNumSiegesStandard(listTrains.get(i).getNumSiegesStandard() + formerNbTickets);
						break;		
					case "Business":
						listTrains.get(i).setNumSiegesBusiness(listTrains.get(i).getNumSiegesBusiness() + formerNbTickets);
						break;
					case "First":
						listTrains.get(i).setNumSiegesFirst(listTrains.get(i).getNumSiegesFirst() + formerNbTickets);
						break;
					default:
						return "Classe incorrecte";
				}
			}
		}
		if (index == -1)
        	return "Not enough tikets available or train not found";
		boolean save = saveData(listTrains);
        if (save)
        	return listTrains.get(index).toString();
        return "Error in saving data";      
		
	}
	
	private List<Train> getList() {
		List<Train> listTrains = new ArrayList<>();

		String userDir = System.getProperty("user.dir");
		File fichier = new File(userDir, "\\listeTrains.txt");

        // test de lecture du fichier
        try
        {
			Scanner dataFile = new Scanner(fichier);
            while(dataFile.hasNext())
            {
            	String[] attributs = dataFile.nextLine().split(",");
            	listTrains.add(new Train(Integer.parseInt(attributs[0]), attributs[1], attributs[2], attributs[3], attributs[4], attributs[5], attributs[6], Integer.parseInt(attributs[7]), Integer.parseInt(attributs[8]), Integer.parseInt(attributs[9])));
            }
            dataFile.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        return listTrains;
	}
	
	
	private boolean saveData(List<Train> listTrains) {
		try {
			String userDir = System.getProperty("user.dir");
			File file = new File(userDir, "\\listeTrains.txt");
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			for (int i = 0 ; i < listTrains.size() ; i++) {
				String line = listTrains.get(i).getNumTrain() + "," + listTrains.get(i).getDateDepart() + "," + listTrains.get(i).getHeureDepart() + "," + listTrains.get(i).getDateArrivee() + "," + listTrains.get(i).getHeureArrivee() + "," + listTrains.get(i).getGareDepart() + "," + listTrains.get(i).getGareArrivee() + "," + listTrains.get(i).getNumSiegesFirst() + "," + listTrains.get(i).getNumSiegesBusiness() + "," + listTrains.get(i).getNumSiegesStandard();
				writer.write(line);
				writer.newLine();
			}
			writer.close();
			return(true);
		} catch (IOException e) {
			e.printStackTrace();
			return(false);
		}
	}
}
