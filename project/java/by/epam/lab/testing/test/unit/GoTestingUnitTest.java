package by.epam.lab.testing.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;

public class GoTestingUnitTest extends Assert{

	
	List<Integer> actual = new ArrayList<>();
	
	@Test
	public void positiveTest() throws DAOException{
		List<Integer> expected = new ArrayList<>();
		
		expected.add(3);
		expected.add(4);
		
		actual = DAOFactory.getInstance().getTestApp().chekTestResult(1);
	
		//assertSame(actual, expected, "ooops");
		
		assertEquals(actual, expected, "I hope that work");
	}
	
	@Test
	public void negativTest() throws DAOException{
		List<Integer> expected = new ArrayList<>();
		expected = null;
		actual = DAOFactory.getInstance().getTestApp().chekTestResult(1);
		
		assertNotEquals(actual, expected, "I hope thats false");	
		
	}
	
	
}
