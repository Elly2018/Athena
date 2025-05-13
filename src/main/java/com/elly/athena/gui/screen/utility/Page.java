package com.elly.athena.gui.screen.utility;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class Page {

    public final ResourceLocation background;
    public final int width;
    public final int height;

    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<EditBox> editBoxs = new ArrayList<>();
    private ArrayList<Consumer<GuiGraphics>> render = new ArrayList<>();
    private ArrayList<Runnable> tick = new ArrayList<>();

    public Page(ResourceLocation background, int width, int height){
        this.background = background;
        this.width = width;
        this.height = height;
    }

    public void render(@NotNull GuiGraphics guiGraphics) {
        render.forEach(x -> x.accept(guiGraphics));
    }
    public void tick() {
        tick.forEach(Runnable::run);
    }

    public int OffsetWidth(int screenWidth){
        return (screenWidth - this.width) / 2;
    }

    public int OffsetHeight(int screenHeight){
        return  (screenHeight - this.height) / 2;
    }

    public void SetState(boolean value){
        buttons.forEach(x -> {
            x.active = false;
            x.visible = false;
        });
        editBoxs.forEach(x -> {
            x.active = false;
            x.visible = false;
        });
    }

    public void AddRenderCall(Consumer<GuiGraphics> r){ render.add(r); }
    public void CleanRenderCall() { render.clear(); }

    public void AddTickCall(Runnable r){ tick.add(r); }
    public void CleanTickCall(){ render.clear(); }

    public Button Add(Button button){
        buttons.add(button);
        return button;
    }

    public EditBox Add(EditBox editBox){
        editBoxs.add(editBox);
        return editBox;
    }
}
