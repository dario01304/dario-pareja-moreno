import com.example.insw.model.Article;
import com.example.insw.model.Order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        // Artículo 1: 2 unidades a 10.0€ cada una con 10% de descuento
        Article a1 = new Article("Libro", 2, 10.0, 10.0);
        // Artículo 2: 1 unidad a 50.0€ con 20% de descuento
        Article a2 = new Article("Auriculares", 1, 50.0, 20.0);

        List<Article> articles = Arrays.asList(a1, a2);
        order = new Order("ORD-001", articles);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("ORD-001", order.getId());
        assertEquals(2, order.getArticles().size());
    }

    @Test
    void testSetters() {
        order.setId("ORD-999");
        Article a = new Article("Teclado", 1, 30.0, 0.0);
        order.setArticles(Collections.singletonList(a));

        assertEquals("ORD-999", order.getId());
        assertEquals(1, order.getArticles().size());
    }

    @Test
    void testGetGrossTotal() {
        // Bruto a1 = 20.0; Bruto a2 = 50.0 -> Total = 70.0
        double grossTotal = order.getGrossTotal();
        assertEquals(70.0, grossTotal, 0.0001);
    }

    @Test
    void testGetDiscountedTotal() {
        // Con descuento a1 (10% sobre 20.0) = 18.0
        // Con descuento a2 (20% sobre 50.0) = 40.0
        // Total = 58.0
        double discountedTotal = order.getDiscountedTotal();
        assertEquals(58.0, discountedTotal, 0.0001);
    }

    @Test
    void testEmptyOrderTotals() {
        Order emptyOrder = new Order("ORD-EMPTY", Collections.emptyList());
        assertEquals(0.0, emptyOrder.getGrossTotal(), 0.0001);
        assertEquals(0.0, emptyOrder.getDiscountedTotal(), 0.0001);
    }

    @Test
    void testToString() {
        assertNotNull(order.toString());
    }

    @Test
    void testDefaultConstructor() {
        Order emptyOrder = new Order();
        assertNotNull(emptyOrder);
        assertNotNull(emptyOrder.getArticles());
    }

    @Test
    void testSetArticles() {
        Article extra = new Article("Cable HDMI", 1, 15.0, 0.0);
        order.setArticles(Collections.singletonList(extra));
        assertEquals(1, order.getArticles().size());
        assertEquals("Cable HDMI", order.getArticles().get(0).getName());
    }

    @Test
    void testConstructorVacio() {
        Order o = new Order();
        assertNotNull(o);
}
}