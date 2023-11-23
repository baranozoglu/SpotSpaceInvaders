package reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReaderWrapper {
    List<String> readAllLines(Path path) throws IOException;
}
