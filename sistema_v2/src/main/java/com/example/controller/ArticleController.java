package com.example.controller;

import com.example.model.Article;
import com.example.model.ArticleDAO;
import com.example.view.ArticleView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ArticleController {
    private final ArticleView view;
    private final ArticleDAO articleDAO;

    public ArticleController(ArticleView view) {
        this.view = view;
        this.articleDAO = new ArticleDAO();
        initController();
    }

    private void initController() {
        // Load initial data
        loadArticlesTable();

        // Add button listeners
        view.getBtnInsert().addActionListener(e -> insertArticle());
        view.getBtnUpdate().addActionListener(e -> updateArticle());
        view.getBtnDelete().addActionListener(e -> deleteArticle());
        view.getBtnClear().addActionListener(e -> clearFields());
        view.getBtnExit().addActionListener(e -> System.exit(0));

        // Add table selection listener
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1) {
                    loadArticleToFields(row);
                }
            }
        });

        // Add filter listener (on Enter)
        view.getTxtFilter().addActionListener(e -> filterArticles());
        // Add filter listener (on typing)
        view.getTxtFilter().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterArticles();
            }
        });
    }

    private void loadArticlesTable() {
        List<Article> articles = articleDAO.getAllArticles();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0); // Clear existing rows

        for (Article article : articles) {
            model.addRow(new Object[]{
                article.getId(),
                article.getName(),
                article.getBrand(),
                article.getPrice(),
                article.getStock()
            });
        }
    }

    private void insertArticle() {
        try {
            Article article = getArticleFromFields();
            if (articleDAO.insertArticle(article)) {
                JOptionPane.showMessageDialog(view, "Artículo insertado correctamente");
                loadArticlesTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(view, "Error al insertar el artículo");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor, ingrese valores válidos");
        }
    }

    private void updateArticle() {
        try {
            Article article = getArticleFromFields();
            if (articleDAO.updateArticle(article)) {
                JOptionPane.showMessageDialog(view, "Artículo actualizado correctamente");
                loadArticlesTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(view, "Error al actualizar el artículo");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor, ingrese valores válidos");
        }
    }

    private void deleteArticle() {
        try {
            int id = Integer.parseInt(view.getTxtId().getText());
            int confirm = JOptionPane.showConfirmDialog(view,
                "¿Está seguro de eliminar este artículo?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (articleDAO.deleteArticle(id)) {
                    JOptionPane.showMessageDialog(view, "Artículo eliminado correctamente");
                    loadArticlesTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(view, "Error al eliminar el artículo");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor, seleccione un artículo válido");
        }
    }

    private void clearFields() {
        view.getTxtId().setText("");
        view.getTxtName().setText("");
        view.getTxtBrand().setText("");
        view.getTxtPrice().setText("");
        view.getTxtStock().setText("");
        view.getTxtFilter().setText("");
        view.getTable().clearSelection();
    }

    private void filterArticles() {
        String filter = view.getTxtFilter().getText().trim();
        String selectedField = view.getCboFilter().getSelectedItem().toString();
        if (!filter.isEmpty()) {
            List<Article> filteredArticles = articleDAO.searchArticlesByField(selectedField, filter);
            DefaultTableModel model = view.getTableModel();
            model.setRowCount(0);
            for (Article article : filteredArticles) {
                model.addRow(new Object[]{
                    article.getId(),
                    article.getName(),
                    article.getBrand(),
                    article.getPrice(),
                    article.getStock()
                });
            }
        } else {
            loadArticlesTable();
        }
    }

    private Article getArticleFromFields() throws NumberFormatException {
        Article article = new Article();
        article.setId(Integer.parseInt(view.getTxtId().getText()));
        article.setName(view.getTxtName().getText());
        article.setBrand(view.getTxtBrand().getText());
        article.setPrice(Double.parseDouble(view.getTxtPrice().getText()));
        article.setStock(Integer.parseInt(view.getTxtStock().getText()));
        return article;
    }

    private void loadArticleToFields(int row) {
        view.getTxtId().setText(view.getTable().getValueAt(row, 0).toString());
        view.getTxtName().setText(view.getTable().getValueAt(row, 1).toString());
        view.getTxtBrand().setText(view.getTable().getValueAt(row, 2).toString());
        view.getTxtPrice().setText(view.getTable().getValueAt(row, 3).toString());
        view.getTxtStock().setText(view.getTable().getValueAt(row, 4).toString());
    }
}
