package com.elly.athena.gui.screen;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.gui.menu.Skill_Menu;
import com.elly.athena.system.skill.SkillCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

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
    protected IPlayerSkill playerSkill;

    public Skill_Screen(Skill_Menu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        player = playerInventory.player;
    }

    @Override
    protected void init() {
        super.init();
        offsetWidth = (this.width - this.imageWidth) / 2; // 40
        offsetHeight = (this.height - this.imageHeight) / 2; // 45
        playerSkill = player.getData(Attachment_Register.PLAYER_SKILL);
        renderTags_init();
        ChangePage(0, 0);
    }

    @Override
    protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) { }

    @Override
    public void render(GuiGraphics gui, int xMouse, int yMouse, float tick) {
        super.render(gui, xMouse, yMouse, tick);
        this.renderTooltip(gui, xMouse, yMouse);
        renderList(gui);
        renderDescription(gui);
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
}
