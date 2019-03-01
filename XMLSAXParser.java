import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;




public class XMLSAXParser extends DefaultHandler {

	// SAXParserFactory
	private SAXParserFactory parserFactory;
	
	// SAXParser
	private SAXParser parser;
	
	// xmlファイル名
	private String fileName;
	
	// 始まりのタッグ名
	private String startTagName;
	
	// 終わりのタッグ名
	private String endTagName;
	
	// String buffer
	private StringBuffer buffer = new StringBuffer();
	
	// output VO
	private XMLOutputVO vo;
	
	// output List
	public ArrayList<XMLOutputVO> list = new ArrayList<>();
	
	private String systemName = "";
	private String categoryName = "";
	private String btnName = "";
	private String chkTrainingLaunch = "";
	
	// コンストラクター
	public XMLSAXParser() {}
	
	public XMLSAXParser(String fileName) {
		super();
		
		try {
			
			parserFactory = SAXParserFactory.newInstance();
			parser = parserFactory.newSAXParser();
			
		} catch(Exception e){
			
			System.out.println("Exception >> " + e.toString());
		
		}
		
		this.fileName = fileName;
		
	}
	
	// 始まりのタッグを認識した時のイベント
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		if (qName.equals("system")) {
			systemName = attributes.getValue("name");
		} else if (qName.equals("category")) {
			categoryName = attributes.getValue("name");
		} else if (qName.equals("app")) {
			btnName = attributes.getValue("btnName");
			if (attributes.getValue("trainingLaunch") != null) {
				chkTrainingLaunch = attributes.getValue("trainingLaunch");
			} else {
				chkTrainingLaunch = "---";
			}
			vo = new XMLOutputVO(systemName, categoryName, btnName, chkTrainingLaunch);
			list.add(vo);
		}
	}
	
	// 終わりのタッグを認識した時のイベント
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		
		if (qName.equals("system")) {
			System.out.println(qName);
		}
	}
	
	// parse
	public void parse() {
		
		try {
			
			parser.parse(fileName, this);
			
		} catch(Exception e) {
			
			System.out.println("XMLSAXParser Exception " + e.toString());
			e.printStackTrace();
				
		}
	}
}
