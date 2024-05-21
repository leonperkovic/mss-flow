package mars.mss.flow.client.dto;

public class StorageInfoDto {
    private Integer capacity;
    private Integer used;
    private Integer free;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "StorageInfoDto{" +
               "capacity=" + capacity +
               ", used=" + used +
               ", free=" + free +
               '}';
    }
}
