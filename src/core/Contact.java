package core;

public class Contact {
	
	private String firstName;
	private String lastName;
	private int phoneNumber;
	private String email;
	
	public Contact(String firstName, String lastName, int phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
