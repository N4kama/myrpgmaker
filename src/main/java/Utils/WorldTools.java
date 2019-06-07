package Utils;

import Engine.Character.EngineObj;
import Game.World;
import InspectorPannel.InspectorModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorldTools {
    public static EngineObj player; //static reference to unique player object through a unique world
    private static World w_;
    public static InspectorModel inspectorModel = new InspectorModel(w_); //used almost like a singleton for MapModel reference

    public static void saveWorld(String path, World world) throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(world);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + ".wrld"));
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static World loadWorld(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = null;
        World w = null;
        int i = path.lastIndexOf('.');
        if (i > 0 &&  path.substring(i+1).equals("wrld")) {
            try {
                reader = new JsonReader(new FileReader(path));
                w = gson.fromJson(reader, World.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        w_ = w;
        return w;
    }
}
