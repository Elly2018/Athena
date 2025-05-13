package com.elly.athena.item.equipment.ring;

import com.elly.athena.Athena;
import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.equipment.RPGEquip_Base;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class Single_Earring implements Item_Register.ItemRegisterData {

    private static final ResourceLocation ITEM_MODIFIER = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "modifier.single_earring.armor");

    static class Single_Earring_Equip extends RPGEquip_Base {
        public Single_Earring_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.RING0;
        }
    }

    @Override
    public String get_key() {
        return "ring_single_earring";
    }

    @Override
    public Item.Properties get_behaviour() {
        ItemAttributeModifiers modifier = ItemAttributeModifiers.builder().add(
                Attributes.ARMOR,
                new AttributeModifier(ITEM_MODIFIER, 1, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.ANY
        ).build();
        return new Item.Properties()
                .stacksTo(1)
                .attributes(modifier);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Single_Earring_Equip(props);
    }
}
