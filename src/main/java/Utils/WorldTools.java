package Utils;

import Editor.EditorModel;
import Engine.Character.EngineObj;
import Game.World;
import InspectorPannel.InspectorModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

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

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

final class SubImg
        extends TypeAdapter<BufferedImage> {

    private static final TypeAdapter<BufferedImage> SubImg = new SubImg().nullSafe();

    private SubImg() {
    }

    static TypeAdapter<BufferedImage> getSubImg() {
        return SubImg;
    }

    @Override
    @SuppressWarnings("resource")
    public void write(final JsonWriter out, final BufferedImage image)
            throws IOException {
        // Intermediate buffer
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        // By the way, how to pick up the target image format? BMP takes more space, PNG takes more time, JPEG is lossy...
        ImageIO.write(image, "PNG", output);
        // Not sure about this, but converting to base64 is more JSON-friendly
        final Base64.Encoder encoder = Base64.getEncoder();
        // toByteArray() returns a copy, not the original array (x2 more memory)
        // + creating a string requires more memory to create the String internal buffer (x3 more memory)
        final String imageBase64 = encoder.encodeToString(output.toByteArray());
        out.value(imageBase64);
    }

    @Override
    public BufferedImage read(final JsonReader in)
            throws IOException {
        // The same in reverse order
        final String imageBase64 = in.nextString();
        final Base64.Decoder decoder = Base64.getDecoder();
        final byte[] input = decoder.decode(imageBase64);
        return ImageIO.read(new ByteArrayInputStream(input));
    }

}


public class WorldTools {
    public static EngineObj player; //static reference to unique player object through a unique world
    private static World w_;
    public static InspectorModel inspectorModel = new InspectorModel(null); //used almost like a singleton for MapModel reference
    private static final Gson gson = new GsonBuilder()
    .registerTypeHierarchyAdapter(BufferedImage.class, SubImg.getSubImg())
    .create();
    public static void saveWorld(String path, World world) throws IOException {
        
        //Gson gson = new Gson();
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
        //Gson gson = new Gson();
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
