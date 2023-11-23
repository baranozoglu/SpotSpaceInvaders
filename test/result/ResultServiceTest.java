package result;

import detector.DetectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;
import reader.ReaderService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    @Mock
    private ReaderService readerService;

    @Mock
    private DetectorService detectorService;

    private ResultService resultService;

    @BeforeEach
    void setUp() {
        resultService = new ResultService(detectorService, readerService);
    }

    @Test
    void testResultWhenFilesNotEmptyThenReturnResult() {
        List<RadarPattern> radarPatterns = Collections.singletonList(new RadarPattern());
        List<SpaceInvaderPattern> spaceInvaderPatterns = Collections.singletonList(new SpaceInvaderPattern());
        Result expectedResult = new Result(radarPatterns);

        when(readerService.readRadarFile()).thenReturn(radarPatterns);
        when(readerService.readSpaceInvaderFile()).thenReturn(spaceInvaderPatterns);
        when(detectorService.detectWithBrutForce(radarPatterns, spaceInvaderPatterns)).thenReturn(expectedResult);

        Result actualResult = resultService.result();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testResultWhenRadarFileEmptyAndSpaceInvaderFileNotEmptyThenReturnResult() {
        List<RadarPattern> radarPatterns = Collections.emptyList();
        List<SpaceInvaderPattern> spaceInvaderPatterns = Collections.singletonList(new SpaceInvaderPattern());
        Result expectedResult = new Result(radarPatterns);

        when(readerService.readRadarFile()).thenReturn(radarPatterns);
        when(readerService.readSpaceInvaderFile()).thenReturn(spaceInvaderPatterns);
        when(detectorService.detectWithBrutForce(radarPatterns, spaceInvaderPatterns)).thenReturn(expectedResult);

        Result actualResult = resultService.result();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testResultWhenRadarFileNotEmptyAndSpaceInvaderFileEmptyThenReturnResult() {
        List<RadarPattern> radarPatterns = Collections.singletonList(new RadarPattern());
        List<SpaceInvaderPattern> spaceInvaderPatterns = Collections.emptyList();
        Result expectedResult = new Result(radarPatterns);

        when(readerService.readRadarFile()).thenReturn(radarPatterns);
        when(readerService.readSpaceInvaderFile()).thenReturn(spaceInvaderPatterns);
        when(detectorService.detectWithBrutForce(radarPatterns, spaceInvaderPatterns)).thenReturn(expectedResult);

        Result actualResult = resultService.result();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testResultWhenFilesEmptyThenReturnResult() {
        List<RadarPattern> radarPatterns = Collections.emptyList();
        List<SpaceInvaderPattern> spaceInvaderPatterns = Collections.emptyList();
        Result expectedResult = new Result(radarPatterns);

        when(readerService.readRadarFile()).thenReturn(radarPatterns);
        when(readerService.readSpaceInvaderFile()).thenReturn(spaceInvaderPatterns);
        when(detectorService.detectWithBrutForce(radarPatterns, spaceInvaderPatterns)).thenReturn(expectedResult);

        Result actualResult = resultService.result();

        assertEquals(expectedResult, actualResult);
    }
}