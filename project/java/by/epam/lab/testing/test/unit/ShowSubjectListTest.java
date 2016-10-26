package by.epam.lab.testing.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;

public class ShowSubjectListTest extends Assert{

	@Test
	public void positiveTest() throws DAOException {

		List<String> actual = DAOFactory.getInstance().getTestApp().getSubjectList();
		List<String> expected = new ArrayList<>();
		expected.add("Math");
		expected.add("Physics");
		expected.add("Geography");
		
		assertEquals(actual, expected);


	}
}
