import com.example.insw.model.Article;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArticleTest {

    private Article article;

    @BeforeEach
    void setUp() {
        // 3 unidades a 10.0€ con 20% de descuento
        article = new Article("Ratón USB", 3, 10.0, 20.0);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Ratón USB", article.getName());
        assertEquals(3, article.getQuantity());
        assertEquals(10.0, article.getPrice(), 0.0001);
        assertEquals(20.0, article.getDiscount(), 0.0001);
    }

    @Test
    void testSetters() {
        article.setName("Teclado Mecánico");
        article.setQuantity(5);
        article.setPrice(25.0);
        article.setDiscount(15.0);

        assertEquals("Teclado Mecánico", article.getName());
        assertEquals(5, article.getQuantity());
        assertEquals(25.0, article.getPrice(), 0.0001);
        assertEquals(15.0, article.getDiscount(), 0.0001);
    }

    @Test
    void testGetGrossAmount() {
        // 3 unidades * 10.0 = 30.0
        double gross = article.getGrossAmount();
        assertEquals(30.0, gross, 0.0001);
    }

    @Test
    void testGetDiscountedAmount() {
        // 30.0 con 20% de descuento = 24.0
        double discounted = article.getDiscountedAmount();
        assertEquals(24.0, discounted, 0.0001);
    }

    @Test
    void testGetDiscountedAmountZeroDiscount() {
        article.setDiscount(0.0);
        // 30.0 con 0% de descuento = 30.0
        assertEquals(30.0, article.getDiscountedAmount(), 0.0001);
    }

    @Test
    void testGetDiscountedAmountFullDiscount() {
        article.setDiscount(100.0);
        // 30.0 con 100% de descuento = 0.0
        assertEquals(0.0, article.getDiscountedAmount(), 0.0001);
    }

    @Test
    void testGetDiscountedAmountInvalidDiscountThrows() {
        // Descuento negativo o mayor a 100 debe lanzar excepción
        article.setDiscount(-5.0);
        assertThrows(IllegalArgumentException.class, () -> {
            article.getDiscountedAmount();
        });

        article.setDiscount(110.0);
        assertThrows(IllegalArgumentException.class, () -> {
            article.getDiscountedAmount();
        });
    }

    @Test
    void testToString() {
        assertNotNull(article.toString());
    }

    @Test
    void testDefaultConstructor() {
        Article a = new Article();
        assertNotNull(a);
}
}