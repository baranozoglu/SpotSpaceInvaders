package detector.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;
import result.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BruteForcePatternDetectorTest {
    private BruteForcePatternDetector bruteForcePatternDetector;

    @BeforeEach
    void setup() {
        bruteForcePatternDetector = new BruteForcePatternDetector();
    }

    @Test
    void testDetectPatternsWhenBothListsAreEmptyThenReturnResultWithEmptyList() {
        List<RadarPattern> radarPatterns = new ArrayList<>();
        List<SpaceInvaderPattern> spaceInvaderPatterns = new ArrayList<>();

        List<RadarPattern> enrichedRadarPatterns = bruteForcePatternDetector.detectPatterns(radarPatterns, spaceInvaderPatterns);
        Result result = new Result(enrichedRadarPatterns);

        assertEquals(0, result.getRadarPatterns().size());
    }

    @Test
    void testDetectPatternsWhenRadarPatternsListIsEmptyAndSpaceInvaderPatternsListIsNotEmptyThenReturnResultWithEmptyList() {
        List<RadarPattern> radarPatterns = new ArrayList<>();
        List<SpaceInvaderPattern> spaceInvaderPatterns = Arrays.asList(new SpaceInvaderPattern(1L, new ArrayList<>()));

        List<RadarPattern> enrichedRadarPatterns = bruteForcePatternDetector.detectPatterns(radarPatterns, spaceInvaderPatterns);
        Result result = new Result(enrichedRadarPatterns);

        assertEquals(0, result.getRadarPatterns().size());
    }

    @Test
    void testDetectPatternsWhenRadarPatternsListIsNotEmptyAndSpaceInvaderPatternsListIsEmptyThenReturnResultWithSameList() {
        List<RadarPattern> radarPatterns = Arrays.asList(new RadarPattern(1L, new ArrayList<>()));
        List<SpaceInvaderPattern> spaceInvaderPatterns = new ArrayList<>();

        List<RadarPattern> enrichedRadarPatterns = bruteForcePatternDetector.detectPatterns(radarPatterns, spaceInvaderPatterns);
        Result result = new Result(enrichedRadarPatterns);

        assertEquals(1, result.getRadarPatterns().size());
        assertEquals(radarPatterns, result.getRadarPatterns());
    }

    @Test
    void testDetectPatternsWhenBothListsAreNotEmptyThenReturnResultWithSameList() {
        List<RadarPattern> radarPatterns = Arrays.asList(new RadarPattern(1L, new ArrayList<>()));
        List<SpaceInvaderPattern> spaceInvaderPatterns = Arrays.asList(new SpaceInvaderPattern(1L, new ArrayList<>()));

        List<RadarPattern> enrichedRadarPatterns = bruteForcePatternDetector.detectPatterns(radarPatterns, spaceInvaderPatterns);
        Result result = new Result(enrichedRadarPatterns);

        assertEquals(1, result.getRadarPatterns().size());
        assertEquals(radarPatterns, result.getRadarPatterns());
    }
}