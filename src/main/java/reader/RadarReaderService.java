package reader;

import pattern.PatternEnum;
import pattern.PatternFactory;
import pattern.model.Pattern;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static properties.TextProperties.SEPARATOR;

public class RadarReaderService {
    private final PatternFactory patternFactory;
    private final DefaultFileReaderWrapper defaultFileReaderWrapper;

    public RadarReaderService(PatternFactory patternFactory, DefaultFileReaderWrapper defaultFileReaderWrapper) {
        this.patternFactory = patternFactory;
        this.defaultFileReaderWrapper = defaultFileReaderWrapper;
    }

    public List<Pattern> readRadarFile(PatternEnum patternEnum) {
        List<Pattern> patterns = new ArrayList<>();
        List<List<String>> linesList = readFromFile(patternEnum.getPath());

        for (int i = 0; i < linesList.size(); i++) {
            patterns.add(patternFactory.getPattern(
                    patternEnum,
                    (long) i + 1,
                    linesList.get(i))
            );
        }

        return patterns;
    }

    List<List<String>> readFromFile(String path) {
        List<List<String>> patternList = new ArrayList<>();
        try {
            List<String> allLines = defaultFileReaderWrapper.readAllLines(Paths.get(path));
            if (allLines.contains(SEPARATOR)) {
                enrichPatternList(allLines, patternList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patternList;
    }

    private void enrichPatternList(List<String> allLines, List<List<String>> patternList) {
        List<String> pattern = new ArrayList<>();
        boolean lookingForFirstSeperator = true;

        for (String line : allLines) {
            if (lookingForFirstSeperator) {
                if (SEPARATOR.equals(line)) {
                    lookingForFirstSeperator = false;
                }
            } else {
                if (SEPARATOR.equals(line)) {
                    patternList.add(pattern);
                    pattern = new ArrayList<>();
                    lookingForFirstSeperator = true;
                } else {
                    pattern.add(line);
                }
            }
        }
    }
}
