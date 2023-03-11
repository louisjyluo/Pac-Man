package persistence;

import org.json.JSONObject;

//Interface for Json files
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
