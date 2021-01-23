package core;

import java.util.HashMap;

public class Contacts{
	
	private static HashMap<String, Integer> contacts;
	
	private Contacts() {
		contacts = new HashMap<>();
	}
	
	public static HashMap<String, Integer> getInstance() {
		if(contacts == null) 
			new Contacts();
		
		return contacts;
	}
	
	public String createContact(String name, int phoneNumber) {
		if(!contacts.containsKey(name) && !contacts.containsValue(phoneNumber)) {
			contacts.put(name, phoneNumber);
			return CREATE_CONTACT_SUCCESS;
		} else {
			return CREATE_CONTACT_FAILED;
		}
	}
	
	
	public String removeContact(String name) {
		if(contacts.remove(name) == null)
			return REMOVE_CONTACT_FAILED;
		else
			return REMOVE_CONTACT_SUCCESS;
	}
	
	public int getNumber(String name) {
		return contacts.get(name);
	}
	
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(String s : contacts.keySet())
			sb.append(s + " - " + contacts.get(s));
		
		return sb.toString();
	}

}
