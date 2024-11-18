package ucu.edu.ua;

import java.sql.*;

public class CachedDocument extends DocumentDecorator {
    private static final String DB_URL = "jdbc:sqlite:db";
    public CachedDocument(Document document) {
        super(document);
    }
    @Override
    public String parse(String path) {
        String query = "SELECT parsed_string FROM cache WHERE path = ?";
        String insertSQL = "INSERT OR REPLACE INTO cache (path, parsed_string) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement selectStmt = conn.prepareStatement(query);
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
            selectStmt.setString(1, path);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                System.out.println("Already cached!!!");
                return rs.getString("parsed_string");
            }
            String parsedContent = super.parse(path);
            insertStmt.setString(1, path);
            insertStmt.setString(2, parsedContent);
            insertStmt.executeUpdate();
            return parsedContent;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return super.parse(path);
    }
}
//
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class CachedDocument extends DocumentDecorator {
//    public CachedDocument(Document document){
//        super(document);
//    }
//
//    public String parse(String path){
//        String result
//        try (Connection conn =  DriverManager.getConnection("jdbc:sqlite:db.sqlite")) {
//            String sql = "SELECT parsed_string FROM files WHERE path =?";
//            try PreparedStatement pstmt = conn.prepareStatement(sql){
//                pstmt.setString(1, path);
//                R
//            }
//        }
//        //check if path exists in table of db sqlite, if so return value from color parsed+string
//        // otherwise return result
//        String result = super.parse(path);
//        return result;
//    }
//}
//