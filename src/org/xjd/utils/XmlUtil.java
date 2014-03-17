package org.xjd.utils;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

public class XmlUtil {

	
	private void parseMessage(String messageXml) {
		  try {
		    // parse the XML document into a DOM
		    Document messageDom = XMLParser.parse(messageXml);

		    // find the sender's display name in an attribute of the <from> tag
		    com.google.gwt.xml.client.Node fromNode = messageDom.getElementsByTagName("from").item(0);
		    String from = ((Element)fromNode).getAttribute("displayName");
//		    fromLabel.setText(from);
//
//		    // get the subject using Node's getNodeValue() function
//		    String subject = messageDom.getElementsByTagName("subject").item(0).getFirstChild().getNodeValue();
//		    subjectLabel.setText(subject);
//
//		    // get the message body by explicitly casting to a Text node
//		    Text bodyNode = (Text)messageDom.getElementsByTagName("body").item(0).getFirstChild();
//		    String body = bodyNode.getData();
//		    bodyLabel.setText(body);

		  } catch (Exception e) {
//		    Window.alert("Could not parse XML document.");
		  }
		}
}
