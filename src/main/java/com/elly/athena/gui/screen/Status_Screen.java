package com.elly.athena.gui.screen;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

import static com.elly.athena.gui.Utility.drawFont;

public class Status_Screen extends Screen {

    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"textures/gui/container/status.png");

    private static final WidgetSprites PLUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"recipe_book/button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"recipe_book/button_highlighted"));
    private static final WidgetSprites MINUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"recipe_book/button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"recipe_book/button_highlighted"));

    private final Player player;
    protected int imageWidth = 176;
    protected int imageHeight = 166;
    private final int containerRows = 5;

    private ArrayList<Integer> adding = new ArrayList<Integer>(4);

    public Status_Screen(Player _player) {
        super(Component.empty());
        this.player = _player;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(RenderType::guiTextured, CONTAINER_BACKGROUND, i, j, 0.0F, 0.0F, this.imageWidth, this.containerRows * 18 + 17, 256, 256);
        guiGraphics.blit(RenderType::guiTextured, CONTAINER_BACKGROUND, i, j + this.containerRows * 18 + 17, 0.0F, 126.0F, this.imageWidth, 96, 256, 256);
    }

    @Override
    public void render(GuiGraphics graphics, int x, int y, float partialTick) {
        super.render(graphics, x, y, partialTick);
        render_statue_point();
        render_statue_list();

        var b1 = Button.builder(Component.literal("Test"), (k) -> {})
                .pos(20, 20)
                .size(20,20)
                .build();
        addRenderableWidget(b1);

        var b2 = Button.builder(Component.literal("Test 2"), (k) -> {})
                .pos(70, 70)
                .size(20,20)
                .build();
        addRenderableWidget(b2);
    }


    private void render_statue_point(GuiGraphics graphics){
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        String _str = String.format("%d", status.getStr());
        String _dex = String.format("%d", status.getDex());
        String _int = String.format("%d", status.getInt());
        String _luk = String.format("%d", status.getLuk());

        drawFont(graphics, _str, 0, 0, 0);
        drawFont(graphics, _dex, 0, 0, 0);
        drawFont(graphics, _int, 0, 0, 0);
        drawFont(graphics, _luk, 0, 0, 0);

        ImageButton _plus0 = new ImageButton(0, 0, 20, 20, PLUS_BUTTON_SPRITES, (k) -> { plus_click(0); });
        ImageButton _plus1 = new ImageButton(0, 0, 20, 20, PLUS_BUTTON_SPRITES, (k) -> { plus_click(1); });
        ImageButton _plus2 = new ImageButton(0, 0, 20, 20, PLUS_BUTTON_SPRITES, (k) -> { plus_click(2); });
        ImageButton _plus3 = new ImageButton(0, 0, 20, 20, PLUS_BUTTON_SPRITES, (k) -> { plus_click(3); });
        ImageButton _minus0 = new ImageButton(0, 0, 20, 20, MINUS_BUTTON_SPRITES, (k) -> { minus_click(0); });
        ImageButton _minus1 = new ImageButton(0, 0, 20, 20, MINUS_BUTTON_SPRITES, (k) -> { minus_click(1); });
        ImageButton _minus2 = new ImageButton(0, 0, 20, 20, MINUS_BUTTON_SPRITES, (k) -> { minus_click(2); });
        ImageButton _minus3 = new ImageButton(0, 0, 20, 20, MINUS_BUTTON_SPRITES, (k) -> { minus_click(3); });
        Button _submit = Button.builder(Component.literal("Submit"), (k) -> { minus_click(3); })
                .pos(0, 0).size(0, 0)
                .build();
        Button _clean = Button.builder(Component.literal("Clean"), (k) -> { minus_click(3); })
                .pos(0, 0).size(0, 0)
                .build();
        addRenderableWidget(_plus0);
        addRenderableWidget(_plus1);
        addRenderableWidget(_plus2);
        addRenderableWidget(_plus3);
        addRenderableWidget(_minus0);
        addRenderableWidget(_minus1);
        addRenderableWidget(_minus2);
        addRenderableWidget(_minus3);
        addRenderableWidget(_submit);
        addRenderableWidget(_clean);

        String _str_d = (adding.get(0) > 0 ? "+" : "-") + String.format("%d", Math.abs(adding.get(0)));
        String _dex_d = (adding.get(1) > 0 ? "+" : "-") + String.format("%d", Math.abs(adding.get(1)));
        String _int_d = (adding.get(2) > 0 ? "+" : "-") + String.format("%d", Math.abs(adding.get(2)));
        String _luk_d = (adding.get(3) > 0 ? "+" : "-") + String.format("%d", Math.abs(adding.get(3)));
        if(adding.get(0) != 0) drawFont(graphics, _str_d, 0, 0, 0);
        if(adding.get(1) != 0) drawFont(graphics, _dex_d, 0, 0, 0);
        if(adding.get(2) != 0) drawFont(graphics, _int_d, 0, 0, 0);
        if(adding.get(3) != 0) drawFont(graphics, _luk_d, 0, 0, 0);
    }


    private void render_statue_list(GuiGraphics graphics){
        Component texts = Component.literal("").append("").append("");
        Font font = Minecraft.getInstance().font;
        OptionsList.renderScrollingString(graphics, font, texts, 0, 0, 0, 0, 0);
    }

    private void plus_click(int index){

    }

    private void minus_click(int index){

    }

    private void submit_point(){

    }

    private void clean_point(){
        adding.replaceAll(ignored -> 0);
    }
}
