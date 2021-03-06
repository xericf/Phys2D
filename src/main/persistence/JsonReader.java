package persistence;

import model.util.Vector2;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.object.Ball;
import ui.object.Player;
import ui.object.World;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// A Reader meant for reading JSON formatted files
public class JsonReader {

    // CREDITS: Inspired by https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Reads world data from a file with a given path
    public static World readWorld(String path) throws IOException {
        String worldData = readFile(path);
        JSONObject jsonWorld = new JSONObject(worldData);
        return parseWorld(jsonWorld);
    }

    // CREDITS: Inspired by https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Reads a file from a given path and returns the contents
    private static String readFile(String path) throws IOException {
        StringBuilder strBuilder = new StringBuilder();

        // Try with resources, closes stream after finishing
        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            // append each line to the stringbuilder
            stream.forEach((str) -> {
                strBuilder.append(str);
            });
        }

        return strBuilder.toString();
    }

    // EFFECTS: Converts worldData (JSONObject format) string into a new World with all
    // the same properties, player, and physical objects.
    private static World parseWorld(JSONObject worldData) {
        // World properties
        Vector2 size = Vector2.parseJson(worldData.getJSONObject("size"));
        Vector2 gravityForce = Vector2.parseJson(worldData.getJSONObject("gravityForce"));

        // player
        Player resultPlayer = parsePlayer(worldData);

        // other physical objects
        ArrayList<Ball> resultObjects = new ArrayList<>();
        JSONArray worldObjects = worldData.getJSONArray("world_objects");
        int length = worldObjects.length();

        for (int i = 0; i < length; i++) {
            JSONObject jsonPhysicalObject = worldObjects.getJSONObject(i);
            Vector2 position = Vector2.parseJson(jsonPhysicalObject.getJSONObject("position"));
            Vector2 velocity = Vector2.parseJson(jsonPhysicalObject.getJSONObject("velocity"));
            Vector2 force = Vector2.parseJson(jsonPhysicalObject.getJSONObject("force"));
            Vector2 scale = Vector2.parseJson(jsonPhysicalObject.getJSONObject("scale"));
            Color color = new Color(jsonPhysicalObject.getInt("color"));
            Ball ball = new Ball(position, velocity, force, scale, color);
            resultObjects.add(ball);
        }

        World result = new World(size);
        result.setGravityForce(gravityForce);
        result.setPlayer(resultPlayer);
        result.setWorldObjects(resultObjects);

        return result;
    }

    // EFFECTS: Parses a Player object using a given JSONObject with all the same properties as seen in the object.
    private static Player parsePlayer(JSONObject worldData) {
        JSONObject jsonPlayer = worldData.getJSONObject("player");
        Vector2 playerPosition = Vector2.parseJson(jsonPlayer.getJSONObject("position"));
        Vector2 playerVelocity = Vector2.parseJson(jsonPlayer.getJSONObject("velocity"));
        Vector2 playerForce = Vector2.parseJson(jsonPlayer.getJSONObject("force"));
        Vector2 playerScale = Vector2.parseJson(jsonPlayer.getJSONObject("scale"));
        Color playerColor = new Color(jsonPlayer.getInt("color"));
        return new Player(playerPosition, playerVelocity, playerForce, playerScale, playerColor);
    }



}
