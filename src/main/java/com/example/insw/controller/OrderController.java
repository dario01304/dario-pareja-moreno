package com.example.insw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.insw.Searcher;
import com.example.insw.model.Order;
import com.example.insw.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderView view;
    private List<Order> orders;
    private Searcher searcher;

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;
        this.searcher = new Searcher();

        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOrder();
            }
        });
    }

    private void searchOrder() {
        String id = view.getSearchId();
        log.debug("Searching order with ID: {}", id);

        Order foundOrder = searcher.searchOrderById(orders, id);

        if (foundOrder != null) {
            double rate = 1.162976;
            view.displayOrder(foundOrder, rate);
        } else {
            view.displayOrder(null, 1.0);
        }
    }
}
