package ucu.edu.ua;

public class Main {

    public static void main(String[] args) {
        Document document = new SmartDocument();
        document = new CachedDocument(new TimedDocument(document));
        document.parse("path");

      //  String filePath = "a.jpg";  
      //  
      //  Document document2 = new SmartDocument();
      //  String parsedContent = document2.parse(filePath);
      //  
      //  System.out.println("Parsed Content: ");
      //  System.out.println(parsedContent);

    }

}
