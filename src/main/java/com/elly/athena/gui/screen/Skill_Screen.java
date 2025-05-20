package com.elly.athena.gui.screen;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.gui.menu.Skill_Menu;
import com.elly.athena.network.general.SkillApplyPayload;
import com.elly.athena.system.skill.SkillCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Objects;

import static com.elly.athena.gui.RenderUtility.drawFont;

public class Skill_Screen extends AbstractContainerScreen<Skill_Menu> {

    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"textures/gui/container/skill.png");

    private static final WidgetSprites PLUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"plus_button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"plus_button_darker"));
    private static final WidgetSprites MINUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"minus_button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"minus_button_darker"));

    private final Player player;

    protected int offsetWidth;
    protected int offsetHeight;
    protected int selected;
    protected int page;
    protected int ColorLabel = 6579300;
    protected IPlayerSkill playerSkill;

    protected ImageButton _plus0;
    protected ImageButton _plus1;
    protected ImageButton _plus2;
    protected ImageButton _plus3;
    protected ImageButton _plus4;
    protected Button _last;
    protected Button _next;
    ArrayList<Button> _tags = new ArrayList<>();
    int mouseX;
    int mouseY;

    public Skill_Screen(Skill_Menu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        player = playerInventory.player;
    }

    @Override
    protected void init() {
        this.imageWidth = 239;
        this.imageHeight = 166;
        offsetWidth = (this.width - this.imageWidth) / 2; // 40
        offsetHeight = (this.height - this.imageHeight) / 2; // 45
        playerSkill = player.getData(Attachment_Register.PLAYER_SKILL);
        renderTags_init();
        ChangePage(0, 0);

        int InitX = 123 + offsetWidth;
        int InitY = 21 + offsetHeight;
        int Gap = 19;
        int ButtonSize = 12;

        _plus0 = new ImageButton(InitX, InitY, ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(0); });
        _plus1 = new ImageButton(InitX, InitY + Gap, ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(1); });
        _plus2 = new ImageButton(InitX, InitY + (Gap * 2), ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(2); });
        _plus3 = new ImageButton(InitX, InitY + (Gap * 3), ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(3); });
        _plus4 = new ImageButton(InitX, InitY + (Gap * 3), ButtonSize, ButtonSize, PLUS_BUTTON_SPRITES, (k) -> { plus_click(3); });

        _last = Button.builder(Component.literal("<"), this::last).pos(7 + offsetWidth, 125 + offsetHeight).size(20, 10).build();
        _next = Button.builder(Component.literal(">"), this::next).pos(121 + offsetWidth, 125 + offsetHeight).size(20, 10).build();

        addRenderableWidget(_plus0);
        addRenderableWidget(_plus1);
        addRenderableWidget(_plus2);
        addRenderableWidget(_plus3);
        addRenderableWidget(_plus4);

        addRenderableWidget(_last);
        addRenderableWidget(_next);
        super.init();
    }

    @Override
    protected void containerTick() {
        IPlayerStatus ips = player.getData(Attachment_Register.PLAYER_STATUS);
        ImageButton[] ibs = new ImageButton[]{
                _plus0, _plus1, _plus2, _plus3, _plus4
        };
        int point = ips.getPoint();
        for(int i = 0; i < 5; i++){
            Slot slot = this.menu.getSlot(i);
            ibs[i].visible = !slot.getItem().isEmpty() && point > 0;
        }
        _last.active = this.menu.flags[0] > 0;
        _next.active = this.menu.flags[1] > 0;
        for (int i = 0; i < _tags.size(); i++) {
            Button tag = _tags.get(i);
            tag.active = this.menu.selected[0] != i;
        }
    }

    @Override
    protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) { }

    @Override
    public void render(GuiGraphics gui, int xMouse, int yMouse, float tick) {
        super.render(gui, xMouse, yMouse, tick);
        this.renderTooltip(gui, xMouse, yMouse);
        mouseX = xMouse;
        mouseY = yMouse;
        renderList(gui);
        renderDescription(gui);
        renderPoint(gui);
        renderLabel(gui);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        guiGraphics.blit(RenderType::guiTextured, CONTAINER_BACKGROUND,
                offsetWidth, offsetHeight, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    private void renderTags_init(){
        int InitX = 8;
        int InitY = 3;
        SkillCategory[] sc = playerSkill.getSkills();
        for (int i = 0; i < sc.length; i++) {
            SkillCategory skillCategory = sc[i];
            int size = font.width(skillCategory.Name);
            Button button = Button.builder(Component.literal(String.valueOf(i)), this::TagPressed)
                    .pos(InitX + offsetWidth, InitY + offsetHeight)
                    .size(size, 10)
                    .build();
            addRenderableWidget(button);
            _tags.add(button);
            InitX += button.getWidth() + 5;
        }
    }

    private void renderList(GuiGraphics gui){
        int color = 10862842;
        int InitX = 37;
        int InitY = 18;
        drawFont(gui, "", 37, 18, color);
    }

    private void renderDescription(GuiGraphics gui){

    }

    private void renderPoint(GuiGraphics gui){
        IPlayerStatus ips = player.getData(Attachment_Register.PLAYER_STATUS);
        int point = ips.getPoint();
        drawFont(gui, String.valueOf(point), 62 + offsetWidth, 128 + offsetHeight, 6579300);
    }

    private void renderLabel(GuiGraphics gui){
        for(int i = 0; i < 5; i++){
            Slot slot = this.menu.getSlot(i);
            if(!slot.getItem().isEmpty()){
                String name = slot.getItem().getItemName().getString();
                drawFont(gui, name, 25 + offsetWidth, 22 + (19 * i) + offsetHeight, 6579300);
            }
        }
    }

    private void TagPressed(Button cate){
        String cateName = cate.getMessage().getString();
        SkillCategory[] sc = playerSkill.getSkills();
        for(int i = 0; i < sc.length; i++){
            if(Objects.equals(sc[i].Name, cateName)){
                ChangePage(i, 0);
                return;
            }
        }
        ChangePage(0, 0);
    }

    @Override
    public void onClose() {
        ChangePage(0, 0);
        super.onClose();
    }

    private void ChangePage(int _selected, int _page){
        selected = _selected;
        page = _page;
        this.menu.ChangeState(selected, page);
    }

    private void plus_click(int index){
        Slot slot = this.menu.getSlot(index);
        ItemStack stack = slot.getItem();
        if(stack.isEmpty()) return;;
        String name = stack.getItem().getDescriptionId().replace("item.athena.skill_", "");
        PacketDistributor.sendToServer(new SkillApplyPayload.SkillApplyData(SkillApplyPayload.Generate(name)));
    }

    private void last(Button button){

    }

    private void next(Button button){

    }

    private boolean isHover(int index){
        return false;
    }
}
