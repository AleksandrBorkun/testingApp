package by.epam.lab.testing.view;

import java.util.Scanner;

import by.epam.lab.testing.bean.AuthorizationRequest;
import by.epam.lab.testing.bean.GoTestingRequest;
import by.epam.lab.testing.bean.RegistrationRequest;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewQuestionRequest;
import by.epam.lab.testing.bean.SetNewSubjectRequest;
import by.epam.lab.testing.bean.ShowSubjectRequest;
import by.epam.lab.testing.bean.ShowTestListRequest;
import by.epam.lab.testing.bean.entity.GeneralInformation;
import by.epam.lab.testing.controller.Controller;

public class View {

	static Scanner in = new Scanner(System.in);
	private static boolean exit = true;
	private static String help = "Take a list of command:\n0 - EXIT\n\'help\' - HELP\n1 - REGISTRATION\n2 - AUTHORIZATION\n3 - SHOW_SUBJECT\n4 - GO_TESTING\n5 - SHOW_QUESTION\n6 - CREATE_NEW_QUESTION\n7 - CREATE_NEW_SUBJECT";

	public static void main(String[] args) {

		String login;
		String pass;
		String subjectId;
		String question;
		int answer;
		String subjectName;

		Controller controller = new Controller();
		System.out.println("Hello!!! It's NEW APP FOR TESTING by Aleksand_Br");
		System.out.println();
		System.out.println(help);

		while (exit) {
			System.out.println("Enter the command");
			String choise = in.nextLine();

			// CLOSE PROGRAM
			if (choise.equals("0")) {
				System.out.println("SYSTEM IS CLOSING...\nBEST REGARDS!");
				in.close();
				break;
			}
			switch (choise) {

			// CALL HELP
			case "help":
				System.out.println(help);
				break;

			// REGISTRATION
			case "1":
				RegistrationRequest regReq = new RegistrationRequest();
				regReq.setCommandName("REGISTRATION");
				System.out.println("Please write your full name, or login");
				login = in.nextLine();
				System.out.println("Please write your password");
				pass = in.nextLine();
				regReq.setLogin(login);
				regReq.setPassword(pass);
				Response regResponse = controller.doRequest(regReq);
				if (regResponse.isErrorStatus() == true) {
					System.out.println(regResponse.getErrorMessage());
				} else {
					System.out.println(regResponse.getResultMessage());
				}
				break;

			// AUTHORIZATION
			case "2":
				AuthorizationRequest authReq = new AuthorizationRequest();
				authReq.setCommandName("AUTHORIZATION");
				System.out.println("Please write your full name, or login");
				login = in.nextLine();
				System.out.println("Please write your password");
				pass = in.nextLine();
				authReq.setLogin(login);
				authReq.setPassword(pass);
				Response authResponse = controller.doRequest(authReq);
				if (authResponse.isErrorStatus() == true) {
					System.out.println(authResponse.getErrorMessage());
				} else {
					System.out.println(authResponse.getResultMessage());
				}
				break;

			// SHOW_SUBJECT
			case "3":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				}
				ShowSubjectRequest showSubRequest = new ShowSubjectRequest();
				showSubRequest.setCommandName("SHOW_SUBJECT");
				Response showSubResponse = controller.doRequest(showSubRequest);
				if (showSubResponse.isErrorStatus() == true) {
					System.out.println(showSubResponse.getErrorMessage());
				} else {
					System.out.println(showSubResponse.getResultMessage());
				}
				break;

			// GO_TESTING
			case "4":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				}
				GoTestingRequest goRequest = new GoTestingRequest();
				goRequest.setCommandName("GO_TESTING");
				System.out.println("Please choose the subject by name");
				subjectId = in.nextLine();
				goRequest.setSubjectId(subjectId.toUpperCase());
				Response goResponse = controller.doRequest(goRequest);
				if (goResponse.isErrorStatus() == true) {
					System.out.println(goResponse.getErrorMessage());
				} else {
					System.out.println(goResponse.getResultMessage());
				}
				break;

			// SHOW_QUESTION
			case "5":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				} else if (GeneralInformation.getUserId() != 1) {
					System.out.println("Sorry but for looking the questions you need to be TUTOR");
					break;
				}
				ShowTestListRequest showTestReq = new ShowTestListRequest();
				showTestReq.setCommandName("SHOW_QUESTION");
				System.out.println("Print the name of Test Subject");
				subjectId = in.nextLine();
				showTestReq.setSubjectId(subjectId.toUpperCase());
				Response showTestResponse = controller.doRequest(showTestReq);
				if (showTestResponse.isErrorStatus() == true) {
					System.out.println(showTestResponse.getErrorMessage());
				} else {
					System.out.println(showTestResponse.getResultMessage());
				}
				break;

			// CREATE_NEW_QUESTION
			case "6":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				} else if (GeneralInformation.getUserId() != 1) {
					System.out.println("Sorry but for looking the questions you need to be TUTOR");
					break;
				}
				SetNewQuestionRequest setNewQuestReq = new SetNewQuestionRequest();
				setNewQuestReq.setCommandName("CREATE_NEW_QUESTION");
				System.out.println("Please write the name of subject where you want to create a question");
				String subject = in.nextLine();
				System.out.println("Please write your question");
				String quest = in.nextLine();
				System.out.println("Please write the first option of answer");
				String first = in.nextLine();
				System.out.println("Please write the second option of answer");
				String second = in.nextLine();
				System.out.println("Please write the third option of answer");
				String third = in.nextLine();
				System.out.println("Please write the last option of answer");
				String last = in.nextLine();

				question = quest + "\n1. " + first + "\n2. " + second + "\n3. " + third + "\n4. " + last;

				System.out.println("Pls write a number of correct answer");
				answer = Integer.parseInt(in.nextLine());
				setNewQuestReq.setAnswer(answer);
				setNewQuestReq.setQuestion(question);
				setNewQuestReq.setSubjectId(subject);
				Response setNewQuestResponse = controller.doRequest(setNewQuestReq);
				if (setNewQuestResponse.isErrorStatus() == true) {
					System.out.println(setNewQuestResponse.getErrorMessage());
				} else {
					System.out.println(setNewQuestResponse.getResultMessage());
				}
				break;

			// CREATE_NEW_SUBJECT
			case "7":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				} else if (GeneralInformation.getUserId() != 1) {
					System.out.println("Sorry but for looking the questions you need to be TUTOR");
					break;
				}
				SetNewSubjectRequest setSubReq = new SetNewSubjectRequest();
				setSubReq.setCommandName("CREATE_NEW_SUBJECT");
				System.out.println("Pls write a subject name that you want to create");
				subjectName = in.nextLine();
				setSubReq.setSubjectName(subjectName.toUpperCase());
				Response setSubResponse = controller.doRequest(setSubReq);
				if (setSubResponse.isErrorStatus() == true) {
					System.out.println(setSubResponse.getErrorMessage());
				} else {
					System.out.println(setSubResponse.getResultMessage());
				}
				break;
default: 
		System.out.println("Вы ввели неверную команду. Было бы неплохо исправить ее на более корректную. Удачи!");

break;

			}

		}
	}
}
