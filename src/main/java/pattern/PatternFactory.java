package pattern;

import pattern.model.Pattern;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;

import java.util.List;

public class PatternFactory {

    public Pattern getPattern(PatternEnum type, Long id, List<String> lines) {
        return switch (type) {
            case RADAR -> new RadarPattern(id, lines);
            case SPACE_INVADER -> new SpaceInvaderPattern(id, lines);
        };
    }

}
