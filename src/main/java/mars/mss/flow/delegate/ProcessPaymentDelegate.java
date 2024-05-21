package mars.mss.flow.delegate;

import mars.mss.flow.ProcessVariables;
import mars.mss.flow.client.MssClient;
import mars.mss.flow.client.dto.CargoDto;
import mars.mss.flow.utils.PaymentUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ProcessPaymentDelegate implements JavaDelegate {
    private final MssClient mssClient;

    public ProcessPaymentDelegate(MssClient mssClient) {
        this.mssClient = mssClient;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var crewId = (String) execution.getVariable(ProcessVariables.CREW_ID);
        var engineModules = (Integer) execution.getVariable(ProcessVariables.ENGINE_MODULES);
        var powerCores = (Integer) execution.getVariable(ProcessVariables.POWER_CORES);
        var commArrays = (Integer) execution.getVariable(ProcessVariables.COMM_ARRAYS);
        var hullPlatings = (Integer) execution.getVariable(ProcessVariables.HULL_PLATINGS);

        var cargo = new CargoDto();
        cargo.setEngineModules(engineModules);
        cargo.setPowerCores(powerCores);
        cargo.setCommArrays(commArrays);
        cargo.setHullPlatings(hullPlatings);

        var cargoValue = mssClient.calculateCargoValue(cargo);

        PaymentUtils.processPayment(crewId, cargoValue.getValue());
    }
}
