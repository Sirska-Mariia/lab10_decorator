import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ucu.edu.ua.Document;
import ucu.edu.ua.TimedDocument;

class DocumentTest {

    private TimedDocument timedDocument;

    @BeforeEach
    void setUp() {
        Document mockDocument = new Document() {
            @Override
            public String parse(String path) {
                return "parsed content";
            }
        };
        timedDocument = new TimedDocument(mockDocument);
    }

    @Test
    void testTimedDocumentParse() {
        long startTime = System.currentTimeMillis();

        String parsedContent = timedDocument.parse("path_to_document");

        assertNotNull(parsedContent);
        assertEquals("parsed content", parsedContent);

        long elapsedTime = System.currentTimeMillis() - startTime;
        assertTrue(elapsedTime >= 0);
    }
}
