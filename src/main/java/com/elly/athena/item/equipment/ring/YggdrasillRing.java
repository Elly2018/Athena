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

public class YggdrasillRing extends RPGEquip_Header {

    private static final ResourceLocation ITEM_MODIFIER = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.yggdrasill.armor");
    private static final ResourceLocation ITEM_MODIFIER1 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.yggdrasill.magic_armor");
    private static final ResourceLocation ITEM_MODIFIER2 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.yggdrasill.max_hp");
    private static final ResourceLocation ITEM_MODIFIER3 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.yggdrasill.max_mp");
    private static final ResourceLocation ITEM_MODIFIER4 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.yggdrasill.damage");
    private static final ResourceLocation ITEM_MODIFIER5 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.yggdrasill.max_damage");
    private static final ResourceLocation ITEM_MODIFIER6 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.yggdrasill.magic");
    private static final ResourceLocation ITEM_MODIFIER7 = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "item.modifier.ring.yggdrasill.max_magic");

    static class YggdrasillRing_RPG_Equip extends RPGEquip_Base {
        public YggdrasillRing_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.RING0;
        }
    }

    @Override
    public String get_key() {
        return "ring_yggdrasill";
    }

    @Override
    public Item.Properties get_behaviour() {
        ItemAttributeModifiers modifier = ItemAttributeModifiers.builder().add(
                Attributes.ARMOR,
                new AttributeModifier(ITEM_MODIFIER, 8, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.ANY
        ).add(
                Attribute_Register.MAGIC_ARMOR,
                new AttributeModifier(ITEM_MODIFIER1, 8, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.ANY
        ).add(
                Attributes.MAX_HEALTH,
                new AttributeModifier(ITEM_MODIFIER2, 1.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                EquipmentSlotGroup.ANY
        ).add(
                Attribute_Register.MANA_MAX,
                new AttributeModifier(ITEM_MODIFIER3, 1.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                EquipmentSlotGroup.ANY
        ).add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(ITEM_MODIFIER4, 1.6F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                EquipmentSlotGroup.ANY
        ).add(
                Attribute_Register.DAMAGE_MAX,
                new AttributeModifier(ITEM_MODIFIER5, 2.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                EquipmentSlotGroup.ANY
        ).add(
                Attribute_Register.MAGIC_ATTACK,
                new AttributeModifier(ITEM_MODIFIER6, 1.6F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                EquipmentSlotGroup.ANY
        ).add(
                Attribute_Register.MAGIC_ATTACK_MAX,
                new AttributeModifier(ITEM_MODIFIER7, 2.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                EquipmentSlotGroup.ANY
        ).build().withTooltip(true);
        return super.get_behaviour()
                .attributes(modifier)
                .rarity(Rarity.EPIC);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new YggdrasillRing_RPG_Equip(props);
    }
}
