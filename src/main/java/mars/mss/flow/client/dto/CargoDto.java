package mars.mss.flow.client.dto;

public class CargoDto {
    private Integer engineModules;
    private Integer powerCores;
    private Integer commArrays;
    private Integer hullPlatings;

    public Integer getEngineModules() {
        return engineModules;
    }

    public void setEngineModules(Integer engineModules) {
        this.engineModules = engineModules;
    }

    public Integer getPowerCores() {
        return powerCores;
    }

    public void setPowerCores(Integer powerCores) {
        this.powerCores = powerCores;
    }

    public Integer getCommArrays() {
        return commArrays;
    }

    public void setCommArrays(Integer commArrays) {
        this.commArrays = commArrays;
    }

    public Integer getHullPlatings() {
        return hullPlatings;
    }

    public void setHullPlatings(Integer hullPlatings) {
        this.hullPlatings = hullPlatings;
    }

    @Override
    public String toString() {
        return "CargoDto{" +
               "engineModules=" + engineModules +
               ", powerCores=" + powerCores +
               ", commArrays=" + commArrays +
               ", hullPlatings=" + hullPlatings +
               '}';
    }
}
