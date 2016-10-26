package by.epam.lab.testing.dao;

import java.util.List;

import by.epam.lab.testing.dao.exception.DAOException;

public interface TestApp {

	public List<String> getSubjectList() throws DAOException;

	public boolean setNewSubject(String subjectName) throws DAOException;

	public List<String> getTestListBySubject(int subjectID) throws DAOException;

	public boolean setTestListBySubject(int subjectID, String question, int answer) throws DAOException;

	public List<Integer> chekTestResult(int subjectID) throws DAOException;
	
	public void clearDB() throws DAOException;

}
