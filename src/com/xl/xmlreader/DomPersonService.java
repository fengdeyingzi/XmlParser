package com.xl.xmlreader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;
import org.xml.sax.InputSource;
import java.io.StringReader;


public class DomPersonService {
	
	
	String log = "";
	
	String text = 
	"\n"
	+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
	+"<persons>\n"
	+"    <person id=\"23\">\n"
	+"        <name>xiaanming</name>\n"
	+"        <age>23</age>\n"
	+"    </person>\n"
	+"    <person id=\"20\">\n"
	+"        <name>liudehua</name>\n"
	+"        <age>28</age>\n"
	+"    </person>\n"
	+"</persons>\n";
	

	public void readXML(String text) throws Throwable{
		//获得android.xml文件的输入流
		InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("android.xml");
		//List<Person> persons = new ArrayList<Person>();

		//实例化DocumentBuilderFactory和DocumentBuilder，并创建Document
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(text)));
        
		//返回文档的根(root)元素
		Element rootElement =  document.getDocumentElement();

		//获取一个Note(DOM基本的数据类型)集合，这里有两个person Note
		NodeList nodes = null; //rootElement.getElementsByTagName("person");
        
		//获取第一层子元素notes
		nodes =  rootElement.getChildNodes();
		
		//遍历Note集合
		for(int i=0; i<nodes.getLength(); i++){
			log+="开始解析 "+i+"/"+nodes.getLength()+"\n";
			//先从第一个person元素开始解析
			Node node = (Node) nodes.item(i);
			log += node.getLocalName()+" "+node.getNodeName()+":"+node.getNodeValue()+"\n";
			
			//Person person = new Person();
			//person.setId(Integer.valueOf(personElement.getAttribute("id")));
           // log+= personElement.getLocalName()+ personElement.getNodeName()+":"+personElement.getNodeValue()+"\n";
			//获取person下面的name 和 age 的Note集合
			/*
			NodeList chileNodes = personElement.getChildNodes();
			for(int y=0; y<chileNodes.getLength(); y++){
				Node childNode = chileNodes.item(y);
log  += childNode.getLocalName();
				//判断子Note的类型为元素Note
				if(childNode.getNodeType() == Node.ELEMENT_NODE){
					/*
					Element childElement = (Element) childNode;
					if("name".equals(childElement.getNodeName())){
						person.setName(childElement.getFirstChild().getNodeValue());
					}else if("age".equals(childElement.getNodeName())){
						person.setAge(Integer.valueOf(childElement.getFirstChild().getNodeValue()));
					}
					
				}
			}
*/
			//Log.e("log", person.toString());

			//persons.add(person);
		}

		return ;

	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return log;
	}
	
	
	
}

