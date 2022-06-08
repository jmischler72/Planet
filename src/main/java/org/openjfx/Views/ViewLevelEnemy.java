package org.openjfx.Views;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import org.openjfx.Models.Character.Enemy.Enemy;
import org.openjfx.Models.Game;
import org.openjfx.Models.Level.LevelEnemy;
import org.openjfx.ViewElements.LevelEnemy.ButtonMenu;
import org.openjfx.ViewElements.LevelEnemy.EnemyComponent;

import java.util.ArrayList;

import static javafx.util.Duration.millis;

public class ViewLevelEnemy extends View{
    private static final int WIDTH_ENEMY = 200;

    private final int X_ENEMY = WIDTH/2;
    private final int Y_ENEMY = HEIGHT/2-150;

    private boolean canPlay = true;

    private LevelEnemy levelEnemy;
    private Game game;


    public ViewLevelEnemy(Pane pane, Game game) {
        super(pane);

        levelEnemy = (LevelEnemy) game.getCurrentLevel();

        setBackground("random_background.jpg");

        this.game = game;

        render();

    }

    private void render(){
        ArrayList<EnemyComponent> enemies_components = new ArrayList<EnemyComponent>();

        for (int i = 0; i < levelEnemy.getEnemies().size(); i++) {

            EnemyComponent enemy = new EnemyComponent(levelEnemy.getEnemies().get(i));
            enemy.setLayoutX(X_ENEMY+i*300);
            enemy.setLayoutY(Y_ENEMY);
            addElement(enemy);
            enemies_components.add(enemy);
            for(Animation animation: enemy.getAnimations()){
                addAnimation(animation);
            }

        }

        ButtonMenu b = new ButtonMenu( new double[]{80,HEIGHT-60}, new double[]{100,50}, null, "test");

        b.setOnAction((playEvent) -> {
            int i = 0;
            for (Enemy enemy : levelEnemy.getEnemies()) {
                ButtonMenu button_enemy = new ButtonMenu( new double[]{80+i*100,HEIGHT-120}, new double[]{100,50}, null, enemy.getName());
                int finalI = i;
                button_enemy.setOnAction((f) -> {
                    animationAttack((int)enemies_components.get(finalI).getLayoutX());
                    enemy.getAttacked(game.getPlayer());
                    enemies_components.get(finalI).updateHealth();
                    if(enemy.getHealth()<=0){
                        this.getPane().getChildren().remove(enemies_components.get(finalI));
                    }
                });
                addElement(button_enemy);
                i++;
            }
        });
        addElement(b);

        ButtonMenu b2 = new ButtonMenu( new double[]{200,HEIGHT-60}, new double[]{100,50}, null, "quit");

        b2.setOnAction((playEvent) -> {
            ViewLevelSelector levelView = new ViewLevelSelector(new AnchorPane(), game);
            ViewTransition viewTransition = new ViewTransition(new AnchorPane(),this, levelView, "Select");
        });
        addElement(b2);


    }
    private void animationAttack(int x){
        SVGPath svg = new SVGPath();
        svg.setFill(Color.YELLOW);
        svg.setContent("m 0 59.82677 l 27.527777 -8.654488 l -19.512508 -21.258898 l 28.167 6.268881 l -6.268881 -28.167 l 21.258898 19.512508 l 8.654488 -27.527777 l 8.654491 27.527777 l 21.258896 -19.512508 l -6.2688828 28.167 l 28.167 -6.268881 l -19.512512 21.258898 l 27.527779 8.654488 l -27.527779 8.654491 l 19.512512 21.258896 l -28.167 -6.2688828 l 6.2688828 28.167 l -21.258896 -19.512512 l -8.654491 27.527779 l -8.654488 -27.527779 l -21.258898 19.512512 l 6.268881 -28.167 l -28.167 6.2688828 l 19.512508 -21.258896 z");
        addElement(svg);
        TranslateTransition move = new TranslateTransition();
        move.setNode(svg);
        move.setFromX(0);
        move.setFromY(HEIGHT);
        move.setToX(x);
        move.setToY(Y_ENEMY+50);
        move.setDuration(millis(200));
        move.setOnFinished(e -> {
            delay(200,() ->{svg.setVisible(false);});
        });
        move.play();
    }


    private void renderSandAnimation() {
        Rectangle rectangle = new Rectangle(WIDTH*1.6,HEIGHT);
        rectangle.setOpacity(0.6);

        Image image = new Image(getClass().getResource("sand.png").toExternalForm());
        rectangle.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        addElement(rectangle);
        TranslateTransition move = new TranslateTransition();
        move.setNode(rectangle);
        move.setFromX(WIDTH+500);

        move.setToX(-WIDTH*1.6);
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
