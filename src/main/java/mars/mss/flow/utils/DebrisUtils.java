package mars.mss.flow.utils;

import mars.mss.flow.client.dto.DebrisLocationDto;

import java.util.Collection;
import java.util.Comparator;

public class DebrisUtils {
    private DebrisUtils() {}

    public static DebrisLocationDto findClosestBySize(int size, Collection<DebrisLocationDto> debrisLocations) {
        return debrisLocations.stream()
                .min(Comparator.comparing(dl -> Math.abs(dl.getEstimatedSize() - size)))
                .orElse(null);
    }
}
