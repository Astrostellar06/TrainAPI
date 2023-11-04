package tps.multi.ws;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String password;
	private String mail;
	private boolean isConnected;
	public List<Ticket> commandes = new ArrayList<>();
	
	public User(String name, String password, String mail) {
		this.name = name;
		this.password = password;
		this.mail = mail;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public boolean getIsConnected() {
		return this.isConnected;
	}
	
	public List<Ticket> getCommandes() {
		return this.commandes;
	}
	
	public void setIsConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	
	public void setCommandes(List<Ticket> commandes) {
		this.commandes = commandes;
	}
}
