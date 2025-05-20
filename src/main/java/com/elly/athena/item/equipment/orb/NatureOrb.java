package com.elly.athena.item.equipment.orb;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class NatureOrb extends RPGEquip_Header {

    static class NatureOrb_RPG_Equip extends RPGEquip_Base {
        public NatureOrb_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.ORB;
        }
    }

    @Override
    public String get_key() {
        return "orb_nature";
    }

    @Override
    public Item.Properties get_behaviour() {
        return super.get_behaviour()
                .rarity(Rarity.RARE);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new NatureOrb_RPG_Equip(props);
    }
}
