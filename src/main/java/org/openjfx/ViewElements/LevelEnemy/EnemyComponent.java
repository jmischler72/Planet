package org.openjfx.ViewElements.LevelEnemy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.openjfx.Models.Enemy.Enemy;

import java.util.ArrayList;
import java.util.Random;

import static javafx.util.Duration.millis;

public class EnemyComponent extends GridPane {

    private final Enemy enemy;

    private ArrayList<Animation> animations = new ArrayList<Animation>();

    private static final int WIDTH_ENEMY = 200;
    private static final int HEIGHT_ENEMY = 200;

    public EnemyComponent(Enemy enemy){
        super();
        this.enemy = enemy;
        render();

    }

    private void render(){

        Rectangle rectangle = new Rectangle(WIDTH_ENEMY,HEIGHT_ENEMY);
        Image image = new Image(getClass().getResource(enemy.getType()+".png").toExternalForm());
        rectangle.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        setRowIndex(rectangle, 1);
        setColumnIndex(rectangle, 0);

        Text text = new Text(100, 50, enemy.getName());
        text.setFill(Color.WHITE);
        text.setFont(new Font(15));
        setRowIndex(text, 0);
        setColumnIndex(text, 0);

        renderEnemyAnimation(rectangle);
        renderEnemyAnimation(text);
        getChildren().addAll(rectangle, text);
    }

    private void renderEnemyAnimation(Shape rectangle) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
            final Random random = new Random();
//            int movingStep = (0+i*20)%60;
            int movingStep = 0;

            @Override
            public void handle(Event event) {
                movingStep++;
                double angleAlpha = movingStep * (Math.PI / 30);

                // p(x) = x(0) + r * sin(a)
                // p(y) = y(y) - r * cos(a)

                TranslateTransition move = new TranslateTransition();
                move.setNode(rectangle);
                move.setToX(20 * Math.sin(angleAlpha));
                move.setToY(20 * Math.cos(angleAlpha));
                move.setDuration(millis(50));
                move.playFromStart();

                // Reset after one orbit.
                if (movingStep == 60) {
                    movingStep = 0;
                }
            }
        }), new KeyFrame(millis(50)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        animations.add(timeline);

//        addAnimation(timeline);
    }

    public ArrayList<Animation> getAnimations(){
        return animations;
    }
}
