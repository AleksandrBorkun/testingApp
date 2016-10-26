package by.epam.lab.testing.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.lab.testing.bean.entity.GeneralInformation;
import by.epam.lab.testing.dao.UserDAO;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.impl.pool.ConnectionPool;

public class UserDAOImpl implements UserDAO {

	public boolean authorization(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		if (login == null || password == null) {
			throw new DAOException("It's can't be empty data");
		} else if (login.equals("") || password.equals("")) {
			throw new DAOException("It's can't be empty data");
		}

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st
					.executeQuery("SELECT id FROM users where(name, password)=('" + login + "','" + password + "');");
			if (result.next() == false) {
				return false;
			} else {
				// result.next();
				int userID = result.getInt("id");
				GeneralInformation.setUserId(userID);
				st.close();
				return true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new DAOException(
					"Houston Here is some problem with connect. Don't give up our command help you as soon as posible");
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(
					"Houston Here is some problem with connect. Don't give up our command help you as soon as posible");

		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (SQLException e) {
			} catch (InterruptedException e) {
			}
		}
	}

	public boolean registration(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int result = st
					.executeUpdate("INSERT INTO users(name, password) VALUES('" + login + "','" + password + "');");
			if (result != 0) {
				st.close();
				return true;
			} else
				st.close();
			return false;
		}

		catch (Exception e) {
			throw new DAOException(
					"Houston Here is some problem with connect. Don't give up our command help you as soon as posible");

		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (SQLException e) {
			} catch (InterruptedException e) {
			}
		}

	}

	public void deleteUser(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int result = st
					.executeUpdate("DELETE FROM users Where(name, password)=('" + login + "','" + password + "');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (SQLException e) {
			} catch (InterruptedException e) {
			}
		}

	}

}
