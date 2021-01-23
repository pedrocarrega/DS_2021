package core;

public class Contact {
	
	private String firstName;
	private String lastName;
	private int phoneNumber;
	
	public Contact(String firstName, String lastName, int phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public Contact(String name, int phoneNumber) {
		
		String[] split = name.split(" ");
		this.firstName = split[0];
		this.lastName = split[1];
		this.phoneNumber = phoneNumber;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String newName) {
		this.firstName = newName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String newName) {
		this.lastName = newName;
	}
	
	public int getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(int newNumber) {
		this.phoneNumber = newNumber;
	}

}
