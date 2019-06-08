package Utils;

import Editor.EditorModel;
import Engine.Character.EngineObj;
import Game.Map;
import Game.World;
import InspectorPannel.InspectorModel;
import MapPannel.MapController;
import MapPannel.MapModel;
import MapPannel.MapView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


public class WorldTools {
    public static EngineObj player; // static reference to unique player object through a unique world
    private static World w_;
    public static InspectorModel inspectorModel = new InspectorModel(null); // used almost like a singleton for MapModel
                                                                            // reference
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
        if (i > 0 && path.substring(i + 1).equals("wrld")) {
            try {
                reader = new JsonReader(new FileReader(path));
                w = gson.fromJson(reader, World.class);
                EditorModel.singleton.gameWorld = w;
                reload_world(w);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        w_ = w;
        return w;
    }

    public static void reload_world(World w) {
        for (Map m : w.gameWorld_) {
            EditorModel.singleton.load_map(m);
            for(EngineObj o : m.getEngineObjs())
            {
                if(o.isAlive())
                {
                    o.reloadSprites();
                }
            }
        }
    }
}
