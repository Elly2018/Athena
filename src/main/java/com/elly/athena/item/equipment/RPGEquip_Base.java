package com.elly.athena.item.equipment;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.warrior.DiamondMace;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class RPGEquip_Base extends Item {
    public ModEquipmentSlot slot = ModEquipmentSlot.BELT;

    public RPGEquip_Base(Properties properties) {
        super(properties);
    }

    public boolean canEquip(ModEquipmentSlot target){
        return ModEquipmentSlot.checkEquipable(target.index, slot.index);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public Item_Register.ItemRegisterData_Upgrade GetRegister(){
        return null;
    }
}
