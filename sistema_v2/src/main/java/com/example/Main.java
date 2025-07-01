package com.example;

import com.example.view.ArticleView;
import com.example.controller.ArticleController;

public class Main {
    public static void main(String[] args) {
        try {
            ArticleView view = new ArticleView();
            new ArticleController(view);  // Initialize the controller
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}