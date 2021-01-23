package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContactList {
	
	private Set<Contact> contactList = new HashSet<>();
	
	public void createContact(String firstName, String lastName, int phoneNumber) {
		Contact newContact = new Contact(firstName, lastName, phoneNumber);
		contactList.add(newContact);
	}
	
	public void removeContact(Contact contact) {
		contactList.remove(contact);
	}
}
