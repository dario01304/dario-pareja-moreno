import com.example.insw.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainTest {

    @Test
    void testMain() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new Main());
    }
}