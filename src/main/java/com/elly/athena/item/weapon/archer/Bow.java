package com.elly.athena.item.weapon.archer;

import com.elly.athena.data.DataComponent_Register;
import com.elly.athena.data.datacomponent.BowData;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGBow_Base;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class Bow implements Item_Register.ItemRegisterData_Upgrade_Bow {

    static class Bow_Item extends RPGBow_Base {
        public Bow_Item(Properties properties) {
            super(properties);
        }

        @Override
        public Item_Register.ItemRegisterData_Upgrade_Bow GetRegister() {
            return new Bow();
        }
    }

    @Override
    public BowData get_bowdata(int level) {
        return switch (level){
            case 1, 2, 3, 4 -> new BowData(18);
            case 5, 6, 7, 8 -> new BowData(16);
            case 9, 10 -> new BowData(14);
            default -> new BowData(20);
        };
    }

    @Override
    public int size() {
        return 11;
    }

    @Override
    public ItemAttributeModifiers get_attribute(int index) {
        return switch (index){
            default -> RPGBow_Base.GetModify(-1.0F, -3.0F);
        };
    }

    @Override
    public int get_durability(int index) {
        return switch (index){
            default -> 150;
        };
    }
    
    @Override
    public String get_key() {
        return "weapon_bow";
    }

    @Override
    public BowItem.Properties get_behaviour() {
        return new SwordItem.Properties().stacksTo(1).durability(get_durability(0))
                .component(DataComponent_Register.BOWDATA, new BowData(20));
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Bow_Item(props);
    }
}
