package tps.multi.ws;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AuthentificationService {
	
	public String LogIn(String name, String password) {
		List<User> listUsers = getListUser();
		LogOut();
				
		for (int i = 0 ; i < listUsers.size(); i++) {
			if (listUsers.get(i).getName().equals(name) && listUsers.get(i).getPassword().equals(password)) {
				listUsers.get(i).setIsConnected(true);
				boolean save = saveData(listUsers);
				if (save)
					return "You are connected";
				return "Connexion failed";
			}
		}
		return "User not found";
	}
	
	public String LogOut() {
		List<User> listUsers = getListUser();
		for (int i = 0 ; i < listUsers.size() ; i++) {
			listUsers.get(i).setIsConnected(false);
		}
		boolean save = saveData(listUsers);
		if (save)
			return "Succesfully logged out";
		return "An error occured";
	}
	
	public String SignIn(String name, String password, String mail) {
		User user = new User(name, password, mail);
		List<User> userList = getListUser();
		userList.add(user);
		boolean save = saveData(userList);
		if (save)
			return("Successfuly added user");
		else
			return("Error when creating the user");
	}
	
	public String addTicket(int numTrain, String classe, int nbPlaces) {
		Ticket ticket = new Ticket(numTrain, classe, nbPlaces);
		List<User> listUsers = getListUser();
		boolean change = false;
		for (int i = 0 ; i < listUsers.size() ; i++) {
			if (listUsers.get(i).getIsConnected()) {
				List<Ticket> newCommandes = listUsers.get(i).getCommandes();
				newCommandes.add(ticket);
				listUsers.get(i).setCommandes(newCommandes);
				change = saveData(listUsers);
			}
		}
		if (change)
			return "Your ticket was booked successfuly:\n\n" + ticket.toString();
		return "Error";	
	}
	
	public String getListTicket() {
		List<User> listUsers = getListUser();
		String result = "";
		boolean change = false;
		for (int i = 0 ; i < listUsers.size() ; i++) {
			if (listUsers.get(i).getIsConnected()) {
				if (listUsers.get(i).getCommandes().size() == 0)
					result = "No Tickets booked yet";
				for (int j = 0 ; j < listUsers.get(i).getCommandes().size() ; j++)
					result += listUsers.get(i).getCommandes().get(j).toString() + "\n\n";
			}
		}
		if (result == "")
			return "Error";
		return result;
	}
	
	public String checkTicket(int id) {
		List<User> listUsers = getListUser();
		String result = "";
		boolean change = false;
		for (int i = 0 ; i < listUsers.size() ; i++) {
			if (listUsers.get(i).getIsConnected()) {
				for (int j = 0 ; j < listUsers.get(i).getCommandes().size() ; j++)
					if (listUsers.get(i).getCommandes().get(j).getId() == id)
						result += listUsers.get(i).getCommandes().get(j).getNumTrain() + "&" + listUsers.get(i).getCommandes().get(j).getClasse() + "&" + listUsers.get(i).getCommandes().get(j).getNbPlaces();
			}
		}
		if (result == "")
			return "No train found";
		return result;
	}
	
	public String modifyTicket(int id, String classe, int nbPlaces) {
		List<User> listUsers = getListUser();
		String result = "";
		boolean change = false;
		for (int i = 0 ; i < listUsers.size() ; i++) {
			if (listUsers.get(i).getIsConnected()) {
				for (int j = 0 ; j < listUsers.get(i).getCommandes().size() ; j++) {
					if (listUsers.get(i).getCommandes().get(j).getId() == id) {
						listUsers.get(i).getCommandes().get(j).setClasse(classe);
						listUsers.get(i).getCommandes().get(j).setNbPlaces(nbPlaces);
						if (saveData(listUsers))
							return "Your ticket was successfuly modified:\n\n" + listUsers.get(i).getCommandes().get(j).toString();
					}
				}
			}
		}
		return "It seems that an error occured during the data modification";
	}
	
	
	
	
	private List<User> getListUser(){
		Gson gson = new Gson();
		
		try {
			String userDir = System.getProperty("user.dir");
			File file = new File(userDir, "\\listeUsers.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			StringBuilder jsonContent = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonContent.append(line);
			}
			reader.close();
			List<User> userList = gson.fromJson(jsonContent.toString(), new TypeToken<List<User>>() {}.getType());
			
			return(userList);
		} catch (IOException e) {
			e.printStackTrace();
			return(new ArrayList<>());
		}
	}
	
	private boolean saveData(List<User> userList) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			String userDir = System.getProperty("user.dir");
			File file = new File(userDir, "\\listeUsers.txt");
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			String json = gson.toJson(userList);
			writer.write(json);
			writer.close();
			return(true);
		} catch (IOException e) {
			e.printStackTrace();
			return(false);
		}
	}
}
