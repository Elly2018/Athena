package com.elly.athena.gui.screen.utility;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;

import java.util.ArrayList;

public class ScreenManager {
    private ArrayList<Page> pages = new ArrayList<>();
    private Page active;

    public Page Add(Page page){
        if(active == null) active = page;
        pages.add(page);
        return page;
    }

    public void Active(Page page){
        active = page;
        pages.forEach(x -> {
            x.SetState(x == active);
        });
    }

    public void RenderBackground(GuiGraphics guiGraphics, int width, int height){
        if(active == null) return;
        guiGraphics.blit(RenderType::guiTextured, active.background,
                active.OffsetWidth(width), active.OffsetHeight(height), 0, 0, active.width, active.height, 256, 256);
    }
}
