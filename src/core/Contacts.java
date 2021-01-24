package core;

import java.util.HashMap;
import java.util.Scanner;

import i18n.I18N;
import i18n.Messages;

public class Contacts {
	
	private static HashMap<String, Integer> contacts;
	
	public Contacts() {
		contacts = new HashMap<>();
	}
	
	
	public String createContact(Scanner sc) {
		
		System.out.println(I18N.getString(Messages.INSERT_NAME));
		String name = sc.nextLine();
		System.out.println(I18N.getString(Messages.INSERT_NUMBER));
		int phoneNumber = Integer.parseInt(sc.nextLine());
		
		if(!contacts.containsKey(name) && !contacts.containsValue(phoneNumber)) {
			contacts.put(name, phoneNumber);
			return I18N.getString(Messages.CREATE_CONTACT_SUCCESS);
		} else {
			return I18N.getString(Messages.CREATE_CONTACT_FAILED);
		}
	}
	
	public String editContact(Scanner sc) {
		
		System.out.println(I18N.getString(Messages.INSERT_NAME));
		String name = sc.nextLine();
		
		if(contacts.remove(name) == null) {
			return I18N.getString(Messages.CONTACT_DOESNT_EXIST);
		}
		return createContact(sc);
	}
	
	public String removeContact(Scanner sc) {
		
		System.out.println(I18N.getString(Messages.INSERT_NAME));
		String name = sc.nextLine();
		
		if(contacts.remove(name) == null)
			return I18N.getString(Messages.REMOVE_CONTACT_FAILED);
		else
			return I18N.getString(Messages.REMOVE_CONTACT_SUCCESS);
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
