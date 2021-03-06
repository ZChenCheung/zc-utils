package com.zc.util.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	private static DocumentBuilder documentBuilder;
	
	static {
		try {
			documentBuilder = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private XMLReader() {
	}

	public static Consumer<BiConsumer<Element, Integer>> parse(Element element) {
		NodeList childNodes = element.getChildNodes();

		return listener -> {
			for (int index = 0; index < childNodes.getLength(); index++) {
				Node node = childNodes.item(index);

				if (node instanceof Element) {
					listener.accept((Element) node, index);
				}
			}
		};
	}
	
	public static Consumer<BiConsumer<Element, Integer>> parse(Element element, String tagname) {
		NodeList nodeList = element.getElementsByTagName(tagname);
		
		return listener -> {
            for (int index = 0; index < nodeList.getLength(); index++) {
                Element ele = (Element) nodeList.item(index);
                listener.accept(ele, index);
            }
        };
	}
	
	public static Consumer<BiConsumer<Element, Integer>> parse(Document document, String tagname) {
		NodeList nodeList = document.getElementsByTagName(tagname);
		
        return listener -> {
            for (int index = 0; index < nodeList.getLength(); index++) {
                Element element = (Element) nodeList.item(index);

                listener.accept(element, index);
            }
        };
	}
	
	public static Document document(File path) {
		InputStream is = null;
		try {
			is = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return openDocument(is);
	}
	
	public static Document openDocument(String path) throws FileNotFoundException {
		InputStream is = XMLReader.class.getResourceAsStream(path);
		if (is == null) {
			throw new FileNotFoundException("File[" + path + "] not found!");
		}
		
		return openDocument(is);
	}
	
	public static Document openDocument(InputStream is) {
		Document document = null;
		try {
			document = documentBuilder.parse(is);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return document;
	}
	
}
