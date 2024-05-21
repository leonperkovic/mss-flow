package mars.mss.flow.client.dto;

public class SpaceshipDto {
    private String id;
    private String name;
    private Integer supportedCrew;
    private Integer cargoLimit;
    private boolean available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSupportedCrew() {
        return supportedCrew;
    }

    public void setSupportedCrew(Integer supportedCrew) {
        this.supportedCrew = supportedCrew;
    }

    public Integer getCargoLimit() {
        return cargoLimit;
    }

    public void setCargoLimit(Integer cargoLimit) {
        this.cargoLimit = cargoLimit;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "SpaceshipDto{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", supportedCrew=" + supportedCrew +
               ", cargoLimit=" + cargoLimit +
               ", available=" + available +
               '}';
    }
}
