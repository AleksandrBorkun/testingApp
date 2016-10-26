package by.epam.lab.testing.test.unit;

import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class XMLPool {
	
	public static NodeList getTests() throws SAXException, IOException{
		
		DOMParser parser = new DOMParser();
		parser.parse("../testing/res/storage.xml");
		Document document = parser.getDocument();
		Element root = document.getDocumentElement();
		NodeList tests = root.getElementsByTagName("test");
		return tests;
		
	}

}
