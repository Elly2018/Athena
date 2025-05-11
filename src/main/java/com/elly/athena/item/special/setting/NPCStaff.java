package com.elly.athena.item.special.setting;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class NPCStaff implements Item_Register.ItemRegisterData {

    static class NPCStaff_Item extends Item {

        public NPCStaff_Item(Properties properties) {
            super(properties);
        }

        @Override
        public InteractionResult use(Level level, Player player, InteractionHand hand) {
            if(!player.isLocalPlayer()){

            }
            return super.use(level, player, hand);
        }
    }

    @Override
    public String get_key() {
        return "npc_setting_staff";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new NPCStaff_Item(props);
    }
}
