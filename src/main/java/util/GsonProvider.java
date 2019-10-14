package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {

    public static Gson getGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
}
