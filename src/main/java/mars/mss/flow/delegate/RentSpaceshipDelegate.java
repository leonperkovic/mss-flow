package mars.mss.flow.delegate;

import mars.mss.flow.ProcessVariables;
import mars.mss.flow.client.MssClient;
import mars.mss.flow.utils.ShipUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class RentSpaceshipDelegate implements JavaDelegate {
    private final MssClient mssClient;

    public RentSpaceshipDelegate(MssClient mssClient) {
        this.mssClient = mssClient;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var crewSize = (Integer) execution.getVariable(ProcessVariables.CREW_SIZE);

        var spaceships = mssClient.getSpaceships(true);
        var rentedSpaceship = ShipUtils.findClosestForCrewSize(crewSize, spaceships);

        execution.setVariable(ProcessVariables.RENTED_SHIP_ID, rentedSpaceship != null ? rentedSpaceship.getId() : null);
        execution.setVariable(ProcessVariables.SHIP_CARGO_LIMIT, rentedSpaceship != null ? rentedSpaceship.getCargoLimit() : null);
    }
}
