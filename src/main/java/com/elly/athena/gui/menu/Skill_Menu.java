package com.elly.athena.gui.menu;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IBattleHotbar;
import com.elly.athena.data.interfaceType.IPlayerSkill;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.gui.GUI_Register;
import com.elly.athena.item.Item_Register;
import com.elly.athena.system.skill.SkillCategory;
import com.elly.athena.system.skill.SkillData;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Skill_Menu extends AbstractContainerMenu {
    private final ModContainer inventory;
    private final Player player;

    private Container SlotList;
    private int[] selected = new int[2];
    private IPlayerSkill playerSkill;
    private IBattleHotbar hotbar;

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
        this.setData(0, _selected);
        this.setData(1, _page);
        updateSkillSlot();
    }

    private void init(){
        this.addDataSlot(DataSlot.shared(selected, 0)); // 0
        this.addDataSlot(DataSlot.shared(selected, 1)); // 1
        SlotList = new SimpleContainer(5){
            public void setChanged() {
                Skill_Menu.this.slotsChanged(this);
            }
        };
        for(int i = 0; i < 5; ++i){
            this.addSlot(new Slot(SlotList, i, 8, 18 + i * 19));
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i + 12, 8 + i * 18, 84 + 58));
        }

        playerSkill = player.getData(Attachment_Register.PLAYER_SKILL);
        hotbar = player.getData(Attachment_Register.BATTLE_HOTBAR);
        updateSkillSlot();
    }

    private void updateSkillSlot(){
        SkillCategory[] sc = playerSkill.getSkills();
        updateSkillSlot_pre(sc);
        List<SkillData> sd = sc[selected[0]].Skills.stream().filter(x -> x.Point >= 0).toList();
        int initIndex = selected[1] * 5;
        int leng = initIndex + 5;
        int c = 0;
        for(int i = initIndex; i < sd.size() && i < leng; i++){
            String name = sd.get(i).Name;
            if(!Item_Register.RegisterDict.containsKey(name)) {
                continue;
            }
            Supplier<Item> t = Item_Register.RegisterDict.get(name);

            ItemStack buffer = new ItemStack(t.get().asItem(), 1);
            Slot s = this.slots.get(c);
            s.set(buffer);
            s.setChanged();
            c++;
        }
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
        if(index >= 5) return itemstack;
        Slot skillslot = (Slot)this.slots.get(index);

        int empty = -1;
        for(int i = 0; i < 9; i++){
            ItemStack iss = this.slots.get(i + 5).getItem();
            if(iss.isEmpty()){
                empty = i;
                break;
            }
        }
        if(empty == -1) return itemstack;

        itemstack = skillslot.getItem().copy();

        Slot hotbar_slot = this.slots.get(empty + 5);
        hotbar_slot.set(itemstack);
        hotbar_slot.setChanged();

        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }
}
