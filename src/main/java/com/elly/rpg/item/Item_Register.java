package com.elly.rpg.item;

import com.elly.rpg.item.potion.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class Item_Register {

    public interface ItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
        Item get_binding(Item.Properties props);
    }

    public HashMap<String, RegistryObject<Item>> RegisterDict = new HashMap<String, RegistryObject<Item>>();
    public HashMap<String, RegistryObject<Potion>> RegisterDict_p = new HashMap<String, RegistryObject<Potion>>();

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
            new Elixir()
        };
    }

    public void RegisterAllItems() {
        for (ItemRegisterData itemRegisterData : AllItems){
            String key = itemRegisterData.get_key();
            Item.Properties behaviour = itemRegisterData.get_behaviour();
            RegistryObject<Item> buffer = this.ITEMS.register(key, () -> itemRegisterData.get_binding(behaviour.setId(ITEMS.key(key))));
            this.RegisterDict.put(key, buffer);
        }
    }
}
