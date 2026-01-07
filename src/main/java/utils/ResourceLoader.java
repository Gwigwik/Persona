package utils;

import javafx.scene.image.Image;

import java.io.InputStream;

public class ResourceLoader {

    private ResourceLoader() {}

    public static Image loadImage(String path) {
        InputStream stream = ResourceLoader.class.getResourceAsStream(path);

        if (stream == null) {
            throw new IllegalArgumentException("Resource cannot be found : " + path);
        }

        return new Image(stream);
    }
}
