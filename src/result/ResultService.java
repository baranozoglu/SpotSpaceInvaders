package result;

import detector.DetectorService;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;
import reader.ReaderService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ResultService {

    private final ReaderService readerService;
    private final DetectorService detectorService;

    public ResultService(DetectorService detectorService, ReaderService readerService) {
        this.detectorService = detectorService;
        this.readerService = readerService;
    }

    private static void printResult(List<SpaceInvaderPattern> spaceInvaderPatterns, Result result) {
        System.out.println("Result");
        System.out.println("Total " + result.getRadarPatterns().size() + " Radars");
        for (RadarPattern radarPattern : result.getRadarPatterns()) {
            System.out.println("RadarPatternId :" + radarPattern.getId() + "!");
            System.out.println("Is RadarPattern Safe :" + radarPattern.isSafe() + "!");
            System.out.println("");
            System.out.println("Total Different Space Invader :" + radarPattern.getSpaceInvadersMap().size() + " Found!");
            System.out.println("Total Space Invader :" + radarPattern.getSpaceInvaderCount() + " Found!");
            System.out.println("");
            for (Map.Entry<Long, Long> entry : radarPattern.getSpaceInvadersMap().entrySet()) {
                Optional<SpaceInvaderPattern> first = spaceInvaderPatterns.stream().filter(spaceInvaderPattern -> spaceInvaderPattern.getId() == entry.getKey()).findFirst();
                System.out.println("Space Invader Id: " + first.get().getId());
                System.out.println("Space Invader Seen: " + entry.getValue() + " Times!");
                System.out.println("Space Invader Look Like: ");
                for (String line : first.get().getLines()) {
                    System.out.println(line);
                }
                System.out.println("");
            }
        }
    }

    public Result result() {
        List<RadarPattern> radarPatterns = readerService.readRadarFile();
        List<SpaceInvaderPattern> spaceInvaderPatterns = readerService.readSpaceInvaderFile();
        Result result = detectorService.detectWithBrutForce(radarPatterns, spaceInvaderPatterns);
        printResult(spaceInvaderPatterns, result);
        return result;
    }
}
