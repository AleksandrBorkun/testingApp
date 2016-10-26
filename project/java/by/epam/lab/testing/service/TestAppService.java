package by.epam.lab.testing.service;

import by.epam.lab.testing.service.exception.ServiceException;

public interface TestAppService {

	public boolean showTestList(int subjectId) throws ServiceException;

	public boolean showSubject() throws ServiceException;

	public boolean goTesting(int subjectId) throws ServiceException;

}
