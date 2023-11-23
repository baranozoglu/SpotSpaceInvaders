package pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pattern.model.Pattern;
import pattern.model.RadarPattern;
import pattern.model.SpaceInvaderPattern;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternFactoryTest {
    private PatternFactory patternFactory;

    @BeforeEach
    void setUp() {
        patternFactory = new PatternFactory();
    }

    @Test
    void testGetPatternWithRadarEnum() {
        // Arrange
        Long id = 1L;
        List<String> lines = List.of("Radar Pattern");

        // Act
        Pattern result = patternFactory.getPattern(PatternEnum.RADAR, id, lines);

        // Assert
        assertEquals(RadarPattern.class, result.getClass());
        assertEquals(id, result.getId());
        assertEquals(lines, result.getLines());
    }

    @Test
    void testGetPatternWithSpaceInvaderEnum() {
        // Arrange
        Long id = 2L;
        List<String> lines = List.of("Space Invader Pattern");

        // Act
        Pattern result = patternFactory.getPattern(PatternEnum.SPACE_INVADER, id, lines);

        // Assert
        assertEquals(SpaceInvaderPattern.class, result.getClass());
        assertEquals(id, result.getId());
        assertEquals(lines, result.getLines());
    }
}