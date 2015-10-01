package com.lbschina.srt.update;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseXmlService {
	
	public HashMap<String, String> parseXml(File xmlFile)
			throws Exception {
		
		HashMap<String, String> hashMap = new HashMap<String, String>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		Element root = document.getDocumentElement();
		NodeList childNodes = root.getChildNodes();
		
		for (int j = 0; j < childNodes.getLength(); j++) {
			
			Node childNode = (Node) childNodes.item(j);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) childNode;
				if ("version".equals(childElement.getNodeName())) {
					hashMap.put("version", childElement.getFirstChild().getNodeValue());
				}else if (("name".equals(childElement.getNodeName()))) {
					hashMap.put("name", childElement.getFirstChild().getNodeValue());
				}else if (("url".equals(childElement.getNodeName()))) {
					hashMap.put("url", childElement.getFirstChild().getNodeValue());
				}
			}
		}
		
		return hashMap;
	}
}