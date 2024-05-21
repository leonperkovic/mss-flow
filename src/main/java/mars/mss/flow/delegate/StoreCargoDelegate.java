package mars.mss.flow.delegate;

import mars.mss.flow.ProcessVariables;
import mars.mss.flow.client.MssClient;
import mars.mss.flow.client.dto.CargoDto;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class StoreCargoDelegate implements JavaDelegate {
    private final MssClient mssClient;

    public StoreCargoDelegate(MssClient mssClient) {
        this.mssClient = mssClient;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var engineModules = (Integer) execution.getVariable(ProcessVariables.ENGINE_MODULES);
        var powerCores = (Integer) execution.getVariable(ProcessVariables.POWER_CORES);
        var commArrays = (Integer) execution.getVariable(ProcessVariables.COMM_ARRAYS);
        var hullPlatings = (Integer) execution.getVariable(ProcessVariables.HULL_PLATINGS);

        var cargo = new CargoDto();
        cargo.setEngineModules(engineModules);
        cargo.setPowerCores(powerCores);
        cargo.setCommArrays(commArrays);
        cargo.setHullPlatings(hullPlatings);

        mssClient.storeCargo(cargo);
    }
}
