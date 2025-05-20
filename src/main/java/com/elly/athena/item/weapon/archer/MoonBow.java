package com.elly.athena.item.weapon.archer;

import com.elly.athena.data.datacomponent.BowData;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGBow_Base;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class MoonBow implements Item_Register.ItemRegisterData_Upgrade_Bow {

    static class MoonBow_Item extends RPGBow_Base {
        public MoonBow_Item(Properties properties) {
            super(properties);
        }

        @Override
        public int getDefaultProjectileRange() {
            return super.getDefaultProjectileRange();
        }

        @Override
        public Item_Register.ItemRegisterData_Upgrade_Bow GetRegister() {
            return new MoonBow();
        }
    }

    @Override
    public BowData get_bowdata(int level) {
        return null;
    }

    @Override
    public int size() {
        return 11;
    }

    @Override
    public ItemAttributeModifiers get_attribute(int level) {
        return Bow.Bow_Item.GetModify(1.0F, -2.0F);
    }

    @Override
    public int get_durability(int level) {
        return switch (level){
            default -> 350;
        };
    }

    @Override
    public String get_key() {
        return "weapon_bow_moon";
    }

    @Override
    public BowItem.Properties get_behaviour() {
        return new SwordItem.Properties().stacksTo(1).durability(get_durability(0)).attributes(get_attribute(0));
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new MoonBow_Item(props);
    }
}
