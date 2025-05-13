package com.elly.athena.item.special.setting;

import com.elly.athena.entity.npc.RPGNPC;
import com.elly.athena.gui.menu.NPC_Menu;
import com.elly.athena.item.Item_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class NPCStaff implements Item_Register.ItemRegisterData {
    public static class NPCStaff_Item extends Item {
        public NPCStaff_Item(Properties properties) {
            super(properties);
        }

        public void useStaff(Player player, RPGNPC entity){
            player.openMenu(new SimpleMenuProvider(
                    NPC_Menu::new,
                    Component.empty()
            ));
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
