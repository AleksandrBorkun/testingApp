package by.epam.lab.testing.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.lab.testing.dao.TestApp;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.impl.pool.ConnectionPool;

public class TestAppImpl implements TestApp {

	private List<String> someList = new ArrayList<>();
	private List<Integer> answers = new ArrayList<>();

	@Override
	public List<String> getSubjectList() throws DAOException {

		Connection con = null;
		Statement st = null;
		someList.clear();
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM subject;");
			if (result.next() == false) {
				System.out.println("Sorry but we can't find any subjects! You can to add it");
				st.close();
				return null;
			} else {
				// result.next();
				do {
					someList.add(result.getString("subject_name"));
				} while (result.next());
				st.close();
			}
			return someList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

		return null;
	}

	@Override
	public boolean setNewSubject(String subjectName) throws DAOException {

		Connection con = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			try (Statement st = con.createStatement()) {

				int result = st.executeUpdate("INSERT INTO subject(subject_name) VALUES('" + subjectName + "');");
				st.close();
				if (result != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<String> getTestListBySubject(int subjectID) throws DAOException {

		Connection con = null;
		Statement st = null;
		someList.clear();
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM questions WHERE subject_id = " + subjectID + ";");
			if (result.next() == false) {
				System.out.println("Sorry but we can't find any questions! You can to add it");
				st.close();
				return null;
			} else {
				do {
					someList.add(result.getString("question"));
				} while (result.next());
				st.close();
			}
			return someList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

		return null;
	}

	@Override
	public boolean setTestListBySubject(int subjectID, String question, int answer) throws DAOException {

		Connection con = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			try (Statement st = con.createStatement()) {

				int result = st.executeUpdate("INSERT INTO questions(subject_id, question, answer) VALUES('" + subjectID
						+ "','" + question + "','" + answer + "');");
				st.close();
				if (result != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<Integer> chekTestResult(int subjectID) throws DAOException {

		Connection con = null;
		Statement st = null;
		answers.clear();
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM questions WHERE subject_id = " + subjectID + ";");
			if (result.next() == false) {
				System.out.println("Sorry but we can't find any answers for your question! You can to add it");
				st.close();
				return null;
			} else {
				do {
					answers.add(result.getInt("answer"));
				} while (result.next());
				st.close();
			}
			return answers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

		return null;
	}

	@Override
	public void clearDB() throws DAOException{
		Connection con = null;
		Statement st = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet subDEL = st.executeQuery("DELETE FROM subject Where id>=0");
			ResultSet testKILL = st.executeQuery("DELETE FROM questions Where id>=0");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
