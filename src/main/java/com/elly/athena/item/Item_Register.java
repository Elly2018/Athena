package com.elly.athena.item;

import com.elly.athena.Athena;
import com.elly.athena.item.etc.Coin;
import com.elly.athena.item.potion.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

public class Item_Register {

    public interface ItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
        Item get_binding(Item.Properties props);
    }

    public HashMap<String, Supplier<Item>> RegisterDict = new HashMap<String, Supplier<Item>>();
    public HashMap<String, Supplier<Potion>> RegisterDict_p = new HashMap<String, Supplier<Potion>>();

    private final DeferredRegister<Item> ITEMS;
    private final DeferredRegister<Potion> POTIONS;
    private final ItemRegisterData[] AllItems;

    public Item_Register(DeferredRegister<Item> _ITEMS, DeferredRegister<Potion> _POTIONS){
        this.ITEMS = _ITEMS;
        this.POTIONS = _POTIONS;
        this.AllItems = new ItemRegisterData[]{
            new HP_Potion(),
            new HP_Potion_Large(),
            new MP_Potion(),
            new MP_Potion_Large(),
            new Elixir(),
            new Coin(),
        };
    }

    public void RegisterAllItems() {
        for (ItemRegisterData itemRegisterData : AllItems){
            String key = itemRegisterData.get_key();
            Item.Properties behaviour = itemRegisterData.get_behaviour();
            // https://stackoverflow.com/questions/79318791/item-texture-blank-in-minecraft-1-21-4-forge-mod
            behaviour.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(Athena.MODID + ":" + key)));
            Supplier<Item> buffer = this.ITEMS.register(key, () -> itemRegisterData.get_binding(behaviour));
            this.RegisterDict.put(key, buffer);
        }
    }
}
