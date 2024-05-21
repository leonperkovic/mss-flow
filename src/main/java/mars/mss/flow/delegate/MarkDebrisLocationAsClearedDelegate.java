package mars.mss.flow.delegate;

import mars.mss.flow.ProcessVariables;
import mars.mss.flow.client.MssClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class MarkDebrisLocationAsClearedDelegate implements JavaDelegate {
    private final MssClient mssClient;

    public MarkDebrisLocationAsClearedDelegate(MssClient mssClient) {
        this.mssClient = mssClient;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var selectedDebrisLocationId = (String) execution.getVariable(ProcessVariables.SELECTED_DEBRIS_LOCATION_ID);

        mssClient.markDebrisLocationAsCleared(selectedDebrisLocationId);
    }
}
