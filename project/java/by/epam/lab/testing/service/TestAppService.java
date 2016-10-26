package by.epam.lab.testing.service;

import by.epam.lab.testing.service.exception.ServiceException;

public interface TestAppService {

	public boolean showTestList(String subjectID) throws ServiceException;

	public boolean showSubject() throws ServiceException;

	public boolean goTesting(String subjectId) throws ServiceException;

}
