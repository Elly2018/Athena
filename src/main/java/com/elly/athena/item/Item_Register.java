package com.elly.athena.item;

import com.elly.athena.item.equipment.belt.LeatherBelt;
import com.elly.athena.item.equipment.ring.Single_Earring;
import com.elly.athena.item.etc.Coin;
import com.elly.athena.item.etc.CoinBag;
import com.elly.athena.item.etc.GoldenCoin;
import com.elly.athena.item.etc.GoldenCoinBag;
import com.elly.athena.item.potion.*;
import com.elly.athena.item.skill.commom.Healing;
import com.elly.athena.item.skill.commom.SpeedBoost;
import com.elly.athena.item.skill.magician.MagicBall;
import com.elly.athena.item.skill.warrior.HPBoost;
import com.elly.athena.item.skill.warrior.WindSlash;
import com.elly.athena.item.weapon.archer.Bow;
import com.elly.athena.item.weapon.magician.Staff;
import com.elly.athena.item.weapon.magician.Wand;
import com.elly.athena.item.weapon.warrior.Spear;
import com.elly.athena.item.weapon.warrior.Sword;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;

import java.util.HashMap;
import java.util.function.Supplier;

import static com.elly.athena.Athena.ITEMS;
import static com.elly.athena.Athena.MODID;

public class Item_Register {

    public interface ItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
        Item get_binding(Item.Properties props);
    }

    public static HashMap<String, Supplier<Item>> RegisterDict = new HashMap<String, Supplier<Item>>();
    public static HashMap<String, Supplier<Potion>> RegisterDict_p = new HashMap<String, Supplier<Potion>>();

    private static ItemRegisterData[] AllItems = new ItemRegisterData[0];

    public static void RegisterAllItems() {
        AllItems = new ItemRegisterData[]{
                // system
                new Coin(),
                new GoldenCoin(),
                new CoinBag(),
                new GoldenCoinBag(),
                // potion
                new HP_Potion(),
                new HP_Potion_Large(),
                new MP_Potion(),
                new MP_Potion_Large(),
                new Elixir(),
                // ring
                new Single_Earring(),
                // belt
                new LeatherBelt(),
                // weapon warrior
                new Sword(),
                new Spear(),
                // weapon magician
                new Staff(),
                new Wand(),
                // weapon archer
                new Bow(),
                // skill common
                new SpeedBoost(),
                new Healing(),
                // skill warrior
                new HPBoost(),
                new WindSlash(),
                // skill magician
                new MagicBall(),
                // skill archer
        };

        for (ItemRegisterData itemRegisterData : AllItems){
            String key = itemRegisterData.get_key();
            Item.Properties behaviour = itemRegisterData.get_behaviour();
            // https://stackoverflow.com/questions/79318791/item-texture-blank-in-minecraft-1-21-4-forge-mod
            behaviour.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(MODID + ":" + key)));
            Supplier<Item> buffer = ITEMS.register(key, () -> itemRegisterData.get_binding(behaviour));
            RegisterDict.put(key, buffer);
        }
    }
}
