package InspectorPannel;

import Engine.Character.EngineObj;
import Game.Map;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class InspectorModel extends Observable  implements Observer {
    public static EngineObj obj;
    public static Map m;
    String namet;
    String typet;
    String post;
    String old;
    String teleport;
    JTextField dialog;
    public InspectorModel(EngineObj obj, Map m) {
        this.obj = obj;
        this.m = m;
        setInspector();
    }

    void setInspector(){
        if (obj != null) {
            namet = "Name: " + obj.getName_();

            if (!obj.getIs_player())
                typet = "Type: NPC";
            else
                typet = "Type: Player";
            post = "Position: " + obj.getPosition_().getX() + "  " + obj.getPosition_().getY();
            if (obj.getDialog() != null)
                old = "Current dialog: " + obj.getDialog();
            else
                old = "Current dialog: ";
            if (obj.getTeleportePos() != null)
                teleport = "Current teleport position:" + obj.getTeleportePos().getX() + "  " + obj.getTeleportePos().getY();
            else
                teleport = "Current teleport position: NULL";
        }
        else
        {
            namet = "Name: ";
            typet = "Type: ";
            post = "Position: ";
            old = "Current dialog: ";
            teleport = "Current teleport position: NULL";
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == EngineObj.class) {
            obj = (EngineObj) arg;
            setInspector();
            setChanged();
            notifyObservers();
        }
    }

}
