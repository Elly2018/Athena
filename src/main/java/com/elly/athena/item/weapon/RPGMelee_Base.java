package com.elly.athena.item.weapon;

import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.DataComponent_Register;
import com.elly.athena.data.datacomponent.Upgrade;
import com.elly.athena.item.Item_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

public class RPGMelee_Base extends Item {

    public RPGMelee_Base(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        Upgrade c = stack.getComponents().getOrDefault(DataComponent_Register.UPGRADE.get(), new Upgrade(0));
        if(c.upgrade() == 0) return super.getName(stack);
        return Component.literal(String.format("[%s] %s", c.upgrade() == Item_Register.MAX_UPGRADE ? "MAX" : c.upgrade(), super.getName(stack).getString()));
    }

    public static ItemAttributeModifiers GetModify(float attackDamage, float attackSpeed){
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item_Register.AttackDamage_ID, (double)(attackDamage), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
                .add(Attributes.ATTACK_SPEED,  new AttributeModifier(Item_Register.AttackSpeed_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
                .build().withTooltip(true);
    }

    public Item_Register.ItemRegisterData_Upgrade GetRegister(){
        return null;
    }
}
