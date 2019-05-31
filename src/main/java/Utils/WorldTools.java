package Utils;

import Game.World;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorldTools {
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
        return w;
    }
}
