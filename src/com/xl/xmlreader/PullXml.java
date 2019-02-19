package com.xl.xmlreader;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlPullParser;
import java.io.StringReader;
import android.util.Log;
/*

pull方式解析xml
*/
public class PullXml
{
	public static String TAG = "PullXml";
	
	public String log="";
	
	//用Pull方式解析XML
	public  void parseXMLWithPull(String xmlData){
		try {
			XmlPullParserFactory  factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();
			//设置输入的内容
			xmlPullParser.setInput(new StringReader(xmlData));
			//获取当前解析事件，返回的是数字
			int eventType = xmlPullParser.getEventType();
			//保存内容
			String id = "";
			String name = "";
			String version="";

			while (eventType != (XmlPullParser.END_DOCUMENT)){
				String nodeName = xmlPullParser.getName();
				switch (eventType){
						//开始解析XML
					case XmlPullParser.START_TAG:{
						log+="解析到标签："+nodeName+"\n";
							//nextText()用于获取结点内的具体内容
							
							String temp = null;
							
							if("id".equals(nodeName))
								id = xmlPullParser.nextText();
							else if("name".equals(nodeName))
								name = xmlPullParser.nextText();
							else if("version".equals(nodeName))
								version = xmlPullParser.nextText();
						} break;
						//结束解析
					case XmlPullParser.END_TAG:{
						log+="标签结束："+nodeName+"\n";
							if("app".equals(nodeName)){
								Log.d(TAG, "parseXMLWithPull: id is "+ id);
								Log.d(TAG, "parseXMLWithPull: name is "+ name);
								Log.d(TAG, "parseXMLWithPull: version is "+ version);
							}
						} break;
					case xmlPullParser.TEXT:
						String text = xmlPullParser.getText();
						log+="解析到文本："+text+"\n";
						break;
					default: 
					log+=eventType+"\n";
					break;
				}
				//下一个
				eventType = xmlPullParser.nextToken();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return this.log;
	}
	
	
	
	
}
