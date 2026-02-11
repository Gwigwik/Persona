package ui;

import entities.resistances.Resistance;
import entities.spells.SpellElement;
import entities.stats.StatStatus;
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
	
    public static ImageView getCharacterElementIcon(SpellElement element, int size) {
        Image image = ResourceLoader.loadImage("/images/elements/icons/" + element.getFileName() + ".png");
        ImageView view = new ImageView(image);
        view.setFitWidth(size);
        view.setFitHeight(size);
        return view;
    }

    public static ImageView getCharacterElementSpellIcon(SpellElement element, int size) {
        Image image = ResourceLoader.loadImage("/images/elements/icons/" + element.getFileName() + "_spell.png");
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
    
    public static AnimatedSprite getAnimatedCharacterIcon(entities.Character character, int frameCount, int frameWidth, int frameHeight, int size, int duration) {
        Image spriteSheet = ResourceLoader.loadImage("/images/characters/" + character.getFileName() + ".png");
        AnimatedSprite sprite = new AnimatedSprite(spriteSheet, frameCount, frameWidth, frameHeight, duration);
        sprite.getView().setFitWidth(size);
        sprite.getView().setFitHeight(size);
        return sprite;
    }
}
