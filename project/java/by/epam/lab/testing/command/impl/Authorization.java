package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.AuthorizationRequest;
import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;

public class Authorization implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		AuthorizationRequest req;

		if (request instanceof AuthorizationRequest) {
			req = (AuthorizationRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();

		String login = req.getLogin();
		String password = req.getPassword();

		try {
			if (DAOFactory.getInstance().getUserDAO().authorization(login, password)) {
				response.setErrorStatus(false);
				response.setResultMessage("Hello " + login + "! Your authorization completed successfully!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Sorry we cant't find user with name " + login + " and password " + password);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}

}
