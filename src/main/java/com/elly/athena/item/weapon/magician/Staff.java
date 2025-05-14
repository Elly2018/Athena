package com.elly.athena.item.weapon.magician;

import com.elly.athena.Athena;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMagic_Base;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class Staff implements Item_Register.ItemRegisterData {

    private static final ResourceLocation modifier = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "modifier.weapon_staff.magic_attack");

    public static class Staff_Item extends RPGMagic_Base {
        public Staff_Item(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
            super(material, attackDamage, attackSpeed, properties);
        }
    }

    @Override
    public String get_key() {
        return "weapon_staff";
    }

    @Override
    public SwordItem.Properties get_behaviour() {
        ItemAttributeModifiers iam = ItemAttributeModifiers.builder()
                .add(Attribute_Register.MAGIC_ATTACK, new AttributeModifier(modifier, 6, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
                .build();
        return new SwordItem.Properties()
                .stacksTo(1)
                .attributes(iam)
                .durability(300);
    }

    @Override
    public SwordItem get_binding(Item.Properties props) {
        return new Staff_Item(ToolMaterial.WOOD, 2, -3.0F, props);
    }
}
