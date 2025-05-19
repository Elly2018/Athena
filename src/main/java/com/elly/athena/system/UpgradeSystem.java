package com.elly.athena.system;

import com.elly.athena.data.DataComponent_Register;
import com.elly.athena.data.datacomponent.Upgrade;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.weapon.RPGBow_Base;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;
import java.util.Objects;

public class UpgradeSystem {
    public static boolean CheckUpgrade(ItemStack iss){
        return iss.getItem() instanceof RPGMelee_Base || iss.getItem() instanceof RPGBow_Base
                || iss.getItem() instanceof RPGEquip_Base;
    }

    public static boolean UpgradeAction(ItemStack iss){
        if(!CheckUpgrade(iss)) return false;
        int upgrade = Objects.requireNonNull(iss.get(DataComponent_Register.UPGRADE)).upgrade();
        if(upgrade >= Item_Register.MAX_UPGRADE) return false;

        Item_Register.ItemRegisterData_Upgrade buffer = null;
        switch (iss.getItem()) {
            case RPGMelee_Base target -> buffer = target.GetRegister();
            case RPGBow_Base target -> buffer = target.GetRegister();
            case RPGEquip_Base target -> buffer = target.GetRegister();
            default -> {
            }
        }
        if(buffer == null) return false;
        Apply(buffer, upgrade, iss);
        return true;
    }

    private static void Apply(Item_Register.ItemRegisterData_Upgrade buffer, int upgrade, ItemStack iss){
        ItemAttributeModifiers iam = buffer.get_attribute(upgrade + 1);
        int durability = buffer.get_durability(upgrade + 1);
        iss.set(DataComponents.MAX_DAMAGE, durability);
        ItemAttributeModifiers map = iss.getAttributeModifiers();
        List<ItemAttributeModifiers.Entry> item_og = map.modifiers();
        item_og.clear();
        item_og.addAll(iam.modifiers());
        iss.set(DataComponent_Register.UPGRADE, new Upgrade(upgrade + 1));
    }

    private static void Apply(Item_Register.ItemRegisterData_Upgrade_Bow buffer, int upgrade, ItemStack iss){
        if(iss.getItem() instanceof RPGBow_Base target){
            ItemAttributeModifiers iam = buffer.get_attribute(upgrade + 1);
            int durability = buffer.get_durability(upgrade + 1);
            iss.set(DataComponents.MAX_DAMAGE, durability);
            ItemAttributeModifiers map = iss.getAttributeModifiers();
            List<ItemAttributeModifiers.Entry> item_og = map.modifiers();
            iss.set(DataComponent_Register.BOWDATA, target.GetRegister().get_bowdata(upgrade + 1));
            item_og.clear();
            item_og.addAll(iam.modifiers());
            iss.set(DataComponent_Register.UPGRADE, new Upgrade(upgrade + 1));
        }
    }
}
