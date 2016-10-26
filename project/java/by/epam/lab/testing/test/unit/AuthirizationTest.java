package by.epam.lab.testing.test.unit;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;

public class AuthirizationTest extends Assert {

	TestsDataStorage data = new TestsDataStorage();
	String login;
	String password;
	String negativName = "name";
	String negativPass = "pass";

	@Test
	public void positiveTest() throws DAOException, SAXException, IOException {

		List<Storage> expected = data.authorization();
		for (Storage one : expected) {
			login = one.getLogin();
			password = one.getPassword();

			boolean actual = DAOFactory.getInstance().getUserDAO().authorization(login, password);
			assertEquals(actual, true, "We expected true result");
		}
	}

	@Test
	public void negativTestOne() throws DAOException {

		boolean actual = DAOFactory.getInstance().getUserDAO().authorization(negativName, negativPass);

		assertFalse(actual, "We expected false");

	}

	@Test(expectedExceptions = { DAOException.class })
	public void negativTestTwo() throws DAOException {

		boolean actual = DAOFactory.getInstance().getUserDAO().authorization(null, null);

		assertFalse(actual, "We expected false");

	}

}
