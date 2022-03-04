package persistence;


import org.json.JSONObject;

public interface Savable {

    // EFFECTS: Formats object's data in JSON
    JSONObject toJson();

}
