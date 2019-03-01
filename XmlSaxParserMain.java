import java.io.FileOutputStream;

public class XmlSaxParserMain {
    public static void main(String[] args) {
    	
    	XMLSAXParser parser = new XMLSAXParser("C:\\example\\File\\test.xml");
    	
    	try {
    		
    		parser.parse();
    		FileOutputStream output = new FileOutputStream("C:\\example\\File\\testtttttt.xml");
    		String t = "";
            for(int i=0; i<parser.list.size(); i++){
            	
            	t = parser.list.get(i).systemName + "\t" + parser.list.get(i).categoryName + "\t" + parser.list.get(i).btnName + "\t" +parser.list.get(i).training;
            	output.write(t.getBytes());
            	output.write(13);
            	
            }
    		
    	} catch(Exception e) {
    		
    		System.out.println("parser.parse() Exception >> " + e.toString());
    		
    	}
    	
    }
}
