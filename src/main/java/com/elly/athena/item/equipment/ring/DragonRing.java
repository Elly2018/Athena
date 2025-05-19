package com.elly.athena.item.equipment.ring;

import com.elly.athena.Athena;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class DragonRing extends RPGEquip_Header {

    private static final ResourceLocation ITEM_MODIFIER = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.dragon.armor");
    private static final ResourceLocation ITEM_MODIFIER2 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.dragon.magic_attack");
    private static final ResourceLocation ITEM_MODIFIER3 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.dragon.magic_attack_max");

    static class DragonRing_RPG_Equip extends RPGEquip_Base {
        public DragonRing_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.RING0;
        }
    }

    @Override
    public String get_key() {
        return "ring_dragon";
    }

    @Override
    public Item.Properties get_behaviour() {
        ItemAttributeModifiers modifier = ItemAttributeModifiers.builder().add(
                Attributes.ARMOR,
                new AttributeModifier(ITEM_MODIFIER, 4, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.ANY
        ).add(
                Attribute_Register.MAGIC_ATTACK,
                new AttributeModifier(ITEM_MODIFIER2, 2, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.ANY
        ).add(
                Attribute_Register.MAGIC_ATTACK_MAX,
                new AttributeModifier(ITEM_MODIFIER3, 4, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.ANY
        ).build().withTooltip(true);;
        return super.get_behaviour()
                .rarity(Rarity.UNCOMMON)
                .attributes(modifier);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new DragonRing_RPG_Equip(props);
    }
}
