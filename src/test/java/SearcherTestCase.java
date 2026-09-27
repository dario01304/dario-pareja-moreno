
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.example.insw.Searcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SearcherTestCase {

    private Searcher searcher;
    private List<String> sampleList;

    @BeforeEach
    void setUp() {
        // Setup searcher y datos de prueba
        searcher = new Searcher();
        sampleList = Arrays.asList("hola mundo", "java", "ingenieria", "software", "desarrollo");
    }

    // ==========================================
    // Pruebas para searchWord
    // ==========================================

    @Test
    void testSearchWordExists() {
        // La palabra existe en la lista
        boolean result = searcher.searchWord("java", sampleList);
        assertTrue(result);
    }

    @Test
    void testSearchWordDoesNotExist() {
        // La palabra no existe en la lista
        boolean result = searcher.searchWord("python", sampleList);
        assertFalse(result);
    }

    // ==========================================
    // Pruebas para getWordByIndex
    // ==========================================

    @Test
    void testGetWordByIndexValid() {
        // Un índice válido devuelve la palabra correcta
        String resultFirst = searcher.getWordByIndex(sampleList, 0);
        assertEquals("hola mundo", resultFirst);

        String resultMiddle = searcher.getWordByIndex(sampleList, 2);
        assertEquals("ingenieria", resultMiddle);
    }

    @Test
    void testGetWordByIndexNegative() {
        // Un índice negativo devuelve null
        String result = searcher.getWordByIndex(sampleList, -1);
        assertNull(result);
    }

    @Test
    void testGetWordByIndexTooLarge() {
        // Un índice demasiado grande devuelve null
        String resultExactBoundary = searcher.getWordByIndex(sampleList, sampleList.size());
        assertNull(resultExactBoundary);

        String resultWayTooLarge = searcher.getWordByIndex(sampleList, 100);
        assertNull(resultWayTooLarge);
    }

    // ==========================================
    // Pruebas para searchByPrefix
    // ==========================================

    @Test
    void testSearchByPrefixMatches() {
        // Se devuelven las palabras que empiezan con el prefijo
        List<String> words = Arrays.asList("casa", "carro", "camino", "perro");
        List<String> result = searcher.searchByPrefix("ca", words);

        assertEquals(3, result.size());
        assertEquals(Arrays.asList("casa", "carro", "camino"), result);
    }

    @Test
    void testSearchByPrefixNoMatches() {
        // No se incluyen palabras que no empiecen con el prefijo
        List<String> result = searcher.searchByPrefix("xyz", sampleList);
        assertTrue(result.isEmpty());
    }

    // ==========================================
    // Pruebas para filterByKeyword
    // ==========================================

    @Test
    void testFilterByKeywordMatches() {
        // Se devuelven todos los elementos que contienen la palabra clave
        List<String> words = Arrays.asList("programador", "gramatica", "desarrollo", "diagrama");
        List<String> result = searcher.filterByKeyword("grama", words);

        assertEquals(3, result.size());
        assertTrue(result.contains("programador"));
        assertTrue(result.contains("diagrama"));
        assertFalse(result.contains("desarrollo"));
    }

    @Test
    void testFilterByKeywordNoMatches() {
        // No se devuelve ninguno si la palabra clave no existe
        List<String> result = searcher.filterByKeyword("inexistente", sampleList);
        assertEquals(0, result.size());
    }

    // ==========================================
    // Pruebas para searchExactPhrase (Avanzado)
    // ==========================================

    @Test
    void testSearchExactPhraseFirstElement() {
        // La frase es el primer elemento de la lista (este caso pasaba en el código original)
        boolean result = searcher.searchExactPhrase("hola mundo", sampleList);
        assertTrue(result);
    }

    @Test
    void testSearchExactPhraseNotFirstElement() {
        // La frase buscada NO es el primer elemento de la lista (descubría el bug original)
        boolean result = searcher.searchExactPhrase("software", sampleList);
        assertTrue(result);
    }

    @Test
    void testSearchExactPhraseDoesNotExist() {
        // La frase no existe en la lista
        boolean result = searcher.searchExactPhrase("frase inexistente", sampleList);
        assertFalse(result);
    }

    @Test
    void testSearchExactPhraseEmptyList() {
        // Caso límite: lista vacía
        List<String> emptyList = Collections.emptyList();
        boolean result = searcher.searchExactPhrase("cualquier cosa", emptyList);
        assertFalse(result);
    }
}
