package by.epam.lab.testing;

import java.util.ArrayList;
import java.util.List;

import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws DAOException, ServiceException {
		System.out.println("Hello World!");

		ServiceFactory.getInstance().getTestAppService().goTesting(1);
	}
}
