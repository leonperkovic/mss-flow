package mars.mss.flow.client;

import mars.mss.flow.client.dto.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class MssClient {
    private final WebClient mssWebClient;

    public MssClient(WebClient mssWebClient) {
        this.mssWebClient = mssWebClient;
    }

    public List<String> getRegisteredCrews() {
        return mssWebClient.get()
                .uri("/crews/registered")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .block();
    }

    public List<DebrisLocationDto> getDebrisLocations() {
        return getDebrisLocations(null);
    }

    public List<DebrisLocationDto> getDebrisLocations(Boolean cleared) {
        return mssWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/debris-locations")
                        .queryParamIfPresent("cleared", Optional.of(cleared))
                        .build()
                ).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<DebrisLocationDto>>() {})
                .block();
    }

    public void markDebrisLocationAsCleared(String debrisLocationId) {
        mssWebClient.post()
                .uri("/debris-locations/{debrisLocationId}/mark-as-cleared", debrisLocationId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public StorageInfoDto getStorageInfo() {
        return mssWebClient.get()
                .uri("/cargo/storage-info")
                .retrieve()
                .bodyToMono(StorageInfoDto.class)
                .block();
    }

    public void storeCargo(CargoDto cargo) {
        mssWebClient.post()
                .uri("/cargo/store")
                .bodyValue(cargo)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public CargoValueDto calculateCargoValue(CargoDto cargo) {
        return mssWebClient.post()
                .uri("/cargo/calculate-value")
                .bodyValue(cargo)
                .retrieve()
                .bodyToMono(CargoValueDto.class)
                .block();
    }

    public List<SpaceshipDto> getSpaceships() {
        return getSpaceships(null);
    }

    public List<SpaceshipDto> getSpaceships(Boolean available) {
        return mssWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/spaceships")
                        .queryParamIfPresent("available", Optional.of(available))
                        .build()
                ).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<SpaceshipDto>>() {})
                .block();
    }

    public void markSpaceshipAsAvailable(String spaceshipId) {
        mssWebClient.post()
                .uri("/spaceships/{spaceshipId}/mark-as-available", spaceshipId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void markSpaceshipAsUnavailable(String spaceshipId) {
        mssWebClient.post()
                .uri("/spaceships/{spaceshipId}/mark-as-unavailable", spaceshipId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
