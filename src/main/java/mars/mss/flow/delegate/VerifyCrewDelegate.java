package mars.mss.flow.delegate;

import mars.mss.flow.ProcessVariables;
import mars.mss.flow.client.MssClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class VerifyCrewDelegate implements JavaDelegate {
    private final MssClient mssClient;

    public VerifyCrewDelegate(MssClient mssClient) {
        this.mssClient = mssClient;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var crewId = (String) execution.getVariable(ProcessVariables.CREW_ID);

        var registeredCrews = mssClient.getRegisteredCrews();
        var verificationSuccess = registeredCrews.contains(crewId);

        execution.setVariable(ProcessVariables.CREW_REGISTERED, verificationSuccess);
    }
}
