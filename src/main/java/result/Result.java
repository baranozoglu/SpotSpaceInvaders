package result;

import pattern.model.RadarPattern;

import java.util.List;

public class Result {
    private List<RadarPattern> radarPatterns;

    public Result(List<RadarPattern> radarPatterns) {
        this.radarPatterns = radarPatterns;
    }

    public List<RadarPattern> getRadarPatterns() {
        return radarPatterns;
    }

    public void setRadarPatterns(List<RadarPattern> radarPatterns) {
        this.radarPatterns = radarPatterns;
    }
}
