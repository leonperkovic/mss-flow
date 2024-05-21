package mars.mss.flow.client.dto;

public class DebrisLocationDto {
    private String id;
    private Integer estimatedSize;
    private boolean cleared;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(Integer estimatedSize) {
        this.estimatedSize = estimatedSize;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    @Override
    public String toString() {
        return "DebrisLocationDto{" +
               "id=" + id +
               ", estimatedSize=" + estimatedSize +
               ", cleared=" + cleared +
               '}';
    }
}
