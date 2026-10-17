package com.example.insw;

import com.example.insw.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("orders.json")) {
            List<Order> orders = mapper.readValue(inputStream, new TypeReference<List<Order>>() {});

            for (Order order : orders) {
                log.debug("Loaded order: {}", order.getId());
            }
        }
    }
}