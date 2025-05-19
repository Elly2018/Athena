package com.elly.athena.item.weapon.common;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class CandyStaff implements Item_Register.ItemRegisterData_Upgrade {
    static class CandyStaff_Item extends RPGMelee_Base {
        public CandyStaff_Item(Properties properties) {
            super(properties);
        }

        @Override
        public Item_Register.ItemRegisterData_Upgrade GetRegister() {
            return new CandyStaff();
        }
    }

    @Override
    public String get_key() {
        return "weapon_candy";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties().stacksTo(1).durability(get_durability(0)).attributes(get_attribute(0));
    }

    @Override
    public int size() {
        return 11;
    }

    @Override
    public ItemAttributeModifiers get_attribute(int index) {
        return switch (index){
                case 1 -> RPGMelee_Base.GetModify(3.2F, -2.5F);
                case 2 -> RPGMelee_Base.GetModify(3.4F, -2.5F);
                case 3 -> RPGMelee_Base.GetModify(3.6F, -2.6F);
                case 4 -> RPGMelee_Base.GetModify(3.8F, -2.6F);
                case 5 -> RPGMelee_Base.GetModify(4.0F, -2.7F);
                case 6 -> RPGMelee_Base.GetModify(4.2F, -2.7F);
                case 7 -> RPGMelee_Base.GetModify(4.4F, -2.8F);
                case 8 -> RPGMelee_Base.GetModify(4.8F, -2.8F);
                case 9 -> RPGMelee_Base.GetModify(5.2F, -2.9F);
                case 10 ->RPGMelee_Base.GetModify(5.5F, -3.0F);
                default -> RPGMelee_Base.GetModify(6.0F, -2.5F);
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
    public Item get_binding(Item.Properties props) {
        return new CandyStaff_Item(props);
    }
}
