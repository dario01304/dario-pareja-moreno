
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.example.insw.Calculator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTestCase {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        // Setup calculator
        calculator = new Calculator();
    }

    // ==========================================
    // Pruebas para multiply
    // ==========================================

    @Test 
    void testMultiply() {
        // Multiplicación normal (ej. 2 * 3 = 6)
        int result = calculator.multiply(3, 4);
        assertEquals(12, result);
    }

    @Test
    void testMultiplyWithZero() {
        // Multiplicación con cero
        int result = calculator.multiply(0, 5);
        assertEquals(0, result);

        int result2 = calculator.multiply(5, 0);
        assertEquals(0, result2);
    }

    @Test
    void testMultiplyWithNegatives() {
        // Multiplicación con números negativos
        int resultNegative = calculator.multiply(-3, 4);
        assertEquals(-12, resultNegative);

        int resultBothNegative = calculator.multiply(-3, -4);
        assertEquals(12, resultBothNegative);
    }

    // ==========================================
    // Pruebas para concat
    // ==========================================

    @Test
    void testConcatNormal() {
        // Concatenación de dos cadenas normales
        String result = calculator.concat("Hola", "Mundo");
        assertEquals("HolaMundo", result);
    }

    @Test
    void testConcatNull() {
        // Uno de los parámetros es null -> debe devolver "empty"
        String result1 = calculator.concat(null, "hola");
        assertEquals(Calculator.EMPTY, result1);

        String result2 = calculator.concat("hola", null);
        assertEquals(Calculator.EMPTY, result2);

        String result3 = calculator.concat(null, null);
        assertEquals(Calculator.EMPTY, result3);
    }

    // ==========================================
    // Pruebas para sum
    // ==========================================

    @Test
    void testSumNormal() {
        // Suma normal (usamos delta 0.0001 para comparar doubles)
        double result = calculator.sum(10.5, 5.2);
        assertEquals(15.7, result, 0.0001);
    }

    @Test
    void testSumWithNegatives() {
        // Suma con valores negativos
        double result = calculator.sum(5.0, -3.0);
        assertEquals(2.0, result, 0.0001);

        double resultBothNegative = calculator.sum(-4.0, -2.5);
        assertEquals(-6.5, resultBothNegative, 0.0001);
    }

    // ==========================================
    // Pruebas para discount
    // ==========================================

    @Test
    void testDiscountValid() {
        // Se aplica correctamente un descuento válido (100 con 20% = 80.0)
        double result = calculator.discount(100.0, 20.0);
        assertEquals(80.0, result, 0.0001);
    }

    @Test
    void testDiscountBoundaries() {
        // Descuentos del 0% y del 100%
        double resultZero = calculator.discount(100.0, 0.0);
        assertEquals(100.0, resultZero, 0.0001);

        double resultFull = calculator.discount(100.0, 100.0);
        assertEquals(0.0, resultFull, 0.0001);
    }

    @Test
    void testDiscountInvalidPercentageThrows() {
        // Porcentaje inválido (< 0 o > 100) -> lanza IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.discount(100.0, -5.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.discount(100.0, 105.0);
        });
    }

    // ==========================================
    // Pruebas para calculateTotal
    // ==========================================

    @Test
    void testCalculateTotal() {
        // Una lista de importes devuelve la suma correcta
        List<Double> amounts = Arrays.asList(10.0, 20.5, 4.5);
        double total = calculator.calculateTotal(amounts);
        assertEquals(35.0, total, 0.0001);
    }

    @Test
    void testCalculateTotalEmptyList() {
        // Una lista vacía devuelve 0.0
        List<Double> emptyAmounts = Collections.emptyList();
        double total = calculator.calculateTotal(emptyAmounts);
        assertEquals(0.0, total, 0.0001);
    }
}