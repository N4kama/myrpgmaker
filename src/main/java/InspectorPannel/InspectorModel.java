package InspectorPannel;

import Engine.Character.EngineObj;

import java.util.Observable;
import java.util.Observer;

public class InspectorModel implements Observer {
    public static EngineObj obj;

    public InspectorModel(EngineObj obj) {
        this.obj = obj;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
