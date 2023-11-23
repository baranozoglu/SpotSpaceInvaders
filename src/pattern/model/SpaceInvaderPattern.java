package pattern.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SpaceInvaderPattern extends Pattern {
    public SpaceInvaderPattern(Long id, List<String> lines) {
        super(id, lines);
    }
}
