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

public class Staff implements Item_Register.ItemRegisterData_Upgrade {

    private static final ResourceLocation modifier = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "modifier.weapon_staff.magic_attack");

    public static class Staff_Item extends RPGMagic_Base {
        public Staff_Item(float attackDamage, float attackSpeed, Properties properties) {
            super(attackDamage, attackSpeed, properties);
        }
    }

    @Override
    public String get_key() {
        return "weapon_staff";
    }

    @Override
    public SwordItem.Properties[] get_behaviours() {
        ItemAttributeModifiers iam = ItemAttributeModifiers.builder()
                .add(Attribute_Register.MAGIC_ATTACK, new AttributeModifier(modifier, 6, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
                .add(Attribute_Register.MAGIC_ATTACK_MAX, new AttributeModifier(modifier, 10, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
                .build();
        SwordItem.Properties[] r = {
                new SwordItem.Properties().stacksTo(1).attributes(iam).durability(300)
        };
        return r;
    }

    @Override
    public SwordItem get_binding(int index, Item.Properties props) {
        return switch (index) {
            case 1 -> new Staff_Item(2, -3.0F, props);
            default -> new Staff_Item(2, -3.0F, props);
        };

    }
}

