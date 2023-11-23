package detector;

import detector.strategy.PatternDetectorStrategy;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;
import result.Result;

import java.util.List;

public class DetectorService {

    private final PatternDetectorStrategy patternDetectorStrategy;

    public DetectorService(PatternDetectorStrategy patternDetectorStrategy) {
        this.patternDetectorStrategy = patternDetectorStrategy;
    }

    public Result detectWithBrutForce(List<RadarPattern> radarPatterns, List<SpaceInvaderPattern> spaceInvaderPatterns) {
        return new Result(patternDetectorStrategy.detectPatterns(radarPatterns, spaceInvaderPatterns));
    }
}
