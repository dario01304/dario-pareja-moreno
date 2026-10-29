package com.example.insw.view;

import javax.swing.*;
import com.example.insw.model.Order;
import com.example.insw.model.Article;
import java.awt.*;

public class OrderView extends JFrame {

    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);

    public OrderView() {
        setTitle("Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // add new JLabel("Order ID:");
        add(new JLabel("Order ID:"));

        // add to view the search field
        add(searchField);

        // add to view the search button
        add(searchButton);

        // add to view the result area inside a JScrollPane
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        pack();
        setVisible(true);
    }

    public String getSearchId() {
        return searchField.getText().trim();
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void displayOrder(Order order, double rate) {
        if (order == null) {
            resultArea.setText("Order not found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(order.getId()).append(":\n");

        double grossTotal = 0.0;
        double discountedTotal = 0.0;

        if (order.getArticles() != null) {
            for (Article article : order.getArticles()) {
                double itemGross = article.getQuantity() * article.getPrice();
                double itemDiscounted = itemGross * (1.0 - (article.getDiscount() / 100.0));

                sb.append(article.getName())
                  .append(" x").append(article.getQuantity())
                  .append(" @ ").append(article.getPrice()).append("$")
                  .append(" (discount ").append(article.getDiscount()).append("%) = ")
                  .append(itemDiscounted).append("$\n");

                grossTotal += itemGross;
                discountedTotal += itemDiscounted;
            }
        }

        sb.append("Gross total: ").append(grossTotal).append("$\n");
        sb.append("Discounted total: ").append(discountedTotal).append("$\n");
        sb.append("Gross total (USD): ").append(grossTotal * rate).append("$\n");
        sb.append("Discounted total (USD): ").append(discountedTotal * rate).append("$");

        resultArea.setText(sb.toString());
    }
}
