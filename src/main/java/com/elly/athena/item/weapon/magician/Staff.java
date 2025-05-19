package com.elly.athena.item.weapon.magician;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMagic_Base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class Staff implements Item_Register.ItemRegisterData_Upgrade {
    public static class Staff_Item extends RPGMagic_Base {
        public Staff_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public int size() {
        return 11;
    }

    @Override
    public String get_key() {
        return "weapon_staff";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties().stacksTo(1).durability(get_durability(0)).attributes(get_attribute(0));
    }

    @Override
    public ItemAttributeModifiers get_attribute(int index) {
        return switch(index){
                case 1 -> RPGMagic_Base.GetModify(2, 5.2F, -3.4F);
                case 2 -> RPGMagic_Base.GetModify(2, 5.4F, -3.4F);
                case 3 -> RPGMagic_Base.GetModify(2, 5.6F, -3.4F);
                case 4 -> RPGMagic_Base.GetModify(2, 5.8F, -3.4F);
                case 5 -> RPGMagic_Base.GetModify(2, 6.0F, -3.4F);
                case 6 -> RPGMagic_Base.GetModify(2, 6.2F, -3.4F);
                case 7 -> RPGMagic_Base.GetModify(2, 6.4F, -3.4F);
                case 8 -> RPGMagic_Base.GetModify(2, 6.6F, -3.4F);
                case 9 -> RPGMagic_Base.GetModify(2, 7.0F, -3.2F);
                case 10 -> RPGMagic_Base.GetModify(2, 7.5F, -3.0F);
                default -> RPGMagic_Base.GetModify(2, 5.0F, -3.4F);
        };
    }

    @Override
    public int get_durability(int index) {
        return switch(index) {
                case 1 -> 150;
                case 2 -> 180;
                case 3 -> 210;
                case 4 -> 240;
                case 5 -> 270;
                case 6 -> 300;
                case 7 -> 330;
                case 8 -> 360;
                case 9 -> 390;
                case 10 -> 420;
                default -> 450;
        };
    }

    @Override
    public SwordItem get_binding(Item.Properties props) {
        return new Staff_Item(props);
    }
}

