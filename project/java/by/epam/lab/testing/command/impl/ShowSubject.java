package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.ShowSubjectRequest;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class ShowSubject implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		ShowSubjectRequest req;

		if (request instanceof ShowSubjectRequest) {
			req = (ShowSubjectRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();
		try {
			if (ServiceFactory.getInstance().getTestAppService().showSubject()) {
				response.setErrorStatus(false);
				response.setResultMessage("Completed Success!!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("There is no any subject!");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("There is no any subject!");
			e.printStackTrace();
			return response;
		}
		return response;
	}

}
