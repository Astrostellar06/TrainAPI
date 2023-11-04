package client;
 
import java.io.IOException;
import java.util.Scanner;

import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
 
public class ClientCall {

	public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.println("Choose the service you need :" + "\n");
            System.out.println("1. Find a Train");
            System.out.println("2. Book a Train");
            System.out.println("3. Change ticket");

            int choix = sc.nextInt();
            
            switch(choix) {
                case 1:
                    getService();
                    break;
                case 2:
                    postService();
                    break;
                case 3:
                    putService();
                    break;
                 default:
                     System.out.println("Invalid choice. Exiting.");
                     System.exit(0);
            }
            System.out.println("\n" + "Do you want to do something else ? (y/n)");
            char response = sc.next().charAt(0);
            
            if(response != 'y') {
                break;
            }
        }
        
        System.out.println("Exiting program");
    }
	
	private static void getService() {
        //Accès Get
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Departure from : ");
        String gareDepart = sc.nextLine();
        
        System.out.print("Arrival at : ");
        String gareArrivee = sc.nextLine();
        
        ClientResource resourceGet = new ClientResource("http://localhost:8182/findTrain/" + gareDepart + "&" + gareArrivee);
        try {
            resourceGet.get().write(System.out);
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
	
	private static void postService() {
        //Accès post
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Train number : ");
        String numTrain = sc.nextLine();
        
        System.out.print("Classe : ");
        String classe = sc.nextLine();
        
        System.out.print("Number of tickets : ");
        String nbTickets = sc.nextLine();
        
        ClientResource resource = new ClientResource("http://localhost:8182/train");  
        Form form = new Form();  
        form.add("id", numTrain);
        form.add("classe", classe);
        form.add("nbTickets", nbTickets);
 
        try {
            resource.post(form).write(System.out);
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void putService() {
        //Accès Put
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Former number of tickets : ");
        String formerNbrTickets = sc.nextLine();
        
        System.out.print("Former class : ");
        String formerClasse = sc.nextLine();
        
        System.out.print("Number of tickets : ");
        String nbrTickets = sc.nextLine();
        
        System.out.print("Class : ");
        String classe = sc.nextLine();
        
        ClientResource resourcePut = new ClientResource("http://localhost:8182/updateTicket/123");
        Form formPut = new Form();
        formPut.add("NbTickets", formerNbrTickets);
        formPut.add("Classe", formerClasse);
        formPut.add("newNbTickets", nbrTickets);
        formPut.add("newClasse", classe);
        
        try {
            resourcePut.put(formPut).write(System.out);
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}