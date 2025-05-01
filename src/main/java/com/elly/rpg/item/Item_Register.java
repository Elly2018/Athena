package com.elly.rpg.item;

import com.elly.rpg.blockitem.BlockItems_Register;
import com.elly.rpg.item.potion.HP_Potion;
import com.elly.rpg.item.potion.MP_Potion;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class Item_Register {

    public interface ItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
    }

    public HashMap<String, RegistryObject<Item>> RegisterDict = new HashMap<String, RegistryObject<Item>>();

    private final DeferredRegister<Item> ITEMS;
    private final ItemRegisterData[] AllItems;

    public Item_Register(DeferredRegister<Item> _ITEMS){
        this.ITEMS = _ITEMS;
        this.AllItems = new ItemRegisterData[]{
          new HP_Potion(),
          new MP_Potion()
        };
    }

    public void RegisterAllItems() {
        for (ItemRegisterData itemRegisterData : AllItems){
            String key = itemRegisterData.get_key();
            Item.Properties behaviour = itemRegisterData.get_behaviour();
            RegistryObject<Item> buffer = this.ITEMS.register(key, () -> new Item(behaviour.setId(ITEMS.key(key))));
            this.RegisterDict.put(key, buffer);
        }
    }
}
