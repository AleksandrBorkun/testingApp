package by.epam.lab.testing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.service.TestAppService;
import by.epam.lab.testing.service.exception.ServiceException;

public class TestAppServiceImpl implements TestAppService {
	List<String> list = new ArrayList<>();
	List<Integer> answer = new ArrayList<>();
	List<Integer> chek = new ArrayList<Integer>();
	Scanner in = new Scanner(System.in);

	@Override
	public boolean showTestList(String subjectId) throws ServiceException {
		if (subjectId == null) {
			throw new ServiceException("Please write a correct name of subject");
		}
		else if(subjectId.equals("") ||subjectId.equals(" ")){
			throw new ServiceException("Please write a correct name of subject");
			
		}
		list.clear();
		try {
			list = DAOFactory.getInstance().getTestApp().getTestListBySubject(subjectId);
			for (String quest : list) {
				System.out.println(quest);
				System.out.println();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}

		if (list != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean showSubject() throws ServiceException {
		
		list.clear();
		try {
			list = DAOFactory.getInstance().getTestApp().getSubjectList();
			for (String sub : list) {
				System.out.println(sub + ". You can call it by name ");
				System.out.println();
			}
			if (list != null) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean goTesting(String subjectId) throws ServiceException {

		int choise;

		answer.clear();
		list.clear();
		try {

			answer = DAOFactory.getInstance().getTestApp().chekTestResult(subjectId);
			list = DAOFactory.getInstance().getTestApp().getTestListBySubject(subjectId);

		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}

		for (int i = 0; i < answer.size(); i++) {
			System.out.println(list.get(i));
			System.out.println();
			System.out.println("Make your choose");
			choise = in.nextInt();
			chek.add(choise);
		}

		int point = 0;
		double mark = 0;

		for (int i = 0; i < answer.size(); i++) {
			if (answer.get(i) == chek.get(i)) {
				point++;
			}
		}
		mark = (double) point / answer.size() * 100;
		System.out
				.println("You have " + point + " from " + answer.size() + ". And your mark is " + mark + " from 100.0");

		return true;
	}

}
