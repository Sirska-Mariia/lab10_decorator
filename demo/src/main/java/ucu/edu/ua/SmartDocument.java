package ucu.edu.ua;

//import com.google;
////
//import com.google.cloud; //.vision.v1.AnnotateImageRequest;
////import com.google.cloud.vision.v1.AnnotateImageResponse;
////import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
////import com.google.cloud.vision.v1.Feature;
////import com.google.cloud.vision.v1.Feature.Type;
////import com.google.cloud.vision.v1.Image;
////import com.google.cloud.vision.v1.ImageAnnotatorClient;
////import com.google.cloud.vision.v1.ImageSource;
////import com.google.cloud.vision.v1.TextAnnotation;
//import lombok.AllArgsConstructor;
//import lombok.SneakyThrows;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@AllArgsConstructor
//public class SmartDocument implements Document {
//    public String gcsPath;
//
//    @SneakyThrows
//    public String parse() {
//        List<AnnotateImageRequest> requests = new ArrayList<>();
//
//        ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
//        Image img = Image.newBuilder().setSource(imgSource).build();
//        Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
//        AnnotateImageRequest request =
//                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
//        requests.add(request);
//
//        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
//            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
//            List<AnnotateImageResponse> responses = response.getResponsesList();
//            client.close();
//
//            for (AnnotateImageResponse res : responses) {
//                TextAnnotation annotation = res.getFullTextAnnotation();
//                return annotation.getText();
//            }
//        }
//        return "";
//    }
//
//    @Override
//    public String parse(String pathпі) {
//      
//        throw new UnsupportedOperationException("Unimplemented method 'parse'");
//    }
//}

//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class SmartDocument implements Document {
//    
//    @Override
//    public String parse(String path) {
//        try {
//            String content = new String(Files.readAllBytes(Paths.get(path)));
//            System.out.println("Parsing document at path: " + path);
//            return content;
//        } catch (IOException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
//        return "";
//    }
//}
//
import net.sourceforge.tess4j.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartDocument implements Document {

    public SmartDocument(String string) {
        //TODO Auto-generated constructor stub
    }

    public SmartDocument() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String parse(String path) {
        File file = new File(path);
        if (file.exists() && isImageFile(file)) {
            try {
                Tesseract tesseract = new Tesseract();
                tesseract.setLanguage("eng");
                return tesseract.doOCR(file);
            } catch (TesseractException e) {
                return "Error processing image: " + e.getMessage();
            }
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            return content;
        } catch (Exception e) {
            return "Error reading file: " + e.getMessage();
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".tiff");
    }
}
