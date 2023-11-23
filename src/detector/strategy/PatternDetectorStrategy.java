package detector.strategy;

import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;

import java.util.List;

public interface PatternDetectorStrategy {
    List<RadarPattern> detectPatterns(List<RadarPattern> radarPatterns, List<SpaceInvaderPattern> spaceInvaderPatterns);
}
