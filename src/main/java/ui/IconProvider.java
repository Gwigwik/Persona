package ui;

import entities.CharacterElement;
import entities.Resistance;
import entities.StatStatus;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.ResourceLoader;

public class IconProvider {
	public static ImageView getCharacterIcon(entities.Character character, int size) {
        Image image = ResourceLoader.loadImage("/images/characters/" + character.getFileName() + ".png");
        ImageView view = new ImageView(image);
        view.setFitWidth(size);
        view.setFitHeight(size);
        return view;
    }
	
    public static ImageView getCharacterElementIcon(CharacterElement element, int size) {
        Image image = ResourceLoader.loadImage("/images/elements/icons/" + element.getFileName() + ".png");
        ImageView view = new ImageView(image);
        view.setFitWidth(size);
        view.setFitHeight(size);
        return view;
    }
    
    public static ImageView getCharacterResIcon(Resistance resistance, int size) {
      Image image = ResourceLoader.loadImage("/images/elements/values/" + resistance.getFileName() + ".png");
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
    
    public static AnimatedSprite getAnimatedCharacterIcon(entities.Character character, int frameCount, int frameWidth, int frameHeight, int displaySize, int duration) {
        Image spriteSheet = ResourceLoader.loadImage("/images/characters/" + character.getFileName() + ".png");
        AnimatedSprite sprite = new AnimatedSprite(spriteSheet, frameCount, frameWidth, frameHeight, duration);
        sprite.getView().setFitWidth(displaySize);
        sprite.getView().setFitHeight(displaySize);
        return sprite;
    }
}
