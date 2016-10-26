package by.epam.lab.testing.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewQuestionRequest;
import by.epam.lab.testing.controller.Controller;

public class SetNewQuestionTest extends Assert{

	@Test
	public void positivTest(){
		
		Response response = new Response();
		Controller controller = new Controller();
		SetNewQuestionRequest request = new SetNewQuestionRequest();
		request.setAnswer(1);
		request.setQuestion("Назовите самую маленькую цифру.\n1. 0\n2. -infinity\n3. NaN\4. infinity");
		request.setSubjectId(1);
		request.setCommandName("CREATE_NEW_QUESTION");
		response = controller.doRequest(request);
		
		assertEquals(response.isErrorStatus(), false, "It's may be false");
	}
}
