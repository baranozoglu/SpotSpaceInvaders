package result;

import detector.DetectorService;
import pattern.PatternEnum;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;
import reader.RadarReaderService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class ResultService {
    private static final Logger logger = Logger.getLogger(ResultService.class.getName());
    private final RadarReaderService radarReaderService;
    private final DetectorService detectorService;

    public ResultService(DetectorService detectorService, RadarReaderService radarReaderService) {
        this.detectorService = detectorService;
        this.radarReaderService = radarReaderService;
    }

    private static void appendResultLines(List<SpaceInvaderPattern> spaceInvaderPatterns, StringBuilder stringBuilder, Map.Entry<Long, Long> entry) {
        Optional<SpaceInvaderPattern> first = spaceInvaderPatterns.stream()
                .filter(getSpaceInvaderPatternPredicate(entry))
                .findFirst();
        first.ifPresent(spaceInvaderPattern -> stringBuilder.append("Space Invader Id: ")
                .append(spaceInvaderPattern.getId())
                .append("\n"));
        stringBuilder.append("Space Invader Seen: ")
                .append(entry.getValue())
                .append(" Times!\n");
        stringBuilder.append("Space Invader Look Like: \n");
        if (first.isPresent()) {
            for (String line : first.get().getLines()) {
                stringBuilder.append(line).append("\n");
            }
        }
        stringBuilder.append("\n");
    }

    private static void appendResultDetails(StringBuilder stringBuilder, RadarPattern radarPattern) {
        stringBuilder.append("RadarPatternId :")
                .append(radarPattern.getId())
                .append("!\n");
        stringBuilder.append("Is RadarPattern Safe :")
                .append(radarPattern.isSafe())
                .append("!\n\n");
        if (radarPattern.getSpaceInvadersMap() != null) {
            stringBuilder.append("Total Different Space Invader :")
                    .append(radarPattern.getSpaceInvadersMap().size())
                    .append(" Found!\n");
        }
        stringBuilder.append("Total Space Invader :")
                .append(radarPattern.getSpaceInvaderCount())
                .append(" Found!\n\n");
    }

    private static void appendResulIntro(Result result, StringBuilder stringBuilder) {
        stringBuilder.append("Result\n");
        stringBuilder.append("Total ").append(result.getRadarPatterns().size()).append(" Radars\n");
    }

    private static Predicate<SpaceInvaderPattern> getSpaceInvaderPatternPredicate(Map.Entry<Long, Long> entry) {
        return spaceInvaderPattern -> Objects.equals(spaceInvaderPattern.getId(), entry.getKey());
    }

    public Result result() {
        List<RadarPattern> radarPatterns = getRadarPatterns();
        List<SpaceInvaderPattern> spaceInvaderPatterns = getSpaceInvaderPatterns();
        Result result = detectorService.detectWithBrutForce(radarPatterns, spaceInvaderPatterns);
        printResult(spaceInvaderPatterns, result);
        return result;
    }

    void printResult(List<SpaceInvaderPattern> spaceInvaderPatterns, Result result) {
        StringBuilder stringBuilder = new StringBuilder();
        if (result != null && result.getRadarPatterns() != null) {
            appendResulIntro(result, stringBuilder);
            for (RadarPattern radarPattern : result.getRadarPatterns()) {
                appendResultDetails(stringBuilder, radarPattern);
                if (radarPattern.getSpaceInvadersMap() != null) {
                    for (Map.Entry<Long, Long> entry : radarPattern.getSpaceInvadersMap().entrySet()) {
                        appendResultLines(spaceInvaderPatterns, stringBuilder, entry);
                    }
                }
            }
            logger.info(stringBuilder.toString());
        }
    }

    private List<SpaceInvaderPattern> getSpaceInvaderPatterns() {
        return (List<SpaceInvaderPattern>) (Object) radarReaderService.readRadarFile(PatternEnum.SPACE_INVADER);
    }

    private List<RadarPattern> getRadarPatterns() {
        return (List<RadarPattern>) (Object) radarReaderService.readRadarFile(PatternEnum.RADAR);
    }
}
