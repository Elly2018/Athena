package com.elly.athena.gui.component;

import com.elly.athena.gui.data.LootData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

import java.util.ArrayList;
import java.util.Iterator;

import static com.elly.athena.gui.Hud.LOOTDATA_SIZE;
import static com.elly.athena.gui.Hud.LootDatas;
import static com.elly.athena.gui.Utility.drawFont;

public class PickMessage {
    public static void getPickupMessage(LocalPlayer player, GuiGraphics gui, float tick){
        Update(tick);
        draw(player, gui);
    }

    private static void Update(float tick){
        ArrayList<LootData> live = new ArrayList<>(LOOTDATA_SIZE);
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
        Font font = Minecraft.getInstance().font;
        for (Iterator<LootData> it = LootDatas.iterator(); it.hasNext(); ) {
            var i = it.next();
            String message = String.format("(%s)*%d", i.name, i.amount);
            int w = font.width(message);

            drawFont(gui, message, gui.guiWidth() - w - 30, 150 - (counter * font.lineHeight + 3), i.color);
            counter++;
        }
    }
}
