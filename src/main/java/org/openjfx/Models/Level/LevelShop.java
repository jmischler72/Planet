package org.openjfx.Models.Level;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openjfx.Models.Shop.Armor;
import org.openjfx.Models.Shop.Item;
import org.openjfx.Models.Shop.Weapon;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class LevelShop extends Level {

    private ArrayList<Item> items = new ArrayList<Item>();

    public LevelShop() {
        super("Marchand", LevelType.Shop);
        fetchItems();
    }

    private void fetchItems() {
        Object ob = null;
        try {
            ob = new JSONParser().parse(new FileReader(getClass().getResource("itemsInfos.json").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject json = (JSONObject) ob;

        Map weapons = ((Map)json.get("Weapons"));

        Iterator<Map.Entry> weaponItr = weapons.entrySet().iterator();
        while (weaponItr.hasNext()) {
            Map.Entry weapon = weaponItr.next();
            JSONObject values = (JSONObject) weapon.getValue();
            items.add(
                    new Weapon((long) values.get("Damage"),
                            (String) weapon.getKey(),//Name
                            (long) values.get("Cost"),
                            (long) values.get("ID"),
                            (long) values.get("PV"),
                            (long) values.get("Critique"),
                            (long) values.get("Resource"))
            );
        }

        Map armors = ((Map)json.get("Armors"));

        Iterator<Map.Entry> armorItr = armors.entrySet().iterator();
        while (armorItr.hasNext()) {
            Map.Entry armor = armorItr.next();
            JSONObject values = (JSONObject) armor.getValue();
            items.add(
                    new Armor((String) armor.getKey(),//Name
                            (long) values.get("Cost"),
                            (long) values.get("ID"),
                            (long) values.get("PV"),
                            (long) values.get("Armor"),
                            (long) values.get("Esquive"),
                            (long) values.get("Resource"))
            );
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public boolean buyItem() {
        return true;
    }
}