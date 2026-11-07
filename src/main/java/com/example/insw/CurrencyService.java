package com.example.insw;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Servicio para consultar el tipo de cambio EUR/USD en tiempo real 
 * mediante la API pública de Open Exchange Rates.
 */
public class CurrencyService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);
    private static final String API_URL = "https://open.er-api.com/v6/latest/EUR";
    private static final double FALLBACK_RATE = 1.08; // Valor de respaldo por si falla la conexión

    public static double getEurToUsdRate() {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.body());
                double rate = root.path("rates").path("USD").asDouble(FALLBACK_RATE);
                log.debug("Tipo de cambio EUR/USD obtenido vía API: {}", rate);
                return rate;
            }
        } catch (Exception e) {
            log.error("Error al consultar el servicio de cambio de divisas. Usando valor por defecto.", e);
        }
        return FALLBACK_RATE;
    }
}