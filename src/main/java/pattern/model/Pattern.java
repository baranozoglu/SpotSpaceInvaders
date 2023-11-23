package pattern.model;

import java.util.List;

public abstract class Pattern {
    private Long id;
    private List<String> lines;
    private int verticalSize;
    private int horizontalSize;

    protected Pattern() {
    }

    protected Pattern(Long id, List<String> lines) {
        this.id = id;
        this.lines = lines;
        this.verticalSize = lines.size();
        this.horizontalSize = lines.isEmpty() ? 0 : lines.get(0).length();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public void setVerticalSize(int verticalSize) {
        this.verticalSize = verticalSize;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public void setHorizontalSize(int horizontalSize) {
        this.horizontalSize = horizontalSize;
    }
}
