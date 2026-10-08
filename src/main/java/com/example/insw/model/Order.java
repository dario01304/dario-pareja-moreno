package com.example.insw.model;

import com.example.insw.Calculator;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private String id;
    private List<Article> articles;
    private final Calculator calculator = new Calculator();

    public Order() {
        this.articles = new ArrayList<>();
    }

    public Order(String id, List<Article> articles) {
        this.id = id;
        this.articles = (articles != null) ? articles : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = (articles != null) ? articles : new ArrayList<>();
    }

    public double getGrossTotal() {
        List<Double> amounts = new ArrayList<>();
        for (Article article : articles) {
            amounts.add(article.getGrossAmount());
        }
        return calculator.calculateTotal(amounts);
    }

    public double getDiscountedTotal() {
        List<Double> amounts = new ArrayList<>();
        for (Article article : articles) {
            amounts.add(article.getDiscountedAmount());
        }
        return calculator.calculateTotal(amounts);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", articles=" + articles +
                '}';
    }
}
