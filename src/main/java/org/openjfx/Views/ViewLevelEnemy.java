package org.openjfx.Views;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.openjfx.Models.Enemy.Enemy;
import org.openjfx.Models.Level.Level;
import org.openjfx.Models.Level.LevelEnemy;
import org.openjfx.ViewElements.LevelEnemy.ButtonMenu;

import java.util.Random;

import static javafx.util.Duration.millis;

public class ViewLevelEnemy extends View{
    private static final int WIDTH_ENEMY = 200;
    private static final int HEIGHT_ENEMY = 200;

    private int X_ENEMY = viewManager.getSize()[0]/2;
    private int Y_ENEMY = viewManager.getSize()[1]/2-150;


    public ViewLevelEnemy(Pane pane, ViewManager viewManager) {
        super(pane, viewManager);
        LevelEnemy levelEnemy = (LevelEnemy) viewManager.getGame().getCurrentLevel();

        setBackground("random_background.jpg");


        ButtonMenu b = new ButtonMenu( new double[]{80,viewManager.getSize()[1]-60}, new double[]{100,50}, null, "test");

        b.setOnAction((playEvent) -> {
            renderSandAnimation();
            for (int i = 0; i < levelEnemy.getEnemies().size(); i++) {

                ButtonMenu button_enemy = new ButtonMenu( new double[]{80+i*100,viewManager.getSize()[1]-120}, new double[]{100,50}, null, levelEnemy.getEnemies().get(i).getName());
                int finalI = i;
                button_enemy.setOnAction((f) -> { animationAttack(X_ENEMY-100*(levelEnemy.getEnemies().size()-1)+ finalI *(WIDTH_ENEMY+100));
                });
                addElement(button_enemy);
            }
        });
        addElement(b);

        ButtonMenu b2 = new ButtonMenu( new double[]{200,viewManager.getSize()[1]-60}, new double[]{100,50}, null, "quit");

        b2.setOnAction((playEvent) -> {
            viewManager.getGame().setCurrentLevel(null);
            ViewLevelSelector levelView = new ViewLevelSelector(new AnchorPane(), viewManager);
            ViewTransition viewTransition = new ViewTransition(new AnchorPane(), viewManager, levelView, "Select");
            viewManager.renderView(viewTransition);
        });
        addElement(b2);



        for (int i = 0; i < levelEnemy.getEnemies().size(); i++) {
            Rectangle rectangle = new Rectangle(WIDTH_ENEMY,HEIGHT_ENEMY);
            Image image = new Image(getClass().getResource(levelEnemy.getEnemies().get(i).getType()+".png").toExternalForm(), viewManager.getSize()[0], viewManager.getSize()[1], false, true);
            rectangle.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
            int x = X_ENEMY-100*(levelEnemy.getEnemies().size()-1)+i*(WIDTH_ENEMY+100);
            rectangle.setX(x);
            rectangle.setY(Y_ENEMY);

            Text text = new Text(100, 50, levelEnemy.getEnemies().get(i).getName());
            text.setFill(Color.WHITE);
            text.setFont(new Font(15));
            text.setX(x);
            text.setY(Y_ENEMY-100);

            Rectangle healtbarTank1 = Rectangle(200.0, 50.0, Color.RED);
            Rectangle healtbarTank2 = Rectangle(200.0, 50.0, Color.BLUE);

            renderEnemyAnimation(rectangle, i);
            addElement(rectangle);
            addElement(text);
        }
    }

    public Rectangle animationEnemy(double[] position ){
        Rectangle rectangle = new Rectangle(WIDTH_ENEMY,HEIGHT_ENEMY);
        return rectangle;
    }

    private void animationAttack(int x){
        SVGPath svg = new SVGPath();
        svg.setFill(Color.YELLOW);
        svg.setContent("m 0 59.82677 l 27.527777 -8.654488 l -19.512508 -21.258898 l 28.167 6.268881 l -6.268881 -28.167 l 21.258898 19.512508 l 8.654488 -27.527777 l 8.654491 27.527777 l 21.258896 -19.512508 l -6.2688828 28.167 l 28.167 -6.268881 l -19.512512 21.258898 l 27.527779 8.654488 l -27.527779 8.654491 l 19.512512 21.258896 l -28.167 -6.2688828 l 6.2688828 28.167 l -21.258896 -19.512512 l -8.654491 27.527779 l -8.654488 -27.527779 l -21.258898 19.512512 l 6.268881 -28.167 l -28.167 6.2688828 l 19.512508 -21.258896 z");
        addElement(svg);
        TranslateTransition move = new TranslateTransition();
        move.setNode(svg);
        move.setFromX(0);
        move.setFromY(viewManager.getSize()[1]);
        move.setToX(x);
        move.setToY(Y_ENEMY);
        move.setDuration(millis(200));
        move.setOnFinished(e -> {
            delay(200,() ->{svg.setVisible(false);});
        });
        move.play();


    }

    private void renderEnemyAnimation(Rectangle rectangle, int i) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
            Random random = new Random();
            int movingStep = (0+i*20)%60;

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

        addAnimation(timeline);
    }
    private void renderSandAnimation() {
        Rectangle rectangle = new Rectangle(viewManager.getSize()[0]*1.6,viewManager.getSize()[1]);
        rectangle.setOpacity(0.6);

        Image image = new Image(getClass().getResource("sand.png").toExternalForm());
        rectangle.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        addElement(rectangle);
        TranslateTransition move = new TranslateTransition();
        move.setNode(rectangle);
        move.setFromX(viewManager.getSize()[0]+500);

        move.setToX(-viewManager.getSize()[0]*1.6);
        move.setDuration(millis(5000));
        move.setOnFinished(e -> {
            delay(0,() ->{rectangle.setVisible(false);});
        });
        move.play();
    }

        public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }
    

}
