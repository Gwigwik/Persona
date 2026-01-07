package ui;

import entities.CharacterElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ResourceLoader;

public class ElementIconProvider {
    public static ImageView getIcon(CharacterElement element, double size) {
        Image image = ResourceLoader.loadImage("/images/elements/" + element.getFileName() + ".png");
        ImageView view = new ImageView(image);
        view.setFitWidth(size);
        view.setFitHeight(size);
        return view;
    }
}
