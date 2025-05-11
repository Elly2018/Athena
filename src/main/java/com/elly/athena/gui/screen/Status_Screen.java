package com.elly.athena.gui.screen;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.network.general.StatusApplyPayload;
import com.elly.athena.system.BattleSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;

import static com.elly.athena.gui.RenderUtility.drawFont;

public class Status_Screen extends Screen {

    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"textures/gui/container/status.png");

    private static final WidgetSprites PLUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"plus_button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"plus_button_darker"));
    private static final WidgetSprites MINUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"minus_button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"minus_button_darker"));

    private final Player player;
    protected int imageWidth = 253;
    protected int imageHeight = 190;
    protected int offsetWidth;
    protected int offsetHeight;
    protected int ColorLabel = 6579300;
    protected int ColorGold = 12217620;
    protected int ColorGreen = 2867767;
    protected int ColorRed = 14037306;

    private final ArrayList<Integer> adding = new ArrayList<>(4);

    private ImageButton _plus0;
    private ImageButton _plus1;
    private ImageButton _plus2;
    private ImageButton _plus3;
    private ImageButton _minus0;
    private ImageButton _minus1;
    private ImageButton _minus2;
    private ImageButton _minus3;
    private Button _submit;
    private Button _clean;

    public Status_Screen(Player _player) {
        super(Component.empty());
        this.player = _player;
    }

    @Override
    protected void init() {
        super.init();
        offsetWidth = (this.width - this.imageWidth) / 2; // 40
        offsetHeight = (this.height - this.imageHeight) / 2; // 45
        for(int i = 0; i < 4; i++){
            adding.add(i, 0);
        }
        render_statue_point_init();
    }

    @Override
    public void tick() {
        super.tick();
        render_statue_point_update();
    }

    @Override
    public void onClose() {
        super.onClose();
        this.clean_point();
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.blit(RenderType::guiTextured, CONTAINER_BACKGROUND, offsetWidth, offsetHeight, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        render_character(guiGraphics, mouseX, mouseY);
        // The screen size is 256x256
        // The range should be from
        // Min: [40, 45]
        // Max: [216, 211]
    }

    @Override
    public void render(GuiGraphics graphics, int x, int y, float partialTick) {
        super.render(graphics, x, y, partialTick);
        render_header(graphics);
        render_statue_point(graphics, x, y, partialTick);
        render_statue_list(graphics);
    }

    private void render_header(GuiGraphics graphics){
        // We have 70 pixels height to use
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        int InitX = 8 + offsetWidth;
        int InitY = 8 + offsetHeight;
        int Gap = font.lineHeight + 2;
        int SectionGap = font.lineHeight + 5;
        String _level = String.format("%d", status.getLevel());
        String _job = String.format("%s", status.getJob());
        String _coin = String.format("%d", status.getCoin());
        drawFont(graphics, "Level", InitX, InitY, ColorLabel);
        drawFont(graphics, _level, InitX, InitY + Gap, ColorLabel);
        drawFont(graphics, "Job", InitX, InitY + Gap + SectionGap, ColorLabel);
        drawFont(graphics, _job, InitX, InitY + (Gap * 2) + SectionGap, ColorLabel);
        drawFont(graphics, "Coin", InitX, InitY + (Gap * 2) + (SectionGap * 2), ColorLabel);
        drawFont(graphics, _coin, InitX, InitY + (Gap * 3) + (SectionGap * 2), ColorGold);
    }

    private void render_statue_point_init(){
        int InitX = 80 + offsetWidth;
        int InitY = 85 + offsetHeight;
        int ButtonSize = 12;
        int Gap = font.lineHeight + 5;

        _plus0 = new ImageButton(InitX, InitY, ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(0); });
        _plus1 = new ImageButton(InitX, InitY + Gap, ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(1); });
        _plus2 = new ImageButton(InitX, InitY + (Gap * 2), ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(2); });
        _plus3 = new ImageButton(InitX, InitY + (Gap * 3), ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(3); });

        addRenderableWidget(_plus0);
        addRenderableWidget(_plus1);
        addRenderableWidget(_plus2);
        addRenderableWidget(_plus3);

        InitX = 44 + offsetWidth;
        _minus0 = new ImageButton(InitX, InitY, ButtonSize, ButtonSize, MINUS_BUTTON_SPRITES, (k) -> { minus_click(0); });
        _minus1 = new ImageButton(InitX, InitY + Gap, ButtonSize, ButtonSize, MINUS_BUTTON_SPRITES, (k) -> { minus_click(1); });
        _minus2 = new ImageButton(InitX, InitY + (Gap * 2), ButtonSize, ButtonSize, MINUS_BUTTON_SPRITES, (k) -> { minus_click(2); });
        _minus3 = new ImageButton(InitX, InitY + (Gap * 3), ButtonSize, ButtonSize, MINUS_BUTTON_SPRITES, (k) -> { minus_click(3); });

        addRenderableWidget(_minus0);
        addRenderableWidget(_minus1);
        addRenderableWidget(_minus2);
        addRenderableWidget(_minus3);

        InitX = 4 + offsetWidth;
        InitY = 155 + offsetHeight;
        _submit = Button.builder(Component.literal("Submit"), (k) -> { submit_point(); })
                .pos(InitX, InitY).size(45, 17)
                .build();

        InitX = 50 + offsetWidth;
        _clean = Button.builder(Component.literal("Clean"), (k) -> { clean_point(); })
                .pos(InitX, InitY).size(45, 17)
                .build();
        addRenderableWidget(_submit);
        addRenderableWidget(_clean);
        render_statue_point_update();
    }

    private void render_statue_point_update(){
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        boolean p_active = status.getPoint() - totalUsePoint() > 0;
        _plus0.active = p_active;
        _plus1.active = p_active;
        _plus2.active = p_active;
        _plus3.active = p_active;
        _plus0.visible = p_active;
        _plus1.visible = p_active;
        _plus2.visible = p_active;
        _plus3.visible = p_active;

        _minus0.active = adding.get(0) > 0;
        _minus1.active = adding.get(1) > 0;
        _minus2.active = adding.get(2) > 0;
        _minus3.active = adding.get(3) > 0;
        _minus0.visible = adding.get(0) > 0;
        _minus1.visible = adding.get(1) > 0;
        _minus2.visible = adding.get(2) > 0;
        _minus3.visible = adding.get(3) > 0;

        _submit.active = totalUsePoint() > 0;
        _clean.active = totalUsePoint() > 0;
    }

    private void render_statue_point(GuiGraphics graphics, int mouseX, int mouseY, float tick){
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        int InitX = 8 + offsetWidth;
        int InitY = 85 + offsetHeight;
        int Gap = font.lineHeight + 5;
        String _str = String.format("STR: %d", status.getStr());
        String _dex = String.format("DEX: %d", status.getDex());
        String _int = String.format("INT: %d", status.getInt());
        String _luk = String.format("LUK: %d", status.getLuk());
        String _point = String.format("Point: %d", status.getPoint() - totalUsePoint());

        drawFont(graphics, _str, InitX, InitY, ColorLabel);
        drawFont(graphics, _dex, InitX, InitY + Gap, ColorLabel);
        drawFont(graphics, _int, InitX, InitY + (Gap * 2), ColorLabel);
        drawFont(graphics, _luk, InitX, InitY + (Gap * 3), ColorLabel);
        drawFont(graphics, _point, InitX, InitY + (Gap * 4), totalUsePoint() != 0 ? ColorRed : ColorLabel);

        InitX = 62 + offsetWidth;
        InitY = 87 + offsetHeight;
        Gap = font.lineHeight + 5;
        String _str_d = (adding.get(0) > 0 ? "+" : "-") + String.format("%d", Math.abs(adding.get(0)));
        String _dex_d = (adding.get(1) > 0 ? "+" : "-") + String.format("%d", Math.abs(adding.get(1)));
        String _int_d = (adding.get(2) > 0 ? "+" : "-") + String.format("%d", Math.abs(adding.get(2)));
        String _luk_d = (adding.get(3) > 0 ? "+" : "-") + String.format("%d", Math.abs(adding.get(3)));
        if(adding.get(0) != 0) drawFont(graphics, _str_d, InitX, InitY, ColorGreen);
        if(adding.get(1) != 0) drawFont(graphics, _dex_d, InitX, InitY + Gap, ColorGreen);
        if(adding.get(2) != 0) drawFont(graphics, _int_d, InitX, InitY + (Gap * 2), ColorGreen);
        if(adding.get(3) != 0) drawFont(graphics, _luk_d, InitX, InitY + (Gap * 3), ColorGreen);
    }

    private void render_statue_list(GuiGraphics graphics){
        int InitX = 100 + offsetWidth;
        int InitY = 8 + offsetHeight;
        int Gap = font.lineHeight + 5;
        BattleSystem.BattleSystemStruct bss = new BattleSystem.BattleSystemProvider(player).GetSourceBasic();
        String[] texts = new String[]{
                String.format("MaxHP: %d", bss.MaxHP),
                String.format("MaxMP: %d", bss.MaxMP),
                String.format("Attack Speed: %d", bss.AttackSpeed),
                String.format("Physical Damage: %d - %d", bss.MinDamage, bss.MaxDamage),
                String.format("Magic Damage: %d - %d", bss.MinMagicDamage, bss.MaxMagicDamage),
                String.format("Physical Defense: %d", bss.Defense),
                String.format("Magic Defense: %d", bss.MagicDefense),
                String.format("Physical Accuracy: %d", bss.Accuracy),
                String.format("Magic Accuracy: %d", bss.MagicAccuracy),
                String.format("Physical Dodge: %d", bss.Dodge),
                String.format("Magic Dodge: %d", bss.MagicDodge),
        };
        for(int i = 0; i < texts.length; i++){
            drawFont(graphics, texts[i], InitX, InitY + (Gap * i), ColorLabel);
        }
    }

    private void render_character(GuiGraphics graphics, int mouseX, int mouseY){
        int InitX = 54 + offsetWidth;
        int InitY = 8 + offsetHeight;
        int InitX_End = 102 + offsetWidth;
        int InitY_End = 77 + offsetHeight;
        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, InitX, InitY, InitX_End, InitY_End, 30, 0.0625F, mouseX, mouseY, player);
    }

    private int totalUsePoint(){
        int v = 0;
        for(int i = 0; i < adding.size(); i++){
            v += adding.get(i);
        }
        return v;
    }

    private void plus_click(int index){
        int v = adding.get(index);
        adding.set(index, v + 1);
    }

    private void minus_click(int index){
        int v = adding.get(index);
        adding.set(index, v - 1);
    }

    private void submit_point(){
        PlayerStatus ps = new PlayerStatus();
        ps.setStr(adding.get(0));
        ps.setDex(adding.get(1));
        ps.setInt(adding.get(2));
        ps.setLuk(adding.get(3));
        ps.setPoint(-totalUsePoint());
        PacketDistributor.sendToServer(new StatusApplyPayload.StatusApplyData(ps.serializeNBT(player.registryAccess())));
        adding.replaceAll(ignored -> 0);
        this.onClose();
    }

    private void clean_point(){
        adding.replaceAll(ignored -> 0);
    }
}
