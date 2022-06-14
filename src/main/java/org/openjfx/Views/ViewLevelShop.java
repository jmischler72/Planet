package org.openjfx.Views;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import org.openjfx.Models.Game;
import org.openjfx.Models.Level.LevelShop;
import org.openjfx.Models.Character.Player;
import org.openjfx.Models.Shop.Armor;
import org.openjfx.Models.Shop.Item;
import org.openjfx.Models.Shop.Weapon;
import org.openjfx.ViewElements.LevelEnemy.ButtonMenu;
import org.openjfx.ViewElements.LevelShop.EquipedItem;
import org.openjfx.ViewElements.LevelShop.ButtonItem;
import org.openjfx.ViewElements.LevelShop.Stat;

import java.util.ArrayList;

public class ViewLevelShop extends View {

    private static final int ITEM_AREA_SIZE = 640;
    private static final int ICON_SIZE = 140;
    private final ArrayList<ButtonItem> icons = new ArrayList<>();
    private final LevelShop level;
    private ArrayList<Item> equipedItems = new ArrayList<>();
    private final ArrayList<EquipedItem> equipedItemsView = new ArrayList<>();
    private final Game game;

    public ViewLevelShop(Game game) {
        super(new AnchorPane());
        this.game = game;
        setBackground("shop_background.png");
        this.level = new LevelShop();
        fetchPlayerItems();
        createItemEquipedRectangle();
        createIcons();

        ButtonMenu b2 = new ButtonMenu(new double[]{100, 50}, "quit");
        b2.setLayoutX(200);
        b2.setLayoutY(HEIGHT-60);
        b2.setOnAction((playEvent) -> {
            ViewLevelSelector levelView = new ViewLevelSelector(game);
            ViewTransition viewTransition = new ViewTransition(this, levelView, "Select");
            viewTransition.render();
        });
        addElement(b2);

        StackPane goldsStack = new StackPane();
        Label golds = new Label(String.valueOf(game.getPlayer().getGold()));
        golds.setFont(new Font(30));

        goldsStack.getChildren().add(golds);
        goldsStack.setLayoutX(WIDTH - 200);
        goldsStack.setLayoutY(HEIGHT - 60);
        addElement(goldsStack);

        ButtonMenu b3 = new ButtonMenu(new double[]{100, 50}, "+ 100 g");
        b3.setLayoutX(WIDTH-100);
        b3.setLayoutY(HEIGHT-60);
        b3.setOnAction((playEvent) -> {
            game.getPlayer().addGolds(100);
            refresh();
        });
        addElement(b3);

        setIconAction();
        createPlayerStatsRectangle();
        setItemToolTipData();
        setEquipedItemsToolTipData();
    }

    protected void setItemToolTipData() {
        for(ButtonItem item : icons) {
            String t = "";
            switch (item.getItem().getType()) {
                case Weapon:
                    Weapon w = (Weapon) item.getItem();
                    t = "Nom :" + w.getName() +
                            "\nDegats :" + w.getDamage() +
                            "\nPV :" + w.getPv() +
                            "\nCritique :" + w.getCritique() +
                            "\n\nCout : " + w.getCost();
                    break;
                case Armor:
                    Armor a = (Armor) item.getItem();
                    t = "Nom :" + a.getName() +
                            "\nArmure :" + a.getArmor() +
                            "\nPV :" + a.getPv() +
                            "\nEsquive :" + a.getEsquive() +
                            "\n\nCout : " + a.getCost();
                    break;
            }

            item.getDescription().setText(t);
        }
    }

    protected void setEquipedItemsToolTipData() {
        for (EquipedItem item : equipedItemsView) {
            String t = "";
            switch (item.getItem().getType()) {
                case Weapon:
                    Weapon w = (Weapon) item.getItem();
                    t = "Nom :" + w.getName() +
                            "\nDegats :" + w.getDamage() +
                            "\nPV :" + w.getPv() +
                            "\nCritique :" + w.getCritique() +
                            "\n\nCout : " + w.getCost();
                    break;
                case Armor:
                    Armor a = (Armor) item.getItem();
                    t = "Nom :" + a.getName() +
                            "\nArmure :" + a.getArmor() +
                            "\nPV :" + a.getPv() +
                            "\nEsquive :" + a.getEsquive() +
                            "\n\nCout : " + a.getCost();
                    break;
            }

            item.getDescription().setText(t);
        }
    }

    public void fetchPlayerItems() {
        equipedItems = game.getPlayer().getItems();
    }

    public void refresh() {
        renderView(new ViewLevelShop(game));
    }

    public void createItemEquipedRectangle() {
        BorderPane container = new BorderPane();
        container.setBackground(
                new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(30), null))
        );
        container.setPrefSize(400, 500);
        container.setLayoutX(WIDTH - 500);
        container.setLayoutY(HEIGHT - 700);

        Label title = new Label("Equipement actuel : ");
        title.setFont(new Font(30));
        title.setAlignment(Pos.BASELINE_CENTER);
        container.setTop(title);

        VBox itemsArea = new VBox();
        container.setCenter(itemsArea);
        for (Item i : equipedItems) {
            EquipedItem ei = new EquipedItem(i);
            equipedItemsView.add(ei);
            itemsArea.getChildren().add(ei);
        }

        addElement(container);
    }

    public void createPlayerStatsRectangle() {
        FlowPane container = new FlowPane();
        container.setBackground(
                new Background(new BackgroundFill(Color.LIGHTGRAY, null, null))
        );
        container.setOrientation(Orientation.VERTICAL);
        container.setPrefSize(200, 150);
        container.setLayoutX(WIDTH - 500);
        container.setLayoutY(HEIGHT - 160);

        Text title = new Text("Statistiques actuelles : ");
        title.setFont(new Font(20));

        Player player = game.getPlayer();
        Font statFont = new Font(15);

        Stat vie = new Stat("Vie : " + player.getHealth(), statFont, Color.GREEN);
        Stat resource = new Stat("Ressource : " + player.getRessource(), statFont, Color.DARKORANGE);
        Stat armor = new Stat("Armure : " + player.getShield(), statFont, Color.BLUE);
        Stat damage = new Stat("Dégats : " + player.getDamage(), statFont, Color.RED);
        Stat critique = new Stat("Critique : " + player.getCritique(), statFont, Color.BLUEVIOLET);
        Stat esquive = new Stat("Esquive : " + player.getDodge(), statFont, Color.FORESTGREEN);

        container.getChildren().addAll(title, vie, resource, armor, damage, critique, esquive);
        addElement(container);
    }

    private void createIcons() {
        ArrayList<Item> items = level.getItems();
        FlowPane container = new FlowPane();
        container.setAlignment(Pos.TOP_CENTER);
        container.setVgap(30);
        container.setHgap(20);
        container.setBackground(
                new Background(new BackgroundFill(Color.rgb(226, 211, 161), null, null))
        );
        container.setPadding(new Insets(10));

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (equipedItems.stream().anyMatch(e -> e.getId() == item.getId())) continue;

            double[] position = new double[]{(214 + i * (ICON_SIZE + 46)), 214};
            ButtonItem icon = new ButtonItem(position, new double[]{ICON_SIZE, ICON_SIZE}, new Rectangle(ICON_SIZE, ICON_SIZE), items.get(i));
            icons.add(icon);
            container.getChildren().add(icon);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(container);
        scrollPane.setLayoutX(115);
        scrollPane.setLayoutY(120);
        container.setPrefSize(650, 550);

        addElement(scrollPane);
    }

    private void setIconAction() {
        for (ButtonItem item : icons) {
            item.setOnAction((event) -> {
                Player player = game.getPlayer();
                Boolean isSuccess = player.buy(item.getItem());
                if(!isSuccess) {
                    Label label = new Label("Vous n'avez pas assez d'argent !");
                    label.setFont(new Font(15));
                    Popup popup = new Popup();
                    label.setBackground(new Background(
                            new BackgroundFill(Color.RED, new CornerRadii(20), Insets.EMPTY)
                    ));
                    popup.getContent().add(label);
                    label.setMinWidth(97);
                    label.setMinHeight(63);
                    popup.show(this.getWindow());
                    delay(1000, () -> {
                        popup.hide();
                    });
                    return;
                }

                refresh();
            });
        }
    }

    public LevelShop getLevel() {
        return level;
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
