package org.openjfx.Views;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.*;
import javafx.concurrent.Task;
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
import org.openjfx.Models.Character.Enemy.Enemy;
import org.openjfx.Models.Game;
import org.openjfx.Models.Level.LevelEnemy;
import org.openjfx.ViewElements.LevelEnemy.ButtonMenu;
import org.openjfx.ViewElements.LevelEnemy.EnemyComponent;
import org.openjfx.ViewElements.LevelEnemy.FinalVue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static javafx.util.Duration.millis;

public class ViewLevelEnemy extends View {
    private static final int WIDTH_ENEMY = 200;
    private static final int PLAYER_HEALTH_BAR_WIDTH = 600;

    private final int X_ENEMY = WIDTH / 2 - 150;
    private final int Y_ENEMY = HEIGHT / 2 - 150;

    private BooleanProperty canPlay = new SimpleBooleanProperty();
    private IntegerProperty playerHealth = new SimpleIntegerProperty();
    private StringProperty textPlayerHealth = new SimpleStringProperty();

    private final LevelEnemy levelEnemy;
    private final Game game;

    public ViewLevelEnemy(Pane pane, Game game) {
        super(pane);

        levelEnemy = (LevelEnemy) game.getCurrentLevel();

        setBackground("random_background.jpg");

        this.game = game;
        game.getPlayer().setHealth(game.getPlayer().getMaxHealth());

        canPlay.set(true);
        playerHealth.set(game.getPlayer().getHealth() / game.getPlayer().getMaxHealth() * PLAYER_HEALTH_BAR_WIDTH);
        textPlayerHealth.set(String.valueOf(game.getPlayer().getHealth()));

        playerHealth.addListener((observable, oldValue, newValue) -> {
            if (!game.getPlayer().isAlive()) {
                FinalVue vue = new FinalVue(false, WIDTH, HEIGHT);
                vue.getQuitButton().setOnAction((playEvent) -> {
                    ViewLevelSelector levelView = new ViewLevelSelector(new AnchorPane(), game);
                    ViewTransition viewTransition = new ViewTransition(new AnchorPane(), this, levelView, "Select");
                    viewTransition.render();
                });
                addElement(vue);
            }

            if (levelEnemy.isDone()) {
                FinalVue vue = new FinalVue(true, WIDTH, HEIGHT);
                vue.getQuitButton().setOnAction((playEvent) -> {
                    ViewLevelSelector levelView = new ViewLevelSelector(new AnchorPane(), game);
                    ViewTransition viewTransition = new ViewTransition(new AnchorPane(), this, levelView, "Select");
                    viewTransition.render();
                });

                game.getCurrentPlanet().addDoneLevel(this.levelEnemy);
                game.getPlayer().addGolds(500);
                addElement(vue);
            }
        });

        render();
    }

    private void render(){
        ArrayList<EnemyComponent> enemies_components = new ArrayList<EnemyComponent>();

        for (int i = 0; i < levelEnemy.getEnemies().size(); i++) {
            EnemyComponent enemy = new EnemyComponent(levelEnemy.getEnemies().get(i));
            enemy.visibleProperty().bind(enemy.isDeadProperty().not());
            enemy.setLayoutX(X_ENEMY + i * 300);
            enemy.setLayoutY(Y_ENEMY);
            addElement(enemy);
            enemies_components.add(enemy);
            for (Animation animation : enemy.getAnimations()) {
                addAnimation(animation);
            }
        }
        renderPlayerButtons(enemies_components);
        renderPlayerHealthBar();
    }

    private void renderPlayerHealthBar() {
        StackPane pane = new StackPane();
        pane.setLayoutX(500);
        pane.setLayoutY(HEIGHT - 80);

        Rectangle bar = new Rectangle(PLAYER_HEALTH_BAR_WIDTH, 60, Color.RED);
        bar.widthProperty().bind(playerHealth);

        Text health = new Text(String.valueOf(game.getPlayer().getHealth()));
        try {
            health.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/main_font.ttf"), 40));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        health.textProperty().bind(textPlayerHealth);

        pane.getChildren().addAll(bar, health);
        addElement(pane);
    }

    private void renderPlayerButtons(ArrayList<EnemyComponent> enemies_components) {
        ButtonMenu b = new ButtonMenu(new double[]{100, 50}, "test");
        b.setLayoutX(80);
        b.setLayoutY(HEIGHT - 60);

        b.visibleProperty().bind(canPlay);

        b.setOnAction((playEvent) -> {
            int i = 0;
            for (Enemy enemy : levelEnemy.getEnemies()) {
                int finalI = i;

                ButtonMenu button_enemy = new ButtonMenu(new double[]{100,50}, enemy.getName());
                button_enemy.setLayoutX(80+i*100);
                button_enemy.setLayoutY(HEIGHT-120);
                button_enemy.visibleProperty().bind(enemies_components.get(finalI).isDeadProperty().not());
                button_enemy.setOnAction((f) -> {
                    attackEnemy(enemies_components.get(finalI));
                    canPlay.set(false);
                    attackedByEnemy(enemies_components.get(finalI));
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
            ViewTransition viewTransition = new ViewTransition(new AnchorPane(),this, levelView, "Select");
            viewTransition.render();
        });

        addElement(b);
        addElement(b2);
    }

    private void attackEnemy(EnemyComponent enemyComponent){
        animationAttack((int)enemyComponent.getLayoutX());
        game.getPlayer().attack(enemyComponent.getEnemy());
        enemyComponent.updateHealth();
        if (!enemyComponent.getEnemy().isAlive()) {
            enemyComponent.setIsDead(true);
            levelEnemy.remove(enemyComponent.getEnemy());
        }
    }

    private void attackedByEnemy(EnemyComponent enemyComponent) {
        RotateTransition rotateTransition = new RotateTransition(millis(200));
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setNode(enemyComponent);
        rotateTransition.play();
        enemyComponent.getEnemy().attack(game.getPlayer());
        playerHealth.set(PLAYER_HEALTH_BAR_WIDTH * game.getPlayer().getHealth() / game.getPlayer().getMaxHealth());
        textPlayerHealth.set(String.valueOf(game.getPlayer().getHealth()));
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
