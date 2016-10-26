package by.epam.lab.testing.test.unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestsDataStorage {
//	List<Storage> storage;
	NodeList tests;

	public List<Storage> authorization() throws SAXException, IOException {

		List<Storage> expected = new ArrayList<Storage>();
		NodeList oneData = null;
		
		tests = XMLPool.getTests();
		
		Storage temp = null;
		
		
		for (int i = 0; i < tests.getLength(); i++) {
			NodeList testData = null;
			Element testName = (Element) tests.item(i);
	//		 System.out.println("\n\t\t" + testName.getAttribute("testName") +
	//		"\n");
			oneData = testName.getElementsByTagName("data");
			for (int j = 0; j < oneData.getLength(); j++) {

				temp = new Storage();
				Element currentData = (Element) oneData.item(j);

				temp.setLogin(getSingleChild(currentData, "login").getTextContent().trim());
				temp.setPassword(getSingleChild(currentData, "password").getTextContent().trim());
				temp.setCommandName(getSingleChild(currentData, "CommandName").getTextContent().trim());
				expected.add(temp);
			}
		}
		return expected;

	}

	private static Element getSingleChild(Element element, String childName) {
		NodeList nList = element.getElementsByTagName(childName);
		Element child = (Element) nList.item(0);
		return child;
	}

	/*
	 * private static NodeList XMLPool() throws SAXException, IOException{
	 * 
	 * DOMParser parser = new DOMParser();
	 * parser.parse("../testing/res/storage.xml"); Document document =
	 * parser.getDocument(); Element root = document.getDocumentElement();
	 * 
	 * NodeList tests = root.getElementsByTagName("test"); return tests;
	 * 
	 * }
	 */
}
