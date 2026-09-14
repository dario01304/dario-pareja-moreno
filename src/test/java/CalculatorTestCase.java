

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.example.insw.Calculator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTestCase {

        private Calculator calculator;

        @BeforeEach
        void setUp() {
            // Setup calculator
            calculator = new Calculator();
        }

        @Test 
        void testMultiply() {
            int result = calculator.multiply(3, 4);
            assertEquals(12, result);
        }

        @Test
        void testConcatNull() {
            String result = calculator.concat(null, "hola");
            assertEquals(Calculator.EMPTY, result);
        }
}
