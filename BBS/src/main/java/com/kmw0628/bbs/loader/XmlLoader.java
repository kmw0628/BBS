package com.kmw0628.bbs.loader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlLoader extends DbInfo {
	public void XLoadDB(){ 
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String OS = System.getProperty("os.name").toLowerCase();
			String Pt = null;
			if(OS.indexOf("win") >= 0) {
				Pt="\\";
			}else{
				Pt="//";
			};
			System.out.println("Agremount bbs.cfg = " + System.getProperty("bbs.cfg"));
			String dbConf = System.getProperty("bbs.cfg")+Pt+"dbconf.xml";
			System.out.println("[bbs.loader.XmlLoader.XLoad][dbconf.xml][PATH : " + dbConf + "]");
			Document cfgPath = builder.parse(dbConf);
			Element root = cfgPath.getDocumentElement();
			Node firstNode = root.getFirstChild();
			Node bbsDbConn = firstNode.getNextSibling();
			NodeList childList = bbsDbConn.getChildNodes();
			for(int i = 0; i < childList.getLength(); i++) {
				Node item = childList.item(i);
				if (item.getNodeName() != "#text") {
					System.out.println("[bbs.loader.XmlLoader.XLoad][dbconf.xml][ChildList : "+i+"/"+childList.getLength()+"] [NodeName : "+item.getNodeName()+"] [NodeText : "+item.getTextContent() + " ]");
				}
				if(item.getNodeType() == Node.ELEMENT_NODE) { // 노드의 타입이 Element일 경우(공백이 아닌 경우)
					switch(item.getNodeName()) {
						case "driver" : 
							switch(item.getTextContent()) {
								case "oracle" : 
									setDriver("oracle.jdbc.driver.OracleDriver");
									break;
								case "postgres" :
									setDriver("org.postgresql.Driver");
									break;
								};
							break;
						case "url" : 
							setUrl(item.getTextContent());
							break;
						case "id" : 
							setId(item.getTextContent());
							break;
						case "pw" : 
							setPw(item.getTextContent());
							break;
						}
				} else {
				}
			}			  
			System.out.println("[bbs.loader.XmlLoader.XLoad][dbconf.xml][URL :" + getUrl() + "] [ID :" + getId() + "][PW :" + getPw() + "][Driver :" + getDriver()+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
