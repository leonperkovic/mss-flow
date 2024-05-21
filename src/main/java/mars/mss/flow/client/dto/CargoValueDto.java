package mars.mss.flow.client.dto;

public class CargoValueDto {
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CargoValueDto{" +
               "value=" + value +
               '}';
    }
}
