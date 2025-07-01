package com.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    private final DatabaseConnection dbConnection;

    public ArticleDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public boolean insertArticle(Article article) {
        String sql = "INSERT INTO Articulo (Nombre_Articulo, Marca, Precio, Stock) VALUES ( ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            //pstmt.setInt(1, article.getId());
            pstmt.setString(1, article.getName());
            pstmt.setString(2, article.getBrand());
            pstmt.setDouble(3, article.getPrice());
            pstmt.setInt(4, article.getStock());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateArticle(Article article) {
        String sql = "UPDATE Articulo SET Nombre_Articulo = ?, Marca = ?, Precio = ?, Stock = ? WHERE Id_Articulo = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, article.getName());
            pstmt.setString(2, article.getBrand());
            pstmt.setDouble(3, article.getPrice());
            pstmt.setInt(4, article.getStock());
            pstmt.setInt(5, article.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteArticle(int id) {
        String sql = "DELETE FROM Articulo WHERE Id_Articulo = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM Articulo";
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                articles.add(extractArticleFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public List<Article> searchArticlesByName(String name) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM Articulo WHERE Nombre_Articulo LIKE ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    articles.add(extractArticleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public Article getArticleById(int id) {
        String sql = "SELECT * FROM Articulo WHERE Id_Articulo = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractArticleFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Article> searchArticlesByField(String field, String value) {
        List<Article> articles = new ArrayList<>();
        String sql;
        boolean isNumeric = field.equalsIgnoreCase("ID_ARTICULO") || field.equalsIgnoreCase("PRECIO");
        if (isNumeric) {
            sql = "SELECT * FROM Articulo WHERE " + field + " = ?";
        } else {
            sql = "SELECT * FROM Articulo WHERE " + field + " LIKE ?";
        }
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (isNumeric) {
                if (field.equalsIgnoreCase("PRECIO")) {
                    pstmt.setDouble(1, Double.parseDouble(value));
                } else {
                    pstmt.setInt(1, Integer.parseInt(value));
                }
            } else {
                pstmt.setString(1, "%" + value + "%");
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    articles.add(extractArticleFromResultSet(rs));
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return articles;
    }

    private Article extractArticleFromResultSet(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("Id_Articulo"));
        article.setName(rs.getString("Nombre_Articulo"));
        article.setBrand(rs.getString("Marca"));
        article.setPrice(rs.getDouble("Precio"));
        article.setStock(rs.getInt("Stock"));
        return article;
    }
}
