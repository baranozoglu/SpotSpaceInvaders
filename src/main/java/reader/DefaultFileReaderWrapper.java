package reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DefaultFileReaderWrapper implements FileReaderWrapper {
    @Override
    public List<String> readAllLines(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
