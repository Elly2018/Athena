package com.elly.athena.gui.component;

import com.elly.athena.gui.data.LootData;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

import java.util.ArrayList;
import java.util.Iterator;

import static com.elly.athena.gui.Hud.LootDatas;
import static com.elly.athena.gui.Utility.drawFont;

public class PickMessage {
    public static void getPickupMessage(LocalPlayer player, GuiGraphics gui, float tick){
        Update(tick);
        draw(player, gui);
    }

    private static void Update(float tick){
        ArrayList<LootData> live = new ArrayList<>(5);
        for (Iterator<LootData> it = LootDatas.iterator(); it.hasNext(); ) {
            var i = it.next();
            i.timer -= tick;
            if(i.timer > 0){
                live.add(i);
            }
        }
        LootDatas.clear();
        LootDatas.addAll(live);
    }

    private static void draw(LocalPlayer player, GuiGraphics gui){
        int counter = 0;
        for (Iterator<LootData> it = LootDatas.iterator(); it.hasNext(); ) {
            var i = it.next();
            String message = String.format("(%s)*%d", i.name, i.amount);
            drawFont(gui, message, 200, 200, i.color);
            counter++;
        }
    }
}
