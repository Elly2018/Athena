package com.elly.athena.gui.menu;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IBattleHotbar;
import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.gui.GUI_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.system.skill.SkillCategory;
import com.elly.athena.system.skill.SkillData;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Skill_Menu extends AbstractContainerMenu {
    private final ModContainer inventory;
    private final Player player;

    private Container SlotList;
    // Category, Page
    private int[] selected = new int[2];
    private IPlayerSkill playerSkill;
    private IBattleHotbar hotbar;
    private ArrayList<SkillData> PageList = new ArrayList<>();

    public Skill_Menu(int containerId, @Nullable Inventory _inventory) {
        super(GUI_Register.SKILL_MENU.get(), containerId);
        player = _inventory.player;
        inventory = new ModContainer(player);
        init();
    }

    public Skill_Menu(int containerId, Inventory _inventory, Player _player) {
        super(GUI_Register.SKILL_MENU.get(), containerId);
        player = _player;
        inventory = new ModContainer(player);
        init();
    }

    public void ChangeState(int _selected, int _page){
        boolean changed = false;
        if(selected[0] != _selected){
            this.setData(0, _selected);
            UpdateList();
            changed = true;
        }
        if(selected[1] != _page){
            this.setData(1, _page);
            changed = true;
        }
        if(changed) updateSkillSlot();
    }

    private void init(){
        this.addDataSlot(DataSlot.shared(selected, 0)); // 0
        this.addDataSlot(DataSlot.shared(selected, 1)); // 1
        SlotList = new SimpleContainer(5){
            public void setChanged() {
                Skill_Menu.this.slotsChanged(this);
            }
            public @NotNull ItemStack removeItem(int index, int count) {
                return this.getItem(index);
            }
        };
        for(int i = 0; i < 5; ++i) {
            this.addSlot(new Slot(SlotList, i, 8, 18 + i * 19));
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i + 12, 8 + i * 18, 84 + 58));
        }

        playerSkill = player.getData(Attachment_Register.PLAYER_SKILL);
        hotbar = player.getData(Attachment_Register.BATTLE_HOTBAR);
        UpdateList();
        updateSkillSlot();
    }

    private void updateSkillSlot(){
        SkillCategory[] sc = playerSkill.getSkills();
        updateSkillSlot_pre(sc);
        UpdateList();
        int initIndex = selected[1] * 5;
        int leng = initIndex + 5;
        int c = 0;
        for(int i = initIndex; i < PageList.size() && i < leng; i++){
            String name = "item.athena.skill_" + PageList.get(i).Name;
            DeferredHolder<Item,? extends RPGSkill_Base> t = getItemByName(name);
            if(t == null) {
                Athena.LOGGER.warn(String.format("Cannot find skill: %s", name));
                continue;
            }
            ItemStack buffer = new ItemStack(t.get().asItem());
            Slot s = this.slots.get(c);
            s.set(buffer);
            s.setChanged();
            c++;
        }
    }

    private void UpdateList(){
        PageList.clear();
        SkillCategory[] sc = playerSkill.getSkills();
        for (SkillData skillData : sc[selected[0]].Skills){
            if(skillData.Point >= 0){
                PageList.add(skillData);
            }
        }
    }

    private DeferredHolder<Item,? extends RPGSkill_Base> getItemByName(String name){
        var entries = Athena.ITEMS.getEntries();
        for(var entry: entries){
            if(entry.get().getDescriptionId().equals(name) && entry.get() instanceof RPGSkill_Base)
                return (DeferredHolder<Item, ? extends RPGSkill_Base>) entry;
        }
        return null;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        return super.moveItemStackTo(stack, startIndex, endIndex, reverseDirection);
    }

    private void updateSkillSlot_pre(SkillCategory[] sc){
        if(selected[0] >= sc.length){
            this.setData(0, 0);
        }
        for(int i = 0; i < 5; i++){
            Slot s = this.slots.get(i);
            s.set(ItemStack.EMPTY);
            s.setChanged();
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        if(index >= 5) {
            Slot slot = this.getSlot(index);
            slot.set(ItemStack.EMPTY);
            return itemstack;
        }
        Slot skillslot = this.getSlot(index);
        if(skillslot.getItem().isEmpty()) return itemstack;

        for(int i = 0; i < 9; i++){
            Slot targetSlot = this.getSlot(i + 5);
            if(targetSlot.getItem().isEmpty()){
                itemstack = skillslot.getItem().copy();
                this.moveItemStackTo(itemstack, 5, 12, false);
                return itemstack;
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }
}
