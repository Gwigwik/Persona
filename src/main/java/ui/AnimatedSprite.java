package ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimatedSprite {

    private final ImageView view;
    private final int frameCount;
    private final int frameWidth;
    private final int frameHeight;
    private int currentFrame = 0;
    private final Timeline timeline;

    public AnimatedSprite(Image spriteSheet, int frameCount, int frameWidth, int frameHeight, double frameDurationMs) {

        this.frameCount = frameCount;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        this.view = new ImageView(spriteSheet);
        this.view.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        this.view.setPreserveRatio(true);

        timeline = new Timeline(
            new KeyFrame(
                Duration.millis(frameDurationMs),
                _ -> nextFrame()
            )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void nextFrame() {
        currentFrame = (currentFrame + 1) % frameCount;
        view.setViewport(
            new Rectangle2D(
                currentFrame * frameWidth,
                0,
                frameWidth,
                frameHeight
            )
        );
    }

    public void play() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public ImageView getView() {
        return view;
    }
    
    public BooleanProperty visibleProperty() {
        return view.visibleProperty();
    }
}
