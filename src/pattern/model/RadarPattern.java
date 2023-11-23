package pattern.model;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class RadarPattern extends Pattern {
    private Map<Long, Long> spaceInvadersIdCountMap;
    private Long spaceInvaderCount;
    private boolean isSafe;

    public RadarPattern(Long id, List<String> lines) {
        super(id, lines);
        this.spaceInvadersIdCountMap = new HashMap<>();
        this.spaceInvaderCount = 0L;
        this.isSafe = true;
    }

    public void setSpaceInvaderCount() {
        this.setSpaceInvaderCount(this.spaceInvadersIdCountMap.values().stream().mapToLong(Long::longValue).sum());
    }

    public Map<Long, Long> getSpaceInvadersMap() {
        return spaceInvadersIdCountMap;
    }

    public void setSpaceInvadersMap(Long spaceInvaderPatternId) {
        Long countOfSpaceInvadersInRadar = this.spaceInvadersIdCountMap.getOrDefault(spaceInvaderPatternId, 0L);
        this.spaceInvadersIdCountMap.put(spaceInvaderPatternId, countOfSpaceInvadersInRadar + 1L);
    }

    public void setSpaceInvadersMap(Map<Long, Long> spaceInvadersIdCountMap) {
        this.spaceInvadersIdCountMap = spaceInvadersIdCountMap;
    }

    public Long getSpaceInvaderCount() {
        return spaceInvaderCount;
    }

    public void setSpaceInvaderCount(Long spaceInvaderCount) {
        this.spaceInvaderCount = spaceInvaderCount;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }
}
