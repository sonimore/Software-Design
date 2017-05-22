package sprites;

/**
 * Created by sonimore on 5/21/2017.
 * Sprite subclass creating an fx scene with a fluttery butterfly.
 */

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.awt.*;

public class sonimore extends Sprite {
    private AudioClip audioClip;
    private ImageView imageView;

    public sonimore() {
        Image image = new Image(getClass().getResourceAsStream("/res/sonimore.gif"));
        this.imageView = new ImageView();
        this.imageView.setImage(image);
        this.getChildren().add(this.imageView);

        this.audioClip = new AudioClip(getClass().getResource("/res/sonimore.wav").toString());
    }

    @Override
    public void step() {
        this.getTransforms().add(new Rotate(-10, this.getSize().getX(),this.getSize().getY()));
        Point2D position = this.getPosition();
        this.setPosition(position.getX() -10, position.getY() -2);
        Point2D position2 = this.getPosition();
        Point2D velocity = this.getVelocity();
        this.setPosition(position2.getX() + velocity.getX(), position2.getY() + velocity.getY());

    }
    @Override
    public void setSize(double width, double height) {
      this.imageView.setFitWidth(100);
      this.imageView.setFitHeight(100);

    }

    @Override
    public void makeSound() {
        this.audioClip.play();
    }
}
