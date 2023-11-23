package detector;

import detector.strategy.PatternDetectorStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;
import result.Result;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DetectorServiceTest {
    private DetectorService detectorService;
    private PatternDetectorStrategy patternDetectorStrategy;

    @BeforeEach
    void setUp() {
        patternDetectorStrategy = mock(PatternDetectorStrategy.class);
        detectorService = new DetectorService(patternDetectorStrategy);
    }

    @Test
    void testDetectWithBruteForce() {
        // Arrange
        List<RadarPattern> radarPatterns = new ArrayList<>();
        List<SpaceInvaderPattern> spaceInvaderPatterns = new ArrayList<>();
        List<RadarPattern> expectedPatterns = new ArrayList<>();
        when(patternDetectorStrategy.detectPatterns(radarPatterns, spaceInvaderPatterns))
                .thenReturn(expectedPatterns);

        // Act
        Result result = detectorService.detectWithBrutForce(radarPatterns, spaceInvaderPatterns);

        // Assert
        assertEquals(expectedPatterns, result.getRadarPatterns());
        verify(patternDetectorStrategy, times(1)).detectPatterns(radarPatterns, spaceInvaderPatterns);
    }
}
