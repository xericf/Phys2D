package persistence;

import org.json.JSONObject;
import ui.object.World;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// CREDITS: Inspired by https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// A writer used to write and save data in JSON files
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String path;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.path = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer for use; throws FileNotFoundException if path file cannot
    // be found.
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(path);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of a World to a file
    public void write(World world) {
        JSONObject worldData = world.toJson();

        // Pretty prints with TAB indents
        saveJsonToFile(worldData.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer. Should always close after using the writer.
    public void closeWriter() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to a file with current path
    private void saveJsonToFile(String json) {
        writer.print(json);
    }

}
