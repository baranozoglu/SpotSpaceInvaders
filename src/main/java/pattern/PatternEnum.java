package pattern;

import static properties.TextProperties.RADARS_TXT;
import static properties.TextProperties.SPACE_INVADERS_TXT;

public enum PatternEnum {
    RADAR(RADARS_TXT), SPACE_INVADER(SPACE_INVADERS_TXT);

    private String path;

    PatternEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
