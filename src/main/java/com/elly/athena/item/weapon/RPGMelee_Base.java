package com.elly.athena.item.weapon;

import com.elly.athena.data.DataComponent_Register;
import com.elly.athena.data.datacomponent.Upgrade;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

public class RPGMelee_Base extends SwordItem {
    public RPGMelee_Base(float attackDamage, float attackSpeed, Properties properties) {
        super(properties.attributes(GetModify(attackDamage, attackSpeed)));
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        Upgrade c = stack.getComponents().getOrDefault(DataComponent_Register.UPGRADE.get(), new Upgrade(0));
        return Component.literal(String.format("[%d] %s", c.upgrade(), super.getName(stack)));
    }

    private static ItemAttributeModifiers GetModify(float attackDamage, float attackSpeed){
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, (double)(attackDamage), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build().withTooltip(true);
    }
}
