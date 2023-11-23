package reader;

import pattern.PatternEnum;
import pattern.PatternFactory;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static properties.TextProperties.*;

public class ReaderService {
    private final PatternFactory patternFactory;
    private final DefaultFileReaderWrapper defaultFileReaderWrapper;

    public ReaderService(PatternFactory patternFactory, DefaultFileReaderWrapper defaultFileReaderWrapper) {
        this.patternFactory = patternFactory;
        this.defaultFileReaderWrapper = defaultFileReaderWrapper;
    }

    public List<RadarPattern> readRadarFile() {
        List<List<String>> linesList = readFromFile(RADARS_TXT);
        List<RadarPattern> radarPatterns = new ArrayList<>();
        for (int i = 0; i < linesList.size(); i++) {
            radarPatterns.add((RadarPattern) patternFactory.getPattern(
                    PatternEnum.RADAR,
                    (long) i + 1,
                    linesList.get(i)));
        }

        return radarPatterns;
    }

    public List<SpaceInvaderPattern> readSpaceInvaderFile() {
        List<List<String>> linesList = readFromFile(SPACE_INVADERS_TXT);
        List<SpaceInvaderPattern> spaceInvaderPatterns = new ArrayList<>();
        for (int i = 0; i < linesList.size(); i++) {
            spaceInvaderPatterns.add((SpaceInvaderPattern) patternFactory.getPattern(
                    PatternEnum.SPACE_INVADER,
                    (long) i + 1,
                    linesList.get(i)));
        }

        return spaceInvaderPatterns;
    }

    public List<List<String>> readFromFile(String path) {
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
