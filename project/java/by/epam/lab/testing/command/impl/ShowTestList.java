package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.ShowTestListRequest;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class ShowTestList implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		ShowTestListRequest req;

		if (request instanceof ShowTestListRequest) {
			req = (ShowTestListRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();

		String subjectID = req.getSubjectId();

		try {
			if (ServiceFactory.getInstance().getTestAppService().showTestList(subjectID)) {
				response.setErrorStatus(false);
				response.setResultMessage("Completed Success!!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("There is no any question!");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("There is no any question!");
			e.printStackTrace();
		}

		return response;

	}

}
