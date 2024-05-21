package mars.mss.flow.delegate;

import mars.mss.flow.ProcessVariables;
import mars.mss.flow.client.MssClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class MarkSpaceshipAsAvailableDelegate implements JavaDelegate {
    private final MssClient mssClient;

    public MarkSpaceshipAsAvailableDelegate(MssClient mssClient) {
        this.mssClient = mssClient;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var rentedShipId = (String) execution.getVariable(ProcessVariables.RENTED_SHIP_ID);
        mssClient.markSpaceshipAsAvailable(rentedShipId);
    }
}
