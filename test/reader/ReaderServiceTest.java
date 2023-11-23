package reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pattern.PatternFactory;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static properties.TextProperties.SEPARATOR;

class ReaderServiceTest {

    private PatternFactory patternFactory;
    private ReaderService readerService;
    private DefaultFileReaderWrapper defaultFileReaderWrapper;

    @BeforeEach
    void setUp() {
        patternFactory = mock(PatternFactory.class);
        defaultFileReaderWrapper = mock(DefaultFileReaderWrapper.class);
        readerService = new ReaderService(patternFactory, defaultFileReaderWrapper);
    }

    @Test
    void testReadFromFile() throws Exception {
        // Mock data
        List<String> mockLines = Arrays.asList(SEPARATOR, "---oo---", "--------", SEPARATOR);
        List<String> expectedLines = Arrays.asList("---oo---", "--------");

        when(defaultFileReaderWrapper.readAllLines(Path.of("mockedFilePath"))).thenReturn(mockLines);

        // Test the method
        List<List<String>> result = readerService.readFromFile("mockedFilePath");

        // Assertions
        assertEquals(Collections.singletonList(expectedLines), result);
    }

    @Test
    void testReadFromFileWithoutSeparators() throws Exception {
        // Mock data
        List<String> mockLines = Arrays.asList("---oo---", "--------");

        when(defaultFileReaderWrapper.readAllLines(Path.of("mockedFilePath"))).thenReturn(mockLines);

        // Test the method
        List<List<String>> result = readerService.readFromFile("mockedFilePath");

        // Assertions
        assertEquals(List.of(), result);
    }

}