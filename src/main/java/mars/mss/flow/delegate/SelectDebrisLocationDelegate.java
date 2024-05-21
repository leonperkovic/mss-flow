package mars.mss.flow.delegate;

import mars.mss.flow.ProcessVariables;
import mars.mss.flow.client.MssClient;
import mars.mss.flow.client.dto.DebrisLocationDto;
import mars.mss.flow.utils.DebrisUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectDebrisLocationDelegate implements JavaDelegate {
    private final MssClient mssClient;
    private final RuntimeService runtimeService;

    public SelectDebrisLocationDelegate(MssClient mssClient, RuntimeService runtimeService) {
        this.mssClient = mssClient;
        this.runtimeService = runtimeService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var shipCargoLimit = (Integer) execution.getVariable(ProcessVariables.SHIP_CARGO_LIMIT);

        var unclearedDebrisLocations = mssClient.getDebrisLocations(false);
        var freeUnclearedDebrisLocations = filterOutTakenDebrisLocations(unclearedDebrisLocations);
        var selectedDebrisLocation = DebrisUtils.findClosestBySize(shipCargoLimit, freeUnclearedDebrisLocations);
        var selectedDebrisLocationId = selectedDebrisLocation != null ? selectedDebrisLocation.getId() : null;

        execution.setVariable(ProcessVariables.SELECTED_DEBRIS_LOCATION_ID, selectedDebrisLocationId);
    }

    private List<DebrisLocationDto> filterOutTakenDebrisLocations(List<DebrisLocationDto> debrisLocations) {
        var takenDebrisLocations = runtimeService.createVariableInstanceQuery()
                .variableName(ProcessVariables.SELECTED_DEBRIS_LOCATION_ID)
                .list()
                .stream()
                .map(dl -> (String) dl.getValue())
                .toList();
        return debrisLocations.stream().filter(dl -> takenDebrisLocations.contains(dl.getId())).toList();
    }
}
