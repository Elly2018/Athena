package com.elly.athena.item.special;

import com.elly.athena.entity.Entity_Register;
import com.elly.athena.item.Item_Register;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class NPCSpawnEgg implements Item_Register.ItemRegisterData {

    static class NPCSpawnEgg_Item extends SpawnEggItem{
        public NPCSpawnEgg_Item(EntityType<? extends Mob> defaultType, Properties properties) {
            super(defaultType, properties);
        }
    }

    @Override
    public String get_key() {
        return "special_npc";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new NPCSpawnEgg_Item(Entity_Register.NPC.get(), props);
    }
}
