package core;

import com.google.gson.*;

import java.awt.*;
import java.lang.reflect.Type;

public class ColorAdapter implements JsonSerializer<Color>, JsonDeserializer<Color> {
    /**
     * Converts a color type from a JSON file, to an actual color inside java
     * @return The RGB values of a color, that were saved into a JSON file
     */
    @Override
    public Color deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return new Color(jsonObject.get("rgb").getAsInt(), true);
    }

    /**
     * @return A color as a JSON entry
     */
    @Override
    public JsonElement serialize(Color color, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("rgb", color.getRGB());
        return jsonObject;
    }
}
