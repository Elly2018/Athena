package com.elly.athena.gui.screen.utility;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ScreenManager {
    private ArrayList<Page> pages = new ArrayList<>();
    private Page active;
    public int mouseX;
    public int mouseY;

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

    public void render(@NotNull GuiGraphics guiGraphics, int x, int y, float tick) {
        if(active != null) active.render(guiGraphics);
    }

    public void tick(){
        if(active != null) active.tick();
    }

    public void RenderBackground(GuiGraphics guiGraphics, int width, int height, int mouseX, int mouseY){
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if(active == null) return;
        guiGraphics.blit(RenderType::guiTextured, active.background,
                active.OffsetWidth(width), active.OffsetHeight(height), 0, 0, active.width, active.height, 256, 256);
    }
}
