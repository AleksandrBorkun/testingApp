package by.epam.lab.testing.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewQuestionRequest;
import by.epam.lab.testing.bean.SetNewSubjectRequest;
import by.epam.lab.testing.controller.Controller;

public class SetNewSubjectTest extends Assert {

	@Test
	public void positiveTest() {

		Response response = new Response();
		Controller controller = new Controller();
		SetNewSubjectRequest request = new SetNewSubjectRequest();
		request.setSubjectName("Biology");
		request.setCommandName("CREATE_NEW_SUBJECT");
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), false, "It's may be false");
	}
}
