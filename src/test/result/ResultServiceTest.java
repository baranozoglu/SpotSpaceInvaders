package result;

import detector.DetectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pattern.PatternEnum;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;
import reader.RadarReaderService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    @Mock
    private RadarReaderService radarReaderService;

    @Mock
    private DetectorService detectorService;

    private ResultService resultService;

    @BeforeEach
    void setUp() {
        resultService = new ResultService(detectorService, radarReaderService);
    }

    @Test
    void testResultWhenFilesNotEmptyThenReturnResult() {
        List<RadarPattern> radarPatterns = List.of(new RadarPattern());
        Result expectedResult = new Result(radarPatterns);

        when(radarReaderService.readRadarFile(PatternEnum.RADAR))
                .thenReturn(new ArrayList<>());
        when(radarReaderService.readRadarFile(PatternEnum.SPACE_INVADER))
                .thenReturn(new ArrayList<>());
        when(detectorService.detectWithBrutForce(anyList(), anyList()))
                .thenReturn(expectedResult);

        Result actualResult = resultService.result();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testResultWhenRadarFileEmptyAndSpaceInvaderFileNotEmptyThenReturnResult() {
        List<RadarPattern> radarPatterns = List.of();
        List<SpaceInvaderPattern> spaceInvaderPatterns = List.of(new SpaceInvaderPattern());
        Result expectedResult = new Result(radarPatterns);

        when(radarReaderService.readRadarFile(PatternEnum.RADAR))
                .thenReturn(new ArrayList<>((Collection) radarPatterns));
        when(radarReaderService.readRadarFile(PatternEnum.SPACE_INVADER))
                .thenReturn(new ArrayList<>((Collection) spaceInvaderPatterns));
        when(detectorService.detectWithBrutForce(anyList(), anyList()))
                .thenReturn(expectedResult);

        Result actualResult = resultService.result();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testResultWhenRadarFileNotEmptyAndSpaceInvaderFileEmptyThenReturnResult() {
        List<RadarPattern> radarPatterns = List.of(new RadarPattern());
        List<SpaceInvaderPattern> spaceInvaderPatterns = List.of();
        Result expectedResult = new Result(radarPatterns);

        when(radarReaderService.readRadarFile(PatternEnum.RADAR))
                .thenReturn(new ArrayList<>((Collection) radarPatterns));
        when(radarReaderService.readRadarFile(PatternEnum.SPACE_INVADER))
                .thenReturn(new ArrayList<>((Collection) spaceInvaderPatterns));
        when(detectorService.detectWithBrutForce(anyList(), anyList()))
                .thenReturn(expectedResult);

        Result actualResult = resultService.result();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testResultWhenFilesEmptyThenReturnResult() {
        List<RadarPattern> radarPatterns = List.of();
        List<SpaceInvaderPattern> spaceInvaderPatterns = List.of();
        Result expectedResult = new Result(radarPatterns);

        when(radarReaderService.readRadarFile(PatternEnum.RADAR))
                .thenReturn(new ArrayList<>((Collection) radarPatterns));
        when(radarReaderService.readRadarFile(PatternEnum.SPACE_INVADER))
                .thenReturn(new ArrayList<>((Collection) spaceInvaderPatterns));
        when(detectorService.detectWithBrutForce(anyList(), anyList()))
                .thenReturn(expectedResult);

        Result actualResult = resultService.result();

        assertEquals(expectedResult, actualResult);
    }
}