package com.elly.athena.item.equipment.neck;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class LavaNecklace extends RPGEquip_Header {

    static class Lava_RPG_Equip extends RPGEquip_Base {
        public Lava_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.NECK;
        }
    }

    @Override
    public String get_key() {
        return "necklace_lava";
    }

    @Override
    public Item.Properties get_behaviour() {
        return super.get_behaviour()
                .rarity(Rarity.RARE);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Lava_RPG_Equip(props);
    }
}
