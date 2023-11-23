package detector.strategy;

import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;

import java.util.ArrayList;
import java.util.List;

public class BruteForcePatternDetector implements PatternDetectorStrategy {
    @Override
    public List<RadarPattern> detectPatterns(List<RadarPattern> radarPatterns, List<SpaceInvaderPattern> spaceInvaderPatterns) {
        if (radarPatterns.isEmpty() || spaceInvaderPatterns.isEmpty()) {
            return radarPatterns;
        }

        for (int i = 0; i < radarPatterns.size(); i++) {
            for (int j = 0; j < spaceInvaderPatterns.size(); j++) {
                bruteForce(radarPatterns.get(i), spaceInvaderPatterns.get(j));
            }
        }
        return radarPatterns;
    }

    private void bruteForce(RadarPattern radarPattern, SpaceInvaderPattern spaceInvaderPattern) {
        List<String> radarPatternLines = radarPattern.getLines();
        List<String> spaceInvaderPatternLines = spaceInvaderPattern.getLines();
        if (!checkSize(radarPattern, spaceInvaderPattern)) {
            return;
        }

        for (int i = 0; verticalSizeDif(radarPattern, spaceInvaderPattern, i); i++) {
            for (int j = 0; horizontalSizeDif(radarPattern, spaceInvaderPattern, j); j++) {
                List<String> subMatrix = createSubMatrix(spaceInvaderPattern, radarPatternLines, i, j);
                if (checkSubMatrixEqualsToSpaceInvaderPattern(subMatrix, spaceInvaderPatternLines)) {
                    addFoundedSpaceInvaderToRadar(radarPattern, spaceInvaderPattern);
                }
            }

        }

    }

    private List<String> createSubMatrix(SpaceInvaderPattern spaceInvaderPattern, List<String> radarPatternLines, int i, int j) {
        List<String> subMatrix = new ArrayList<>();
        for (int k = i; k < i + spaceInvaderPattern.getVerticalSize(); k++) {
            subMatrix.add(radarPatternLines.get(k).substring(j, j + spaceInvaderPattern.getHorizontalSize()));
        }
        return subMatrix;
    }

    private boolean horizontalSizeDif(RadarPattern radarPattern, SpaceInvaderPattern spaceInvaderPattern, int j) {
        return j <= radarPattern.getHorizontalSize() - spaceInvaderPattern.getHorizontalSize();
    }

    private boolean verticalSizeDif(RadarPattern radarPattern, SpaceInvaderPattern spaceInvaderPattern, int i) {
        return i <= radarPattern.getVerticalSize() - spaceInvaderPattern.getVerticalSize();
    }

    private boolean checkSize(RadarPattern radarPattern, SpaceInvaderPattern spaceInvaderPattern) {
        return radarPattern.getHorizontalSize() >= spaceInvaderPattern.getHorizontalSize() && radarPattern.getVerticalSize() >= spaceInvaderPattern.getVerticalSize();
    }

    private boolean checkSubMatrixEqualsToSpaceInvaderPattern(List<String> subMatrix, List<String> spaceInvaderPatternLines) {
        for (int i = 0; i < subMatrix.size(); i++) {
            if (!subMatrix.get(i).equals(spaceInvaderPatternLines.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void addFoundedSpaceInvaderToRadar(RadarPattern radarPattern, SpaceInvaderPattern spaceInvaderPattern) {
        radarPattern.setSafe(false);
        radarPattern.setSpaceInvadersMap(spaceInvaderPattern.getId());
        radarPattern.setSpaceInvaderCount();
    }

}
