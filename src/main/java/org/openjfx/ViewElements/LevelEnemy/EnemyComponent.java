package org.openjfx.ViewElements.LevelEnemy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.openjfx.Models.Character.Enemy.Enemy;
import org.openjfx.Models.Character.Enemy.EnemyType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static javafx.util.Duration.millis;

public class EnemyComponent extends GridPane {

    private final EnemyType type;
    private final String name;
    private static final int ENEMY_HEALTHBAR_WIDTH = 200;

    private final ArrayList<Animation> animations = new ArrayList<Animation>();

    private static final int WIDTH_ENEMY = 200;
    private static final int HEIGHT_ENEMY = 200;

    private final IntegerProperty health = new SimpleIntegerProperty();

    private final BooleanProperty isDead = new SimpleBooleanProperty();
    private final BooleanProperty isSelected = new SimpleBooleanProperty();


    public EnemyComponent(EnemyType type, String name, int healthRatio){
        super();
        this.type = type;
        this.name = name;
        isDead.set(false);
        health.set(100*healthRatio);
        isSelected.set(false);
        render();

    }
    private void render(){

        Rectangle enemy_rectangle = new Rectangle(WIDTH_ENEMY,HEIGHT_ENEMY);
        Image image = new Image(getClass().getResource(type+".png").toExternalForm());
        enemy_rectangle.setFill(new ImagePattern(image, 0, 0, 1, 1, true));

        ColorAdjust colorAdjust = new ColorAdjust();

        colorAdjust.setBrightness(-0.5);
        isSelected.addListener((observable, oldValue, newValue) -> {
            if(isSelected.get()){
                this.setEffect(colorAdjust);
            }else {
                this.setEffect(null);
            }
        }
        );
        setOnMousePressed(event -> {
                    isSelected.set(!isSelected.get());
                }
            );
        setRowIndex(enemy_rectangle, 1);
        setRowSpan(enemy_rectangle,2);
        setColumnIndex(enemy_rectangle, 0);

        Text text = new Text(100, 50, name);
        try {
            text.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/main_font.ttf"), 13));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setRowIndex(text, 0);
        setColumnIndex(text, 0);

        Rectangle healthbar = new Rectangle(ENEMY_HEALTHBAR_WIDTH, 15, Color.RED);
        healthbar.widthProperty().bind(health);
        setRowIndex(healthbar, 0);
        setColumnIndex(healthbar, 0);

        renderEnemyAnimation(healthbar);
        renderEnemyAnimation(enemy_rectangle);
        renderEnemyAnimation(text);
        getChildren().addAll(enemy_rectangle,healthbar, text);
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
    }

    public ArrayList<Animation> getAnimations(){
        return animations;
    }

    public BooleanProperty isDeadProperty() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead.set(isDead);
    }

    public void setHealth(int health) {
        this.health.set(health);
    }
}
