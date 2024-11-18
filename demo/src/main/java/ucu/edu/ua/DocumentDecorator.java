package ucu.edu.ua;

//public abstract class DocumentDecorator extends Document{
//    private Document document;
//
//    public DocumentDecorator(Document document){
//        this.document = document;
//
//    }
//
//    @Override
//    public String parse(String path){
//        return document.parse(path);
//    }
//
//
//}
//

//package ua.edu.ucu.apps.documents;

public class DocumentDecorator implements Document{
    private Document document;
    public DocumentDecorator(Document doc) {
        this.document = doc;
    }
    @Override
    public String parse(String path) {
        return document.parse(path);
    }
}