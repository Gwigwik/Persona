package ui;

import entities.CharacterElement;
import entities.StatStatus;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ResourceLoader;

public class IconProvider {
    public static ImageView getCharacterElementIcon(CharacterElement element, int size) {
//        Image image = ResourceLoader.loadImage("/images/elements/" + element.getFileName() + ".png");
        Image image = ResourceLoader.loadImage("/images/elements/fire.png");
        ImageView view = new ImageView(image);
        view.setFitWidth(size);
        view.setFitHeight(size);
        return view;
    }
    
    public static ImageView getCharacterStatStatusIcon(StatStatus statStatus, int size) {
        Image image = ResourceLoader.loadImage("/images/stats/" + statStatus.getFileName() + ".png");
        ImageView view = new ImageView(image);
        view.setFitWidth(size);
        view.setFitHeight(size);
        return view;
    }
}
