package com.xl.xmlreader;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		TextView textView = (TextView) findViewById(R.id.text_main);
		String xmlData ="";
		
		String text = 
			"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
			+"<LinearLayout\n"
			+"\txmlns:android=\"http://schemas.android.com/apk/res/android\"\n"
			+"\tlayout_width=\"fill_parent\"\n"
			+"\tandroid:layout_height=\"fill_parent\"\n"
			+"\tandroid:gravity=\"center\"\n"
			+"\tandroid:orientation=\"vertical\">\n"
			+"\n"
			+"\t<TextView\n"
			+"\t\tandroid:layout_width=\"wrap_content\"\n"
			+"\t\tandroid:layout_height=\"wrap_content\"\n"
			+"\t\tandroid:text=\"@string/hello\"\n"
			+"\t\tandroid:id=\"@+id/text_main\"/>\n"
			+"\n风的影子"
			+"\t<TextView\n"
			+"\t\tandroid:layout_width=\"wrap_content\"\n"
			+"\t\tandroid:layout_height=\"wrap_content\"\n"
			+"\t\tandroid:text=\"@string/hello\"\n"
			+"\t\tandroid:id=\"@+id/text_main\"/>\n"
			
			+"</LinearLayout>\n";
		
		PullXml pull = new PullXml();
		pull.parseXMLWithPull(text);
		textView.setText(pull.toString());
		
		
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
        try
		{
			XMLReader reader = factory.newSAXParser().getXMLReader();
			
			ContentHandler handler = new ContentHandler();
			//将contentHandler的实例设置到XMLReader中
			reader.setContentHandler(handler);
			//开始解析
			try
			{
				reader.parse(new InputSource(new StringReader(text)));
				
				textView.setText(handler.toString());
				
			}
			catch (IOException e)
			{}
			catch (SAXException e)
			{}
			
		}
		catch (ParserConfigurationException e)
		{}
		catch (SAXException e)
		{}

		

		DomPersonService person = new DomPersonService();
		try
		{
			person.readXML(text);
		}
		catch (Throwable e)
		{
			textView.setText(e.toString());
		}

		textView.setText(person.toString());
		
    }
}
