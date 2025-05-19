package com.elly.athena.item.weapon.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class Spear implements Item_Register.ItemRegisterData_Upgrade {

    static class Spear_Item extends RPGMelee_Base {
        public Spear_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public int size() {
        return 11;
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties().stacksTo(1).durability(get_durability(0)).attributes(get_attribute(0));
    }

    @Override
    public String get_key() {
        return "weapon_spear";
    }

    @Override
    public ItemAttributeModifiers get_attribute(int index) {
        return switch(index){
                case 1 ->  RPGMelee_Base.GetModify(7.2F, -3.5F);
                case 2 ->  RPGMelee_Base.GetModify(7.4F, -3.5F);
                case 3 ->  RPGMelee_Base.GetModify(7.6F, -3.5F);
                case 4 ->  RPGMelee_Base.GetModify(7.8F, -3.5F);
                case 5 ->  RPGMelee_Base.GetModify(8.0F, -3.5F);
                case 6 ->  RPGMelee_Base.GetModify(8.3F, -3.5F);
                case 7 ->  RPGMelee_Base.GetModify(8.6F, -3.5F);
                case 8 ->  RPGMelee_Base.GetModify(8.9F, -3.0F);
                case 9 ->  RPGMelee_Base.GetModify(9.5F, -3.0F);
                case 10 -> RPGMelee_Base.GetModify(10.0F, -3.0F);
                default -> RPGMelee_Base.GetModify(7.0f, -3.5F);
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
        return new Item(props);
    }
}
