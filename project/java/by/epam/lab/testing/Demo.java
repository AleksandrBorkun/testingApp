package by.epam.lab.testing;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

import by.epam.lab.testing.test.unit.Storage;
import by.epam.lab.testing.test.unit.TestsDataStorage;

public class Demo {


	public static void main(String[] args) throws SAXException, IOException {
		
		TestsDataStorage data = new TestsDataStorage();
		String login;
		String password;


		List<Storage> expected = data.authorization();
		for (Storage one : expected) {
			login = one.getLogin();
			password = one.getPassword();
			System.out.println(login + " " + password);
			
	}
	}
}
