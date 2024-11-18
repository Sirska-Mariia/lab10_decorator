package ucu.edu.ua;

//public class TimeDocument  extends DocumentDecorator{
//    public TimeDocument(Document document(){
//        super(document);
//    })
//
//    public String parse(String path) {
//        return super.parse(path);
//    }
//}
//


//package com.example.lab10;

//public class TimeDocument extends DocumentDecorator{
//    public TimeDocument(Document document) {
//        super(document);
//    }
//
//    public String parse(String path) {
//        long start = System.nanoTime();
//
//        String result =  super.parse(path);
//
//        long end = System.nanoTime();
//        long duration = end - start;
//        System.out.println("Time taken in seconds: " + duration + " ns");
//        return result;
//    }
//}

//package ua.edu.ucu.apps.documents;

public class TimedDocument extends DocumentDecorator {
    public TimedDocument(Document document) {
        super(document);
    }
    @Override
    public String parse(String path){
        long startTime = System.nanoTime();
        String res = super.parse(path);
        double duration = (System.nanoTime() - startTime) / 1_000_000.0;
        System.out.println("Duration in seconds, "+ duration);
        return res;
    }
}