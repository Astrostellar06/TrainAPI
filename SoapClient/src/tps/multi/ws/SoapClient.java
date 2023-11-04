package tps.multi.ws;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;

import tps.multi.ws.AuthentificationServiceStub.LogIn;
import tps.multi.ws.AuthentificationServiceStub.LogOut;
import tps.multi.ws.AuthentificationServiceStub.SignIn;
import tps.multi.ws.AuthentificationServiceStub.AddTicket;
import tps.multi.ws.AuthentificationServiceStub.GetListTicket;
import tps.multi.ws.AuthentificationServiceStub.CheckTicket;
import tps.multi.ws.AuthentificationServiceStub.ModifyTicket;

public class SoapClient {
	private static boolean connected = false;
    public static void main(String[] args) throws RemoteException {
    	Disconnect();//Pour être bien sûr de ne pas avoir de duplicata de connexion en cas de crash précédent
    	boolean stayInLoop = true;
        while(stayInLoop) {
        	Scanner sc = new Scanner(System.in);
	        System.out.println("\nChoose the service you need:" + "\n");
	        if (!connected) {
		        System.out.println("1. Create account");
		        System.out.println("2. Log in");
	        } else {
		        System.out.println("1. Find a Train");
		        System.out.println("2. Book a Train");
		        System.out.println("3. See my Tickets");
		        System.out.println("4. Modify a Ticket");
		        System.out.println("5. Log out");
	        }
	
	        int choix = sc.nextInt();
	        
	        if (!connected) {
	        	switch(choix) {
		        case 1:
		             CreateAccount();//Done
		             break;
		        case 2:
		             Connection();//Done
		             break;
		        default:
		              System.out.println("Invalid choice. Exiting.");
		              Disconnect();
		              System.exit(0);
	        	}
	        } else {
	        	switch(choix) {
		        case 1:
		             findTrain();//Done
		             break;
		        case 2:
		             bookTrain();//Done
		             break;
		        case 3:
		             seeTickets();//Done
		             break;
		        case 4:
		             modifyTickets();
		             break;
		        case 5:
		             Disconnect();//Done
		             connected = false;
		             break;
	        	}
	        
	        }
	        System.out.println("\n\n" + "Do you want to do something else? (y/n)");
	        char response = sc.next().charAt(0);
         
	        if(response != 'y') {
	        	stayInLoop = false;
	        }
        }
        System.out.println("Exiting program");
        Disconnect();
    }
     
     
    private static void Connection() throws RemoteException {
         Scanner sc = new Scanner(System.in);
         
         System.out.println("Enter your name:");
         String name = sc.nextLine();
         
         System.out.println("Enter your password:");
         String password = sc.nextLine();
         
         AuthentificationServiceStub astub = new AuthentificationServiceStub();
         LogIn l = new LogIn();
         l.setName(name);
         l.setPassword(password);
         String result = astub.logIn(l).get_return();
         if (!result.equals("User not found"))
        	 connected = true;
         System.out.print(result);
     }
         
     private static void Disconnect() throws RemoteException {
         AuthentificationServiceStub astub2 = new AuthentificationServiceStub();
         LogOut n = new LogOut();
         
         String result = astub2.logOut(n).get_return();
         if (!result.equals("An error occured"))
        	 connected = false;
         System.out.print(result);
     }
         
     
     private static void CreateAccount() throws RemoteException {
         Scanner sc = new Scanner(System.in);
         
         System.out.println("Enter your name:");
         String name = sc.nextLine();
         
         System.out.println("Enter your password:");
         String password = sc.nextLine();
         
         System.out.println("Enter your email:");
         String email = sc.nextLine();
         
         AuthentificationServiceStub astub3 = new AuthentificationServiceStub();
         SignIn s = new SignIn();
         s.setName(name);
         s.setPassword(password);
         s.setMail(email);
         System.out.print(astub3.signIn(s).get_return());
     }
     
     private static void findTrain() throws RemoteException {
    	 Scanner sc = new Scanner(System.in);
         
         System.out.println("Enter departure city:");
         String gareDepart = sc.nextLine();
         
         System.out.println("Enter arrival city:");
         String gareArrivee = sc.nextLine();
         
         try {
			System.out.print(getService(gareDepart, gareArrivee));
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
     
     private static void bookTrain() throws RemoteException{
    	 Scanner sc = new Scanner(System.in);
         
         System.out.println("Enter the Train Number:");
         String number = sc.nextLine();
         
         System.out.println("Enter the Train Class:");
         String classe = sc.nextLine();
         
         System.out.println("Enter the number of tickets:");
         String nbTickets = sc.nextLine();
         
         try {
        	 String result = postService(number, classe, String.valueOf(nbTickets));
        	 System.out.println(result);
        	 if (!(result.equals("Error") || result.equals("Classe incorrecte") || result.equals("Not enough tikets available or train not found") || result.equals("Error in saving data"))){
        		 AuthentificationServiceStub astub = new AuthentificationServiceStub();
                 AddTicket s = new AddTicket();
                 s.setNumTrain(Integer.parseInt(number));
                 s.setClasse(classe);
                 s.setNbPlaces(Integer.parseInt(nbTickets));
                 System.out.print(astub.addTicket(s).get_return());  
        	 }
         } catch (IOException e) {
        	 System.out.print("Error");
         }
         
     }
     
     private static void seeTickets() throws RemoteException{
    	 AuthentificationServiceStub astub = new AuthentificationServiceStub();
         GetListTicket s = new GetListTicket();
         System.out.print(astub.getListTicket(s).get_return());
     }
    
     private static void modifyTickets() throws RemoteException{
    	 Scanner sc = new Scanner(System.in);
    	 
    	 System.out.println("Enter the Ticket ID:");
         String id = sc.nextLine();
         
         System.out.println("Enter the New Train Class:");
         String classe = sc.nextLine();
         
         System.out.println("Enter the New Number of Tickets:");
         String nbTickets = sc.nextLine();
         
         //On vérifie que la commande existe et on récupère les infos
         AuthentificationServiceStub astub = new AuthentificationServiceStub();
         CheckTicket s = new CheckTicket();
         s.setId(Integer.parseInt(id));
         String[] result = (astub.checkTicket(s).get_return()).split("&");
         if (result[0].equals("No train found")) {
        	 System.out.print(result[0]);
        	 return;
         }
         
         //On fait la modification au niveau des trains avec le @Put
         String modifie = putService(result[0], result[2], result[1], nbTickets, classe);
         if (modifie.equals("Error") || modifie.equals("Classe incorrecte") || modifie.equals("Not enough tikets available or train not found") || modifie.equals("Error in saving data")) {
        	 System.out.print(modifie);
        	 return;
         }
         
         //On fait la modification au niveau des users
         AuthentificationServiceStub astub2 = new AuthentificationServiceStub();
         ModifyTicket l = new ModifyTicket();
         l.setId(Integer.parseInt(id));
         l.setClasse(classe);
         l.setNbPlaces(Integer.parseInt(nbTickets));
         System.out.print(astub2.modifyTicket(l).get_return());
     }
     
     
     
     private static String getService(String gareDepart, String gareArrivee) throws IOException {
         //Accès Get         
         ClientResource resourceGet = new ClientResource("http://localhost:8182/findTrain/" + gareDepart + "&" + gareArrivee);
         try {
             return resourceGet.get().getText();
         } catch (ResourceException e) {
             e.printStackTrace();
             return "Error";
         }
     }
     
     private static String postService(String numTrain, String classe, String nbTickets) throws IOException {
         //Accès post
         
         ClientResource resource = new ClientResource("http://localhost:8182/train");  
         Form form = new Form();  
         form.add("id", numTrain);
         form.add("classe", classe);
         form.add("nbTickets", nbTickets);
  
         try {
             Representation representation = resource.post(form);
             return representation.getText();
         } catch (ResourceException e) {
             e.printStackTrace();
         }
         return "Error";
     }
     
     private static String putService(String id, String formerNbrTickets, String formerClasse, String nbrTickets, String classe) {
         //Accès Put
         String url = "http://localhost:8182/updateTicket/" + String.valueOf(id);
         ClientResource resourcePut = new ClientResource(url);
         Form formPut = new Form();
         formPut.add("NbTickets", formerNbrTickets);
         formPut.add("Classe", formerClasse);
         formPut.add("newNbTickets", nbrTickets);
         formPut.add("newClasse", classe);
         
         try {
             return resourcePut.put(formPut).getText();
         } catch (ResourceException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return ("Error");
     } 
 }
        