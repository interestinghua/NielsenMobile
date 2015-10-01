package com.lbschina.srt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.annotation.SuppressLint;
import android.os.Environment;

public class XmlCreateUtil {

	private Document document;

	public void init() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			this.document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressLint("NewApi")
	public void createXml(String serialNo,String rsltNo) {

		String sdpath = Environment.getExternalStorageDirectory() + "/";
		String mSavePath = sdpath + "mobilemap/validate.xml";
		
		Element root = this.document.createElement("validate");
		this.document.appendChild(root);
		
		Element SerialNo = this.document.createElement("serialno");
		Element RsltNo = this.document.createElement("rsltno");
		
		SerialNo.appendChild(this.document.createTextNode(serialNo));
		RsltNo.appendChild(this.document.createTextNode(rsltNo));
		
		root.appendChild(SerialNo);
		root.appendChild(RsltNo);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			PrintWriter pw = new PrintWriter(new FileOutputStream(mSavePath));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (TransformerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public HashMap<String, String> parserXml(String fileName) {
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(fileName));

    		Element root = document.getDocumentElement();
    		NodeList childNodes = root.getChildNodes();
    		for (int j = 0; j < childNodes.getLength(); j++) {
    			Node childNode = (Node) childNodes.item(j);
    			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
    				
    				Element childElement = (Element) childNode;
    				if ("serialno".equals(childElement.getNodeName())) {
    					hashMap.put("serialno", childElement.getFirstChild().getNodeValue());
    				}else if (("rsltno".equals(childElement.getNodeName()))) {
    					hashMap.put("rsltno", childElement.getFirstChild().getNodeValue());
    				}
    			}
    		}
    		return hashMap;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
