package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewSubjectRequest;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;

public class SetNewSubject implements Command{

	@Override
	public Response execute(Request request) throws CommandException {
		SetNewSubjectRequest req;

		if (request instanceof SetNewSubjectRequest) {
			req = (SetNewSubjectRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		Response response = new Response();
		
		String subjectName = req.getSubjectName();
		
		try {
			if (DAOFactory.getInstance().getTestApp().setNewSubject(subjectName)) {

				response.setErrorStatus(false);
				response.setResultMessage("Your subject is added to Test");

			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Something was wrong. Try Again!");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			response.setErrorStatus(true);
			response.setErrorMessage("Something was wrong. Try Again!");
			return response;
		}
		return response;
	}

}
