package org.openjfx.Views;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import org.openjfx.Models.Shop.Armor;
import org.openjfx.Models.Shop.Item;
import org.openjfx.Models.Shop.Weapon;
import org.openjfx.ViewElements.LevelShop.ItemIcon;
import org.openjfx.Models.Level.LevelShop;
import org.openjfx.Models.Personage.Player;
import org.openjfx.ViewElements.LevelEnemy.ButtonMenu;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ViewLevelShop extends View{

    private static final int ITEM_AREA_SIZE = 640;
    private static final int ICON_SIZE = 140;
    private ArrayList<ItemIcon> icons = new ArrayList<>();
    private LevelShop level;
    private ArrayList<Item> equipedItems = new ArrayList<>();

    public ViewLevelShop(Pane pane, ViewManager viewManager) {
        super(pane, viewManager);
        setBackground("shop_background.png");
        this.level = new LevelShop();
        fetchPlayerItems();
        createIcons();

        ButtonMenu b2 = new ButtonMenu( new double[]{200,viewManager.getSize()[1]-60}, new double[]{100,50}, null, "quit");
        b2.setOnAction((playEvent) -> {
            viewManager.getGame().setCurrentLevel(null);
            ViewLevelSelector levelView = new ViewLevelSelector(new AnchorPane(), viewManager);
            ViewTransition viewTransition = new ViewTransition(new AnchorPane(), viewManager, levelView, "Select");
            viewManager.renderView(viewTransition);
        });
        addElement(b2);

        StackPane goldsStack = new StackPane();
        Label golds = new Label(String.valueOf(viewManager.getGame().getPlayer().getGold()));
        golds.setFont(new Font(30));

        goldsStack.getChildren().add(golds);
        goldsStack.setLayoutX(viewManager.getSize()[0] - 200);
        goldsStack.setLayoutY(viewManager.getSize()[1] - 60);
        addElement(goldsStack);

        ButtonMenu b3 = new ButtonMenu( new double[]{viewManager.getSize()[0] - 100,viewManager.getSize()[1]-60}, new double[]{100,50}, null, "+ 100 g");
        b3.setOnAction((playEvent) -> {
            viewManager.getGame().getPlayer().addGolds(100);
            refresh();
        });
        addElement(b3);

        setIconAction();
        createItemEquipedRectangle();
        setItemToolTipData();
    }

    protected void setItemToolTipData() {
        for(ItemIcon item : icons) {
            String t = "";
            switch (item.getItem().getType()) {
                case Weapon:
                    Weapon w = (Weapon) item.getItem();
                    t = "Nom :" + w.getName() +
                            "\nDegats :" + w.getDamage() +
                            "\nCout : " + w.getCost();
                    break;
                case Armor:
                    Armor a = (Armor) item.getItem();
                    t = "Nom :" + a.getName() +
                            "\nCout : " + a.getCost();
                    break;
            }

            item.getDescription().setText(t);
        }
    }

    public void fetchPlayerItems() {
        equipedItems = viewManager.getGame().getPlayer().getItems();
    }

    public void refresh() {
        viewManager.renderView(new ViewLevelShop(new AnchorPane(), viewManager));
    }

    public void createItemEquipedRectangle() {
        Rectangle s = new Rectangle(400, 500, Color.LIGHTGRAY);
        StackPane stack = new StackPane();

        Text title = new Text("Equipement actuel : ");
        title.setFont(new Font(30));
        stack.setAlignment(title, Pos.TOP_CENTER);

        String t = "";
        for(Item i : equipedItems) {
            t += i.getName() + "\n";
        }

        Text text = new Text(t);
        text.setFont(new Font(20));

        stack.getChildren().addAll(s, title, text);
        stack.setLayoutX(viewManager.getSize()[0] - 500);
        stack.setLayoutY(viewManager.getSize()[1] - 700);
        addElement(stack);
    }

    private void createIcons() {
        ArrayList<Item> items = level.getItems();
        for(int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if(equipedItems.stream().anyMatch(e -> e.getId() == item.getId())) continue;

            double[] position = new double[] {(216 + i*(ICON_SIZE + 46)), 216};
            ItemIcon icon = new ItemIcon(position, new double[] {ICON_SIZE, ICON_SIZE}, new Rectangle(ICON_SIZE, ICON_SIZE), items.get(i));
            icons.add(icon);
            addElement(icon);
        }
    }

    private void setIconAction() {
        for (ItemIcon item : icons) {
            item.setOnAction((event) -> {
                Player player = viewManager.getGame().getPlayer();
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
                    popup.show(viewManager.getMainStage());
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
