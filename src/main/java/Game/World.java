package Game;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class World {
    private ArrayList<Map> gameWorld_;
    private String name_;

    public World(String s) {
        this.gameWorld_ = new ArrayList<>();
        this.name_ = s;
    }

    public ArrayList<Map> getGameWorld() {
        return gameWorld_;
    }

    public void setGameWorld(ArrayList<Map> gameWorld) {
        this.gameWorld_ = gameWorld_;
    }

    public void saveWorld(String path) throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(gameWorld_);
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path + '/' + name_ + ".wrld"));
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public World loadWorld(String path) throws FileNotFoundException {
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
