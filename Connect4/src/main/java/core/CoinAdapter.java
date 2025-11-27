package core;

import com.google.gson.*;
import gui.Coin;

import java.awt.*;
import java.lang.reflect.Type;

public class CoinAdapter implements JsonSerializer<Coin>, JsonDeserializer<Coin> {

    @Override
    public Coin deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        int x = obj.get("x").getAsInt();
        int y = obj.get("y").getAsInt();
        int width = obj.get("width").getAsInt();
        int height = obj.get("height").getAsInt();
        int gridX = obj.get("gridX").getAsInt();
        int gridY = obj.get("gridY").getAsInt();
        Color color = jsonDeserializationContext.deserialize(obj.get("color"), Color.class);

        if(color.equals(Color.RED)){
            return new Coin.RedCoin(x, y, height, width, gridX, gridY);
        } else if(color.equals(Color.YELLOW)){
            return new Coin.YellowCoin(x, y, height, width, gridX, gridY);
        }
        return null;
    }

    @Override
    public JsonElement serialize(Coin coin, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", coin.getClass().getSimpleName());
        obj.addProperty("x", coin.getX());
        obj.addProperty("y", coin.getY());
        obj.addProperty("width", coin.getWidth());
        obj.addProperty("height", coin.getHeight());
        obj.addProperty("gridX", coin.getColumn());
        obj.addProperty("gridY", coin.getRow());
        obj.add("color", jsonSerializationContext.serialize(coin.getColor()));
        return obj;
    }
}
