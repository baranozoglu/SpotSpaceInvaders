package result;

import lombok.Data;
import lombok.NoArgsConstructor;
import pattern.model.RadarPattern;

import java.util.List;

@Data
@NoArgsConstructor
public class Result {
    private List<RadarPattern> radarPatterns;

    public Result(List<RadarPattern> radarPatterns) {
        this.radarPatterns = radarPatterns;
    }
}
