package org.openjfx.Views;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

    private final BooleanProperty canPlay = new SimpleBooleanProperty();
    private final LevelEnemy levelEnemy;
    private final Game game;


    public ViewLevelEnemy(Pane pane, Game game) {
        super(pane);

        levelEnemy = (LevelEnemy) game.getCurrentLevel();

        setBackground("random_background.jpg");

        this.game = game;
        canPlay.set(true);

        render();

    }

    private void render(){
        ArrayList<EnemyComponent> enemies_components = new ArrayList<EnemyComponent>();
        int i =0;
        for (Enemy enemy : levelEnemy.getEnemies()) {
            EnemyComponent enemy_component= new EnemyComponent(enemy.getType(),enemy.getName(),enemy.getHealth()/enemy.getMaxHealth());
            enemy_component.visibleProperty().bind(enemy_component.isDeadProperty().not());
            enemy_component.setLayoutX(X_ENEMY+i*300);
            enemy_component.setLayoutY(Y_ENEMY);
            addElement(enemy_component);
            enemies_components.add(enemy_component);
            for(Animation animation: enemy_component.getAnimations()){
                addAnimation(animation);
            }
            i++;
        }
        renderPlayerButtons(enemies_components);
    }

    private void renderPlayerButtons(ArrayList<EnemyComponent> enemies_components){
        ButtonMenu b = new ButtonMenu(new double[]{100,50}, "test");
        b.setLayoutX(80);
        b.setLayoutY(HEIGHT-60);

        b.visibleProperty().bind(canPlay);

        b.setOnAction((playEvent) -> {
            int i = 0;
            for (Enemy enemy : levelEnemy.getEnemies()) {
                int finalI = i;
                EnemyComponent enemy_component = enemies_components.get(finalI);

                ButtonMenu button_enemy = new ButtonMenu(new double[]{100,50}, enemy.getName());
                button_enemy.setLayoutX(80+i*100);
                button_enemy.setLayoutY(HEIGHT-120);
                button_enemy.visibleProperty().bind(enemy_component.isDeadProperty().not());
                button_enemy.setOnAction((f) -> {

                    game.getPlayer().attack(enemy);
                    enemy_component.setHealth(100*enemy.getHealth()/enemy.getMaxHealth());
                    if(enemy.getHealth()<=0){
                        enemy_component.setIsDead(true);
                    }
                    TranslateTransition attack = animationAttack((int) enemy_component.getLayoutX());
                    RotateTransition attacked = animationAttacked(enemy_component);
                    canPlay.set(false);

                    SequentialTransition seqT = new SequentialTransition(attack, attacked);
                    seqT.play();
                    enemy.attack(game.getPlayer());
                    canPlay.set(true);

                });
                addElement(button_enemy);
                i++;
            }
        });

        ButtonMenu b2 = new ButtonMenu(new double[]{100,50}, "quit");
        b2.setLayoutX(200);
        b2.setLayoutY(HEIGHT-60);

        b2.setOnAction((playEvent) -> {
            ViewLevelSelector levelView = new ViewLevelSelector(new AnchorPane(), game);
            ViewTransition viewTransition = new ViewTransition(new StackPane(),this, levelView, "Select");
        });

        addElement(b);
        addElement(b2);

    }

    private RotateTransition animationAttacked(EnemyComponent enemy){
        RotateTransition rotateTransition = new RotateTransition(millis(200));
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setNode(enemy);
        return rotateTransition;
    }

    private TranslateTransition animationAttack(int x){
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
        return move;
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
