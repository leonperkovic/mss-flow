package mars.mss.flow.utils;

import mars.mss.flow.client.dto.SpaceshipDto;

import java.util.Collection;
import java.util.Comparator;

public class ShipUtils {
    private ShipUtils() {}

    public static SpaceshipDto findClosestForCrewSize(int crewSize, Collection<SpaceshipDto> spaceships) {
        return spaceships.stream()
                .filter(s -> s.getSupportedCrew() >= crewSize)
                .min(Comparator.comparing(s -> s.getSupportedCrew() - crewSize))
                .orElse(null);
    }
}
